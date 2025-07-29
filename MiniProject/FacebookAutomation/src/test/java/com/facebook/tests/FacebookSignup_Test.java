package com.facebook.tests;

import org.testng.ITestResult;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.time.Duration;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.facebook.pages.Facebook_Login_Page;
import com.facebook.pages.Signup_Page;
import com.facebook.util.DriverSetup;
import com.facebook.util.ExcelReadWrite;
import com.facebook.util.ExtentReportManager;
import com.facebook.util.ScreenshotUtility;

public class FacebookSignup_Test {

	WebDriver driver;
	DriverSetup setup;
	ExcelReadWrite excel;
	Facebook_Login_Page loginPage;
	Signup_Page signupPage;
	ExtentReportManager erm;

	@Parameters("browser")
	@BeforeClass
	public void setUp(@Optional("chrome") String browser) {
		setup = new DriverSetup();
		driver = setup.getDriver(browser);
		excel = new ExcelReadWrite();
		excel.setupExcel();
		excel.readExcel();
	}

	@Test
	public void testFacebookSignup() {
		String[] userData = excel.getUserDataForForm();

		loginPage = new Facebook_Login_Page(driver);

		// Screenshot
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		try {
			ScreenshotUtility.takeScreenshot(driver, "mainPage");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		loginPage.clickSignUpBtn();

		erm.logPass("Opens Signup page and Filled details and Got the Error Messages");

		// Screenshot
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		try {
			ScreenshotUtility.takeScreenshot(driver, "SignUpPage");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		signupPage = new Signup_Page(driver);
		signupPage.fillFirstname(userData[0]);
		signupPage.fillLastname(userData[1]);
		signupPage.fillDate(userData[2], userData[3], userData[4]);
		signupPage.selectGender(userData[5]);
		signupPage.fillMobileNumber(userData[6]);
		signupPage.submitDetails();

		// Screenshot
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		try {
			ScreenshotUtility.takeScreenshot(driver, "ErrorMessages");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Capture and write errors
		try {
			String passwordError = signupPage.getPasswordErrorMsg();
			String mobileError = signupPage.getMobileErrorMsg();
			excel.writeExcel(1, passwordError, mobileError); // Row 1 = second row (after header)
		} catch (Exception e) {
			System.out.println("No error messages found or elements not visible.");
		}
	}

	@AfterClass
	public void tearDown() {
		excel.saveExcelChanges();
		setup.closeDriver();
	}

	@BeforeTest
	public void startReporter() {
		erm = new ExtentReportManager();
		erm.createTest("Facebook Login");
	}

	@AfterMethod
	public void getResult(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			// MarkupHelper is used to display the output in different colors
			erm.testLogger.log(Status.FAIL,
					MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			erm.testLogger.log(Status.FAIL,
					MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
			String screenshotPath = ScreenshotUtility.takeScreenshot(driver, result.getName());
			// To add it in the extent report
			erm.testLogger.fail("Test Case Failed Snapshot is below "
					+ erm.testLogger.addScreenCaptureFromPath("." + screenshotPath));
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			erm.testLogger.log(Status.PASS,
					MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
		} else if (result.getStatus() == ITestResult.SKIP) {
			erm.testLogger.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
		}
	}

	@AfterTest
	public void flushReport() {
		erm.flushReports();
	}

}
