package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class ParentPage extends CommonActionsWithElements {
    //final String baseUrl = "https://www.ukrposhta.ua/ua/";

    final String postIndexUrl = "https://index.ukrposhta.ua/find-post-index";
    final String postAddressUrl = "https://index.ukrposhta.ua/find-address";
    final String domesticCalculatorUrl = "https://calc.ukrposhta.ua/domestic-calculator";
    final String internationalCalculatorUrl = "https://calc.ukrposhta.ua/international-calculator";

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openPage(String page) {
        try {
            webDriver.get(page);
            logger.info("The Page '" + page + " 'was open");
        } catch (Exception e) {
            logger.error("Can not open '" + page + "' page");
            Assert.fail("Can not open '" + page + " 'page");
        }
    }

}
