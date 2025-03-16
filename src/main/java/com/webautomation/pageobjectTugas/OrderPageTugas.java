package com.webautomation.pageobjectTugas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.webautomation.abstractcomponent.AbstractComponent;

public class OrderPageTugas extends AbstractComponent {
    WebDriver driver;   

    public OrderPageTugas(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "first-name")
    WebElement firstName;

    @FindBy(id = "last-name")
    WebElement lastName;

    @FindBy(id = "postal-code")
    WebElement postalCode;

    @FindBy(css = ".checkout_buttons .btn_primary.cart_button")
    WebElement continueButton;

    By orderPage = By.cssSelector(".checkout_info");

    public void order(String firstName, String lastName, String postalCode) throws InterruptedException{
        visibilityOfElementLocated(orderPage);
        this.firstName.sendKeys(firstName);
        this.lastName.sendKeys(lastName);
        this.postalCode.sendKeys(postalCode);
        Thread.sleep(2000);
        continueButton.click();
    }
}
