package org.example.advanced.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import org.example.homework.model.Movie;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieList {

    private int id;

    private String name;

    private LocalDateTime createdAt;

    private List<Movie> movies;
}
