package com.urban.pageObject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.urban.utillities.ExcelUtil;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='search']")
	WebElement searchField;
	@FindBy(xpath = "//span[@class='search-icon icofont-search']")
	WebElement searchIcon;

	// Made By Abhishek
	@FindBy(xpath = "//h4[normalize-space()='Bookshelves']")
	WebElement bookshelfLink;

	@FindBy(xpath = "//li[@class='topnav_item storesunit']/span")
	WebElement storesTab;

	@FindBy(xpath = "//li[contains(@class,'storesunit')]/div/div/ul/li/div/a[not(contains(text(),'Products'))]")
	List<WebElement> statesList;
	// Abhishek End

	// Jain open(Social Media)
	@FindBy(className = "close-reveal-modal")
	WebElement popupClose;

	@FindBy(id = "footer-social")
	WebElement footerSocial;
	// Jain end

	// Jain Open(AutoSuggestion)

	@FindBy(className = "close-reveal-modal")
	WebElement closePopupBtn;
	@FindBy(id = "search")
	WebElement searchBox;
	@FindBy(xpath = "//div[@class='tt-suggestion tt-selectable']")
	List<WebElement> suggestionList;
	//Jain end
	
	//Tathagat open(Login)
	@FindBy(xpath = "//span[@class='header-icon-link user-profile-icon']//*[name()='svg']")
    WebElement profileIcon;
 
    @FindBy(xpath = "//a[@id='header-icon-login']")
    WebElement loginButton;
 
    @FindBy(xpath = "//a[@id='logout']")
    WebElement logoutButton;
    
    @FindBy(xpath="//a[@class='inherit contact-channel'][normalize-space()='Help']")
    WebElement help;
    //Tathagat end(Login)
    
    
    
//--------------------------------------------------
    
    //Aashray open(Sofas & Recliner)
    @FindBy(xpath=("//li[@class = 'topnav_item sofasunit']//span[@class = 'topnav_itemname']"))
	WebElement sofaBtn;
	
	@FindBy(xpath=("//li[@class = 'topnav_item sofasunit']//li[@class = 'sublist_item']//div[@class = 'taxontype']"))
	List<WebElement> headings;
	//Aashray end(Sofas & Recliner)
	
	//Aashray open (Track order)
	@FindBy(xpath=("//a[@id='header-icon-track-order']"))
	WebElement trackBtn;
	//Aashray close (Track order)
	
	//--------------------------------------------------

	public void searchField(String query) {
		searchField.sendKeys(query);
	}

	public void clickSearchIcon() {
		searchIcon.click();
	}

	// Made By Abhishek
	public void clickBookshelf() {
		bookshelfLink.click();
	}

	public void hoverOverStoresTab() {
		Actions actions = new Actions(driver);
		actions.moveToElement(storesTab).build().perform();
	}

	public List<String> getStoreStates() throws InterruptedException {
		Thread.sleep(5000);
	    List<String> states = new ArrayList<>();
	    for (WebElement state : statesList) {
	        String name = state.getText();
	        if (name != null && !name.trim().isEmpty()) {
	            states.add(name.trim());
	            System.out.println(name.trim());
	        } else {
	            System.out.println("[Skipped empty or null state element]");
	        }
	    }
	    return states;
	

	
	
	
	
	
//	public List<String> getStoreStates() {
//		List<String> states = new ArrayList<String>();
//		for (WebElement state : statesList) {
//			states.add(state.getText());
//			System.out.println(state.getText());
//
//		}
//		return states;
	}
	// abhishek end

	// Jain open(Social media)
	public void scrollToFooter() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", footerSocial);
		Thread.sleep(1500);
	}

	public boolean validateSocialLink(String name, String xpath) throws InterruptedException {
		String originalWindow = driver.getWindowHandle();

		try {
			WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
			link.click();
			Thread.sleep(2000);

			for (String handle : driver.getWindowHandles()) {
				if (!handle.equals(originalWindow)) {
					driver.switchTo().window(handle);
					break;
				}
			}

			Thread.sleep(3000);
			String url = driver.getCurrentUrl().toLowerCase();
//			String title = driver.getTitle().toLowerCase();
			System.out.println(url);
			return url.contains(name);

		} catch (Exception e) {
			return false;
		} finally {
			for (String win : driver.getWindowHandles()) {
				if (!win.equals(originalWindow)) {
					driver.close();
				}
			}
			driver.switchTo().window(originalWindow);
		}
	}
	// Jain end

	// Jain(AutoSuggestion Methods)
	public void closePopupIfVisible() {
		try {
			WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(closePopupBtn));
			closeBtn.click();
			System.out.println("Popup closed successfully.");
		} catch (Exception e) {
			System.out.println("No popup appeared.");
		}
	}

	public void search(String keyword) {
		searchBox.sendKeys(keyword);
	}

	public List<WebElement> getSuggestions() {
		return suggestionList;
	}
	//Jain close
	//Tathagat open
	public WebElement getLoginButton() {
        return loginButton;
    }
 
    public void hoverOverProfileIcon() {
        new Actions(driver).moveToElement(profileIcon).perform();
    }
 
    public void clickLogin() {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("arguments[0].click();", loginButton);
    }
 
    public void clickLogout() {
        logoutButton.click();
    }
    public void helpclick() {
    	help.click();
    }
    //Tathagat end
    
    
//--------------------------------------------------
    
    //Aashray open (Sofas & Recliners)
    public void moveToElement() {
		action.moveToElement(sofaBtn).perform();
	}
    
    public void listItems() {
		List<List<WebElement>> itemsList = new ArrayList<>();
		wait.until(ExpectedConditions.visibilityOf(headings.get(0)));
		for(int i = 1 ; i <= headings.size() ; i++) {
			List<WebElement> Element = driver.findElements(By.xpath("//li[@class = 'topnav_item sofasunit']//ul[@class = 'inline-list left']//li["+i+"]//ul//li"));
			itemsList.add(Element);
		}
		
		List<WebElement> items = new ArrayList<>();
		int c = 0;
		for(int i = 0 ; i < itemsList.size() ; i++) {
			int r = 1;
			items = itemsList.get(i);
			for(int j = 0 ; j < items.size() ; j++) {
				ExcelUtil.writeDataIntoExcel("SofasAndRecliners", r, c, items.get(j).getText());
				r++;
			}
			c++;
		}
		
//		return itemsList;
	}
    //Aashray end (Sofas & Recliners)
    
    //Aashray open (Track order)
    public void click() {
		trackBtn.click();
	}
    // Rest code in track details POM
    //Aashray close (Track Order)
    
    //--------------------------------------------------
 
    
    
    
    
    
    
    
}