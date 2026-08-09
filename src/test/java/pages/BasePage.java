package pages;

import enums.Attribute;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertyReader;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static enums.Attribute.DATA_TEST;

public class BasePage {

    private static final long WAIT = 5;
    protected static final String BASE_URL = PropertyReader.getProperty("saucedemo.url");
    protected static final String PATTERN = "[%s='%s']";
    protected final Map<Attribute, String> attributes = fillMap();

    protected final By pageTitle = toByCssSelector(attributes.get(DATA_TEST), "title");
    protected final By errorText = toByCssSelector(attributes.get(DATA_TEST), "error");
    private final By inventoryItemName = toByCssSelector(attributes.get(DATA_TEST), "inventory-item-name");
    private final By inventoryItemPrice = toByCssSelector(attributes.get(DATA_TEST), "inventory-item-price");

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT));
    }

    @Step("Получение заголовка страницы.")
    public String getText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle))
                .getText();
    }

    @Step("Проверка отображения заголовка страницы.")
    public boolean pageIsOpen() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle))
                .isDisplayed();
    }

    @Step("Получение текста сообщения об ошибке.")
    public String getErrorText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorText))
                .getText();
    }

    @Step("Проверка отображения сообщения об ошибке.")
    public boolean isErrorTextDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorText))
                .isDisplayed();
    }

    @Step("Получение списка товаров на странице Checkout: Overview.")
    public List<String> getProductsName() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inventoryItemName));
        return driver.findElements(inventoryItemName).stream()
                .map(WebElement::getText)
                .toList();
    }

    @Step("Получение стоимости товаров на странице Checkout: Overview.")
    public double getProductsPrice() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inventoryItemPrice));
        return driver.findElements(inventoryItemPrice).stream()
                .map(WebElement::getText)
                .map(e -> e.substring(e.indexOf('$') + 1))
                .map(Double::parseDouble)
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    protected By toByCssSelector(String attribute, String text) {
        return By.cssSelector(PATTERN.formatted(attribute, text));
    }

    private Map<Attribute, String> fillMap() {
        return Arrays.stream(Attribute.values()).collect(Collectors.toMap(
                        Function.identity(),
                        Attribute::getAttribute
                ));
    }
}
