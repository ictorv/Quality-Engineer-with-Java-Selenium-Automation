package com.FlightSearchAutomation;

//Importing the  Necessary packages for functionality..
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.TestUtil.ExcelUtil3;
import com.TestUtil.ScreenShot;
import com.TestUtil.WaitUtils;


//The class Handling the Flight booking.
public class IRCTCFlightBooking {

	WebDriver driver;

	@FindBy(id = "stationFrom")
	WebElement fromCity;
	@FindBy(id = "stationTo")
	WebElement toCity;
	@FindBy(id = "originDate")
	WebElement datePicker;
	@FindBy(id = "noOfpaxEtc")
	WebElement classDropdown;
	@FindBy(id = "travelClass")
	WebElement travelClass;
	@FindBy(xpath = "//div[@class='form-in-custom3']/button[contains(@class,'yellow-gradient')]")
	WebElement searchButton;

	@FindBy(xpath = "//*[@id='carouselExampleInterval']//form")
	WebElement homePageForm;
	@FindBy(xpath = "//ul[contains(@id,'ui-id')]/li/div")
	List<WebElement> fromCityOptions;
	@FindBy(xpath = "//ul[contains(@id,'ui-id')]/li")
	List<WebElement> toCityOptions;
	@FindBy(xpath = "//*[@id='carouselExampleInterval']/div[1]/div/div/div[2]/form/div[3]/datepickermodifi/div/div[2]/div[2]/table/tbody/tr/td/span")
	List<WebElement> dateOptions;
	@FindBy(xpath = "//*[@id='TravellerEconomydropdown']//option")
	List<WebElement> classOptions;
	@FindBy(xpath = "//div[@class='col-12 col-md-9 right-searchbar']")
	WebElement resultsElement;
	@FindBy(xpath = "//div[@class='right-searchbarbtm']")
	List<WebElement> flights;

	@FindBy(xpath = "//*[@id=\"carouselExampleInterval\"]/div[1]/div/div/div[2]/form/div[3]/datepickermodifi/div/div[2]/div[1]/table/tr[3]/td/span[1]")
	List<WebElement> months;
	@FindBy(xpath = "//*[@id=\"carouselExampleInterval\"]/div[1]/div/div/div[2]/form/div[3]/datepickermodifi/div/div[2]/div[1]/table/tr[3]/td/sub")
	List<WebElement> year;

	
	// Constructor to initialize the WebDriver and PageFactory elements
	public IRCTCFlightBooking(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Navigates to the IRCTC flight booking homepage and waits for the form to load
	public void loadHomePage() {
		driver.get("https://www.air.irctc.co.in/");
		WaitUtils.waitForVisibility(driver, homePageForm, 15);
	}
	
	// Selects the "From" city based on a given input and clicks the appropriate suggestion
	public void selectFromCity(String city, String fullTextMatch) {
		fromCity.click();
		fromCity.clear();
		fromCity.sendKeys(city);
		WaitUtils.waitForDuration(driver, 1);

		for (WebElement option : fromCityOptions) {
			if (option.getText().contains(fullTextMatch)) {
				option.click();
				break;
			}
		}
	}
	
	// Selects the "To" city similarly to the from-city logic

	public void selectToCity(String city, String fullTextMatch) {
		toCity.click();
		toCity.clear();
		toCity.sendKeys(city);
		WaitUtils.waitForDuration(driver, 1);

		for (WebElement option : toCityOptions) {
			if (option.getText().contains(fullTextMatch)) {
				option.click();
				break;
			}
		}
	}
	// Opens the date picker and selects today's date if specified
	public void selectTodayDate(String date) {
		if (date.contains("TODAY")) {
			datePicker.click();

			String today = String.valueOf(LocalDate.now().getDayOfMonth()).trim();

			for (WebElement day : dateOptions) {
				String classes = day.getDomAttribute("class");
				String dayText = day.getText().trim();

				// Only click if it's not dimmed and text matches today's date
				if (!classes.contains("dimmed") && dayText.startsWith(today)) {
					day.click();
					break;
				}

			}
		}
			
		
	}
	
	
	
	// Selects the desired travel class from the dropdown

	public void selectTravelClass(String className) {
		classDropdown.click();
		travelClass.click();
		for (WebElement option : classOptions) {
			if (option.getText().equals(className)) {
				option.click();
				break;
			}
		}
	}
	
	// Clicks the search button, fetches the list of flights, and stores them in an Excel sheet

	public void clickSearch() throws IOException {
		WaitUtils.waitForClickability(driver, searchButton, 10);
		searchButton.click();
		System.out.println("Total Flights Found: " + flights.size());

		String filePath = "src/test/resources/Book1.xlsx";
		String sheetName = "Sheet2";

		// Clear old data before writing new
		ExcelUtil3.clearSheetData(filePath, sheetName);

		String size = "Total available flights are " + flights.size();
		ExcelUtil3.putCellData(filePath, sheetName, 0, 0, size);

		for (int i = 0; i < flights.size(); i++) {
			WebElement flight = flights.get(i);
			String airlineName = flight.findElement(By.xpath(".//b")).getText();
			String flightNumbers = flight.findElement(By.xpath(".//span")).getText();

			System.out.println("Airline: " + airlineName + " | Flight No(s): " + flightNumbers);

			ExcelUtil3.putCellData(filePath, sheetName, i + 1, 0, airlineName);
			ExcelUtil3.putCellData(filePath, sheetName, i + 1, 1, flightNumbers);
		}

		System.out.println("Flight data written to Excel successfully.");
	}
	
	// After search results load, takes a screenshot of the search results section

	public void afterSearch() {
		WaitUtils.waitForVisibility(driver, resultsElement, 20);
		try {
			ScreenShot.screenShotTC(driver, "Search");
		} catch (IOException e) {
			System.out.println("Screenshot failed: " + e.getMessage());
		}
	}
}

	