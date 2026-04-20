import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import java.util.HashMap;

/*
Сделать цепочку из действий: наведение на профиль,
проверка имени, клик по ссылке, проверка, что нет 404 ошибки. Повторить
для каждого из профилей. Использовать класс Actions и
https://stackoverflow.com/questions/17293914/how-to-perform-mouseover-function
-in-selenium-webdriver-using-java
 */

public class HoversTest {

    @Test
    public void hover() {
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        WebDriver driver = new ChromeDriver(options);
        Actions actions = new Actions(driver);
        driver.get("https://the-internet.herokuapp.com/hovers");

        try {
            for (int i = 1; i <= 3; i++) {
                String avatarXpath = String.format("//div[@class='figure'][%d]", i);
                String nameXpath = String.format("//div[@class='figure'][%d]//h5", i);
                String linkXpath = String.format("//div[@class='figure'][%d]//a", i);

                WebElement avatar = driver.findElement(By.xpath(avatarXpath));
                WebElement link = driver.findElement(By.xpath(linkXpath));

                //наводимся на аватар
                actions.moveToElement(avatar).perform();

                //проверяем имя
                WebElement nameElement = driver.findElement(By.xpath(nameXpath));
                System.out.println("Профиль " + i + ": " + nameElement.getText());

                //кликаем по ссылке
                actions.moveToElement(link).click().perform();

                //возвращаемся назад
                driver.navigate().back();
            }
        } finally {
            driver.quit();
        }
    }
}
