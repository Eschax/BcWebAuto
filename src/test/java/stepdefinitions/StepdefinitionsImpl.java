package stepdefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.webautomation.pageobject.CartPage;
import com.webautomation.pageobject.ConfirmationPage;
import com.webautomation.pageobject.LandingPage;
import com.webautomation.pageobject.OrderPage;
import com.webautomation.pageobject.ProductListPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepdefinitionsImpl {

    public WebDriver driver;
    public WebDriverWait wait;
    // String productName = "ZARA COAT 3";
    // String destination = "Indonesia";


    @Given("Buyer landing to ecommerce")
    public void landingPage(){
        //setup driver
        System.setProperty("webdriver.chrome.driver", "C:/Windows/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize(); // Maximize window
        driver.get("https://rahulshettyacademy.com/client");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Given("^Buyer login to the website email (.+) and password (.+)$")
    public void buyerLogin(String email, String password){
        LandingPage landingPage = new LandingPage(driver);
        landingPage.login(email, password);
    }

    @When("^Buyer add a product (.+) to the cart$")
    public void addProductToCart(String productName) throws InterruptedException{
        ProductListPage productListPage = new ProductListPage(driver);
        productListPage.addToCart(productName);
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

    }
    @And("^Buyer checkout product (.+)$")
    public void checkoutProduct(String productName){
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.verifyCheckoutProduct(productName)); 
        cartPage.checkout();

    }
    @And("^Buyer place order (.+)$")
    public void placeOrder(String destination){
        OrderPage orderPage = new OrderPage(driver);
        orderPage.selectCountry(destination);
        orderPage.selectDestination(destination);
        orderPage.submit();
    }
    
    @Then("^Buyer will see message is displayed on confirmation page (.+)$")
    public void orderConfirmation(String succesCheckout){
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        String thanks = confirmationPage.getConfirmation();

        Assert.assertEquals(thanks, succesCheckout);
        driver.close();
    }
}
