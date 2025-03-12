package com.webautomation.scenario;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class StandAloneTest {

    public static void main(String[] args) throws InterruptedException {
        /*
         * 1. Implement e2e test for checkout
         * 2. Wait != Thread
         * 3. Stream
         */

        /*
         * Scenario automation
         * 1. Buyer Login
         * 2. Buyer checkout product
         * 3. Verifikasi thanks page
         */

        System.setProperty("webdriver.chrome.driver", "C:/Windows/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize(); // Maximize window
        driver.get("https://rahulshettyacademy.com/client");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userEmail")));

         // Scenario Login
        driver.findElement(By.id("userEmail")).sendKeys("aotomation@mailnesia.com");
        driver.findElement(By.id("userPassword")).sendKeys("jPF.TLurtbM@Yk5");

        driver.findElement(By.className("login-btn")).click();

         // Tunggu hingga halaman produk keliatan
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

        //List Product
        List<WebElement> listProduct =  driver.findElements(By.cssSelector(".mb-3"));

        String productName = "ZARA COAT 3";

        //cari produk yang sesuai
        WebElement product = listProduct.stream().filter(prod ->
        prod.findElement(By.cssSelector("b")).getText().equals(productName))
        .findFirst()
        .orElse(null);

        product.findElement(By.xpath("//div[@class='card-body']//child::button//child::i[@class='fa fa-shopping-cart']")).click();

            System.out.println("list product" + product);

            //klik cartnya

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));

            Thread.sleep(2000);

            driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

            Thread.sleep(2000);

            //lanjut checkout

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".totalRow button")));

            driver.findElement(By.cssSelector(".totalRow button")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[placeholder = 'Select Country']")));

            Actions action = new Actions(driver);
            //masukin nama negara
            action.sendKeys(driver.findElement(By.cssSelector("[placeholder = 'Select Country']")), "Ind").build().perform();

            String destination = "Indonesia";

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ng-star-inserted']")));

            //lokasi negara ada disini
            List<WebElement> country = driver.findElements(By.xpath("//span[@class='ng-star-inserted']"));

            System.out.println("list country" + country);

            //pake stream buat ngambil negara yang sesuai
            WebElement countryDestination = country.stream().filter(dest ->
            dest.getText().endsWith(destination)).findFirst().orElse(null);
            
        

            countryDestination.click();

            driver.findElement(By.cssSelector(".action__submit")).click();

            //ini udah di page confirmasi
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("hero-primary")));

            String thanks = driver.findElement(By.className("hero-primary")).getText();

            System.out.println("Buyer berhasil " + thanks);
            
            Thread.sleep(2000);
            driver.quit();
    }
}
