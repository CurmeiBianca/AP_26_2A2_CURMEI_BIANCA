package org.example.compulsory.ui;

import javax.swing.*;
import java.awt.*;

public class ConfigPanel extends JPanel {

    private JSpinner rowsSpinner;

    private JSpinner colsSpinner;

    private JButton drawButton;

    public ConfigPanel(Runnable onDraw) {
        // Folosim FlowLayout pentru a aseza componentele pe un rand
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JLabel rowsLabel = new JLabel("Rows:");
        rowsSpinner = new JSpinner(new SpinnerNumberModel(
                5,
                2,
                30,
                1
        ));
        rowsSpinner.setPreferredSize(new Dimension(60, 25));

        JLabel colsLabel = new JLabel("Cols:");
        colsSpinner = new JSpinner(new SpinnerNumberModel(
                5,
                2,
                30,
                1
        ));
        colsSpinner.setPreferredSize(new Dimension(60, 25));

        drawButton = new JButton("Draw");
        drawButton.addActionListener(e -> onDraw.run());

        add(rowsLabel);
        add(rowsSpinner);
        add(colsLabel);
        add(colsSpinner);
        add(drawButton);
    }

    public int getSelectedRows() {
        return (int) rowsSpinner.getValue();
    }

    public int getSelectedCols() {
        return (int) colsSpinner.getValue();
    }
}
