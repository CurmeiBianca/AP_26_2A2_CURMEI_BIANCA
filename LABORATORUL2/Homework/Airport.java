/**
 * Clasa concreta care reprezinta o locatie de tip aeroport.
 *
 * Extinde clasa abstracta Location si adauga
 * o proprietate specifica: numarul de terminale.
 */
public final class Airport extends Location {

    /**
     * Numarul de terminale ale aeroportului.
     */
    private final int numberOfTerminals;

    /**
     * Constructor care initializeaza numele si numarul de terminale.
     *
     * @param name numele aeroportului
     * @param numberOfTerminals numarul de terminale
     */
    public Airport(String name, int numberOfTerminals) {
        super(name);
        this.numberOfTerminals = numberOfTerminals;
    }

    /**
     * Returneaza numarul de terminale ale aeroportului.
     *
     * @return numarul de terminale
     */
    public int getNumberOfTerminals() {
        return numberOfTerminals;
    }
}
