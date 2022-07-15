import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class T08_WindowHandling {

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
    //1- click on facebook
    driver.findElement(By.cssSelector("a[href=\"http://www.facebook.com/nopCommerce\"]")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

    //2- get window list inside array
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        System.out.println(tabs.size());

        //3- switch from tab 0 to tab 1
        driver.switchTo().window(tabs.get(1));
        System.out.println("tab 1:  " +driver.getCurrentUrl());

        //4- switch from tab 1 to tab 0
        driver.switchTo().window(tabs.get(0));
        System.out.println("tab 0:  " + driver.getCurrentUrl());


        //Difference between driver.quit() and driver.close()
        driver.close();


        // reload
        tabs = new ArrayList<>(driver.getWindowHandles());

    }

    @AfterMethod
    public void quitDriver() throws InterruptedException {
//        Thread.sleep(3000);
//        driver.quit();
    }

}
