package com.webautomation.locator;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HandleWindow {

    public static void main(String[] args) throws InterruptedException {
        /*
         * Get id tab/window.
         * move ke tab lain menggunakan id tab tersebut
         * tab1 = 10
         * tab2 = 20
         * tab3 = 40
         * 
         * current_tab = 10
         * 
         * call tab2.id
         * call tab3.id
         */

        System.setProperty("webdriver.chrome.driver", "C:/Windows/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://rahulshettyacademy.com/loginpagePractise/#");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.findElement(By.className("blinkingText")).click();
        Set<String> windows = driver.getWindowHandles();

        System.out.println("Ini adalah windows" + windows);

        

        Thread.sleep(5000);


        driver.quit();
    }
}
