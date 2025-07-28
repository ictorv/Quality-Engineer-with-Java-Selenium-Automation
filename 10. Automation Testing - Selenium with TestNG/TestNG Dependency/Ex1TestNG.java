import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.TestNG;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

//ADD the required TestNG annotations on respective methods

public class Ex1TestNG {         //DO NOT Change the class Name
	
	//Do NOT change these below declarations.
	public static WebDriver driver; 

	
	// Apply the required annotation
	@BeforeSuite
	public WebDriver createDriver() {         //DO NOT change the method signature
		
		// Invoke the getWebDriver() method from the DriverSetup File 
	    // Store it in static variable 'driver'
	    driver=DriverSetup.getWebDriver();
	    return driver;
		
    }
	
	// Apply the required annotation
	@Test(groups="database")
	public void testInsert() {         //DO NOT change the method signature
		System.out.println("testInsert");
	}
	
	// Apply the required annotation
	@Test(groups="database")
	public void testUpdate() {    //DO NOT change the method signature      
		System.out.println("testUpdate");
	}
	
	// Apply the required annotation
	@Test(dependsOnGroups="database")
	public void groupDependent() {               //DO NOT change the method signature
		System.out.println("groupDependent");
	}
	
	// Apply the required annotation
	@AfterSuite
	public void closeBrowser() {         //DO NOT change the method signature
		System.out.println("closeBrowser");
	}
	
	public static void main(String[] args) {          //DO NOT change the method signature
	
		Ex1TestNG page=new Ex1TestNG();
	    
	    //Implement the required code
	    
	}
}
