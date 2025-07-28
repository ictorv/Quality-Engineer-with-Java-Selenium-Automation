package com.selenium.tests;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import com.selenium.pages.GetFormValues;
import com.selenium.pages.SetFormValues;
import com.selenium.setup.DriverSetup;


import org.testng.annotations.Listeners;

@Listeners(FeatureTest.class)  //Do not change or remove this line.
public class TestInvoke extends DriverSetup {

    public static WebDriver driver;
    public static String baseUrl = "https://webapps.tekstac.com/FormRegistration/EMICalculator.html";

    private static GetFormValues getForm;
    private static SetFormValues setForm;
    private static String emiResult;
   

	public static String getEmiResult() {
		return emiResult;
	}

	public static void setEmiResult(String emiResult) {
		TestInvoke.emiResult = emiResult;
	}

	@BeforeClass
    public WebDriver setUp() {
        //create the driver using method 'getDriver' in class DriverSetup.
		//Assign it to the variable 'driver' and navigate to the baseUrl
		driver=DriverSetup.getDriver();
		driver.get(baseUrl);
		//DO NO CHANGE THE BELOW 2 OBJECT CREATIONS
         getForm = new GetFormValues(driver);
         setForm = new SetFormValues(driver);
		return driver;
    }

    @Test
    public void enterData() throws Exception {
       
        // Set user form values using the SetFormValues methods and pass the values
        // Click the calculate button to calculate EMI 
        // Call the method setAlert to handle the alert
        // Retrieve the EMI amount result using the GetFormValues method
        // Set the retrieved EMI result for further use or validation
        GetFormValues getObj=new GetFormValues(driver);
        SetFormValues setObj=new SetFormValues(driver);
        
        setObj.setName("John Doe");
        setObj.setAmount("10000");
        setObj.setYear("5");
        setObj.setROI("8.5");
        setObj.setCalculate();
        setObj.setAlert();
        
        TestInvoke.setEmiResult(getObj.getEmiAmount());
    }

    @AfterClass
    public void cleanup() {
        driver.close();
    }
}