package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getProductsName() {
        return driver.findElements(By.cssSelector(".inventory_item_name")).stream()
                .map(WebElement::getText)
                .toList();
    }
}
