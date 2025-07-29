package com.test.objRepo;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.ExcelUtil.ExcelWriting;

/*
* This class is responsible for extracting and validating train search results
* based on the source and destination station codes provided by the user.
* It uses Selenium's PageFactory to locate result elements and writes validated
* train data into an Excel sheet using the utility.
*/

public class ERail_ResultsPage {
	WebDriver driver;
	ExcelWriting  excelWriteObj;
	String filePath = "src\\test\\java\\com\\test\\data\\ERailData.xlsx";
	String sheetName = "BrowserAndURL";
	int count = 0;
	int rowNum = 1;
	
	public ERail_ResultsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Locating details of the trains result
	@FindBy(xpath = "//div[@id='divTrainsList']/table/tbody/tr/td")
	
	List<WebElement> resultTrains;
	
	
	//Validating the trains based on input source and destination station codes
	public void resultTrains(String sourceStation,String destinationStation) throws IOException{
		
		excelWriteObj = new ExcelWriting(filePath,sheetName);
		excelWriteObj.setCellData(0,0,"Results");
		excelWriteObj.setColorCellData(0, 0);
		for(int i = 1 ; i < resultTrains.size(); i++) {
			String listBreak = resultTrains.get(i).getText();
			if(listBreak.contains("Below trains not departing on")) {
				break;
			}
			if(listBreak.equalsIgnoreCase(sourceStation) && resultTrains.get(i + 3).getText().equalsIgnoreCase(destinationStation)) {
				excelWriteObj.setCellData(rowNum,0,resultTrains.get(i - 1).getText());
				System.out.println("Train Number : " + resultTrains.get(i - 2).getText());
				System.out.println("Train Name : " + resultTrains.get(i - 1).getText());
				count++;
				rowNum++;
			}
		}
		System.out.println("Count : " + count);
	}
	
	
	//Counting the number of result trains after validation
	public void setCount() throws IOException{
		excelWriteObj.setCellData(3, 0, "Count : " + Integer.toString(count));
	}
}
