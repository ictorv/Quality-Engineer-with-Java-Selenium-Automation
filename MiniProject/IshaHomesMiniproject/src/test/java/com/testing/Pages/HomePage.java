package com.testing.Pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	public HomePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// POM
	@FindBy(xpath = "//nav[contains(@class,'main-nav')]/ul/li/a[@href='https://ishahomes.com/completed-projects/']")
	WebElement completedProjects;
	
	@FindBy(xpath = "(//div[@id=\"module_properties\"])[1]//child::div[contains(@class,'item-body')]//h2/a")
	List<WebElement> projectList;

	// Click on completed projects to generate total completed projects
	public void clickOnCompletedProjects() {

		completedProjects.click();
	}

	// Retrieving the list of first five completed projects are returned
	public String[] completedProjects() {

		int i = 0;
		String[] result = new String[5];
		System.out.println("Total Number of projects : " + projectList.size());
		System.out.println("List of five  projects:");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		for (WebElement li : projectList) {
			if (i == 5)
				break;
			result[i] = (String) js.executeScript("return arguments[0].innerText;", li);

			System.out.println(result[i]);
			i++;
		}
		return result;

	}

}
