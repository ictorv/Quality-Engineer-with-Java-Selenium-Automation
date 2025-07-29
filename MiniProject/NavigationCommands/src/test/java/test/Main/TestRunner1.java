package test.Main;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import test.Objects.ContactSales;
import test.Objects.NavigationCommands;
import test.Objects.OrangeHRMAction1;
import test.Utility.BaseTest1;
import test.Utility.ExcelUtil;
import test.Utility.ExtentReportManager;
import test.Utility.ScreenshotHelper;

public class TestRunner1 extends BaseTest1 {

    final String excelFilePath = "\\src\\test\\java\\test\\Utility\\Excel.xlsx";
    final String sheetName = "Sheet1";
    List<String> listExcelData;
    OrangeHRMAction1 formPage;
    ContactSales contactPage;
    NavigationCommands navigation;
    
    ExtentReportManager erm;

    @BeforeClass
    @Parameters("browser")
    public void setUpTest(String browser) throws Exception {
        driver = driverInstantiate(browser);

        ExcelUtil eut = new ExcelUtil(excelFilePath, sheetName);
        eut.excelDataFetch();
        listExcelData = eut.getListArray();
        navigation = new NavigationCommands(driver);
        contactPage = new ContactSales(driver);
        formPage = new OrangeHRMAction1(driver);
    }

    @Test(priority = 1)
    public void launchAndNavigateToContactForm() throws InterruptedException {
        navigation.pageUrl(listExcelData.get(0));
        contactPage.clickContactSales();
        erm.logPass("Contact Sales was clicked");
        
    }

    @Test(priority = 2)
    public void fillFormAndSubmitBeforeMessage() throws IOException {
        formPage.fillForm(
            listExcelData.get(1),
            listExcelData.get(2),
            listExcelData.get(3),
            listExcelData.get(4),
            listExcelData.get(5),
            listExcelData.get(6)
        );

        formPage.handleCaptcha();

        System.out.println("Enter your message to proceed...");
        new Scanner(System.in).nextLine(); // Pause for manual input, if needed

        formPage.submitForm();
        erm.logPass("Submit was clicked without entering message");
        ScreenshotHelper.screenShotTC(driver, "BeforeInputMessage");
    }

    @Test(priority = 3)
    public void submitMessageAndCaptureAfterScreenshot() throws IOException {
        formPage.msg(listExcelData.get(7));
        erm.logPass("Message was entered in message input box.");
        formPage.submitForm();
        erm.logPass("Submit was clicked after message.");
        ScreenshotHelper.screenShotTC(driver, "AfterInputMessage");
        System.out.println("Test executed successfully.");
    }

    @AfterClass
    public void tearDownTest() {
        tearDown();
    }
    
    @AfterTest
    public void flushReport() {
	 erm.flushReports();
    }
    
    
    @AfterMethod
	   public void getResult(ITestResult result) throws IOException {
	       if(result.getStatus() == ITestResult.FAILURE) {
	    	 //MarkupHelper is used to display the output in different colors
	    	   erm.testLogger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
	    	   erm.testLogger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
	    	   //String screenshotPath = ScreenshotHelper.screenShotTC(driver, result.getName());
	    	 //To add it in the extent report
	    	   //erm.testLogger.fail("Test Case Failed Snapshot is below " + erm.testLogger.addScreenCaptureFromPath("."+screenshotPath));
	    	   
	    	   String screenshotPath = ScreenshotHelper.screenShotTC(driver, result.getName());
	    	   erm.testLogger.fail("Test Case Failed Snapshot is below",
	    	   MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());

	       }
	       else if(result.getStatus() == ITestResult.SUCCESS) {
	    	   erm.testLogger.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
	       }
	       else if(result.getStatus() == ITestResult.SKIP){
	    	   erm.testLogger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
	       }
	   }
    
    @BeforeTest
	public void startReporter()
	   {
		erm = new ExtentReportManager();
		erm.createTest("NavigationCommandsProject");
	   }
}
