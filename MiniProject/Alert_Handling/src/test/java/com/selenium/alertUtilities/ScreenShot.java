package com.selenium.alertUtilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;


/*
 * -----------------------------------------------------------------------------
 * File Name   : ScreenShot.java
 * Package     : com.selenium.alertUtilities
 * Author      : Adinath Khose
 * 
 * Description :
 * This utility class provides functionality to capture screenshots during 
 * Selenium WebDriver test execution. Screenshots are saved with a timestamp 
 * and can be used for debugging or reporting purposes.
 * 
 * Features:
 * - Captures screenshots of the current browser window.
 * - Saves screenshots in the "Screenshots" directory under the project root.
 * - Filenames include the test case name and timestamp for uniqueness.
 * - Returns the full path of the saved screenshot for use in reports.
 * 
 * Dependencies:
 * - Selenium WebDriver
 * - Java IO and Date formatting classes
 * 
 * Usage:
 * - Call `screenShotTC(WebDriver driver, String fileName)` to capture and save 
 *   a screenshot. The method returns the path to the saved image.
 * - Typically used in test failure scenarios to attach screenshots to reports.
 * 
 * -----------------------------------------------------------------------------
 */


public class ScreenShot {
	public static String filepath = System.getProperty("user.dir")+"\\Screenshots\\";
	
	public static String screenShotTC(WebDriver scdriver,String fileName)throws IOException{
		DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy h-m-s");
        Date date = new Date();
		File src=((TakesScreenshot)scdriver).getScreenshotAs(OutputType.FILE);   
		String destination = filepath+fileName+"_"+dateFormat.format(date)+".png";
		try
		{  
			FileHandler.copy(src, new File(destination));
			return destination;

        }catch (IOException e)      
			{
        	throw new RuntimeException("Screenshot capture failed: " + e.getMessage());
			}
		
	  }

}
