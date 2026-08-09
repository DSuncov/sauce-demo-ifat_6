package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import utils.CommonDataProviders;
import utils.PropertyReader;

import java.util.List;

import static enums.PageTitle.CART;
import static org.testng.Assert.*;

@Epic("Интернет-магазин.")
@Feature("Корзина.")
@Owner("Dmitrii Suntsov")
public class CartTest extends BaseTest {

    @Story("Проверка перехода на страницу корзины.")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("sauce-demo-ifat_6")
    @Issue("sauce-demo-ifat_6")
    @Description("Тестовый метод выполняет проверку перехода на страницу с корзиной товаров.")
    @Test(description = "Проверяет переход на страницу с корзиной.",
            dataProvider = "validLoginDataForCartAndProducts", dataProviderClass = CommonDataProviders.class)
    public void checkTransitionToCartPage(String loginPath) {
        loginPage
                .open()
                .enterLogin(PropertyReader.getProperty(loginPath))
                .enterPassword(password)
                .submit();

        goods.forEach(productsPage::addToCart);

        productsPage.switchToCart();

        assertEquals(cartPage.getText(), CART.getTitle());
        assertTrue(cartPage.pageIsOpen());
    }

    @Story("Проверка добавленных товаров на странице корзины.")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("sauce-demo-ifat_6")
    @Issue("sauce-demo-ifat_6")
    @Description("Тестовый метод выполняет проверку количества выбранных товаров в корзине и их состав на совпадение.")
    @Test(description = "Проверяет состав и количество добавленных товаров в корзине.",
            dataProvider = "validLoginDataForCartAndProducts", dataProviderClass = CommonDataProviders.class)
    public void checkGoodsAdded(String loginPath) {
        loginPage
                .open()
                .enterLogin(PropertyReader.getProperty(loginPath))
                .enterPassword(password)
                .submit();

        goods.forEach(productsPage::addToCart);

        productsPage.switchToCart();

        List<String> productsNames = cartPage.getProductsName();
        assertFalse(productsNames.isEmpty());
        assertEquals(productsNames, goods);
        assertEquals(productsNames.size(), goods.size());
    }
}
