package reflection;

import reflection.testcases.TestCase;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.*;

public class Problem {

    private static final Pattern FILE_STRUCTURE = Pattern.compile(
            "(?<desc>.*)"
                    + "(\\s+){2}" // two newlines
                    + "(?<testcases>.*)"
                    + "(\\s+){2}"
                    + "(?<signature>public (?<returntype>\\w+) [\\w_$]+\\((?<params>.*)\\))"
                    + "\\s*",
            Pattern.DOTALL // . includes new lines
    );

    public static Problem parseFile(String url) {
        String s = "";
        try {
            s = Files.readString(Path.of("problems/array/level1/firstElementBoolean.txt"));
        } catch (IOException e) {
            throw new IllegalArgumentException("File " + url + "is not found");
        }

        Matcher m = FILE_STRUCTURE.matcher(s);
        System.out.println("File Structure valid: " + m.matches());
        String desc = m.group("desc");
        String testCasesFull = m.group("testcases");
        String signature = m.group("signature");
        String returntype = m.group("returntype");
        String params = m.group("params");

        // assuming one parameter
        String paramType = params.strip().split(" ")[0];

        Problem p = new Problem(desc, signature,
                TypeValuePair.classFromString(paramType),
                TypeValuePair.classFromString(returntype),
                "func"); // TODO: maybe one day i will actually parse the method name

        String[] testCaseLines = testCasesFull.strip().split("[\\r\\n]+");
        for (String line : testCaseLines) {
            try {
                p.addTestCase(TestCase.parseTestCase(line, returntype, paramType));
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                     IllegalAccessException e) {
                throw new IllegalStateException(e);
            }
        }

        return p;
    }

    public static void main(String[] args) throws AnswerCompilationException {
        parseFile("");

        Answer a = new Answer("public boolean firstElementBoolean(boolean[] arr)", "return arr[0];");
        //System.out.println(a.execute(new boolean[]{false, false, false}));
    }

    private final String problemDescription;
    private ArrayList<TestCase> testCases;
    private final String methodSignature;
    private final Type paramType;
    private final Type returnType;
    private final String methodName;

    public Problem(String problemDescription, String methodSignature, Type paramType, Type returnType, String methodName) {
        testCases = new ArrayList<>();
        this.problemDescription = problemDescription;
        this.methodSignature = methodSignature;
        this.paramType = paramType;
        this.returnType = returnType;
        this.methodName = methodName;
    }

    public void addTestCase(TestCase tc) {
        testCases.add(tc);
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }


    public String getProblemDescription() {
        return problemDescription;
    }

    public String getMethodSignature() {
        return methodSignature;
    }

    public String getCompilableMethodSignature() {
        return "public " + returnType.getName() + " " + methodName + "(" + paramType.getName() + " arr)";
    }

    public Type getParamType() {
        return paramType;
    }

    public Type getReturnType() {
        return returnType;
    }
}
