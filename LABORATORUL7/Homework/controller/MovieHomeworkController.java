package org.example.homework.controller;

import lombok.RequiredArgsConstructor;

import org.example.compulsory.model.Movie;
import org.example.homework.service.MovieService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/homework/movies")
@RequiredArgsConstructor
public class MovieHomeworkController {

    private final MovieService movieService;

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Integer id) {
        Movie movie = movieService.getMovieById(id);
        return ResponseEntity.ok(movie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(
            @PathVariable Integer id,
            @RequestBody Movie updatedMovie) {

        Movie movie = movieService.updateMovie(id, updatedMovie);

        return ResponseEntity.ok(movie);
    }

    @PatchMapping("/{id}/score")
    public ResponseEntity<Movie> updateScore(
            @PathVariable Integer id,
            @RequestBody Double newScore) {

        Movie movie = movieService.updateScore(id, newScore);

        return ResponseEntity.ok(movie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Integer id) {

        movieService.deleteMovie(id);;

        return ResponseEntity.noContent().build();
    }
}
