package cronometer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Week implements Comparable<Week> {

    List<Day> days = new ArrayList<Day>();
    private int year;
    private int week;

    public Week(String date) {
        Calendar c = parseDate(date);
        year = c.get(Calendar.YEAR);
        week = c.get(Calendar.WEEK_OF_YEAR);
    }

    static Calendar parseDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd");
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(formatter.parse(date));
            return c;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getYear() {
        return year;
    }

    public int getWeek() {
        return week;
    }

    @Override
    public int compareTo(Week o) {
        if (year > o.year) {
            return 1;
        } else if (year < o.year) {
            return -1;
        }
        if (o.year == year) {
            if (week > o.week) {
                return 1;
            } else if (week < o.week) {
                return -1;
            }
        }
        return 0;
    }

    public void add(Day d) {
        days.add(d);
    }

    @Override
    public String toString() {
        return Month.toStringTwoFields(year, week);
    }

    public int getEnergy() {
        int tot = 0;
        for (Day d : days) {
            tot += d.getEnergy();
        }
        return tot;
    }

    public int getAverageEnergy() {
        return getEnergy() / days.size();
    }

    public double getTotalCalories() {
        double total = 0;
        for (Day f : days) {
            total += f.getTotalCalories();
        }
        return total;
    }

    public double getFatCalories() {
        double total = 0;
        for (Day f : days) {
            total += f.getFatCalories();
        }
        return total;
    }

    public double getCarbCalories() {
        double total = 0;
        for (Day f : days) {
            total += f.getCarbCalories();
        }
        return total;
    }

    public double getProteinCalories() {
        double total = 0;
        for (Day f : days) {
            total += f.getProteinCalories();
        }
        return total;
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
        return Serving.formatRatio(getCarbRatio(), getFatRatio(),
                getProteinRatio());
    }
}
