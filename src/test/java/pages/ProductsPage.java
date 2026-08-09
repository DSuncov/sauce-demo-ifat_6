package pages;

import enums.Attribute;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductsPage extends BasePage {

    private static final String ADD_TO_CART = "//*[text()='%s']//ancestor::div" +
            "[@class='inventory_item']//child::*[text()='Add to cart']";

    private final By counter = toByCssSelector(attributes.get(Attribute.DATA_TEST), "shopping-cart-badge");
    private final By cartLink = toByCssSelector(attributes.get(Attribute.DATA_TEST), "shopping-cart-link");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Добавление товара в корзину.")
    public void addToCart(String nameProduct) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
        driver.findElement(By.xpath(ADD_TO_CART.formatted(nameProduct))).click();
    }

    @Step("Проверка количества товаров в корзине.")
    public int checkCounterValue() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
        return Integer.parseInt(driver.findElement(counter).getText());
    }

    @Step("Переход на страницу с корзиной товаров.")
    public void switchToCart() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
        driver.findElement(cartLink).click();
    }
}
