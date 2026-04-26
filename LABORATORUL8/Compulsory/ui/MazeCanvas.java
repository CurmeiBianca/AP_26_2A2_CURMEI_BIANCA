package org.example.compulsory.ui;

import org.example.compulsory.model.Maze;
import org.example.compulsory.model.Cell;

import javax.swing.*;
import java.awt.*;

public class MazeCanvas extends JPanel {

    // Dimensiunea in pixeli a unei celule
    private static final int CELL_SIZE = 40;

    // Culoarea de umplere a celulelor
    private static final Color CELL_COLOR = new Color(220, 235, 255);

    // Culoarea peretilor
    private static final Color WALL_COLOR = new Color (30, 60, 120);

    private Maze maze;

    public MazeCanvas() {
        setBackground(Color.WHITE);
    }

    public void setMaze(Maze maze) {
        this.maze = maze;

        // Dimensiunea panelului = nr celule * dimensiunea unei celule
        int width = maze.getCols() * CELL_SIZE;
        int height = maze.getRows() * CELL_SIZE;
        setPreferredSize(new Dimension(width, height));

        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics graph) {
        super.paintComponent(graph);

        if (maze == null)
            return;

        Graphics2D graphics2D = (Graphics2D) graph;

        // Activam antialiasing pentru linii mai clare
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Grosimea liniilor pentru pereti
        graphics2D.setStroke(new BasicStroke(2f));

        int rows = maze.getRows();
        int cols = maze.getCols();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                int x = col * CELL_SIZE;
                int y = row * CELL_SIZE;

                // 1. Deseneaza fundalul celulei
                graphics2D.setColor(CELL_COLOR);
                graphics2D.fillRect(x, y, CELL_SIZE, CELL_SIZE);

                // 2. Deseneaza peretii existenti ai celulei
                graphics2D.setColor(WALL_COLOR);
                Cell cell = maze.getCell(row, col);

                if (cell.isTopWall())
                    graphics2D.drawLine(x, y, x + CELL_SIZE, y);

                if (cell.isRightWall())
                    graphics2D.drawLine(x + CELL_SIZE, y, x + CELL_SIZE, y + CELL_SIZE);

                if (cell.isBottomWall())
                    graphics2D.drawLine(x, y + CELL_SIZE, x + CELL_SIZE, y + CELL_SIZE);

                if (cell.isLeftWall())
                    graphics2D.drawLine(x, y, x, y + CELL_SIZE);
            }
        }
    }
}
