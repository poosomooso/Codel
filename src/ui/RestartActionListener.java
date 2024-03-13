package ui;

import reflection.Answer;
import reflection.AnswerCompilationException;
import reflection.Problem;
import reflection.testcases.TestCase;

import javax.swing.*;
import java.awt.dnd.DropTarget;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RestartActionListener implements ActionListener {

    private final ArrayList<String> code;
    private JPanel dt;

    public RestartActionListener(JPanel dt, ArrayList<String> code) {
        this.dt = dt;
        this.code = code;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dt.removeAll();
        code.clear();
        dt.repaint();
    }
}
