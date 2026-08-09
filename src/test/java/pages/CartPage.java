package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static enums.Attribute.DATA_TEST;

public class CartPage extends BasePage {

    private final By checkoutButton = toByCssSelector(attributes.get(DATA_TEST), "checkout");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Переход на страницу заполнения информации покупателя.")
    public void switchToCheckout() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutButton)).click();
    }
}
