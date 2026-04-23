package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    private final By TITLE = (By.cssSelector("[data-test=title]"));

    public CartPage(WebDriver driver) {
        super(driver);
    }
    //возвращаемся на страницу с товарами
    public void backShopping() {
        driver.findElement(By.xpath("//button[@id='continue-shopping']")).click();
    }
    //переходим на страницу оформления заказа
    public void goCheckout() {
        driver.findElement(By.xpath("//button[@id='checkout']")).click();
    }
    //проверка, что мы на странице "корзина"
    public String getTitleCart() {
        return driver.findElement(TITLE).getText();
    }

}
