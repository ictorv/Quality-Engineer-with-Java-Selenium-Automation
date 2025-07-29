package com.urban.testrunner;

import org.testng.annotations.AfterSuite;

import com.urban.utillities.AllureReportOpener;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
		features = {
		
//		  ".//src//test//resources//feature/TC_04_Login.feature",
//		  ".//src//test//resources//feature/TC_02_AddToCart.feature",
//		  ".//src//test//resources//feature/TC_03_Cities.feature",
//		  ".//src//test//resources//feature/TC_05_SocialMedia.feature",
//		  ".//src//test//resources//feature/TC_06_AutoSuggestion.feature",
//		  ".//src//test//resources//feature/TC_07_HelpPage.feature",
//		  ".//src//test//resources//feature/TC_08_BeingAtHome.feature",
//		  ".//src//test//resources//feature/TC_09_SofasAndRecliners.feature",
//		  ".//src//test//resources//feature/TC_01_BookShelves.feature",
		  ".//src//test//resources//feature/TC_10_TrackOrder.feature",
		 
//			".//src//test//resources//feature/"
		},

		glue = { "com.urban.stepDefinitions", "com.urban.hooks" },
		plugin = { "pretty", "html:test-output/cucumber-reports.html",
				"json:test-output/cucumber.json",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" }, 
		monochrome = true
//			dryRun = false,
//			monochrome = true,
//			publish = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
	@AfterSuite
	public void afterSuite()
	{
		AllureReportOpener.openAllureReport();
	}
}
