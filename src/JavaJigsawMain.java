import reflection.Problem;
import ui.ActivityWindow;

import javax.swing.*;
import java.awt.*;

// TODO:
// test cases -- execute arbitrary test cases
// load random problems
// opening screen to select level and topic
public class JavaJigsawMain {
    public static void main(String[] args) {
        Problem p = Problem.parseFile("");
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                         UnsupportedLookAndFeelException ignored) {
                }

                JFrame frame = new JFrame("Codel");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new ActivityWindow(p));
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }

        });
    }
}
