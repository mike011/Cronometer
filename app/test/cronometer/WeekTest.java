package cronometer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;

public class WeekTest {

    @Test
    public void testGet() throws Exception {
        Week w = new Week("2012-9-10");
        assertThat(w.getYear(), is(2012));
        assertThat(w.getWeek(), is(37));
    }

    @Test
    public void testCompareSame() throws Exception {
        Week w = new Week("2012-9-10");
        Week w2 = new Week("2012-9-10");
        assertThat(w.compareTo(w2), is(0));
    }

    @Test
    public void testCompareDifferentWeeksYearDiffersNewer() throws Exception {
        Week w = new Week("2012-9-10");
        Week w2 = new Week("2013-9-10");
        assertThat(w.compareTo(w2), is(-1));
    }

    @Test
    public void testCompareDifferentWeeksYearDiffersOlder() throws Exception {
        Week w = new Week("2012-9-10");
        Week w2 = new Week("2011-9-10");
        assertThat(w.compareTo(w2), is(1));
    }

    @Test
    public void testCompareDifferentWeeksDiffersNewer() throws Exception {
        Week w = new Week("2012-9-10");
        Week w2 = new Week("2012-11-10");
        assertThat(w.compareTo(w2), is(-1));
    }

    @Test
    public void testCompareDifferentWeeksDiffersOlder() throws Exception {
        Week w = new Week("2012-9-10");
        Week w2 = new Week("2012-7-10");
        assertThat(w.compareTo(w2), is(1));
    }

    @Test
    public void testToString() {
        Week w = new Week("2012-9-10");
        assertThat(w.toString(), is("2012-37"));
    }

    @Test
    public void testAdd() throws Exception {
        Week w = getWeek();
        Day d2 = new Day("2012-9-10");
        d2.add(ServingTest.getTitles(), ServingTest.getValues());
        w.add(d2);
        assertThat(w.getEnergy(), is(234));
    }

    @Test
    public void testGetAverageEnergy() throws Exception {
        assertThat(getWeek().getAverageEnergy(), is(117));
    }

    private Week getWeek() {
        Week w = new Week("2012-9-10");
        Day d = new Day("2012-9-10");
        d.add(ServingTest.getTitles(), ServingTest.getValues());
        w.add(d);
        return w;
    }

    @Ignore
    @Test
    public void testGetRatio() throws Exception {
        assertThat(getWeek().getRatio(), is("87|5|8"));
    }

    @Test
    public void getTotalCalories() throws Exception {
        assertThat(getWeek().getTotalCalories(), is(132.15));
    }

    @Test
    public void getFatCalories() throws Exception {
        assertThat(getWeek().getFatCalories(), is(6.75));
    }

    @Test
    public void getCarbCalories() throws Exception {
        assertThat(getWeek().getCarbCalories(), is(114.48));
    }

    @Test
    public void getProteinCalories() throws Exception {
        assertThat(getWeek().getProteinCalories(), is(10.92));
    }
}
