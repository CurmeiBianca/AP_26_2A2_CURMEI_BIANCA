package org.example.homework.util;

import lombok.experimental.UtilityClass;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

@UtilityClass
public class MazeValidator {

    public boolean isSolvable(boolean[][][] walls) {
        if (walls == null)
            return false;

        int length = walls.length;
        boolean[][] visited = new boolean[length][length];

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0,0));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int row = point.x;
            int col = point.y;

            // Daca am ajuns la destinatie ->labirintul este rezolvabil
            if (row == length - 1 && col == length - 1)
                return true;

            // TOP
            if (!walls[row][col][0] && row > 0 && !visited[row - 1][col]) {
                visited[row - 1][col] = true;
                queue.add(new Point(row - 1, col));
            }

            // RIGHT
            if (!walls[row][col][1] && col < length - 1 && !visited[row][col + 1]) {
                visited[row][col + 1] = true;
                queue.add(new Point(row, col + 1));
            }

            // BOTTOM
            if (!walls[row][col][2] && row < length - 1 && !visited[row + 1][col]) {
                visited[row + 1][col] = true;
                queue.add(new Point(row + 1, col));
            }

            // LEFT
            if (!walls[row][col][3] && col > 0 && !visited[row][col - 1]) {
                visited[row][col - 1] = true;
                queue.add(new Point(row, col - 1));
            }
        }

        return false;
    }
}
