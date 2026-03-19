package org.example.homework;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import com.github.javafaker.Faker;

import org.example.compulsory.*;

import java.util.*;

import org.jgrapht.Graph;
import org.jgrapht.alg.spanning.KruskalMinimumSpanningTree;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Getter
@AllArgsConstructor
@ToString
public class City {

    private List<Intersection> intersections;

    private List<Street> streets;

    public void printCityInfo() {

        System.out.println("City information:");
        System.out.println("Number of intersections: " + intersections.size());
        System.out.println("Number of streets: " + streets.size());
    }

    /*
        Metoda care intoarce toate strazile care:
        - au lungimea mai mare decat o valoare specificata (minLength)
        - si se conecteaza la cel putin o intersectie la care se intalnesc cel putin 'minDegree' strazi.
     */
    public List<Street> getStreetsLongerThanAndWidthHighDegree(int minLength, int minDegree) {

        // 1. Calculam gradul fiecarei intersectii.
        Map<Intersection, Long> degreeByIntersection = streets.stream()

                .flatMap(street -> List.of(
                        street.getFirstIntersection(),
                        street.getSecondIntersection()
                ).stream())

                .collect(Collectors.groupingBy(
                        intersection -> intersection,
                        Collectors.counting()
                ));

        // 2. Filtram strazile dupa lungime si gradul intersectiilor.
        return streets.stream()

                .filter(street -> street.getLength() > minLength)

                .filter(street -> {

                    long degreeFirst = degreeByIntersection.getOrDefault(
                            street.getFirstIntersection(), 0L);

                    long degreeSecond = degreeByIntersection.getOrDefault(
                            street.getSecondIntersection(), 0L);

                    return degreeFirst >= minDegree || degreeSecond >= minDegree;
                })

                .toList();
    }

    public static City generateRandomCity(int numberOfIntersections, int numberOfStreets) {

        // Folosim JavaFaker pentru a genera nume realiste
        Faker faker = new Faker();

        List<Intersection> intersections =
                IntStream.range(0, numberOfIntersections)
                        .mapToObj(i -> new Intersection(faker.address().streetName()))
                        .toList();

        Random random = new Random();

        List<Street> streets = new ArrayList<>();

        for (int i = 0; i < numberOfStreets; i++) {

            Intersection a = intersections.get(random.nextInt(numberOfIntersections));
            Intersection b = intersections.get(random.nextInt(numberOfIntersections));

            // Evitam strazile care leaga aceeasi intersectie
            if (a.equals(b)) {
                i--; // refacem iteratia
                continue;
            }

            int length = random.nextInt(20) + 1;

            Street street = new Street(
                    faker.address().streetName(), // nume random
                    length,
                    a,
                    b
            );

            streets.add(street);
        }

        return new City(intersections, streets);
    }

    public Graph<Intersection, DefaultWeightedEdge> toGraph() {

        Graph<Intersection, DefaultWeightedEdge> graph =
                new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

        // Adaugam toate intersectiile ca noduri
        intersections.forEach(graph::addVertex);

        // Adaugam toate strazile ca muchii
        for (Street s : streets) {
            DefaultWeightedEdge edge =
                    graph.addEdge(s.getFirstIntersection(), s.getSecondIntersection());

            if (edge != null)
                graph.setEdgeWeight(edge, s.getLength());
        }

        return graph;
    }

    /*
        Metoda care genereaza o lista de MST-uri (arbori de acoperire minima,
        ordonate dupa cost, si returneaza primele k solutii.
     */
    public List<Graph<Intersection, DefaultWeightedEdge>> getKBestMSTs(int k) {

        Graph<Intersection, DefaultWeightedEdge> graph = toGraph();

        // 1. MST-ul de baza
        KruskalMinimumSpanningTree<Intersection, DefaultWeightedEdge> baseMST =
                new KruskalMinimumSpanningTree<>(graph);

        Set<DefaultWeightedEdge> baseEdges =
                baseMST.getSpanningTree().getEdges();

        List<Graph<Intersection, DefaultWeightedEdge>> solutions = new ArrayList<>();

        solutions.add(copyGraphWithEdges(graph, baseEdges));

        // 2. Generam solutii alternative
        for (DefaultWeightedEdge removedEdge : baseEdges) {

            Graph<Intersection, DefaultWeightedEdge> modifiedGraph = toGraph();
            modifiedGraph.removeEdge(removedEdge);

            KruskalMinimumSpanningTree<Intersection, DefaultWeightedEdge> mst =
                    new KruskalMinimumSpanningTree<>(modifiedGraph);

            Set<DefaultWeightedEdge> edges = mst.getSpanningTree().getEdges();

            if (edges.size() == intersections.size() - 1) {

                solutions.add(copyGraphWithEdges(
                        modifiedGraph,
                        edges
                ));
            }
        }

        // 3. Sortam solutiile dupa cost
        solutions.sort(Comparator.comparingDouble(this::computeGraphCost));

        // 4. Returnam primele k
        return solutions.stream().limit(k).toList();
    }

    /*
        Metoda care creeaza o copie a unui graf, dar pastreaza doar muchiile
        care fac parte dintr-un anumit arbore de acoperire (MST).
     */
    public Graph<Intersection, DefaultWeightedEdge> copyGraphWithEdges(
            Graph<Intersection, DefaultWeightedEdge> originalGraph,
            Set<DefaultWeightedEdge> mstEdges) {

        Graph<Intersection, DefaultWeightedEdge> copiedGraph =
                new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

        intersections.forEach(copiedGraph::addVertex);

        for (DefaultWeightedEdge edge : mstEdges) {

            Intersection source = originalGraph.getEdgeSource(edge);
            Intersection target = originalGraph.getEdgeTarget(edge);

            DefaultWeightedEdge newEdge = copiedGraph.addEdge(source, target);

            copiedGraph.setEdgeWeight(newEdge, originalGraph.getEdgeWeight(edge));
        }

        return copiedGraph;
    }

    /*
        Metoda care calculeaza costul total al unui graf.
     */
    public double computeGraphCost(Graph<Intersection, DefaultWeightedEdge> graph) {

        return graph.edgeSet().stream()

                .mapToDouble(graph::getEdgeWeight)

                .sum();
    }
}