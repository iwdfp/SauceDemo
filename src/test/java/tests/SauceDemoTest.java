package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/*
a. Залогиниться
b. Добавить товар в корзину
c. Перейти в корзину
d. Проверить (assertEquals) стоимость товара и его имя в корзине
 */

public class SauceDemoTest extends BaseTest {

    String itemName = "Sauce Labs Backpack";
    String itemName1 = "Sauce Labs Bike Light";

    @Test
    @Description("Проверить соответствие имени и цены добавленного товара")
    @Epic("Regression")
    @Feature("SauceTests in Sauce Demo")
    @Story("Tests")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("SDTest-5066")
    @Issue("Test-7897")
    @Owner("Danil")
    public void checkAddBucketItem() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addItemCart(itemName);
        productsPage.goToCart();
        assertEquals(cartPage.getCartItemName(itemName), itemName);
        assertEquals(cartPage.getCartItemPrice(itemName), "$29.99");
    }

    @Test
    @Description("Проверить соответствие имени и цены добавленных товаров")
    @Epic("Regression")
    @Feature("SauceTests in Sauce Demo")
    @Story("Tests")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("SDTest-5067")
    @Issue("Test-7898")
    @Owner("Danil")
    public void checkAddBucketSomeItems() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addItemCart(itemName);
        productsPage.addItemCart(itemName1);
        productsPage.goToCart();
        assertEquals(cartPage.getCartItemName(itemName), itemName);
        assertEquals(cartPage.getCartItemPrice(itemName), "$29.99");
        assertEquals(cartPage.getCartItemName(itemName1), itemName1);
        assertEquals(cartPage.getCartItemPrice(itemName1), "$9.99");
    }

    @Test
    @Description("Проверить нажатие кнопки Remove")
    @Epic("Regression")
    @Feature("SauceTests in Sauce Demo")
    @Story("Tests")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("SDTest-5068")
    @Issue("Test-7899")
    @Owner("Danil")
    public void checkRemoveItem() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addItemCart(itemName);
        productsPage.removeItemCart(itemName);
        assertEquals(productsPage.getButtonText(itemName), "Add to cart");
    }

//    @Test
//    @Description("Проверка выбора сортировки")
//    @Epic("Regression")
//    @Feature("SauceTests in Sauce Demo")
//    @Story("Tests")
//    @Severity(SeverityLevel.CRITICAL)
//    @TmsLink("SDTest-5069")
//    @Issue("Test-7900")
//    @Owner("Danil")
//    public void checkSortingZA() {
//        loginPage.open();
//        loginPage.login("standard_user", "secret_sauce");
//        assertEquals(productsPage.getActiveOptionText(), "Name (A to Z)");
//        productsPage.setDropdown("za");
//        assertEquals(productsPage.getActiveOptionText(), "Name (Z to A)");
//        productsPage.setDropdown("lohi");
//        assertEquals(productsPage.getActiveOptionText(), "Price (low to high)");
//        productsPage.setDropdown("hilo");
//        assertEquals(productsPage.getActiveOptionText(), "Price (high to low)");
//    }

    @Test
    @Description("Проверка выхода из аккаунта через бургер меню")
    @Epic("Regression")
    @Feature("SauceTests in Sauce Demo")
    @Story("Tests")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("SDTest-5069")
    @Issue("Test-7900")
    @Owner("Danil")
    public void logout() throws InterruptedException {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPage.getTitle(), "Products");
        productsPage.logoutBurger();
        assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
    }
}
