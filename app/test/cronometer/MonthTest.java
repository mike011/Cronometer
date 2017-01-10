package cronometer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class MonthTest {

    @Test
    public void testToString() throws Exception {
        Month m = new Month("2012-12-3");
        assertThat(m.toString(), is("2012-12"));
    }
    
    @Test
    public void testToStringLessThanTen() throws Exception {
        Month m = new Month("2012-3-3");
        assertThat(m.toString(), is("2012-03"));
    }
    
    @Test
    public void testDaysInMonth() throws Exception {
        Month m = new Month("2012-9-3");
        Day d2 = new Day("2012-9-10");
        d2.add(ServingTest.getTitles(), ServingTest.getValues());
        m.add(d2);
        assertThat(m.getEnergy(), is(117));
    }

}
