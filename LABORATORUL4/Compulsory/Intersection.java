package org.example.compulsory;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Intersection implements Comparable<Intersection> {

    private String name;

    @Override
    public int compareTo(Intersection other) {

        return this.name.compareTo(other.name);
    }
}