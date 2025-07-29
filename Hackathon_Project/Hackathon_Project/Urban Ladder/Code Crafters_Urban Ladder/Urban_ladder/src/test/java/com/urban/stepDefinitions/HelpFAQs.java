package com.urban.stepDefinitions;
 
import java.util.List;
 
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
 
 
import com.urban.base.BaseClass;
import com.urban.pageObject.BasePage;
import com.urban.pageObject.HelpPage;
import com.urban.utillities.ExcelUtil;
import com.urban.utillities.ScreenShot;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
 
public class HelpFAQs {
    private static final Logger logger = LogManager.getLogger(BookShelves.class);
	
	WebDriver driver = BaseClass.driver;
	HelpPage helpPage = new HelpPage(BaseClass.driver);
	@Given("User navigates to the Help section")
	public void user_navigates_to_help_section() {
		helpPage.navigateToHelpSection();
	}
 
	@When("User expands all available help questions")
	public void user_expands_all_questions() {
		helpPage.expandAllAnswers();
        ScreenShot.captureScreenshot(BasePage.driver, "HelpFAQ");

	}
 
	@Then("User should extract all visible FAQs and save them into Excel")
	public void user_extracts_faqs_to_excel() {
		try {
	        List<String> questions = helpPage.getQuestionsList();
	        List<String> answers = helpPage.getCleanAnswersList(questions);
 
	        int rowQ = 1;
	        for (String question : questions) {
	            ExcelUtil.writeDataIntoExcel("FAQs", rowQ++, 0, question);
	        }
 
	        int rowA = 1;
	        for (String answer : answers) {
	            ExcelUtil.writeDataIntoExcel("FAQs", rowA++, 1, answer);
	        }
 
	        logger.info("✅ FAQs written successfully.");
	    } catch (Exception e) {
	    	logger.error("Error during FAQ extraction: ", e.getMessage());
	    }
	}
}
 
 