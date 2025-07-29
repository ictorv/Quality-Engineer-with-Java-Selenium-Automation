package com.urban.stepDefinitions;
 
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.urban.base.BaseClass;
import com.urban.pageObject.BasePage;
import com.urban.pageObject.TrackDetails;
import com.urban.utillities.ExcelUtil;
import com.urban.utillities.ScreenShot;
import com.urban.utillities.TrackOrderxml;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
 
public class TrackOrder {
	
//	HomePage home;
	TrackDetails td;
    private static final Logger logger = LogManager.getLogger(TrackOrder.class);

	
	@When("the user clicks on the Track Order")
	public void the_user_clicks_on_the_track_order() {
        logger.info("The user clicks on the Track Order");
		BaseStep.homePageObject.click();
	}
 
	@Then("the user is redirected to new page")
	public void the_user_is_redirected_to_new_page() {
	    td = new TrackDetails(BaseClass.driver);
        logger.info("The user is redirected to new page");
        ScreenShot.captureScreenshot(BasePage.driver, "TrackOrder");
	}
 
	@Then("the user enters the order number and phone number and gets the response")
	public void the_user_enters_the_order_number_and_phone_number_and_gets_the_response() throws InterruptedException {
		String[] details = TrackOrderxml.getInput();
        logger.info("The user enters the order number and phone number and gets the response");

		int r = 1;
		for(int i = 0 ; i < details.length ; i = i+2) {
//			int c = 0;
			td.orderInput(details[i]);
			td.phoneNoInput(details[i+1]);
			td.Click();
			String msg = td.message();
			System.out.println(msg);
			BaseClass.driver.navigate().refresh();
//			for(int j = 0 ; j < (details.length / 2)+1 ; j++) {
				ExcelUtil.writeDataIntoExcel("TrackOrder", r, 0, details[i]);
				ExcelUtil.writeDataIntoExcel("TrackOrder", r, 1, details[i+1]);
				ExcelUtil.writeDataIntoExcel("TrackOrder", r, 2, msg);
//				c++;
//			}
			r++;
		}
	}
 
 
}