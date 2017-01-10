package cronometer;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Serving {

    private String[] values;
    private int name;
    private int energy;
    private int fat;
    private int protein;
    private int carbs;

    Serving(String[] titles, String[] values) {
        this.values = values;

        for (int i = 0; i < titles.length; i++) {
            String t = titles[i];
            if (t.equals("Energy (kcal)")) {
                energy = i;
            } else if (t.equals("Fat (g)")) {
                fat = i;
            } else if (t.equals("Food Name")) {
                name = i;
            } else if (t.equals("Protein (g)")) {
                protein = i;
            } else if (t.equals("Carbs (g)")) {
                carbs = i;
            }
        }
    }

    private double get(int i) {
        if (i == 0) {
            throw new RuntimeException("String not found");
        }
        String s = values[i];
        if(s.trim().isEmpty()) {
            return 0D;
        }
        return Double.valueOf(s);
    }
    
    public String getName() {
        return values[name];
    }

    int getEnergyIndex() {
        return energy;
    }

    public double getEnergy() {
        return get(energy);
    }

    public double getFat() {
        return get(fat);
    }

    public double getCarbs() {
        return get(carbs);
    }

    public double getProtein() {
        return get(protein);
    }

    public double getFatCalories() {
        return getFat() * 9;
    }

    public double getCarbCalories() {
        return getCarbs() * 4;
    }

    public double getProteinCalories() {
        return getProtein() * 4;
    }

    public double getTotalCalories() {
        return getFatCalories() + getCarbCalories() + getProteinCalories();
    }

    private double getDoubleRatio(double top) {
        return top / getTotalCalories() * 100;
    }
    
    public double getFatRatio() {
        return getDoubleRatio(getFatCalories());
    }

    public double getCarbRatio() {
        return getDoubleRatio(getCarbCalories());
    }

    public double getProteinRatio() {
        return getDoubleRatio(getProteinCalories());
    }

    public String getRatio() {
        String out = formatRatio(getCarbRatio(),getFatRatio(),getProteinRatio());
        return out;
    }

    static String formatRatio(double c, double f, double p) {
        NumberFormat formatter = new DecimalFormat("#0");     
        String out = formatter.format(c);
        out += "|";
        out += formatter.format(f);
        out += "|";
        out += formatter.format(p);
        return out;
    }
}