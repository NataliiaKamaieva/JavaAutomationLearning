package deliveryCostCalculator;

import baseTest.BaseTest;
import jdk.jfr.Description;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class DomesticCalculatorTests extends BaseTest {

    @Test
    @Description("Check domestic delivery cost calculation for goToPackage type of delivery")
    @Parameters(method = "paramsDomesticCalculationGoToPackageWithAllValidParametersTest")
    public void domesticCalculationGoToPackageWithAllValidParametersTest(
            String inputCountryFrom,
            String countryFrom,
            String inputCountryTo,
            String countryTo,
            String weight,
            String maxSide,
            String declaredValue) {
        pageProvider.getDomesticCalculatorPage().openDomesticPage();
        pageProvider.getDomesticCalculatorPage().clickOnButtonAgree();
        pageProvider.getDomesticCalculatorPage().selectCityFrom(inputCountryFrom, countryFrom);
        pageProvider.getDomesticCalculatorPage().selectCityTo(inputCountryTo, countryTo);
        pageProvider.getDomesticCalculatorPage().clickOnButtonShipmentPackage();
        pageProvider.getDomesticCalculatorPage().enterWeight(weight);
        pageProvider.getDomesticCalculatorPage().enterMaxSide(maxSide);
        pageProvider.getDomesticCalculatorPage().enterDeclaredValue(declaredValue);
        pageProvider.getDomesticCalculatorPage().selectAllCheckboxesForTypeGoToPackage();
        pageProvider.getDomesticCalculatorPage().clickOnCalculateButton();
        pageProvider.getDomesticCalculatorPage().checkIsServiceUkrposhtaEkspresIsVisible();
        pageProvider.getDomesticCalculatorPage().checkIsServiceUkrposhtaStandartIsVisible();
        pageProvider.getDomesticCalculatorPage().checkIsServiceUkrposhtaDocsNotVisible();
    }

    public Object[][] paramsDomesticCalculationGoToPackageWithAllValidParametersTest() {
        return new Object[][]{
                {"Черніг", "Чернігів", "Київ", "Київ", "0.5", "40", "1000"},
                {"Сум", "Суми", "Чернівц", "Чернівці", "1", "55.5", "5592"}
        };
    }

    @Test
    @Description("Check domestic delivery cost calculation for goTodocs type of delivery")
    @Parameters(method = "parametersForDomesticCalculationGoToDocsWithoutAdditionalServiceTest")
    public void domesticCalculationGoToDocsWithoutAdditionalServiceTest(
            String inputCountryFrom,
            String countryFrom,
            String inputCountryTo,
            String countryTo) {
        pageProvider.getDomesticCalculatorPage().openDomesticPage();
        pageProvider.getDomesticCalculatorPage().clickOnButtonAgree();
        pageProvider.getDomesticCalculatorPage().selectCityFrom(inputCountryFrom, countryFrom);
        pageProvider.getDomesticCalculatorPage().selectCityTo(inputCountryTo, countryTo);
        pageProvider.getDomesticCalculatorPage().clickOnButtonShipmentDocs();
        pageProvider.getDomesticCalculatorPage().clickOnCalculateButton();
        pageProvider.getDomesticCalculatorPage().checkIsServiceUkrposhtaDocsIsVisible();
        pageProvider.getDomesticCalculatorPage().checkIsServiceUkrposhtaStandartNotVisible();
        pageProvider.getDomesticCalculatorPage().checkIsServiceUkrposhtaEkspresNotVisible();
    }

    public Object[][] parametersForDomesticCalculationGoToDocsWithoutAdditionalServiceTest() {
        return new Object[][]{
                {"Прилуки", "Прилуки", "Чернігів", "Чернігів"},
                {"Черніг", "Чернігів", "Київ", "Київ"},
                {"Сум", "Суми", "Чернівц", "Чернівці"}
        };
    }

    @Test
    @Description("Check swap button")
    public void swapButtonTest() {
        pageProvider.getDomesticCalculatorPage().openDomesticPage();
        pageProvider.getDomesticCalculatorPage().clickOnButtonAgree();
        pageProvider.getDomesticCalculatorPage().selectCityFrom("Чернігів", "Чернігів");
        pageProvider.getDomesticCalculatorPage().selectCityTo("Київ", "Київ");
        pageProvider.getDomesticCalculatorPage().clickOnButtonSwap();
        pageProvider.getDomesticCalculatorPage().checkIsCityFromSelected("Київ");
        pageProvider.getDomesticCalculatorPage().checkIsCityToSelected("Чернігів");
    }
}
