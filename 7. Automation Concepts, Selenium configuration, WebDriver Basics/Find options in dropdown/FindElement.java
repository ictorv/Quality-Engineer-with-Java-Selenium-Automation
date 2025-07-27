import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.*;  

public class FindElement {      // DO NOT CHANGE THE CLASS NAME
	  
	// Use the below declared variables 
	public static WebDriver driver;  
	public static WebElement element; 
	public static List<WebElement> optionList;
	String baseUrl = "https://webapps.tekstac.com/RentaCar/";
	
	public WebDriver createDriver() {    // DO NOT CHANGE THE METHOD SIGNATURE 
	    
	    /*Invoke the getDriver() method from the DriverSetup File */
	    /*Store it in static variable 'driver', navigate to baseUrl andreturn it*/
	    driver=DriverSetup.getDriver();
	    driver.get(baseUrl);
	   
	    return driver;
	}
	
	public WebElement getElementById() {    // DO NOT CHANGE THE METHOD SIGNATURE
       
       ///Find the drop down element using id 'cartype' and return the webelement.
        element= driver.findElement(By.id("cartype"));
         
       return element;
	}
	
	public List<WebElement> getOptions(WebElement dropdownElement) {    // DO NOT CHANGE THE METHOD SIGNATURE
       
       // get the number of options available in the dropdown by calling the getOptions() method of the Select class
       // store it in list 'optionList' and return it
        Select select = new Select(dropdownElement);
        optionList=select.getOptions();
        return optionList;
	}

	public static void main(String[] args) {    // DO NOT CHANGE THE METHOD SIGNATURE
	    
	     FindElement findElement = new FindElement();
	    
	    findElement.createDriver();
	    WebElement element=findElement.getElementById();
	    List<WebElement> options = findElement.getOptions(element);

	    System.out.println("The option in dropdown are:");
	    for(WebElement e:options)
	    	System.out.println(e.getText());
	}

}

