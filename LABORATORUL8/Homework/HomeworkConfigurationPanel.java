package org.example.homework;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

@Getter
public class HomeworkConfigurationPanel extends JPanel {

    private final HomeworkFrame frame;
    private final JTextField sizeField;
    private final JButton drawButton;

    public HomeworkConfigurationPanel(HomeworkFrame frame) {
        this.frame = frame;
        setLayout(new FlowLayout());

        JLabel label = new JLabel("Maze size:");
        sizeField = new JTextField("10", 5);
        drawButton = new JButton("Draw Maze");

        drawButton.addActionListener((ActionEvent event) -> {
            try {
                int size = Integer.parseInt(sizeField.getText());

                if (size > 0 && size <= 100) {
                    frame.getDrawingPanel().createGrid(size);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter a size between 1 and 100");
                }
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(frame, "Invalid input! Please enter a number");
            }
        });

        add(label);
        add(sizeField);
        add(drawButton);
    }
}
