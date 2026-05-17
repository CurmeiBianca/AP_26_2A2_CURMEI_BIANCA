package org.example.compulsory.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Position {

    private final int row;

    private final int col;
}
