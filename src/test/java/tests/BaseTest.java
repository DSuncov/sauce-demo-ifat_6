package tests;

import enums.GoodsName;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.PropertyReader;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class BaseTest {

    protected WebDriver driver;
    protected LoginPage loginPage;
    protected ProductsPage productsPage;
    protected CartPage cartPage;
    protected String password = PropertyReader.getProperty("saucedemo.password");;
    protected List<String> goods = fillList();

    private static final long WAIT = 7;

    @Parameters({"browser"})
    @BeforeMethod
    public void setUp(@Optional("edge") String browser) {
        if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions()
                    .addArguments("start-maximized")
                    .addArguments("headless")
                    .addArguments("--guest");

            driver = new EdgeDriver(options);
        }

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WAIT));

        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
    }

    @AfterMethod
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    private List<String> fillList() {
        return Arrays.stream(GoodsName.values())
                .map(GoodsName::getValue)
                .toList();
    }
}
