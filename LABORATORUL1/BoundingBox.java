public class BoundingBox { // Declaram clasa principala care contine algoritmii pentru bounding box si boundary

    // Determina bounding box-ul unei forme din matrice
    public static int[] findBoundingBox(int[][] m) { // Metoda care primeste o matrice si returneaza coordonatele bounding box-ului
        int n = m.length;
        int minRow = n, maxRow = -1; // initializam limitele randurilor cu valori extreme
        int minCol = n, maxCol = -1; // initializam limitele coloanelor cu valori extreme

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (m[i][j] != 0) { // Daca celula face parte din forma
                    if (i < minRow) minRow = i;
                    if (i > maxRow) maxRow = i;
                    if (j < minCol) minCol = j;
                    if (j > maxCol) maxCol = j;
                }
            }
        }

        if (maxRow == -1) { // Daca maxRow a ramas -1, nu s-a gasit nicio celula != 0
            return null; // nu exista forma in matrice
        }

        // Returnam [minRow, minCol, maxRow, maxCol] - coordonatele bounding box-ului
        return new int[]{minRow, minCol, maxRow, maxCol};
    }

    // Determina o matrice booleana care marcheaza boundaray-ul formei
    public static boolean[][] findBoundary(int[][] m) { // Metoda care returneaza o matrice booleana cu conturul formei
        int n = m.length;
        int cols = m[0].length;
        boolean[][] boundary = new boolean[n][cols];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < cols; j++) {
                if (m[i][j] != 0) { // daca celula face parte din forma
                    if (isOnBoundary(m, i, j)) { // verificam daca este pe contur
                        boundary[i][j] = true; // marcam celula ca fiind pe contur
                    }
                }
            }
        }

        return boundary; // returnam matricea de contur
    }

    // Verifica daca o celula este pe contur
    private static boolean isOnBoundary(int[][] m, int i, int j) { // Metoda care decide daca o celula este pe marginea formei
        int n = m.length;
        int cols = m[0].length;

        // Vecinii directi
        int[][] dirs = {
                {-1, 0}, {1, 0}, // sus, jos
                {0, -1}, {0, 1} // stanga, dreapta
        };

        for (int[] d : dirs) { // parcurgem fiecare directie
            int ni = i + d[0]; // coordonata randului vecinului
            int nj = j + d[1]; // coordonata coloanei vecinului

            // Daca vecinul e in afara matricei sau este fundal (0), celula este pe contur
            if (ni < 0 || ni >= n || nj < 0|| nj >= cols || m[ni][nj] == 0) {
                return true; // celula este pe margine
            }
        }

        return false; // daca toti vecinii sunt parte din forma, nu este contur
    }
}