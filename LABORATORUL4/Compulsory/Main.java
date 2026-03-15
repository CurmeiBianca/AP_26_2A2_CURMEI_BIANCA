package org.example.compulsory;

import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {

        List<Intersection> intersections =
                IntStream.rangeClosed(1, 10)

                        .mapToObj(i -> new Intersection("I" + i))

                        .toList();

        System.out.println("Intersections:");
        intersections.forEach(System.out::println);

        LinkedList<Street> streets = new LinkedList<>();

        streets.add(new Street("S1", 10, intersections.get(0), intersections.get(1)));
        streets.add(new Street("S2", 5, intersections.get(1), intersections.get(2)));
        streets.add(new Street("S3", 12, intersections.get(2), intersections.get(3)));
        streets.add(new Street("S4", 3, intersections.get(3), intersections.get(4)));
        streets.add(new Street("S5", 8, intersections.get(4), intersections.get(5)));
        streets.add(new Street("S6", 15, intersections.get(5), intersections.get(6)));
        streets.add(new Street("S7", 7, intersections.get(6), intersections.get(7)));
        streets.add(new Street("S8", 6, intersections.get(7), intersections.get(8)));
        streets.add(new Street("S9", 11, intersections.get(8), intersections.get(9)));

        streets.sort((s1, s2) -> Integer.compare(s1.getLength(), s2.getLength()));

        System.out.println("\nSorted streets:");
        streets.forEach(System.out::println);

        Set<Intersection> intersectionSet = new HashSet<>(intersections);

        intersectionSet.add(new Intersection("I1"));

        System.out.println("\nIntersections in HashSet:");
        intersectionSet.forEach(System.out::println);

        System.out.println("\nNumber of intersections in set: " + intersectionSet.size());
    }
}