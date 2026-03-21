package org.example.compulsory.catalog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resource {

    private String id;

    private String title;

    private String location;

    private String year;

    private String author;
}
