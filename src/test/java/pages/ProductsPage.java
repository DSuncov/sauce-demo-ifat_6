package pages;

import enums.Attribute;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductsPage extends BasePage {

    private static final String ADD_TO_CART = "//*[text()='%s']//ancestor::div" +
            "[@class='inventory_item']//child::*[text()='Add to cart']";

    private final By counter = toByXpath(attributes.get(Attribute.DATA_TEST), "shopping-cart-badge");
    private final By textPage = toByXpath(attributes.get(Attribute.DATA_TEST), "title");
    private final By cartLink = toByXpath(attributes.get(Attribute.DATA_TEST), "shopping-cart-link");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getTextPage() {
        return driver.findElement(textPage).getText();
    }

    public boolean pageIsOpen() {
        return driver.findElement(textPage).isDisplayed();
    }

    public void addToCart(String nameProduct) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(textPage));
        driver.findElement(By.xpath(ADD_TO_CART.formatted(nameProduct))).click();
    }

    public int checkCounterValue() {
        return Integer.parseInt(driver.findElement(counter).getText());
    }

    public void switchToCart() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(textPage));
        driver.findElement(cartLink).click();
    }
}
