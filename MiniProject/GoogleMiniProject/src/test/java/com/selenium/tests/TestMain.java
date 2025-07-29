package com.selenium.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.selenium.objectRepositories.*;
import com.selenium.utilities.*;

import com.selenium.utilities.ExtentReportManager;

public class TestMain {
	public static WebDriver driver;
	static DriverSetup objDriver;
	public static String browser = "edge";
	public static CognizantSearchPage cognSearch;
	public static LandingPage landPage;
	public static ImagesPage imgPage;
	public static NewsPage newsPage;
	public static VideosPage vidPage;
	public ExtentReportManager erm;
	

	
	@BeforeClass	
	@Parameters({"browser"})
	public void driverConfig(String br) {
		// Instantiate driver
		browser=br;
		objDriver = new DriverSetup();
		driver = objDriver.getDriver(browser);
		
		landPage = new LandingPage(driver);
		cognSearch = new CognizantSearchPage(driver);
		imgPage = new ImagesPage(driver);
		newsPage = new NewsPage(driver);
		vidPage = new VideosPage(driver);
		System.out.println("--------------------------------------------------------");
		System.out.println("Opened the URL in the browser");
		
	}
	
	@BeforeTest
	public void startReporter()
	   {
		erm = new ExtentReportManager();
		erm.createTest("Search Navigation Scenario Flow");
	   }
	
	@Test
	public void searchNavigation() throws Exception{
		//get total number of links

		int landPageLinks = landPage.getNumberOfTotalLinks();
		erm.logPass("Fetched total number of links from landing page.");
		DataDrivenUtils.writeDataIntoExcel(1,browser, String.valueOf(landPageLinks));
		
		//print all links
		landPage.printAllLinks();
		erm.logPass("Printed all links.");
		
		//Type cognizant in search bar
		landPage.typeCognizant();
		
		//get suggestions count
		int suggestionCount = landPage.getOptionsCount();
		erm.logPass("Fetched total suggestion count.");
		DataDrivenUtils.writeDataIntoExcel(2,browser, String.valueOf(suggestionCount));
		//print suggestions
		landPage.printSearchSuggestions();
		erm.logPass("Printed all suggestion options.");
		//take screenshot of suggestions
		WebElement suggestionFrame = landPage.getOptionsFrame();
		ScreenShot.takeElementScreenshot(suggestionFrame, "Suggestions");
		erm.logPass("Took suggestion frame screenshot");

		//Search the cognizant
		cognSearch.clickSearch();
		
		//total search results count
		int totalSearchedLinks = cognSearch.getNumberOfTotalLinks();
		erm.logPass("Fetched cognizant search results count.");

		DataDrivenUtils.writeDataIntoExcel(3,browser, String.valueOf(totalSearchedLinks));
		//take home page screenshot
		ScreenShot.takeScreenshot(driver, "HomePage");

		//go to news section
		newsPage.navigateToNews();
		erm.logPass("Navigated to the News section");

		//switch to new tab
		newsPage.switchToNewTab();
		
		//count the number of results
		int totalNewsCount = newsPage.countNumberOfNews();
		erm.logPass("Fetched total news count.");

		DataDrivenUtils.writeDataIntoExcel(4,browser, String.valueOf(totalNewsCount));
		//take news page screenshot
		ScreenShot.takeScreenshot(driver, "NewsPage");
		erm.logPass("Took News window screenshot.");

		//Image section
		imgPage.navigateToImagesSection();
		erm.logPass("NAvigated to Images section.");

	
		//take screenshot of image section
		ScreenShot.takeScreenshot(driver, "ImagePage");
		erm.logPass("Took images section screenshot.");

		//navigate to videos section
		vidPage.navigateToVideoSection();
		erm.logPass("Navigated to videos section.");

		//count videos
		int totalVideosCount = vidPage.countNumberOfVideos();
		erm.logPass("Fetched total videos count.");

		DataDrivenUtils.writeDataIntoExcel(5,browser, String.valueOf(totalVideosCount));
		//take screenshot of videos page
		ScreenShot.takeScreenshot(driver, "VideosPage");
		erm.logPass("Took videos section screenshot");
		erm.logPass("Closed the browser");



		
	}
	
	@AfterMethod
	   public void getResult(ITestResult result) throws Exception {
	       if(result.getStatus() == ITestResult.FAILURE) {
	    	 //MarkupHelper is used to display the output in different colors
	    	   erm.testLogger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
	    	   erm.testLogger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
	    	   String screenshotPath = ScreenShot.takeScreenshot(driver, result.getName());
	    	 //To add it in the extent report 
	    	   erm.testLogger.fail("Test Case Failed Snapshot is below " + erm.testLogger.addScreenCaptureFromPath("."+screenshotPath));	    	  
	       }
	       else if(result.getStatus() == ITestResult.SUCCESS) {
	    	   erm.testLogger.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
	       }
	       else if(result.getStatus() == ITestResult.SKIP){
	    	   erm.testLogger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE)); 
	       }
	   }
	
	
	
	 @AfterClass
	    public void tearDown() {
		// 10. Close the browser
	        if (driver != null) {
	        	objDriver.closeDriver();
	        }	        
	    }
	 
	 @AfterTest
	    public void flushReport() {
		 erm.flushReports();
	    }
	 

	 
}
