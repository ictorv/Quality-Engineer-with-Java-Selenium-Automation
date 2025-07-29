package testRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/featurefile/TC_010.feature",
    glue = {"stepDefination", "hooks"},
    plugin = {
        "pretty",
        "html:target/cucumber-reports/report-junit.html", // Separate report
        "json:target/cucumber-reports/report-junit.json",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
    },
    monochrome = true
)
public class JunitTestRunner {
    // This class remains empty
}