package org.example.advanced;

import org.example.homework.*;

import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class SocialNetworkTest {

    @Test
    public void testArticulationPoints() {

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

        ConnectivityAnalyzer analyzer = new ConnectivityAnalyzer(network);
        List<Profile> points = analyzer.findArticulationPoints();

        assertTrue(points.contains(p1));
        assertTrue(points.contains(c1));
        assertTrue(points.contains(d1));

        assertFalse(points.contains(p2));
        assertFalse(points.contains(c2));
    }

    @Test
    public void testBiconnectedComponents() {

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

        ConnectivityAnalyzer analyzer = new ConnectivityAnalyzer(network);
        List<List<Profile>> components = analyzer.findBiconnectedComponents();

        for (List<Profile> comp : components) {
            assertTrue(comp.size() >= 2);
        }

        int totalProfiles = 0;
        for (List<Profile> comp : components) {
            totalProfiles += comp.size();
        }

        assertTrue(totalProfiles >= network.getProfiles().size());
    }
}