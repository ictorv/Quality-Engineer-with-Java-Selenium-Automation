/*
 * File Name: EduVidyaHomePage.java
 * Description:
 * This class represents the Page Object Model (POM) for the EduVidya homepage.
 * It provides methods to interact with the UI elements required for performing
 * a school search based on course type and city.

 * Main Objective:
 * Encapsulates all web elements and actions related to the homepage, including:
 * - Navigating through the menu
 * - Selecting course type and city from dropdowns
 * - Clicking the search button
 * - Verifying if search results are displayed

 * Methods:
 * - clickSubMenu(String subMenu): Clicks on the specified submenu item.
 * - selectCourseType(String course): Selects a course type from the dropdown.(CBSE)
 * - selectCity(String cityVal): Selects a city from the dropdown.(PUNE)
 * - clickSearchBtn(): Clicks the search button to initiate the search.
 * - verifySearchResult(): Checks if search results are displayed and returns a message.
 */

package searchForSchools;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import utils.WaitUtil;

public class EduVidyaHomePage {

	WebDriver driver;

	public EduVidyaHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@id='cssmenu']//li/a")
	List<WebElement> menuBar;

	@FindBy(id = "ddl_Category")
	WebElement courseType;

	@FindBy(id = "ddl_City")
	WebElement city;

	@FindBy(id = "btnSearch")
	WebElement searchbtn;

	@FindBy(css = "div[class='contentblock']")
	List<WebElement> searchResult;

	public void clickSubMenu(String subMenu) {
		for (WebElement x : menuBar) {
			if (x.isDisplayed() && x.getText().equalsIgnoreCase(subMenu)) {
				x.click();
				break;
			}
		}
		System.out.println("Clicked the '" + subMenu + "' link in 'Menu bar'....");
	}

	public void selectCourseType(String course) {

//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ddl_Category")));

		WaitUtil.waitForOneElement(driver, courseType);

		Select selectCourse = new Select(courseType);
		selectCourse.selectByVisibleText(course);
		System.out.println("Selected '" + course + "' in 'Course Type' drop down....");
	}

	public void selectCity(String cityVal) {
		Select selectCity = new Select(city);
		selectCity.selectByVisibleText(cityVal);
		System.out.println("Selected '" + cityVal + "' in 'City' drop down....");
	}

	public void clickSearchBtn() {

//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.visibilityOf(searchbtn));

		WaitUtil.waitForOneElement(driver, searchbtn);

		searchbtn.click();
		System.out.println("Clicked the 'Search' button....");
	}

	public String verifySearchResult() {

//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class = 'heading']")));

		WebElement resultElmt = driver.findElement(By.xpath("//div[@class = 'heading']"));
		WaitUtil.waitForOneElement(driver, resultElmt);

		if (!searchResult.isEmpty()) {
			System.out.println("The Search Results are displayed....");
			return "The Search Results are displayed....";
		} else {
			System.out.println("The Search Results are not displayed....");
		}
		return "The Search Results are not displayed....";
	}

}

