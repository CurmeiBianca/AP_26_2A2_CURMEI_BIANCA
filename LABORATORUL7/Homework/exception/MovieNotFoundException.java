package org.example.homework.exception;

public class MovieNotFoundException extends RuntimeException {

    public MovieNotFoundException(Integer id) {
        super("Movie with id " + id + " not found.");
    }
}
