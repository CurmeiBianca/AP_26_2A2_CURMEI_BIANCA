package org.example.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Movie {

    private int id;
    private String title;
    private LocalDate releaseDate;
    private int duration;
    private double score;
    private int genreId;
}
