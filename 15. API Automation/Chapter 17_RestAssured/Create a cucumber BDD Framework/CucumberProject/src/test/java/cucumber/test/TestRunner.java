package cucumber.test;

import org.junit.runner.RunWith;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
//cucumber.test.TestRunner
@RunWith(Cucumber.class)
@CucumberOptions(
    features = "C:\\Users\\2403620\\OneDrive - Cognizant\\Desktop\\Java\\CucumberProject\\features",
//    glue = "cucumber.test",
//    plugin = {"pretty", "html:target/cucumber-report.html"},
//    monochrome = true
    tags = "@api" 
)

public class TestRunner {

@BeforeClass
@Parameters({"mode"})
public void setExecutionMode(String mode) {
String tag = mode.equalsIgnoreCase("Api") ? "@api" : "@web";
System.setProperty("cucumber.filter.tags", tag);

}

}
