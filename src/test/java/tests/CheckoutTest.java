package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CheckoutTest extends BaseTest {

    @Test
    public void placeOrder() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addItemBucket(1);
        productsPage.goToCart();
        cartPage.goCheckout();
        checkoutPage.makeOrder("Michael", "Cerber", "673-201");
        assertEquals(checkoutPage.getTitleCheckout(), "Checkout: Overview");
    }

    @Test
    public void emptyFirstName() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addItemBucket(1);
        productsPage.goToCart();
        cartPage.goCheckout();
        checkoutPage.makeOrder("", "Cerber", "673-201");
        assertEquals(checkoutPage.getErrorMessage(), ("Error: First Name is required"));
    }

    @Test
    public void emptyLastName() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addItemBucket(1);
        productsPage.goToCart();
        cartPage.goCheckout();
        checkoutPage.makeOrder("Michael", "", "673-201");
        assertEquals(checkoutPage.getErrorMessage(), ("Error: Last Name is required"));
    }

    @Test
    public void emptyPostalCode() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addItemBucket(1);
        productsPage.goToCart();
        cartPage.goCheckout();
        checkoutPage.makeOrder("Michael", "Cerber", "");
        assertEquals(checkoutPage.getErrorMessage(), ("Error: Postal Code is required"));
    }
}
