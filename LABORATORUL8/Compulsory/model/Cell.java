package org.example.compulsory.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cell {

    private int row;
    private int col;

    private boolean topWall;
    private boolean rightWall;
    private boolean bottomWall;
    private boolean leftWall;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        topWall = rightWall = bottomWall = leftWall = true;
    }

    public void resetWalls() {
        topWall = rightWall = bottomWall = leftWall = true;
    }
}
