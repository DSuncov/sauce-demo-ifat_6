package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static enums.Attribute.DATA_TEST;

public class CartPage extends BasePage {

    private final By inventoryItemNaneLocator = toByCssSelector(attributes.get(DATA_TEST), "inventory-item-name");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Получение списка товаров в корзине.")
    public List<String> getProductsName() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inventoryItemNaneLocator));
        return driver.findElements(inventoryItemNaneLocator).stream()
                .map(WebElement::getText)
                .toList();
    }
}
