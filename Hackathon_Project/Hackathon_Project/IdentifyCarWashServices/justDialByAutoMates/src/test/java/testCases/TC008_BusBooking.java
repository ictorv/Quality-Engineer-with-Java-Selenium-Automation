package testCases;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.BusBookingPage;
import pages.HomePage;
import pages.SearchResultPageBusBooking;
import testBase.BaseClass;
import utilities.DataProviders;
import utilities.ExcelUtilityClass;

public class TC008_BusBooking extends BaseClass {

	@Test(dataProvider = "TC008", dataProviderClass = DataProviders.class)
	public void BusBooking(String departure,String destionation) {

		logger.info("------ Starting TC008_BusBooking ------");
		
		String path=".\\testData\\Identify-Car-Wash-Services_TestData.xlsx";
		int count = 1;
		
		ExcelUtilityClass obj = new ExcelUtilityClass(path, "TC008");

		HomePage homePage = new HomePage(driver);

		homePage.clickMayBeLaterButton();
		logger.info("------ Clicked May Be Later Button ------");
		homePage.clickServiceMenu("Bus");
		logger.info("------ Clicked Bus Service Menu ------");

		BusBookingPage busBook = new BusBookingPage(driver);

		busBook.setDepartureCity(departure);
		logger.info("------ Selected Coimbatore in the Departure City ------");
		busBook.setDestinationCity(destionation);
		logger.info("------ Selected Chennai in the Destination City ------");
		busBook.clickDateOfJourney();
		busBook.clickNextMonth();
		busBook.selectDate();
		logger.info("------ Selected Date ------");
		busBook.clickSearchButton();
		logger.info("------ Clicked Search Button ------");

		SearchResultPageBusBooking busBookResult = new SearchResultPageBusBooking(driver);

		busBookResult.clickBusType();
		logger.info("------ Clicked Bus Type ------");
		busBookResult.clickNonAcFilterCheckBox();
		logger.info("------ Selected Non Ac CheckBox ------");
		busBookResult.clickSleeperFilterCheckBox();
		logger.info("------ Selected Sleeper CheckBox ------");
		busBookResult.clickBusType();
		WebElement nonAcFilter = busBookResult.getNonAcFilter();
		WebElement sleeperFilter = busBookResult.getSleeperFilterCheckBox();
		List<String> results = busBookResult.getBusDetails();

		for (String x : results) {
			obj.setCellData(x,count,2);
			count++;
			String[] values = x.split(" , ");
			System.out.println("Travels Name: " + values[0] + "\tDeparture Time: "
					+values[1]+ "\nPrice: " + values[2] + " rupees"
					+ "\n----------------------------------------------------------------------------------------------------------------------------------------------------------------");
		}
		logger.info("------ Displayed Results ------");

		if (nonAcFilter.isEnabled() && sleeperFilter.isEnabled()) {
			obj.setCellData("Both Non-AC and Sleeper Check box are Enabled",1,4);
			obj.setCellData("Pass",1,5);
			Assert.assertTrue(true);
			logger.info("------ Test Case Passed ------");
		} else {
			obj.setCellData("Both Non-AC and Sleeper Check box are Not Enabled",1,4);
			obj.setCellData("Fail",1,5);
			Assert.fail();
			logger.info("------ Test Case Failed ------");
		}
		logger.info("----------------------------------------------------------------------------------------");
	}

}
