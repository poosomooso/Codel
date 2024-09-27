package ui;

import reflection.Problem;

import javax.swing.*;
import java.awt.*;

public class WindowLauncher {
    private static JFrame frame;

    public static void closePickerLaunchActivity(String problemFileName) {
        frame.dispose();
        Problem p = Problem.parseFile(problemFileName);
    }

    public static void launchActivity(Problem p) {
        ActivityWindow aw = new ActivityWindow(p);
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                         UnsupportedLookAndFeelException ignored) {
                }

                frame = new JFrame("Java Jigsaw");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(aw);

                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }

        });
    }

    public static void launchPicker() { // returns problem name
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                         UnsupportedLookAndFeelException ignored) {
                }

                frame = new JFrame("Java Jigsaw");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new ProblemPickerWindow());

                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }

        });
    }
}
