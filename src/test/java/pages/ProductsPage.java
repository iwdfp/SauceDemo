package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {

    private final By TITLE = (By.cssSelector("[data-test=title]"));
    private final By sortDropdown = By.cssSelector("[data-test=product-sort-container]");
    private final String ADD_ITEM_TO_CART =
            "//*[text()='%s']/ancestor::div[@class='inventory_item']//button[text()='Add to cart']";
    private final String REMOVE_ITEM_TO_CART =
            "//*[text()='%s']/ancestor::div[@class='inventory_item']//button[text()='Remove']";

    public ProductsPage(WebDriver driver) {
        super(driver);
    }
    @Step("Добавляем товар: '{itemName}' в корзину")
    public void addItemCart(String itemName) {
        driver.findElement(By.xpath(String.format(ADD_ITEM_TO_CART, itemName))).click();
    }
    @Step("Удаляем товар: '{itemName}' из корзины")
    public void removeItemCart(String itemName) {
        driver.findElement(By.xpath(String.format(REMOVE_ITEM_TO_CART, itemName))).click();
    }
    @Step("Получаем название кнопки вещи: '{itemName}'")
    public String getButtonText(String itemName) {
        return driver.findElement(By.xpath(String.format(
                "//*[text()='%s']/ancestor::div[@class='inventory_item']//button", itemName))).getText();
    }

    public void open() {
        driver.get(BASE_URL + "/inventory.html");
    }
    @Step("Проверяем, что мы на странице 'Products'")
    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }
//    //так как при нажатии на кнопку add to cart кол-во кнопок меняется, нам нужно искать товар и нажимать его кнопку
//    public void addItemBucket(int position) {
//        driver.findElement(By.xpath(String.format("(//div[@class='inventory_item'])" +
//                "[%d]//button[text()='Add to cart']", position))).click();
//    }
    @Step("Переходим на страницу 'корзина'")
    public void goToCart() {
        driver.findElement(By.className("shopping_cart_link")).click();
    }
    @Step("Проверяем, что есть кнопка 'Add to cart'")
    public String getAddCartMessage(int position) {
        return driver.findElement(By.xpath(String.format("(//div[@class='inventory_item'])" +
                "[%d]//button[text()='Add to cart']", position))).getText();
    }
    @Step("Проверяем, что кнопка 'remove' появилась")
    public String getRemoveMessage() {
        return driver.findElement(By.xpath("//button[text()='Remove']")).getText();
    }
    @Step("Нажимаем кнопку 'remove'")
    public void removeItem(int position) {
        driver.findElement(By.xpath(String.format("(//div[@class='inventory_item'])" +
                "[%d]//button[text()='Remove']", position))).click();
    }
    @Step("Выбираем сортировку")
    public void setDropdown(String value) {
        driver.findElement(By.cssSelector("[data-test=product-sort-container]")).click();
        driver.findElement(By.xpath(String.format("//option[@value='%s']", value))).click();
    }
    @Step("Получаем текст текущей сортировки")
    public String getActiveOptionText() {
        return driver.findElement(By.cssSelector("[data-test=active-option]")).getText();
    }
    @Step("Выходим из аккаунта через 'бургер' меню")
    public void logoutBurger() throws InterruptedException {
        driver.findElement(By.className("bm-burger-button")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("logout_sidebar_link")).click();
    }
}
