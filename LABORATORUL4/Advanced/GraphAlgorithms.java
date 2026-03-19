package org.example.advanced;

import org.example.compulsory.Intersection;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.*;

public class GraphAlgorithms {

    /*
        Metoda care calculeaza un arbore de acoperire minim (MST)
        folosind algoritmul Prim.
     */
    public Graph<Intersection, DefaultWeightedEdge> computeMSTPrim(
            Graph<Intersection, DefaultWeightedEdge> graph) {

        // Cream un graf gol in care vom pune rezultatul
        Graph<Intersection, DefaultWeightedEdge> mst =
                new org.jgrapht.graph.SimpleWeightedGraph<>(DefaultWeightedEdge.class);

        // Adaugam toate nodurile
        graph.vertexSet().forEach(mst::addVertex);

        Intersection startNode = graph.vertexSet().iterator().next();

        Set<Intersection> visited = new HashSet<>();
        visited.add(startNode);

        PriorityQueue<DefaultWeightedEdge> edgeQueue =
                new PriorityQueue<>(Comparator.comparingDouble(graph::getEdgeWeight));

        edgeQueue.addAll(graph.edgesOf(startNode));

        // Algoritmul Prim
        while (!edgeQueue.isEmpty() && visited.size() < graph.vertexSet().size()) {

            DefaultWeightedEdge smallestEdge = edgeQueue.poll();

            Intersection source = graph.getEdgeSource(smallestEdge);
            Intersection target = graph.getEdgeTarget(smallestEdge);

            // Alegem nodul care nu a fost vizitat inca
            Intersection nextNode = visited.contains(source) ? target : source;

            if (visited.contains(nextNode)) {
                continue;
            }

            // Adaugam muchia in MST
            DefaultWeightedEdge newEdge = mst.addEdge(source, target);
            mst.setEdgeWeight(newEdge, graph.getEdgeWeight(smallestEdge));

            visited.add(nextNode);

            // Adaugam muchiile care pornesc din nodul nou adaugat
            for (DefaultWeightedEdge edge : graph.edgesOf(nextNode)) {
                Intersection other = graph.getEdgeSource(edge).equals(nextNode)
                        ? graph.getEdgeTarget(edge)
                        : graph.getEdgeSource(edge);

                if (!visited.contains(other)) {
                    edgeQueue.add(edge);
                }
            }
        }

        return mst;
    }
}
