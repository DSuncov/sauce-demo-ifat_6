package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static enums.ErrorMessage.*;
import static enums.PageTitle.PRODUCTS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.*;

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
        loginPage.open().login(createUser(loginPath, password));

        String actual = productsPage.getTextPage();

        assertEquals(actual, PRODUCTS.getValue());
    }

    @Test(description = "Проверка ввода невалидного логина.",
            dataProvider = "invalidLoginData")
    public void loginNotAccepted(String loginPath) {
        loginPage.open().login(createUser(loginPath, password));

        String actual = loginPage.getErrorText();

        assertEquals(actual, INVALID_LOGIN_OR_PASSWORD.getValue());
        assertTrue(loginPage.isErrorTextDisplayed());
    }

    @Test(description = "Проверка ввода валидного логина с пустым паролем.",
            dataProvider = "validLoginData")
    public void emptyPasswordCheck(String loginPath) {
        loginPage.open().login(withEmptyPassword(loginPath));

        String actual = loginPage.getErrorText();

        assertEquals(actual, PASSWORD_REQUIRED.getValue());
        assertTrue(loginPage.isErrorTextDisplayed());
    }

    @Test(description = "Проверка ввода валидного логина с невалидным паролем паролем.",
            dataProvider = "validLoginData")
    public void invalidPasswordCheck(String loginPath) {
        loginPage.open().login(withInvalidPassword(loginPath));

        String actual = loginPage.getErrorText();

        assertEquals(actual, INVALID_LOGIN_OR_PASSWORD.getValue());
        assertTrue(loginPage.isErrorTextDisplayed());
    }

    @Test(description = "Проверка ввода заблокированного логина.")
    public void lockedUserCheck() {
        loginPage.open().login(lockedUser(password));

        String actual = loginPage.getErrorText();

        assertEquals(actual, LOCKED_USER.getValue());
        assertTrue(loginPage.isErrorTextDisplayed());
    }

    @Test(description = "Проверка ввода пустого логина.")
    public void emptyUserCheck() {
        loginPage.open().login(withEmptyLogin(password));

        String actual = loginPage.getErrorText();

        assertEquals(actual, USERNAME_REQUIRED.getValue());
        assertTrue(loginPage.isErrorTextDisplayed());
    }
}
