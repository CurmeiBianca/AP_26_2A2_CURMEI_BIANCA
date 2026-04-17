package org.example.homework.service;

import lombok.RequiredArgsConstructor;

import org.example.compulsory.model.Movie;
import org.example.homework.repository.MovieRepository;
import org.example.homework.exception.MovieNotFoundException;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public Movie getMovieById(Integer id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));
    }

    public Movie updateMovie(Integer id, Movie updatedMovie) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));

        movie.setTitle(updatedMovie.getTitle());
        movie.setReleaseDate(updatedMovie.getReleaseDate());
        movie.setDuration(updatedMovie.getDuration());
        movie.setScore(updatedMovie.getScore());

        return movieRepository.save(movie);
    }

    public Movie updateScore(Integer id, Double newScore) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));

        movie.setScore(newScore);

        return movieRepository.save(movie);
    }

    public void deleteMovie(Integer id) {
        if (!movieRepository.existsById(id)) {
            throw new MovieNotFoundException(id);
        }

        movieRepository.deleteById(id);
    }
}
