package cronometer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ServingTest {

    static final String TITLE = "Day,Food Name,Amount,Energy (kcal),Alcohol (g),Caffeine (mg),Water (g),B1 (Thiamine) (mg),B12 (Cobalamin) (µg),B2 (Riboflavin) (mg),B3 (Niacin) (mg),B5 (Pantothenic Acid) (mg),B6 (Pyridoxine) (mg),Folate (µg),Vitamin A (IU),Vitamin C (mg),Vitamin D (IU),Vitamin E (mg),Vitamin K (µg),Calcium (mg),Copper (mg),Iron (mg),Magnesium (mg),Manganese (mg),Phosphorus (mg),Potassium (mg),Selenium (µg),Sodium (mg),Zinc (mg),Carbs (g),Fiber (g),Starch (g),Sugars (g),Fat (g),Cholesterol (mg),Monounsaturated (g),Omega-3 (g),Omega-6 (g),Polyunsaturated (g),Saturated (g),Trans-Fats (g),Cystine (g),Histidine (g),Isoleucine (g),Leucine (g),Lysine (g),Methionine (g),Phenylalanine (g),Protein (g),Threonine (g),Tryptophan (g),Tyrosine (g),Valine (g)";
    static final String VALUE = "2012-9-10,\"Peaches yellow raw\",\"2.00 medium (2-2/3\"\" dia)\",117,,,266.61,0.07,,0.09,2.42,0.46,0.08,12.00,978.00,19.80,,2.19,7.80,18.00,0.20,0.75,27.00,0.18,60.00,570.00,0.30,,0.51,28.62,4.50,,25.17,0.75,,0.20,0.01,0.25,0.26,0.06,,0.04,0.04,0.05,0.08,0.09,0.03,0.06,2.73,0.05,0.03,0.04,0.07";

    private static final Serving serving = new Serving(getTitles(), getValues());

    static String[] getValues() {
        return Parser.split(VALUE);
    }

    static String[] getTitles() {
        return TITLE.split(",");
    }

    @Test
    public void testGetEnergyIndex() throws Exception {
        assertThat(serving.getEnergyIndex(), is(3));
    }

    @Test
    public void testGetName() throws Exception {
        assertThat(serving.getName(), is("Peaches yellow raw"));
    }
    
    @Test
    public void testGetEnergy() throws Exception {
        assertThat(serving.getEnergy(), is(117.00D));
    }
    
    @Test
    public void testGetEnergyEmpty() throws Exception {
        String value = "2012-9-10,\"Peaches yellow raw\",\"2.00 medium (2-2/3\"\" dia)\",,,,266.61,0.07,,0.09,2.42,0.46,0.08,12.00,978.00,19.80,,2.19,7.80,18.00,0.20,0.75,27.00,0.18,60.00,570.00,0.30,,0.51,28.62,4.50,,25.17,0.75,,0.20,0.01,0.25,0.26,0.06,,0.04,0.04,0.05,0.08,0.09,0.03,0.06,2.73,0.05,0.03,0.04,0.07";
        Serving serving = new Serving(getTitles(),Parser.split(value));
        assertThat(serving.getEnergy(), is(0D));
    }

    @Test
    public void testGetFat() throws Exception {
        assertThat(serving.getFat(), is(0.75D));
    }

    @Test
    public void testGetCarbs() throws Exception {
        assertThat(serving.getCarbs(), is(28.62D));
    }

    @Test
    public void testGetProtein() throws Exception {
        assertThat(serving.getProtein(), is(2.73D));
    }
    
    @Test
    public void testGetFatCalories() throws Exception {
        assertThat(serving.getFatCalories(), is(6.75D));
    }

    @Test
    public void testGetCarbCalories() throws Exception {
        assertThat(serving.getCarbCalories(), is(114.48D));
    }

    @Test
    public void testGetProteinCalories() throws Exception {
        assertThat(serving.getProteinCalories(), is(10.92D));
    }
    
    @Test
    public void testTotalCalories() throws Exception {
        assertThat(serving.getTotalCalories(), is(132.15D));
    }
    
    @Test
    public void testGetFatRatio() throws Exception {
        assertThat(serving.getFatRatio(), is(5.10783200908059D));
    }
    
    @Test
    public void testGetCarbRatio() throws Exception {
        assertThat(serving.getCarbRatio(), is(86.62883087400681D));
    }

    @Test
    public void testGetProteinRatio() throws Exception {
        assertThat(serving.getProteinRatio(), is(8.263337116912599D));
    }
    
    @Test
    public void getRatio() {
        assertThat(serving.getRatio(), is("87|5|8"));
    }
    
    @Test
    public void getExtraQuotes() {
        String csv = "2012-12-7,\"Bananas, raw\",\"8.00 medium (7\"\" to 7-7/8\"\" long)\",840.16,,,707.15,0.29,,0.69,6.28,3.15,3.46,188.80,604.16,82.13,,0.94,4.72,47.20,0.74,2.45,254.88,2.55,207.68,3379.52,9.44,9.44,1.42,215.61,24.54,50.79,115.45,3.12,,0.30,0.25,0.43,0.69,1.06,,0.08,0.73,0.26,0.64,0.47,0.08,0.46,10.29,0.26,0.08,0.08,0.44";
        Serving serving = new Serving(getTitles(), Parser.split(csv));
        assertThat(serving.getName(), is("Bananas, raw"));

    }
}
