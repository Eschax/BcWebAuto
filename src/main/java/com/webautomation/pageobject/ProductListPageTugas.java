package com.webautomation.pageobject;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.webautomation.abstractcomponent.AbstractComponent;

public class ProductListPageTugas extends AbstractComponent {
    WebDriver driver;
    WebElement product;

    public ProductListPageTugas(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[data-test='inventory-item']")
    List<WebElement> listProducts;

    By cartButton = By.cssSelector("button.btn_primary.btn_inventory");
    By titleProduct = By.cssSelector(".inventory_item_name");
    By listElement = By.cssSelector(".inventory_item");

    public  List<WebElement> getProductList(){
        return listProducts;
    }

    public WebElement getProductByName(String productName){
        WebElement product = getProductList().stream().filter(prod -> 
        prod.findElement(titleProduct).getText().equals(productName)).findFirst().orElse(null);

        return product;
    }

    public void addToCart(String productName) throws InterruptedException{
        visibilityOfElementLocated(listElement);
        product = getProductByName(productName);
        product.findElement(cartButton).click();
    }
}


