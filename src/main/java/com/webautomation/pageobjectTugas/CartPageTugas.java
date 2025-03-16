package com.webautomation.pageobjectTugas;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.webautomation.abstractcomponent.AbstractComponent;

public class CartPageTugas extends AbstractComponent {
    WebDriver driver;

        public CartPageTugas(WebDriver driver){
            super(driver);
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }

        @FindBy (css = ".shopping_cart_container a.shopping_cart_link")
        WebElement GoToCartpage;

        @FindBy(css = ".cart_footer .btn_action.checkout_button")
        WebElement checkoutButton;

        @FindBy(css = ".cart_item .inventory_item_name")
        List<WebElement> listProduct;

        By cartPage = By.cssSelector(".shopping_cart_container a.shopping_cart_link");
        By rowButton = By.cssSelector(".cart_list");
        
        public void GoToCheckout(){
            visibilityOfElementLocated(cartPage);
            GoToCartpage.click();
        }

        public Boolean verifyCheckoutProduct(String productName){
            visibilityOfElementLocated(cartPage);
            Boolean match = listProduct.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
    
            return match;
        }

        public void checkout() throws InterruptedException{
            visibilityOfElementLocated(rowButton);
            Thread.sleep(2000);
            checkoutButton.click();
        }

}
