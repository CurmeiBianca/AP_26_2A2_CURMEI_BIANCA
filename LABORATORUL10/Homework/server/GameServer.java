package org.example.server;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameServer {

    private static final int PORT = 5000;

    public static void main(String[] args) {

        // ExecutorService cu un pool fix de thread-uri (max 10 clienti simultan)
        ExecutorService executor = Executors.newFixedThreadPool(10);

        Game game = new Game();

        System.out.println("Server starting on port " + PORT + "...");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            System.out.println("Server started. Waiting for clients...");

            while (!executor.isShutdown()) {

                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress());

                executor.submit(new ClientThread(clientSocket, game));
            }

        } catch (IOException exception) {
            System.err.println("Server error: " + exception.getMessage());
        } finally {
            executor.shutdown();
            System.out.println("Server stopped");
        }
    }
}
