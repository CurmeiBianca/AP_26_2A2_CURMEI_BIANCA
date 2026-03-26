package org.example.compulsory;

import org.example.compulsory.dao.GenreDAO;
import org.example.compulsory.db.DatabaseConnection;
import org.example.compulsory.model.Genre;

public class Main {
    public static void main(String[] args) {

        // Initializam conexiunea la baza de date
        DatabaseConnection.init();

        GenreDAO genreDAO = new GenreDAO();

        genreDAO.create("Action");

        Genre genre1 = genreDAO.findByName("Action");
        System.out.println("Found by name: " + genre1);

        Genre genre2 = genreDAO.findById(1);
        System.out.println("Found by id: " + genre2);
    }
}
