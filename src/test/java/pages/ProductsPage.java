package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {

    private final By TITLE = (By.cssSelector("[data-test=title]"));
    private final By sortDropdown = By.cssSelector("[data-test=product-sort-container]");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL + "/inventory.html");
    }
    //получить тайтл со страницы с товарами (проверка успешной авторизации)
    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }
    //так как при нажатии на кнопку add to cart кол-во кнопок меняется, нам нужно искать товар и нажимать его кнопку
    public void addItemBucket(int position) {
        driver.findElement(By.xpath(String.format("(//div[@class='inventory_item'])" +
                "[%d]//button[text()='Add to cart']", position))).click();
    }
    //перейти в корзину
    public void goToCart() {
        driver.findElement(By.className("shopping_cart_link")).click();
    }
    //проверить, что есть кнопка Add To Cart у выбранного товара
    public String getAddCartMessage(int position) {
        return driver.findElement(By.xpath(String.format("(//div[@class='inventory_item'])" +
                "[%d]//button[text()='Add to cart']", position))).getText();
    }
    //проверить, что кнопка Remove появилась
    public String getRemoveMessage() {
        return driver.findElement(By.xpath("//button[text()='Remove']")).getText();
    }
    //нажать кнопку удалить товар
    public void removeItem(int position) {
        driver.findElement(By.xpath(String.format("(//div[@class='inventory_item'])" +
                "[%d]//button[text()='Remove']", position))).click();
    }
    //выбираем сортировку по значению
    public void setDropdown(String value) {
        driver.findElement(By.cssSelector("[data-test=product-sort-container]")).click();
        driver.findElement(By.xpath(String.format("//option[@value='%s']", value))).click();
    }
    //получаем текст текущей сортировки
    public String getActiveOptionText() {
        return driver.findElement(By.cssSelector("[data-test=active-option]")).getText();
    }
    //сделать логаут из "бургер" меню. добавлено ожидание открытия бургер меню
    public void logoutBurger() throws InterruptedException {
        driver.findElement(By.className("bm-burger-button")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("logout_sidebar_link")).click();
    }
}
