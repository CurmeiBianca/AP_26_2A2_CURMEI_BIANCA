package org.example.homework;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class HomeworkFrame extends JFrame {

    private final HomeworkConfigurationPanel configurationPanel;
    private final HomeworkDrawingPanel drawingPanel;
    private final HomeworkControlPanel controlPanel;

    public HomeworkFrame() {
        super("Homework Maze Editor");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        drawingPanel = new HomeworkDrawingPanel();
        configurationPanel = new HomeworkConfigurationPanel(this);
        controlPanel = new HomeworkControlPanel(this);

        add(configurationPanel, BorderLayout.NORTH);
        add(drawingPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
