package userDefinedLibraries;
//
//import org.openqa.selenium.*;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//
//public class HandleRetryPage {
//
//    private WebDriver driver;
//    private WebDriverWait wait;
//
//    public HandleRetryPage(WebDriver driver) {
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//    }
//
//    public void checkRetry() {
//        try {
//            WebElement retryBtn = driver.findElement(By.id("retry_btn"));
//            if (retryBtn.isDisplayed()) {
//                System.out.println("Retry page detected. Waiting for retry button to be clickable...");
//                wait.until(ExpectedConditions.elementToBeClickable(retryBtn));
//                retryBtn.click();
//                Thread.sleep(3000); // Wait for page reload
//                System.out.println("Retry button clicked. Page reloaded.");
//            }
//        } catch (NoSuchElementException | TimeoutException e) {
//            // Retry page not present
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//    }
//}


//import java.time.Duration;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//public class HandleRetryPage {
//
//    WebDriver driver;
//    WebDriverWait wait;
//
//    public HandleRetryPage(WebDriver driver) {
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//    }
//
//    public void checkRetry() {
//        String retryPageTitle = "Online Shopping India Mobile, Cameras, Lifestyle & more Online @ Flipkart.com";
//        int maxAttempts = 3;
//        int attempts = 0;
//
//        while (driver.getTitle().equalsIgnoreCase(retryPageTitle) && attempts < maxAttempts) {
//            try {
//                System.out.println("[INFO] Retry page detected. Attempting retry #" + (attempts + 1));
//
//                // Wait for retry button to be clickable
//                WebElement retryBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("retry_btn")));
//                retryBtn.click();
//                System.out.println("[INFO] Retry button clicked.");
//
//                // Wait for the page title to change (i.e., retry page disappears)
//                boolean pageChanged = wait.until(ExpectedConditions.not(
//                        ExpectedConditions.titleIs(retryPageTitle)));
//
//                if (pageChanged) {
//                    System.out.println("[INFO] Page successfully navigated after retry.");
//                    return;
//                }
//
//            } catch (Exception e) {
//                System.out.println("[WARN] Retry attempt failed: " + e.getMessage());
//            }
//
//            attempts++;
//        }
//
//        if (attempts == maxAttempts) {
//            System.out.println("[ERROR] Max retry attempts reached. Still on retry page.");
//        }
//    }
//}


//import java.time.Duration;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//
//
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//public class HandleRetryPage {
//
//    WebDriver driver;
//    WebDriverWait wait;
//
//    public HandleRetryPage(WebDriver driver) {
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds timeout
//    }
//
//    public void checkRetry() {
//        try {
//            String retryPageTitle = "Online Shopping India Mobile, Cameras, Lifestyle & more Online @ Flipkart.com";
//
//            // Check if current page title matches the retry page
//            if (driver.getTitle().equalsIgnoreCase(retryPageTitle)) {
//                System.out.println("[INFO] Retry page detected. Attempting to click Retry...");
//
//                WebElement retryBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("retry_btn")));
//                retryBtn.click();
//
//                System.out.println("[INFO] Retry button clicked successfully.");
//           }
//               else {
//                System.out.println("[INFO] Normal page detected. No retry needed.");
//            }
//
//        } catch (Exception e) {
//            System.out.println("[WARN] Retry button not found or not clickable.");
//        }
//    }
//
//}


//public class HandleRetryPage {
//	
//	WebDriver driver;
//	
//	public HandleRetryPage(WebDriver driver) {
//		this.driver=driver;
//	}
//	
//	public void checkRetry() {
//		
//		while(driver.getTitle().equals("Online Shopping India Mobile, Cameras, Lifestyle & more Online @ Flipkart.com")) {
//				
//				WebElement retryElement=driver.findElement(By.id("retry_btn"));
//				System.out.println("Handling Retry page...");
//				try {
//					Thread.sleep(4000);  //this sleep used to wait until retry page handled
//				} catch (Exception e) {
//					System.out.println("thread exception");
//				}
//		 
//				retryElement.click();
//				
//			
//		}
//		
//	}
//	
//}

//import org.openqa.selenium.WebDriver;
//
//public class HandleRetryPage {
//	
//	WebDriver driver;
//	
//	public HandleRetryPage(WebDriver driver) {
//		this.driver=driver;
//	}
//	
//	public void checkRetry() {
//		
//		while(driver.getTitle().equals("Online Shopping India Mobile, Cameras, Lifestyle & more Online @ Flipkart.com")) {
//			
//			driver.navigate().refresh();
//			System.out.println("[INFO]Retry page refreshed successfully");
//		}
//	}
//}

import org.openqa.selenium.WebDriver;

public class HandleRetryPage {
	
	WebDriver driver;
	
	public HandleRetryPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public void checkRetry() {
			driver.navigate().refresh();
			System.out.println("[INFO]Retry page refreshed successfully");
	}
}

