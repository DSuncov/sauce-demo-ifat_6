package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends BasePage {

    private final By inventoryItemNaneLocator = By.cssSelector(".inventory_item_name");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getProductsName() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inventoryItemNaneLocator));
        return driver.findElements(inventoryItemNaneLocator).stream()
                .map(WebElement::getText)
                .toList();
    }
}
