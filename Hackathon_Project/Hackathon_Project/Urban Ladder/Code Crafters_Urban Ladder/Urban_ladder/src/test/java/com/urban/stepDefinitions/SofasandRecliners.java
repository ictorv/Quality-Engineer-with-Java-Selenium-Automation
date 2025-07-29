package com.urban.stepDefinitions;
 
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.urban.base.BaseClass;
import com.urban.pageObject.BasePage;
import com.urban.pageObject.HomePage;
import com.urban.utillities.ScreenShot;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
 
public class SofasandRecliners {
	
	HomePage home;
    private static final Logger logger = LogManager.getLogger(SofasandRecliners.class);

	
	@When("the user hover to Sofas and Recliners")
	public void the_user_hover_to_sofas_and_recliners() {
		logger.info("Navigating to Sofa and Recliners Option");
	    home = new HomePage(BaseClass.driver);
	    home.moveToElement();
	}
 
	@Then("the user see all items under particular category and collect them")
	public void the_user_see_all_items_under_particular_category_and_collect_them() {
	    home.listItems();
        ScreenShot.captureScreenshot(BasePage.driver, "SofasAndRecliner");

		logger.info("All the Categories are Visible");

	}
 
	
}