package main.ProductListAutomation;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
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

import object.ProductListAutomation.FilterPanel;
import object.ProductListAutomation.HomePage;
import object.ProductListAutomation.MenuNavigation;
import object.ProductListAutomation.ProductListingPage;
import test.data.excelDataSheet;
import utility.ProductListAutomation.DriverUtility;
import utility.ProductListAutomation.ExtentReportManager;
import utility.ProductListAutomation.ScreenShot;

public class PepperfryMainTest {

    static WebDriver driver;
    static excelDataSheet excel;
    ExtentReportManager erm;
    
    // Define the Excel file path and sheet name
    final String filePath = "src\\test\\java\\test\\data\\PepperfryTestData.xlsx";
    final String sheetName = "TestInputs";
    final int row = 1; 
    

    @BeforeClass
    @Parameters("browser")
    public void setUp(@Optional("chrome") String Browser) {
        // Initialize Excel and the WebDriver before tests run
        excel = new excelDataSheet(filePath, sheetName);
        DriverUtility ds = new DriverUtility();
        driver = ds.driverInstantiate("chrome");
        erm = new ExtentReportManager();
        erm.createTest("Productlist Automation");
        
    }

    @Test
    public void testPepperfryWorkflow() throws IOException {
        // Read the test URL from Excel
        String url = excel.getCellData(row, 0);
        
        // Home page actions
        HomePage home = new HomePage(driver);
        home.navigateToSite(url);
        String validate = home.validateTitle("Buy Furniture & Home Decor Online – Up to 60% Off at Best Prices in India | Pepperfry");
        
        excelDataSheet.setCellData(filePath, sheetName, row, 1, validate);
        home.handleLoginPopup();
        ScreenShot.screenShotTC(driver, "01_HomePage");
        
        erm.logPass("Navigated to site");
        
        // Menu navigation
        MenuNavigation menu = new MenuNavigation(driver);
        erm.logPass("Hovering on Mainmenu Furniture");
        
        menu.clickSetteesUnderFurniture();
        ScreenShot.screenShotTC(driver, "02_MenuNavigation");
        
        erm.logPass("Clicked the Settees");
        
        // Count of all options displayed
        ProductListingPage productPage = new ProductListingPage(driver);
        List<String> li = productPage.printProductCounts();
        for (int i = 0; i < li.size(); i++) 
        {
            excelDataSheet.setCellData(filePath, sheetName, row + i, 2, li.get(i));
        }
        ScreenShot.screenShotTC(driver, "03_OptionsCount");
        erm.logPass("Count of options");
        
        // Apply the material metal filter
        FilterPanel filter = new FilterPanel(driver);
        filter.applyMaterialMetalFilter();
        erm.logPass("Options Filtered");
        
        // Count the filtered benches
        String filterCount = productPage.printFinalCount();
        excelDataSheet.setCellData(filePath, sheetName, row, 3, filterCount);
        erm.logPass("Count of benches after display");
    }

    @AfterClass
    public void tearDown() 
    {
        // Close the WebDriver and Excel file after tests complete
        if (driver != null)
        {
            driver.quit();
        }
        if (excel != null)
        {
            excel.closeFile();
        }
    }
    
    
    @BeforeTest
	public void startReporter()
	   {
		erm = new ExtentReportManager();
		erm.createTest("Productlist Automation");
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

    
    @AfterTest
    public void flushReport() {
	 erm.flushReports();
    }
 
}

