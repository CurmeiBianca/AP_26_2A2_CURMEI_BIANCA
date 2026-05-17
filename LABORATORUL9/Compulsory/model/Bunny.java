package org.example.compulsory.model;

import lombok.Getter;
import org.example.compulsory.core.SharedMemory;

public class Bunny implements Runnable {

    @Getter
    private Position position;
    private final Maze maze;
    private final SharedMemory memory;
    private volatile boolean running = true;

    public Bunny(Position start, Maze maze, SharedMemory memory) {
        this.position = start;
        this.maze = maze;
        this.memory = memory;
    }

    public boolean moveTo(Position next) {
        if (maze.canMove(position, next)) {
            position = next;
            memory.write("Bunny moved to " + next);
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        while (running) {
            // Exemplu simplu: incearca sa mearga in sus
            Position up = new Position(position.getRow() - 1, position.getCol());
            moveTo(up);

            try {
                Thread.sleep(300);
            } catch (InterruptedException exception) {
                running = false;
            }
        }
    }

    public void stop() {
        running = false;
    }
}
