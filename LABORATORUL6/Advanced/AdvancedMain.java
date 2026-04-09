package org.example.advanced;

import org.example.advanced.algorithm.MoviePartitioner;

import org.example.advanced.importer.CsvImporter;

import org.example.advanced.migration.FlywayMigration;

import org.example.advanced.dao.MovieListDAO;
import org.example.homework.dao.GenreDAO;
import org.example.homework.dao.MovieDAO;
import org.example.homework.dao.ActorDAO;
import org.example.homework.dao.MovieActorDAO;

import org.example.homework.model.Movie;

import java.util.List;

public class AdvancedMain {

    public static void main(String[] args) {

        try {
            // 1. Rulam migrarile Flyway (creeaza tabelele)
            FlywayMigration.migrate();
            System.out.println("Migrarile Flyway au fost aplicate.");

            // 2. Initializam conexiunea la baza de date
            org.example.homework.config.DataSourceProvider.init();

            // 3. Initializam DAO-urile
            GenreDAO genreDAO = new GenreDAO();
            MovieDAO movieDAO = new MovieDAO();
            ActorDAO actorDAO = new ActorDAO();
            MovieActorDAO movieActorDAO = new MovieActorDAO();
            MovieListDAO movieListDAO = new MovieListDAO();

            // 4. Importam datele din CSV
            CsvImporter importer = new CsvImporter(movieDAO, actorDAO, movieActorDAO, genreDAO);
            importer.importAll();
            System.out.println("Datele din CSV au fost importate.");

            // 5. Rulam algoritmul de partitionare
            MoviePartitioner partitioner = new MoviePartitioner(movieDAO, movieActorDAO);
            List<List<Movie>> partitions = partitioner.partitionMovies();
            System.out.println("Filmele au fost partitionate in liste neinrudite");

            // 6. Afisam rezultatele
            int index = 1;
            for (List<Movie> group : partitions) {
                System.out.println("\n=== Grup " + index + " ===");
                for (Movie movie : group) {
                    System.out.println(" - " + movie.getTitle());
                }

                // 7. Salvam grupul in tabela movie_list
                movieListDAO.saveMovieList("Group " + index, group);
                index++;
            }

            System.out.println("\nToate grupurile au fost salvate in tabela movie_list");

        } catch (Exception exception) {
            System.err.println("Eroare in AdvancedMain: " + exception.getMessage());
            exception.printStackTrace();
        }
    }
}
