package com.selenium.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Dimension;

public class ContactPageValues { // DO NOT CHANGE THE CLASS NAME
	
	// Use the below declared variable 
	private WebDriver driver;
	private WebElement element;
	

    //Constructors are already given. If required, you can add more code into it but do NOT remove the existing code.
    public ContactPageValues(){}
    
	public ContactPageValues(WebDriver driver){
		this.driver= driver;
		PageFactory.initElements(driver,this);
	}
	
	// Implement code here
	
	@FindBy(id="nickname")
	WebElement nameInput;
	
	@FindBy(id="contact")
	WebElement contactInput;
	
	@FindBy(id="company")
	WebElement companyInput;
	
	@FindBy(id="city")
	WebElement cityInput;
	
	@FindBy(id="country")
	WebElement countryInput;
	
	@FindBy(id="type")
	WebElement typeInput;
	
	@FindBy(id="add")
	WebElement addBtn;
	
	@FindBy(xpath="//*[@id='result']/table/tbody/tr[2]/td[1]")
	WebElement nameResult;
	
	@FindBy(xpath="//*[@id='result']/table/tbody/tr[2]/td[2]")
	WebElement contactResult;
	
	@FindBy(xpath="//*[@id='result']/table/tbody/tr[2]/td[3]")
	WebElement companyResult;
	
	@FindBy(xpath="//*[@id='result']/table/tbody/tr[2]/td[4]")
	WebElement cityResult;
	
	@FindBy(xpath="//*[@id='result']/table/tbody/tr[2]/td[5]")
	WebElement countryResult;
	
	@FindBy(xpath="//*[@id='result']/table/tbody/tr[2]/td[6]")
	WebElement typeResult;
	
	public void setNickName(String nickName) {
		// Locate the WebElement 'NickName' using specific locator
        // Use sendKeys method to fill the nickname field
	    nameInput.sendKeys(nickName);
	}
	
	public void setContactName(String contact) {
		// Locate the WebElement 'Contact Nme' using specific locator
        // Use sendKeys method to fill the contact field
        contactInput.sendKeys(contact);
	}
	
	public void setCompany(String company) {
	    
	// Locate the WebElement 'Company' using specific locator
    // Use sendKeys method to fill the company field
        companyInput.sendKeys(company);
	}	 	  	    	 	    	     	 	
	
	public void setCity(String city) {
	// Locate the WebElement 'City' using specific locator
    // Use sendKeys method to fill the city field
	    cityInput.sendKeys(city);
	}
	
	public void setCountry(String country) {
	// Locate the WebElement 'Country' using specific locator
    // Use sendKeys method to fill the country field
	    countryInput.sendKeys(country);
	}
	
	public void setType(String type) {
		// Locate the WebElement 'Type' using specific locator
        // Use sendKeys method to fill the type field
	    typeInput.sendKeys(type);
	    
	}
	
	public void clickAddButton() {
		// Locate the WebElement 'Add' using specific locator
        // Use click method to submit the form.
	    addBtn.click();
	    
	}
	
	
	public String getNickName() {// Do not change the method signature
	// Locate the WebElement corresponding to the 'Nickname' displayed after form. 
	// Get the WebElement, store it in a variable and return the text as String.
	   return nameResult.getText();
	}	 	  	    	 	    	     	 	
	
	public String getContactName() {// Do not change the method signature
		// Locate the WebElement corresponding to the 'Contact' displayed after form. 
	    // Get the WebElement, store it in a variable and return the text as String.
		return contactResult.getText();
	}
	
	public String getCompany() {// Do not change the method signature
		// Locate the WebElement corresponding to the 'Company' displayed after form. 
	    // Get the WebElement, store it in a variable and return the text as String.
		return companyResult.getText();
	}
	
	public String getCity() {// Do not change the method signature
		// Locate the WebElement corresponding to the 'City' displayed after form. 
	    // Get the WebElement, store it in a variable and return the text as String.
		return cityResult.getText();
	}
	
	public String getCountry() {// Do not change the method signature
		// Locate the WebElement corresponding to the 'Country' displayed after form. 
	    // Get the WebElement, store it in a variable and return the text as String.
		return countryResult.getText();
	}
	
	public String getType() {// Do not change the method signature
		// Locate the WebElement corresponding to the 'Type' displayed after form. 
	    // Get the WebElement, store it in a variable and return the text as String.
		return typeResult.getText();
	}
	
}
