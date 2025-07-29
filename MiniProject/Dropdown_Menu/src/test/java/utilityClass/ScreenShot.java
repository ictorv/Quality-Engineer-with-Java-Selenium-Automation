package utilityClass;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenShot {
	
	static String filePath = System.getProperty("user.dir")+"\\Rediff_Screenshots\\";
	
	
	public static String takeScreenshot(WebDriver driver,String fname){
		
		 DateFormat formatter = new SimpleDateFormat("dd-mm-yyyy h-m-s");
		 Date date = new Date(); 

		 File filesource = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
		 try {
			FileHandler.copy(filesource, new File(filePath + fname + formatter.format(date) + ".png"));
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
			return filePath + fname + formatter.format(date) + ".png";
	}
}
