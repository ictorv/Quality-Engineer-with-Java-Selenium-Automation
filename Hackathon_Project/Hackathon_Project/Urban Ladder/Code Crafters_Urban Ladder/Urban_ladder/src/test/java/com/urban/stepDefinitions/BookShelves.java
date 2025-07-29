package com.urban.stepDefinitions;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.urban.pageObject.BasePage;
import com.urban.pageObject.BookshelfPage;
import com.urban.utillities.ExcelUtil;
import com.urban.utillities.ScreenShot;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BookShelves {

    BookshelfPage bookshelf;
    LinkedHashMap<String, String> topProducts;
    private static final Logger logger = LogManager.getLogger(BookShelves.class);

    @When("User navigates to Bookshelves section from homepage")
    public void clickBookshelves() {
        logger.info("Navigating to Bookshelves section from homepage.");
        BaseStep.homePageObject.clickBookshelf();
    }

    @And("User handles any popup appearing on screen")
    public void handlePopup() {
        logger.info("Handling popups on screen.");
        BasePage.popupHandle();
    }

    @And("User sets price filter for bookshelves under 15,000")
    public void setPriceFilter() {
        logger.info("Setting price filter for bookshelves under ₹15,000.");
        bookshelf = new BookshelfPage(BasePage.driver);
        bookshelf.setPriceFilter();
    }

    @And("User selects Open Storage type from bookshelf categories")
    public void selectOpenStorageType() {
        logger.info("Selecting 'Open Storage' category from bookshelf types.");
        bookshelf.selectOpenStorageType();
    }

    @And("User filters results to show only In Stock items")
    public void filterInStockItems() {
        logger.info("Filtering bookshelves to show only In Stock items.");
        bookshelf.filterInStock();
    }

    @And("User fetches top {int} bookshelf products with price")
    public void fetchTopProducts(int count) throws InterruptedException {
        logger.info("Fetching top {} bookshelf products with price.", count);
        topProducts = bookshelf.getTopProducts(count);
        ScreenShot.captureScreenshot(BasePage.driver, "Products");
        logger.info("Screenshot captured for filtered products.");
    }

    @Then("Product details are written to BookShelves Excel sheet")
    public void writeProductsToExcel() {
        logger.info("Writing product details to BookShelves Excel sheet.");
        int r = 0;
        for (Map.Entry<String, String> entry : topProducts.entrySet()) {
            int rowNum = r + 1;
            ExcelUtil.writeDataIntoExcel("BookShelves", rowNum, 0, entry.getKey());
            ExcelUtil.writeDataIntoExcel("BookShelves", rowNum, 1, entry.getValue());
            logger.debug("Written to Excel: {} - ₹{}", entry.getKey(), entry.getValue());
            r++;
        }
    }
}
































































//package com.urban.stepDefinitions;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//import com.urban.pageObject.BasePage;
//import com.urban.pageObject.BookshelfPage;
//import com.urban.utillities.ExcelUtil;
//import com.urban.utillities.ScreenShot;
//
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//
//public class BookShelves
//{
//	BookshelfPage bookshelf;
//	LinkedHashMap<String,String> topProducts;
//	
//	@When("User navigates to Bookshelves section from homepage")
//	public void clickBookshelves() {
//		BaseStep.homePageObject.clickBookshelf();
//	}
//
//	@And("User handles any popup appearing on screen")
//	public void handlePopup()  {
//	    BasePage.popupHandle();
//	}
//
//	@And("User sets price filter for bookshelves under 15,000")
//	public void setPriceFilter()  {
//		bookshelf=new BookshelfPage(BasePage.driver);
//		bookshelf.setPriceFilter();
//	}
//
//	@And("User selects Open Storage type from bookshelf categories")
//	public void selectOpenStorageType() {
//		bookshelf.selectOpenStorageType();
//	}
//
//	@And("User filters results to show only In Stock items")
//	public void filterInStockItems() {
//		bookshelf.filterInStock();
//	}
//
//	
//	@And("User fetches top {int} bookshelf products with price")
//	public void fetchTopProducts(int count) throws InterruptedException {
//	    topProducts = bookshelf.getTopProducts(count);
//	    ScreenShot.captureScreenshot(BasePage.driver, "Products");
//	}
//
//	@Then("Product details are written to BookShelves Excel sheet")
//	public void writeProductsToExcel() {
//	    int r = 0;
//	   
//	    for (Map.Entry<String, String> entry : topProducts.entrySet()) {
//			int rowNum = r + 1;
//	        ExcelUtil.writeDataIntoExcel("BookShelves", rowNum , 0, entry.getKey());
//	        ExcelUtil.writeDataIntoExcel("BookShelves", rowNum , 1, entry.getValue());
//	        r++;
//	    }
//	}
//
//}
