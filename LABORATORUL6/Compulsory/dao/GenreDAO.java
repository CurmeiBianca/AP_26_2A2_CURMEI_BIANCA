package org.example.compulsory.dao;

import org.example.compulsory.db.DatabaseConnection;
import org.example.compulsory.model.Genre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreDAO {

    public void create(String name) {
        // SQL pentru inserarea cu parametru
        String sql = "INSERT INTO genres(name) VALUES (?)";

        try {
            // Pregatim statement-ul pentru executare
            PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql);

            statement.setString(1, name);

            statement.executeUpdate();

            System.out.println("Inserted genre: " + name);

        } catch (SQLException except) {
            except.printStackTrace();
        }
    }

    public Genre findById(int id) {
        // SQL cu parametru pentru ID
        String sql = "SELECT * FROM genres WHERE id = ?";

        try {
            // Pregatim statement-ul
            PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql);

            statement.setInt(1, id);

            ResultSet resSet = statement.executeQuery();

            // Daca exista un rand in rezultat
            if (resSet.next()) {
                return new Genre(
                        resSet.getInt("id"),
                        resSet.getString("name")
                );
            }

        } catch (SQLException except) {
            except.printStackTrace();
        }

        return null;
    }

    public Genre findByName(String name) {
        // SQL cu parametru pentru nume
        String sql = "SELECT * FROM genres WHERE name = ?";

        try {
            // Pregatim statement-ul
            PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql);

            statement.setString(1, name);

            ResultSet resSet = statement.executeQuery();

            if (resSet.next()) {
                return new Genre(
                        resSet.getInt("id"),
                        resSet.getString("name")
                );
            }

        } catch (SQLException except) {
            except.printStackTrace();
        }

        return null;
    }
}
