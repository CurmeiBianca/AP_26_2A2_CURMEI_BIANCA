package org.example.compulsory.model;

import lombok.Getter;

import java.util.Random;

@Getter
public class Maze {

    private int rows;
    private int cols;

    private Cell[][] cells;

    public Maze(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        initCells();
    }

    private void initCells() {
        cells = new Cell[rows][cols];
        for (int row = 0; row < rows; row++)
            for (int col = 0; col < cols; col++)
                cells[row][col] = new Cell(row, col);
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public void reset() {
        for (int row = 0; row < rows; row++)
            for (int col = 0; col < cols; col++)
                cells[row][col].resetWalls();
    }

    public void removeRandomWalls() {
        Random rand = new Random();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                if (col + 1 < cols && rand.nextBoolean()) {
                    cells[row][col].setRightWall(false);
                    cells[row][col + 1].setLeftWall(false);
                }

                if (row + 1 < rows && rand.nextBoolean()) {
                    cells[row][col].setBottomWall(false);
                    cells[row + 1][col].setTopWall(false);
                }
            }
        }
    }
}
