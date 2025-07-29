package com.urban.stepDefinitions;
 
import java.util.LinkedHashMap;
import java.util.Map;
 
import org.openqa.selenium.By;
import com.urban.base.BaseClass;
import com.urban.pageObject.BasePage;
import com.urban.utillities.ExcelUtil;
import com.urban.utillities.ScreenShot;

import io.cucumber.java.en.Then;
 
public class SocialMedia {
 
  
    Map<String, String> socialMediaXpaths = new LinkedHashMap<>();
    Map<String, String> validatedUrls = new LinkedHashMap<>();
 
    @Then("Social media icons should be visible in the footer")
    public void social_media_icons_should_be_visible_in_the_footer() throws InterruptedException {
    	BaseStep.homePageObject.scrollToFooter();
        BaseClass.getLogger().info("Scrolled to footer to view social media icons");
        ScreenShot.captureScreenshot(BasePage.driver, "SocialMedia");

    }
 
    @Then("Each social media icon should redirect to the correct platform")
    public void each_social_media_icon_should_redirect_to_the_correct_platform() throws InterruptedException {
//        socialMediaXpaths.put("facebook", "//*[@id=\"footer-social\"]/li[3]/a/div");
        socialMediaXpaths.put("pinterest", "//a[contains(@href,'pinterest')]");
        socialMediaXpaths.put("youtube", "//a[contains(@href,'youtube')]");
 
        for (Map.Entry<String, String> entry : socialMediaXpaths.entrySet()) {
            boolean isValid = BaseStep.homePageObject.validateSocialLink(entry.getKey(), entry.getValue());
            String actualUrl = BaseClass.driver.findElement(By.xpath(entry.getValue())).getAttribute("href");
 
            if (isValid) {
                validatedUrls.put(entry.getKey(), actualUrl);
                BaseClass.getLogger().info(entry.getKey() + " link validated: " + actualUrl);
            } else {
                validatedUrls.put(entry.getKey(), "Invalid or broken link");
                BaseClass.getLogger().warn(entry.getKey() + " link validation failed.");
            }
        }
    }
 
    @Then("The URLs should be printed in the console")
    public void the_urls_should_be_printed_in_the_console() {
        for (Map.Entry<String, String> entry : validatedUrls.entrySet()) {
            System.out.println("Platform: " + entry.getKey() + " | URL: " + entry.getValue());
        }
    }
 
    @Then("The URLs should be written to the {string} Excel sheet")
    public void the_urls_should_be_written_to_the_excel_sheet(String sheetName) {
        ExcelUtil.writeDataIntoExcel(sheetName, 0, 0, "Platform");
        ExcelUtil.writeDataIntoExcel(sheetName, 0, 1, "URL");
 
        int row = 1;
        for (Map.Entry<String, String> entry : validatedUrls.entrySet()) {
            ExcelUtil.writeDataIntoExcel(sheetName, row, 0, entry.getKey());
            ExcelUtil.writeDataIntoExcel(sheetName, row, 1, entry.getValue());
            row++;
        }
        BaseClass.getLogger().info("Social media URLs written to Excel sheet: " + sheetName);
    }
}
 
 //