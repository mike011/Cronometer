package cronometer;

import java.util.ArrayList;
import java.util.List;

public class Day implements Comparable<Day> {

    private String date;

    private List<Serving> foods = new ArrayList<Serving>();

    Day(String date) {
        this.date = date;
    }

    @Override
    public int compareTo(Day o) {
        return o.date.compareTo(date);
    }

    public void add(String[] titles, String[] splits) {
        foods.add(new Serving(titles, splits));
    }

    public double getEnergy() {
        double total = 0;
        for (Serving f : foods) {
            total += f.getEnergy();
        }
        return total;
    }

    public double getTotalCalories() {
        double total = 0;
        for (Serving f : foods) {
            total += f.getTotalCalories();
        }
        return total;
    }

    public double getFatCalories() {
        double total = 0;
        for (Serving f : foods) {
            total += f.getFatCalories();
        }
        return total;
    }

    public double getCarbCalories() {
        double total = 0;
        for (Serving f : foods) {
            total += f.getCarbCalories();
        }
        return total;
    }

    public double getProteinCalories() {
        double total = 0;
        for (Serving f : foods) {
            total += f.getProteinCalories();
        }
        return total;
    }

    @Override
    public String toString() {
        return date;
    }
}
