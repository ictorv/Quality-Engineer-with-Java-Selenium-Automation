package testObjectRepository;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import driverConfiguration.DriverSetup;
import userDefinedLibraries.TakeScreenShot;

public class DemoQaFormWindow {

	WebDriver driver;
	JavascriptExecutor js;
	String selectedDateValue;
	WebDriverWait wait;

	// constructor initiate driver
	public DemoQaFormWindow(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		this.js = (JavascriptExecutor) driver;
		PageFactory.initElements(driver, this);

	}

	// locators
	@FindBy(xpath = "(//*[contains(@class,'btn-light')])[10]")
	WebElement formLeftPanelButton;

	@FindBy(xpath = "//input[@id='firstName']")
	WebElement firstNameInput;

	@FindBy(xpath = "//input[@id='lastName']")
	WebElement lastNameInput;

	@FindBy(xpath = "//input[@id='userEmail']")
	WebElement emailInput;

	@FindBy(xpath = "//input[@id='gender-radio-1']")
	WebElement genderMaleRadioButton;

	@FindBy(xpath = "//input[@id='gender-radio-2']")
	WebElement genderFemaleRadioButton;

	@FindBy(xpath = "//input[@id='gender-radio-3']")
	WebElement genderOtherRadioButton;

	@FindBy(xpath = "//input[@id='userNumber']")
	WebElement mobileNumberInput;

	@FindBy(xpath = "//input[@id='dateOfBirthInput']")
	WebElement dateOfBirthInput;

	@FindBy(xpath = "//input[@id='hobbies-checkbox-1']")
	WebElement hobbySportsCheckbox;

	@FindBy(xpath = "//input[@id='hobbies-checkbox-2']")
	WebElement hobbyReadingCheckbox;

	@FindBy(xpath = "//input[@id='hobbies-checkbox-3']")
	WebElement hobbuMusicCheckbox;

	@FindBy(xpath = "//textarea[@placeholder='Current Address']")
	WebElement currentAddressTextarea;

	@FindBy(xpath = "(//div[@class=' css-1wy0on6'])[1]")
	WebElement stateDropdownElement;

	@FindBy(xpath = "//div[contains(@class,'option')]")
	List<WebElement> availableStateOptions;

	@FindBy(xpath = "(//div[@class=' css-1hwfws3'])[2]")
	WebElement cityDropdown;

	@FindBy(xpath = "//div[contains(@class,'option')]")
	List<WebElement> cityOptions;

	@FindBy(xpath = "//button[@id='submit']")
	WebElement submitButton;

	@FindBy(xpath = "//button[@id='closeLargeModal']")
	WebElement closeButton;

	@FindBy(xpath = "//select[@class='react-datepicker__month-select']")
	WebElement birthMonthDropdown;

	@FindBy(xpath = "//select[@class='react-datepicker__year-select']")
	WebElement birthYearDropdown;

	@FindBy(xpath = "//*[@id='example-modal-sizes-title-lg']")
	WebElement resultModalTitle;

	// actions

	// Navigates back twice in the browser history—used to return to the home page
	public void navigateBackToHome() {

		for (int i = 0; i < 2; i++) {
			driver.navigate().back();
		}

	}

	// Fills out the student registration form using data provided as parameters
	public void fillStudentRegistrationForm(String firstNameData, String lastNameData, String emailData,
			String genderInputData, String mobileNumberData, String dateOfBirthInputData, String hobbyCheckboxData,
			String addressData, String stateData, String cityData) throws IOException {

		// Scroll up the window to make form elements visible
		DriverSetup driverSetup = new DriverSetup();
		driverSetup.windowscroll(driver, -9999);

		// Use JavaScript to interact with fields and populate them with values
		js.executeScript("arguments[0].click();", formLeftPanelButton);
		js.executeScript("arguments[0].value=arguments[1];", firstNameInput, firstNameData);
		js.executeScript("arguments[0].value=arguments[1];", lastNameInput, lastNameData);
		js.executeScript("arguments[0].value=arguments[1];", emailInput, emailData);

		// Select appropriate gender radio button
		selectGender(genderInputData);
		js.executeScript("arguments[0].value=arguments[1];", mobileNumberInput, mobileNumberData);

		driverSetup.windowscroll(driver, 100);
		TakeScreenShot.screenShotTC(driver, "FormFilledPartially");

		// Select birth date
		selectDate(dateOfBirthInputData);
		// Select hobby check boxes
		selectHobbies(hobbyCheckboxData);
		js.executeScript("arguments[0].value=arguments[1];", currentAddressTextarea, addressData);
		// Choose state from drop down
		selectState(stateData);
		// Choose city from drop down
		selectCity(cityData);
		// Submit the form
		js.executeScript("arguments[0].click()", submitButton);

	}

	// Closes the confirmation modal that appears after successful form submission
	public void closeFormModal() {
		js.executeScript("arguments[0].click()", closeButton);
	}

	// Selects a gender radio button based on input string
	public void selectGender(String gender) {

		String normalizedGender = gender.trim().toLowerCase();

		switch (normalizedGender) {
		case "male":
			js.executeScript("arguments[0].click();", genderMaleRadioButton);
			break;
		case "female":
			js.executeScript("arguments[0].click();", genderFemaleRadioButton);
			break;
		case "other":
			js.executeScript("arguments[0].click();", genderOtherRadioButton);
			break;
		}

	}

