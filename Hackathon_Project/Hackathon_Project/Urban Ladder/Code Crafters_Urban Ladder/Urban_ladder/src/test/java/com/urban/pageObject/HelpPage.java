package com.urban.pageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HelpPage extends BasePage{
//	WebDriver driver;

	@FindBy(xpath = "//a[@class='inherit contact-channel'][normalize-space()='Help']")
	WebElement helpLink;

	@FindBy(xpath = "//a[@class='topic-title inverted highlight']")
	WebElement ulInteriorLink;

	@FindBy(xpath = "//ul[@class='entries']//li")
	List<WebElement> questionList;

	@FindBy(xpath = "//li//span[@class='col-sm-1 col-md-1 col-lg-1 no-padding accordion-icon-wrapper']")
	List<WebElement> arrowList;

	@FindBy(xpath = "//div[@class='answer']//p")
	List<WebElement> answerList;

	public HelpPage(WebDriver driver) {
		super(driver);
	}

	public void navigateToHelpSection() {
		helpLink.click();
		ulInteriorLink.click();
	}


	public void expandAllAnswers() {
		for (WebElement arrow : arrowList) {
			try {
				arrow.click();
			} catch (Exception e) {
				System.out.println("Could not click arrow: " + e.getMessage());
			}
		}
	}


	public List<String> getQuestionsList() {
		return questionList.stream().map(q -> {
			String raw = q.getText().trim();
			int newlineIndex = raw.indexOf("\n");
			return newlineIndex > 0 ? raw.substring(0, newlineIndex) : raw;
		}).filter(t -> !t.isEmpty()).toList();
	}

	
	public List<String> getCleanAnswersList(List<String> questions) {
		return answerList.stream().map(a -> a.getText().trim()).filter(a -> !a.isEmpty() && !questions.contains(a))
				.toList();
	}
}