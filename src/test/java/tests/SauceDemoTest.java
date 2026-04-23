package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/*
a. Залогиниться
b. Добавить товар в корзину
c. Перейти в корзину
d. Проверить (assertEquals) стоимость товара и его имя в корзине
 */

public class SauceDemoTest extends BaseTest {

    int position = 3;
    int position1 = 4;
    int position2 = 1;

    @Test
    public void checkAddBucketItem() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        //запоминаем название и цену товара, который кладем в коризну
        String itemName = driver.findElements(By.className("inventory_item_name"))
                .get(position - 1).getText();
        String itemPrice = driver.findElements(By.className("inventory_item_price"))
                .get(position - 1).getText();
        //добавляем товар в корзину и переходим в нее
        productsPage.addItemBucket(position);
        productsPage.goToCart();
        //сравниваем товар и цену
        assertEquals(driver.findElement(By.className("inventory_item_name"))
                .getText(), itemName);
        assertEquals(driver.findElement(By.className("inventory_item_price"))
                .getText(), itemPrice);
    }

    @Test
    public void checkAddBucketSomeItems() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        List<WebElement> names = driver.findElements(By.className("inventory_item_name"));
        List<WebElement> prices = driver.findElements(By.className("inventory_item_price"));

        String itemName = names.get(position - 1).getText();
        String itemPrice = prices.get(position - 1).getText();
        String itemName1 = names.get(position1 - 1).getText();
        String itemPrice1 = prices.get(position1 - 1).getText();
        String itemName2 = names.get(position2 - 1).getText();
        String itemPrice2 = prices.get(position2 - 1).getText();

        productsPage.addItemBucket(position);
        productsPage.addItemBucket(position1);
        productsPage.addItemBucket(position2);
        productsPage.goToCart();

        List<WebElement> cartNames = driver.findElements(By.className("inventory_item_name"));
        List<WebElement> cartPrices = driver.findElements(By.className("inventory_item_price"));
        assertEquals(cartNames.get(0).getText(), itemName);
        assertEquals(cartPrices.get(0).getText(), itemPrice);
        assertEquals(cartNames.get(1).getText(), itemName1);
        assertEquals(cartPrices.get(1).getText(), itemPrice1);
        assertEquals(cartNames.get(2).getText(), itemName2);
        assertEquals(cartPrices.get(2).getText(), itemPrice2);
    }

    @Test
    public void checkRemoveItem() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
            assertEquals(productsPage.getAddCartMessage(position), "Add to cart");
            productsPage.addItemBucket(position);
            assertEquals(productsPage.getRemoveMessage(), "Remove");
            productsPage.removeItem(position);
            assertEquals(productsPage.getAddCartMessage(position), "Add to cart");
    }

    @Test
    public void checkSortingZA() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPage.getActiveOptionText(), "Name (A to Z)");
        productsPage.setDropdown("za");
        assertEquals(productsPage.getActiveOptionText(), "Name (Z to A)");
        productsPage.setDropdown("lohi");
        assertEquals(productsPage.getActiveOptionText(), "Price (low to high)");
        productsPage.setDropdown("hilo");
        assertEquals(productsPage.getActiveOptionText(), "Price (high to low)");
    }

    @Test
    public void logout() throws InterruptedException {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPage.getTitle(), "Products");
        productsPage.logoutBurger();
        assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
    }
}
