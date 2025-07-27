//DO NOT CHANGE THE GIVEN TEMPLATE CODE.

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.firefox.FirefoxOptions;

import org.openqa.selenium.firefox.FirefoxProfile;

import org.openqa.selenium.firefox.FirefoxBinary;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.en.Given;

import cucumber.api.java.en.Then;

import cucumber.api.java.en.When;

import cucumber.api.java.After;

import org.junit.*;

public class StepDefinition {

    WebDriver driver;

    String verifyTitle = null;

    String verifyName = null;

    // DO NOT CHANGE GIVEN SET OF CODE TO CREATE 'DRIVER'

    @Given("^Start firefox browser and open the application$")

    public void SetUp() {

        driver = DriverSetup.getDriver();

        driver.get("https://webapps.tekstac.com/AddressBookCucumber/");

    }

    // Do not change Given set of Code Templates

    @When("^Text \"(.*)\" is present in h1 tag$")

    public void checkTitle(String Title) {

        // Fill in the required codes

        WebElement title = driver.findElement(By.tagName("h1"));

        verifyTitle = title.getText().trim();

        Assert.assertEquals(Title, verifyTitle);

    }

    // Do not change Given set of Code Templates

    @When("^address details are given Name as \"(.*)\" Type as \"(.*)\" Address as \"(.*)\" Email as \"(.*)\" Mobile Number as \"(.*)\" Location as \"(.*)\"$")

    public void addAddressBookDetail(String Name, String Type, String Address, String Email, String Mobile_Number,
            String Location) {

        // Fill in the required codes

        driver.findElement(By.id("name")).sendKeys(Name);

        driver.findElement(By.xpath("//input[@value='" + Type + "']")).click();

        driver.findElement(By.id("address")).sendKeys(Address);

        driver.findElement(By.id("email")).sendKeys(Email);

        driver.findElement(By.id("mobile")).sendKeys(Mobile_Number);

        Select select = new Select(driver.findElement(By.id("location")));

        select.selectByValue(Location);

    }

    // Do not change Given set of Code Templates

    @Then("^click add and display \"(.*)\"$")

    public void addAddress(String Name) {

        // Fill in the required codes

        driver.findElement(By.xpath("//input[@type='submit']")).click();

        String verifyName = driver.findElement(By.xpath("//div[@id='result']/table/tbody/tr[2]/td[1]")).getText();

        Assert.assertEquals(Name, verifyName);

    }

    // Do not change Given set of Code Templates

    @After

    public void tearDown() {

        if (driver != null)

        {

            driver.quit();

        }

    }

}
