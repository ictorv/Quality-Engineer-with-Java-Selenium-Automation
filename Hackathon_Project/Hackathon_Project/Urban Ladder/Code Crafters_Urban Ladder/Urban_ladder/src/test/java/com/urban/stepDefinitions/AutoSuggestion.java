package com.urban.stepDefinitions;
 
import java.util.List;
import org.openqa.selenium.WebElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
 
import com.urban.pageObject.HomePage;
import com.urban.base.BaseClass;
import com.urban.utillities.ExcelUtil;
import com.urban.utillities.ScreenShot;
 
import io.cucumber.java.en.*;
 
public class AutoSuggestion extends BaseClass {
 
    private static final Logger logger = LogManager.getLogger(AutoSuggestion.class);
    HomePage home;
    List<WebElement> suggestions;
 
    @And("user closes the popup if present")
    public void user_closes_popup_if_present() {
        home = new HomePage(BaseClass.driver);
        home.closePopupIfVisible();
        logger.info("Popup closed if visible.");
    }
 
    @When("user enters {string} into the search field")
    public void user_enters_keyword(String keyword) {
        home.searchField(keyword);
        logger.info("Entered keyword into search field: " + keyword);
    }
 
    @Then("user should see auto-suggestions")
    public void user_should_see_auto_suggestions() {
        suggestions = home.getSuggestions();
        logger.info("Total suggestions found: " + suggestions.size());
        ScreenShot.captureScreenshot(BaseClass.driver, "AutoSuggestionsVisible");
        for (WebElement element : suggestions) {
            String suggestionText = element.getText();
            logger.debug("Suggestion: " + suggestionText);
            System.out.println("Suggestion: " + suggestionText);
        }
    }
 
    @And("user writes all auto-suggestion texts to Excel")
    public void user_writes_auto_suggestions_to_excel() {
        for (int i = 0; i < suggestions.size(); i++) {
            String text = suggestions.get(i).getText();
            ExcelUtil.writeDataIntoExcel("AutoSuggestion", i + 1, 1, text);
            logger.info("Written to Excel: " + text);
        }
    }
}
 