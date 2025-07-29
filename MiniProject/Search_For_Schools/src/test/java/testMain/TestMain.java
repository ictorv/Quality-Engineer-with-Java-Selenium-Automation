/**
 * Project Name: Search For Schools (Website: Eduvidya)
 * File Name: TestMain.java
 * Description:
 * This is the main test class for automating the school search functionality on the EduVidya website.
 * It uses Selenium WebDriver and TestNG for browser automation and test execution.
 * The test data is read from an Excel file and results are written back to the same file.
 * Screenshots are captured at each step for visual verification.
 *
 * Key Components:
 * - @BeforeTest: Initializes the WebDriver based on the browser parameter.
 * - @Test: Executes the school search test using data from the Excel sheet.
 * - @DataProvider: Supplies test data to the test method from the Excel file.
 * - @AfterTest: Closes the WebDriver after test execution.
 *
 * Test Flow:
 * 1. Navigate to EduVidya homepage.
 * 2. Select submenu, course type, and city based on test data.
 * 3. Click the search button and wait for the page to load.
 * 4. Capture and verify the search result against the expected result.
 * 5. Log the actual result and test status (Pass/Fail) in the Excel sheet.
 * 6. Take screenshots at each step for documentation.
 */

package testMain;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import driverConfig.DriverSetup;
import searchForSchools.EduVidyaHomePage;
import utils.ExcelUtil;
import utils.ScreenShot;

public class TestMain {

	public WebDriver driver;
	public String filePath = System.getProperty("user.dir") + "\\ExcelData\\ExcelTestData.xlsx";
	public int row = 1;

	@BeforeTest
	@Parameters("browser")
	public void driverConfig(String browser) {
		// setting up the driver using getDriver() method
		driver = DriverSetup.getDriver(browser);
	}

	@Test(dataProvider = "testData")
	public void testMain(String subMenu, String courseType, String city, String expectedResult) throws IOException {

		EduVidyaHomePage homeObj = new EduVidyaHomePage(driver);

		homeObj.clickSubMenu(subMenu);
		ScreenShot.takeScreenShot(driver, "Schools Sub Menu");

		homeObj.selectCourseType(courseType);
		ScreenShot.takeScreenShot(driver, "Course Type");

		homeObj.selectCity(city);
		ScreenShot.takeScreenShot(driver, "City");

		homeObj.clickSearchBtn();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
				.executeScript("return document.readyState").equals("complete"));

		String actualResult = homeObj.verifySearchResult();
		ScreenShot.takeScreenShot(driver, "Search Results");

		ExcelUtil.setCellvalue(filePath, "Sheet1", row, 4, actualResult);
		if (actualResult.equalsIgnoreCase(expectedResult)) {
			ExcelUtil.setCellvalue(filePath, "Sheet1", row, 5, "Pass");
			Assert.assertTrue(true);
		} else {
			ExcelUtil.setCellvalue(filePath, "Sheet1", row, 5, "Fail");
			Assert.assertTrue(false);
		}
		row = row + 1;
		driver.navigate().back();
	}

	@DataProvider(name = "testData")
	public String[][] dataProvider() {

		int rowNum = 0;
		try {
			rowNum = ExcelUtil.getLastRowNumber(filePath, "Sheet1");
		} catch (IOException e) {
			e.printStackTrace();
		}

		String excelData[][] = new String[rowNum][4];

		for (int r = 1; r <= rowNum; r++) {
			try {
				excelData[r - 1][0] = ExcelUtil.getCellvalue(filePath, "Sheet1", r, 0);
				excelData[r - 1][1] = ExcelUtil.getCellvalue(filePath, "Sheet1", r, 1);
				excelData[r - 1][2] = ExcelUtil.getCellvalue(filePath, "Sheet1", r, 2);
				excelData[r - 1][3] = ExcelUtil.getCellvalue(filePath, "Sheet1", r, 3);
			} catch (IOException e) {
				e.printStackTrace();
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		}

		return excelData;
	}

	@AfterTest
	public void driverClose() {
		DriverSetup.quitDriver(driver);
	}

}
