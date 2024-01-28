package deliveryCostCalculator;

import baseTest.BaseTest;
import jdk.jfr.Description;
import org.junit.Test;

public class InternationalCalculatorTests extends BaseTest {

    @Test
    @Description("Check international delivery cost calculation")
    public void calculateInternationalDeliveryCostTest() {
        pageProvider.getInternationalCalculatorPage()
                .openInternationalCalculatorPage();
    }


}
