package com.test.utilities;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

/*
 * This class provides a method to take a screenshot of the current browser window
 * and save it to a predefined directory with a time stamped filename.
 */

public class Screenshot {
	public static String filepath = "./screenshots/";
	
	public static String screenshotTaken(WebDriver driver,String fileName) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String dest = filepath + fileName + "_" + ".png";
		File destination = new File(dest);
		try {
			FileHandler.copy(src, destination);
			return dest;
		}
		catch(IOException e) {
			throw new RuntimeException("Screenshot capture failed: " + e.getMessage());
		}
	}
}
