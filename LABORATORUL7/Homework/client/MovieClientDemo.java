package org.example.homework.client;

import lombok.RequiredArgsConstructor;

import org.example.compulsory.model.Movie;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovieClientDemo implements CommandLineRunner {

    private final MovieClient movieClient;

    @Override
    public void run(String... args) {

        System.out.println("=== HOMEWORK MOVIE CLIENT DEMO ===");

        // 1. PUT - actualizare completa a filmului cu ID 1
        Movie updatedMovie = new Movie();
        updatedMovie.setTitle("Updated Title");
        updatedMovie.setReleaseDate(java.time.LocalDate.parse("2024-01-01"));
        updatedMovie.setDuration(120);
        updatedMovie.setScore(9.5);

        Movie resultPut = movieClient.updateMovie(1, updatedMovie);
        System.out.println("PUT result: " + resultPut);

        // 2. PATCH - actualizare doar a scorului filmului cu ID 1
        Movie resultPatch = movieClient.updateScore(1, 8.7);
        System.out.println("PATCH result: " + resultPatch);

        // 3. DELETE - stergere film cu ID 2
        movieClient.deleteMovie(2);
        System.out.println("DELETE completed for movie with ID = 2");

        System.out.println("=== END DEMO ===");
    }
}
