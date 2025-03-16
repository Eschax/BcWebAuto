package com.webautomation.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.webautomation.abstractcomponent.AbstractComponent;

public class OrderPage extends AbstractComponent{
    WebDriver driver;

    public OrderPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
        
        @FindBy(css = "[placeholder = 'Select Country']")
        WebElement selectCountry;

        @FindBy(xpath = "//span[@class='ng-star-inserted']")
        List<WebElement> country;

        @FindBy(css = ".action__submit")
        WebElement submitButton;

        By elementSelectCountry = By.cssSelector("[placeholder = 'Select Country']");
        By elementListCountry = By.xpath("//span[@class='ng-star-inserted']");

        public void selectCountry(String country){
            visibilityOfElementLocated(elementSelectCountry);
            Actions action = new Actions(driver);
            action.sendKeys(selectCountry, country).build().perform();
        }

        public void selectDestination(String destination){
            visibilityOfElementLocated(elementListCountry);
            WebElement countryDestination = country.stream().filter(dest ->
            dest.getText().equalsIgnoreCase(destination)).findFirst().orElse(null);
            countryDestination.click();
        }



        public void submit(){
            submitButton.click();
        }

    }