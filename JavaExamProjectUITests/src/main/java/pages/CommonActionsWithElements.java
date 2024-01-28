package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.Logger;

import java.time.Duration;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());
    protected WebDriverWait webDriverWait10, webDriverWait15;

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);//initialization of elements on page which described in this class
        webDriverWait10 = new WebDriverWait(webDriver,Duration.ofSeconds(10));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(15));
    }

    protected void enterTextIntoInput(WebElement input, String text) {
        try {
            input.clear();
            input.sendKeys(text);
            logger.info(text + " was inputted into input " + getElementName(input));
        } catch (Exception e) {
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
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
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(element));
            String elementName = getElementName(element);
            element.click();
            logger.info(elementName + "Element was clicked");
        } catch (Exception e) {
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
    }

    protected void clickOnElement(String locator) {
        try {
            clickOnElement(webDriver.findElement(By.xpath(locator)));
        } catch (Exception e) {
            logger.error("Can not work with locator");
            Assert.fail("Can not work with locator");
        }
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
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
            logger.error("Can not work with element");
            return false;
        }
    }

    protected boolean isElementDisplayed(WebElement element, String elementName) {
        try {
            boolean state = element.isDisplayed();
            logger.info("Element '" + elementName + "' is displayed -> " + state);
            return state;
        } catch (Exception e) {
            logger.error("Can not work with element");
            return false;
        }
    }

    //select Text in dropdown

    protected void selectTextInDropdown(WebElement dropDown, String text) {
        try {
            Select select = new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info(text + " was selected in dropdown" + getElementName(dropDown));
        } catch (Exception e) {
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
    }

    //select Value in dropdown
    protected void selectValueInDropdown(WebElement dropDown, String value) {
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info(value + " was selected in dropdown" + getElementName(dropDown));
        } catch (Exception e) {
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
    }

    protected void isElementVisible(WebElement webElement) {
        Assert.assertTrue("Element is not visible", isElementDisplayed(webElement));
    }

    public boolean checkIsElementNotVisible(WebElement webElement, String elementName) {
        try {
            boolean state = webElement.isDisplayed();
            logger.info(elementName + " is displayed -> " + state);
            return state;
        } catch (Exception e) {
            logger.info(elementName + " is not displayed");
            return false;
        }
    }

    public boolean checkIsValidationMessageVisible(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            logger.info(webElement.getText() + " is displayed -> " + state);
            return state;
        } catch (Exception e) {
            logger.info(webElement.getText() + " is not displayed");
            return false;
        }
    }

    //check text in element
    protected void checkTextInElement(WebElement element, String expectedText) {
        try {
            String textFromElement = element.getText();
            Assert.assertEquals("Text in element not matched", expectedText, textFromElement);
        } catch (Exception e) {
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
    }

    protected void selectCheckbox(WebElement checkbox, String elementName) {
        try {
            if (!checkbox.isSelected()) {
                checkbox.click();
                logger.info(elementName + " was selected");
            } else {
                logger.info(elementName + " is already selected.");
            }
        } catch (Exception e) {
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
    }

    protected void unselectCheckbox(WebElement checkbox, String elementName) {
        try {
            if (checkbox.isSelected()) {
                checkbox.click();
                logger.info(elementName + " was unselected");
            } else {
                logger.info(elementName + " is already unselected.");
            }
        } catch (Exception e) {
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
    }

    protected void setCheckboxState(WebElement checkbox, String elementName, String state) {
        try {
            if (state.toLowerCase().equals("check")) {
                selectCheckbox(checkbox, elementName);
            } else if (state.toLowerCase().equals("uncheck")) {
                unselectCheckbox(checkbox, elementName);
            }
        } catch (Exception e) {
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
    }

    public void openNewTabInBrowser() {
        try {
            ((JavascriptExecutor) webDriver).executeScript("window.open()");
            logger.info("New tab was opened");
        } catch (Exception e) {
            logger.error("Can not open new tab");
            Assert.fail("Can not open new tab");
        }
    }

    public void switchToTabInBrowser(int tabNumber) {
        try {
            webDriver.switchTo().window(webDriver.getWindowHandles().toArray()[tabNumber].toString());
            logger.info("Switched to new tab");
        } catch (Exception e) {
            logger.error("Can not switch to new tab");
            Assert.fail("Can not switch to new tab");
        }
    }

    public void closeTab(int tabNumber) {
        try {
            webDriver.switchTo().window(webDriver.getWindowHandles().toArray()[tabNumber].toString());
            webDriver.close();
            logger.info("New tab was closed and switched to main tab");
        } catch (Exception e) {
            logger.error("Can not close new tab and switch to main tab");
            Assert.fail("Can not close new tab and switch to main tab");
        }
    }

    public void refreshPage() {
        try {
            webDriver.navigate().refresh();
            logger.info("Page was refreshed");
        } catch (Exception e) {
            logger.error("Can not refresh page");
            Assert.fail("Can not refresh page");
        }
    }
}

