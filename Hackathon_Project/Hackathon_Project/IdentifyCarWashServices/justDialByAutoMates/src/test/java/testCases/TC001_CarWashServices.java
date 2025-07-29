package testCases;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SearchResultPageCarWash;
import testBase.BaseClass;
import utilities.DataProviders;
import utilities.ExcelUtilityClass;

public class TC001_CarWashServices extends BaseClass {

	@Test(dataProvider = "TC001", dataProviderClass = DataProviders.class)
	public void carWashServices(String city, String search, String filter, String rating) {

		logger.info("------ Starting TC001_CarWashServices ------");
		
		String path=".\\testData\\Identify-Car-Wash-Services_TestData.xlsx";
		
		ExcelUtilityClass obj = new ExcelUtilityClass(path, "TC001");
		int count = 1;
		
		HomePage homePage = new HomePage(driver);

		homePage.clickMayBeLaterButton();
		logger.info("------ Clicked May be Later Button ------");
		homePage.setLocationName(city);
		homePage.clickLocationFirstDropDown();
		logger.info("------ Selected Location ------");
		homePage.setSearch(search);
		homePage.clickSearchFirstDropDown();
		logger.info("------ Searching Car Washing Services in the Search Box ------");

		SearchResultPageCarWash carWash = new SearchResultPageCarWash(driver);

		carWash.selectFilter(filter);
		carWash.setRating(rating);
		logger.info("------ Selected Rating ------");
		List<String> results = carWash.getSearchResults();
		String resultHeading = carWash.getHeading();

		for (String value : results) {
			String [] values = value.split(" / "); 
			System.out.println("\nCar Washing Sevices: " + values[0] + "\nContact Number: " + values[1]);
			obj.setCellData(value,count,4);
			count++;
			logger.info("------ Displaying Search Results ------");
		}

		if (results.size() == 5 && resultHeading.toLowerCase().contains("car wash")) {
			obj.setCellData("Pass",1,7);
			obj.setCellData(" The Search result contains  \"Car Washing Services\" and \"Saravanampatti, Coimbatore\"",1,6);
			Assert.assertTrue(true);
			logger.info("------ Test Case Passed ------");
		} else {
			obj.setCellData("Fail",1,7);
			obj.setCellData(" The Search result does Not contains  \"Car Washing Services\" and \"Saravanampatti, Coimbatore\"",1,6);
			Assert.fail();
			logger.info("------ Test Case Failed ------");
		}

		logger.info("----------------------------------------------------------------------------------------");

	}

}
