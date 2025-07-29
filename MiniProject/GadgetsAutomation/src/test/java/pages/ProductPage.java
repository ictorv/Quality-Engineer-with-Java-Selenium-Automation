package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import data.ExcelUtils;

public class ProductPage {
	WebDriver driver;
	String path = "Snapdeal.xlsx"; // Ensure this matches your Excel file name
    String sheet = "Snapdeal"; // Ensure this matches your sheet name

	public ProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		@FindBy(className="sort-label")
		WebElement sortButton;
		@FindBy(xpath="//ul[@class='sort-value']/li")
		List<WebElement> sortOptions;
		@FindBy(name="fromVal")
		WebElement minValue;
		@FindBy(name="toVal")
		WebElement maxValue;
		@FindBy(className="price-go-arrow")
		WebElement goButton;
		@FindBy(xpath="(//*[@id='products']//div[@class='product-desc-rating ']/a/p)[position()<=5]")
		List<WebElement> productNames;
		@FindBy(xpath="(//*[@id='products']//a//div/span[@class='lfloat product-price'])[position()<=5]")
		List<WebElement> productPrices;
		
		
		//click on sort button
		public void sort() {
			sortButton.click();
		}
		
		//Select "Popularity" from drop down
		public void sortDropDown(String sortbydata){
			for (WebElement ele : sortOptions) {
	            String optionText = ele.getText().trim(); 
	            if (optionText.contains(sortbydata)) { 
	                ele.click(); 
	                break; 
	            }
			}
		}
		
		//Set price minimum value to '700'
		public void minimum(String minprice) {
			minValue.clear();
	        minValue.sendKeys(minprice);
		}
		
		//Set price maximum value to '1400'
		public void maximum(String maxprice) {
			maxValue.clear();
	        maxValue.sendKeys(maxprice);
		}
		
		//click GO button
		public void go() {
			goButton.click();
		}
		
		//Give a explicit Wait!!!
		public void explicitWait() {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'clr-btn-Advfilters')]")));
		}
		
		//Print the first 5 product name and price
		public void print(int rowIndex, String sheetName) {
            // Determine the number of results to process, up to a maximum of 5.
            int resultsToProcess = Math.min(productNames.size(), 5);

            // Check if we found any products and if the counts for names and prices match.
            if (resultsToProcess > 0 && productNames.size() == productPrices.size()) {
                System.out.println("--- Top " + resultsToProcess + " Products Found ---");

                try {
                    // Instantiate your Excel utility. This assumes a constructor like:
                    // public ExcelUtils(String filePath, String sheetName)
                    ExcelUtils excelUtils = new ExcelUtils(path, sheetName);

                    // Loop through the first 5 products 
                    for (int i = 0; i < resultsToProcess; i++) {
                        //Get the product name and price from the WebElements.
                        String name = productNames.get(i).getText();
                        String price = productPrices.get(i).getText(); 

                        //Print the details to the console for verification.
                        System.out.println("Product " + (i + 1) + " Name: " + name);
                        System.out.println("Product " + (i + 1) + " Price: " + price);
                        System.out.println("-------------------------------------");

                        // Write the product details into the Excel sheet 
                        int productDataRow = 2 + i;
                        excelUtils.setCellData(productDataRow, 4, name);  // Column E for Name
                        excelUtils.setCellData(productDataRow, 5, price); // Column F for Price
                    }

                    // 4. After successfully processing all products, mark the main test case as "Passed".
                    System.out.println("Successfully printed products");
                    excelUtils.setCellData(rowIndex, 6, "Passed");
                    // Assuming fillGreenColor is also an instance method now.
                    excelUtils.fillGreenColor(rowIndex, 6);

                } catch (Exception e) {
                    // If any error occurs during the loop, print the error and mark the test as Failed.
                    System.err.println("An error occurred while interacting with Excel: " + e.getMessage());
                    e.printStackTrace();
                    markTestAsFailed(rowIndex, sheetName);
                }

            } else {
                // This block runs if no products were found or the name/price lists don't match.
                System.out.println("No products found or product name/price count mismatch. Marking test case as FAILED.");
                markTestAsFailed(rowIndex, sheetName);
            }
        }
        
        /**
         * Helper method to centralize the logic for marking a test as failed in the Excel sheet.
         * @param rowIndex  The row index for the status.
         * @param sheetName The sheet name.
         */
        private void markTestAsFailed(int rowIndex, String sheetName) {
            try {
                // Instantiate the utility here as well to ensure the failure is logged.
                ExcelUtils excelUtils = new ExcelUtils(path, sheetName);
                excelUtils.setCellData(rowIndex, 6, "Failed");
                // Assuming fillRedColor is also an instance method.
                excelUtils.fillRedColor(rowIndex, 6);
            } catch (Exception e) {
                System.err.println("Additionally, failed to write 'Failed' status to Excel: " + e.getMessage());
            }
        }

			
			
}
	
