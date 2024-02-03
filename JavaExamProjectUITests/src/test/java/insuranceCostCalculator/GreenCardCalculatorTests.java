package insuranceCostCalculator;

import baseTest.BaseTest;
import jdk.jfr.Description;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class GreenCardCalculatorTests extends BaseTest {
    final String ERROR_MESSAGE_PERIOD = "Виберіть період покриття";
    final String ERROR_MESSAGE_TERRITORY = "Виберіть територію покриття";
    final String SEMICOLON = ";";

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

    @Test
    @Description("Check validation errors on the Green Card Page")
    @Parameters(method = "parametersForValidationMessagesTests")
    public void validationErrorsTest(String territory, String period, String errorMessage){
        pageProvider.getGreenCardPage().openGreenCardPage();
        if (!territory.isEmpty()) {
            pageProvider.getGreenCardPage().selectTerritory(territory);
        }
        if (!period.isEmpty()) {
            pageProvider.getGreenCardPage().selectPeriod(period);
        }
        pageProvider.getGreenCardPage().clickOnButtonCalculate();
        pageProvider.getGreenCardPage().checkErrorsMessages(errorMessage);
    }

    public Object[][] parametersForValidationMessagesTests() {
        return new Object[][]{
                {"", "", ERROR_MESSAGE_TERRITORY + SEMICOLON + ERROR_MESSAGE_PERIOD},
                {"Європа", "", ERROR_MESSAGE_PERIOD},
                {"", "10 місяців", ERROR_MESSAGE_TERRITORY},
        };
    }
}
