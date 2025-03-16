package com.webautomation.pageobjectTugas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.webautomation.abstractcomponent.AbstractComponent;

public class ConfrimationPageTugas extends AbstractComponent {
    WebDriver driver;

    public ConfrimationPageTugas(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".summary_info .btn_action.cart_button")
    WebElement finishButton;

    By confirmationPage = By.cssSelector(".summary_info");
    
    public void finish() throws InterruptedException {
        visibilityOfElementLocated(confirmationPage);
        Thread.sleep(2000);
        finishButton.click();
    }

}
