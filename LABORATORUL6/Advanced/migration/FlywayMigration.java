package org.example.advanced.migration;

import org.flywaydb.core.Flyway; // importam Flyway

public class FlywayMigration {

    // Metoda statica pentru a rula migratiile Flyway
    public static void migrate() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driverul MySQL nu a fost găsit!", e);
        }

        Flyway flyway = Flyway.configure()
                .dataSource(
                        "jdbc:mysql://localhost:3306/moviesdb",
                        "root",
                        ""
                )
                .locations("classpath:db/migration")
                .load();

        flyway.migrate();
    }
}
