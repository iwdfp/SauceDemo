package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {
    //рефакторинг тестов, старые тоже оставил, но закомментил для себя, чтоб не забывать
    @DataProvider(name = "Тестовые данные для проверки негативных сценариев авторизации пользователя")
    public Object[][] invalidLogin() {
        return new Object[][] {
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"invalid", "invalid", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test (
            dataProvider = "Тестовые данные для проверки негативных сценариев авторизации пользователя",
            testName = "Проверка авторизации с невалидными данными",
            description = "Проверяем авторизацию с пустыми полями и невалидными данными"
    )
    public void negativeLogin(String name, String password, String errorMessage) {
        loginPage.open();
        loginPage.login(name, password);
        assertEquals(loginPage.getErrorMessage(), errorMessage);
    }

    @Test (
            testName = "Авторизация с валидными данными",
            description = "Авторизуемся с валидными данными под обычного пользователя",
            groups = {"smoke"}
    )
    public void checkLoginWithPositiveCred() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPage.getTitle(), "Products");
    }

//    @Test
//    public void checkLoginWithEmptyPassword() {
//        loginPage.open();
//        loginPage.login("standard_user", "");
//        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required");
//    }
//
//    @Test
//    public void checkLoginWithEmptyLogin() {
//        loginPage.open();
//        loginPage.login("", "secret_sauce");
//        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required");
//    }
//
//    @Test
//    public void checkLoginWithNegativeCred() {
//        loginPage.open();
//        loginPage.login("name", "pass");
//        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username and password " +
//                "do not match any user in this service");
//    }
}
