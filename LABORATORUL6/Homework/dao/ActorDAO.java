package org.example.homework.dao;

import org.example.homework.config.DataSourceProvider;
import org.example.homework.model.Actor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ActorDAO {

    public void create(String name) {

        String sql = "INSERT INTO actors(name) VALUES (?)";

        try (Connection connection = DataSourceProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.executeUpdate();

            System.out.println("Inserted actor: " + name);

        } catch (SQLException except) {

            except.printStackTrace();
        }
    }

    public Actor findById(int id) {

        String sql = "SELECT * FROM actors WHERE id = ?";

        try (Connection connection = DataSourceProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                return new Actor(
                        resultSet.getInt("id"),

                        resultSet.getString("name")
                );
            }

        } catch (SQLException except) {

            except.printStackTrace();
        }

        return null;
    }

    public Actor findByName(String name) {

        String sql = "SELECT * FROM actors WHERE name = ?";

        try (Connection connection = DataSourceProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            if ((resultSet.next())) {

                return new Actor(
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