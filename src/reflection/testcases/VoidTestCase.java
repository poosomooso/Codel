package reflection.testcases;

import reflection.testcases.TestCase;

public class VoidTestCase implements TestCase {
    @Override
    public void getParams() {

    }

    @Override
    public boolean checkAnswer(Object o) {
        return false;
    }
}
