package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefinitions","hook"},
        plugin = {
                    "pretty",                                       // Pretty console output
                    "html:target/cucumber-reports.html",            // HTML report
                    "json:target/cucumber.json",                    // JSON report
                    "junit:target/cucumber.xml"                     // JUnit XML report
                }  
)
public class TestRunnersTesNG extends AbstractTestNGCucumberTests{
    

}
