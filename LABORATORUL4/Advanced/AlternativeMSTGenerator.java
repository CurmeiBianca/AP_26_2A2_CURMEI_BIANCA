package org.example.advanced;

import org.example.compulsory.Intersection;
import org.example.homework.City;

import org.jgrapht.Graph;
import org.jgrapht.alg.spanning.KruskalMinimumSpanningTree;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.*;

public class AlternativeMSTGenerator {

    /*
        Metoda care genereaza primele k MST-uri ordonate dupa cost.
        Foloseste Kruskal si elimina pe rand cate o muchie din MST-ul de baza.
     */
    public List<Graph<Intersection, DefaultWeightedEdge>> getKBestMSTs(City city, int k) {

        Graph<Intersection, DefaultWeightedEdge> graph = city.toGraph();

        KruskalMinimumSpanningTree<Intersection, DefaultWeightedEdge> baseMST =
                new KruskalMinimumSpanningTree<>(graph);

        Set<DefaultWeightedEdge> baseEdges = baseMST.getSpanningTree().getEdges();

        List<Graph<Intersection, DefaultWeightedEdge>> solutions = new ArrayList<>();
        solutions.add(city.copyGraphWithEdges(graph, baseEdges));

        // Generam solutii alternative
        for (DefaultWeightedEdge removedEdge : baseEdges) {

            Graph<Intersection, DefaultWeightedEdge> modifiedGraph = city.toGraph();
            modifiedGraph.removeEdge(removedEdge);

            KruskalMinimumSpanningTree<Intersection, DefaultWeightedEdge> mst =
                    new KruskalMinimumSpanningTree<>(modifiedGraph);

            Set<DefaultWeightedEdge> edges = mst.getSpanningTree().getEdges();

            if (edges.size() == city.getIntersections().size() - 1) {
                solutions.add(city.copyGraphWithEdges(modifiedGraph, edges));
            }
        }

        solutions.sort(Comparator.comparingDouble(city::computeGraphCost));

        return solutions.stream().limit(k).toList();
    }
}
