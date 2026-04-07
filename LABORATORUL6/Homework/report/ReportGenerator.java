package org.example.homework.report;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import org.example.homework.config.DataSourceProvider;

import java.io.FileWriter;
import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportGenerator {

    public void generateReport() {

        List<Map<String, Object>> movies = new ArrayList<>();
        // Lista in care vom pune fiecare film ca un Map (cheie -> valoare)

        String sql = "SELECT * FROM movie_report";

        try (Connection connection = DataSourceProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Map<String, Object> movieData = new HashMap<>();
                // Cream un map pentru un film

                movieData.put("id", resultSet.getInt("movie_id"));

                movieData.put("title", resultSet.getString("movie_title"));

                movieData.put("release_date", resultSet.getDate("release_date"));

                movieData.put("duration", resultSet.getInt("duration"));

                movieData.put("score", resultSet.getDouble("score"));

                movieData.put("genre", resultSet.getString("genre_name"));

                movies.add(movieData);
            }

        } catch (SQLException except) {
            except.printStackTrace();
        }

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);

        try {
            cfg.setDirectoryForTemplateLoading(
                    new java.io.File("src/main/resources/templates"));

            Template template = cfg.getTemplate("movies.ftl");

            Map<String, Object> data = new HashMap<>();
            // Map pentru datele trimise in template

            data.put("movies", movies);

            FileWriter writer = new FileWriter("report.html");

            template.process(data, writer);
            writer.close();

            System.out.println("Report generated successfully!");

        } catch (IOException | TemplateException except) {

            except.printStackTrace();
        }
    }
}
