package org.example.compulsory;

import org.example.compulsory.core.Game;

public class Lab9Main {
    public static void main(String[] args) {

        Game game = new Game();
        game.start();

        // Lasam jocul sa ruleze cateva secunde
        try {
            Thread.sleep(5000); // ruleaza 5 secunde
        } catch (InterruptedException ignored) {

        }
        game.stop();
    }
}