	// Maps month names to their corresponding index (needed for custom date
	// selector)
	public String getMonthIndex(String monthName) {
		Map<String, String> monthNameToIndexMap = new HashMap<>();
		monthNameToIndexMap.put("January", "0");
		monthNameToIndexMap.put("February", "1");
		monthNameToIndexMap.put("March", "2");
		monthNameToIndexMap.put("April", "3");
		monthNameToIndexMap.put("May", "4");
		monthNameToIndexMap.put("June", "5");
		monthNameToIndexMap.put("July", "6");
		monthNameToIndexMap.put("August", "7");
		monthNameToIndexMap.put("September", "8");
		monthNameToIndexMap.put("October", "9");
		monthNameToIndexMap.put("November", "10");
		monthNameToIndexMap.put("December", "11");

		return monthNameToIndexMap.get(monthName);
	}

	// Selects a date by interacting with the calendar widget
	public void selectDate(String dateInputValue) {

		js.executeScript("arguments[0].scrollIntoView(true);", dateOfBirthInput);
		js.executeScript("arguments[0].click();", dateOfBirthInput); // Open calendar

		if (dateInputValue == null || dateInputValue.trim().isEmpty()) {
			return;
		}

		// Extract day, month, year from dateValue
		String[] dateParts = dateInputValue.split("-");
		String day = dateParts[0];
		String month = dateParts[1];
		String year = dateParts[2];

		// Select month
		WebElement monthOption = driver.findElement(By.xpath("//option[text()='" + month + "']"));
		js.executeScript("arguments[0].scrollIntoView(true);", monthOption);
		js.executeScript("arguments[0].value = '" + getMonthIndex(month) + "';"
				+ "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));"
				+ "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", birthMonthDropdown);

		// Select year
		WebElement yearOption = driver.findElement(By.xpath("//option[@value='" + year + "']"));
		js.executeScript("arguments[0].scrollIntoView(true);", yearOption);
		js.executeScript("arguments[0].value = '" + year + "';"
				+ "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));"
				+ "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", birthYearDropdown);

		// Select Day
		WebElement dayElement = driver.findElement(By.xpath("//div[text()='" + day + "']"));
		js.executeScript("arguments[0].scrollIntoView(true);", dayElement);
		js.executeScript("arguments[0].click();", dayElement);

		// Store selected date value for confirmation or reporting
		selectedDateValue = (String) js.executeScript("return arguments[0].value;", dateOfBirthInput);
	}

	// Selects a hobby check box based on input value
	public void selectHobbies(String hobby) {

		if (hobby.equalsIgnoreCase("Sports")) {
			js.executeScript("arguments[0].click();", hobbySportsCheckbox);
		} else if (hobby.equalsIgnoreCase("Reading")) {
			js.executeScript("arguments[0].click();", hobbyReadingCheckbox);
		} else if (hobby.equalsIgnoreCase("Music")) {
			js.executeScript("arguments[0].click();", hobbuMusicCheckbox);
		}

	}

	// Selects a state from a custom-styled drop down
	public void selectState(String stateName) {

		js.executeScript(
				"arguments[0].dispatchEvent(new MouseEvent('mousedown', { bubbles: true }));"
						+ "arguments[0].dispatchEvent(new MouseEvent('mouseup', { bubbles: true }));"
						+ "arguments[0].dispatchEvent(new MouseEvent('click', { bubbles: true }));",
				stateDropdownElement);

		for (WebElement option : availableStateOptions) {
			String optionText = (String) js.executeScript("return arguments[0].innerText;", option);
			if (optionText.equalsIgnoreCase(stateName)) {
				js.executeScript("arguments[0].click();", option);
				break;
			}
		}

	}

	// Selects a city based on user input, after dynamically loading options
	public void selectCity(String cityName) {

		WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		webDriverWait.until(ExpectedConditions.elementToBeClickable(cityDropdown));

		js.executeScript("arguments[0].scrollIntoView(true);", cityDropdown);
		js.executeScript("arguments[0].style.display='block';", cityDropdown);

		js.executeScript("arguments[0].dispatchEvent(new MouseEvent('mousedown', { bubbles: true }));"
				+ "arguments[0].dispatchEvent(new MouseEvent('mouseup', { bubbles: true }));"
				+ "arguments[0].dispatchEvent(new MouseEvent('click', { bubbles: true }));", cityDropdown);

		webDriverWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(@class,'option')]")));

		for (WebElement cityOption : cityOptions) {
			String cityText = (String) js.executeScript("return arguments[0].textContent;", cityOption);
			if (cityText.equalsIgnoreCase(cityName)) {
				js.executeScript("arguments[0].click();", cityOption);
				break;
			}
		}

	}

	// Resets all form fields and selections to their default empty state
	public void clearFormFields() {

		// Clear text inputs
		js.executeScript("arguments[0].value='';", firstNameInput);
		js.executeScript("arguments[0].value='';", lastNameInput);
		js.executeScript("arguments[0].value='';", emailInput);
		js.executeScript("arguments[0].value='';", mobileNumberInput);
		js.executeScript("arguments[0].value='';", dateOfBirthInput);
		js.executeScript("arguments[0].value='';", currentAddressTextarea);

		// Reset radio buttons (if one needs to be unselected)
		js.executeScript("arguments[0].checked=false;", genderMaleRadioButton);
		js.executeScript("arguments[0].checked=false;", genderFemaleRadioButton);
		js.executeScript("arguments[0].checked=false;", genderOtherRadioButton);

		// uncheck check boxes
		js.executeScript("arguments[0].checked=false;", hobbySportsCheckbox);
		js.executeScript("arguments[0].checked=false;", hobbyReadingCheckbox);
		js.executeScript("arguments[0].checked=false;", hobbuMusicCheckbox);

	}

	// Returns the text of the modal title shown after form submission
	public String getModalTitle() {
		try {
			wait.until(ExpectedConditions.visibilityOf(resultModalTitle));
			return resultModalTitle.getText();
		} catch (Exception e) {
			return "";
		}
	}

}
