import java.util.*;

/**
 * Reprezinta o instanta a problemei de retea de locatii si drumuri.
 *
 * O instanta contine:
 * - un set de locatii unice
 * - un set de drumuri unice
 *
 * Permite verificarea validitatii instantei si determinarea
 * daca este posibil sa se ajunga de la o locatie la alta.
 */
public class ProblemInstance {

    /**
     * Set de locatii.
     * Folosim HashSet pentru a preveni adaugarea duplicatelor.
     */
    private final Set<Location> locations = new HashSet<>();

    /**
     * Returneaza setul de locatii din instanta.
     *
     * @return multimea locatiilor
     */
    public Set<Location> getLocations() {
        return locations;
    }

    /**
     * Set de drumuri.
     * Folosim HashSet pentru a preveni adaugarea duplicatelor.
     */
    private final Set<Road> roads = new HashSet<>();

    /**
     * Adauga o locatie in instanta.
     *
     * @param location locatie de adaugat
     * @return true daca locatia a fost adaugata, false daca exista deja
     */
    public boolean addLocation(Location location) {
        return locations.add(location);
    }

    /**
     * Adauga un drum in instanta.
     *
     * Drumul este adaugat daca:
     * - locatiile de plecare si destinatie exista in instanta
     * - drumul nu exista deja
     *
     * @param road drumul de adaugat
     * @return true daca drumul a fost adaugat, false altfel
     */
    public boolean addRoad (Road road) {

        if (!locations.contains(road.getFrom())
                || !locations.contains(road.getTo())) {
            return false;
        }

        return roads.add(road);
    }

    /**
     * Verifica daca instanta este valida.
     *
     * O instanta nu este valida daca:
     * - nu exista nicio locatie
     * - exista drumuri care au locatii inexistente in instanta
     *
     * @return true daca instanta este valida, false altfel
     */
    public boolean isValid() {

        if (locations.isEmpty()) return false;

        for (Road road : roads) {
            if (!locations.contains(road.getFrom())
                    || !locations.contains(road.getTo())) {
                return false;
            }
        }

        return true;
    }

    /**
     * Determina daca este posibil sa se ajunga de la o locatie la alta.
     *
     * Foloseste algoritmul BFS pentru a parcurge graful.
     *
     * @param start locatia de start
     * @param end locatia destinatie
     * @return true daca exista un drum de la start la end, false altfel
     */
    public boolean canReach(Location start, Location end) {

        if (!locations.contains(start) || !locations.contains(end))
            return false;

        Set<Location> visited = new HashSet<>();
        Queue<Location> queue = new LinkedList<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {

            Location current = queue.poll();

            if (current.equals(end))
                return true;

            for (Road road : roads) {

                if (road.getFrom().equals(current)) {

                    Location neighbor = road.getTo();

                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.add(neighbor);
                    }
                }
            }
        }

        return false;
    }

    // ADVANCED

    public Solution findShortestRoute(Location start, Location end) {

        Map<Location, Double> distance = new HashMap<>();

        Map<Location, Location> previous = new HashMap<>();

        for (Location location : locations) {
            distance.put(location, Double.POSITIVE_INFINITY);
        }

        distance.put(start, 0.0);

        PriorityQueue<Location> queue =
                new PriorityQueue<>(Comparator.comparingDouble(distance::get));

        queue.add(start);

        while (!queue.isEmpty()) {

            Location current = queue.poll();

            if (current.equals(end)) {
                break;
            }

            for (Road road : roads) {

                if (road.getFrom().equals(current)) {

                    Location neighbor = road.getTo();

                    double altDistance =
                            distance.get(current) + road.getLength();

                    if (altDistance < distance.get(neighbor)) {

                        distance.put(neighbor, altDistance);

                        previous.put(neighbor, current);

                        queue.add(neighbor);
                    }
                }
            }
        }

        List<Location> route = new ArrayList<>();
        Location step = end;

        if (!previous.containsKey(step) && !step.equals(start)) {
            return null; // nu exista solutie
        }

        while (step != null) {
            route.add(step);
            step = previous.get(step);
        }

        Collections.reverse(route);

        return new Solution(route, distance.get(end));
    }

    public static ProblemInstance generateRandom(int locationsCount, int roadsCount) {

        ProblemInstance problem = new ProblemInstance();

        Random random = new Random();

        List<Location> locationList = new ArrayList<>();

        for (int i = 0; i < locationsCount; i++) {

            int type = random.nextInt(3);

            Location location;

            if (type == 0) {
                location = new City("City" + i, random.nextInt(1_000_000));
            } else if (type == 1) {
                location = new Airport("Airport" + i, random.nextInt(10) + 1);
            } else {
                location = new GasStation("GasStation" + i, random.nextDouble() * 10);
            }

            problem.addLocation(location);

            locationList.add(location);
        }

        for (int i = 0; i < roadsCount; i++) {

            Location from = locationList.get(random.nextInt(locationsCount));
            Location to = locationList.get(random.nextInt(locationsCount));

            if (!from.equals(to)) {

                RoadType[] types = RoadType.values();
                RoadType type = types[random.nextInt(types.length)];

                double length = 1 + random.nextDouble() * 100;

                Road road = new Road(from, to, type, length);

                problem.addRoad(road);
            }
        }

        return problem;
    }
}