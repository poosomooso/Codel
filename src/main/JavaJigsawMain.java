package main;

import reflection.Problem;
import ui.ActivityWindow;
import ui.WindowLauncher;

import javax.swing.*;
import java.awt.*;

// TODO:
// test cases -- execute arbitrary test cases
// load random problems
// opening screen to select level and topic
public class JavaJigsawMain {

    public static void main(String[] args) {

        Problem p = Problem.parseFile(
                JavaJigsawMain.class.getClassLoader().getResource(
                    ProblemList.ARRAY_LEVEL_1.getPath(
                            ProblemList.ARRAY_LEVEL_1.problemFiles[0]
                    )
                )
        );

        //WindowLauncher.launchActivity(p);
        WindowLauncher.launchPicker();
    }
}
