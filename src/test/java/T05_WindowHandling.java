import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class T05_WindowHandling {

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
        driver.get("https://the-internet.herokuapp.com/login");

    }


    @Test(priority = 1)
    public void HandlingTwoTabs() throws InterruptedException {

        driver.findElement(By.cssSelector("a[href=\"http://elementalselenium.com/\"]")).click();

        Thread.sleep(2000);

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

        driver.switchTo().window(tabs.get(1));

        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());

        Assert.assertEquals(driver.getCurrentUrl(),"http://elementalselenium.com/");

        driver.close();
        driver.switchTo().window(tabs.get(0));

        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());


    }

    @Test(priority = 2)
    public void HandlingThreeTabs() throws InterruptedException {

        driver.findElement(By.cssSelector("a[href=\"http://elementalselenium.com/\"]")).click();

        Thread.sleep(2000);
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());
        Assert.assertEquals(driver.getCurrentUrl(),"http://elementalselenium.com/");


        driver.findElement(By.cssSelector("a[href=\"https://saucelabs.com/\"]")).click();

    }



    @AfterMethod
    public void quitDriver() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

}
