package reflection.testcases;

import reflection.Answer;
import reflection.TypeValuePair;

public class ReturningTestCase implements TestCase {
    private TypeValuePair param;
    private TypeValuePair correctReturn;

    public ReturningTestCase(TypeValuePair param, TypeValuePair correctReturn) {
        this.param = param;
        this.correctReturn = correctReturn;
    }
    @Override
    public Object getParams() {
        return param.getJavaValue();
    }

    @Override
    public boolean checkAnswer(Answer a) {
        Object o = a.execute(param);
        return correctReturn.getJavaValue().equals(o); // should work for autoboxed primitives and Strings
    }

    public String toString() {
        return "Parameter: " + param.getGivenValue() + " || Return : " + correctReturn.getGivenValue();
    }
}
