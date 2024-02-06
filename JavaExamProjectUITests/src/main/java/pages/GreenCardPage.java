package pages;

import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class GreenCardPage extends ParentPage {
    public GreenCardPage(WebDriver webDriver) {
        super(webDriver);
    }

    final String greenCardPageUrl = "https://ukrposhta-greencard.ewa.ua/";

    @FindBy(xpath = "//div[@class='form-group' and p[@class='form-group__header' and text()='Тип транспортного засобу']]")
    private WebElement dropdownTransportType;

    @FindBy(xpath = "//div[@class='form-group' and p[@class='form-group__header' and text()='Територія покриття']]")
    private WebElement dropdownTerritory;

    @FindBy(xpath = "//div[@class='form-group' and p[@class='form-group__header' and text()='Період покриття']]")
    private WebElement dropdownPeriod;

    @FindBy(xpath = "//h2/button[contains(text(), 'змінити параметри')]")
    private WebElement buttonChangeParameters;

    @FindBy(xpath = "//div[@class='tariffs-calculator__act']//button[@class='button button_a']")
    private WebElement buttonCalculate;

    @FindBy(xpath = "//div[@class='products-list-item__buy']/button[@class='button button_a']")
    private WebElement buttonBuy;

    @FindBy(xpath = "//p[@class='form-group__error']")
    private List<WebElement> listErrorsMessages;

    private String dropDownOptionLocator = ".//li[@role='option' and @class='select__option' and contains(text(), '%s')]";
    private String listErrorsMessagesLocator = "//p[@class='form-group__error']";

    @Step
    public void openGreenCardPage() {
        openPage(greenCardPageUrl);
    }

    private String getElementName(WebElement webElement) {
        try {
            return webElement.getAccessibleName();
        } catch (Exception e) {
            return "";
        }
    }

    @Step
    public void selectDropdownOption(WebElement dropdown, String optionText) {
        try {
            dropdown.click();
            WebElement option = dropdown.findElement(By.xpath(String.format(dropDownOptionLocator, optionText)));
            option.click();
            logger.info(optionText + " was selected from dropdown " + getElementName(dropdown));
        } catch (NoSuchElementException e) {
            logger.error("Dropdown option not found: " + optionText);
            Assert.fail("Unable to select dropdown option: " + optionText);
        }
    }

    @Step
    public GreenCardPage selectTransportType(String optionText) {
        selectDropdownOption(dropdownTransportType, optionText);
        return this;
    }

    @Step
    public GreenCardPage selectTerritory(String optionText) {
        selectDropdownOption(dropdownTerritory, optionText);
        return this;
    }

    @Step
    public void selectPeriod(String optionText) {
        selectDropdownOption(dropdownPeriod, optionText);
    }


    @Step
    public GreenCardPage clickOnButtonCalculate() {
        clickOnElement(buttonCalculate);
        return this;
    }

    private String constructHeaderResultXPath(String expectedText) {
        return String.format("//h2/span[contains(text(), '%s')]", expectedText);
    }

    @Step
    public void checkHeaderResultOfCalculation(String typeOfVehicle, String territory, String period) {
        String expectedText = String.format("%s / %s / %s", typeOfVehicle, territory, period);

        String xpath = constructHeaderResultXPath(expectedText);

        WebElement headerResultOfCalculation = webDriver.findElement(By.xpath(xpath));

        Assert.assertEquals("Header result text does not match", expectedText, headerResultOfCalculation.getText());
    }

    @Step
    public GreenCardPage checkIsButtonChangeParametersVisible() {
        Assert.assertTrue("Button - Change Parameters is not visible", isElementDisplayed(buttonChangeParameters));
        return this;
    }
    @Step
    public GreenCardPage checkIsBuyButtonVisible() {
        Assert.assertTrue("Button - Buy is not visible", isElementDisplayed(buttonBuy));
        return this;
    }
    @Step
    public GreenCardPage checkErrorsMessages(String message) {
        String[] expectedErrors = message.split(";");

        webDriverWaitLong.until(ExpectedConditions.numberOfElementsToBe(
                By.xpath(listErrorsMessagesLocator), expectedErrors.length));

        waitABit(1);
        Assert.assertEquals("Number of messages", expectedErrors.length, listErrorsMessages.size());

        ArrayList<String> actualErrors = new ArrayList<>();
        for (WebElement element : listErrorsMessages) {
            actualErrors.add(element.getText());
        }

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrors.length; i++) {
            softAssertions.assertThat(expectedErrors[i])
                    .as("Error " + i)
                    .isIn(actualErrors);
        }
        softAssertions.assertAll();
        return this;
    }
}
