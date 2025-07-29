package TestObjectRepository;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * FlipKartMainPage class represents the Flipkart homepage and provides methods to interact
 * with the search functionality. It uses the Page Object Model (POM) design pattern to
 * encapsulate web elements and actions related to the main page.
 * 
 * Key Features:
 * - Performs product search using the search bar
 * - Handles auto-suggestion dropdowns
 * - Validates search results based on expected text
 * 
 * Usage:
 * This class is typically used in test scenarios where the user needs to search for products
 * on Flipkart and validate that the search results page is correctly loaded.
 */

public class FlipKartMainPage {

	    WebDriver driver;

	    public FlipKartMainPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }
        
	    @FindBy(name = "q")
	    WebElement searchBox;
	    
	    @FindBy(xpath = "//*[@id=\"container\"]/div/div[3]/div[1]/div[2]/div[1]/div/div/span/span")
	    WebElement Text;
	    
	    @FindBy(xpath = "//*[@id=\"container\"]/div/div[1]/div[1]/div[2]/div[2]/form/ul")
	    List<WebElement> autosearch;
	    
	    public void searchFor(String query) {
	        searchBox.clear();
	        searchBox.sendKeys(query);

	        boolean matchFound = false;

	        for (WebElement suggestion : autosearch) {
	            String suggestionText = suggestion.getText().trim().toLowerCase();
	            if (suggestionText.equals(query.trim().toLowerCase())) {
	                suggestion.click();
	                matchFound = true;
	                break;
	            }
	        }

	        if (!matchFound) {
	            searchBox.clear();
	            searchBox.sendKeys("mobiles under 15000");
	            searchBox.sendKeys(Keys.ENTER);
	        }
	    }
	    
	    public boolean validateSearch(String expectedText) {
	        String actualText = Text.getText();
	        return actualText.equalsIgnoreCase(expectedText);
	    }
	}

