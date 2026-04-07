package org.example.homework.dao;

import org.example.homework.config.DataSourceProvider;
import org.example.homework.model.Movie;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieDAO {

    public void create(Movie movie) {

        String sql = "INSERT INTO movies(title, release_date, duration, score, genre_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DataSourceProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, movie.getTitle());
            statement.setDate(2, movie.getReleaseDate() != null ? Date.valueOf(movie.getReleaseDate()) : null);
            // Convertim LocalDate -> java.sql.Date (sau null daca nu exista data)

            statement.setInt(3, movie.getDuration());
            statement.setDouble(4, movie.getScore());
            statement.setInt(5, movie.getGenreId());

            statement.executeUpdate();

            System.out.println("Inserted movie: " + movie.getTitle());

        } catch (SQLException except) {

            except.printStackTrace();
        }
    }

    public Movie findById(int id) {

        String sql = "SELECT * FROM movies WHERE id = ?";

        try (Connection connection = DataSourceProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                return new Movie(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),

                        resultSet.getDate("release_date") != null
                                ? resultSet.getDate("release_date").toLocalDate()
                                : null,
                        // Convertim SQL DATE -> LocalDate (sau null)

                        resultSet.getInt("duration"),
                        resultSet.getDouble("score"),
                        resultSet.getInt("genre_id")
                );
            }

        } catch (SQLException except) {

            except.printStackTrace();
        }

        return null;
    }

    public Movie findByName(String title) {

        String sql = "SELECT * FROM movies WHERE title = ?";

        try (Connection connection = DataSourceProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, title);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                return new Movie(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),

                        resultSet.getDate("release_date") != null
                                ? resultSet.getDate("release_date").toLocalDate()
                                : null,

                        resultSet.getInt("duration"),
                        resultSet.getDouble("score"),
                        resultSet.getInt("genre_id")
                );
            }

        } catch (SQLException except) {

            except.printStackTrace();
        }

        return null;
    }
}
