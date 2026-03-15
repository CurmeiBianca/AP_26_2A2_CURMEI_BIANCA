package org.example.advanced;

import org.example.homework.Profile;
import org.example.homework.SocialNetwork;

import java.util.*;

public class ConnectivityAnalyzer {

    private Map<Profile, List<Profile>> graph;

    private Map<Profile, Integer> discoveryTime = new HashMap<>();
    private Map<Profile, Integer> low = new HashMap<>();
    private Map<Profile, Profile> parent = new HashMap<>();
    private Set<Profile> articulationPoints = new HashSet<>();
    private Deque<Pair<Profile, Profile>> edgeStack = new ArrayDeque<>();

    private int time;

    public ConnectivityAnalyzer(SocialNetwork network) {
        this.graph = network.buildGraph();
    }

    // --------------------
    // ARTICULATION POINTS
    // --------------------
    public List<Profile> findArticulationPoints() {
        discoveryTime.clear();
        low.clear();
        parent.clear();
        articulationPoints.clear();
        time = 0;

        for (Profile p : graph.keySet()) {
            if (!discoveryTime.containsKey(p)) {
                dfsArticulation(p);
            }
        }

        return new ArrayList<>(articulationPoints);
    }

    private void dfsArticulation(Profile u) {
        discoveryTime.put(u, time);
        low.put(u, time);
        time++;

        int children = 0;

        for (Profile v : graph.getOrDefault(u, Collections.emptyList())) {

            if (!discoveryTime.containsKey(v)) {

                parent.put(v, u);
                children++;

                dfsArticulation(v);

                low.put(u, Math.min(low.get(u), low.get(v)));

                if (!parent.containsKey(u) && children > 1) {
                    articulationPoints.add(u);
                }

                if (parent.containsKey(u) && low.get(v) >= discoveryTime.get(u)) {
                    articulationPoints.add(u);
                }
            }
            else if (!v.equals(parent.get(u))) {
                low.put(u, Math.min(low.get(u), discoveryTime.get(v)));
            }
        }
    }

    // --------------------
    // BICONNECTED COMPONENTS
    // --------------------
    public List<List<Profile>> findBiconnectedComponents() {
        discoveryTime.clear();
        low.clear();
        parent.clear();
        articulationPoints.clear();
        edgeStack.clear();
        time = 0;

        List<List<Profile>> components = new ArrayList<>();

        for (Profile profile : graph.keySet()) {
            if (!discoveryTime.containsKey(profile)) {
                dfsBiconnected(profile, components);
            }
        }

        return components;
    }

    private void dfsBiconnected(Profile u, List<List<Profile>> components) {
        discoveryTime.put(u,time);
        low.put(u, time);
        time++;
        int children = 0;

        for (Profile v : graph.getOrDefault(u, Collections.emptyList())) {

            if (!discoveryTime.containsKey(v)) {

                parent.put(v, u);
                children++;

                edgeStack.push(new Pair<>(u, v));

                dfsBiconnected(v, components);

                low.put(u, Math.min(low.get(u), low.get(v)));

                if ((parent.containsKey(u) && low.get(v) >= discoveryTime.get(u)) ||
                        (!parent.containsKey(u) && children > 1)) {

                    List<Profile> component = new ArrayList<>();
                    Pair<Profile, Profile> edge;
                    do {
                        edge = edgeStack.pop();
                        if (!component.contains(edge.getKey())) {
                            component.add(edge.getKey());
                        }
                        if (!component.contains(edge.getValue())) {
                            component.add(edge.getValue());
                        }
                    } while (!edge.equals(new Pair<>(u, v)));

                    components.add(component);
                }
            }
            else if (!v.equals(parent.get(u))) {
                low.put(u, Math.min(low.get(u), discoveryTime.get(v)));

                if (discoveryTime.get(v) < discoveryTime.get(u)) {
                    edgeStack.push(new Pair<>(u, v));
                }
            }
        }
    }

    // --------------------
    // PAIR
    // --------------------
    private static class Pair<K, V> {
        private final K key;
        private final V value;

        public Pair(K k, V v) {
            this.key = k;
            this.value = v;
        }

        public K getKey() {

            return key;
        }

        public V getValue() {

            return value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Pair<?, ?> pair)) {
                return false;
            }
            return Objects.equals(key, pair.key) &&
                    Objects.equals(value, pair.value);
        }

        @Override
        public int hashCode() {

            return Objects.hash(key, value);
        }
    }
}
