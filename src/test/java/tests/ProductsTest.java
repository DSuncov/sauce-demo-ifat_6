package tests;

import org.testng.annotations.Test;
import utils.CommonDataProviders;

import static org.testng.Assert.assertEquals;
import static user.UserFactory.createUser;

public class ProductsTest extends BaseTest {

    @Test(description = "Проверка количества товаров в корзине.",
            dataProvider = "validLoginDataForCartAndProducts", dataProviderClass = CommonDataProviders.class)
    public void checkGoodsAdded(String loginPath) {
        loginPage.open().login(createUser(loginPath, password));
        productsPage.pageIsOpen();

        goods.forEach(productsPage::addToCart);

        assertEquals(productsPage.checkCounterValue(), goods.size());
    }
}
