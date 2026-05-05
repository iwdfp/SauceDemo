package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    /*
    1. Описываем элементы для взаимодействия
    2. Описываем методы взаимодействия с этими элементами
     */

    private final By USERNAME_FIELD = By.xpath("//*[@data-test='username']");
    private final By PASSWORD_FIELD = By.xpath("//*[@data-test='password']");
    private final By LOGIN_BUTTON = By.xpath("//*[@data-test='login-button']");
    private final By ERROR_MESSAGE = By.xpath("//*[@data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    //открываем главную страницу
    public void open() {
        driver.get("https://saucedemo.com/");
    }
    //логинимся под обычным юзером
    public void login(String name, String password) {
        driver.findElement(USERNAME_FIELD).sendKeys(name);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }
    //ошибки при пустых полях
    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }
}
