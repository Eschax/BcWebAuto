package components;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BaseTest {

    public WebDriver driver;

    public WebDriver initializeDriver() throws IOException {
        /*
         * Check global.properties and get brower
         * if (browser == "chrome"){
         *      driver = chrome;
         * }else if(browser == "firefox") {
         *      driver = firefox;
         * }else{
         *      driver = edge
         * }
         */

        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("D:\\Bootcamp QA\\WebAutomation\\src\\main\\resources\\GlobalData.properties");
        
        properties.load(fileInputStream);
        String browserName = properties.getProperty("browser");
    
        System.out.println("Browser name is: " + browserName);

        if (browserName.equals("chrome")){
            //driver chrome
            System.setProperty("webdriver.chrome.driver", "C:/Windows/chromedriver.exe");
            driver = new ChromeDriver();
        } else {
            //driver firefox
            System.setProperty("webdriver.gecko.driver", "C:/Windows/geckodriver.exe");
            FirefoxOptions options = new FirefoxOptions();
            options.addPreference("marionette", true);
            options.setBinary("C:/Program Files/Mozilla Firefox/firefox.exe");
            // options.addArguments("--headless");
            driver = new FirefoxDriver(options);
        }
        driver.get("https://rahulshettyacademy.com/client");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        // driver.manage().window().maximize();
        return driver;

    }

}
