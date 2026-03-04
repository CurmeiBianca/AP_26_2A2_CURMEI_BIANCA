/**
 * Clasa concreta care reprezinta o locatie de tip oras.
 *
 * Extinde clasa abstracta Location si adauga o proprietate specifica: populatia.
 */
public final class City extends Location {

    /**
     * Populatia orasului.
     */
    private final int population;

    /**
     * Constructor care initializeaza numele si populatia orasului.
     *
     * @param name numele orasului
     * @param population populatia orasului
     */
    public City(String name, int population) {
        super(name);
        this.population = population;
    }

    /**
     * Returneaza populatia.
     *
     * @return populatia orasului
     */
    public int getPopulation() {
        return population;
    }
}