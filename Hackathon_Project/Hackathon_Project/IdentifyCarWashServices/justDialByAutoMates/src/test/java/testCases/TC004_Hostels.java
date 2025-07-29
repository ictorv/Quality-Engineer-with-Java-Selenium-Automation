package testCases;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SearchResultPageHostels;
import testBase.BaseClass;
import utilities.DataProviders;
import utilities.ExcelUtilityClass;

public class TC004_Hostels extends BaseClass {

	@Test(dataProvider = "TC004", dataProviderClass = DataProviders.class)
	public void hostels(String city, String search, String topRated,String verified,String Trusted) {

		logger.info("------ Started TC004_Hostels ------");
		
		String path=".\\testData\\Identify-Car-Wash-Services_TestData.xlsx";
		int count = 1;
		
		ExcelUtilityClass obj = new ExcelUtilityClass(path, "TC004");

		HomePage homePage = new HomePage(driver);

		homePage.clickMayBeLaterButton();
		logger.info("------ Clicked May Be Later Button ------");
		homePage.setLocationName(city);
		homePage.clickLocationFirstDropDown();
		logger.info("------ Selected Location ------");
		homePage.setSearch(search);
		homePage.clickSearchFirstDropDown();
		logger.info("------ Searching Hostels in the Search Box ------");

		SearchResultPageHostels hostels = new SearchResultPageHostels(driver);

		hostels.selectFilter(topRated);
		logger.info("------ Selected Top Rated in Filters ------");
		hostels.selectFilter(verified);
		logger.info("------ Selected Top JD Verified in Filters ------");
		hostels.selectFilter(Trusted);
		logger.info("------ Selected Top JD Trust in Filters ------");

		List<String> results = hostels.getHotelResultList();
		for (String result : results) {
			obj.setCellData(result,count,5);
			String [] value = result.split(" , ");
			System.out.println("\nName: " + value[0] + "\nLocation: " + value[1]);
			count++;
		}
		logger.info("------ Displayed the Search Results ------");

		boolean flag = true;

		for (String x : results) {
			if (!(x.toLowerCase().contains("coimbatore"))) {
				flag = false;
			}
		}

		if (flag) {
			obj.setCellData(" The Search result Contains coimbatore in Location",1,7);
			obj.setCellData("Pass",1,8);
			Assert.assertTrue(true);
			logger.info("------ Test Case Passed ------");
		} else {
			obj.setCellData(" The Search result does Not Contains coimbatore in Location",1,7);
			obj.setCellData("Fail",1,8);
			Assert.fail();
			logger.info("------ Test Case Failed ------");
		}
		logger.info("----------------------------------------------------------------------------------------");

	}

}
