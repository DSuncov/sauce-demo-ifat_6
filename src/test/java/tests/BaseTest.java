package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;
import pages.ProductsPage;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected LoginPage loginPage;
    protected ProductsPage productsPage;

    @BeforeMethod
    public void setUp() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("start-maximized");
        options.addArguments("headless");
        options.addArguments("--guest"); // or --incognito

        driver = new EdgeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));

        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
    }

    @AfterMethod
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
