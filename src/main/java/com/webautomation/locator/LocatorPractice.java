package com.webautomation.locator;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocatorPractice {

    public static void main(String[] args) throws InterruptedException {
    System.setProperty("webdriver.chrome.driver", "C:/Windows/chromedriver.exe");

    ChromeOptions options = new ChromeOptions();

    options.addArguments("--disable-gpu"); // Disable GPU (recommended for headless)
    options.addArguments("--no-sandbox"); // Disable sandbox (optional)
    options.addArguments("--disable-dev-shm-usage"); // Avoid /dev/shm usage (for Docker/Linux)
    WebDriver driver = new ChromeDriver(options);

    driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    /*
     * select currency
     * condition ; dropdown
     */
    
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement staticDropdown = wait.until(
        ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_mainContent_DropDownListCurrency"))
    );
    
    Select dropdown = new Select (staticDropdown);
    System.out.println("All option" + dropdown.getAllSelectedOptions().size());
    System.out.println("first option" + dropdown.getFirstSelectedOption().getText());

    

    driver.close();
    }
}
