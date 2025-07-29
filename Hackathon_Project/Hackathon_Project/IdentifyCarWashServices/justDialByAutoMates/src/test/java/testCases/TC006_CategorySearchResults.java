package testCases;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.HomePage;
import testBase.BaseClass;
import utilities.DataProviders;
import utilities.ExcelUtilityClass;

public class TC006_CategorySearchResults extends BaseClass {

	public boolean flag = true;
	public HomePage homePage;
	public String path=".\\testData\\Identify-Car-Wash-Services_TestData.xlsx";
	public int count=1;
	
	ExcelUtilityClass obj; 

	@BeforeMethod
	public void categorySearch() {

		if (flag) {
			logger.info("------ Starting TC006_CategorySearchResults ------");
			homePage = new HomePage(driver);
			obj = new ExcelUtilityClass(path, "TC006");
			homePage.clickMayBeLaterButton();
			logger.info("------ Clicked May Be Later Button ------");
			homePage.clickCloseButton();
			logger.info("------ Clicked Close Button for a pop up ------");
			homePage.clickPopularCategoryButton();
			logger.info("------ Clicked Popular Category Button ------");
			flag = false;
		}
	}

	@Test(dataProvider = "TC006", dataProviderClass = DataProviders.class, priority = 2)
	public void categorySearchResults(String search) {

		String result ="";
		homePage.setSearchValue(search);
		logger.info("------ Searching " + search + " in the Seach Box ------");
		System.out.println("\n" + search.toUpperCase() + " Search Result: \n");
		logger.info("------ Displaying the Search Results ------");
		List<String> results = homePage.getCategorySearchResults();
		for (String x : results) {
			System.out.println(x);
			result=result+x+"   ";
		}
		obj.setCellData(result,count,1);
		count++;
		homePage.clickSearchResultClose();
		

		if (results.size() >= 1) {
			obj.setCellData("Each Search Results have atleast one Result",1,3);
			obj.setCellData("Pass",1,4);
			Assert.assertTrue(true);
			logger.info("------ Test Case Passed ------");
		} else {
			obj.setCellData("Not Each Search Results have atleast one Result",1,3);
			obj.setCellData("Fail",1,4);
			Assert.fail();
			logger.info("------ Test Case Failed ------");
		}
		logger.info("----------------------------------------------------------------------------------------");

	}

}
