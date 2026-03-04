/**
 * Clasa concreta care reprezinta o locatie de tip benzinarie.
 *
 * Extinde clasa abstracta Location si adauga
 * o proprietate specifica: pretul carburantului.
 */
public final class GasStation extends Location {

    /**
     * Pretul carburantului la aceasta benzinarie.
     */
    private final double gasPrice;

    /**
     * Constructor care initializeaza numele si pretul carburantului.
     *
     * @param name numele benzinariei
     * @param gasPrice pretul carburantului
     */
    public GasStation(String name, double gasPrice) {
        super(name);
        this.gasPrice = gasPrice;
    }

    /**
     * Returneaza pretul carburantului.
     *
     * @return pretul carburantului
     */
    public double getGasPrice() {
        return gasPrice;
    }
}
