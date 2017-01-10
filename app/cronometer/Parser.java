package cronometer;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import jaybird.FileIO;

public class Parser {

    public static void main(String args[]) {

        File f = new File("E:\\Downloads\\servings.csv");
        List<String> out = FileIO.readFile(f.toPath());
        Collection<Day> days = parseDays(out);
        for (Day s : days) {
            System.out.println(s + " " + s.getEnergy());
        }
        Collection<Week> weeks = parseWeeks(days);
        for (Week w : weeks) {
            System.out.println(w.toString() + "\t" + w.getAverageEnergy() + "\t" + w.getRatio());
        }
        
        Collection<Month> months = parseMonths(days);
        for (Month w : months) {
            System.out.println(w.toString() + "\t" + w.getAverageEnergy() + "\t" + w.getRatio());
        }
        
        Collection<Year> years = parseYears(days);
        for (Year w : years) {
            System.out.println(w.toString() + "\t" + w.getAverageEnergy() + "\t" + w.getRatio());
        }
    }
    
    static Collection<Year> parseYears(Collection<Day> days) {
        Map<String, Year> years = new TreeMap<String, Year>();
        for (Day day : days) {
            Year month = new Year(day.toString());
            if (years.containsKey(month.toString())) {
                month = years.get(month.toString());
            } else {
                years.put(month.toString(), month);
            }
            month.add(day);
        }
        return years.values();
    }
    

    static Collection<Month> parseMonths(Collection<Day> days) {
        Map<String, Month> months = new TreeMap<String, Month>();
        for (Day day : days) {
            Month month = new Month(day.toString());
            if (months.containsKey(month.toString())) {
                month = months.get(month.toString());
            } else {
                months.put(month.toString(), month);
            }
            month.add(day);
        }
        return months.values();
    }
    
    static Collection<Week> parseWeeks(Collection<Day> days) {
        Map<String, Week> weeks = new TreeMap<String, Week>();
        for (Day day : days) {
            Week week = new Week(day.toString());
            if (weeks.containsKey(week.toString())) {
                week = weeks.get(week.toString());
            } else {
                weeks.put(week.toString(), week);
            }
            week.add(day);
        }
        return weeks.values();
    }

    static Collection<Day> parseDays(List<String> out) {
        Map<String, Day> days = new TreeMap<String, Day>();
        String[] titles = out.get(0).split(",");
        for (int x = 1; x < out.size(); x++) {
            String[] splits = split(out.get(x));
            String date = splits[0];
            Day day = new Day(date);
            if (days.containsKey(date)) {
                day = days.get(date);
            } else {
                days.put(date, day);
            }
            day.add(titles, splits);
        }
        return days.values();
    }

    static String[] split(String string) {
        List<String> strings = new ArrayList<String>();
        int comma = string.indexOf(',');
        while (true) {
            comma = string.indexOf(',');
            if(comma == -1) {
                strings.add(string);
                break;
            }
            int quote = string.indexOf('"');
            if(quote == -1) {
                quote = Integer.MAX_VALUE;
            }
            if (comma < quote) {
                strings.add(string.substring(0, comma));
                string = string.substring(comma + 1);
            } else {
                int endQuote = string.indexOf("\",");
                strings.add(string.substring(quote + 1, endQuote));
                string = string.substring(endQuote + 2);
            }
        }
        return (strings.toArray(new String[0]));
    }
}
