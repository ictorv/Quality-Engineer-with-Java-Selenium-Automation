package testScenario;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import testObjectRepositary.*;
import userDefinedLibraries.*;

public class FlipKartTest {

	public WebDriver driver;
	DriverSetup objDriver;
	String url;

	// Excel file and sheet details
	String xlfile = System.getProperty("user.dir") + "//src/test/resources//testData//filpkartTestData.xlsx";
	String xlsheet = "Sheet1";
	int testRow = 1;
	String searchKeyword;

	// Variables to store totals
	int Total_1_Item;
	int calculatedTotal;
	int Total_2_Items;

	// Report manager instance
	ExtentReportManager reportManager;

	// Initialize the report before tests start
	@BeforeTest
	public void startReporter() {
		reportManager = new ExtentReportManager();
	}

	// Setup browser and open URL before class execution
	@BeforeClass
	@Parameters("browser")
	public void driverConfig(String browser) throws IOException {
		objDriver = new DriverSetup();
		url = ExcelUtility.getCellData(xlfile, xlsheet, testRow, 0);
		driver = objDriver.driverInstantiate(browser, url);
		System.out.println("Opened the URL in the browser");
	}
		//Can I remove the objDriver instance and directly use class name???
	
	// Read test data before each test method
	@BeforeMethod
	public void readTestData() throws IOException {
		searchKeyword = ExcelUtility.getCellData(xlfile, xlsheet, testRow, 1);
	}

	// Main test method to validate Flipkart cart totals
	@Test
	public void flipkartCartValidationTest() throws Exception {
		reportManager.createTest("Flipkart Cart Validation Test");

		// Step 1: Search for product
		Flipkart_HomePage objHomePage = new Flipkart_HomePage(driver);
		objHomePage.setAndClickSearchBox(searchKeyword);
		reportManager.logPass("Searched for product: " + searchKeyword);

		// Step 2: Click first product and add to cart
		Flipkart_AppliancesPage objAppliancesPage = new Flipkart_AppliancesPage(driver);
		objAppliancesPage.click_First_Product();
		reportManager.logPass("Clicked on first product");

		Flipkart_ProductDetailsPage objProductDetailsPage = new Flipkart_ProductDetailsPage(driver);
		objProductDetailsPage.scrollTo_addToCartBtn();
		objProductDetailsPage.click_addToCartbtn();
		reportManager.logPass("Added first product to cart");

		// Step 3: Get total for first item
		Flipkart_CartPage objCartPage1 = new Flipkart_CartPage(driver);
		String Item1_total = objCartPage1.getTotalAmount();
		reportManager.logPass("Total for 1 item: " + Item1_total);

		FlipkartValidation fValidation = new FlipkartValidation();
		Total_1_Item = fValidation.convertStringToInt(Item1_total);

		// Step 4: Close first product tab
		objDriver.closeCurrentPage();
		reportManager.logPass("Closed first product page");

		// Step 5: Add second product to cart
		objAppliancesPage.click_Next_Product();
		objProductDetailsPage.scrollTo_addToCartBtn();
		objProductDetailsPage.click_addToCartbtn();
		reportManager.logPass("Added second product to cart");

		// Step 6: Get all price components and total
		Flipkart_CartPage objCartPage2 = new Flipkart_CartPage(driver);
		String Item2_total = objCartPage2.getTotalAmount();
		String Actualprice = objCartPage2.getPrice();
		String discount = objCartPage2.getDiscount();
		String coupon = objCartPage2.getCoupons();
		String DeliveryCharge = objCartPage2.getDeliveryCharges();
		String protectFee = objCartPage2.getProtectFee();
		String handlingFee = objCartPage2.getHandlingCharges();
		String buy_save_More_String = objCartPage2.getBuyMoreSaveMore();

		// Log all price components
		reportManager.logPass("Actual Price: " + Actualprice);
		reportManager.logPass("Discount: " + discount);
		reportManager.logPass("Coupon: " + coupon);
		reportManager.logPass("Delivery Charge: " + DeliveryCharge);
		reportManager.logPass("Buy More Save More discount: " + buy_save_More_String);
		reportManager.logPass("Protect Fee: " + protectFee);
		reportManager.logPass("Handling Fee: " + handlingFee);
		reportManager.logPass("Total for 2 items: " + Item2_total);

		// Step 7: Capture screenshot of cart with both items
		String screenshotPath = ScreenShot.screenShotTC(driver, "CartPage_TwoItems");
		String absolutePath = new File(screenshotPath).getAbsolutePath();
		reportManager.testLogger.addScreenCaptureFromPath(absolutePath, "Cart Page with Two Items");

		// Step 8: Validate total
		calculatedTotal = fValidation.validateTotal(Actualprice, discount, coupon, DeliveryCharge, protectFee,handlingFee, buy_save_More_String);
		Total_2_Items = fValidation.convertStringToInt(Item2_total);

		reportManager.logPass("Expected Total: " + calculatedTotal);

		// Log validation result
		if (calculatedTotal == Total_2_Items) {
			reportManager.logPass("Validation Passed: Total matches expected value.");
		} else {
			reportManager.logFail("Validation Failed: Total does not match expected value.");
		}
	}

	// Write test results back to Excel after each test method
	@AfterMethod
	public void writeTestData() throws IOException {
		if (calculatedTotal == 0 && Total_2_Items == 0) {
			System.out.println("[INFO] Unable to Validate...");
			reportManager.logFail("Validation Failed: Rush Hour or some issues...");
		}

		ExcelUtility.setCellData(xlfile, xlsheet, testRow, 2, Total_1_Item + "");
		ExcelUtility.setCellData(xlfile, xlsheet, testRow, 3, Total_2_Items + "");
		ExcelUtility.setCellData(xlfile, xlsheet, testRow, 4, calculatedTotal + "");

		if (calculatedTotal == Total_2_Items) {
			ExcelUtility.setCellData(xlfile, xlsheet, testRow, 5, "PASS");
			ExcelUtility.fillGreenColor(xlfile, xlsheet, testRow, 5);
			
			System.out.println("[PASS] Total calculated successfully...");
		} else {
			ExcelUtility.setCellData(xlfile, xlsheet, testRow, 5, "FAIL");
			ExcelUtility.fillRedColor(xlfile, xlsheet, testRow, 5);
			System.out.println("[FAIL] Total calculation mismatch...");
		}
	}

	// Flush the report after all tests
	@AfterTest
	public void flushReport() {
		reportManager.flushReports();
	}

	// Close browser after all tests
	@AfterClass
	public void tearDown() {
		objDriver.driverTearDown();
	}
}

