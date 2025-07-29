package testCases;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SearchResultPageRestaurant;
import testBase.BaseClass;
import utilities.DataProviders;
import utilities.ExcelUtilityClass;

public class TC005_Restaurants extends BaseClass {

	@Test(dataProvider = "TC005", dataProviderClass = DataProviders.class)
	public void restaurants(String city, String search, String cuisinesFilter,String cuisine,String delivery,String openNow ) {

		logger.info("------ Starting TC005_Restaurants ------");
		
		String path=".\\testData\\Identify-Car-Wash-Services_TestData.xlsx";
		int count = 1;
		
		ExcelUtilityClass obj = new ExcelUtilityClass(path, "TC005");

		HomePage homePage = new HomePage(driver);

		homePage.clickMayBeLaterButton();
		logger.info("------ Clicked May Be Later Button ------");
		homePage.setLocationName(city);
		homePage.clickLocationFirstDropDown();
		logger.info("------ Selected Location ------");
		homePage.setSearch(search);
		homePage.clickSearchFirstDropDown();
		logger.info("------ Searching Restaurants in the Search Box ------");

		SearchResultPageRestaurant restaurant = new SearchResultPageRestaurant(driver);

		restaurant.selectFilter(cuisinesFilter);
		logger.info("------ Selected Cuisines in the Filters ------");
		restaurant.setCuisine(cuisine);
		logger.info("------ Selected South Indian in the Cuisines Filter ------");
		restaurant.selectFilter(delivery);
		logger.info("------ Selected Online Delivery in the Filters ------");
		restaurant.selectFilter(openNow);
		logger.info("------ Selected Open Now in the Filters ------");
		List<String> results = restaurant.getRestaurantSearchResults();
		List<Integer> ratings = restaurant.getNoOfRatings();
		for (String result : results) {
			obj.setCellData(result,count,6);
			String [] value = result.split(" , ");
			System.out.println("\nHotel Name: "+value[0]+"\nRating(5): "+value[1]+"\nNumber Of Ratings: "+value[2]);
			count++;
		}
		logger.info("------ Displaying the Results ------");

		boolean flag = true;

		for (Integer x : ratings) {
			if (x < 20) {
				flag = false;
				break;
			}
		}

		if (flag) {
			obj.setCellData(" The number rating is greater than 20",1,8);
			obj.setCellData("Pass",1,9);
			Assert.assertTrue(true);
			logger.info("------ Test Case Passed ------");
		} else {
			obj.setCellData(" The number rating is Lesser than 20",1,8);
			obj.setCellData("Fail",1,9);
			Assert.fail();
			logger.info("------ Test Case Failed ------");
		}
		logger.info("----------------------------------------------------------------------------------------");

	}

}
