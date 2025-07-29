package testRepository;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

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

import objectRepository.LoginPage;
import objectRepository.SearchMainPage;
import objectRepository.SearchResultPage;
import objectRepository.SignUpPage;
import utilityFiles.DriverSetup;
import utilityFiles.ExcelUtil;
import utilityFiles.ExtentReportManager;
import utilityFiles.ScreenShot;

public class TestMain {
    
    public static WebDriver driver;
    static DriverSetup objDriver;
    
    //static String browser = "chrome"; // Set default or read from suite parameter
    static String url;
    static String email;
    static String password;
    static String searchInput;
    static int selection;
    static String expectedText;
    static String expectedUrl;
    static String invalidEmail;
    static String profileName;
    static String sheet = "dataSheet";
    static String path = TestMain.class.getClassLoader().getResource("testData/testData.xlsx").getPath();
    
    ExtentReportManager erm; 
    
    @BeforeClass
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        objDriver = new DriverSetup();
        driver = objDriver.initializeDriver(browser);
        System.out.println(browser + " launched successfully!");
        setValues();
    }
    
    @BeforeClass
    public void setValues() {
        try {
            email = ExcelUtil.getCellData(path, sheet, 1, 1);
            password = ExcelUtil.getCellData(path, sheet, 1, 2);
            searchInput = ExcelUtil.getCellData(path, sheet, 1, 3);
            selection = Integer.parseInt(ExcelUtil.getCellData(path, sheet, 1, 4));
            expectedText = ExcelUtil.getCellData(path, sheet, 1, 5);
            expectedUrl = ExcelUtil.getCellData(path, sheet, 1, 7);
            profileName = ExcelUtil.getCellData(path, sheet, 1, 10);
            invalidEmail = ExcelUtil.getCellData(path, sheet, 1, 11);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @BeforeTest
	public void startReporter()
	   {
		erm = new ExtentReportManager();
		erm.createTest("Quora Scenario Flow");
	   }


    @Test(priority = 1)
    public void loginTest() {
        LoginPage lp = new LoginPage(driver);
        ScreenShot.screenShotTC(driver, "HomePage");
        System.out.println("Navigating to login page...");
        lp.clickSignIn();
        lp.clickLogIn();
        System.out.println("Sign In page accessed.");
        System.out.println("Fetching credentials from Excel...");
        lp.setEmail(email);
        lp.setPassword(password);
        
        ScreenShot.screenShotTC(driver, "LoginDetails");
        erm.logPass("Login Credentials Entered. Logging In");
        
        System.out.println("Credentials entered. Attempting login...");
        
        System.out.println("Have you solved the puzzle?");
        Scanner sc = new Scanner(System.in);
        String check = sc.nextLine();
        
        lp.clickLoginButton();
        System.out.println("Login submitted");
        erm.logPass("Login submitted");
    }

    @Test(priority = 2)
    public void searchValidationTest() throws TimeoutException {
        SearchMainPage smp = new SearchMainPage(driver);
        smp.setSearchInput(searchInput);
        
        System.out.println("Preparing to search keyword: "+searchInput);
        List<WebElement> suggestions = smp.getSearchList();
        System.out.println("First Auto Suggestion : " + suggestions.get(1).getText());
        
        erm.logPass("Search Hapenning");
        
        System.out.println("Clicking on suggestion number: " + selection);
        smp.clickSearchOption(selection);
        
        erm.logPass("First Auto Suggestion Selected.");
        
        System.out.println("Validating search results page...");
        
        SearchResultPage srp = new SearchResultPage(driver);
        srp.titleVerification(expectedText, expectedUrl);
        ScreenShot.screenShotTC(driver, "Verification");
        System.out.println("Title verification completed.");
        
        erm.logPass("Validation of Result Page");
        
        srp.clickProfile();
        System.out.println("Logging out..."); 
        srp.clickLogout();
        System.out.println("Logged out successfully.");
        
        erm.logPass("Logging Out");
    }

    @Test(priority = 3)
    public void invalidSignupTest() {
    	System.out.println("Testing invalid sign-up...");
        SignUpPage sup = new SignUpPage(driver);
        sup.clickSignUp();
        
        erm.logPass("Testing invalid sign-up");
        
        sup.setName(profileName);
        sup.setInvalidEmail(invalidEmail);
        
        erm.logPass("Invalid Email Entered");
        
        String error = sup.getErrorMessage();
        System.out.println("Captured error: " + error);

        ScreenShot.screenShotTC(driver, "ErrorMsg");
        
        erm.logPass("Captured Error Message");
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
	
	@AfterTest
    public void flushReport() {
	 erm.flushReports();
    }
	
    @AfterClass
    public void tearDown() {
        objDriver.tearDown();
        System.out.println("Browser closed. TestNG test complete.");
        erm.logPass("Browser closed. TestNG test completed");
    }
}

