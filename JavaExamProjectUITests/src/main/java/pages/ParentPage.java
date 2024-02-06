package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class ParentPage extends CommonActionsWithElements {

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
