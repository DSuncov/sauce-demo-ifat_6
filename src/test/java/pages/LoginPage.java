package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static enums.Attribute.DATA_TEST;
import static enums.Attribute.PLACEHOLDER;

public class LoginPage extends BasePage {

    private final By usernameFieldLocator = toByCssSelector(attributes.get(PLACEHOLDER), "Username");
    private final By passwordFieldLocator = toByCssSelector(attributes.get(PLACEHOLDER), "Password");
    private final By loginButtonLocator = toByCssSelector(attributes.get(DATA_TEST), "login-button");
    private final By errorTextLocator = toByCssSelector(attributes.get(DATA_TEST), "error");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие браузера.")
    public LoginPage open() {
        driver.get(BASE_URL);
        return this;
    }

    @Step("Ввод логина: {login}")
    public LoginPage enterLogin(String login) {
        driver.findElement(usernameFieldLocator).sendKeys(login);
        return this;
    }

    @Step("Ввод пароля: {password}")
    public LoginPage enterPassword(String password) {
        driver.findElement(passwordFieldLocator).sendKeys(password);
        return this;
    }

    @Step("Выполнение входа. Нажатие на кнопку Login")
    public LoginPage submit() {
        driver.findElement(loginButtonLocator).click();
        return this;
    }

    @Step("Получение текста сообщения об ошибке.")
    public String getErrorText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorTextLocator))
                .getText();
    }

    @Step("Проверка отображения сообщения об ошибке.")
    public boolean isErrorTextDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorTextLocator))
                .isDisplayed();
    }
}
