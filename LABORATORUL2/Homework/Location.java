import java.util.Objects;

/**
 * Clasa abstracta si sealed care reprezinta o locatie generica.
 *
 * Aceasta clasa este bazata pe toate tipurile concrete de locatii
 * (City, Airport, GasStation).
 *
 * Fiind sealed, doar clasele specificate in clauza permits
 * au treptul sa o extinda.
 */
public abstract sealed class Location
        permits City, Airport, GasStation {

    /**
     * Numele locatiei.
     * Este final deoarece nu trebuie modificat dupa creare.
     */
    protected final String name;

    /**
     * Constructor care initializeaza numele locatiei.
     *
     * @param name numele locatiei
     */
    public Location(String name) {
        this.name = name;
    }

    /**
     * Returneaza numele locatiei.
     *
     * @return numele locatiei
     */
    public String getName() {
        return name;
    }

    /**
     * Suprascrierea metodei equals.
     *
     * Doua locatii sunt considerate egale daca:
     * - sunt de acelasi tip (aceeasi clasa)
     * - au acelasi nume
     *
     * @param obj obiectul comparat
     * @return true daca obiectele sunt egale, false altfel
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Location other = (Location) obj;

        return Objects.equals(name, other.name);
    }

    /**
     * Suprascrierea metodei hashCode.
     *
     * Este obligatoriu sa fie suprascrisa impreuna cu equals,
     * pentru a functiona corect in colectii de tip HashSet sau HashMap.
     *
     * @return codul hash al obiectului
     */
    @Override
    public int hashCode() {
        return Objects.hash(getClass(), name);
    }
}