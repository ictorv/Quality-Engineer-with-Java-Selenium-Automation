	package TestRepository;
	
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.testng.ITestResult;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.AfterTest;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
	import org.testng.annotations.Test;
	
	import com.aventstack.extentreports.Status;
	import com.aventstack.extentreports.markuputils.ExtentColor;
	import com.aventstack.extentreports.markuputils.MarkupHelper;
	import UserDefinedLibraries.ExtentReportManager;
	
	import TestObjectRepository.FlipKartMainPage;
	import TestObjectRepository.MobilesMainPage;
	import UserDefinedLibraries.DriverSetup;
	import UserDefinedLibraries.ScreenShot;
	
	import java.io.IOException;
	import java.util.List;
	
	/**
	 * TestMain class automates the FlipKart mobile search scenario using Selenium WebDriver and TestNG.
	 * It performs actions such as searching for mobiles, applying filters, sorting results, and validating product data.
	 * The class also integrates ExtentReports for detailed test reporting and captures screenshots at various steps.
	 * 
	 * Dependencies:
	 * - Selenium WebDriver
	 * - TestNG
	 * - ExtentReports
	 * - Custom libraries: DriverSetup, ScreenShot, ExtentReportManager
	 * 
	 * Test Flow:
	 * 1. Browser setup and initialization
	 * 2. Search for mobiles on FlipKart
	 * 3. Apply filters and sort results
	 * 4. Validate product data
	 * 5. Generate test report and screenshots
	 * 6. Cleanup and close browser
	 */
	
	public class TestMain {
	
		public static WebDriver driver;
		static DriverSetup objDriver;
		String path = "MiniProject_TestData.xlsx";
		String sheet = "FlipKart TestData";
		ExtentReportManager erm;
	    
		//Driver Instantiation
		@BeforeClass
		@Parameters("browser")
		public void driverConfig(@Optional("chrome") String browser) {
			System.out.println("Initializing driver setup...");
			objDriver = new DriverSetup();
			driver = objDriver.driverInstantiate(browser);
			System.out.println("Driver instantiated and browser launched.");
		}
		
		//Extent Report Generation
		@BeforeTest
		public void startReporter()
		   {
			erm = new ExtentReportManager();
			erm.createTest("FlipKart Scenario Flow");
		   }
	    
		@Test
		public void FlipKartPage() throws Exception {
	
			// 1. Open the browser and the Application url
			// Take screen shot
			ScreenShot.screenShotTC(driver, "01_Homepage");
			erm.logPass("Navigated to Filpkart main page.");
			FlipKartMainPage home = new FlipKartMainPage(driver);
	        //2. Select menu mobiles
			System.out.println("Performing search for 'mobiles'...");
			home.searchFor("mobiles");
			erm.logPass("Performed search for mobiles.");
			//3. Validating Search Results 
			System.out.println("Validating search results...");
			if (home.validateSearch("mobiles under 15000")) {
				System.out.println("Search is validated");
			} else {
				System.out.println("Search is not validated");
			}
	        //4. Going to Mobiles Page
			ScreenShot.screenShotTC(driver, "02_mobiles_page");
			System.out.println("Navigating to mobiles listing page...");
			erm.logPass("Navigated to Mobiles page after search.");
			MobilesMainPage mobiles = new MobilesMainPage(driver);
	        //5. Apply slider filter for price
			System.out.println("Applying price slider filter...");
			mobiles.applyPriceSlider();
			ScreenShot.screenShotTC(driver, "03_sliderilter");
			erm.logPass("Clicked on Slider Filter");
			//6. Filter according to operating system
			System.out.println("Applying operating system filter...");
			mobiles.applyOperatingSystemFilter();
			ScreenShot.screenShotTC(driver, "04_sliderilter");
			erm.logPass("Clicked on Slider Filter");
	        //7. Click on Pie
			System.out.println("Selecting Android version...");
			mobiles.selectAndroidVersion();
			ScreenShot.screenShotTC(driver, "05_selectandroidVersion");
			erm.logPass("Clicked on Operating System Version");
	        //8. Sort the mobiles by newestFirst
			System.out.println("Sorting by newest...");
			mobiles.sortByNewest();
			ScreenShot.screenShotTC(driver, "06_sortByNewest");
			erm.logPass("Sorted By Newest First");
	        //9. Fetching out product names and prices
			System.out.println("Fetching product names and prices...");
			List<WebElement> names = mobiles.getProductNames();
			List<WebElement> prices = mobiles.getProductPrices();
			mobiles.writeFirstFiveProducts(names,prices);
			erm.logPass("Printed first 5 product names.");
	        //10. Check for the first price
			System.out.println("Checking if first product price is under 30000...");
			mobiles.checkFirstProductPriceUnderLimit(prices, 30000);
			erm.logPass("Validated the first mobile price.");
			
	        System.out.println("Taking final screenshot...");
	        //11. Closing the browser after successful test completion 
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
		// 12. Close the browser
	        if (driver != null) {
	        	objDriver.driverTearDown();
	        }	        
	    }
	 
	   @AfterTest
	   // 13. Flush the Extent Reports
	   public void flushReport() {
		 erm.flushReports();
	   }
	
	 }
	
	
