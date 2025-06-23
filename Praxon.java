package TestCase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.logging.Logger;

public class Praxon {
    private static final Logger logger = Logger.getLogger(Praxon.class.getName());

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver-win64/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);

        // ✅ Declare WebDriverWait here
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("https://www.bing.com");
            driver.findElement(By.name("q")).sendKeys("praxon technovation");
            driver.findElement(By.name("q")).submit();

            // Wait for and click first search result
            wait.until(ExpectedConditions.elementToBeClickable(By.className("tpmeta"))).click();

            // ✅ Switch to new tab
            String originalWindow = driver.getWindowHandle();
            wait.until(d -> driver.getWindowHandles().size() > 1);
            for (String handle : driver.getWindowHandles()) {
                if (!handle.equals(originalWindow)) {
                    driver.switchTo().window(handle);
                    break;
                }
            }

            // Navigate to contact
            wait.until(ExpectedConditions.elementToBeClickable(By.className("responsive-menu-toggle"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("menu-item-465"))).click();

            // Fill contact form
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("your-name"))).sendKeys("Paramveer Singh");
            driver.findElement(By.name("your-email")).sendKeys("param27singh27@gmail.com");
            driver.findElement(By.name("your-subject")).sendKeys("Selenium Testing");
            driver.findElement(By.name("your-message")).sendKeys("Hello Anand sir, on your call I wrote this message for my testing for selenium.");

            // Submit form
            driver.findElement(By.className("wpcf7-form-control")).submit();

            Thread.sleep(3000);
            System.out.println("Current URL: " + driver.getCurrentUrl());
            System.out.println("Page Title: " + driver.getTitle());
            System.out.println("Page Source Snippet:\n" + driver.getPageSource().substring(0, 1000));

        } catch (Exception e) {
            logger.severe("An error occurred: " + e.getMessage());
        } finally {
           driver.quit();
        }
    }
}
