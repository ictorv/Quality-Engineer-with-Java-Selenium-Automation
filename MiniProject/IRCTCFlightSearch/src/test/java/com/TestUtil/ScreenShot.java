package com.TestUtil;
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
	public static String filepath = "./screenshots/";
	public static String screenShotTC(WebDriver scdriver,String fileName)throws IOException{
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        Date date = new Date();
		File src=((TakesScreenshot)scdriver).getScreenshotAs(OutputType.FILE); 
		String destination=filepath+fileName+"_"+dateFormat.format(date)+".png";
		try
		{  // now copy the screenshot to desired location using copyFile method
			//FileUtils.copyFile(src, new File(filepath+fileName+"_"+dateFormat.format(date)+".png"));
			FileHandler.copy(src, new File(destination));

        }catch (IOException e)      
			{
        		System.out.println(e.getMessage());      
			}
		return destination;
	  }

}
