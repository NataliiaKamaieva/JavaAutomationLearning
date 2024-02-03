package pages;

import org.openqa.selenium.WebDriver;

public class PageProvider {
    private WebDriver webDriver;
    public PageProvider(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public GreenCardPage getGreenCardPage() {
        return new GreenCardPage(webDriver);
    }
    public DomesticCalculatorPage getDomesticCalculatorPage() {
        return new DomesticCalculatorPage(webDriver);
    }
}
