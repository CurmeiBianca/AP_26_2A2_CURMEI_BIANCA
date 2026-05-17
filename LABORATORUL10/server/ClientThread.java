package org.example.server;

import org.example.model.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.Socket;

public class ClientThread implements Runnable {

    private final Socket clientSocket;

    private final Game game;

    public ClientThread(Socket clientSocket, Game game) {
        this.clientSocket = clientSocket;
        this.game = game;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            out.println("Welcome to the Quiz Game!");
            out.println("Please enter your name:");

            String name = in.readLine();
            Player player = new Player(name);
            game.addPlayer(player);

            out.println("Hello, " + name + "! Waiting for the game to start...");

            for (int idx = 0; idx < game.getQuestionCount(); idx++) {

                var question = game.getQuestion(idx);

                out.println("QUESTION " + (idx + 1) + ": " + question.getText());
                out.println("Your answer:");

                long start = System.currentTimeMillis();

                String answer = in.readLine();

                long end = System.currentTimeMillis();
                long duration = end - start;

                player.addTime(duration);

                if (question.isCorrect(answer)) {
                    out.println("Correct!");
                    player.incrementScore();
                } else {
                    out.println("Wrong! The correct answer was: " + question.getCorrectAnswer());
                }

                out.println("Time taken: " + duration + " ms");
                out.println("----------------------------------------");
            }

            out.println("Game over!");
            out.println("Your final score is: " + player.getScore());
            out.println("Your total time is: " + player.getTotalTime() + " ms");
            out.println("Thank you for playing!");

            Player winner = game.getWinner();
            if (winner != null) {
                out.println("The WINNER is: " + winner.getName() +
                        " with score " + winner.getScore() +
                        " and total time " + winner.getTotalTime() + " ms");
            }

        } catch (IOException exception) {
            System.err.println("Client error: " + exception.getMessage());
        }
    }
}
