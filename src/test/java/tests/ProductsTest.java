package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import utils.CommonDataProviders;
import utils.PropertyReader;

import static org.testng.Assert.assertEquals;

@Epic("Интернет-магазин.")
@Feature("Список товаров.")
@Owner("Dmitrii Suntsov")
public class ProductsTest extends BaseTest {

    @Story("Проверка количества добавленных товаров на странице списка товаров.")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("sauce-demo-ifat_6")
    @Issue("sauce-demo-ifat_6")
    @Description("Тестовый метод выполняет проверку количества выбранных товаров в корзине.")
    @Test(description = "Проверяет количество добавленных товаров в корзине.",
            dataProvider = "validLoginDataForCartAndProducts", dataProviderClass = CommonDataProviders.class)
    public void checkGoodsAdded(String loginPath) {
        loginPage
                .open()
                .enterLogin(PropertyReader.getProperty(loginPath))
                .enterPassword(password)
                .submit();

        productsPage.pageIsOpen();

        goods.forEach(productsPage::addToCart);

        assertEquals(productsPage.checkCounterValue(), goods.size());
    }
}
