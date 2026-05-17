package org.example.compulsory.core;

import org.example.compulsory.model.Bunny;
import org.example.compulsory.model.Maze;
import org.example.compulsory.model.Position;
import org.example.compulsory.model.Robot;

public class Game {

    private final Maze maze;
    private final SharedMemory memory;
    private final Robot robot;
    private final Bunny bunny;

    private Thread robotThread;
    private Thread bunnyThread;

    public Game() {
        this.maze = new Maze(10, 10);

        this.memory = new SharedMemory();

        Position robotStart = new Position(0, 0);
        Position bunnyStart = new Position(9, 9);

        this.robot = new Robot(robotStart, maze, memory);
        this.bunny = new Bunny(bunnyStart, maze, memory);
    }

    public void start() {
        robotThread = new Thread(robot);
        bunnyThread = new Thread(bunny);

        robotThread.start();
        bunnyThread.start();

        System.out.println("Game started!");
    }

    public void stop() {
        robot.stop();
        bunny.stop();

        try {
            robotThread.join();
            bunnyThread.join();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        System.out.println("Game stopped!");
    }
}
