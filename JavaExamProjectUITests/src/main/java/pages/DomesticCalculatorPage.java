package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DomesticCalculatorPage extends ParentPage {
    public DomesticCalculatorPage(WebDriver webDriver) {
        super(webDriver);
    }

    final String domesticCalculatorUrl = "https://calc.ukrposhta.ua/domestic-calculator";

    @FindBy(xpath = "//button[@class='submitButton' and text()='Дякую, ознайомлений (-а)']")
    private WebElement buttonAgree;

    @FindBy(xpath = "//input[@id='fromWhere']")
    private WebElement inputFromWhere;

    @FindBy(xpath = "//input[@id='toWhere']")
    private WebElement inputToWhere;

    @FindBy(xpath = "//div[@role='menuitem']//button")
    private WebElement dropdownOption;

    @FindBy(xpath = "//button[@id='goTopackage']")
    private WebElement buttonShipmentPackage;

    @FindBy(xpath = "//button[@id='goTodocs']")
    private WebElement buttonShipmentDocs;

    @FindBy(xpath = "//button[@id='goTolatter']")
    private WebElement buttonShipmentLatter;

    @FindBy(xpath = "//input[@id='weight']")
    private WebElement inputWeight;

    @FindBy(xpath = "//input[@id='maxSide']")
    private WebElement inputMaxSide;

    @FindBy(xpath = "//input[@id='declaredValue']")
    private WebElement inputDeclaredValue;

    @FindBy(xpath = "//label[@for='courier']")
    private WebElement checkboxCourier;

    @FindBy(xpath = "//label[@for='courierDelivery']")
    private WebElement checkboxCourierDelivery;

    @FindBy(xpath = "//label[@for='deliverySms']")
    private WebElement checkboxDeliverySms;

    @FindBy(xpath = "//label[@for='withDeliveryNotification']")
    private WebElement checkboxWithDeliveryNotification;

    @FindBy(xpath = "//label[@for='documentBack']")
    private WebElement checkboxDocumentBack;

    @FindBy(xpath = "//button[@id='swapButton' and @class='swapButton']")
    private WebElement buttonSwap;

    @FindBy(xpath = "//div[@class='row responseBlock']")
    private WebElement responseBlock;

    @FindBy(xpath = "//button[@class='submitButton']")
    private WebElement buttonCalculate;

    @FindBy(xpath = "//a[contains(@class, 'serviceName') and @href='https://www.ukrposhta.ua/ua/standart']")
    private WebElement serviceUkrposhtaStandart;

    @FindBy(xpath = "//a[contains(@class, 'serviceName') and contains(@href, 'https://www.ukrposhta.ua/ua/ekspres')]")
    private WebElement serviceUkrposhtaEkspres;

    @FindBy(xpath = "//p[contains(@class, 'serviceName') and contains(text(), 'Документи')]")
    private WebElement serviceUkrposhtaDocs;


    private String dropDownOptionLocator = "//div[@role='menuitem']//button[contains(text(), '%s')]";

    public void openDomesticPage() {
        openPage(domesticCalculatorUrl);
        waitUntilPageIsLoaded();
    }

    @Step
    public DomesticCalculatorPage selectCityFrom(String inputText, String optionText) {
        enterTextIntoInput(inputFromWhere, inputText);
        clickOnElement(String.format(dropDownOptionLocator, optionText));
        return this;
    }

    @Step
    public DomesticCalculatorPage selectCityTo(String inputText, String optionText) {
        enterTextIntoInput(inputToWhere, inputText);
        clickOnElement(String.format(dropDownOptionLocator, optionText));
        return this;
    }

    @Step
    public void clickOnButtonAgree() {
        webDriverWaitLong.until(ExpectedConditions.visibilityOf(buttonAgree));
        clickOnElement(buttonAgree);
    }

    @Step
    public void clickOnButtonShipmentPackage() {
        clickOnElement(buttonShipmentPackage);
    }

    @Step
    public void clickOnButtonShipmentDocs() {
        clickOnElement(buttonShipmentDocs);
    }

    @Step
    public void enterWeight(String weight) {
        enterTextIntoInput(inputWeight, weight);
    }

    @Step
    public void enterMaxSide(String maxSide) {
        enterTextIntoInput(inputMaxSide, maxSide);
    }

    @Step
    public void enterDeclaredValue(String declaredValue) {
        enterTextIntoInput(inputDeclaredValue, declaredValue);
    }

    @Step
    public DomesticCalculatorPage selectAllCheckboxesForTypeGoToPackage() {
        checkboxCourier.click();
        checkboxCourierDelivery.click();
        checkboxDeliverySms.click();
        checkboxWithDeliveryNotification.click();
        checkboxDocumentBack.click();
        return this;
    }

    private void scrollToElement(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;
        executor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    @Step
    public void clickOnCalculateButton() {
        scrollToElement(buttonCalculate);
        waitUntilElementIsVisible(buttonCalculate);
        waitUntilElementIsClickable(buttonCalculate);
        clickOnElement(buttonCalculate);
    }

    @Step
    public void checkIsServiceUkrposhtaEkspresIsVisible() {
        isElementDisplayed(serviceUkrposhtaEkspres);
    }

    @Step
    public void checkIsServiceUkrposhtaStandartIsVisible() {
        isElementDisplayed(serviceUkrposhtaStandart);
    }

    @Step
    public void checkIsServiceUkrposhtaDocsIsVisible() {
        isElementDisplayed(serviceUkrposhtaDocs);
    }

    @Step
    public void checkIsServiceUkrposhtaStandartNotVisible() {
        isElementNotVisible(serviceUkrposhtaStandart, "Service Ukrposhta Standart");
    }

    @Step
    public void checkIsServiceUkrposhtaEkspresNotVisible() {
        isElementNotVisible(serviceUkrposhtaEkspres, "Service Ukrposhta Ekspres");
    }

    @Step
    public void checkIsServiceUkrposhtaDocsNotVisible() {
        isElementNotVisible(serviceUkrposhtaDocs, "Service Ukrposhta Docs");
    }

    @Step
    public void clickOnButtonSwap() {
        clickOnElement(buttonSwap);
    }

    @Step
    public void checkIsCityFromSelected(String city) {
        Assert.assertEquals("City is not selected or different", city, inputFromWhere.getAttribute("value"));
    }

    @Step
    public void checkIsCityToSelected(String city) {
        Assert.assertEquals("City is not selected or different", city, inputToWhere.getAttribute("value"));
    }
}
