package com.urban.stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.urban.base.BaseClass;
import com.urban.pageObject.BasePage;
import com.urban.pageObject.ProductPage;
import com.urban.pageObject.SearchresultPage;
import com.urban.utillities.ScreenShot;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddToCart {

    SearchresultPage results;
    ProductPage product;
    BasePage base;
    String expectedName;
    private static final Logger logger = LogManager.getLogger(BookShelves.class);


    @When("the user searches for {string}")
    public void the_user_searches_for(String string) {
        logger.info("Searching for: " + string);
        BaseStep.homePageObject.searchField(string);
        BaseStep.homePageObject.clickSearchIcon();
    }

    @When("clicks the first item and switches to new window")
    public void clicks_the_first_item_and_switches_to_new_window() {
        results = new SearchresultPage(BaseClass.getDriver());
        expectedName = results.getFirstItemName();
        logger.info("First item name: " + expectedName);
        BasePage.popupHandle();
        results.clickFirstItem();
        logger.info("Clicked on first item from search results");
        results.switchWindow();
        logger.info("Switched to new window");
    }

    @When("adds the item to cart")
    public void adds_the_item_to_cart() {
    	logger.info("Handling any popup before adding to cart");
        BasePage.popupHandle();
        product = new ProductPage(BaseClass.getDriver());
        product.addToCart();
        logger.info("Item added to cart");
    }

    @Then("the item name in cart should match the search result")
    public void the_item_name_in_cart_should_match_the_search_result() {
        String actualName = product.getItemNameInCart();
        logger.info("Item name in cart: " + actualName);
        if (actualName.equalsIgnoreCase(expectedName)) {
        	logger.info("✅ Item name matches with search result");
            System.out.println("Valid Item");
        } else {
        	logger.warn("❌ Item name mismatch: Expected [" + expectedName + "], but found [" + actualName + "]");
            System.out.println("Item name mismatch in cart");
        }
    }

    @Then("print the item price")
    public void print_the_item_price() {
        String itemPrice = product.getItemPrice();
        ScreenShot.captureScreenshot(BasePage.driver, "ItemDetails");
        logger.info("Final Price of item in cart: " + itemPrice);
        System.out.println("Final Price: " + itemPrice);
    }

}




















































































//
//package com.urban.stepDefinitions;
//
//import com.urban.base.BaseClass;
//import com.urban.pageObject.BasePage;
//import com.urban.pageObject.ProductPage;
//import com.urban.pageObject.SearchresultPage;
//
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//
//public class AddToCart {
//	
//
//	SearchresultPage results;
//	ProductPage product;
//	BasePage base;
//	String expectedName;
//	
//	@When("the user searches for {string}")
//	public void the_user_searches_for(String string) {
//	    // Write code here that turns the phrase above into concrete actions
//	    BaseStep.homePageObject.searchField(string);
//	    BaseStep.homePageObject.clickSearchIcon();
//	}
//
//	@When("clicks the first item and switches to new window")
//	public void clicks_the_first_item_and_switches_to_new_window() {
//	    // Write code here that turns the phrase above into concrete actions
//	    results = new SearchresultPage(BaseClass.getDriver());
//	    expectedName = results.getFirstItemName();
//		results.clickFirstItem();
//		BaseClass.getLogger().info("clicked on first item");
//		results.switchWindow();
//	}
//
//	@When("adds the item to cart")
//	public void adds_the_item_to_cart() {
//	    // Write code here that turns the phrase above into concrete actions
//		BasePage.popupHandle();
//		product = new ProductPage(BaseClass.getDriver());
//		product.addToCart();
//	}
//
//	@Then("the item name in cart should match the search result")
//	public void the_item_name_in_cart_should_match_the_search_result() {
//	    // Write code here that turns the phrase above into concrete actions
//		String actualName = product.getItemNameInCart();
//		if(actualName.equalsIgnoreCase(expectedName)) {
//			System.out.println("Valid Item");
//		}
//		else {
//			System.out.println("Item name mismatch in cart");
//		}
//	}
//
//	@Then("print the item price")
//	public void print_the_item_price() {
//	    // Write code here that turns the phrase above into concrete actions
//		BaseClass.getLogger().info("Item verified in cart");
//		System.out.println("Final Price: " + product.getItemPrice());
//	    
//	}
//
//}
