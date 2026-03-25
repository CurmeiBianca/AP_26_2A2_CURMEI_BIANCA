package org.example.advanced.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Resource {

    private String id;

    private List<String> keywords;
}
