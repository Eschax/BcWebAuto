package stepdefinitions;

import java.io.IOException;
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
import com.webautomation.pageobjectTugas.CartPageTugas;
import com.webautomation.pageobjectTugas.ConfrimationPageTugas;
import com.webautomation.pageobjectTugas.LandingPageTugas;
import com.webautomation.pageobjectTugas.OrderPageTugas;
import com.webautomation.pageobjectTugas.ProductListPageTugas;

import components.BaseTest;
import hook.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepdefinitionsImpl extends BaseTest {

    WebDriver driver;
    WebDriverWait wait;

    @Given("Buyer landing to ecommerce")
    public void landingPage() throws IOException{
        //setup driver
        driver = Hooks.initializeDriver();
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
    }

    //tugas
    @Given("Buyer landing to saucedemo")
    public void landingPageTugas(){
        //setup driver
        System.setProperty("webdriver.chrome.driver", "C:/Windows/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize(); // Maximize window
        driver.get("https://www.saucedemo.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Given("^Buyer login to the saucedemo username (.+) and password (.+)$")
    public void buyerLoginTugas(String username, String password) throws InterruptedException{
        LandingPageTugas landingPage = new LandingPageTugas(driver);
        landingPage.login(username, password);
    }

    @When("^Buyer add a saucedemo product (.+) to the cart$")
    public void addProductToCartTugas(String productname) throws InterruptedException{
        ProductListPageTugas productListPage = new ProductListPageTugas(driver);
        productListPage.addToCart(productname);
        driver.findElement(By.cssSelector(".shopping_cart_container a.shopping_cart_link")).click();

    }

    @And("^Buyer checkout saucedemo product (.+)$")
    public void checkoutProductTugas(String productname) throws InterruptedException{
        CartPageTugas cartPage = new CartPageTugas(driver);
        cartPage.GoToCheckout();
        Assert.assertTrue(cartPage.verifyCheckoutProduct(productname));
        cartPage.checkout();
    }

    @And("^Buyer insert (.+) (.+) (.+) for shipping$")
    public void placeOrderTugas(String firstName, String lastName, String postalCode) throws InterruptedException {
        OrderPageTugas orderPage = new OrderPageTugas(driver);
        orderPage.order(firstName, lastName, postalCode);
    }

    @And("^Buyer will see checkout overview (.+)$")
    public void CheckoutOverview(String succesCheckout) throws InterruptedException{
        ConfrimationPageTugas confirmationPage = new ConfrimationPageTugas(driver);
        confirmationPage.finish();
    }

    @Then("^Buyer will see message confirmation page (.+)$")
    public void messageConfirmation(String terimaKasih){
        ConfrimationPageTugas confirmationPage = new ConfrimationPageTugas(driver);
        String confirmationText = confirmationPage.verifyConfirmation();
        Assert.assertEquals(confirmationText, terimaKasih);

    
        driver.close();
    }
}

