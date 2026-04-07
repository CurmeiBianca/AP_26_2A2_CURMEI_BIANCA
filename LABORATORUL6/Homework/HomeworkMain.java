package org.example.homework;

import org.example.homework.config.DataSourceProvider;

import org.example.homework.dao.ActorDAO;
import org.example.homework.dao.GenreDAO;
import org.example.homework.dao.MovieActorDAO;
import org.example.homework.dao.MovieDAO;

import org.example.homework.model.Movie;

import org.example.homework.report.ReportGenerator;

import java.time.LocalDate;

public class HomeworkMain {

    public static void main(String[] args) {

        // 1. Initializam pool-ul de conexiuni HikariCP
        DataSourceProvider.init();

        // 2. Instantiem DAO-urile
        GenreDAO genreDAO = new GenreDAO();
        ActorDAO actorDAO = new ActorDAO();
        MovieDAO movieDAO = new MovieDAO();
        MovieActorDAO movieActorDAO = new MovieActorDAO();

        // 3. Inseram cateva date de test
        genreDAO.create("Action");
        genreDAO.create("Drama");

        actorDAO.create("Leonardo DiCaprio");
        actorDAO.create("Tom Hardy");

        // 4. Cream un film de test
        Movie movie = new Movie(
                0,
                "Inception",
                LocalDate.of(2010, 7, 16),
                148,
                8.8,
                genreDAO.findByName("Action").getId()
        );

        movieDAO.create(movie);

        // 5. Legam actorii de film (many-to-many)
        int movieId = movieDAO.findByName("Inception").getId();
        int leoId = actorDAO.findByName("Leonardo DiCaprio").getId();
        int hardyId = actorDAO.findByName("Tom Hardy").getId();

        movieActorDAO.addActorToMovie(movieId, leoId);
        movieActorDAO.addActorToMovie(movieId, hardyId);

        // 6. Generam raportul HTML
        ReportGenerator generator = new ReportGenerator();
        generator.generateReport();

        System.out.println("Homework finished successfully!");

        // 7. Inchidem pool-ul de conexiuni
        DataSourceProvider.close();
    }
}
