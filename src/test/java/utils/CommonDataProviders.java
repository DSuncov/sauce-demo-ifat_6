package utils;

import org.testng.annotations.DataProvider;

public class CommonDataProviders {

    @DataProvider(name = "validLoginDataForCartAndProducts")
    public Object[][] validLoginDataForCartAndProducts() {
        return new Object[][] {
                {"saucedemo.standard-user"},
                {"saucedemo.performance-glitch-user"},
                {"saucedemo.visual-user"}
        };
    }
}
