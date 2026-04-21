import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import java.util.HashMap;

/*
Проверить содержимое нескольких (3-5) ячеек
таблицы. Использовать xpath типа //table//tr[1]//td[1] - получение первой
ячейки из первого ряда первой таблицы и так далее
 */

public class SortableDataTablesTest {

    @Test
    public void table() {
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
        driver.get("https://the-internet.herokuapp.com/tables");

        try {
            WebElement element1 = driver.findElement(By.xpath("//table//tr[1]//td[1]"));
            String text1 = element1.getText();
            System.out.println("Данные из таблицы 1, строка 1, ячейка 1: " + text1);

            WebElement element2 = driver.findElement(By.xpath("//table//tr[1]//td[2]"));
            String text2 = element2.getText();
            System.out.println("Данные из таблицы 1, строка 1, ячейка 2: " + text2);

            WebElement element3 = driver.findElement(By.xpath("//table[2]//tr[1]//td[5]"));
            String text3 = element3.getText();
            System.out.println("Данные из таблицы 2, строка 1, ячейка 5: " + text3);

            WebElement element4 = driver.findElement(By.xpath("//table[2]//tr[3]//td[4]"));
            String text4 = element4.getText();
            System.out.println("Данные из таблицы 2, строка 3, ячейка 4: " + text4);
        } finally {
            driver.quit();
        }
    }
}
