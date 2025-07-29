package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.WaitUtil;

public class HomePage extends BasePage {

	JavascriptExecutor js;

	public HomePage(WebDriver driver) {
		super(driver);
		js = (JavascriptExecutor) driver;
	}

	// locators
	@FindBy(xpath = "//div[starts-with(@class, 'jsx-c21ded63fbf3c5d8') and contains(@class, 'maybelater')]/a")
	WebElement mayBeLaterButton;

	@FindBy(xpath = "//div[@class = 'input_location_box ']//input")
	WebElement locationTab;

	@FindBy(xpath = "//div[starts-with(@class, 'input_search_result')]//li[2]/a")
	WebElement firstLocationDropDown;

	@FindBy(xpath = "//div[contains(@class, 'input_seach_box_results')]/input")
	WebElement searchTab;

	@FindBy(xpath = "//div[starts-with(@class, 'input_search_result')]//li[1]")
	WebElement searchfirstDropDown;

	@FindBy(xpath = "//div[@class='search_button']")
	WebElement searchButton;

	@FindBy(xpath = "//div[starts-with(@class, 'jd_floatbox')]/a[1]")
	WebElement freeListingList;

	@FindBy(xpath = "//button[starts-with(@id,'tab')]")
	List<WebElement> subMenu;

	@FindBy(xpath = "//div[@id = 'panel4']//a")
	List<WebElement> gymList;

	@FindBy(xpath = "//*[@id='home-page-container']/div[3]/span")
	WebElement closeButton;

	@FindBy(xpath = "//button[@id='popular_categories']")
	WebElement popularCategoryButton;

	@FindBy(xpath = "//input[@placeholder='Search']")
	WebElement searchBox;

	@FindBy(xpath = "//div[contains(@class,'sidemenu_text')]")
	List<WebElement> searchResults;

	@FindBy(xpath = "//div[contains(@class, 'home_billtravel_li_img')]//following-sibling::div[1]")
	List<WebElement> serviceMenu;

	@FindBy(xpath = "//div[contains(@class, 'jsx-af15f929d7668785') and contains(@class, 'dtlboxleft_headbox')]")
	WebElement moviesSection;

	// actions
	@FindBy(xpath = "//button[@id='hk_srchbtn']")
	WebElement searchResultClose;

	@FindBy(xpath = "//a[contains(@title, 'Explore Top Tourist')]//div[contains(@class, 'locality__name')]")
	List<WebElement> topTouristPlaces;

	// actions
	public void clickMayBeLaterButton() {
		WaitUtil.waitForOneElement(driver, mayBeLaterButton, 30);
		mayBeLaterButton.click();
	}

	public void setLocationName(String location) {
		locationTab.sendKeys(location);
	}

	public void clickLocationFirstDropDown() {
		firstLocationDropDown.click();
	}

	public void setSearch(String search) {
		searchTab.sendKeys(search);
	}

	public void clickSearchFirstDropDown() {
		searchfirstDropDown.click();
	}

	public void clickFreeListingList() {
		freeListingList.click();
	}

	public void setSubMenu(String menu) {
		for (int i = 0; i < 20; i++) {
			try {
				if (subMenu.get(0).isDisplayed()) {
					js.executeScript("arguments[0].scrollIntoView({block: 'center'});", subMenu.get(0));
					for (WebElement x : subMenu) {
						if (x.getText().contains(menu)) {
							x.click();
							break;
						}
					}
					break;
				}
			} catch (Exception e) {
				// Element not found yet, scroll down further
				js.executeScript("window.scrollBy(0, 300);");
			}
		}
	}

	public List<String> getGymSubMenuList() {
		List<String> result = new ArrayList<>();
		for (WebElement x : gymList) {
			String value = x.getText().toLowerCase();
			if (value.contains("gym") || value.contains("fitness")) {
				result.add(x.getText());
			}
		}
		return result;
	}

	public void clickServiceMenu(String menu) {
		for (WebElement x : serviceMenu) {
			String value = x.getText();
			if (value.contains(menu)) {
				x.click();
				break;
			}
		}
	}

	public void clickCloseButton() {
		closeButton.click();
	}

	public void clickPopularCategoryButton() {
		popularCategoryButton.click();
	}

	public void setSearchValue(String search) {
		searchBox.sendKeys(search);
	}

	public List<String> getCategorySearchResults() {
		List<String> results = new ArrayList<>();
		for (WebElement x : searchResults) {
			results.add(x.getText());
		}
		return results;
	}

	public void clickSearchButton() {
		searchButton.click();
	}

	public void clickMoviesSection() {
		moviesSection.click();
	}

	public void clickSearchResultClose() {
		searchResultClose.click();
	}
	
	public void clickTopTouristPlace() {
	    try {
	        List<WebElement> freshList = topTouristPlaces;
	        if (!freshList.isEmpty()) {
	            freshList.get(0).click();
	        } else {
	            System.out.println("No tourist places found to click.");
	        }
	    } catch (StaleElementReferenceException e) {
	        List<WebElement> retryList = topTouristPlaces;
	        if (!retryList.isEmpty()) {
	            retryList.get(0).click();
	        }
	    }
	}


}
