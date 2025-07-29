package userDefinedLibraries;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

//Utility class to capture and save screenshots during test execution
public class TakeScreenShot {

	// Base directories for storing screenshots
	public static String filepath = System.getProperty("user.dir") + "/TestScreenShots/";
	public static String filepathPass = System.getProperty("user.dir") + "/TestScreenShots/testCaseScreenshotPassed/";
	public static String filepathFail = System.getProperty("user.dir") + "/TestScreenShots/testCaseScreenshotFailed/";

	// Captures a screenshot (not linked to test result)
	public static String screenShotTC(WebDriver scdriver, String fileName) {

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss");
		Date date = new Date();

		// Capture screenshot and build path with time stamp
		File src = ((TakesScreenshot) scdriver).getScreenshotAs(OutputType.FILE);
		String destination = filepath + fileName + "_" + dateFormat.format(date) + ".png";

		File dest = new File(destination);
		try {
			FileHandler.copy(src, dest);
			// Save file to destination
			return destination;

		} catch (IOException e) {
			throw new RuntimeException("Screenshot capture failed: " + e.getMessage());
		}
	}

	// Captures a screenshot for a failed test case and saves in the Fail folder
	public static String screenShotTCFail(WebDriver driver, String fileName) {

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss");
		Date date = new Date();
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destination = filepathFail + fileName + "_" + dateFormat.format(date) + ".png";
		File dest = new File(destination);
		try {
			FileHandler.copy(src, dest);
			return destination;

		} catch (IOException e) {
			throw new RuntimeException("Screenshot capture failed: " + e.getMessage());
		}
	}

	// Captures a screenshot for a passed test case and saves in the Pass folder
	public static String screenShotTCPass(WebDriver driver, String fileName) {

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss");
		Date date = new Date();
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destination = filepathPass + fileName + "_" + dateFormat.format(date) + ".png";
		File dest = new File(destination);
		try {
			FileHandler.copy(src, dest);
			return destination;

		} catch (IOException e) {
			throw new RuntimeException("Screenshot capture failed: " + e.getMessage());
		}
	}

}
