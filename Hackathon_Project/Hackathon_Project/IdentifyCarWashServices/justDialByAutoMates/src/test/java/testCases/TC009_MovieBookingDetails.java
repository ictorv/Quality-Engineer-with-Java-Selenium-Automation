package testCases;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.HomePage;
import pages.MoviesPage;
import pages.SearchResultMoviePage;
import testBase.BaseClass;
import utilities.DataProviders;
import utilities.ExcelUtilityClass;

public class TC009_MovieBookingDetails extends BaseClass {

	@Test(dataProvider = "TC009", dataProviderClass = DataProviders.class)
	public void MovieBookingDetails(String languges,String format,String genre) {
		logger.info("------ Starting TC009_MovieBookingDetails ------");
		
		String path=".\\testData\\Identify-Car-Wash-Services_TestData.xlsx";
		int count = 1;
		
		ExcelUtilityClass obj = new ExcelUtilityClass(path, "TC009");

		HomePage homePage = new HomePage(driver);

		homePage.clickMayBeLaterButton();
		logger.info("------ Clicked May Be Later Button ------");
		homePage.clickMoviesSection();
		logger.info("------ Clicked Movies Section ------");

		MoviesPage movies = new MoviesPage(driver);

		movies.waitUntilHeadingTagAppears();
		movies.clickChosenLanguages(languges);
		logger.info("------ Selected Prefered Languages in the filter ------");
		movies.clickChosenFormat(format);
		logger.info("------ Selected 2D in the format Filter ------");
		movies.clickChosenJonour(genre);
		logger.info("------ Selected Comedy in the Genre Filter ------");
		movies.clickApplyFilterButton();
		logger.info("------ Clicked Apply Button ------");

		SoftAssert softAssert = new SoftAssert();
		WebElement comedyJonourFilterCheckBox = movies.getComedyJonourFilterCheckBox();
		if (comedyJonourFilterCheckBox.isEnabled()) {
			obj.setCellData("Languages,Format,Genre check box are Enabled ",1,5);
			softAssert.assertTrue(true);
			logger.info("------ Selection of Comedy Genre is verified ------");
		} else {
			obj.setCellData("Languages,Format,Genre check box are Not Enabled ",1,5);
			softAssert.fail();
			logger.info("------ Selection of Comedy Genre is not reflected ------");
		}

		movies.waitUntilFilteredHeadingTagAppears();
		movies.clickFirstMovie();
		logger.info("------ First Movie in the Results is Selected ------");

		SearchResultMoviePage movieSearch = new SearchResultMoviePage(driver);
		movieSearch.waitUntilMovieNameToAppear();
		String movie = movieSearch.getMovieName();
		System.out.println("Movie Name: " + movie);
		List<String> details = movieSearch.getMovieDetails();
		for (String x : details) {
			obj.setCellData(x,count,3);
			count++;
			String [] values = x.split(" , ");
			System.out.println("\nTheatre Name: " + values[0] + "\nLocation Name: "
					+ values[1] + "\nShow Timings: " + values[2]);
		}
		logger.info("------ Displayed the Results ------");
		logger.info("----------------------------------------------------------------------------------------");

		try{
			softAssert.assertAll();
			obj.setCellData("Pass",1,6);
			}catch(AssertionError e){
				obj.setCellData("Fail",1,6);
		}
	}

}
