package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.PropertyReader;

import java.time.Duration;
import java.util.List;

public class BaseTest {

    protected WebDriver driver;
    protected LoginPage loginPage;
    protected ProductsPage productsPage;
    protected CartPage cartPage;
    protected String password;

    protected final List<String> goods = List.of(
            "Sauce Labs Bolt T-Shirt",
            "Sauce Labs Backpack",
            "Sauce Labs Fleece Jacket",
            "Sauce Labs Bike Light",
            "Sauce Labs Onesie",
            "Test.allTheThings() T-Shirt (Red)"
    );

    @BeforeMethod
    public void setUp() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("start-maximized");
        options.addArguments("headless");
        options.addArguments("--guest");

        driver = new EdgeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));

        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        password = PropertyReader.getProperty("saucedemo.password");
    }

    @AfterMethod
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
