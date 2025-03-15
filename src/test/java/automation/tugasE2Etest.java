package automation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.webautomation.pageobject.LandingPageTugas;
import com.webautomation.pageobject.ProductListPageTugas;

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

        Thread.sleep(2000);

        String productName = "Sauce Labs Backpack";
        ProductListPageTugas productListPage = new ProductListPageTugas(driver);
        productListPage.addToCart(productName);

        //login success

    Thread.sleep(2000);

    //lanjut checkout

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".shopping_cart_container a.shopping_cart_link"))).click();

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart_list")));

    driver.findElement(By.cssSelector(".cart_footer .btn_action.checkout_button")).click();

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".checkout_info")));

    Thread.sleep(2000);

    driver.findElement(By.id("first-name")).sendKeys("John");
    driver.findElement(By.id("last-name")).sendKeys("Wick");
    driver.findElement(By.id("postal-code")).sendKeys("12345");

    Thread.sleep(2000);

    driver.findElement(By.cssSelector(".checkout_buttons .btn_primary.cart_button")).click();

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".summary_info")));

    driver.findElement(By.cssSelector(".summary_info .btn_action.cart_button")).click();

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".complete-header")));

    String thanks = driver.findElement(By.cssSelector(".complete-header")).getText();

    System.out.println("Buyer berhasil " + thanks);

        Thread.sleep(2000);

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

        return new Object[][] {
            {data1}
        };
    }

}
