package com.test.objRepo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/*
* This class encapsulates all WebElements and user actions required to perform a train search
* on the ERail platform.It uses Selenium's PageFactory to locate the elements and 
* It supports interacting with source and destination fields, selecting
* travel dates via the calendar widget, choosing quotas and travel classes from dropdowns,
* and triggering the search via the "Get Trains" button.
*/

public class ERail_HomePage {
	WebDriver driver;
	Actions action;

	public ERail_HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Locating the Source Station Textbox
	@FindBy(xpath = "//*[@id = 'txtStationFrom']")
	WebElement fromStation;
	
	//Locating the Destination Station Textbox
	@FindBy(xpath = "//*[@id = 'txtStationTo']")
	WebElement toStation;
	
	//Locating the calender
	@FindBy(xpath = "//input[@type='button' and contains(@title,'date')]")
	WebElement calendar;
	
	@FindBy(xpath = "//tbody//table[contains(@class,'Month')]//td[contains(@style,'text-align:right')]")
	List<WebElement> monList;
	
	//Locating the quota dropdown list
	@FindBy(xpath  = "//*[@id = 'cmbQuota']")
	WebElement quotaDropdown;
	
	//Locating the class dropdown list
	@FindBy(xpath = "//*[@id=\"selectClassFilter\"]")
	WebElement classDropdown;

	//Locating the Get Trains button
	@FindBy(xpath = "//*[@id=\"buttonFromTo\"]")
	WebElement getTrainsButton;
	
	//Entering the source Station in the textbox
	public void enterSourceStation(String sourceStation) {
		action = new Actions(driver);
		action.keyDown(fromStation, Keys.CONTROL)
				.sendKeys("a")
				.keyUp(Keys.CONTROL)
				.keyDown(fromStation,Keys.BACK_SPACE)
				.build().perform();
		fromStation.sendKeys(sourceStation);
	}
	
	//Clicking the appropriate source station from Suggestions
	public void clickSourceStation(String source) {
		WebElement sourceStat = driver.findElement(By.xpath("//div[@class='autocomplete']/div[@title='" + source + "']"));
		sourceStat.click();
	}
	
	//Entering the destination Station in the textbox
	public void enterDestinationStation(String destinationStation) {
		action = new Actions(driver);
		action.keyDown(toStation, Keys.CONTROL)
				.sendKeys("a")
				.keyUp(Keys.CONTROL)
				.keyDown(toStation,Keys.BACK_SPACE)
				.build().perform();
		toStation.sendKeys(destinationStation);
	}
	
	//Clicking the appropriate destination station form Suggestions
	public void clickDestinationStation(String destination) {
		WebElement destinationStat = driver.findElement(By.xpath("//div[@class='autocomplete']/div[@title='" + destination + "']"));
		destinationStat.click();
	}
	
	//Clicking on the calender Input Field
	public void clickonCalender() {
		calendar.click();
	}
	
	//Selecting the date(According to the Month and Year then Date
	public void selectDate(String monthYear,String date) {
		boolean flag = false;
		for(WebElement month : monList) {
			String mon = month.getText();
			if(mon.equals(monthYear)) {
				flag = true;
				System.out.println("Valid Month : " + monthYear);
				WebElement datePicker = driver.findElement(By.xpath("(//table//tbody//td[text()='" + monthYear + "']/../following-sibling::tr/td[text()='" + date + "'])[1]"));
				datePicker.click();
			}
		}
		
		if(flag == false) {
			System.out.println("Invalid Month. Please select the correct date");
		}
	}
	
	//Selecting the specified quota
	public void selectQuota(String quota) {
		new Select(quotaDropdown).selectByVisibleText(quota);
	}

	//Selecting the class
	public void selectClass(String travelClass) {
		new Select(classDropdown).selectByVisibleText(travelClass);
	}

	//Selecting on Get Trains Button
	public void clickGetTrains() {
		getTrainsButton.click();
	}
	
}
