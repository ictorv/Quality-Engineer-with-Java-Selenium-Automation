package pageObjects;

import java.io.IOException;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.ExcelUtility;

/*This class represents the Search Results page after a book search is performed.
 * It includes actions such as sorting results, reading book titles and prices,
 * and exporting data to an Excel sheet.*/

public class SearchBooks extends BasePage{
	
	// Constructor initializes web elements on this page using PageFactory
	public SearchBooks(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	// Dropdown element for sorting the search results
	@FindBy(xpath="//select[@id='ddlSort']")
	WebElement sortByList;
	
	// Displays the total number of search results found
	@FindBy(xpath="//div[@class='preferences-show']/b")
	WebElement totResults;
	
	// List of web elements representing each book in the result
	@FindBy(xpath="//*[@class='list-view-books']")
	List<WebElement> bookList;
	
	// Selects a sorting criterion (e.g., "Price: Low to High") from dropdown
	public void setSort(String criteria) {
		Select select = new Select(sortByList);
		select.selectByVisibleText(criteria);
	}
	
	// Fetches and parses the total number of search results
	public int getTotalResults() {
		String tot = totResults.getText();
		int totalResults = Integer.parseInt(tot);
		return totalResults;
	}
	
	// Retrieves the names and prices of the first 'n' books displayed in results
	public Map<String,String> getNameAndPrice(int n) throws InterruptedException{
		// Waits until the book list container is visible
		new WebDriverWait(driver,Duration.ofSeconds(120)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='list-view-books']")));
	
		Map<String, String> nameAndPrice = new LinkedHashMap<>();
		
		// Extracts name and price for each book, up to the specified limit 'n'
		for(int i = 0; i<n && i<bookList.size(); i++) {	
			WebElement book = bookList.get(i);
			WebElement nameEl = book.findElement(By.className("title"));
			WebElement priceEl = book.findElement(By.className("sell"));
			
			String nameOfBook = nameEl.getText();
			String priceOfBook = priceEl.getText();
			
			nameAndPrice.put(nameOfBook, priceOfBook);
		}
		return nameAndPrice;
	}
	
	// Writes the book name and price data into an Excel sheet using ExcelUtility
	public void printNameAndPrice(Map<String, String> mp) throws IOException {
		ExcelUtility xutils = new ExcelUtility(".\\testData\\OnlineBookSearch_TestData.xlsx");
		
		int row = 0;
		int col1 = 0;
		int col2 = 1;
		
		// Iterates through the map and writes keys (titles) and values (prices) into Excel
		for(String key: mp.keySet()) {
			String val = mp.get(key);
			xutils.setCellData("Output", row, col1, key);
			xutils.setCellData("Output", row, col2, val);
			row++;
		}
	}
}
