package pages;

import enums.Attribute;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertyReader;

import java.time.Duration;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BasePage {

    protected static final String BASE_URL = PropertyReader.getProperty("saucedemo.url");
    protected static final String XPATH_PATTERN = "//*[@%s='%s']";
    protected final Map<Attribute, String> attributes = fillMap();
    private static final long WAIT = 5;

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT));
    }

    protected By toByXpath(String attribute, String text) {
        return By.xpath(XPATH_PATTERN.formatted(attribute, text));
    }

    private Map<Attribute, String> fillMap() {
        return Arrays.stream(Attribute.values()).collect(Collectors.toMap(
                        Function.identity(),
                        Attribute::getValue
                ));
    }
}
