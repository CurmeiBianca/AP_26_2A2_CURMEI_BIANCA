package org.example.advanced.importer;

import org.example.homework.dao.GenreDAO;
import org.example.homework.dao.MovieDAO;
import org.example.homework.dao.ActorDAO;
import org.example.homework.dao.MovieActorDAO;
import org.example.homework.model.Movie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;

public class CsvImporter {

    private final MovieDAO movieDAO;
    private final ActorDAO actorDAO;
    private final MovieActorDAO movieActorDAO;
    private final GenreDAO genreDAO;

    public CsvImporter(MovieDAO movieDAO, ActorDAO actorDAO, MovieActorDAO movieActorDAO, GenreDAO genreDAO) {
        this.movieDAO = movieDAO;
        this.actorDAO = actorDAO;
        this.movieActorDAO = movieActorDAO;
        this.genreDAO = genreDAO;
    }

    public void importAll() throws IOException {
        importGenres();
        importMovies();
        importActors();
        importMovieActors();
    }

    private void importGenres() throws IOException {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("dataset/genres.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {

            String line;
            reader.readLine(); // skip header: id,name

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                String name = parts[1].trim();

                genreDAO.create(name);
            }

            System.out.println("Genurile au fost importate.");
        }
    }

    private void importMovies() throws IOException {

        // Deschidem fisierul movies.csv din resources/dataset
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("dataset/movies.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {

            String line;
            reader.readLine(); // sarim peste header-ul CSV

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                int id = Integer.parseInt(parts[0]);
                String title = parts[1];

                LocalDate releaseDate = parts[2].isEmpty() ? null : LocalDate.parse(parts[2]);

                int duration = Integer.parseInt(parts[3]);
                double score = Double.parseDouble(parts[4]);
                String genreName = parts[5].trim();
                int genreId = genreDAO.findByName(genreName).getId();

                Movie movie = new Movie(id, title, releaseDate, duration, score, genreId);

                movieDAO.create(movie);
            }
        }
    }

    private void importActors() throws IOException {

        // Deschidem fisierul actors.csv
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("dataset/actors.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {

            String line;
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                actorDAO.create(name);
            }
        }
    }

    private void importMovieActors() throws IOException {

        // Deschidem fisierul movie_actors.csv
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("dataset/movie_actors.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {

            String line;
            reader.readLine();

            // Citim fiecare linie
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                int movieId = Integer.parseInt(parts[0]);
                int actorId = Integer.parseInt(parts[1]);

                movieActorDAO.addActorToMovie(movieId, actorId);
            }
        }
    }
}