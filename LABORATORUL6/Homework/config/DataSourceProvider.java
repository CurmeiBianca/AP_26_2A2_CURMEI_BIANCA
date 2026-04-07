package org.example.homework.config;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariConfig;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceProvider {

    private static HikariDataSource dataSource;
    // Instanta statica a pool-ului (Singleton) - un singur pool pentru toata aplicatia

    public static void init() {
        HikariConfig config = new HikariConfig();
        // Cream un model de configurare pentru HikariCP

        config.setJdbcUrl("jdbc:mysql://localhost:3306/moviesdb");
        config.setUsername("root");
        config.setPassword("");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setMinimumIdle(2);
        config.setMaximumPoolSize(10);
        config.setPoolName("MoviesHikariPool");

        dataSource = new HikariDataSource(config);
        // Initializam efectiv pool-ul pe baza configuratiei

        System.out.println("HikariCP connection pool initialized!");
    }

    public static Connection getConnection() throws SQLException {
        // Metoda statica pentru obtinerea unei conexiuni din pool

        return dataSource.getConnection();
    }

    public static void close() {

        if (dataSource != null) {

            dataSource.close();
        }
    }
}
