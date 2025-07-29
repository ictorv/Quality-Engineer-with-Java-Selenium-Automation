package testObjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utilityClass.WaitUtility;


public class Rediff_RegistrationPage {
	
	public  WebDriver driver;
	
	// List to store country names from the dropdown
	public  List<String> countrys;
	
	// Variables to store expected and actual selected country values
	public  String expSelectedCountry,actSelectedCountry;
	
	// Define WebElements using PageFactory annotation for Rediff registration fields
	@FindBy(xpath = "//a[@title ='Create new Rediffmail account']")
	public WebElement newAcc;
	
	@FindBy(xpath = "//input[@placeholder='Enter your full name']")
	public  WebElement name;
	
	@FindBy(xpath = "//input[@placeholder='Enter Rediffmail ID']")
	public  WebElement email;
	
	@FindBy(xpath = "//*[@id='recommend_text']/table/tbody/tr/td[1]")
	public List<WebElement> emailSuggestions;
	
	@FindBy(xpath = "//input[@value='Check availability']")
	public  WebElement avail;
	
	@FindBy(xpath = "//div[@id='check_availability']/div/span")
	public WebElement availMsg;

	@FindBy(id = "newpasswd")
	public  WebElement password;
	
	@FindBy(id = "newpasswd1")
	public WebElement retypePassword;
	
	@FindBy(xpath = "//input[@type='checkbox']")
	public  WebElement checkBox;
	
	@FindBy(xpath = "//select[@class='day']")
	public  WebElement Date;
    
	@FindBy(xpath = "//select[@class='middle month']")
	public  WebElement Month;
    
	@FindBy(xpath = "//select[@class='year']")
	public  WebElement Year;
    
	@FindBy(id = "country")
	public  WebElement Country;
	// Select object for interacting with country dropdown
	public Select country;

    public int countryCount;
   
    // Constructor: Initialize all elements using PageFactory
	public Rediff_RegistrationPage(WebDriver driver){
		
		this.driver=driver;
		
		PageFactory.initElements(driver, this);	
	}
	
	// Click on the "Create new account" link
	public void clickCreateNewAccountLink() {
		newAcc.click();
	}
	
	// Enter full name in the form
	public void enterName(String Name) {
		WaitUtility.waitForElementToBeClickable(driver,name);
		name.sendKeys(Name);
	}
	
	// Enter email ID with a wait until the field is clickable
	public void enterEmail(String mail) {
		WaitUtility.waitForElementToBeClickable(driver,email);
		email.sendKeys(mail);
	}
	
	// Click the "Check availability" button and return the status message
	public String checkAvailability(){
		WaitUtility.waitForElementToBeClickable(driver,avail);
		avail.click();
		System.out.println(availMsg.getText());
		return availMsg.getText();
	}
	
	// Select a random email suggestion if the entered email is unavailable
	public void selectAutoSuggesstion() {
        Random random = new Random();
        int select =0;
        try{
            select = random.nextInt(0, emailSuggestions.size()-1);
            System.out.println("selected option is "+select+1);
            emailSuggestions.get(select).click();
        }
        catch(IndexOutOfBoundsException e) {
        	System.out.println(e);
        }
	}
	
	// Enter password and confirm it
	public void enterPassword(String pass) {
		password.sendKeys(pass);
		retypePassword.sendKeys(pass);
	}
	
	// Check the agreement checkbox using JavaScript executor
	public void clickCheckBox() {
		WaitUtility.waitForElementToBeClickable(driver,checkBox);
	     JavascriptExecutor js = (JavascriptExecutor) driver;
	     js.executeScript("arguments[0].click()",checkBox);
	}
	
	// Select date of birth from dropdowns
	public void enterDateOfBirth(String dob) {
		String Dob[] = dob.split("-");
		
		Select date = new Select(Date);
		Select month = new Select(Month);
		Select year = new Select(Year);
		
		date.selectByVisibleText(Dob[0]);
		month.selectByVisibleText(Dob[1]);
		year.selectByVisibleText(Dob[2]);
	}
	
	// Fetch and print all country names from the dropdown
	public List<String> getCountrys() {
		
		System.out.println(" All the available country names in the Drop Down:");
		countrys =new ArrayList<>();
		country = new Select(Country);
		for(WebElement x:country.getOptions()) {
			this.countrys.add(x.getText());
			System.out.println(x.getText());
			countryCount++;
		}
		return countrys;
	}
	
	// Print the total count of countries in the dropdown
	public void printCountryCount() {
		System.out.println("the total count of countries :"+countryCount);
	}
	
	// Select a country and store the expected selection
	public void selectCountry(String countryName) {
		country.selectByVisibleText(countryName);
		expSelectedCountry =countryName;
	}
	
	// Retrieve and print the currently selected country from the dropdown
	public void getSelectedCountry(){
		actSelectedCountry = country.getFirstSelectedOption().getText();
		System.out.println("Selected Country Name: "+actSelectedCountry);
	}
	
	// Validate whether the selected country matches the expected value
	public boolean ValidateCountryDropDown() {
        if(actSelectedCountry.equals(expSelectedCountry)) {
        	System.out.println("Selected country name is the same as the expected country name");
        	return true;
        }
        else {
        	System.out.println("Selected country name is NOT the expected country name");
        	return false;
        }
	}

}
