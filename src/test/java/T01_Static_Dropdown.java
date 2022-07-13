import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class T01_Static_Dropdown {

//    Static dropdown list:	dropdowns having tagName "Select"
//    Dynamic dropdown list:	dropdowns having different tagName other than "Select"

    WebDriver driver;

    @BeforeMethod
    public void openBrowser()
    {
        //1- Define Bridge
        String key = "webdriver.chrome.driver";
        String value = System.getProperty("user.dir")+"\\src\\main\\resources\\chromedriver.exe";
        System.setProperty(key,value);

        // 2- Create new object from chromedriver
        driver = new ChromeDriver();

        // 3- Configuration
        //3.1- Maximize browser
        driver.manage().window().maximize();

        //3.2- Set implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        // 4- Navigate to website
        driver.get("https://the-internet.herokuapp.com/dropdown");

    }


    @Test
    public void StaticDropdown() throws InterruptedException {

        // 1- Define webEelement for the list
        WebElement list = driver.findElement(By.id("dropdown"));

        // 2- Create new object from Select class in selenium
        //you should make sure about this >> import org.openqa.selenium.support.ui.Select;
        Select droplist = new Select(list);

        //3- Select options using 3 methods
        //3.1- SelectByIndex
        Thread.sleep(2000);
        droplist.selectByIndex(1);

        Thread.sleep(2000);
        droplist.selectByIndex(2);

        //3.2- SelectByValue
        //Note SelectByValue input value is String but SelectByIndex is Integer
        Thread.sleep(2000);
        droplist.selectByValue("1");

        Thread.sleep(2000);
        droplist.selectByValue("2");

        //3.3- SelectByVisibleText
        Thread.sleep(2000);
        droplist.selectByVisibleText("Option 1");

        Thread.sleep(2000);
        droplist.selectByVisibleText("Option 2");

    }

    @AfterMethod
    public void quitDriver() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }


}
