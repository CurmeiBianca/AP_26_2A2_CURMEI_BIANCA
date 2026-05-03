package org.example.compulsory.model;

import lombok.Getter;
import org.example.compulsory.core.SharedMemory;

public class Robot implements Runnable {

    @Getter
    private Position position;
    private final Maze maze;
    private final SharedMemory memory;
    private volatile boolean running = true;

    public Robot(Position start, Maze maze, SharedMemory memory) {
        this.position = start;
        this.maze = maze;
        this.memory = memory;
    }

    public boolean moveTo(Position next) {
        if (maze.canMove(position, next)) {
            position = next;
            memory.write("Robot moved to " + next);
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        while (running) {
            // Exemplu simplu: incearca sa mearga in jos
            Position down = new Position(position.getRow() + 1, position.getCol());
            moveTo(down);

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
