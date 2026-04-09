package org.example.advanced.dao;

import org.example.advanced.model.MovieList;
import org.example.homework.model.Movie;
import org.example.homework.config.DataSourceProvider;

import java.sql.*;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

public class MovieListDAO {

    // Metoda pentru a crea o lista noua in baza de date
    public MovieList create(String name) throws SQLException {
        String sql = "INSERT INTO movie_list(name, created_at) VALUES (?, ?)";

        try (Connection connection = DataSourceProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, name);

            LocalDateTime now = LocalDateTime.now();
            statement.setTimestamp(2, Timestamp.valueOf(now));

            statement.executeUpdate();

            // Obtinem ID-ul generat automat
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    return new MovieList(id, name, now, new ArrayList<>());
                }
            }
        }
        return null;
    }

    // Metoda pentru a adauga un film intr-o lista
    public void addMovieToList(int listId, int movieId) throws SQLException {
        String sql = "INSERT INTO movie_list_movies(list_id, movie_id) VALUES (?, ?)";

        try (Connection connection = DataSourceProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, listId);
            statement.setInt(2, movieId);

            statement.executeUpdate();
        }
    }

    // Metoda pentru a gasi o lista dupa ID
    public MovieList findById(int id) throws SQLException {
        String sql = "SELECT * FROM movie_list WHERE id = ?";

        try (Connection connection = DataSourceProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();

                    List<Movie> movies = findMoviesInList(id);

                    return new MovieList(id, name, createdAt, movies);
                }
            }
        }
        return null;
    }

    // Metoda pentru a incarca toate filmele dintr-o lista
    public List<Movie> findMoviesInList(int listId) throws SQLException {
        String sql = """
            SELECT m.id, m.title, m.release_date, m.duration, m.score, m.genre_id
            FROM movies m
            JOIN movie_list_movies mlm ON m.id = mlm.movie_id
            WHERE mlm.list_id = ?
        """;


        List<Movie> movies = new ArrayList<>();

        try (Connection connection = DataSourceProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, listId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    // reconstruim obiectul Movie
                    Movie movie = new Movie(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getDate("release_date").toLocalDate(),
                            resultSet.getInt("duration"),
                            resultSet.getDouble("score"),
                            resultSet.getInt("genre_id")
                    );
                    movies.add(movie);
                }
            }
        }
        return movies;
    }

    public void saveMovieList(String name, List<Movie> movies) throws SQLException {
        // 1. Cream lista in tabela movie_list
        MovieList movieList = create(name);

        if (movieList == null) {
            throw new SQLException ("Nu s-a putut crea lista de filme: " + name);
        }

        int listId = movieList.getId();

        // 2. Adaugam fiecare film in tabela movie_list_movies
        for (Movie movie : movies) {
            addMovieToList(listId, movie.getId());
        }
    }
}
