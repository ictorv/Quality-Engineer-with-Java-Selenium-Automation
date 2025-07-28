import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class RentACar { // Do not change 
    
    // Use the below declarations
    static WebDriver driver;
    String baseUrl = "https://webapps.tekstac.com/RentaCar/";
   
	static int textBoxcount,checkBoxCount;  

    static List<WebElement> textBoxList,checkBoxList; 
	static By textBoxXpath, checkBoxXpath;

 
	public WebDriver createDriver() {   // Do not change the method signature
	    
		// Invoke the getDriver() method from the DriverSetup File 
	    // Store it in static variable 'driver', navigate and return it
	    driver= DriverSetup.getDriver();
	    driver.get(baseUrl);
	    return driver;
	}
	
	public List<WebElement> getTextBoxCount() {  // Do not change the method signature
	    
	    
	    // Identify the text fields by customized xpath and print how many text boxes in the page using List
		
		// Assign your locator to 'textBoxXpath' variable and use that to locate the element
		// Get the list of all text boxes and store that in a list 'textBoxList' ( Use customized xpath locator to identify the elements )

		// Get the size of the 'textBoxList' (total text box count) and store it in a static variable 'textBoxcount' then print it
		
		// Return the list 'textBoxList'
		textBoxXpath=By.xpath("//input[@type='text' or @type = 'spinbutton' or @type = 'combobox']");
		textBoxList=driver.findElements(textBoxXpath);
		textBoxcount=textBoxList.size();
		System.out.println(textBoxcount);
		return textBoxList;
	}	 	  	  		 	     	     	      	 	
	
	
	public List<WebElement> getCheckBoxCount() {  // Do not change the method signature
	    
	    
	    // Identify the check boxes by customized xpath and print how many text boxes in the page using List
		
		// Assign your locator to 'checkBoxXpath' variable and use that to locate the element
		// Get the list of all check boxes and store that in a list 'checkBoxList' ( Use customized xpath locator to identify the elements )

		// Get the size of the 'checkBoxList' (total check box count) and store it in a static variable 'checkBoxCount' then print it
		
		// Return the list 'checkBoxList'
        checkBoxXpath=By.xpath("//input[@type='checkbox']");
        checkBoxList=driver.findElements(checkBoxXpath);
        checkBoxCount=checkBoxList.size();
	    return checkBoxList;
	}

	public void closeBrowser() {  // Do not change the method signature
	     
		// close the browser
		driver.close();
	}


	public static void main(String[] args){
		
		RentACar printoptions=new RentACar();  
		
		// Add your code here
		printoptions.createDriver();
		printoptions.getTextBoxCount();
		printoptions.getCheckBoxCount();
		
	}	 	  	  		 	     	     	      	 	

}