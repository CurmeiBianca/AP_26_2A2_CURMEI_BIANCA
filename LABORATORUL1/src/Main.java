public class Main { // Declaram clasa principala a programului Java
    public static void main(String[] args) { // Metoda main - punctul de intrare al aplicatiei

        // Afiseaza mesajul "Hello World!"
        System.out.println("Hello World!"); // afiseaza textul in consola

        // Definirea array-ului de limbaje
        String[] languages = { // Declaram un array de stringuri numit "languages"
                "C", "C++", "C#", "Python", "Go",
                "Rust", "JavaScript", "PHP", "Swift", "Java"
        };

        // Generarea unui numar aleator n
        int n = (int) (Math.random() * 1_000_000); // genereaza un numar intre 0 si 999.999
        System.out.println("Random n = " + n); // afiseaza numarul generat

        // Calculul cerut
        int result = n * 3; // inmultim n cu 3
        result += 0b10101; // adaugam numarul binar 10101 (21)
        result += 0xFF; // adaugam numarul hexadecimal FF (255)
        result *= 6; // inmultim rezultatul cu 6

        System.out.println("After operations = " + result); // afisam rezultatul dupa calcule

        // Suma cifrelor repetata pana ramane o singura cifra
        result = sumDigitsRepeated(result);

        // Afisam limbajul corespunzator indexului rezultat
        System.out.println("Willy-nilly, this semester I will learn " + languages[result]);
    }

    // Functie care calculeaza suma cifrelor pana ramane o singura cifra
    public static int sumDigitsRepeated(int number) { // Functie care primeste un numar
        while (number > 9) {
            number = sumDigits(number);
        }
        return number;
    }

    // Suma cifrelor unui numar
    public static int sumDigits(int number) {
        int sum = 0;
        while (number != 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }
}