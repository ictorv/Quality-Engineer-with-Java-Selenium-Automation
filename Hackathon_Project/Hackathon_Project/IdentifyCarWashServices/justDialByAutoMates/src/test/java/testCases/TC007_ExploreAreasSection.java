package testCases;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.TouristPlacesPage;
import testBase.BaseClass;
import utilities.DataProviders;
import utilities.ExcelUtilityClass;

public class TC007_ExploreAreasSection extends BaseClass {

	@Test(dataProvider = "TC007", dataProviderClass = DataProviders.class)
	public void ExploreAreasSection(String location) {
		logger.info("------ Starting TC007_ExploreAreasSection ------");
		
		String path=".\\testData\\Identify-Car-Wash-Services_TestData.xlsx";
		
		
		ExcelUtilityClass obj = new ExcelUtilityClass(path, "TC007");

		HomePage homePage = new HomePage(driver);

		homePage.clickMayBeLaterButton();
		logger.info("------ Clicked May Be Later Button ------");
		homePage.setLocationName(location);
		homePage.clickLocationFirstDropDown();
		logger.info("------ Selected Location ------");
		homePage.clickTopTouristPlace();
		logger.info("------ Selected Top Tourist Place in that Location ------");

		TouristPlacesPage tourist = new TouristPlacesPage(driver);
		tourist.clickCloseButtonForLocation();
		// Hotels
		List<String> hotels = tourist.getHotelDetails();
		// Restaurant
		List<String> restaurants = tourist.getRestaurantDetails();
		// Coffee Shops
		List<String> coffeeShops = tourist.getCoffeeShop();
		// Restaurant
		List<String> travelAgents = tourist.getTravelAgentDetails();
		// Coffee Shops
		List<String> thingsToDos = tourist.getThingsToDo();

		System.out.println("\nTop Hotels in " + location + "\n");

		int counter =1;
		for(String hotel:hotels) {
			obj.setCellData(hotel,counter,1);
			String [] value = hotel.split(" , ");
			System.out.println("Hotel Name : "+value[0]+"\nRating : "+value[1]);
			counter++;
		}
		
		logger.info("------ Displayed Top Hotels ------");

		System.out.println("\nTop Restaurants in " + location + "\n");
		counter=1;
		for(String restaurant :restaurants) {
			obj.setCellData(restaurant,counter ,2);
			String [] value = restaurant.split(" , ");
			System.out.println("Restaurant Name  : "+value[0]+"\nRating : "+value[1]);
			counter++;
		}
		logger.info("------ Displayed Top Restaurants ------");

		System.out.println("\nTop Coffee Shops in " + location + "\n");
		counter=1;
		for(String coffeeShop:coffeeShops) {
			obj.setCellData(coffeeShop,counter,3);
			String [] value = coffeeShop.split(" , ");
			System.out.println("Coffee Shop Name : "+value[0]+"\nRating : "+value[1]);
			counter++;
		}
		
		logger.info("------ Displayed Top Coffee Shops ------");
		System.out.println("\nTop Travel Agents in " + location + "\n");
		counter=1;
		for(String travelAgent:travelAgents) {
			obj.setCellData(travelAgent,counter,4);
			String [] value = travelAgent.split(" , ");
			System.out.println("Travel Agents : "+value[0]+"\nRating : "+value[1]);
			counter++;
		}
		
		logger.info("------ Displayed Top Travel Agents ------");
	
		System.out.println("\nTop Things to do in " + location + "\n");

		counter=1;
		for(String thingsToDo:thingsToDos) {
			obj.setCellData(thingsToDo,counter,5);
			String [] value = thingsToDo.split(" , ");
			System.out.println("Things To Do : "+value[0]+"\nRating : "+value[1]);
			counter++;
		}
		
		logger.info("------ Displayed Top Things To Do ------");
		
		for(int i=0; i<5;i++) {
			
		}
		

		if (hotels.isEmpty() || restaurants.isEmpty() || coffeeShops.isEmpty() || travelAgents.isEmpty()
				|| thingsToDos.isEmpty()) {
			logger.info("------ Test Case Failed ------");
			Assert.fail();
			obj.setCellData("All the fields does Not have 5 Results",1,7);
			obj.setCellData("Fail",1,8);

		} else {
			logger.info("------ Test Case Passed ------");
			Assert.assertTrue(true);
			obj.setCellData("All the fields have 5 Results",1,7);
			obj.setCellData("Pass",1,8);

		}

		logger.info("----------------------------------------------------------------------------------------");

	}

}
