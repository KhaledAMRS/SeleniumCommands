import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class T07_ExplicitWait {

    WebDriver driver;

    @BeforeMethod
    public void openBrowser() {
        //1- Define Bridge
        String key = "webdriver.chrome.driver";
        String value = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";
        System.setProperty(key, value);

        // 2- Create new object from chromedriver
        driver = new ChromeDriver();

        // 3- Configuration
        //3.1- Maximize browser
        driver.manage().window().maximize();

        //3.2- Set implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        // 4- Navigate to website
        driver.get("https://demo.nopcommerce.com/");
    }


    @Test(priority = 1)
    public void HandlingTwoTabs() throws InterruptedException {
    //1- click on wishlist button for this product "HTC One M8 Android L 5.0 Lollipop"
List<WebElement> wishlistbtns = driver.findElements(By.cssSelector("button[class=\"button-2 add-to-wishlist-button\"]"));
    //[0, 1, 2, 3]
     wishlistbtns.get(2).click();

     //2- click on x button
     driver.findElement(By.cssSelector("span[title=\"Close\"]")).click();

    //3- wait until green line is disappeared using explicit wait
        //3.1- Create new object from WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //3.2- wait until element is dissappeared from UI Page
//        WebElement successBar = driver.findElement(By.cssSelector("div[class=\"bar-notification success\"]"));
//        wait.until(ExpectedConditions.invisibilityOf(successBar));
//another method
        WebElement wishlist = driver.findElement(By.cssSelector("span[class=\"wishlist-label\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(wishlist));


     //4- go to wishlist page
     driver.findElement(By.cssSelector("span[class=\"wishlist-label\"]")).click();


     // 5- get qty value and assert it's larger than zero
    String count =   driver.findElement(By.className("qty-input")).getAttribute("value");

    int countint=    Integer.parseInt(count);

        Assert.assertTrue(countint>0);
    }

    @AfterMethod
    public void quitDriver() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

}
