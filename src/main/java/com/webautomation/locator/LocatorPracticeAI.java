package com.webautomation.locator;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LocatorPracticeAI {

    public static void main(String[] args) throws InterruptedException {
        // Set path to chromedriver.exe
        System.setProperty("webdriver.chrome.driver", "C:/Windows/chromedriver.exe");

        // Configure ChromeOptions for headless mode (optional)
        // ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless"); // Enable headless mode
        // options.addArguments("--disable-gpu"); // Disable GPU (recommended for headless)
        // options.addArguments("--no-sandbox"); // Disable sandbox (optional)
        // options.addArguments("--disable-dev-shm-usage"); // Avoid /dev/shm usage (for Docker/Linux)

        // Initialize WebDriver with ChromeOptions
        WebDriver driver = new ChromeDriver();

            // Maximize browser window
            driver.manage().window().maximize();

            driver.get("https://rahulshettyacademy.com/dropdownsPractise/");

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement staticDropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_mainContent_DropDownListCurrency"))
            );

            // Select dropdownnya
            Select dropdown = new Select(staticDropdown);

            // Print the number of options and the first selected option
            System.out.println("Number of options: " + dropdown.getOptions().size());
            System.out.println("First selected option: " + dropdown.getFirstSelectedOption().getText());

            dropdown.selectByContainsVisibleText("AED");
            System.out.println("mata uangnya adalah " + dropdown.getFirstSelectedOption().getText());

            dropdown.selectByValue("USD");

            Thread.sleep(1000);

            dropdown.selectByIndex(1);

            Thread.sleep(1000);

            /*
             * handle dynamic dropdown
             */
            driver.findElement(By.id("divpaxinfo")).click();

            Thread.sleep(1000);
    
            /*
             * menambahkan passenger
             */

        for (int i = 0; i < 5; i++) {
            driver.findElement(By.id("hrefIncAdt")).click();
            }

            Thread.sleep(1000);

            //child
            for (int i = 0; i < 3; i++) {
                driver.findElement(By.id("hrefIncChd")).click();
            }

            Thread.sleep(1000);
            
            //Decrease adult
            for (int i = 0; i < 3; i++) {
                driver.findElement(By.id("hrefDecAdt")).click();
            }
            Thread.sleep(3000);

            driver.findElement(By.id("btnclosepaxoption")).click();

        Thread.sleep(3000);


        /*
         * scenario select from
         * Delhi = //div[@id='dropdownGroup1']//child::div[@class='dropdownDiv']//child::ul[1]//child::li//child::a[@value='DEL']
         */

        /*
         * kode
         */
        driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();;
        // // WebElement countryDelhi = driver.findElement(By.xpath("//div[@id=\'dropdownGroup1\']//div[@class=\'dropdownDiv\']//ul[1]//li//a[@value=\'DEL\']"));
        
        List<WebElement> options = driver.findElements(By.xpath("//div[@id='dropdownGroup1']//child::div[@class='dropdownDiv']//child::ul[1]//child::li"));
        // System.out.println("ini adalah options" + options);

        for (WebElement element : options){
            // System.out.println("List Country" + element.getText());
            if (element.getText().equals("Delhi (DEL)")){
                element.click();
                break;
            }
        }

        /*
         * Arrival City
         */

        List<WebElement> arrivalCity = driver.findElements(By.xpath("(//div[@id='dropdownGroup1']//child::div[@class='dropdownDiv']//child::ul[1])[2]//child::li"));
        
        for (WebElement element : arrivalCity){
            System.out.println("list country" + element.getText());
            if (element.getText().equals("Chennai (MAA)")) {
                element.click();
                break;
            }
        }

        Thread.sleep(3000);

        //Handle suggestion
        driver.findElement(By.xpath("(//*[@id='autosuggest'])[1]")).sendKeys("ind");

        Thread.sleep(3000);

        List<WebElement> country = driver.findElements( By.cssSelector("li[class='ui-menu-item'] a"));

        for (WebElement webElement : country) {
            System.out.println("Ini adalah negara " + webElement.getText());
            if (webElement.getText().equals("Indonesia")) {
                webElement.click();
                break;
            }
        }

         //Hande radio button
        driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_0")).click();

         //Handle checkbox
        driver.findElement(By.id("ctl00_mainContent_chk_friendsandfamily")).click();

        Thread.sleep(3000);
        
        driver.quit();
        }
    }
