public class Main {

    public static void main(String[] args) {

        // Cream prima locatie folosind constructor
        Location loc1 = new Location("Bucharest", "City", 10, 20);

        // Cream a doua locatie
        Location loc2 = new Location("Cluj", "City", 50, 80);

        // Cream a treia locatie
        Location loc3 = new Location("Airport", "Airport", 15, 25);

        // Afisam locatiile
        System.out.println(loc1);
        System.out.println(loc2);
        System.out.println(loc3);

        // Cream primul drum
        Road road1 = new Road("Highway1", "Highway", 300, loc1, loc2);
        // Cream al doilea drum
        Road road2 = new Road("AirportRoad", "Express", 10, loc1, loc3);

        System.out.println(road1);
        System.out.println(road2);

        // Cream locatie folosind constructor default
        Location loc4 = new Location();

        // Setam valorile folosind setter
        loc4.setName("Constanta");
        loc4.setType("City");
        loc4.setX(100);
        loc4.setY(200);

        // Afisam loc4
        System.out.println(loc4);
    }
}