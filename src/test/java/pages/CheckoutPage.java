package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static enums.Attribute.DATA_TEST;

public class CheckoutPage extends BasePage {

    private final By continueButton = toByCssSelector(attributes.get(DATA_TEST), "continue");
    private final By finishButton = toByCssSelector(attributes.get(DATA_TEST), "finish");

    private final By completeImg = toByCssSelector(attributes.get(DATA_TEST), "pony-express");
    private final By textCompletedOrder = toByCssSelector(attributes.get(DATA_TEST), "complete-header");

    private final By enterFirstName = toByCssSelector(attributes.get(DATA_TEST), "firstName");
    private final By enterLastName = toByCssSelector(attributes.get(DATA_TEST), "lastName");
    private final By enterZipOrPostalCode = toByCssSelector(attributes.get(DATA_TEST), "postalCode");

    private final By itemTotalPrice = toByCssSelector(attributes.get(DATA_TEST), "subtotal-label");
    private final By taxPrice = toByCssSelector(attributes.get(DATA_TEST), "tax-label");
    private final By totalPrice = toByCssSelector(attributes.get(DATA_TEST), "total-label");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввод First Name: {firstName}")
    public CheckoutPage enterFirstName(String firstName) {
        driver.findElement(enterFirstName).sendKeys(firstName);
        return this;
    }

    @Step("Ввод Last Name: {lastName}")
    public CheckoutPage enterLastName(String lastName) {
        driver.findElement(enterLastName).sendKeys(lastName);
        return this;
    }

    @Step("Ввод Zip/Postal Code: {code}")
    public CheckoutPage enterZipOrPostalCode(String code) {
        driver.findElement(enterZipOrPostalCode).sendKeys(code);
        return this;
    }

    @Step("Выполнение входа. Нажатие на кнопку Continue и переход на страницу обзора заказа.")
    public CheckoutPage submitContinue() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(continueButton)).click();
        return this;
    }

    @Step("Переход на страницу завершения формирования заказа.")
    public void submitFinish() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(finishButton)).click();
    }

    @Step("Проверка наличия изображения успешности завершения офрмления заказа.")
    public boolean isImageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(completeImg)).isDisplayed();
    }

    @Step("Получение текста об успешности оформления заказа.")
    public String textCompletedOrder() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(textCompletedOrder)).getText();
    }

    @Step("Получение стоимости товаров.")
    public double getItemTotalPrice() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(itemTotalPrice)).getText();
        return Double.parseDouble(text.substring(text.indexOf('$') + 1));
    }

    @Step("Получение величины налога.")
    public double getTaxPrice() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(taxPrice)).getText();
        return Double.parseDouble(text.substring(text.indexOf('$') + 1));
    }

    @Step("Получение общей стоимости заказа.")
    public double getTotalPrice() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(totalPrice)).getText();
        return Double.parseDouble(text.substring(text.indexOf('$') + 1));
    }
}
