package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;
import utils.PropertyReader;

import static enums.ErrorMessage.*;
import static enums.PageTitle.PRODUCTS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.*;

@Epic("Интернет-магазин.")
@Feature("Авторизация.")
@Owner("Dmitrii Suntsov")
public class LoginTest extends BaseTest {

    @DataProvider(name = "validLoginData")
    public Object[][] validLoginData() {
        return new Object[][] {
                {"saucedemo.valid.login[0]"},
                {"saucedemo.valid.login[2]"},
                {"saucedemo.valid.login[3]"},
                {"saucedemo.valid.login[4]"},
                {"saucedemo.valid.login[5]"}
        };
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return new Object[][] {
                {"saucedemo.invalid.login[0]"},
                {"saucedemo.invalid.login[1]"},
                {"saucedemo.invalid.login[2]"},
                {"saucedemo.invalid.login[3]"},
                {"saucedemo.invalid.login[4]"}
        };
    }

    @Story("Успешная авторизация.")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("sauce-demo-ifat_6")
    @Issue("sauce-demo-ifat_6")
    @Description("Тестовый метод выполняет проверку входа на сайт. Вводятся валидные логин и пароль. " +
            "Авторизация должна успешно выполниться и открыться страница товаров.")
    @Test(description = "Проверяет успешный вход на страницу товаров.",
            dataProvider = "validLoginData")
    public void loginAndPasswordAccepted(String loginPath) {
        loginPage
                .open()
                .enterLogin(PropertyReader.getProperty(loginPath))
                .enterPassword(password)
                .submit();

        assertEquals(productsPage.getText(), PRODUCTS.getTitle());
    }

    @Story("Неудачная авторизация с невалидным логином.")
    @TmsLink("sauce-demo-ifat_6")
    @Issue("sauce-demo-ifat_6")
    @Description("Тестовый метод выполняет проверку входа на сайт. Вводятся невалидный логин и валидный пароль. " +
            "Авторизация не должна выполниться. Должно появиться сообщение об ошибке.")
    @Test(description = "Проверяет наличие сообщения об ошибке при попытке входа с невалидным логином.",
            dataProvider = "invalidLoginData")
    public void loginNotAccepted(String loginPath) {
        loginPage
                .open()
                .enterLogin(PropertyReader.getProperty(loginPath))
                .enterPassword(password)
                .submit();

        assertEquals(loginPage.getErrorText(), INVALID_LOGIN_OR_PASSWORD.getMessage());
        assertTrue(loginPage.isErrorTextDisplayed());
    }

    @Story("Неудачная авторизация с пустым паролем.")
    @TmsLink("sauce-demo-ifat_6")
    @Issue("sauce-demo-ifat_6")
    @Description("Тестовый метод выполняет проверку входа на сайт. Вводятся валидный логин и пустой пароль. " +
            "Авторизация не должна выполниться. Должно появиться сообщение об ошибке.")
    @Test(description = "Проверяет наличие сообщения об ошибке при попытке входа с пустым паролем.",
            dataProvider = "validLoginData")
    public void emptyPasswordCheck(String loginPath) {
        loginPage
                .open()
                .enterLogin(PropertyReader.getProperty(loginPath))
                .submit();

        assertEquals(loginPage.getErrorText(), PASSWORD_REQUIRED.getMessage());
        assertTrue(loginPage.isErrorTextDisplayed());
    }

    @Story("Неудачная авторизация с невалидным паролем.")
    @TmsLink("sauce-demo-ifat_6")
    @Issue("sauce-demo-ifat_6")
    @Description("Тестовый метод выполняет проверку входа на сайт. Вводятся валидный логин и невалидный пароль. " +
            "Авторизация не должна выполниться. Должно появиться сообщение об ошибке.")
    @Test(description = "Проверяет наличие сообщения об ошибке при попытке входа с невалидным паролем.",
            dataProvider = "validLoginData")
    public void invalidPasswordCheck(String loginPath) {
        User testUser = withInvalidPassword(loginPath);

        loginPage
                .open()
                .enterLogin(testUser.login())
                .enterPassword(testUser.password())
                .submit();

        assertEquals(loginPage.getErrorText(), INVALID_LOGIN_OR_PASSWORD.getMessage());
        assertTrue(loginPage.isErrorTextDisplayed());
    }

    @Story("Неудачная авторизация с заблокированным логином.")
    @TmsLink("sauce-demo-ifat_6")
    @Issue("sauce-demo-ifat_6")
    @Description("Тестовый метод выполняет проверку входа на сайт. Вводятся заблокированный логин и валидный пароль. " +
            "Авторизация не должна выполниться. Должно появиться сообщение об ошибке.")
    @Test(description = "Проверяет наличие сообщения об ошибке при попытке входа с заблокированным логином.")
    public void lockedUserCheck() {
        User testUser = lockedUser();

        loginPage
                .open()
                .enterLogin(testUser.login())
                .enterPassword(testUser.password())
                .submit();

        assertEquals(loginPage.getErrorText(), LOCKED_USER.getMessage());
        assertTrue(loginPage.isErrorTextDisplayed());
    }

    @Story("Неудачная авторизация с пустым логином.")
    @TmsLink("sauce-demo-ifat_6")
    @Issue("sauce-demo-ifat_6")
    @Description("Тестовый метод выполняет проверку входа на сайт. Вводятся пустой логин и валидный пароль. " +
            "Авторизация не должна выполниться. Должно появиться сообщение об ошибке.")
    @Test(description = "Проверяет наличие сообщения об ошибке при попытке входа с пустым логином.")
    public void emptyUserCheck() {
        loginPage
                .open()
                .enterPassword(password)
                .submit();

        assertEquals(loginPage.getErrorText(), USERNAME_REQUIRED.getMessage());
        assertTrue(loginPage.isErrorTextDisplayed());
    }
}
