package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
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
public class TestRunners {
    

}
