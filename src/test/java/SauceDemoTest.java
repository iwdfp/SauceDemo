import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;

/*
a. Залогиниться
b. Добавить товар в корзину
c. Перейти в корзину
d. Проверить (assertEquals) стоимость товара и его имя в корзине
 */

public class SauceDemoTest {

    @Test
    public void sauce() {
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
        driver.get("https://www.saucedemo.com/");

        try {
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            String itemName = driver.findElement(By.className("inventory_item_name")).getText();
            String itemPrice = driver.findElement(By.className("inventory_item_price")).getText();

            driver.findElement(By.xpath("//button[text()='Add to cart']")).click();

            driver.findElement(By.className("shopping_cart_link")).click();

            String cartItemName = driver.findElement(By.className("inventory_item_name")).getText();
            String cartItemPrice = driver.findElement(By.className("inventory_item_price")).getText();

            Assert.assertEquals(cartItemName, itemName);
            Assert.assertEquals(cartItemPrice, itemPrice);

            System.out.println("Товар: " + cartItemName + ", цена: " + cartItemPrice);
        } finally {
            driver.quit();
        }
    }
}
