package reflection;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.*;

public class Problem<P, R> {

    private static Pattern fileStructure = Pattern.compile(
            "(?<desc>.*)"
                    + "(\\s+){2}" // two newlines
                    + "(?<testcases>.*)"
                    + "(\\s+){2}"
                    + "(?<signature>public (?<returntype>\\w+) [\\w_$]+\\((?<params>.*)\\))"
                    + "\\s*",
            Pattern.DOTALL // . includes new lines
    );
    public static void parseFile(String url) {
        String s = "";
        try {
            s = Files.readString(Path.of("problems/array/level1/firstElementBoolean.txt"));
        } catch (IOException e) {
            throw new IllegalArgumentException("File " + url + "is not found");
        }

        Matcher m = fileStructure.matcher(s);
        System.out.println("File Structure valid: " + m.matches());
        String desc = m.group("desc");
        String testCasesFull = m.group("testcases");
        String signature = m.group("signature");
        String returntype = m.group("returntype");
        String params = m.group("params");

        Class retClass;

        String cappedReturnType = returntype.substring(0,1).toUpperCase() + returntype.substring(1);
        try {
            retClass = Class.forName("java.lang." + cappedReturnType);
        } catch (ClassNotFoundException e2) {
            System.out.println(returntype);
            System.out.println("Not a class");
            throw new IllegalStateException(e2);
        }

    }

    public static void main(String[] args) {
        parseFile("");

        Answer a = new Answer("public boolean firstElementBoolean(boolean[] arr)", "return arr[0];");
        System.out.println(a.execute(new boolean[]{false, false, false}));
    }

    private String problemDescription;
    private Map<P, R> testCases;
    private String methodSignature;

    public Problem() {

    }

    public void addTestCase(P params, R returnVal) {
        testCases.put(params, returnVal);
    }


}
