package testScenario;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import driverConfiguration.DriverSetup;
import userDefinedLibraries.ExcelUtils;
import userDefinedLibraries.TestDataProviderUtils;
import testObjectRepository.DemoQaAlertWindow;
import testObjectRepository.DemoQaFormWindow;
import testObjectRepository.DemoQaHomePage;

public class MainTest {

	public WebDriver driver;
	DriverSetup setup;
	Integer row = 1;
	String filepath = System.getProperty("user.dir") + "\\testdata\\testDatademoQA.xlsx";
	String sheetName = "Form";

	@BeforeClass
	@Parameters("browser")
	public void initializeBrowserDriver(String browser, ITestContext context) throws IOException {

		// creating instance for DriverSetup
		setup = new DriverSetup();
		// Initiating browser launch
		driver = setup.driverIntialization(browser);
		context.setAttribute("driver", driver);

	}

	@Test(priority = 1)
	public void testhandleJavaScriptAlert() throws IOException {

		// creating instance for demoQAHomePage
		DemoQaHomePage homePage = new DemoQaHomePage(driver);

		// creating instance for demoQAAlertWindow
		DemoQaAlertWindow alertWindowPage = new DemoQaAlertWindow(driver);

		// Scroll to viewpoint
		setup.windowscroll(driver, 200);

		// Click on Alert Tab
		homePage.navigateToAlertsSection();

		// Scroll to viewpoint
		setup.windowscroll(driver, 100);

		// Click on alert section in left panel
		alertWindowPage.openAlertSection();

		// Scroll to viewpoint
		setup.windowscroll(driver, 100);

		// Click on alert button
		try {
			alertWindowPage.handlePromptAlert();
		}
		catch(Exception e) {
			Assert.assertTrue(false);		
		}

	}

	@Test(dataProvider = "studentData", dataProviderClass = TestDataProviderUtils.class, priority = 2)
	public void testFormSubmissionWithJSE(String firstName, String lastName, String email, String gender, String mobile,
			String dob, String hobby, String address, String state, String city) throws IOException {

		// creating instance for demoQAHomePage
		DemoQaHomePage homePage = new DemoQaHomePage(driver);

		// creating instance for demoQAFormWindow
		DemoQaFormWindow formWindowPage = new DemoQaFormWindow(driver);

		// Navigate back to home page
		formWindowPage.navigateBackToHome();

		// Scroll to viewpoint
		setup.windowscroll(driver, 100);

		// Click on form tab
		homePage.navigateToFormsSection();

		// Handling the form
		formWindowPage.fillStudentRegistrationForm(firstName, lastName, email, gender, mobile, dob, hobby, address,
				state, city);

		// Validating the form Submission
		if (formWindowPage.getModalTitle().equals("Thanks for submitting the form")) {
			Assert.assertTrue(true);
			int r = row++;
			ExcelUtils.setCellValue(filepath, sheetName, r, 10, "Pass");
			ExcelUtils.fillGreenColor(filepath, sheetName, r, 10);
		} else {
			int r = row++;
			ExcelUtils.setCellValue(filepath, sheetName, r, 10, "Fail");
			ExcelUtils.fillRedColor(filepath, sheetName, r, 10);
			Assert.assertTrue(false);
		}

	}

	@AfterClass
	public void closeBrowserSession() {

		// closes the browser
		setup.exitBrowser(driver);

	}

}
