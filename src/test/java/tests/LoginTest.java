package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    private static final String PASSWORD = "secret_sauce";
    private static final String EMPTY_DATA_FOR_LOGIN_OR_PASSWORD = "";

    @DataProvider(name = "validLoginData")
    public Object[][] validLoginData() {
        return new Object[][] {
                {"standard_user"},
                {"problem_user"},
                {"performance_glitch_user"},
                {"error_user"},
                {"visual_user"}
        };
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return new Object[][] {
                {"standard"},
                {"locked_out"},
                {"problem"},
                {"performance"},
                {"error"},
                {"visual"}
        };
    }

    @DataProvider(name = "lockedUsersData")
    public Object[][] lockedUsersData() {
        return new Object[][] {
                {"locked_out_user"}
        };
    }

    @DataProvider(name = "lockedUsersDataWithEmptyPassword")
    public Object[][] lockedUsersDataWithEmptyPassword() {
        return new Object[][] {
                {"standard_user"},
                {"problem_user"},
                {"performance_glitch_user"},
                {"error_user"},
                {"visual_user"}
        };
    }

    @Test(description = "Проверка успешного ввода логинов и пароля.",
            dataProvider = "validLoginData")
    public void loginAndPasswordAccepted(String login) {
        loginPage.open();
        loginPage.login(login, PASSWORD);

        String actual = productsPage.getTextPage();
        String expected = "Products";

        assertEquals(actual, expected);
    }

    @Test(description = "Проверка ввода невалидного логина.",
            dataProvider = "invalidLoginData")
    public void loginNotAccepted(String login) {
        loginPage.open();
        loginPage.login(login, PASSWORD);

        String actual = loginPage.getErrorText();
        String expected = "Epic sadface: Username and password do not match any user in this service";

        assertEquals(actual, expected);
        assertTrue(loginPage.isErrorText());
    }

    @Test(description = "Проверка ввода логина заблокированного пользователя.",
            dataProvider = "lockedUsersData")
    public void lockedUserNotAccepted(String login) {
        loginPage.open();
        loginPage.login(login, PASSWORD);

        String actual = loginPage.getErrorText();
        String expected = "Epic sadface: Sorry, this user has been locked out.";

        assertEquals(actual, expected);
        assertTrue(loginPage.isErrorText());
    }

    @Test(description = "Проверка ввода пустого логина.")
    public void emptyLoginCheck() {
        loginPage.open();
        loginPage.login(EMPTY_DATA_FOR_LOGIN_OR_PASSWORD, PASSWORD);

        String actual = loginPage.getErrorText();
        String expected = "Epic sadface: Username is required";

        assertEquals(actual, expected);
        assertTrue(loginPage.isErrorText());
    }

    @Test(description = "Проверка ввода валидного логина с пустым паролем.",
            dataProvider = "lockedUsersDataWithEmptyPassword")
    public void emptyPasswordCheck(String login) {
        loginPage.open();
        loginPage.login(login, EMPTY_DATA_FOR_LOGIN_OR_PASSWORD);

        String actual = loginPage.getErrorText();
        String expected = "Epic sadface: Password is required";

        assertEquals(actual, expected);
        assertTrue(loginPage.isErrorText());
    }
}
