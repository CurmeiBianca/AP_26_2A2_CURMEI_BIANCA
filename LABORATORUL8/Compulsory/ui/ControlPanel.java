package org.example.compulsory.ui;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {

    private JButton createButton;

    private JButton resetButton;

    private JButton exitButton;

    public ControlPanel(Runnable onCreate, Runnable onReset, Runnable onExit) {
        // Folosim FlowLayout pentru a aseza butoanele pe un rand
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        createButton = new JButton("Create");
        createButton.addActionListener(e -> onCreate.run());

        resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> onReset.run());

        exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> onExit.run());

        add(createButton);
        add(resetButton);
        add(exitButton);
    }
}
