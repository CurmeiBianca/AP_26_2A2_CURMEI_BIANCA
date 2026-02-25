public class Matrix2D {
    public static void main(String[] args) {

        if (args.length < 2) { // Verificam daca utilizatorul a dat cele 2 argumente necesare
            System.out.println("Usage: java Matrix2D <n> <rectangle|circle>"); // mesaj de ajutor
            return;
        }

        int n = Integer.parseInt(args[0]); // convertim primul argument la numar (dimensiunea imaginii)
        String type = args[1]; // al doilea argument reprezinta tipul imaginii cerute

        long start = System.nanoTime(); // pornim cronometrul pentru a masura timpul de executie

        int [][] image; // declaram matricea care va contine imaginea

        if (type.equalsIgnoreCase("rectangle")) { // Daca utilizatorul a cerut dreptunghi
            image = createDarkRectangle(n); // generam dreptunghiul intunecat
        } else if (type.equalsIgnoreCase("circle")) { // Daca utilizatorul a cerut cerc
            image = createWhiteCircle(n); // generam cercul alb
        } else { // Daca tipul este necunoscut
            System.out.println("Unknown type: " + type); // afisam eroare
            return; // oprim executia
        }

        long end = System.nanoTime(); // oprim cronometrul

        if (n <= 50) { // Daca imaginea este mica
            System.out.println(matrixToString(image)); // afisam reprezentarea grafica
        } else { // Daca imaginea este mare
            System.out.println("Execution time: " + (end - start) / 1_000_000 + " ms"); // afisam doar timpul
        }
    }

    // Dreptunghi intunecat pe fundal alb
    public static int[][] createDarkRectangle(int n) {
        int[][] m = new int[n][n]; // cream o matrice n x n

        // Fundal alb
        for (int i = 0; i < n; i++) // parcurgem fiecare linie
            for (int j = 0; j < n; j++) // parcurgem fiecare coloana
                m[i][j] = 255; // setam pixelul la alb (255)

        // Dreptunghi central intunecat
        int margin = n / 4; // calculam marginea pentru pozitionarea dreptunghiului
        for (int i = margin; i < n - margin; i++) // parcurgem zona interioara
            for (int j = margin; j < n - margin; j++)
                m[i][j] = 30; // setam pixelul la gri inchis (aproape negru)

        return m; // returnam matricea generata
    }

    // Cerc alb pe fundal negru
    public static int[][] createWhiteCircle(int n) {
        int[][] m = new int[n][n]; // cream matricea n x n

        int cx = n / 2; // coordonata X a centrului cercului
        int cy = n / 2; // coordonata X a centrului cercului
        int radius = n / 3; // raza cercului

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int dx = i - cx; // distanta pe axa X fata de centru
                int dy = j - cy; // distanta pe axa Y fata de centru
                double dist = Math.sqrt(dx * dx + dy * dy); // calculam distanta pana la centru

                if (dist <= radius) // daca punctul este in interiorul cercului
                    m[i][j] = 255; // pixel alb
                else
                    m[i][j] = 0; // pixel negru
            }
        }

        return m; // returnam matricea
    }

    // Reprezentare frumoasa cu Unicode
    public static String matrixToString(int[][] m) {
        StringBuilder sb = new StringBuilder(); // folosim StringBuilder pentru eficienta

        for (int[] row : m) { // parcurgem fiecare linie din matrice
            for (int value : row) { // parcurgem fiecare pixel
                char c = grayscaleToChar(value); // convertim valoarea grayscale intr-un caracter
                sb.append(c).append(c); // dublam pentru un aspect mai plin
            }
            sb.append("\n"); // trecem la linia urmatoare
        }

        return sb.toString(); // returnam string-ul final
    }

    // Conversie grayscale -> caracter Unicode
    public static char grayscaleToChar(int v) {
        if (v < 50) return '\u2588'; // █ - foarte intunecat
        if (v < 120) return '\u2593'; // ▓ - intunecat
        if (v < 200) return '\u2592'; // ▒ - gri
        return '\u2591'; // ░ - deschis
    }
}