
package TestObjectRepository;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

