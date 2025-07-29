package com.facebook.util;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenshotUtility {

	// Path where screenshots will be saved
	public static String filepath = "./screenshots/";

	// Method to take screenshot
	public static String takeScreenshot(WebDriver scdriver, String fileName) throws IOException {

		File dir = new File(filepath);

		if (!dir.exists()) {

			dir.mkdirs(); // Create directory if it doesn't exist

		}

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");

		Date date = new Date();

		// Take screenshot

		File src = ((TakesScreenshot) scdriver).getScreenshotAs(OutputType.FILE);
		String destination  = filepath + "\\" + fileName + "_" + dateFormat.format(date) + ".png";
		
		File dest  = new File(destination);
		try {

			// Save screenshot to the specified path

			FileHandler.copy(src, dest);

			System.out.println("Screenshot saved successfully.");

		} catch (IOException e) {

			System.out.println("Screenshot failed: " + e.getMessage());

		}
		
		return destination;

	}

}