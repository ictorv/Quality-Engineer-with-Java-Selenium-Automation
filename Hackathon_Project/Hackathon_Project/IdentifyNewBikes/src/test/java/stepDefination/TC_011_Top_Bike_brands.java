package stepDefination;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import factory.BaseClass;
import io.cucumber.java.en.Then;
import pageObject.HomePage;

public class TC_011_Top_Bike_brands {
	WebDriver driver = BaseClass.getDriver();
	HomePage hp= new HomePage(driver);

	
	@Then("scroll to the Top Car brands in India")
	public void scroll_to_the_top_car_brands_in_india() {
	    hp.scrollToTopBikes();
	}
	
	@Then("check whether all 12 Brands are displayed or not")
	public void check_whether_all_brands_are_displayed_or_not() {
	    hp.topBikeBrands();
	}

	@Then("validate the retrived Brands")
	public void validate_the_retrived_brands() {
	    boolean result = hp.validateTopBrands();
	    Assert.assertEquals(result, true);
	    if(result) {
	    	System.out.println("top Brand test passed");
	    }else {
	    	System.out.println("top Brand test failed");
	    }
	}

}
