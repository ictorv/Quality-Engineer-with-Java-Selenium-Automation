package mypack;     //Do not change the package name         

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class GetCustomerDetails {  //Do not change the class name
	
	//Use the below declared variable
	public WebDriver driver;
	public RespositoryParser parser;
	
	//Constructors are already given. If required, you can add more code into it but do NOT remove the existing code.
    public GetCustomerDetails(){}
    
	public GetCustomerDetails(WebDriver driver) throws Exception{
		//Assign driver and parser
		this.driver=driver;
		parser=new RespositoryParser("ObjectRepo.properties");
		
	}
	
	
	
	public WebElement getFirstName() {
		//Find and return the text displayed against FirstName using Xpath 
		WebElement firstName=driver.findElement(By.xpath(parser.getObjectLocator("firstname")));
		return firstName;
		
	}
	
	public WebElement getLastName() {
		//Find and return the text displayed against LastName using Xpath
		WebElement lastName=driver.findElement(By.xpath(parser.getObjectLocator("lastname")));
		return lastName;
	}

	public WebElement getUsername() {
		//Find and return the text displayed against Username using Xpath
		WebElement userName=driver.findElement(By.xpath(parser.getObjectLocator("username")));
		return userName;
	}	 	  	  		 	     	     	      	 	
	
	public WebElement getCity() {

		WebElement city=driver.findElement(By.xpath(parser.getObjectLocator("city")));
		return city;
	
	}
	
	
}