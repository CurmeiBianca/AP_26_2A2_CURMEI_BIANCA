import java.util.List;

public class Solution {

    private final List<Location> route;

    private final double totalCost;

    public Solution(List<Location> route, double totalCost) {
        this.route = route;
        this.totalCost = totalCost;
    }

    public double getTotalCost() {
        return totalCost;
    }

    @Override
    public String toString() {
        return "Route: " + route + ", total cost = " + totalCost;
    }
}
