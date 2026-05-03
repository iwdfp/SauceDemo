package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CartTest extends BaseTest {

    @Test(
            testName = "Проверка кнопки 'Continue Shopping' на странице 'Корзина'",
            description = "Нажать кнопку 'Continue Shopping' на странице 'Корзина'"
    )
    public void btnCntnShp() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.goToCart();
        cartPage.backShopping();
        assertEquals(productsPage.getTitle(), "Products");
    }

    @Test(
            testName = "Проверка кнопки 'Checkout' на странице 'Корзина'",
            description = "Нажать кнопку 'Checkout' на странице 'Корзина'"
    )
    public void btnCancelCheckout() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.goToCart();
        cartPage.goCheckout();
        checkoutPage.backToCart();
        assertEquals(cartPage.getTitleCart(), "Your Cart");
    }
}
