package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {

    private final By textPageLocator = By.xpath("//span[@data-test='title']");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getTextPage() {
        return driver.findElement(textPageLocator).getText();
    }
}
