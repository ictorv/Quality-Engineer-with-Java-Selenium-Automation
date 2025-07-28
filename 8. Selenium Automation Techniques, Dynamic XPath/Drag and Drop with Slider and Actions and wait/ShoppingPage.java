import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.NoAlertPresentException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Alert;

public class ShoppingPage {	//Do not change the class name


	  //Use the below declared variable
    private static WebDriver driver;
    private String baseUrl = "https://webapps.tekstac.com/OnlineShopping/";
	
	public ShoppingPage() {}
	public ShoppingPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public WebDriver createDriver()
	{
	   //Create driver,store it in static variable driver, navigate to baseUrl and return it
	   driver=DriverSetup.getDriver();
	   driver.get(baseUrl);
		return driver;
	}

    public void setItemName(String name) throws Exception {  //Do not change the method signature
			
		// Implement the Drag and Drop code here
		// Locate the source web element that needs to be dragged. 
		// Choose the source web element based on the item 'name'
		// Locate the target web element that needs to be dropped. 
		// Creating object of Actions class to build composite actions
		// Performing the drag and drop action 
    
		try{
		    WebElement drag=driver.findElement(By.xpath("//*[@id='"+name+"']"));
            WebElement drop=driver.findElement(By.id("droppable"));
            Actions action=new Actions(driver);
            action.dragAndDrop(drag,drop).perform();
		}catch(Exception e){
		    System.out.println("Invalid action");
		}
	}
	
	public void setItemQuantity(String quantity) throws Exception { //Do not change the method signature
			
		//Creating object of Actions class to build composite actions
		//Locate the slider web element
		//Move the slider based on the quantity. 
				
		//For Example

           // You can use the below methods to move the slider
           // * moveToElement(WebElement target)
           // * dragAndDropBy(WebElement source, int xOffset, int yOffset)

           // xOffset value is chosen as given below
			// a. If the ‘quantity’ value is 5 the xOffset is "-10"   
            // b. If the ‘quantity’ value is 4 the xOffset is "-20"
            // c. If the ‘quantity’ value is 3 the xOffset is "-40"
            // d. If the ‘quantity’ value is 6 the xOffset is "10"
            // e. If the ‘quantity’ value is 7 the xOffset is "20"
			
			//Handle the code logic for different xOffset value, it must be dynamic based on quantity
			

           // yOffset value is default ‘0’
            Actions action=new Actions(driver);
            WebElement slider=driver.findElement(By.id("myRange"));
            int xOffset=0;
            switch(quantity){
                case "5":xOffset =-10;break;
                case "4":xOffset =-20;break;
                case "3":xOffset =-40;break;
                case "6":xOffset =-10;break;
                case "7":xOffset =-20;break;
                default:throw new Exception("Invalid quantity");
            }
            try{
                action.dragAndDropBy(slider,xOffset,0).perform();
            }catch(Exception e){
                System.out.println("Invalid action");
            }
        
	}	 	  	  		 	     	     	      	 	

	public void setItemPlatform(String platform) throws Exception { //Do not change the method signature
	
		//"Select" package has been already imported.
		//Declare the drop-down element as an instance of the Select class.
		//Select ‘Platform’ Drop down (For Example: Use id or xpath to identify the web element )
		//Select the platform option according to the ‘platform’ value read from the XML file  (For Example: Use selectByVisibleText or selectByValue)
		//Locate the web element of the ‘OK’ button and click it
       Select dropdown =new Select(driver.findElement(By.id("Platform")));
       dropdown.selectByVisibleText(platform);
       driver.findElement(By.id("quantityOK")).click();
	}	
	
	public String showBill() { //Do not change the method signature
	
	// Locate the 'Show Bill' button element using the "By.id" locator with the identifier "showBill".
    // Click the 'Show Bill' button using the "click" method on the 'showBillButton' WebElement.
    // Create an instance of WebDriverWait with a timeout of 10 seconds to wait for elements to become visible.
    // Locate the bill text element using the "By.id" locator with the identifier "billShow".
    // Get the text content from the WebElement using the "getText" and return the result of the method.

	    driver.findElement(By.id("showBill")).click();
        WebDriverWait wait=new WebDriverWait(driver,10);
        WebElement bill=driver.findElement(By.id("billShow"));
		return bill.getText();
	}

	public String getShoppingPlatform() { //Do not change the method signature
	
		//Locate the message displayed after clicking ‘Show Bill’ button. 
		//Locate the text displaying shopping platform chosen. 
		//Return the text using getText() method.
	        
	    WebElement msg=driver.findElement(By.xpath("//*[@id='billShow']/h4"));
		return msg.getText();
	}	 
	
	public static void main(String[] args) throws Exception {
		
		// Create an instance of ShoppingPage
	    // Create and initialize the WebDriver
	    // Load values from the properties file
	    // Get values from properties and store it in the variable itemName, itemQuantity, itemPlatform
	   	// Call the setter methods and passing the values from the properties file
	   	// Get and print the item price details by calling getItemPriceDetails method
	    // Call the method showBill and print the bill text
	    // Call the method getShoppingPlatform and print the name of the Platform
	   ShoppingPage sp=new ShoppingPage();
	   sp.createDriver();
	   Properties properties=new Properties();
	   FileInputStream input=new FileInputStream("config.properties");
	   properties.load(input);
	   String name=properties.getProperty("item.name");
	   String quantity=properties.getProperty("item.quantity");
	   String platform=properties.getProperty("item.platform");
	   sp.setItemName(name);
	   sp.setItemQuantity(quantity);
	   sp.setItemPlatform(platform);
	}
}
	 	  	  		 	     	     	      	 	
