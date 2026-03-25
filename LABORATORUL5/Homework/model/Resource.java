package org.example.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resource implements Serializable {

    private String id;
    private String title;
    private String location;
    private String year;
    private String author;
}
