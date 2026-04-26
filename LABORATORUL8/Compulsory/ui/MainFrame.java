package org.example.compulsory.ui;

import org.example.compulsory.model.Maze;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private  Maze maze;

    private MazeCanvas mazeCanvas;

    private ConfigPanel configPanel;

    private ControlPanel controlPanel;

    public MainFrame() {
        setTitle("Maze Application");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        mazeCanvas = new MazeCanvas();

        configPanel = new ConfigPanel(this::onDraw);

        controlPanel = new ControlPanel(
                this::onCreate,
                this::onReset,
                this::onExit
        );

        add(configPanel, BorderLayout.NORTH);

        // Canvas-ul il punem intr-un JScrollPane pentru
        // a permite scroll daca labirintul e mai mare decat fereastra
        add(new JScrollPane(mazeCanvas), BorderLayout.CENTER);

        add(controlPanel, BorderLayout.SOUTH);

        setSize(600, 500);

        // Centram fereastra pe ecran
        setLocationRelativeTo(null);
    }

    private void onDraw() {
        int rows = configPanel.getSelectedRows();
        int cols = configPanel.getSelectedCols();

        // Cream un labirint nou cu toti peretii prezenti
        maze = new Maze(rows, cols);

        mazeCanvas.setMaze(maze);

        // Redimensionam fereastra dupa noul canvas
        pack();
    }

    private void onCreate() {
        if (maze == null) {
            JOptionPane.showMessageDialog(this,
                    "Apasa Draw mai intai pentru a crea labirintul!",
                    "Atentie",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        maze.removeRandomWalls();

        mazeCanvas.repaint();
    }

    private void onReset() {
        if (maze == null) {
            JOptionPane.showMessageDialog(this,
                    "Apasa Draw mai intai pentru a crea labirintul!",
                    "Atentie",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        maze.reset();

        mazeCanvas.repaint();
    }

    private void onExit() {
        System.exit(0);
    }
}
