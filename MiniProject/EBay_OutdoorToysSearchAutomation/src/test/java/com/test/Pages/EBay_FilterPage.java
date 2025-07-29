package com.test.Pages;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class EBay_FilterPage {
	WebDriver driver;
	
	//Constructors
	public EBay_FilterPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Locators
	@FindBy(xpath = "/html/body/div[2]/div/main/form/fieldset[1]/div[1]/span/span/input")
	WebElement keywordName_loc;
	@FindBy(xpath = "//select[@name='_in_kw']")
	WebElement keywordOption_loc;
	@FindBy(xpath = "//span//select[@name='_sacat']")
	WebElement catagoryDropDown_loc;
	@FindBy(xpath = "//input[@name='LH_TitleDesc']")
	WebElement searchIncludeButton_loc;
	@FindBy(xpath = "//fieldset//div[1]//span//input[@value='LH_ItemCondition']")
	WebElement newCheckbox_loc;
	@FindBy(xpath = "//fieldset[6]//input[@name='LH_FR']")
	WebElement freeReturnCheckbox_loc;
	@FindBy(xpath = "//fieldset[6]//input[@name='LH_RPA']")
	WebElement returnAccpectCheckbox_loc;
	@FindBy(xpath = "//fieldset[8]//div[4]//input[@value='LH_PrefLoc']")
	WebElement worldwideselect_loc;
	@FindBy(xpath = "//fieldset//div[5]//button")
	WebElement searchBotton_loc;
	
	//Actions
	//1)Enter "outdoor toys" in the Enter keywords or item number textbox and select “Any words, any order” in the list box next the textbox.
	public void eSendKeys(String item) {
		keywordName_loc.sendKeys(item);
		WebElement options1 = keywordOption_loc;
		Select dropDown1 = new Select(options1);
		List<WebElement> list1 = dropDown1.getOptions();
		for(WebElement option1 : list1) {
			if(option1.getText().equals("Any words, any order")){
				option1.click();
				break;
			}
		}
	}
	
	// 2) Select "Toys & Hobbies" under the "In this category" column.
	public void category(String catItem){
		WebElement options2 = catagoryDropDown_loc;
		Select dropDown2 = new Select(options2);
		List<WebElement> list2 = dropDown2.getOptions();
		for(WebElement option2 : list2) {
			if(option2.getText().equals(catItem)){
				option2.click();
				break;
			}
		}
	}
	
	// 3)Select the "Title and description", checkbox under the “Search Including” column.
	public void selectCheckbox1() {
		searchIncludeButton_loc.click();
	}
	
	// 4)Select the checkBox “New” under Condition.
	public void selectCheckbox2() {
		newCheckbox_loc.click();
	}
	
	// 5)Select "Free returns" under “Show Results” column.
	public void selectCheckbox3() {
		freeReturnCheckbox_loc.click();
	}
	// 6) Select "Returns accepted" under “Show Results” column.
	public void selectCheckbox4() {
		returnAccpectCheckbox_loc.click();
	}
	
	// 7) Under Location, Select From preferred locations as “Worldwide”.
	public void selectCheckbox5() {
		worldwideselect_loc.click();
	}
	
	//8) 10)Click on “Search” button.
	public void hitSearch() {
		searchBotton_loc.click();
	}
}
