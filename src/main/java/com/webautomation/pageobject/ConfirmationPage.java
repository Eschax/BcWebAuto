package com.webautomation.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.webautomation.abstractcomponent.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{
    WebDriver driver;   

    public ConfirmationPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "hero-primary")
    WebElement ConfirmationPage;

    By elementHeroText = By.className("hero-primary");

    public String getConfirmation(){
        visibilityOfElementLocated(elementHeroText);
        return ConfirmationPage.getText();
    }
}
