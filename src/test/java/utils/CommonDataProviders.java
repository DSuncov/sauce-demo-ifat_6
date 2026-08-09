package utils;

import org.testng.annotations.DataProvider;

public class CommonDataProviders {

    @DataProvider(name = "validLoginDataForCartAndProducts")
    public Object[][] validLoginDataForCartAndProducts() {
        return new Object[][] {
                {"saucedemo.valid.login[0]"},
                {"saucedemo.valid.login[3]"},
                {"saucedemo.valid.login[5]"}
        };
    }
}
