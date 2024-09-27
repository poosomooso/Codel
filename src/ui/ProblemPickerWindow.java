package ui;

import main.JavaJigsawMain;
import main.ProblemList;
import snippets.ArraySnippets;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProblemPickerWindow extends JPanel {
    private JList<String> list;
    private DefaultListModel<String> model;



    public ProblemPickerWindow() {
        setLayout(new BorderLayout());

        list = new JList<>();
        model = new DefaultListModel<>();
        list.setModel(model);

        ButtonGroup group = new ButtonGroup();
        JPanel radioPanel = new JPanel(new GridLayout(0, 1));

        for (ProblemList pl : ProblemList.values()) {
            JRadioButton button = getjRadioButton(pl);
            radioPanel.add(button);
            group.add(button);
        }


        add(radioPanel, BorderLayout.LINE_START);
        list.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    ProblemPickerWindow.class.getClassLoader().getResource(
                            ProblemList.ARRAY_LEVEL_1.getPath(
                                    ProblemList.ARRAY_LEVEL_1.problemFiles[0]
                            )
                    )
                    String problemList = group.getSelection().getActionCommand();
                    String problemFile = list.getSelectedValue();
                }
            }
        });

        this.add(list);

    }

    private JRadioButton getjRadioButton(ProblemList pl) {
        JRadioButton button = new JRadioButton(pl.name);
        button.setActionCommand(pl.name);
        button.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     if (button.isSelected()) {
                         model.removeAllElements();
                         for (String s : pl.problemFiles) {
                             model.addElement(s);
                         }
                     }
                 }
             }
        );
        return button;
    }


}
