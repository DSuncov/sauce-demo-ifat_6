package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static enums.Attribute.DATA_TEST;
import static enums.Attribute.PLACEHOLDER;

public class LoginPage extends BasePage {

    private final By usernameField = toByCssSelector(attributes.get(PLACEHOLDER), "Username");
    private final By passwordField = toByCssSelector(attributes.get(PLACEHOLDER), "Password");
    private final By loginButton = toByCssSelector(attributes.get(DATA_TEST), "login-button");

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
        driver.findElement(usernameField).sendKeys(login);
        return this;
    }

    @Step("Ввод пароля: {password}")
    public LoginPage enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    @Step("Выполнение входа. Нажатие на кнопку Login")
    public LoginPage submit() {
        driver.findElement(loginButton).click();
        return this;
    }
}
