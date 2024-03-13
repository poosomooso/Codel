package ui;

import reflection.Answer;
import reflection.AnswerCompilationException;
import reflection.Problem;
import reflection.testcases.TestCase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SubmitActionListener implements ActionListener {

    private Problem problem;
    private ArrayList<String> generatedCode;

    public SubmitActionListener(Problem p, ArrayList<String> code) {
        problem = p;
        generatedCode = code;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Answer a = null;
        boolean correct = true;
        String message = "";
        try {
            a = new Answer(problem.getCompilableMethodSignature(), String.join(" ", generatedCode));
            for (TestCase tc : problem.getTestCases()) {
                boolean ac = tc.checkAnswer(a);
                System.out.println(ac);
                message += "Test case " + tc.toString();
                if (!ac) {
                    correct = false;
                    message += " : was not correct\n";
                } else {
                    message += " : was correct!\n";
                }
            }
        } catch (AnswerCompilationException ex) {
            correct = false;
            message += "Failed to compile: " + ex.getFailure();
        }

        System.out.println(message); // TODO: open window

    }
}
