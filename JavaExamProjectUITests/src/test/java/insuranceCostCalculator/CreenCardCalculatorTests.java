package insuranceCostCalculator;

import baseTest.BaseTest;
import jdk.jfr.Description;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class CreenCardCalculatorTests extends BaseTest {

    @Test
    @Description("Check Green Card Calculation for Various Scenarios with all Valid Parameters.")
    @Parameters(method = "parametersForGreenCardCalculationTest")
    public void greenCardCalculationWithAllValidParametersTest(String typeOfVehicle, String territory, String period) {
        pageProvider.getGreenCardPage().openGreenCardPage();
        pageProvider.getGreenCardPage().selectTransportType(typeOfVehicle);
        pageProvider.getGreenCardPage().selectTerritory(territory);
        pageProvider.getGreenCardPage().selectPeriod(period);
        pageProvider.getGreenCardPage().clickOnButtonCalculate();
        pageProvider.getGreenCardPage().checkHeaderResultOfCalculation(typeOfVehicle, territory, period);
        pageProvider.getGreenCardPage().isButtonChangeParametersVisible();
        pageProvider.getGreenCardPage().isBuyButtonVisible();
    }

    public Object[][] parametersForGreenCardCalculationTest() {
        return new Object[][]{
                {"C - Вантажні автомобілі", "Європа", "15 днів"},
                {"B - Мотоцикли", "Молдова, Азербайджан", "10 місяців"},
                {"A - Легкові автомобілі", "Європа", "Рік"}
        };
    }
}
