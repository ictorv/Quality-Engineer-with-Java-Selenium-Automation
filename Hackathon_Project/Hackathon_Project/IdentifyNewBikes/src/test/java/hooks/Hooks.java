package hooks;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;

import factory.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import pageObject.HomePage;


public class Hooks {

	 WebDriver driver;
	 Properties p;
     HomePage home;
     
 
	@Before
    public void setup() throws IOException
    {
    	driver=BaseClass.initilizeBrowser();
    	p=BaseClass.getProperties();
    	driver.get(p.getProperty("appURL"));
    	driver.manage().window().maximize();
    			
	}
		
    @After
    public void tearDown(Scenario scenario) {
    	
    	if (scenario.isFailed()) {
            System.out.println("❌ Scenario failed: " + scenario.getName());
        } else {
            System.out.println("✅ Scenario passed: " + scenario.getName());
        }
 
        if (driver != null) {
            driver.quit();
        }
    }
    
    @AfterStep
    public void addScreenshotToAllure(Scenario scenario) {
        if (scenario.isFailed() && driver != null) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Failed Screenshot", new ByteArrayInputStream(screenshot));
        }
    }
}

   

