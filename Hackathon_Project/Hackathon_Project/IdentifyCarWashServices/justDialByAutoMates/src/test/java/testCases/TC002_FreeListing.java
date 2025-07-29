package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.FreeListingPhoneNumberPage;
import pages.HomePage;
import testBase.BaseClass;
import utilities.DataProviders;
import utilities.ExcelUtilityClass;

public class TC002_FreeListing extends BaseClass {

	@Test(dataProvider = "TC002", dataProviderClass = DataProviders.class)
	public void freeListing(String phoneNumber) {

		logger.info("------ Starting TC002_FreeListing ------");
		
		String path=".\\testData\\Identify-Car-Wash-Services_TestData.xlsx";
		
		ExcelUtilityClass obj = new ExcelUtilityClass(path, "TC002");
		
		HomePage homePage = new HomePage(driver);

		homePage.clickFreeListingList();
		logger.info("------ Clicked Free Listing Option ------");

		FreeListingPhoneNumberPage freelisting = new FreeListingPhoneNumberPage(driver);

		freelisting.setPhoneNumber(phoneNumber);
		logger.info("------ Entered Invalid Phone Number in the Phone Number Field ------");

		freelisting.clickStartNowButton();
		logger.info("------ Clicked Start Now Button ------");

		String Message = freelisting.getErrorMessage();
		logger.info("------ Retrieved the Error Message ------");

		System.out.println("The Error Message is: " + Message);
		
		obj.setCellData(Message,1,2);

		if (Message.equalsIgnoreCase("Please Enter a Valid Mobile Number")) {
			obj.setCellData("Pass",1,3);
			Assert.assertTrue(true);
			logger.info("------ Test Case Passed ------");
		} else {
			obj.setCellData("Fail",1,3);
			Assert.fail();
			logger.info("------ Test Case Failed ------");
		}

		logger.info("----------------------------------------------------------------------------------------");

	}

}
