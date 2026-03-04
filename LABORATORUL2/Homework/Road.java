import java.util.Objects;

/**
 * Clasa care reprezinta un drum intre doua locatii.
 *
 * Un drum are:
 * - o locatie de plecare
 * - o locatie de destinatie
 * - un tip (definit prin enum RoadType)
 * - o lungime
 */
public class Road {

    /**
     * Locatie de plecare.
     */
    private final Location from;

    /**
     * Locatie de destinatie.
     */
    private final Location to;

    /**
     * Tipul drumului.
     */
    private final RoadType type;

    /**
     * Lungimea drumului.
     */
    private final double length;

    /**
     * Constructor care initializeaza toate campurile drumului.
     *
     * @param from locatie de plecare
     * @param to locatie de destinatie
     * @param type tipul drumului
     * @param length lungimea drumului
     */
    public Road(Location from, Location to, RoadType type, double length) {
        this.from = from;
        this.to = to;
        this.type = type;
        this.length = length;
    }

    /**
     * Returneaza locatia de plecare.
     *
     * @return locatia de plecare
     */
    public Location getFrom() {
        return from;
    }

    /**
     * Returneaza locatia de destinatie.
     *
     * @return locatia de destinatie
     */
    public Location getTo() {
        return to;
    }

    /**
     * Returneaza tipul drumului.
     *
     * @return tipul drumului
     */
    public RoadType getType() {
        return type;
    }

    /**
     * Returneaza lungimea drumului.
     *
     * @return lungimea drumului
     */
    public double getLength() {
        return length;
    }

    /**
     * Suprascrierea metodei equals.
     *
     * Doua drumuri sunt considerate egale daca:
     * - au aceleasi locatii (from si to)
     * - au acelasi tip
     *
     * Lungimea nu este inclusa in comparatii.
     *
     * @param obj obiectul comparat
     * @return true daca drumurile sunt egale false altfel
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (!(obj instanceof Road other)) return false;

        return Objects.equals(from, other.from)
                && Objects.equals(to, other.to)
                && type == other.type;
    }

    /**
     * Suprascrierea metodei hashCode.
     *
     * Este necesara pentru a asigura functionarea corecta
     * in colectii de tip HashSet sau HashMap.
     *
     * @return codul hash al drumului
     */
    @Override
    public int hashCode() {
        return Objects.hash(from, to, type);
    }
}
