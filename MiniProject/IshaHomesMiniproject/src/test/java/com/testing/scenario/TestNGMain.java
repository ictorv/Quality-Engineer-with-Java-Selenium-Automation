package com.testing.scenario;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
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
import com.testing.Pages.Enquiry;
import com.testing.Pages.HomePage;
import com.testing.Pages.ResultPage;
import com.testing.UserDefineLibraries.DriverSetup;
import com.testing.UserDefineLibraries.ExcelReadOrWriteUtility;
import com.testing.UserDefineLibraries.ExtentReportManager;
import com.testing.UserDefineLibraries.ScreenShotUtil;

public class TestNGMain {

	static String[] result;
	DriverSetup objDriver;
	static String sheet = "IshaHomesUrl";
	static WebDriver driver;
	Enquiry enq;
	ExtentReportManager erm;
	ExcelReadOrWriteUtility eds;

	@BeforeClass
	@Parameters("browser")
	public void driverInstantiation(String browser) {

		System.out.println("start");
		try {
			// 1.Open the browser and application url
			DriverSetup.pathInitialize();
			driver = DriverSetup.driverInstantiate(browser);
			System.out.println("Opened the URL in the browser");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Open Home page and scroll down and click on element to navigate
	// @Test(priority=1)
	public void navigatingToResultPage() {
		ResultPage result = new ResultPage(driver);
		result.scrollDown();
	}

	@Test(priority = 1)
	public void navigatingToHomePage() {
		// 2.Open home page and click on the element "completed projects"
		HomePage home = new HomePage(driver);
		home.clickOnCompletedProjects();
	}

	// storing the list of completed projects in to the result
	@Test(priority = 2)
	public void projectList() {
		HomePage obj = new HomePage(driver);
		result = obj.completedProjects();
	}

	// Click the element "Enquire Now" to fetch Email
	@Test(priority = 3)
	public void navigatingToEnquiry() {
		enq = new Enquiry(driver);
		enq.clickEnquiry();
		// click on element other Enquire
		enq.clikcOtherEnquiry();
		// fetching the email
		System.out.println(enq.getEmail());
		System.out.println("end");
	}

	// Taking screenshot after every method execution
	@AfterMethod
	public void screeShot() throws IOException {
		ScreenShotUtil.screenShotTC(driver, "PageScreenShot");
	}

	// Writing the data to excel the list of completed projects
	@AfterClass
	public void writeDataIntoExcel() throws IOException {
		int j = 0;
		for (String i : result) {
			ExcelReadOrWriteUtility.writeDataIntoExcel(sheet, j + 1, 2, i);
			j++;
		}
	}

	// Writing the fetched email to Excel
	@AfterClass
	public void dataEmailWrite() throws IOException {

		ExcelReadOrWriteUtility.writeDataIntoExcel(sheet, 1, 3, enq.getEmail());
	}

	// generating the ExtentReport
	@BeforeClass
	public void startReporter() {
		erm = new ExtentReportManager();
		erm.createTest("IshaHomes Scenario Flow");
	}

	// Getting the report based on the conditions
	@AfterMethod
	public void getResult(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			// MarkupHelper is used to display the output in different colors
			erm.testLogger.log(Status.FAIL,
					MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			erm.testLogger.log(Status.FAIL,
					MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
			String screenshotPath = ScreenShotUtil.screenShotTC(driver, result.getName());
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

	// closing the browser
	@AfterClass
	public void tearDown() {
		if (driver != null) {
			DriverSetup.driverTearDown();
		}
		//eds.closeFile();
	}

}
