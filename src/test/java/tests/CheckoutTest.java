package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CheckoutTest extends BaseTest {
    //рефакторинг тестов, старые тоже оставил, но закомментил для себя, чтоб не забывать
    @DataProvider(name = "Тестовые данные для проверки негативных сценариев оформления заказа")
    public Object[][] invalidOrder() {
        return new Object[][] {
                {"", "Cerber", "673-201", "Error: First Name is required"},
                {"Michael", "", "673-201", "Error: Last Name is required"},
                {"Michael", "Cerber", "", "Error: Postal Code is required"}
        };
    }

    String itemName = "Sauce Labs Backpack";

    @Test (dataProvider = "Тестовые данные для проверки негативных сценариев оформления заказа",
            testName = "Проверка оформления заказа с невалидными данными",
            description = "Проверяем оформление заказа с пустыми полями: имя, фамилия, почтовый индекс")
    @Description("Тестовые данные для проверки негативных сценариев оформления заказа")
    @Epic("Regression")
    @Feature("Checkout in Sauce Demo")
    @Story("Checkout")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("SDTest-5064")
    @Issue("Test-7895")
    @Owner("Danil")
    public void negativeOrder(String firstName, String lastName, String postalCode, String errorMessage) {
        loginPage.open();
        loginPage.login(user, password);
        productsPage.addItemCart(itemName);
        productsPage.goToCart();
        cartPage.goCheckout();
        checkoutPage.makeOrder(firstName, lastName, postalCode);
        assertEquals(checkoutPage.getErrorMessage(), errorMessage);
    }

    @Test(testName = "Сделать заказ с валидными данными",
            description = "Сделать заказ с полностью заполненными валидными данными")
    @Description("Сделать заказ с валидными данными")
    @Epic("Regression")
    @Feature("Checkout in Sauce Demo")
    @Story("Checkout")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("SDTest-5065")
    @Issue("Test-7896")
    @Owner("Danil")
    public void placeOrder() {
        loginPage.open();
        loginPage.login(user, password);
        productsPage.addItemCart(itemName);
        productsPage.goToCart();
        cartPage.goCheckout();
        checkoutPage.makeOrder("Michael", "Cerber", "673-201");
        assertEquals(checkoutPage.getTitleCheckout(), "Checkout: Overview");
    }

//    @Test(
//            testName = "Сделать заказ empty firstName",
//            description = "Сделать заказ с пустым полем 'Имя'"
//    )
//    public void emptyFirstName() {
//        loginPage.open();
//        loginPage.login(user, password);
//        productsPage.addItemBucket(1);
//        productsPage.goToCart();
//        cartPage.goCheckout();
//        checkoutPage.makeOrder("", "Cerber", "673-201");
//        assertEquals(checkoutPage.getErrorMessage(), ("Error: First Name is required"));
//    }
//
//    @Test(
//            testName = "Сделать заказ empty lastName",
//            description = "Сделать заказ с пустым полем 'Фамилия'"
//    )
//    public void emptyLastName() {
//        loginPage.open();
//        loginPage.login(user, password);
//        productsPage.addItemBucket(1);
//        productsPage.goToCart();
//        cartPage.goCheckout();
//        checkoutPage.makeOrder("Michael", "", "673-201");
//        assertEquals(checkoutPage.getErrorMessage(), ("Error: Last Name is required"));
//    }
//
//    @Test(
//            testName = "Сделать заказ empty postalCode",
//            description = "Сделать заказ с пустым полем 'Postal Code'"
//    )
//    public void emptyPostalCode() {
//        loginPage.open();
//        loginPage.login(user, password);
//        productsPage.addItemBucket(1);
//        productsPage.goToCart();
//        cartPage.goCheckout();
//        checkoutPage.makeOrder("Michael", "Cerber", "");
//        assertEquals(checkoutPage.getErrorMessage(), ("Error: Postal Code is required"));
//    }
}
