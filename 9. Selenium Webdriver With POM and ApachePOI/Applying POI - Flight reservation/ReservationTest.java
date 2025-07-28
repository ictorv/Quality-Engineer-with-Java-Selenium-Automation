import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ReservationTest {      //Do not change the class name

    public static WebDriver driver;
    String baseUrl = "https://webapps.tekstac.com/selenium/FlightReservation/index.html";
    private static String successMsg;
    
    public static String getSuccessMsg() {
		return successMsg;
	}

	public static void setSuccessMsg(String successMsg) {
		ReservationTest.successMsg = successMsg;
	}

	public WebDriver createDriver() {  //Do not change the method signature
        //Implement code to create driver and assign it to 'static' driver variable	
    	//Navigate to the baseUrl and return it
        driver=DriverSetup.getDriver();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        driver.get(baseUrl);
    	return driver;
    }

    public void testReservationForm() throws IOException {    //Do not change the method signature
    	//Call the method readExcelData using the filename 'reservationvalid.xlsx'
        //find the elements in the form and set values parsed from excel sheet. 
    	//Submit the form for reservation
    	//Read the success message displayed and set the success message using setSuccessMsg method
    	String [] data=Reservation.readExcelData("reservationvalid.xlsx");
    	
    	WebElement name=driver.findElement(By.id("name"));
    	name.sendKeys(data[0]);
    	
    	WebElement phone=driver.findElement(By.id("phonenumber"));
    	phone.sendKeys(data[1]);
    	
    	WebElement tickets=driver.findElement(By.id("tickets"));
    	tickets.sendKeys(data[2]);
    	
    	Select departureOptions=new Select(driver.findElement(By.id("departureCity")));
        departureOptions.selectByVisibleText(data[3]);
        
        Select destinationOptions=new Select(driver.findElement(By.id("destinationCity")));
        destinationOptions.selectByVisibleText(data[4]);
        
        driver.findElement(By.id("submit")).click();
        
        String result=driver.findElement(By.id("result")).getText();
        ReservationTest.setSuccessMsg(result);
        
    }
    
    public static void main(String[] args) throws Exception 
	{ 
    	ReservationTest obj = new ReservationTest();
    	//Add required code
    	obj.createDriver();
    	obj.testReservationForm();
    
	}
}