package com.xlreading;

import java.io.IOException;

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
import com.test.Utilitylibraries.OrangeHRM.DriverSetup;
import com.test.Utilitylibraries.OrangeHRM.ExtentReportManager;
import com.test.Utilitylibraries.OrangeHRM.ScreenShot;
import com.test.objects.AdminJobTitlePage;
import com.test.objects.LoginPage;

public class OrangeHRMJobTitleTest {

    static String sheet = "Orange";
    static String path = "src\\test\\java\\test\\Scripts\\projectOrangeHrm.xlsx";
    static WebDriver driver;
    private static String user;
    private static String password;
    static String jobTitle;
    String expected_url = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
	ExtentReportManager  erm;

    DriverSetup ds;
    ExcelDataFetch edf;
    Object[][] val;
    
    
    @BeforeClass
    @Parameters("browser")
    public void setup(String browser) {
        ds = new DriverSetup();
        edf = new ExcelDataFetch();
        val = edf.excelDataFetch(path, sheet);

        DriverSetup.url = val[1][0].toString();
        user = val[1][1].toString();
        password = val[1][2].toString();
        jobTitle = val[1][3].toString();

        driver = ds.driverInstantiate(browser);
    }

    @Test
    public void testAddJobTitle() throws IOException {
        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user, password);
        erm.logPass("Loged in Successfully.");
        String result= loginPage.verification( expected_url) ;
        erm.logPass("dashboard Url verified successfully");
        ExcelReadorWrite erw=new ExcelReadorWrite(path, sheet);
        erw.setCellData(1,4,result);

        // Navigate to Admin > Job Titles
        AdminJobTitlePage jobPage = new AdminJobTitlePage(driver);
        erm.logPass("clicked on Admin.");
        jobPage.goToJobTitles();
        erm.logPass("Clicked on JobTitle");

        // Add job title if it doesn't exist
        if (!jobPage.isJobTitlePresent(jobTitle)) {
            jobPage.addJobTitle(jobTitle);
            System.out.println("Job Title '" + jobTitle + "' added.");
            erm.logPass("Job Title Added Successfully");
        } else {
            System.out.println("Job Title '" + jobTitle + "' already exists.");
        }

        jobPage.logout();
    }

    @AfterClass
    public void tearDown() {
        // Uncomment this when ready to close the browser
        // driver.quit();
    }
    
    @BeforeTest
	public void startReporter()
	   {
		erm = new ExtentReportManager();
		erm.createTest("Orange HRM Flow");
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
