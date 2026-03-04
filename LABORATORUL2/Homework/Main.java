import java.util.ArrayList;
import java.util.List;

/**
 * Clasa principala pentru testarea aplicatiei.
 *
 * Creeaza locatii si drumuri, valideaza instanta
 * si verifica daca se poate ajunge de la o locatie la alta
 */
public class Main {

    /**
     * Metoda de pornire a programului.
     *
     * @param args argumente din linia de comanda (nu sunt folosite)
     */
    public static void main(String[] args) {

        ProblemInstance problem = new ProblemInstance();

        City city1 = new City("CityA", 100000);
        City city2 = new City("CityB", 50000);
        Airport airport = new Airport("AirportX", 3);
        GasStation station = new GasStation("Station1", 1.5);

        problem.addLocation(city1);
        problem.addLocation(city2);
        problem.addLocation(airport);
        problem.addLocation(station);

        problem.addRoad(new Road(city1, city2, RoadType.HIGHWAY, 120));
        problem.addRoad(new Road(city2, airport, RoadType.EXPRESS, 50));
        problem.addRoad(new Road(airport, station, RoadType.NATIONAL, 10));

        System.out.println("Instanta valida: " + problem.isValid());

        System.out.println("CityA -> Station1: "
                + problem.canReach(city1, station));

        // ADVANCED

        ProblemInstance problem2 =
                ProblemInstance.generateRandom(5000, 20000);

        List<Location> locations = new ArrayList<>(problem2.getLocations());

        Location start = locations.get(0);
        Location end = locations.get(locations.size() - 1);

        long startTime = System.nanoTime();

        Solution solution = problem2.findShortestRoute(start, end);

        long endTime = System.nanoTime();

        System.out.println("Running time: "
                + (endTime - startTime) / 1_000_000 + " ms");

        Runtime runtime = Runtime.getRuntime();
        long memory = runtime.totalMemory() - runtime.freeMemory();

        System.out.println("Memory used: "
                + memory / 1024 + " KB");

        System.out.println(solution);
    }
}
