package tugas;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.webautomation.pageobjectTugas.CartPageTugas;
import com.webautomation.pageobjectTugas.ConfrimationPageTugas;
import com.webautomation.pageobjectTugas.LandingPageTugas;
import com.webautomation.pageobjectTugas.OrderPageTugas;
import com.webautomation.pageobjectTugas.ProductListPageTugas;

import java.util.HashMap;

public class tugasE2Etest {

    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:/Windows/chromedriver.exe");
        //setup driver
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test(dataProvider = "getData")
    public void tugas(HashMap<String, String> input) throws InterruptedException {
        
        LandingPageTugas landingPage = new LandingPageTugas(driver);
        landingPage.login(input.get("username"), input.get("password"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".inventory_item")));

        String productName = "Sauce Labs Backpack";
        ProductListPageTugas productListPage = new ProductListPageTugas(driver);
        productListPage.addToCart(productName);

    //lanjut checkout
    CartPageTugas cartPage = new CartPageTugas(driver);
    cartPage.GoToCheckout();
    Assert.assertTrue(cartPage.verifyCheckoutProduct(productName));
    cartPage.checkout();

    //udah di checkout overview
    String FirstName = "John";
    String LastName = "Wick";
    String PostalCode = "12345";

    OrderPageTugas orderPage = new OrderPageTugas(driver);
    orderPage.order(FirstName, LastName, PostalCode);

    ConfrimationPageTugas confirmationPage = new ConfrimationPageTugas(driver);
    confirmationPage.finish();
    
    String confirmationText = confirmationPage.verifyConfirmation();
    Assert.assertEquals(confirmationText, "Thank you for your order!");

    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @DataProvider
    public Object[][] getData() {
        HashMap<String, String> data1 = new HashMap<String, String>();
        data1.put("username", "standard_user");
        data1.put("password", "secret_sauce");
        data1.put("productname", "Sauce Labs Backpack");
        data1.put("firstname", "John");
        data1.put("lastname", "Wick");
        data1.put("postalcode", "12345");

        return new Object[][] {
            {data1}
        };
    }

}
