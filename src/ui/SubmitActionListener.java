package ui;

import reflection.Answer;
import reflection.Problem;

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
        Answer a = new Answer(problem.getMethodSignature(), String.join(" ", generatedCode));
        System.out.println(a.execute(new boolean[]{false, false, false}));
    }
}
