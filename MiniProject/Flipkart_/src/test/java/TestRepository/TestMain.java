package TestRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import TestObjectRepository.FlipKartMainPage;
import TestObjectRepository.MobilesMainPage;
import UserDefinedLibraries.DriverSetup;
import UserDefinedLibraries.ExtentReportManager;
import UserDefinedLibraries.ScreenShot;


public class TestMain {

	public static WebDriver driver;
	static DriverSetup objDriver;
	int i,j;
	Object[][] prodFilter;
	String Output;
	String path = "MiniProject_TestData.xlsx";
	String sheet = "FlipKart TestData";
	ExtentReportManager erm;
	
	FlipKartMainPage home;
	MobilesMainPage mobiles;

	@BeforeClass
	@Parameters("browser")
	public void driverConfig(String browser) {
		System.out.println("Initializing driver setup...");
		objDriver = new DriverSetup();
		driver = objDriver.driverInstantiate(browser);
		System.out.println("Driver instantiated and browser launched.");
		//return driver;
	}
	
	@BeforeClass
	public void startReporter()
	   {
		erm = new ExtentReportManager();
		erm.createTest("FlipKart Scenario Flow");
	   }
    

	@Test(priority=1)
	public void openBrowser() throws Exception{
		// Take screen shot
		ScreenShot.screenShotTC(driver, "00_Homepage");
		erm.logPass("Navigated to Filpkart main page.");
		home = new FlipKartMainPage(driver);
	}
	
	
	@Test(priority=2)
	public void selectMenu() throws Exception{
		home = new FlipKartMainPage(driver);
		System.out.println("Performing search for 'mobiles'...");
		home.searchFor("mobiles ");
		ScreenShot.screenShotTC(driver, "01_EnterValue");
		erm.logPass("Performed search for mobiles.");
	}
	
	@Test(priority=3)
	public void validatePage() throws Exception{
		System.out.println("Validating search results...");
		
		if (home.validateSearch("mobiles under 15000")) {
			System.out.println("Search is validated");
		} else {
			System.out.println("Search is not validated");
		}
	}
	
	@Test(priority=4)
	public void navigate() throws Exception{
		ScreenShot.screenShotTC(driver, "02_mobiles_page");
		System.out.println("Navigating to mobiles listing page...");
		erm.logPass("Navigated to Mobiles page after search.");
		mobiles = new MobilesMainPage(driver);
	}
	
	@Test(priority=5)
	public void slider() throws Exception{
		System.out.println("Applying price slider filter...");
		mobiles.applyPriceSlider();
		ScreenShot.screenShotTC(driver, "03_sliderilter");
		erm.logPass("Clicked on Slider Filter");
	}
	
	@Test(priority=6)
	public void selectPie() throws Exception{
		System.out.println("Applying operating system filter...");
		mobiles.applyOperatingSystemFilter();
		ScreenShot.screenShotTC(driver, "04_sliderilter");
		erm.logPass("Clicked on Slider Filter");
		
		System.out.println("Selecting Android version...");
		mobiles.selectAndroidVersion();
		ScreenShot.screenShotTC(driver, "05_selectandroidVersion");
		erm.logPass("Clicked on Operating System Version");
	}
	
	@Test(priority=7)
	public void selectNewest() throws Exception{
		System.out.println("Sorting by newest...");
		mobiles.sortByNewest();
		ScreenShot.screenShotTC(driver, "06_sortByNewest");
		erm.logPass("Sorted By Newest First");

	}
	List<WebElement> names, prices;
	
	@Test(priority=8)
	public void putData() throws Exception{
		System.out.println("Fetching product names and prices...");
		
		Map<WebElement, WebElement> productData = mobiles.getData();
		names = new ArrayList<>();
		prices = new ArrayList<>();
		for(WebElement name : productData.keySet()) {
			names.add(name);
			prices.add(productData.get(name));
		}
		mobiles.writeFirstFiveProducts(names,prices);
		erm.logPass("Printed first 5 product names.");
		
	}
	
	@Test(priority=9)
	public void checkproduct() throws Exception{
		System.out.println("Checking if first product price is under 30000...");
		mobiles.checkFirstProductPriceUnderLimit(prices, 30000);
		erm.logPass("Validated the first mobile price.");
		System.out.println("Taking final screenshot...");
        ScreenShot.screenShotTC(driver, "Sample");
        
        System.out.println("Closing browser...");
		System.out.println("Test execution completed.");
		erm.logPass("Test completed successfully.");
	}
	

  @AfterMethod
	   public void getResult(ITestResult result) throws IOException {
	       if(result.getStatus() == ITestResult.FAILURE) {
	    	 //MarkupHelper is used to display the output in different colors
	    	   erm.testLogger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
	    	   erm.testLogger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
	    	   String screenshotPath = ScreenShot.screenShotTC(driver, result.getName());
	    	 //To add it in the extent report 
	    	   erm.testLogger.fail("Test Case Failed Snapshot is below " + erm.testLogger.addScreenCaptureFromPath("."+screenshotPath));	    	  
	       }
	       else if(result.getStatus() == ITestResult.SUCCESS) {
	    	   erm.testLogger.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
	       }
	       else if(result.getStatus() == ITestResult.SKIP){
	    	   erm.testLogger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE)); 
	       }
	   }
   @AfterClass
    public void tearDown() {
	// 10. Close the browser
        if (driver != null) {
        	objDriver.driverTearDown();
        }	        
    }
 
   @AfterTest
   public void flushReport() {
	 erm.flushReports();
   }

 }
