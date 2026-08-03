package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import user.User;

import static enums.Attribute.DATA_TEST;
import static enums.Attribute.PLACEHOLDER;

public class LoginPage extends BasePage {

    private final By usernameFieldLocator = toByXpath(attributes.get(PLACEHOLDER), "Username");
    private final By passwordFieldLocator = toByXpath(attributes.get(PLACEHOLDER), "Password");
    private final By loginButtonLocator = toByXpath(attributes.get(DATA_TEST), "login-button");
    private final By errorTextLocator = toByXpath(attributes.get(DATA_TEST), "error");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        driver.get(BASE_URL);
        return this;
    }

    public LoginPage login(User user) {
        driver.findElement(usernameFieldLocator).sendKeys(user.login());
        driver.findElement(passwordFieldLocator).sendKeys(user.password());
        driver.findElement(loginButtonLocator).click();
        return this;
    }

    public String getErrorText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorTextLocator));
        return driver.findElement(errorTextLocator).getText();
    }

    public boolean isErrorTextDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorTextLocator));
        return driver.findElement(errorTextLocator).isDisplayed();
    }
}
