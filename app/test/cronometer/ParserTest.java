package cronometer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

public class ParserTest {

    private static final String VALUE = "2012-9-11,Blueberries raw,0.50 cup,42.18,,,62.32,0.03,,0.03,0.31,0.09,0.04,4.44,39.96,7.18,,0.42,14.28,4.44,0.04,0.21,4.44,0.25,8.88,56.98,0.07,0.74,0.12,10.72,1.78,0.02,7.37,0.24,,0.03,0.04,0.07,0.11,0.02,,0.01,0.01,0.02,0.03,0.01,0.01,0.02,0.55,0.01,0.00,0.01,0.02";
    private static final String VALUE_2 = "2012-9-11,Raspberries raw,0.50 cup,31.98,,,52.74,0.02,,0.02,0.37,0.20,0.03,12.92,20.30,16.11,,0.54,4.80,15.38,0.06,0.42,13.53,0.41,17.84,92.87,0.12,0.62,0.26,7.34,4.00,0.63,2.72,0.40,,0.04,0.08,0.15,0.23,0.01,,,,,,,,,0.74,,,,";

    @Test
    public void testParseDaysSameDay() throws Exception {
        List<String> out = new ArrayList<String>();
        out.add(ServingTest.TITLE);
        out.add(VALUE);
        out.add(VALUE_2);
        Collection<Day> days = Parser.parseDays(out);
        assertThat(days.size(), is(1));
        assertThat(days.iterator().next().getEnergy(), is(42.18 + 31.98));
    }

    private Collection<Day> parseTwoDays() {
        List<String> out = new ArrayList<String>();
        out.add(ServingTest.TITLE);
        out.add(ServingTest.VALUE);
        out.add(VALUE_2);
        Collection<Day> days = Parser.parseDays(out);
        return days;
    }

    @Test
    public void testParseDaysDIfferent() throws Exception {
        Collection<Day> days = parseTwoDays();
        assertThat(days.size(), is(2));
    }

    @Test
    public void testParseWeeksOneWeek() throws Exception {
        Collection<Week> weeks = Parser.parseWeeks(parseTwoDays());
        assertThat(weeks.size(), is(1));
    }
    
    @Test
    public void testParseWeeksOneWeek2() throws Exception {
        String nextWeek = "2012-9-22,Raspberries raw,0.50 cup,31.98,,,52.74,0.02,,0.02,0.37,0.20,0.03,12.92,20.30,16.11,,0.54,4.80,15.38,0.06,0.42,13.53,0.41,17.84,92.87,0.12,0.62,0.26,7.34,4.00,0.63,2.72,0.40,,0.04,0.08,0.15,0.23,0.01,,,,,,,,,0.74,,,,";
        List<String> out = new ArrayList<String>();
        out.add(ServingTest.TITLE);
        out.add(ServingTest.VALUE);
        out.add(nextWeek);
        Collection<Day> days = Parser.parseDays(out);        
        Collection<Week> weeks = Parser.parseWeeks(days);
        assertThat(weeks.size(), is(2));
    }

    @Test
    public void testSplitJustDate() throws Exception {
        String csv = "2012-12-7,";
        String[] split = Parser.split(csv);
        assertThat(split[0], is("2012-12-7"));
    }
    
    @Test
    public void testSplit() throws Exception {
        String csv = "2012-12-7,\"Bananas\"";
        String[] split = Parser.split(csv);
        int i = 0;
        assertThat(split[i++], is("2012-12-7"));
        assertThat(split[i++], is("\"Bananas\""));
    }
    
    @Test
    public void testSplitWithExtraCommas() throws Exception {
        String csv = "2012-12-7,\"Bananas, raw\",\"8.00 medium (7\"\" to 7-7/8\"\" long)\",840.16,,,707.15,0.29,,0.69,6.28,3.15,3.46,188.80,604.16,82.13,,0.94,4.72,47.20,0.74,2.45,254.88,2.55,207.68,3379.52,9.44,9.44,1.42,215.61,24.54,50.79,115.45,3.12,,0.30,0.25,0.43,0.69,1.06,,0.08,0.73,0.26,0.64,0.47,0.08,0.46,10.29,0.26,0.08,0.08,0.44";
        String[] split = Parser.split(csv);
        int i = 0;
        assertThat(split[i++], is("2012-12-7"));
        assertThat(split[i++], is("Bananas, raw"));
        assertThat(split[i++], is("8.00 medium (7\"\" to 7-7/8\"\" long)"));
        
    }

    @Test
    public void testParseMonths() throws Exception {
        String nextMonth = "2012-10-22,Raspberries raw,0.50 cup,31.98,,,52.74,0.02,,0.02,0.37,0.20,0.03,12.92,20.30,16.11,,0.54,4.80,15.38,0.06,0.42,13.53,0.41,17.84,92.87,0.12,0.62,0.26,7.34,4.00,0.63,2.72,0.40,,0.04,0.08,0.15,0.23,0.01,,,,,,,,,0.74,,,,";
        List<String> out = new ArrayList<String>();
        out.add(ServingTest.TITLE);
        out.add(ServingTest.VALUE);
        out.add(nextMonth);
        Collection<Day> days = Parser.parseDays(out);        
        Collection<Month> weeks = Parser.parseMonths(days);
        assertThat(weeks.size(), is(2));
    }
}
