package testCases;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import testBase.BaseClass;
import utilities.DataProviders;
import utilities.ExcelUtilityClass;

public class TC003_GymSubMenuList extends BaseClass {

	@Test(dataProvider = "TC003", dataProviderClass = DataProviders.class)
	public void gymSubMenuList(String categorey) {

		logger.info("------ Starting TC003_GymSubMenuList ------");
		
		String path=".\\testData\\Identify-Car-Wash-Services_TestData.xlsx";
		int count = 1;
		
		ExcelUtilityClass obj = new ExcelUtilityClass(path, "TC003");

		HomePage homePage = new HomePage(driver);

		homePage.clickMayBeLaterButton();
		logger.info("------ Clicked May Be Later Button ------");

		homePage.setSubMenu(categorey);
		logger.info("------ Clicked the Fitness Menu ------");

		List<String> gymSubMenuList = homePage.getGymSubMenuList();
		for (int i = 0; i < gymSubMenuList.size(); i++) {
			System.out.println((i + 1) + ". " + gymSubMenuList.get(i));
			obj.setCellData(gymSubMenuList.get(i),count,1);
			count++;
		}
		logger.info("------ Displayed the GYMs Sub menus ------");

		boolean flag = true;

		for (String x : gymSubMenuList) {
			if (!(x.toLowerCase().contains("gym") || x.toLowerCase().contains("fitness"))) {
				flag = false;
			}
		}

		if (flag) {
			obj.setCellData("The Search result contains  gym/fitness",1,3);
			obj.setCellData("Pass",1,4);
			Assert.assertTrue(true);
			logger.info("------ Test Case Passed ------");
		} else {
			obj.setCellData("The Search result does Not contains  gym/fitness",1,3);
			obj.setCellData("Fail",1,4);
			Assert.fail();
			logger.info("------ Test Case Failed ------");
		}

		logger.info("----------------------------------------------------------------------------------------");

	}

}
