import org.openqa.selenium.By;
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

public class T04_MouseHoverAgin {

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


    @Test
    public void findelements() throws InterruptedException {

        Actions actions=new Actions(driver);
        List<WebElement> mainLinks = driver.findElements(By.xpath("//ul[@class=\"top-menu notmobile\"]/li"));
//        System.out.println(mainLinks.get(3).getText());
        int count = mainLinks.size();
        System.out.println("count is "+count);
        int min = 0;
        int max = count-1;
        int selectedCategory = (int)Math.floor(Math.random()*(max-min+1)+min);
        actions.moveToElement(mainLinks.get(selectedCategory)).perform();
        String selectedCategoryText = mainLinks.get(selectedCategory).getText();
        System.out.println("to know which category is selected:   " +selectedCategoryText);
        Thread.sleep(2000);

        selectedCategory = selectedCategory+1;
        String locator = "(//ul[@class='top-menu notmobile']//ul)["+selectedCategory+"]/li";
        List<WebElement> subCategoryLinks = driver.findElements(By.xpath(locator));

        // minimize implicit wait to not waste time if it's empty
        driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);

        String selectedSubCategoryText;
        if(!subCategoryLinks.isEmpty()) {
            int subCategoryCount = subCategoryLinks.size();
            System.out.println("profile links " + subCategoryCount);
            System.out.println(subCategoryLinks.get(0).getText());
            System.out.println(subCategoryLinks.get(1).getText());
            System.out.println(subCategoryLinks.get(2).getText());
            int selectedSubCategory = (int) Math.floor(Math.random() * (max - min + 1) + min);
            selectedSubCategoryText = subCategoryLinks.get(selectedSubCategory).getText();
            subCategoryLinks.get(selectedSubCategory).click();
            // Do your assertion on selected sub category
        }
        else
        {
            mainLinks.get(selectedCategory).click();
            // Do your assertion on selected main category
        }

        // reset it to the initial value
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

    }

    @AfterMethod
    public void quitDriver() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

}
