// Definim clasa Road
public class Road {

    // Variabila privata pentru numele drumului
    private String name;

    // Variabila privata pentru tipul drumului
    private String type;

    // Variabila privata pentru lungime
    private double length;

    // Variabila privata pentru locatia de start
    private Location start;

    // Variabila privata pentru locatia de final
    private Location end;

    // Constructor default
    public Road() {

    }

    // Constructor cu parametri
    public Road(String name, String type, double length, Location start, Location end) {

        // Setam numele drumului
        this.name = name;

        this.type = type;
        this.length = length;
        this.start = start;
        this.end = end;
    }

    // Getter pentru name
    public String getName() {
        return name;
    }

    // Setter pentru name
    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public double getLength() {
        return length;
    }
    public void setLength(double length) {
        this.length = length;
    }

    public Location getStart() {
        return start;
    }
    public void setStart(Location start) {
        this.start = start;
    }

    public Location getEnd() {
        return end;
    }
    public void setEnd(Location end) {
        this.end = end;
    }

    // Suprascriem metoda toString
    @Override
    public String toString() {
        return "Road{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", length=" + length +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
