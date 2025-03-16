package com.webautomation.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.webautomation.abstractcomponent.AbstractComponent;

public class CartPage extends AbstractComponent{
    WebDriver driver;

    public CartPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //define CssSelector

    @FindBy(css = ".totalRow button")
    WebElement checkoutButton;

    @FindBy(css = ".cartSection h3")
    List<WebElement> listProducts;

    By rowButton = By.cssSelector(".totalRow button");

    public void checkout(){
        visibilityOfElementLocated(rowButton);
        checkoutButton.click();
    }
    public Boolean verifyCheckoutProduct(String productName){
        visibilityOfElementLocated(rowButton);
        Boolean match = listProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));

        return match;
    }
}
