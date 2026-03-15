import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Profile> profiles = new ArrayList<>();

        profiles.add(new Person("Andrei", 22));
        profiles.add(new Person("Maria", 25));

        profiles.add(new Company("Google", 100000));
        profiles.add(new Company("Amazon", 150000));

        profiles.sort(new Comparator<Profile>() {

            @Override
            public int compare(Profile p1, Profile p2) {

                return p1.getName().compareTo(p2.getName());
            }
        });

        for (Profile p : profiles) {
            System.out.println(p);
        }
    }
}
