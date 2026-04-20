import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;

/*
Взять все элементы дроп-дауна и проверить их наличие.
Выбрать первый, проверить, что он выбран, выбрать второй, проверить, что
он выбран
Локатор: By.id(“dropdown”)
 */

public class DropdownTest {

    @Test
    public void dropdown() {
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
        driver.get("https://the-internet.herokuapp.com/dropdown");

        try {
            driver.findElement(By.id("dropdown")).click();
            WebElement option1 = driver.findElement(By.xpath("//option[@value='1']"));
            option1.click();
            Assert.assertTrue(option1.isSelected());

            driver.findElement(By.id("dropdown")).click();
            WebElement option2 = driver.findElement(By.xpath("//option[@value='2']"));
            option2.click();
            Assert.assertTrue(option2.isSelected());
        } finally {
            driver.quit();
        }
    }
}
