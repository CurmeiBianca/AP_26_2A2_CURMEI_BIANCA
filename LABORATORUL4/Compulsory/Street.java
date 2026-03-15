package org.example.compulsory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Street implements Comparable<Street> {

    private String name;

    private int length;

    private Intersection firstIntersection;

    private Intersection secondIntersection;

    @Override
    public int compareTo(Street other) {

        return Integer.compare(this.length, other.length);
    }
}