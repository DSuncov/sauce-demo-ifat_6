package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By usernameFieldLocator = By.xpath("//input[@placeholder='Username']");
    private final By passwordFieldLocator = By.xpath("//input[@placeholder='Password']");
    private final By loginButtonLocator = By.xpath("//input[@data-test='login-button']");
    private final By errorTextLocator = By.xpath("//h3[@data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL);
    }

    public void login(String login, String password) {
        driver.findElement(usernameFieldLocator).sendKeys(login);
        driver.findElement(passwordFieldLocator).sendKeys(password);
        driver.findElement(loginButtonLocator).click();
    }

    public String getErrorText() {
        return driver.findElement(errorTextLocator).getText();
    }

    public boolean isErrorText() {
        return driver.findElement(errorTextLocator).isDisplayed();
    }
}
