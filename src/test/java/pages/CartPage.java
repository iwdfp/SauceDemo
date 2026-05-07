package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    private final By TITLE = (By.cssSelector("[data-test=title]"));
    private final String CART_ITEM_NAME =
            "//div[@class='cart_item']//div[@class='inventory_item_name'][text()='%s']";
    private final String CART_ITEM_PRICE =
            "//div[@class='cart_item']//div[@class='inventory_item_name'][text()='%s']/ancestor::div[@class='cart_item']//div[@class='inventory_item_price']";

    public String getCartItemName(String itemName) {
        return driver.findElement(By.xpath(String.format(CART_ITEM_NAME, itemName))).getText();
    }

    public String getCartItemPrice(String itemName) {
        return driver.findElement(By.xpath(String.format(CART_ITEM_PRICE, itemName))).getText();
    }

    public CartPage(WebDriver driver) {
        super(driver);
    }
    @Step("Возвращаемся на страницу с товарами")
    public void backShopping() {
        driver.findElement(By.xpath("//button[@id='continue-shopping']")).click();
    }
    @Step("Переходим на страницу с товарами")
    public void goCheckout() {
        driver.findElement(By.xpath("//button[@id='checkout']")).click();
    }
    @Step("Проверяем, что мы на странице с товарами")
    public String getTitleCart() {
        return driver.findElement(TITLE).getText();
    }
}
