package stepDefination;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageObject.HomePage;
import pageObject.NewBikePage;
import pageObject.UpcomingBikePage;
import utilities.ExcelUtils;

public class TC_005_UpcomingBikesSteps {

    WebDriver driver = BaseClass.getDriver();
    HomePage home = new HomePage(driver);
    UpcomingBikePage upcomingBikePage = new UpcomingBikePage(driver);
    NewBikePage newBike=new NewBikePage(driver);
    
	int i=0,j=0,k=0;
	
	String path = "TestData.xlsx"; // Ensure this matches your Excel file name
    String sheetName = "UpcomingBikes"; // Ensure this matches your sheet name
    ExcelUtils excelUtils = new ExcelUtils(path, sheetName);
    public static String[] data=new String[3];
    List<String> bikeNames=new ArrayList<String>();
    String errorMessage;
    
    public void readData() throws IOException {		
		data[0] = excelUtils.getCellData(2,0).toString();
		data[1]= excelUtils.getCellData(3,0).toString();
	}
    
    
    @And("the user enters invalid data in the search box")
    public void userEntersSearchTerm() throws IOException {
    	readData();
        home.invalidData(data[0]);
    }

	public void writeData() throws IOException {
		excelUtils.setCellData(2, 1, home.errorMessage());
	}
    
    @And("display the searchTerm not found message")
    public void not_found_message() throws IOException {
    	writeData();
    	System.out.println(home.errorMessage());
    	BaseClass.getLogger().info("Display"+ home.errorMessage());
    	errorMessage = home.errorMessage();
    }
    
    @Then("the user clicks New Bikes")
    public void newBike() {
        home.clickNewBikes();
    }
    
    @And("enter Honda in Search New Bikes")
    public void selectmake() {
    	newBike.selectBikeMake(data[1]);
    }
    
    @And("click Search button")
    public void searchButton() {
    	newBike.clickSearchButton();
    }

    @And("click on readmore button")
    public void userClicksReadmore() {
        upcomingBikePage.readMore();
    }
    
    public void writeData1() throws IOException {    	
    	for (String allBikes:upcomingBikePage.upcomingBikeList()) {
    		excelUtils.setCellData(i+3, 1, allBikes);
    		i++;
    	}
    }
    public void writeData2() throws IOException {
    	for (Map.Entry<String, String> entry : upcomingBikePage.upcomingBike().entrySet()) {
			String bike = entry.getKey().toString();
			excelUtils.setCellData(j+3, 2, bike );
			j++;
    	}
    }
    
    @And("filters the results for upcoming bikes priced under 4 lakhs")
    public void filterUpcomingBikes() throws IOException {
    	writeData1();
        upcomingBikePage.upcomingBike();
        writeData2();
    }

    @Then("FAQs about upcoming Honda models should be printed")
    public void printFaqs() {
        upcomingBikePage.FQAss();
    }
    
    public void writeData3() throws IOException {
    	String bike = upcomingBikePage.FQAss();
    	String[] parts = bike.split(" are ");
        String bikeSegment = parts[0].trim();

        // Step 2: Split the bike names into a list
        bikeNames = Arrays.asList(bikeSegment.split(",\\s*"));
        
        for(String faqBike : bikeNames) {
        	excelUtils.setCellData(k+3, 3, faqBike );
			k++;
        }
        
    }
    @And("display the upcoming bikes")
    public void display() throws IOException{
    	writeData3();
    }
    
    public void result() throws IOException {
    	if(errorMessage.contains("No results found")) {
    		excelUtils.setCellData(2,4,"Pass");
    		excelUtils.fillGreenColor(2,4);
    	}else {
    		excelUtils.setCellData(2,4,"Fail");
    		excelUtils.fillRedColor(2,4);
    	}    	
    	
    	if(upcomingBikePage.upcomingBikeList().equals(bikeNames)) {
    		excelUtils.setCellData(3,4,"Pass");
    		excelUtils.fillGreenColor(3,4);
    	}else {
    		excelUtils.setCellData(3,4,"Fail");
    		excelUtils.fillRedColor(3,4);
    	}
    }
    @And("display result")
    public void pass_fail() throws IOException {
    	result();
    }
}
