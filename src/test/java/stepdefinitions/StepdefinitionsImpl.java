package stepdefinitions;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.webautomation.pageobject.CartPage;
import com.webautomation.pageobject.ConfirmationPage;
import com.webautomation.pageobject.LandingPage;
import com.webautomation.pageobject.OrderPage;
import com.webautomation.pageobject.ProductListPage;

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

}