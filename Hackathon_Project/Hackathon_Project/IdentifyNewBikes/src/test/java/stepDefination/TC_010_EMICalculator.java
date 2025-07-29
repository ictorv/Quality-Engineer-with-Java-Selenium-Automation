package stepDefination;

import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pageObject.CalculatorPage;
import pageObject.EmiCalculatorPage;
import pageObject.HomePage;
import pageObject.NewCarsPage;
import utilities.JSONUtils;

public class TC_010_EMICalculator {
	WebDriver driver = BaseClass.getDriver();
	HomePage home= new HomePage(driver);
	NewCarsPage ncp = new NewCarsPage(driver);
	EmiCalculatorPage ecal = new EmiCalculatorPage(driver);
	CalculatorPage cal = new CalculatorPage(driver);
	JSONUtils data = new JSONUtils("EMICalculator.json");
			
	String[] Data= new String[7];
	public void readData() {
		Data[0]=data.getValue("emi Calculator");
		Data[1]=data.getValue("make");
		Data[2]=data.getValue("model");
		Data[3]=data.getValue("variant");
		Data[4]=data.getValue("downPayment");
		Data[5]=data.getValue("tenure");
		Data[6]=data.getValue("interest");
	}
	
	@Given("clicks on the NEW CARS")
	public void clicks_on_the_new_cars() {
	    home.clickNewCars();
	}

	@Then("Scroll to the Quick Research and click on the EMI Calculator")
	public void scrolls_to_the_quick_research() {
		readData();
		ncp.emiCalculator(Data[0]);
	}

	@Then("set Make, Model and Verient")
	public void set_make_model_and_variant() {
	    // Write code here that turns the phrase above into concrete actions
	    ecal.setMakeType(Data[1]);
	    ecal.setModelType(Data[2]);
	    ecal.setVariantType(Data[3]);
	}

	@Then("click on Check EMI")
	public void click_on_the_check_emi() {
	    // Write code here that turns the phrase above into concrete actions
	    ecal.getCalculator();
	}

	@Then("Adjust the Downpayment using the Slider")
	public void adjust_the_downpayment_using_the_slider() {
	    // Write code here that turns the phrase above into concrete actions
	    cal.setDownPayment(Data[4]);
	}

	@Then("Set the Loan Tenure by select duration in years")
	public void set_the_loan_tenure_by_select_duration_in_years() {
	    // Write code here that turns the phrase above into concrete actions
	    cal.setTenure(Data[5]);
	}

	@Then("Set the Intrest rate using the Slider")
	public void set_the_intrest_rate_using_the_slider() {
	    // Write code here that turns the phrase above into concrete actions
	   cal.setIntrestRate(Data[6]);
	}

	@Then("Display the value showed below Your monthly EMI")
	public void display_the_value_showed_below_your_monthly_emi() {
	    // Write code here that turns the phrase above into concrete actions
	    String emi = cal.getMonthlyEmi();
	    System.out.println("EMI:"+emi);
	}
	

}
