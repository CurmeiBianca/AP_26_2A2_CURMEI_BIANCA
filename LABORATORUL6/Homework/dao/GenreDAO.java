package org.example.homework.dao;

import org.example.homework.config.DataSourceProvider;
import org.example.homework.model.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreDAO {

    public void create(String name) {
        // Metodă pentru inserarea unui gen nou în baza de date

        String sql = "INSERT INTO genres(name) VALUES (?)";

        try (Connection connection = DataSourceProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.executeUpdate();

            System.out.println("Inserted genre: " + name);

        } catch (SQLException except) {

            except.printStackTrace();
        }
    }

    public Genre findById(int id) {

        String sql = "SELECT * FROM genres WHERE id = ?";

        try (Connection connection = DataSourceProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                return new Genre(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );
            }
        } catch (SQLException except) {

            except.printStackTrace();
        }

        return null;
    }

    public Genre findByName(String name) {

        String sql = "SELECT * FROM genres WHERE name = ?";
        try (Connection connection = DataSourceProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                return new Genre(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );
            }
        } catch (SQLException except) {

            except.printStackTrace();
        }

        return null;
    }
}
