package org.example.homework.client;

import lombok.RequiredArgsConstructor;

import org.example.compulsory.model.Movie;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class MovieClient {

    private final RestTemplate restTemplate;

    private static final String BASE_URL = "http://localhost:8080/homework/movies";

    public Movie updateMovie(Integer id, Movie updatedMovie) {
        String url = BASE_URL + "/" + id;

        restTemplate.put(url, updatedMovie);

        return restTemplate.getForObject(url, Movie.class);
    }

    public Movie updateScore(Integer id, Double newScore) {
        String url = BASE_URL + "/" + id + "/score";

        restTemplate.patchForObject(url, newScore, Void.class);

        return restTemplate.getForObject(BASE_URL + "/" + id, Movie.class);
    }

    public void deleteMovie(Integer id) {
        String url = BASE_URL + "/" + id;

        restTemplate.delete(url);
    }
}
