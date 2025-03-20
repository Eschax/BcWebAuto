package hook;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    public static WebDriver driver;

    //setup driver
    @Before
    public void setup() throws Exception {
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
    }

    //close driver
    @After
    public void tearDownAutomation(){
        if (driver != null) {
            driver.close();
        }
    }

    public static WebDriver initializeDriver(){
        System.out.println("driver adalah " + driver);
        return driver;
    }
}
