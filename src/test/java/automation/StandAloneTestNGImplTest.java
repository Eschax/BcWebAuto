package automation;

import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.webautomation.pageobject.CartPage;
import com.webautomation.pageobject.ConfirmationPage;
import com.webautomation.pageobject.LandingPage;
import com.webautomation.pageobject.OrderPage;
import com.webautomation.pageobject.ProductListPage;

public class StandAloneTestNGImplTest {
    /*
     * Anotasi
     * dataprovider
     * running testng
     */
    
    public WebDriver driver;
    public WebDriverWait wait;
    
    @BeforeMethod
    public void setUp(){
        //setup driver
        System.setProperty("webdriver.chrome.driver", "C:/Windows/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize(); // Maximize window
        driver.get("https://rahulshettyacademy.com/client");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    @Test(dataProvider = "dataTestMapping")
    public void createOrder(HashMap<String, String> input) throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userEmail")));

        LandingPage landingPage = new LandingPage(driver);
        landingPage.login(input.get("useremail"), input.get("password"));

         // Tunggu hingga halaman produk keliatan
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

        //List Product
        String productName = "ZARA COAT 3";
        ProductListPage productListPage = new ProductListPage(driver);
        productListPage.addToCart(productName);

            driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

            //lanjut checkout
            CartPage cartPage = new CartPage(driver);
            cartPage.verifyCheckoutProduct(productName);    
            cartPage.checkout();

            //udah di checkout overview
            String destination = "Indonesia";

            OrderPage orderPage = new OrderPage(driver);
            orderPage.selectCountry(destination);
            orderPage.selectDestination(destination);
            orderPage.submit();

            //ini udah di page confirmasi
            ConfirmationPage confirmationPage = new ConfirmationPage(driver);
            String thanks = confirmationPage.getConfirmation();

            System.out.println("Buyer berhasil " + thanks);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @DataProvider
    public Object[][] dataTest(){
        return new Object[][]{
            {"aotomation@mailnesia.com", "jPF.TLurtbM@Yk5", "ZARA COAT 3"},
        };
    }

    //Mapping
    @DataProvider
    public Object[][] dataTestMapping(){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("useremail", "aotomation@mailnesia.com");
        map.put("password", "jPF.TLurtbM@Yk5");
        map.put("productname", "ZARA COAT 3");

        return new Object[][] {{map}};
    }
}
