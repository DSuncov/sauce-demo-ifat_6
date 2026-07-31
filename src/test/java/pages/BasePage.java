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
    protected static final String PATTERN = "//*[@%s='%s']";
    protected final Map<Attribute, String> attributes = fillMap();

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    protected By toBy(String attribute, String text) {
        return By.xpath(PATTERN.formatted(attribute, text));
    }

    private Map<Attribute, String> fillMap() {
        return Arrays.stream(Attribute.values()).collect(Collectors.toMap(
                        Function.identity(),
                        Attribute::getValue
                ));
    }
}
