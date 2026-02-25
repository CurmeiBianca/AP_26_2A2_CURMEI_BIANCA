// Definim clasa Location
public class Location {

    // Variabila privata pentru numele locatiei
    private String name;

    // Variabila privata pentru tipul locatiei (ex: oras, aeroport, etc)
    private String type;

    // Variabila privata pentru coordonata X
    private double x;

    // Variabila privata pentru coordonata Y
    private double y;

    // Constructor fara parametri (constructor default)
    public Location() {
        // nu face nimic, dar este necesar
    }

    // Constructor cu parametri pentru initializarea variabilelor
    public Location(String name, String type, double x, double y) {

        // Setam numele locatiei
        this.name = name;

        // Setam tipul locatiei
        this.type = type;

        // Setam coordonata x
        this.x = x;

        // Setam coordonata y
        this.y = y;
    }

    // Getter pentru name (returneaza numele)
    public String getName() {
        return name;
    }

    // Setter pentru name (seteaza numele)
    public void setName(String name) {
        this.name = name;
    }

    // Getter pentru type
    public String getType() {
        return type;
    }

    // Setter  pentru type
    public void setType(String tyipe) {
        this.type = type;
    }

    // Getter pentru x
    public double getX() {
        return x;
    }

    // Setter pentru x
    public void setX(double x) {
        this.x = x;
    }

    // Getter pentru y
    public double getY() {
        return y;
    }

    // Setter pentru y
    public void setY(double y) {
        this.y = y;
    }

    // Suprascriem metoda toString pentru afisare frumoasa
    @Override
    public String toString() {

        // Returnam un text care contine toate informatiile
        return "Location{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}