package objectRepository;

import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilityFiles.WaitUtil;

public class SearchMainPage {
	
	WebDriver driver;
	
	//Constructor
	public SearchMainPage(WebDriver driver){
		this.driver= driver;
		PageFactory.initElements(driver,this);	
	}
	
	@FindBy(xpath="/html/body/div[2]/div/div[2]/div/div[2]/div/div[2]/div/div[1]/div/div/form/div/div/div/div/div/input")
	WebElement searchBox;
	
	@FindBy(xpath="//div[@class='q-box' and @role='list']//div[@class = 'q-box puppeteer_test_selector_result']") 
	List<WebElement> searchQueryList;
	
	// //div[@id='selector:1']
	
	public void setSearchInput(String enterSearchItem) {
		//WaitUtil.waitForVisibility(driver,searchBox,30);
		searchBox.click();
		searchBox.sendKeys(enterSearchItem);
	}
	
	public List<WebElement> getSearchList() throws TimeoutException {
	    WaitUtil.waitForVisibility(driver,searchQueryList, 30);
	    return searchQueryList;
	}

	public void clickSearchOption(int position) throws TimeoutException {
	    List<WebElement> suggestions = getSearchList();
	    if (position > 0 && position <= suggestions.size()) {
	        suggestions.get(position - 1).click();
	    } else {
	        throw new IllegalArgumentException("Invalid position: " + position);
	    }
	}
	


}
