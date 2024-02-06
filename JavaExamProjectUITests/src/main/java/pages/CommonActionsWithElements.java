package pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.Logger;

import java.time.Duration;

public class CommonActionsWithElements {

    protected WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());
    protected WebDriverWait webDriverWaitShort, webDriverWaitLong;

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        webDriverWaitShort = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriverWaitLong = new WebDriverWait(webDriver, Duration.ofSeconds(15));
    }

    protected void waitUntilPageIsLoaded() {
        try {
            webDriverWaitLong.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//div[@class='loader']")));
            webDriverWaitLong.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//div[@class='preloader done']")));
        } catch (Exception e) {
            logger.error("Page was not loaded. " + e.getMessage());
            Assert.fail("Page was not loaded. " + e.getMessage());
        }
    }

    protected void waitUntilElementIsVisible(WebElement element) {
        try {
            webDriverWaitLong.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            logger.error("Element is not visible. " + e.getMessage());
            Assert.fail("Element is not visible. " + e.getMessage());
        }
    }

    protected void waitUntilElementIsClickable(WebElement element) {
        try {
            webDriverWaitLong.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            logger.error("Element is not clickable. " + e.getMessage());
            Assert.fail("Element is not clickable. " + e.getMessage());
        }
    }

    public static void waitABit(Integer second){
        try {
            Thread.sleep(second * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void enterTextIntoInput(WebElement input, String text) {
        try {
            input.clear();
            input.sendKeys(text);
            logger.info(text + " was inputted into field: " + getElementName(input));
        } catch (Exception e) {
            logger.error("Can not work with element. " + e.getMessage());
            Assert.fail("Can not work with element. " + e.getMessage());
        }
    }

    private String getElementName(WebElement webElement) {
        try {
            return webElement.getAccessibleName();
        } catch (Exception e) {
            return "";
        }
    }

    protected void clickOnElement(WebElement element) {
        try {
            String elementName = getElementName(element);
            element.click();
            logger.info(elementName + " Element was clicked. ");
        } catch (Exception e) {
            logger.error("Can not work with element. " + e.getMessage());
            Assert.fail("Can not work with element. " + e.getMessage());
        }
    }

    protected void clickOnElement(String locator) {
        try {
            webDriverWaitLong.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
            clickOnElement(webDriver.findElement(By.xpath(locator)));
        } catch (Exception e) {
            logger.error("Can not work with locator. " + e.getMessage());
            Assert.fail("Can not work with locator. " + e.getMessage());
        }
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            waitUntilElementIsVisible(element);
            boolean state = element.isDisplayed();
            String elementText = element.getText();
            String logMessage;
            if (!elementText.isEmpty()) {
                logMessage = "Element '" + elementText + "' is displayed -> " + state;
            } else {
                logMessage = "Element '" + getElementName(element) + "' is displayed -> " + state;
            }
            logger.info(logMessage);
            return state;
        } catch (Exception e) {
            logger.error("Can not work with element." + e.getMessage());
            Assert.fail("Can not work with element. " + e.getMessage());
            return false;
        }
    }

    public boolean isElementNotVisible(WebElement webElement, String elementName) {
        try {
            boolean state = !webElement.isDisplayed();
            logger.info(elementName + " is displayed -> " + state);
            return state;
        } catch (Exception e) {
            logger.info(elementName + " is not displayed");
            return false;
        }
    }
}

