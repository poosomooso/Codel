package reflection.testcases;

import reflection.testcases.TestCase;

public class ReturningTestCase implements TestCase {
    private Object param;
    private Object correctReturn;
    @Override
    public void getParams() {

    }

    @Override
    public boolean checkAnswer(Object o) {
        return false;
    }
}
