package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomeLoanEMIPage extends BasePage{
		
	public HomeLoanEMIPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="homeprice")
	WebElement homePrice;
	 
	@FindBy(id="downpayment")
	WebElement downPayment;
	
	@FindBy(id="homeloaninsuranceamount")
	WebElement loanInsurance;
	 
	@FindBy(id="homeloanamount")
	WebElement loanAmount;
	 
	@FindBy(id="homeloaninterest")
	WebElement interestRate;
	 
	@FindBy(id="loanfees")
	WebElement loanFees;
	
	@FindBy(id="startmonthyear")
	WebElement dateElement;
	
	@FindBy(id="startmonthyear")
	WebElement yearClick;
	
	@FindBy(xpath="//div[@class='datepicker-months']/table/tbody/tr/td/span")
	List<WebElement> startMonth;
	@FindBy(xpath="/html/body/div[2]/div[3]/table/tbody/tr/td/span")
	List<WebElement> startYear;
	 
	@FindBy(id="homeinsurance")
	WebElement homeInsurance;
	 
	@FindBy(id="maintenanceexpenses")
	WebElement maintenanceExpenses;
	
	public void setHomePrice(String price) {
		homePrice.sendKeys(Keys.CONTROL + "a");
		homePrice.sendKeys(price);
	}
	
	public void setDownPayment(String payment) {
		downPayment.sendKeys(Keys.CONTROL + "a");
		downPayment.sendKeys(payment);
	}
	
	public void setLoanInsurance(String insurance) {
		loanInsurance.sendKeys(Keys.CONTROL + "a");
		loanInsurance.sendKeys(insurance);
	}
	
	public void setLoanAmount() {
		loanAmount.click();
	}
	
	public void setInterestRate(String rate) {
		interestRate.sendKeys(Keys.CONTROL + "a");
		interestRate.sendKeys(rate);
	}
	
	public void setLoanFees(String fees) {
		loanFees.sendKeys(Keys.CONTROL + "a");
		loanFees.sendKeys(fees);
	}
	
	public void setStartDate(String date) {
		yearClick.click();
		String[] data = date.split(" ");
		String month = data[0];
		String year = data[1];
		
		dateElement.click();
		
		for(WebElement element : startYear) {
			if(element.getText().contains(year)) {
				element.click();
				break;
			}
		}
		
		for(WebElement element : startMonth) {
			if(element.getText().contains(month)) {
				element.click();
				break;
			}
		}
		
	}
	
	public void setHomeInsurance(String hi) {
		homeInsurance.sendKeys(Keys.CONTROL + "a");
		homeInsurance.sendKeys(hi);
	}
	
	public void setMaintenanceExpenses(String expenses) {
		maintenanceExpenses.sendKeys(Keys.CONTROL + "a");
		maintenanceExpenses.sendKeys(expenses);
	}
	
	@FindBy(id = "paymentschedule")
	WebElement scrollTo;
	
	@FindBy(css = "tr.row.no-margin")
	WebElement headerRow;
	
	@FindBy(xpath = "//*[contains(@class,'yearlypaymentdetails')]")
	List<WebElement> dataRows;
	
	@FindBy(id = "monthlypayment")
	WebElement confirmMsg;
	
	public String getConfirmation() {
		return confirmMsg.getText();
	}
	
	public List<List<String>> getYearOnYearTable() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", scrollTo);
		
		List<List<String>> tableData = new ArrayList<>();
		
		List<WebElement> headers = headerRow.findElements(By.tagName("th"));
		
		List<String> headerText = new ArrayList<>();
        for (WebElement header : headers) {
        	String cleanText = header.getText().replace("\n", " ").trim();
            headerText.add(cleanText);
        }
        tableData.add(headerText);
        
        
        for (WebElement row : dataRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            List<String> rowData = new ArrayList<>();
            for (WebElement cell : cells) {
            	rowData.add(cell.getText().trim());
            }
            tableData.add(rowData);
        }
        
        return tableData;
	}
	
	@FindBy(id = "menu-item-dropdown-2696")
	WebElement menu;
	
	@FindBy(xpath = "//*[contains(@class,'dropdown-menu show')]/li/a")
	List<WebElement> menuData;
	
	public void selectDropDown(String value) throws StaleElementReferenceException {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,0);");
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(menu)));
		menu.click();
		for(WebElement element : menuData) {
			if(element.getText().contains(value)) {
				wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
				element.click();
				System.out.println(element.getText());
			}
		}

	}
	
}
