package pages;

import enums.Attribute;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import user.User;

public class LoginPage extends BasePage {

    private final By usernameFieldLocator = toBy(attributes.get(Attribute.PLACEHOLDER), "Username");
    private final By passwordFieldLocator = toBy(attributes.get(Attribute.PLACEHOLDER), "Password");
    private final By loginButtonLocator = toBy(attributes.get(Attribute.DATA_TEST), "login-button");
    private final By errorTextLocator = toBy(attributes.get(Attribute.DATA_TEST), "error");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL);
    }

    public void login(User user) {
        driver.findElement(usernameFieldLocator).sendKeys(user.login());
        driver.findElement(passwordFieldLocator).sendKeys(user.password());
        driver.findElement(loginButtonLocator).click();
    }

    public String getErrorText() {
        return driver.findElement(errorTextLocator).getText();
    }

    public boolean isErrorTextDisplayed() {
        return driver.findElement(errorTextLocator).isDisplayed();
    }
}
