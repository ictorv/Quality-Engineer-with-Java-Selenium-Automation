package testRunner;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@Test
@CucumberOptions(
    features = "src/test/resources/featurefile",
    glue = {"stepDefination", "hooks"},
    plugin = {
        "pretty",
        "html:target/cucumber-reports/report-testng.html", 
        "json:target/cucumber-reports/report-testng.json",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
    },
    monochrome = true
)

@Listeners({
    listenerUtility.CustomListener.class,
    listenerUtility.RetryTransformer.class
})
 
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
    // This class remains empty
}

