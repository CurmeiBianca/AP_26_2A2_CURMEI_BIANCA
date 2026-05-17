package org.example.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.Socket;

public class GameClient {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 5000;

    public static void main(String[] args) {

        try (
                Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))
        ) {

            System.out.println("Connected to the server!");

            String serverMessage;

            while ((serverMessage = in.readLine()) != null) {

                System.out.println(serverMessage);

                // Daca serverul cere input de la utilizator, citim de la tastatura
                if (serverMessage.endsWith(":")) {
                    String userResponse = userInput.readLine();
                    out.println(userResponse);
                }
            }

        } catch (IOException exception) {
            System.err.println("Client error: " + exception.getMessage());
        }
    }
}
