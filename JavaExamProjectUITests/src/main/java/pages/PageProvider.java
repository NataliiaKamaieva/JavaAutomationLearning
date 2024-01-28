package pages;

import org.openqa.selenium.WebDriver;

public class PageProvider {
    private WebDriver webDriver;
    public PageProvider(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public InternationalCalculatorPage getInternationalCalculatorPage() {
        return new InternationalCalculatorPage(webDriver);
    }

    public GreenCardPage getGreenCardPage() {
        return new GreenCardPage(webDriver);
    }

}
