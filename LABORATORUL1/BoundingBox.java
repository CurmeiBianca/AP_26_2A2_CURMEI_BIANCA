public class BoundingBox {

    // Functie care determina bounding box-ul unei forme dintr-o matrice
    public static int[] findBoundingBox(int[][] m) {
        int rows = m.length; //numarul de randuri din matrice
        int cols = m[0].length; // numarul de coloane din matrice

        // Initializam limitele bounding box-ului
        // minRow si minCol sunt setate la valori mari pentru a fi reduse
        // maxRow si maxCol sunt setate la valori mici pentru a fi crescute
        int minRow = rows, maxRow = -1;
        int minCol = cols, maxCol = -1;

        // Parcurgem intreaga matrice
        for (int i = 0; i < rows; i++) { // pentru fiecare rand
            for (int j = 0; j < cols; j++) { // pentru fiecare coloana
                if (m[i][j] != 0) { // daca celula face parte din forma
                    if (i < minRow)
                        minRow = i; // actualizam randul de sus
                    if (i > maxRow)
                        maxRow = i; // actualizam randul de jos
                    if (j < minCol)
                        minCol = j; // actualizam coloana stanga
                    if (j > maxCol)
                        maxCol = j; // actualizam coloana dreapta
                }
            }
        }

        // Daca nu s-a gasit nicio celula a formei, returnam null
        if (maxRow == -1)
            return null;

        // Returnam bounding box-ul sub forma {minRow, minCol, maxRow, maxCol}
        return new int[]{minRow, minCol, maxRow, maxCol};
    }

    // Functie care determina matricea frontiera a formei
    public static boolean[][] findBoundary(int[][] m) {
        int rows = m.length; // numarul de randuri
        int cols = m[0].length; // numarul de coloane

        // Initializam matricea de frontiera cu false
        boolean[][] boundary = new boolean[rows][cols];

        // Parcurgem matricea
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                // Daca celula face parte din forma si este pe frontiera
                if (m[i][j] != 0 && isOnBoundary(m, i, j))
                    boundary[i][j] = true; // marcam ca frontiera

        return boundary; // returnam matricea frontiera
    }

    // Functie care verifica daca o celula este pe frontiera
    private static boolean isOnBoundary(int[][] m, int i, int j) {
        int rows = m.length; // numarul de randuri
        int cols = m[0].length; // numarul de coloane

        // Directiile vecinilor: sus, jos, stanga, dreapta
        int[][] dirs = {
                {-1,0},{1,0},{0,-1},{0,1}
        };

        // Verificam fiecare vecin
        for (int[] d : dirs) {
            int ni = i + d[0]; // randul vecinului
            int nj = j + d[1]; // coloana vecinului

            // Daca vecinul este in afara matricei sau este fundal (0)
            if (ni < 0 || ni >= rows || nj < 0 || nj >= cols || m[ni][nj] == 0)
                return true; // celula curenta este pe frontiera
        }

        return false; // altfel, nu este frontiera
    }

    public static void main(String[] args) {
        // Exemplu de marice cu o forma
        int[][] matrix = {
                {0,0,0,0,0},
                {0,1,1,0,0},
                {0,1,1,1,0},
                {0,0,1,0,0},
                {0,0,0,0,0}
        };

        // Calculam bounding box-ul
        int[] box = findBoundingBox(matrix);

        System.out.println("Bounding Box:");
        System.out.println("minRow = " + box[0]);
        System.out.println("minCol = " + box[1]);
        System.out.println("maxRow = " + box[2]);
        System.out.println("maxCol = " + box[3]);

        // Calculam matricea frontiera
        boolean[][] boundary = findBoundary(matrix);

        System.out.println("\nBoundary:");
        for (int i = 0; i < boundary.length; i++) {
            for (int j = 0; j < boundary[0].length; j++) {
                if (boundary[i][j])
                    System.out.print("1 "); // celula pe frontiera
                else
                    System.out.print("0 "); // celula non-frontiera
            }
            System.out.println();
        }
    }
}