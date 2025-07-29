/**
 * Utility Class: ScreenShot.java
 * 
 * Purpose:
 * - Provides functionality to capture screenshots during test execution.
 * - Saves images with timestamps to prevent overwriting.
 * - Helps in debugging and reporting failed test cases effectively.
 *
 * Methods:
 * - screenShotTC(WebDriver scdriver, String fileName): Captures and saves screenshots with a unique timestamp.
 */

package utils;


import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenShot {
	public static String filepath = System.getProperty("user.dir")+"\\ScreenShots\\";
	public static void screenShotTC(WebDriver scdriver,String fileName)throws IOException{
		DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy h-m-s");
        Date date = new Date();
		File src=((TakesScreenshot)scdriver).getScreenshotAs(OutputType.FILE);       
		try
		{  FileHandler.copy(src, new File(filepath+fileName+"_"+dateFormat.format(date)+".png"));

        }catch (IOException e)      
			{
        		System.out.println(e.getMessage());      
			}
	  }
}
