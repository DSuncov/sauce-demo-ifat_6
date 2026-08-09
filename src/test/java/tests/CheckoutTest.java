package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;
import utils.CommonDataProviders;
import utils.PropertyReader;

import java.util.List;

import static enums.ErrorMessage.*;
import static enums.PageTitle.*;
import static org.testng.Assert.*;
import static user.UserFactory.standardUser;

@Epic("Интернет-магазин.")
@Feature("Оформление заказа.")
@Owner("Dmitrii Suntsov")
public class CheckoutTest extends BaseTest {

    @DataProvider(name = "dataForCheckout")
    public Object[][] data() {
        return new Object[][] {
                {"saucedemo.first-name[0]", "saucedemo.last-name[0]", "saucedemo.code"},
                {"saucedemo.first-name[1]", "saucedemo.last-name[1]", "saucedemo.code"},
                {"saucedemo.first-name[2]", "saucedemo.last-name[2]", "saucedemo.code"},
                {"saucedemo.first-name[3]", "saucedemo.last-name[3]", "saucedemo.code"},
                {"saucedemo.first-name[4]", "saucedemo.last-name[4]", "saucedemo.code"},
                {"saucedemo.first-name[5]", "saucedemo.last-name[5]", "saucedemo.code"}
        };
    }

    @Story("Проверка перехода на страницу добавления информации о покупателе.")
    @TmsLink("sauce-demo-ifat_6")
    @Issue("sauce-demo-ifat_6")
    @Description("Тестовый метод выполняет проверку перехода на страницу с заполнением информации о покупателе," +
            " необходимой для оформлением товаров.")
    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "Проверяет переход на страницу добавления информации о покупателе.",
            dataProvider = "validLoginDataForCartAndProducts", dataProviderClass = CommonDataProviders.class)
    public void checkSwitchToInformationPage(String loginPath) {
        loginPage
                .open()
                .enterLogin(PropertyReader.getProperty(loginPath))
                .enterPassword(password)
                .submit();

        productsPage.switchToCart();
        cartPage.switchToCheckout();

        assertEquals(checkoutPage.getText(), CHECKOUT_INFORMATION.getTitle());
        assertTrue(checkoutPage.pageIsOpen());
    }

    @Story("Проверка ошибки при пустом First Name.")
    @TmsLink("sauce-demo-ifat_6")
    @Issue("sauce-demo-ifat_6")
    @Description("Тестовый метод выполняет проверку отображения ошибки при вводе пустого First Name")
    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "Проверяет наличие ошибки при вводе пустого First Name.",
            dataProvider = "dataForCheckout")
    public void emptyFirstNameCheck(String firstName, String lastName, String code) {
        login();
        productsPage.switchToCart();
        cartPage.switchToCheckout();

        checkoutPage
                .enterLastName(lastName)
                .enterZipOrPostalCode(code)
                .submitContinue();

        assertEquals(checkoutPage.getErrorText(), FIRST_NAME_REQUIRED.getMessage());
        assertTrue(checkoutPage.isErrorTextDisplayed());
    }

    @Story("Проверка ошибки при пустом Last Name.")
    @TmsLink("sauce-demo-ifat_6")
    @Issue("sauce-demo-ifat_6")
    @Description("Тестовый метод выполняет проверку отображения ошибки при вводе пустого Last Name")
    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "Проверяет наличие ошибки при вводе пустого Last Name.",
            dataProvider = "dataForCheckout")
    public void emptyLastNameCheck(String firstName, String lastName, String code) {
        login();
        productsPage.switchToCart();
        cartPage.switchToCheckout();

        checkoutPage
                .enterFirstName(firstName)
                .enterZipOrPostalCode(code)
                .submitContinue();

        assertEquals(checkoutPage.getErrorText(), LAST_NAME_REQUIRED.getMessage());
        assertTrue(checkoutPage.isErrorTextDisplayed());
    }

    @Story("Проверка ошибки при пустом Zip/Postal Code.")
    @TmsLink("sauce-demo-ifat_6")
    @Issue("sauce-demo-ifat_6")
    @Description("Тестовый метод выполняет проверку отображения ошибки при вводе пустого Zip/Postal Code")
    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "Проверяет наличие ошибки при вводе пустого Zip/Postal Code.",
            dataProvider = "dataForCheckout")
    public void emptyZipOrPostalCode(String firstName, String lastName, String code) {
        login();
        productsPage.switchToCart();
        cartPage.switchToCheckout();

        checkoutPage
                .enterFirstName(firstName)
                .enterLastName(lastName)
                .submitContinue();

        assertEquals(checkoutPage.getErrorText(), POSTAL_CODE_REQUIRED.getMessage());
        assertTrue(checkoutPage.isErrorTextDisplayed());
    }

    @Story("Проверка перехода на страницу обзора заказа.")
    @TmsLink("sauce-demo-ifat_6")
    @Issue("sauce-demo-ifat_6")
    @Description("Тестовый метод выполняет проверку перехода на страницу обзора заказа.")
    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "Проверяет открытие страницы с обзором заказа.",
            dataProvider = "dataForCheckout")
    public void checkSwitchToOverviewPage(String firstName, String lastName, String code) {
        login();
        productsPage.switchToCart();
        cartPage.switchToCheckout();

        checkoutPage
                .enterFirstName(firstName)
                .enterLastName(lastName)
                .enterZipOrPostalCode(code)
                .submitContinue();

        assertEquals(checkoutPage.getText(), CHECKOUT_OVERVIEW.getTitle());
        assertTrue(checkoutPage.pageIsOpen());
    }

    @Story("Проверка перехода на страницу завершения заказа.")
    @TmsLink("sauce-demo-ifat_6")
    @Issue("sauce-demo-ifat_6")
    @Description("Тестовый метод выполняет проверку перехода на страницу с завершением заказа.")
    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "Проверяет открытие страницы с завершение сбора заказа.",
            dataProvider = "dataForCheckout")
    public void checkSwitchToFinishPage(String firstName, String lastName, String code) {
        login();
        productsPage.switchToCart();
        cartPage.switchToCheckout();

        checkoutPage
                .enterFirstName(firstName)
                .enterLastName(lastName)
                .enterZipOrPostalCode(code)
                .submitContinue()
                .submitFinish();

        assertEquals(checkoutPage.getText(), CHECKOUT_COMPLETE.getTitle());
        assertEquals(checkoutPage.textCompletedOrder(), "Thank you for your order!");
        assertTrue(checkoutPage.pageIsOpen());
        assertTrue(checkoutPage.isImageDisplayed());
    }

    @Story("Проверка соответствия товаров в корзине и на странице оформления заказа.")
    @TmsLink("sauce-demo-ifat_6")
    @Issue("sauce-demo-ifat_6")
    @Description("Тестовый метод выполняет проверку соотвествия количества и состава товаров в корзине " +
            "и на странице оформления заказа.")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Проверяет количество и состав товаров на странице Checkout: Overview и корзине.",
            dataProvider = "dataForCheckout")
    public void checkGoodsInCartAndCheckoutPage(String firstName, String lastName, String code) {
        login();

        goods.stream()
                .filter(good -> good.contains("T-Shirt"))
                .forEach(productsPage::addToCart);

        productsPage.switchToCart();
        List<String> productsNamesOnCart = cartPage.getProductsName();

        cartPage.switchToCheckout();

        checkoutPage
                .enterFirstName(firstName)
                .enterLastName(lastName)
                .enterZipOrPostalCode(code)
                .submitContinue();

        List<String> productsNamesOnCheckout = checkoutPage.getProductsName();
        assertEquals(productsNamesOnCart, productsNamesOnCheckout);
        assertEquals(productsNamesOnCart.size(), productsNamesOnCheckout.size());
    }

    @Story("Проверка стоимости товаров в заказе.")
    @TmsLink("sauce-demo-ifat_6")
    @Issue("sauce-demo-ifat_6")
    @Description("Тестовый метод выполняет проверку стоимости товаров в заказе.")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Проверяет стоимость товаров.",
            dataProvider = "dataForCheckout")
    public void checkPrice(String firstName, String lastName, String code) {
        login();

        goods.stream()
                .filter(good -> good.contains("Labs"))
                .forEach(productsPage::addToCart);

        productsPage.switchToCart();
        double productsPriceOnCart = cartPage.getProductsPrice();

        cartPage.switchToCheckout();

        checkoutPage
                .enterFirstName(firstName)
                .enterLastName(lastName)
                .enterZipOrPostalCode(code)
                .submitContinue();

        double productsPriceOnCheckout = checkoutPage.getProductsPrice();
        assertEquals(productsPriceOnCart, productsPriceOnCheckout);
        assertEquals(checkoutPage.getItemTotalPrice() + checkoutPage.getTaxPrice(), checkoutPage.getTotalPrice());
    }

    private void login() {
        User testUser = standardUser();

        loginPage
                .open()
                .enterLogin(testUser.login())
                .enterPassword(testUser.password())
                .submit();
    }
}
