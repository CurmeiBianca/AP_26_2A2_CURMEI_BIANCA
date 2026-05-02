package org.example.homework;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

@Getter
public class HomeworkControlPanel extends JPanel {

    private final HomeworkFrame frame;

    public HomeworkControlPanel(HomeworkFrame frame) {
        this.frame = frame;
        setLayout(new FlowLayout());

        JButton createButton = new JButton("Create");
        JButton resetButton = new JButton("Reset");
        JButton validateButton = new JButton("Validate");
        JButton savePngButton = new JButton("Save PNG");
        JButton saveMazeButton = new JButton("Save Maze");
        JButton loadMazeButton = new JButton("Load Maze");
        JButton exitButton = new JButton("Exit");

        add(createButton);
        add(resetButton);
        add(validateButton);
        add(savePngButton);
        add(saveMazeButton);
        add(loadMazeButton);
        add(exitButton);

        // --- FUNCTIONALITATI ---

        createButton.addActionListener((ActionEvent event) -> {
            int size = frame.getDrawingPanel().getMazeSize();
            if (size <= 0)
                return;

            boolean[][][] walls = frame.getDrawingPanel().getWalls();

            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {

                    if (Math.random() > 0.5) {
                        walls[row][col][1] = false; // Right curent
                        if (col < size - 1)
                            walls[row][col + 1][3] = false; // Left vecin
                    }
                    if (Math.random() > 0.5) {
                        walls[row][col][2] = false; // Bottom curent
                        if (row < size - 1)
                            walls[row + 1][col][0] = false; // Top vecin
                    }
                }
            }
            frame.getDrawingPanel().repaint();
        });

        resetButton.addActionListener((ActionEvent event) -> {
            int currentSize = frame.getDrawingPanel().getMazeSize();
            frame.getDrawingPanel().createGrid(currentSize > 0 ? currentSize : 10);
        });

        validateButton.addActionListener((ActionEvent event) -> {
            boolean solvable = org.example.homework.util.MazeValidator.isSolvable(
                    frame.getDrawingPanel().getWalls()
            );

            JOptionPane.showMessageDialog(
                    frame,
                    solvable ? "Maze is solvable!" : "Maze is NOT solvable!",
                    "Validation Result",
                    solvable ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE
            );
        });

        savePngButton.addActionListener((ActionEvent event) -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                String filePath = file.getAbsolutePath();

                if (!filePath.toLowerCase().endsWith(".png")) {
                    file = new File(filePath + ".png");
                }

                org.example.homework.util.MazeExporter.exportPNG(
                        frame.getDrawingPanel().exportImage(),
                        file
                );
            }
        });

        saveMazeButton.addActionListener((ActionEvent event) -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                org.example.homework.serialization.MazeSerializer.save(
                        frame.getDrawingPanel().getWalls(),
                        file
                );
            }
        });

        loadMazeButton.addActionListener((ActionEvent event) -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                boolean[][][] loaded = org.example.homework.serialization.MazeLoader.load(chooser.getSelectedFile());

                if (loaded != null) {
                    frame.getDrawingPanel().setWalls(loaded);
                    frame.getDrawingPanel().repaint();
                }
            }
        });

        exitButton.addActionListener((ActionEvent event) -> System.exit(0));
    }
}
