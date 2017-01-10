package cronometer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class DayTest {
    
    private Day d = new Day("today");
    
    @Before
    public void setup() {
        d.add(ServingTest.getTitles(), ServingTest.getValues());
        d.add(ServingTest.getTitles(), ServingTest.getValues());
    }

    @Test
    public void testGetEnergy() throws Exception {
        assertThat(d.getEnergy(), is(234D));
    }

    @Test
    public void testGetTotalCalories() throws Exception {
        assertThat(d.getTotalCalories(), is(264.3D));
    }

    @Test
    public void testGetFatCalories() throws Exception {
        assertThat(d.getFatCalories(), is(13.5D));
    }

    @Test
    public void testGetCarbCalories() throws Exception {
        assertThat(d.getCarbCalories(), is(228.96D));
    }

    @Test
    public void testGetProteinCalories() throws Exception {
        assertThat(d.getProteinCalories(), is(21.84D));
    }
}
