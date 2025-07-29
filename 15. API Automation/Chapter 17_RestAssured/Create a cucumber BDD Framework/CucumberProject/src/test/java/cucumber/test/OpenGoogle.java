package cucumber.test;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class OpenGoogle {
	WebDriver driver;
@Given("I open the Google homepage")
public void openGoogleHomePage() {
    System.out.println("Opening Google");
    driver = new ChromeDriver();
    driver.get("https://www.google.com");
}

@Then("the page title should be {string}")
public void verifyPageTitle(String string) {
   System.out.println("Verifying Title");
   String actualTitle = driver.getTitle();
   assertEquals(actualTitle, string);
   driver.quit();
}
}