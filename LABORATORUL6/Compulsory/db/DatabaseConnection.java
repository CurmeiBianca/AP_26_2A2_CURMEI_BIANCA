package org.example.compulsory.db;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    @Getter
    private static Connection connection;

    // Constructor privat pentru a preveni instantierea clasei
    private DatabaseConnection() {

    }

    // Metoda statica pentru instantierea conexiunii la baza de date
    public static void init() {
        try {
            String url = "jdbc:mysql://localhost:3306/moviesdb";

            String user = "root";

            String password = ""; // parola goala in XAMPP

            // DriverManager stabileste conexiunea catre baza de date
            connection = DriverManager.getConnection(url, user, password);

            System.out.println("Connected to database!");

        } catch (SQLException except) {
            except.printStackTrace();
        }
    }
}
