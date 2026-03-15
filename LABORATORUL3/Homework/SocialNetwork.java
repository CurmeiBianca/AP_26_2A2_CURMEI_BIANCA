package org.example.homework;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class SocialNetwork {

    private List<Profile> profiles = new ArrayList<>();

    public void addProfile(Profile profile) {

        profiles.add(profile);
    }

    public void printNetwork() {

        profiles.sort((a, b) -> Integer.compare(b.getImportance(), a.getImportance()));

        for (Profile profile : profiles) {
            System.out.println(profile);
        }
    }

    public Map<Profile, List<Profile>> buildGraph() {
        Map<Profile, List<Profile>> graph = new HashMap<>();

        for (Profile prof : profiles) {
            graph.putIfAbsent(prof, new ArrayList<>());

            for (Profile other : prof.getRelationships().keySet()) {
                graph.putIfAbsent(other, new ArrayList<>());
                graph.get(prof).add(other);
                graph.get(other).add(prof);
            }
        }

        return graph;
    }
}