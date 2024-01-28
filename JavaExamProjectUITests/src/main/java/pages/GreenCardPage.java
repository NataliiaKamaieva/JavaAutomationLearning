package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GreenCardPage extends ParentPage {

    final String greenCardUrl = "https://ukrposhta-greencard.ewa.ua/";

    @FindBy(xpath = "//div[@class='form-group__content']//ul[@class='select__list']//li[@class='select__option']")
    private WebElement dropdownOption;

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
    private WebElement buyButton;

    private String dropDownOptionLocator = ".//li[@role='option' and @class='select__option' and contains(text(), '%s')]";

    private String getElementName(WebElement webElement) {
        try {
            return webElement.getAccessibleName();
        } catch (Exception e) {
            return "";
        }
    }

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

    public GreenCardPage selectTransportType(String optionText) {
        selectDropdownOption(dropdownTransportType, optionText);
        return this;
    }

    public GreenCardPage selectTerritory(String optionText) {
        selectDropdownOption(dropdownTerritory, optionText);
        return this;
    }

    public void selectPeriod(String optionText) {
        selectDropdownOption(dropdownPeriod, optionText);
    }

    public GreenCardPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openGreenCardPage() {
        openPage(greenCardUrl);
    }

    public GreenCardPage clickOnButtonCalculate() {
        clickOnElement(buttonCalculate);
        return this;
    }

    private String constructHeaderResultXPath(String expectedText) {
        return String.format("//h2/span[contains(text(), '%s')]", expectedText);
    }

    public void checkHeaderResultOfCalculation(String typeOfVehicle, String territory, String period) {
        String expectedText = String.format("%s / %s / %s", typeOfVehicle, territory, period);

        String xpath = constructHeaderResultXPath(expectedText);

        WebElement headerResultOfCalculation = webDriver.findElement(By.xpath(xpath));

        Assert.assertEquals("Header result text does not match", expectedText, headerResultOfCalculation.getText());
    }

    public GreenCardPage isButtonChangeParametersVisible() {
        Assert.assertTrue("Button - Change Parameters is not visible", isElementDisplayed(buttonChangeParameters));
        return this;
    }

    public GreenCardPage isBuyButtonVisible() {
        Assert.assertTrue("Button - Buy is not visible", isElementDisplayed(buyButton));
        return this;
    }
}
