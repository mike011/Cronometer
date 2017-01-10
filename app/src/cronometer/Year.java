package cronometer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Year extends Week {

    List<Day> days = new ArrayList<Day>();
    private int year;

    Year(String date) {
        super(date);
        Calendar c = Week.parseDate(date);
        year = c.get(Calendar.YEAR);
    }

    @Override
    public String toString() {
        return String.valueOf(year);
    }
}
