package tests;

import org.testng.annotations.Test;
import utils.CommonDataProviders;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static user.UserFactory.createUser;

public class CartTest extends BaseTest {

    @Test(dataProvider = "validLoginDataForCartAndProducts", dataProviderClass = CommonDataProviders.class)
    public void checkGoodsAdded(String loginPath) {
        loginPage.open().login(createUser(loginPath, password));

        goods.forEach(productsPage::addToCart);

        productsPage.switchToCart();
        assertFalse(cartPage.getProductsName().isEmpty());
        assertEquals(cartPage.getProductsName(), goods);
        assertEquals(cartPage.getProductsName().size(), goods.size());
    }
}
