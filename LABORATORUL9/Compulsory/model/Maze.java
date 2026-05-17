package org.example.compulsory.model;

import lombok.Getter;

@Getter
public class Maze {

    private final int rows;
    private final int cols;

    private final Cell[][] cells;

    public Maze(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.cells = new Cell[rows][cols];
        initCells();
    }

    private void initCells() {
        for (int row = 0; row < rows; row++)
            for (int col = 0; col < cols; col++) {
                cells[row][col] = new Cell(row, col);

                // DESCHIDEM TOTI PERETII CA SA SE POATA MISCA ROBOTUL SI IEPURELE
                cells[row][col].setTopWall(false);
                cells[row][col].setBottomWall(false);
                cells[row][col].setLeftWall(false);
                cells[row][col].setRightWall(false);
            }
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    private boolean isInside(Position position) {
        return position.getRow() >= 0 && position.getRow() < rows &&
                position.getCol() >= 0 && position.getCol() < cols;
    }

    public boolean canMove(Position from, Position to) {

        // 1. Verifica daca positiile sunt valide
        if (!isInside(from) || !isInside(to))
            return false;

        Cell cell1 = getCell(from.getRow(), from.getCol());
        Cell cell2 = getCell(to.getRow(), to.getCol());

        int destrow = to.getRow() - from.getRow();
        int destcol = to.getCol() - from.getCol();

        // 2. Miscare in sus
        if (destrow == -1 && destcol == 0) {
            return !cell1.isTopWall() && !cell2.isBottomWall();
        }

        // 3. Miscare in jos
        if (destrow == 1 && destcol == 0) {
            return !cell1.isBottomWall() && !cell2.isTopWall();
        }

        // 4. Miscare la stanga
        if (destrow == 0 && destcol == -1) {
            return !cell1.isLeftWall() && !cell2.isRightWall();
        }

        // 5. Miscare la dreapta
        if (destrow == 0 && destcol == 1) {
            return !cell1.isRightWall() && !cell2.isLeftWall();
        }

        // 6. Pozitiile nu sunt vecine
        return false;
    }

    public void reset() {
        for (int row = 0; row < rows; row++)
            for (int col = 0; col < cols; col++)
                cells[row][col].resetWalls();
    }
}
