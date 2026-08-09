package tests;

import enums.GoodsName;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.PropertyReader;
import utils.TestListener;

import java.util.Arrays;
import java.util.List;

@Listeners({AllureTestNg.class, TestListener.class})
public class BaseTest {

    protected WebDriver driver;
    protected LoginPage loginPage;
    protected ProductsPage productsPage;
    protected CartPage cartPage;
    protected CheckoutPage checkoutPage;
    protected String password = PropertyReader.getProperty("saucedemo.password");;
    protected List<String> goods = fillList();

    @Parameters({"browser"})
    @BeforeMethod
    public void setUp(@Optional("edge") String browser, ITestContext context) {
        if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions()
                    .addArguments("--start-maximized")
                    .addArguments("--headless=new")
                    .addArguments("--guest")
                    .addArguments("--no-sandbox")
                    .addArguments("--disable-dev-shm-usage")
                    .addArguments("--disable-gpu");

            driver = new EdgeDriver(options);
        }

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

        context.setAttribute("driver", driver);

        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @AfterMethod
    @Step("Закрытие браузера.")
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    private List<String> fillList() {
        return Arrays.stream(GoodsName.values())
                .map(GoodsName::getName)
                .toList();
    }
}
