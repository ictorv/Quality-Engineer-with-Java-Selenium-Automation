import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.TestNG;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

//ADD the required TestNG annotations on respective methods

@Listeners(FeatureTest.class)  // Do not change or remove this line
public class Ex2TestNG {
	
	//Use the below declared variables
	public static WebDriver driver;
	String username, password; 
	String payeeName, payeeAccount;
	String amount;
	
	//Apply the required annotation
	@BeforeSuite
	public WebDriver createDriver() {
	    
		// Invoke the getWebDriver() method from the DriverSetup File 
	    // Store it in static variable 'driver' 
	    driver=DriverSetup.getWebDriver();
	    return driver;
	}
	
	//Apply the required annotation
	@BeforeClass
	public void pageNavigation(){
	    System.out.println("pageNavigation");
	    
	}
	
	//Apply the required annotation
	@Test(priority=0)
	public void setLogin(){
	    System.out.println("setLogin");
	    username="admin";
	    password="admin";
	}
	
	//Apply the required annotation 
	@Test(priority=1)
	public void addPayee() {
		System.out.println("addPayee");
		payeeName="John";
		payeeAccount="01001234";
	}
	
	//Apply the required annotation 
	@Test(priority=2, dependsOnMethods={"setLogin","addPayee"})
	public void setFundTransfer() {
		System.out.println("setFundTransfer");
		amount="2000";
	}
	
    //Apply the required annotation 
    @AfterClass
	public void setLogout(){
	    System.out.println("setLogout");
	    
	}
	
	//Apply the required annotation 
	@AfterSuite
	public void closeBrowser(){
	    System.out.println("closeBrowser");
	}
	
	public void invokeTest() {
		//code to invoke test using TestNG
		
		try {
			TestNG testng = new TestNG();
			List<String> suites = Lists.newArrayList();
			suites.add("testng.xml");
			
			testng.setTestSuites(suites);
			testng.run();		
		
		} catch (Exception e) {
			
		} 
	}
	
	public static void main(String[] args) {
	    
	    Ex2TestNG ex2=new Ex2TestNG();
	    // Add Required Code 
	    ex2.invokeTest();
	}

}
