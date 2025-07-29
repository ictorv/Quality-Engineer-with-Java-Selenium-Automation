package com.test.Pages;
import java.io.IOException;
//import java.net.HttpURLConnection;
//import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.test.ExcelUtil.ExcelReadAndWrite;

public class EBay_ResultsPage {
WebDriver driver;
	
	//Constructors
	public EBay_ResultsPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//locator
	@FindBy(xpath = "//ul[@class='srp-results srp-grid clearfix']/li/div/div[2]//a[@href]")
	List<WebElement> allhref;
	
	
	//actions
	public void findAllherf(String filterName) throws IOException {
		
		Map<String,String> toysData = new HashMap<>();
		/*
		 * for (WebElement link : allhref) {
		 * 
		 * String itemName = link.getText().replace("Opens in a new window or tab",
		 * "").trim(); String itemLink = link.getAttribute("href").trim(); if(itemLink
		 * == null || itemLink.isEmpty()) {
		 * if(itemName.toLowerCase().contains(filterName)) { toysData.put(itemName,
		 * "href attribute value is null or empty, So not possible to check ");
		 * continue; } } //hit URL to the server if
		 * (itemName.toLowerCase().contains(filterName)) {
		 * //System.out.println(itemName); try { URL linkURL = new URL(itemLink);
		 * HttpURLConnection conn = (HttpURLConnection) linkURL.openConnection();
		 * conn.setRequestMethod("HEAD"); // Use HEAD instead of GET to avoid large
		 * downloads conn.connect(); if (conn.getResponseCode() >= 400) { // Broken Link
		 * toysData.put(itemName, itemLink + " Broken Link"); } else {
		 * toysData.put(itemName, itemLink); } } catch (IOException e) { if
		 * (itemName.toLowerCase().contains(filterName)) { toysData.put(itemName,
		 * "Error connecting: " + e.getMessage()); } } } }
		 */
		
		for (WebElement link : driver.findElements(By.xpath("//ul[@class='srp-results srp-grid clearfix']/li/div/div[2]//a[@href]"))) { 
		    String itemName = link.getText().replace("Opens in a new window or tab", "").trim();
		    String itemLink = link.getAttribute("href").trim();
		    if (itemName.toLowerCase().contains("toys")) {
		        toysData.put(itemName, itemLink);
		    }
		}
		
		Assert.assertFalse(toysData.isEmpty());
		
		int rowNum = 1;
		String xlfile = System.getProperty("user.dir") + "\\testdata\\ReadWrite.xlsx";
		String xlsheet = "Sheet1";

		for (Map.Entry<String, String> entry : toysData.entrySet()) {
		    // Set toy name
		    ExcelReadAndWrite.setCellData(xlfile, xlsheet, rowNum, 2, entry.getKey());
		    // Set toy data
		    ExcelReadAndWrite.setCellData(xlfile, xlsheet, rowNum, 3, entry.getValue());
		    rowNum++;
		}
		
		System.out.println("Matching items containing 'toys' Displayed in Excel :");
		/*
		for (Map.Entry<String, String> entry : toysData.entrySet()) {
		    System.out.println("Item: " + entry.getKey());
		    System.out.println("Link: " + entry.getValue());
		    System.out.println("-------------------------");}*/
	}
}
