package pages;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.WaitUtil;

public class MoviesPage extends BasePage{
	
	public MoviesPage(WebDriver driver) {
		super(driver);
	}
	
	//locators
	@FindBy(xpath="//div[contains(@class, 'jsx-158093aea8ca66f1') and contains(@class, 'movies_heading')]")
	WebElement headingTag;
	
	@FindBy(xpath="//input[@id='10003-comedy']/following-sibling::span[contains(@class,'movfilter_icon')]")
	WebElement comedyJonourFilterCheckBox;
	
	@FindBy(xpath="//input[contains(@id,'10001-')]/following-sibling::span[contains(@class,'movfilter_icon')]")
	List<WebElement> languagesCheckBox;
	
	@FindBy(xpath="//input[contains(@id,'10001-')]/following-sibling::span[contains(@class,'movfilter_text')]")
	List<WebElement> languagesNames;
	
	@FindBy(xpath="//input[contains(@id,'10002-')]/following-sibling::span[contains(@class,'movfilter_icon')]")
	List<WebElement> formatCheckBox;
	
	@FindBy(xpath="//input[contains(@id,'10002-')]/following-sibling::span[contains(@class,'movfilter_text')]")
	List<WebElement> formatNames;
	
	@FindBy(xpath="//input[contains(@id,'10003-')]/following-sibling::span[contains(@class,'movfilter_icon')]")
	List<WebElement> jonourCheckBox;
	
	@FindBy(xpath="//input[contains(@id,'10003-')]/following-sibling::span[contains(@class,'movfilter_text')]")
	List<WebElement> jonourNames;
	
	@FindBy(xpath="//div[text()='Apply']")
	WebElement applyFiltersButton;
	
	@FindBy(xpath="//div[contains(@class, 'jsx-158093aea8ca66f1') and contains(@class, 'movies_heading')]")
	WebElement filteredHeadingTag;
	
	@FindBy(xpath="(//div[contains(@class,'jsx-158093aea8ca66f1') and contains(@class,'movies_box')])[2]")
	WebElement firstMovie;
	
	//actions
	public WebElement getComedyJonourFilterCheckBox() {
		return comedyJonourFilterCheckBox;
	}
	
	public void waitUntilHeadingTagAppears() {
		WaitUtil.waitForOneElement(driver, headingTag, 10);
	}
	
	public void clickChosenLanguages(String choices) {
	    Set<String> selectedChoices = new HashSet<>(Arrays.asList(choices.split(",")));

	    for (int i = 0; i < languagesCheckBox.size(); i++) {
	        String currentLang = languagesNames.get(i).getText().trim();
	        if (selectedChoices.contains(currentLang.toLowerCase())) {
	            if (!languagesCheckBox.get(i).isSelected()) {
	                languagesCheckBox.get(i).click();
	            }
	        }
	    }
	}
	
	public void clickChosenFormat(String choices) {
	    Set<String> selectedChoices = new HashSet<>(Arrays.asList(choices.split(",")));

	    for (int i = 0; i < formatCheckBox.size(); i++) {
	        String currentLang = formatNames.get(i).getText().trim();
	        if (selectedChoices.contains(currentLang.toLowerCase())) {
	            if (!formatCheckBox.get(i).isSelected()) {
	            	formatCheckBox.get(i).click();
	            }
	        }
	    }
	}
	
	public void clickChosenJonour(String choices) {
	    Set<String> selectedChoices = new HashSet<>(Arrays.asList(choices.split(",")));

	    for (int i = 0; i < jonourCheckBox.size(); i++) {
	        String currentLang = jonourNames.get(i).getText().trim();
	        if (selectedChoices.contains(currentLang.toLowerCase())) {
	            if (!jonourCheckBox.get(i).isSelected()) {
	            	jonourCheckBox.get(i).click();
	            }
	        }
	    }
	}
	
	public void clickComedyJonourFilterCheckBox() {
		comedyJonourFilterCheckBox.click();
	}
	
	public void clickApplyFilterButton() {
		applyFiltersButton.click();
	}
	
	public void waitUntilFilteredHeadingTagAppears() {
		WaitUtil.waitForOneElement(driver, filteredHeadingTag, 10);
	}
	
	public void clickFirstMovie() {
		firstMovie.click();
	}
	
	
}
