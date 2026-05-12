package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CartTest extends BaseTest {

    @Test(testName = "Проверка кнопки 'Continue Shopping' на странице 'Корзина'",
            description = "Нажать кнопку 'Continue Shopping' на странице 'Корзина'")
    @Description("Проверка кнопки 'Continue Shopping' на странице 'Корзина'")
    @Epic("Regression")
    @Feature("Cart in Sauce Demo")
    @Story("Carts")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("SDTest-5062")
    @Issue("Test-7893")
    @Owner("Danil")
    public void btnCntnShp() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.goToCart();
        cartPage.backShopping();
        assertEquals(productsPage.getTitle(), "Products");
    }

    @Test(testName = "Проверка кнопки 'Checkout' на странице 'Корзина'",
            description = "Нажать кнопку 'Checkout' на странице 'Корзина'")
    @Description("Проверка кнопки 'Checkout' на странице 'Корзина'")
    @Epic("Regression")
    @Feature("Cart in Sauce Demo")
    @Story("Carts")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("SDTest-5063")
    @Issue("Test-7894")
    @Owner("Danil")
    public void btnCancelCheckout() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.goToCart();
        cartPage.goCheckout();
        checkoutPage.backToCart();
        assertEquals(cartPage.getTitleCart(), "Your Cart");
    }
}
