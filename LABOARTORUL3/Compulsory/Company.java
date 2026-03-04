public class Company implements Profile, Comparable<Company> {

    private String name;
    private int employees;

    public Company(String name, int employees) {
        this.name = name;
        this.employees = employees;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getEmployees() {
        return employees;
    }

    @Override
    public int compareTo(Company other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "Company: " + name + ", employees=" + employees;
    }
}
