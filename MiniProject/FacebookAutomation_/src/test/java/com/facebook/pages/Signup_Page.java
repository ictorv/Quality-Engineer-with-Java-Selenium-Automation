package com.facebook.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class Signup_Page {

	public WebDriver driver;

	@FindBy(name = "firstname")
	public WebElement firstName;

	@FindBy(name = "lastname")
	public WebElement lastName;

	@FindBy(id = "day")
	public WebElement dayDropdown;

	@FindBy(id = "month")
	public WebElement monthDropdown;

	@FindBy(id = "year")
	public WebElement yearDropdown;

	@FindBy(css = "input[name='sex'][value='1']")
	public WebElement femaleGenderBtn;

	@FindBy(css = "input[name='sex'][value='2']")
	public WebElement maleGenderBtn;

	@FindBy(css = "input[name='sex'][value='-1']")
	public WebElement customGenderBtn;

	@FindBy(name = "reg_email__")
	public WebElement mobileNumber;

	@FindBy(name = "websubmit")
	public WebElement signupBtn;

	@FindBy(css = "div._5633._5634._53ij")
	public WebElement passwordError;

	@FindBy(xpath = "//div/div[@class='_5dbb _5634']/i")
	public WebElement errorIcon;

	@FindBy(xpath = "(//div[@class='_5v-0 _53im']/div[starts-with(@id, 'js_')])[2]")
	public WebElement mobileError;

	public Signup_Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void fillFirstname(String fName) {
		firstName.sendKeys(fName);

	}

	public void fillLastname(String lName) {
		lastName.sendKeys(lName);

	}

	public void fillDate(String day, String month, String year) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			wait.until(ExpectedConditions.elementToBeClickable(dayDropdown));
			new Select(dayDropdown).selectByVisibleText(day);

			wait.until(ExpectedConditions.elementToBeClickable(monthDropdown));
			new Select(monthDropdown).selectByVisibleText(month);

			wait.until(ExpectedConditions.elementToBeClickable(yearDropdown));
			new Select(yearDropdown).selectByVisibleText(year);
		} catch (Exception e) {
			System.err.println("Error selecting date of birth: " + e.getMessage());
		}
	}

	public void selectGender(String gender) {

		gender = gender.trim();
		if (gender.equalsIgnoreCase("female")) {
			femaleGenderBtn.click();
		} else if (gender.equalsIgnoreCase("male")) {
			maleGenderBtn.click();
		} else if (gender.equalsIgnoreCase("custom")) {
			customGenderBtn.click();
		} else {
			System.err.println("SET VALID GENDER VALUE ");
		}

	}

	public void fillMobileNumber(String number) {
		mobileNumber.sendKeys(number);
	}

	public void submitDetails() {
		signupBtn.click();
	}

	public String getPasswordErrorMsg() {		
		return passwordError.getText();
	}

	public String getMobileErrorMsg() {
		errorIcon.click();
		return mobileError.getText();

	}

}
