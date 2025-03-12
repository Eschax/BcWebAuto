package com.webautomation.scenario;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class tugasE2E {

    public static void main(String[] args) throws InterruptedException {
        
        //https://www.saucedemo.com/

        System.setProperty("webdriver.chrome.driver", "C:/Windows/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));

                driver.findElement(By.id("user-name")).sendKeys("standard_user");
                driver.findElement(By.id("password")).sendKeys("secret_sauce");

                driver.findElement(By.id("login-button")).click();
                

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#menu_button_container")));

        System.out.println("Login Success");

        Thread.sleep(2000);

        //login success
        List<WebElement> listProduct = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
        By.cssSelector("[data-test='inventory-item']")
            )
        );
        
        String productName = "Sauce Labs Backpack";
        
    listProduct.stream().filter(item -> 
        item.findElement(By.cssSelector(".inventory_item_label .inventory_item_name"))
                                .getText()
                                .equals(productName))
                                .findFirst()
                                .ifPresentOrElse(
        item -> {
            // "Add to Cart" nya pake cssSelector yang spesifik
            item.findElement(By.cssSelector("button.btn_primary.btn_inventory")).click();
            System.out.println("Produk '" + productName + "' berhasil ditambahkan ke keranjang");
        },
        () -> {
            System.out.println("Barang '" + productName + "' tidak ditemukan");
        }
    );

    Thread.sleep(2000);

    //lanjut checkout

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".shopping_cart_container a.shopping_cart_link"))).click();

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart_list")));

    driver.findElement(By.cssSelector(".cart_footer .btn_action.checkout_button")).click();

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".checkout_info")));

    Thread.sleep(2000);

    driver.findElement(By.id("first-name")).sendKeys("John");
    driver.findElement(By.id("last-name")).sendKeys("Wick");
    driver.findElement(By.id("postal-code")).sendKeys("12345");

    Thread.sleep(2000);

    driver.findElement(By.cssSelector(".checkout_buttons .btn_primary.cart_button")).click();

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".summary_info")));

    driver.findElement(By.cssSelector(".summary_info .btn_action.cart_button")).click();

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".complete-header")));

    String thanks = driver.findElement(By.cssSelector(".complete-header")).getText();

    System.out.println("Buyer berhasil " + thanks);

        Thread.sleep(2000);

        driver.quit();

    }
}
