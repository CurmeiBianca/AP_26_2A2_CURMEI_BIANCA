package org.example.homework;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class HomeworkDrawingPanel extends JPanel {

    @Getter @Setter
    private boolean[][][] walls; // walls[row][col][4] = top/right/bottom/left

    public HomeworkDrawingPanel() {
        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                handleClick(mouseEvent.getX(), mouseEvent.getY());
            }
        });
    }

    public int getMazeSize() {
        return (walls != null) ? walls.length : 0;
    }

    public void createGrid(int n) {
        walls = new boolean[n][n][4];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                for (int k = 0; k < 4; k ++)
                    walls[i][j][k] = true;

        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        if (walls == null)
            return;

        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setStroke(new BasicStroke(3));
        graphics2D.setColor(Color.BLACK);

        int n = walls.length;
        int cellSize = Math.min(getWidth(), getHeight()) / n;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                int x = col * cellSize;
                int y = row * cellSize;

                if (walls[row][col][0]) graphics2D.drawLine(x, y, x + cellSize, y); // TOP
                if (walls[row][col][1]) graphics2D.drawLine(x + cellSize, y, x + cellSize, y + cellSize); // RIGHT
                if (walls[row][col][2]) graphics2D.drawLine(x, y + cellSize, x + cellSize, y + cellSize); // BOTTOM
                if (walls[row][col][3]) graphics2D.drawLine(x, y, x, y + cellSize); // LEFT
            }
        }
    }

    private void handleClick(int x, int y) {
        if (walls == null)
            return;

        int n = walls.length;
        int cellSize = Math.min(getWidth(), getHeight()) / n;

        int row = y / cellSize;
        int col = x / cellSize;

        if (row < 0 || row >= n || col < 0 || col >= n)
            return;

        int localX = x % cellSize;
        int localY = y % cellSize;

        int distTop = localY;
        int distBottom = cellSize - localY;
        int distLeft = localX;
        int distRight = cellSize - localX;

        // determinam peretele cel mai apropiat
        int minDist = Math.min(Math.min(distTop, distBottom), Math.min(distLeft, distRight));

        if (minDist == distTop) {
            walls[row][col][0] = !walls[row][col][0];
            if (row > 0)
                walls[row - 1][col][2] = walls[row][col][0];
        } else if (minDist == distRight) {
            walls[row][col][1] = !walls[row][col][1];
            if (col < n - 1)
                walls[row][col + 1][3] = walls[row][col][1];
        } else if (minDist == distBottom) {
            walls[row][col][2] = !walls[row][col][2];
            if (row < n - 1)
                walls[row + 1][col][0] = walls[row][col][2];
        } else if (minDist == distLeft) {
            walls[row][col][3] = !walls[row][col][3];
            if (col > 0)
                walls[row][col - 1][1] = walls[row][col][3];
        }

        repaint();
    }

    public BufferedImage exportImage() {

        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = image.createGraphics();
        this.paint(graphics2D);
        graphics2D.dispose();
        return image;
    }
}
