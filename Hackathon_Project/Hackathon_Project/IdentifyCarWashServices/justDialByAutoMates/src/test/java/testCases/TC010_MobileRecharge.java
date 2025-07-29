package testCases;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SearchPageResultMobileRecharge;
import testBase.BaseClass;
import utilities.DataProviders;
import utilities.ExcelUtilityClass;

public class TC010_MobileRecharge extends BaseClass {

	@Test(dataProvider = "TC010", dataProviderClass = DataProviders.class)
	public void MobileRecharge(String service,String mobileNumber,String data) {

		logger.info("------ Starting TC010_MobileRecharge ------");
		
		String path=".\\testData\\Identify-Car-Wash-Services_TestData.xlsx";
		int count = 1;
		
		ExcelUtilityClass obj = new ExcelUtilityClass(path, "TC010");
		
		HomePage homePage = new HomePage(driver);

		homePage.clickMayBeLaterButton();
		logger.info("------ Clicked May Be Later Button ------");
		homePage.clickServiceMenu("Mobile");
		logger.info("------ Selected Mobile Service Menu ------");

		SearchPageResultMobileRecharge recharge = new SearchPageResultMobileRecharge(driver);

		recharge.setMobileNumber("8122537244");
		logger.info("------ Entered Valid Mobile Number ------");
		recharge.clickNewPlansButton();
		logger.info("------ Clicked New Plans Button in the Window ------");
		recharge.setPlan("Data");
		logger.info("------ Clicked Data plan in the plans Sub Menu ------");
		List<String> plans = recharge.getPlanDetails();
		for (String x : plans) {
			obj.setCellData(x,count,3);
			count++;
			String [] temp = x.split(" , ");
			System.out.println(temp[0] + "\nAmount: " + temp[1] + "\n");
		}
		List<Integer> amount = recharge.getAmountsFromData();
		logger.info("------ Displayed Results ------");

		boolean flag = true;

		for (Integer x : amount) {
			if (!(x <= 1000)) {
				flag = false;
			}
		}

		if (flag) {
			obj.setCellData("Plans Amounts are Lesser Than 1000 Rs",1,5);
			obj.setCellData("Pass",1,6);
			Assert.assertTrue(true);
			logger.info("------ Test Case Passed ------");
		} else {
			obj.setCellData("Plans Amounts are greater Than 1000 Rs",1,5);
			obj.setCellData("Pass",1,6);
			Assert.fail();
			logger.info("------ Test Case Failed ------");
		}
		logger.info("----------------------------------------------------------------------------------------");

	}

}
