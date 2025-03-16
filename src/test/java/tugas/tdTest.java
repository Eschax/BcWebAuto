package tugas;

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

import com.webautomation.pageobjectTugas.LandingPageTugas;
import com.webautomation.pageobjectTugas.ProductListPageTugas;

public class tdTest {

    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/Windows/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test(dataProvider = "getData")
    public void tugas(HashMap<String, String> input) throws Exception {
        try {
            // Login
            LandingPageTugas landingPage = new LandingPageTugas(driver);
            landingPage.login(input.get("username"), input.get("password"));

            // Tunggu hingga halaman inventory dimuat
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".inventory_item_name")));

            // Tambahkan produk ke keranjang
            String productName = "Sauce Labs Backpack";
            ProductListPageTugas productListPage = new ProductListPageTugas(driver);
            productListPage.addToCart(productName);

            // Lanjutkan ke checkout
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".shopping_cart_container a.shopping_cart_link"))).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart_list")));
            driver.findElement(By.cssSelector(".cart_footer .btn_action.checkout_button")).click();

            // Isi informasi checkout
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".checkout_info")));
            driver.findElement(By.id("first-name")).sendKeys("John");
            driver.findElement(By.id("last-name")).sendKeys("Wick");
            driver.findElement(By.id("postal-code")).sendKeys("12345");
            driver.findElement(By.cssSelector(".checkout_buttons .btn_primary.cart_button")).click();

            // Selesaikan checkout
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".summary_info")));
            driver.findElement(By.cssSelector(".summary_info .btn_action.cart_button")).click();

            // Verifikasi pesanan berhasil
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".complete-header")));
            String thanks = driver.findElement(By.cssSelector(".complete-header")).getText();
            System.out.println("Buyer berhasil " + thanks);
        } catch (Exception e) {
            System.err.println("Test gagal: " + e.getMessage());
            throw e; // Melempar exception agar test dianggap gagal
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @DataProvider
    public Object[][] getData() {
        HashMap<String, String> data1 = new HashMap<>();
        data1.put("username", "standard_user");
        data1.put("password", "secret_sauce");
        data1.put("productname", "Sauce Labs Backpack");

        return new Object[][] {
            {data1}
        };
    }
}