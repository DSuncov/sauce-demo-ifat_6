package pages;

import enums.Attribute;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {

    private static final String ADD_TO_CART = "//*[text()='%s']//ancestor::div" +
            "[@class='inventory_item']//child::*[text()='Add to cart']";

    private final By counter = toBy(attributes.get(Attribute.DATA_TEST), "shopping-cart-badge");
    private final By textPage = toBy(attributes.get(Attribute.DATA_TEST), "title");
    private final By cartLink = toBy(attributes.get(Attribute.DATA_TEST), "shopping-cart-link");

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
        driver.findElement(By.xpath(ADD_TO_CART.formatted(nameProduct))).click();
    }

    public int checkCounterValue() {
        return Integer.parseInt(driver.findElement(counter).getText());
    }

    public void switchToCart() {
        driver.findElement(cartLink).click();
    }
}
