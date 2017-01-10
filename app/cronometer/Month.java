package cronometer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Month extends Week {

    List<Day> days = new ArrayList<Day>();
    private int year;
    private int month;

    Month(String date) {
        super(date);
        Calendar c = Week.parseDate(date);
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        ++month;
    }

    @Override
    public String toString() {
        return toStringTwoFields(year, month);
    }

    static String toStringTwoFields(int one, int two) {
        String out = String.valueOf(one);
        out += "-";
        if(two < 10) {
            out += "0";
        }
        out += String.valueOf(two);
        return out;
    }
}
