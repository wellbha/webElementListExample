package webelementlist;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AddToCartButtonWithList {
    public static WebDriver driver;
    SoftAssert softAssert = new SoftAssert();
    @BeforeMethod //run before every method
    public void openingBrowser(){
        //initializing the chrome driver and passing the url
        //pre conditions
        System.setProperty("webdriver.chrome.driver","src\\BrowserDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }
    @AfterMethod //run after every method
    public void closingBrowser(){
        driver.quit();
    }

    @Test (priority = 1)
    public void verifyAddToCardButtonPresence(){
        driver.get("https://demo.nopcommerce.com/");
        List<WebElement> elements = driver.findElements(By.cssSelector(".item-box .product-box-add-to-cart-button"));
        //System.out.println("Number of elements:" +elements.size());

        for (WebElement tag:elements) {
            //System.out.println(tag.getAttribute("value"));
            softAssert.assertEquals(tag.getAttribute("value"),"Add to cart");
        }
        softAssert.assertAll();
    }
    @Test(priority = 2)
    public void verifyEuroSignInProduct(){
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.linkText("Jewelry")).click();
        Select currency = new Select(driver.findElement(By.id("customerCurrency")));
        currency.selectByValue("https://demo.nopcommerce.com/changecurrency/6?returnurl=%2Fjewelry");
        List<WebElement> elements = driver.findElements(By.cssSelector(".item-box .actual-price"));
        //System.out.println("Number of elements:" +elements.size());

        for (WebElement price : elements) {
            //System.out.println(price.getText().charAt(0));
            softAssert.assertEquals(price.getText().charAt(0),'Ђ');
        }
        softAssert.assertAll();

    }

}
