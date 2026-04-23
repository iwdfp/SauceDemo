package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    private final By FIRST_NAME_FIELD = By.xpath("//*[@data-test='firstName']");
    private final By LAST_NAME_FIELD = By.xpath("//*[@data-test='lastName']");
    private final By ZIP_POSTAL_CODE = By.xpath("//*[@data-test='postalCode']");
    private final By ORDER_BUTTON = By.xpath("//*[@data-test='continue']");
    private final By ERROR_MESSAGE = By.xpath("//*[@data-test='error']");
    private final By TITLE = (By.cssSelector("[data-test=title]"));

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }
    //заполнение полей для оформления заказа с нажатием
    public void makeOrder(String firstName, String lastName, String postalCode) {
        driver.findElement(FIRST_NAME_FIELD).sendKeys(firstName);
        driver.findElement(LAST_NAME_FIELD).sendKeys(lastName);
        driver.findElement(ZIP_POSTAL_CODE).sendKeys(postalCode);
        driver.findElement(ORDER_BUTTON).click();
    }
    //проверка, что мы на странице "оформление заказа"
    public String getTitleCheckout() {
        return driver.findElement(TITLE).getText();
    }
    //проверка ошибки при незаполненных полях
    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }
    //кнопка для возвращения на страницу "корзина"
    public void backToCart() {
        driver.findElement(By.cssSelector("[data-test='cancel']")).click();
    }
}
