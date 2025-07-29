//package com.urban.stepDefinitions;
//
//import io.cucumber.java.en.Then;
//
//public class BeingAthome {
//	
//	@Then("the user should be redirected to being at home page")
//	public void the_user_shoul_be_redirected_to_being_at_home_page() {
//	    
//	}
//
//	@Then("the user clicks the category option")
//	public void the_user_clicks_the_category_option() {
//	    
//	}
//
//	@Then("the user see list of category")
//	public void the_user_see_list_of_category() {
//	    
//	}
//
//	@Then("the user collect all the categories")
//	public void the_user_collect_all_the_categories() {
//	    
//	}
//	
//}
 
 
package com.urban.stepDefinitions;
 
import java.util.List;

import org.openqa.selenium.WebElement;

import com.urban.base.BaseClass;
import com.urban.pageObject.BasePage;
import com.urban.pageObject.BeingAtHome;
import com.urban.utillities.ScreenShot;
import com.urban.utillities.WaitUtils;

import io.cucumber.java.en.Then;
 
public class BeingAthome{
//	private static final Logger logger = LogManager.getLogger(BeingAthome.class);
 
    BeingAtHome bah;
    @Then("the user should be redirected to being at home page")
    public void the_user_should_be_redirected_to_being_at_home_page() {
        bah = new BeingAtHome(BaseClass.driver);
//        String searchTerm = Beingxml.getInput();
//        BaseStep.homePageObject.searchField(searchTerm);
//        BaseStep.homePageObject.clickSearchIcon();
        BasePage.popupHandle();
        BaseClass.getLogger().info("Goto my Calculator section-->Click on View all Calculators.");
    }
 
    @Then("the user clicks the category option")
    public void the_user_clicks_the_category_option() {
        bah.category();
    }
 
    @Then("the user see list of category")
    public void the_user_see_list_of_category() {
        List<WebElement> categoryItemsList = bah.categoryItems();
        WaitUtils.waitForVisibility(BaseClass.driver, categoryItemsList.get(0), 10);
        ScreenShot.captureScreenshot(BasePage.driver, "BeingAtHome");

    }
 
    @Then("the user collect all the categories")
    public void the_user_collect_all_the_categories() {
    	bah.categoryItems();
//        List<WebElement> categoryItemsList = bah.categoryItems();
//        int r = 1;
//        for (WebElement item : categoryItemsList) {
//            String itemName = item.getText();
//            System.out.println(itemName);
//            ExcelUtil.writeDataIntoExcel("BeingAtHome", r, 1, itemName);
//            r++;
//        }
    }
}