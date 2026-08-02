package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.UserFactory;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @DataProvider(name = "validLoginData")
    public Object[][] validLoginData() {
        return new Object[][] {
                {"saucedemo.standard-user"},
                {"saucedemo.problem-user"},
                {"saucedemo.performance-glitch-user"},
                {"saucedemo.error-user"},
                {"saucedemo.visual-user"}
        };
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return new Object[][] {
                {"saucedemo.invalid.login.standard-user"},
                {"saucedemo.invalid.login.problem-user"},
                {"saucedemo.invalid.login.performance-glitch-user"},
                {"saucedemo.invalid.login.error-user"},
                {"saucedemo.invalid.login.visual-user"}
        };
    }

    @Test(description = "Проверка успешного ввода логинов и пароля.",
            dataProvider = "validLoginData")
    public void loginAndPasswordAccepted(String loginPath) {
        loginPage.open();
        loginPage.login(UserFactory.createUser(loginPath, password));

        String actual = productsPage.getTextPage();

        assertEquals(actual, "Products");
    }

    @Test(description = "Проверка ввода невалидного логина.",
            dataProvider = "invalidLoginData")
    public void loginNotAccepted(String loginPath) {
        loginPage.open();
        loginPage.login(UserFactory.createUser(loginPath, password));

        String actual = loginPage.getErrorText();

        assertEquals(actual, "Epic sadface: Username and password do not match any user in this service");
        assertTrue(loginPage.isErrorTextDisplayed());
    }

    @Test(description = "Проверка ввода валидного логина с пустым паролем.",
            dataProvider = "validLoginData")
    public void emptyPasswordCheck(String loginPath) {
        loginPage.open();
        loginPage.login(UserFactory.withEmptyPassword(loginPath));

        String actual = loginPage.getErrorText();

        assertEquals(actual, "Epic sadface: Password is required");
        assertTrue(loginPage.isErrorTextDisplayed());
    }

    @Test(description = "Проверка ввода заблокированного логина.")
    public void lockedUserCheck() {
        loginPage.open();
        loginPage.login(UserFactory.lockedUser(password));

        String actual = loginPage.getErrorText();

        assertEquals(actual, "Epic sadface: Sorry, this user has been locked out.");
        assertTrue(loginPage.isErrorTextDisplayed());
    }

    @Test(description = "Проверка ввода пустого логина.")
    public void emptyUserCheck() {
        loginPage.open();
        loginPage.login(UserFactory.withEmptyLogin(password));

        String actual = loginPage.getErrorText();

        assertEquals(actual, "Epic sadface: Username is required");
        assertTrue(loginPage.isErrorTextDisplayed());
    }
}
