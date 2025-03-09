package com.webautomation.locator;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import java.util.Iterator;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TugasLocator {
    public static void main(String[] args) throws InterruptedException {
        // Set path to chromedriver.exe
        System.setProperty("webdriver.chrome.driver", "C:/Windows/chromedriver.exe");
        
        WebDriver driver = new ChromeDriver();

        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //radio Button
        List<WebElement> labels = driver.findElements(By.cssSelector("#radio-btn-example label"));

        for (WebElement label : labels) {
            String labelText = label.getText().trim();
            
            if (labelText.equals("Radio1")) { 
                WebElement radioInput = label.findElement(By.tagName("input"));
                radioInput.click();
                break;
            }
        }

        Thread.sleep(2000);

        //AutoComplete

        driver.findElement(By.xpath("(//*[@id='autocomplete'])[1]")).sendKeys("ind");

        List<WebElement> country = driver.findElements( By.cssSelector("li[class='ui-menu-item'] div"));

        for (WebElement webElement : country) {
            System.out.println("Ada Negara " + webElement.getText());
            if (webElement.getText().equals("Indonesia")) {
                webElement.click();
                break;
            }
        }

        Thread.sleep(2000);

        //Dropdown

        WebElement dropdown = driver.findElement(By.id("dropdown-class-example"));
        
        Select select = new Select(dropdown);

        select.selectByVisibleText("Option1");

        Thread.sleep(2000);

        //Checkbox

        WebElement checkbox = driver.findElement(By.id("checkBoxOption1"));
        checkbox.click();

        Thread.sleep(2000);

        //switch Window

        driver.findElement(By.id("openwindow")).click();
        Set<String> windows = driver.getWindowHandles();

        System.out.println("Ini adalah windowsnya" + windows);

        Iterator<String> iterator = windows.iterator();
        String parentId = iterator.next();
        String childId = iterator.next();

        Thread.sleep(2000);

        driver.switchTo().window(childId);

        Thread.sleep(2000);

        driver.switchTo().window(parentId);

        //switch Tab

        driver.findElement(By.id("opentab")).click();
        Set<String> TabTest = driver.getWindowHandles();

        Iterator<String> TAB = TabTest.iterator();
        String TabAwal = TAB.next();
        String TabBaru = TAB.next();

        Thread.sleep(2000);

        driver.switchTo().window(TabBaru);
        Thread.sleep(2000);

        driver.switchTo().window(TabAwal);
        Thread.sleep(4000);

        //Alert

        WebElement username = driver.findElement(By.id("name"));

        username.click();

        username.sendKeys("Trescha");

        driver.findElement(By.id("alertbtn")).click();

        System.out.println(driver.switchTo().alert().getText());

         // 2. Tunggu alert muncul dan tangkap objek Alert
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Thread.sleep(2000);

        // 3. Klik OK pada alert
        alert.accept();
        Thread.sleep(2000);


        Thread.sleep(2000);
        driver.quit();
            }
        }
    


