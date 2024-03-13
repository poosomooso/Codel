package reflection.testcases;

import reflection.Answer;

public class VoidTestCase implements TestCase {
    @Override
    public Object getParams() {
        return null;
    }

    @Override
    public boolean checkAnswer(Answer a) {
        return false;
    }
}
