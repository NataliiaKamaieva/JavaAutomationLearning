package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class InternationalCalculatorPage extends ParentPage {

    public InternationalCalculatorPage(WebDriver webDriver) {
        super(webDriver);
    }
    public void openInternationalCalculatorPage(){
        openPage(internationalCalculatorUrl);
    }
}
