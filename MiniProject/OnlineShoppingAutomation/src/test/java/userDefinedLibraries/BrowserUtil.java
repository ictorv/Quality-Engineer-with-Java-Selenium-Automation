package userDefinedLibraries;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;

public class BrowserUtil {
	
	//Switch to Product Page
	public void switchToProductPage(WebDriver driver) {
		
		String mainTab = driver.getWindowHandle();
		Set<String> hometabSet = driver.getWindowHandles();

		
		for(String page: hometabSet) {
			if(!page.equals(mainTab)) {
				driver.switchTo().window(page);
				break;
			}
		}
	}
	
	//Switch to Main Page
	public void switchToMainPage(WebDriver driver) {
		
		List<String> tabList=new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabList.get(0));
		
	}

	public int convertStringToInt(String str) {
		String tempString=str.replaceAll("[^0-9]", "");
		 return Integer.parseInt(tempString);
	}
	
	public void refresh_Page(WebDriver driver) {
		driver.navigate().refresh();
		System.out.println("[INFO]Retry page refreshed successfully");
	}
	
}
