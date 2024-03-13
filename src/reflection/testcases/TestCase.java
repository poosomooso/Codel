package reflection.testcases;

import reflection.Answer;
import reflection.TypeValuePair;

import java.lang.reflect.InvocationTargetException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface TestCase {
    public static final Pattern TEST_CASE_PATTERN = Pattern.compile("[\\w_$]+\\((?<params>.*)\\)\\s*->\\s*(?<result>.*)");

    /** for test cases with one parameter only */
    static TestCase parseTestCase(String line, String returnType, String paramType) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (returnType.strip().equals("void")) {
            return new VoidTestCase();
        } else {
            // firstElementBoolean([true, false, false]) -> true
            Matcher m = TEST_CASE_PATTERN.matcher(line.strip());
            if (!m.matches()) {
                throw new IllegalArgumentException("Test case " + line + " is not valid");
            }

            String params = m.group("params");
            String result = m.group("result");

            TypeValuePair retPair = TypeValuePair.parseTypeValuePair(returnType, result);
            TypeValuePair paramPair = TypeValuePair.parseTypeValuePair(paramType, params);
            return new ReturningTestCase(paramPair, retPair);
        }

    }

    public Object getParams();

    public boolean checkAnswer(Answer a);
}
