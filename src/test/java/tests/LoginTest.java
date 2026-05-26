package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {
    //рефакторинг тестов, старые тоже оставил, но закомментил для себя, чтоб не забывать
    @DataProvider(name = "Тестовые данные для проверки негативных сценариев авторизации пользователя")
    public Object[][] invalidLogin() {
        return new Object[][] {
                {"", password, "Epic sadface: Username is required"},
                {user, "", "Epic sadface: Password is required"},
                {"invalid", "invalid", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test (dataProvider = "Тестовые данные для проверки негативных сценариев авторизации пользователя",
            testName = "Проверка авторизации с невалидными данными",
            description = "Проверяем авторизацию с пустыми полями и невалидными данными")
    @Description ("Проверка авторизации с невалидными данными")
    @Epic("Regression")
    @Feature("Login in Sauce Demo")
    @Story("Negative Login")
    @Severity(SeverityLevel.BLOCKER)
    @Link("https://saucedemo.com/")
    @TmsLink("SDTest-5060")
    @Issue("Test-7891")
    @Flaky
    @Owner("Danil")
    public void negativeLogin(String name, String password, String errorMessage) {
        loginPage.open();
        loginPage.login(name, password);
        assertEquals(loginPage.getErrorMessage(), errorMessage);
    }

    @Test (testName = "Авторизация с валидными данными",
            description = "Авторизуемся с валидными данными под обычного пользователя",
            groups = {"smoke"})
    @Description ("Авторизация с валидными данными")
    @Epic("Regression")
    @Feature("Login in Sauce Demo")
    @Story("Positive Login")
    @Severity(SeverityLevel.BLOCKER)
    @Link("https://saucedemo.com/")
    @TmsLink("SDTest-5061")
    @Issue("Test-7892")
    @Owner("Danil")
    public void checkLoginWithPositiveCred() {
        loginPage.open();
        loginPage.login(user, password);
        assertEquals(productsPage.getTitle(), "Products");
    }

//    @Test
//    public void checkLoginWithEmptyPassword() {
//        loginPage.open();
//        loginPage.login(user, "");
//        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required");
//    }
//
//    @Test
//    public void checkLoginWithEmptyLogin() {
//        loginPage.open();
//        loginPage.login("", password);
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
