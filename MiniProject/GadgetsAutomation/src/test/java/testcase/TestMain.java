package testcase;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import pages.ProductPage;
import pages.HomePage;

import data.ExcelUtils;
import utilities.DriverSetup;
import utilities.ExtentReportManager;
import utilities.ScreenShot;
import java.io.IOException; 

public class TestMain {

    public WebDriver driver;
    DriverSetup objDriver;
    Object[][] prodFilter;
    String Output;
    String path = "Snapdeal.xlsx"; // Ensure this matches your Excel file name
    String sheet = "Snapdeal"; // Ensure this matches your sheet name
    ExcelUtils eu;
    ExtentReportManager erm;

    @BeforeClass
    @Parameters("browser")
    public void driverConfig(String browser) {
        objDriver = new DriverSetup();
        driver = objDriver.driverInstantiate(browser);
        System.out.println("Opened the URL in the browser");
    }

    @BeforeTest
    public void testDataRead() {
        try {
            eu = new ExcelUtils(path, sheet);
            int rows = eu.getRowCount(); // Call instance method
            System.out.println("Number of Rows of Data: " + rows);
            int cols = eu.getCellCount(2); // Get cell count for the row where your data starts
            System.out.println("Number of Columns of Data: " + cols);

            // Initialize prodFilter with actual data rows (excluding header)
            prodFilter = new Object[rows][cols];

            // Loop starts from row 2 (third row) 
            for (int i = 2; i <= rows; i++) { // Loop till rows (inclusive of last row)
                for (int j = 0; j < cols; j++) {
                    prodFilter[i-2][j] = eu.getCellData(i, j); 
                }
            }
            // Print the data to confirm
            System.out.println("Test Data Read:");
            for (Object[] rowData : prodFilter) {
                for (Object cellData : rowData) {
                    System.out.print(cellData + "\t");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error reading test data from Excel: " + e.getMessage());
        }
    }
    
    @BeforeTest
	public void startReporter()
	   {
		erm = new ExtentReportManager();
		erm.createTest("Snapdeal Scenario Flow");
	   }
    
    @Test
	public void SnapdealProductsPage() throws Exception {
    	int i = 0, j = 0;
    	
		String productname = prodFilter[i][j].toString();
		String sortbydata = prodFilter[i][j + 1].toString();
		String minprice = prodFilter[i][j+2].toString();
		String maxprice = prodFilter[i][j+3].toString();
		
		// 1. Open the browser and the Application url
		WebDriver driver = this.driver;		
		// Take screen shot
		ScreenShot.screenShotTC(driver, "01_Homepage");
		erm.logPass("Navigated to Snapdeal homepage.");
		
		HomePage objHomePg = new HomePage(driver);
		// 2.Handle the popup
		
		objHomePg.popup();
		System.out.println("Closed the popup Message");
		erm.logPass("Popup closed successfully.");
		
		// 3.Enter “Bluetooth headphone” in searchBox
		objHomePg.searchTextBox(productname);
		// Take screen shot
		ScreenShot.screenShotTC(driver, "02_Bluetooth");
		erm.logPass("Entered Bluetooth Headphones");
		
		//4.Click "search" button
		objHomePg.searchButton();
		erm.logPass("Clicked Search Button");
		
		ProductPage objproductPg = new ProductPage(driver);
		//5.Click on sortby arrow
		objproductPg.sort();
		ScreenShot.screenShotTC(driver,"03_DropDown");
		erm.logPass("Clicked Sort Button");
		
		//6.Select "Popularity" from drop down
		objproductPg.sortDropDown(sortbydata);
		System.out.println("Selected sort option");
		erm.logPass("Selected Popularity");
		
		//7.Set price minimum value to '700'
		objproductPg.minimum(minprice);
		ScreenShot.screenShotTC(driver,"04_Minprice");
		System.out.println("Set minimum price to 700");
		erm.logPass("Set minimum price to 700");
		
		//8.Set price minimum value to '1400'
		objproductPg.maximum(maxprice);
		ScreenShot.screenShotTC(driver,"05_Maxprice");
		System.out.println("Set maximum price to 1400");
		erm.logPass("Set maximum price to 1400");
		
		//9. Click "Go" Button
		objproductPg.go();
		System.out.println("Clicked GO button");
		erm.logPass("Clicked GO button");
		
		objproductPg.explicitWait();
		/*WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[11]/div[7]/div[5]/div[3]/div[6]/div[2]/button")));*/
		
		//10.Print product name and price of first 5 products
		objproductPg.print(2,sheet);
		erm.logPass("Printed First 5 Products");
	}
    
    @AfterMethod
	   public void getResult(ITestResult result) {
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
	        eu.close();	        
	    }
	 
	 @AfterTest
	    public void flushReport() {
		 erm.flushReports();
	 } 
}