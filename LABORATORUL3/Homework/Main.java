package org.example.homework;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Programmer p1 = new Programmer("Alice", LocalDate.of(1990, 5, 10), "Java");
        Designer d1 = new Designer("Bob", LocalDate.of(1985, 8, 23), "Figma");
        Programmer p2 = new Programmer("Charlie", LocalDate.of(1992, 2, 15), "Python");

        Company c1 = new Company("TechCorp", "Software");
        Company c2 = new Company("DesignHub", "Design");

        p1.addRelationship(d1, "friend");
        p1.addRelationship(p2, "friend");
        p1.addRelationship(c1, "works_at");

        d1.addRelationship(p1, "friend");
        d1.addRelationship(c2, "works_at");

        p2.addRelationship(c1, "works_at");
        p2.addRelationship(p1, "friend");

        c1.addRelationship(p1, "employee");
        c1.addRelationship(p2, "employee");

        c2.addRelationship(d1, "employee");

        SocialNetwork network = new SocialNetwork();
        network.addProfile(p1);
        network.addProfile(p2);
        network.addProfile(d1);
        network.addProfile(c1);
        network.addProfile(c2);

        network.printNetwork();
    }
}