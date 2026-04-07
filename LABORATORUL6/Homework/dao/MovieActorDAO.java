package org.example.homework.dao;

import org.example.homework.config.DataSourceProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class MovieActorDAO {

    public void addActorToMovie(int movieId, int actorId) {

        String sql = "INSERT INTO movie_actors(movie_id, actor_id) VALUES (?, ?)";

        try (Connection connection = DataSourceProvider.getConnection();

             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, movieId);
            statement.setInt(2, actorId);

            statement.executeUpdate();

            System.out.println("Linked movie " + movieId + " with actor " + actorId);

        } catch (SQLException except) {

            except.printStackTrace();
        }
    }

    public List<Integer> findActorsByMovie(int movieId) {
        // Metodă pentru a obține lista actorilor care joacă într-un film

        String sql = "SELECT actor_id FROM movie_actors WHERE movie_id = ?";

        List<Integer> actorIds = new ArrayList<>();
        // Lista în care vom pune ID-urile actorilor

        try (Connection connection = DataSourceProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, movieId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Parcurgem toate rândurile rezultate

                actorIds.add(resultSet.getInt("actor_id"));
            }

        } catch (SQLException except) {

            except.printStackTrace();
        }

        return actorIds;
    }

    public List<Integer> findMoviesByActor(int actorId) {

        String sql = "SELECT movie_id FROM movie_actors WHERE actor_id = ?";

        List<Integer> movieIds = new ArrayList<>();
        // Lista în care vom pune ID-urile filmelor

        try (Connection connection = DataSourceProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, actorId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                movieIds.add(resultSet.getInt("movie_id"));
            }

        } catch (SQLException except) {

            except.printStackTrace();
        }

        return movieIds;
    }
}