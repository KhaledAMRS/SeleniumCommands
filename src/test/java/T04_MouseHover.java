import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class T04_MouseHover {

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
        driver.get("https://the-internet.herokuapp.com/hovers");

    }


    @Test
    public void findelements() throws InterruptedException {

        //1- Create object from Actions class in selenium
        //you should make sure that this is imported above >> import org.openqa.selenium.interactions.Actions;
        Actions action = new Actions(driver);

        //2- Select random profile then hover on the card then click on "View profile" link
        List<WebElement> avatars = driver.findElements(By.cssSelector("img[src=\"/img/avatar-blank.jpg\"]"));
        int count = avatars.size();
        // int min = 50;
        // int max = 100;
        // Generate random int value from 50 to 100
        // int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);

        int selectedUser =  (int)Math.floor(Math.random()*((count-1)-0+1)+0);
        System.out.println(selectedUser);
        action.moveToElement(avatars.get(selectedUser)).perform();
        Thread.sleep(2000);

        List<WebElement> profileLinks = driver.findElements(By.cssSelector("a[href*=\"/users/\"]"));
        profileLinks.get(selectedUser).click();

        /*
      Note: if you tried to click on "View profile" link without hovering on first profile card
      you will get this error
       org.openqa.selenium.ElementNotInteractableException: element not interactable
        */


    }

    @AfterMethod
    public void quitDriver() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

}
