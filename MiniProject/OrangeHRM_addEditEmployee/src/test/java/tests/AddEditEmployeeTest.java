package tests;

import base.BaseTest;

import java.io.IOException;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Pages.*;
import utils.ExcelUtil;
import utils.ExcelWriterUtil;
import utils.ExtentManager;

public class AddEditEmployeeTest extends BaseTest {

	// fetch data from employeedata obj
	// fetch url and directories from urlData.properties
	@Test(dataProvider = "employeeData")
	public void testAddEditEmployee(String firstName, String middleName, String lastName, String gender,
			String nationality, String maritalStatus) throws IOException {
		test = ExtentManager.createTest("Add/Edit Employee: " + firstName + " " + lastName);

//		test.info("Stating test for Add/Edit Employee");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login();
		test.log(Status.INFO, "Logged In");

		DashboardPage dashboardPage = new DashboardPage(driver);
		dashboardPage.PimButtonWait();
		dashboardPage.goToAddEmployee();
		test.log(Status.INFO, "Add Employee Navigated");

		String username = firstName + "_" + System.currentTimeMillis();
		String password = "PaSS_" +username+"_"+ System.currentTimeMillis();

		AddEmployeePage addEmployeePage = new AddEmployeePage(driver);
		addEmployeePage.waitForErrorScreenShot();
		Assert.assertNotNull(firstName, "First name is null");
		Assert.assertNotNull(middleName, "Middle name is null");
		Assert.assertNotNull(lastName, "Last name is null");
		Assert.assertFalse(firstName.trim().isEmpty(), "First name is empty or blank");
		Assert.assertFalse(middleName.trim().isEmpty(), "Middle name is empty or blank");
		Assert.assertFalse(lastName.trim().isEmpty(), "Last name is empty or blank");
		addEmployeePage.addEmployeeWithLogin(firstName, middleName, lastName, username, password);
		test.info("New Employee Created");
		String empId = addEmployeePage.getEmployeeId();
		dashboardPage.resultPopup();
		test.log(Status.INFO, "Employee added with id : " + empId + " and username : " + username);

//		dashboardPage.popupDisappear();

		dashboardPage.goToEmployeeList();
		EmployeeListPage employeeListPage = new EmployeeListPage(driver);
		employeeListPage.enterEmployeeDetails(firstName, empId);
		employeeListPage.clickOnSearchedEmployee(empId);
		test.log(Status.INFO, "Employeed Found");

		EditEmployeePage editDetails = new EditEmployeePage(driver);
		editDetails.waitForErrorScreenShot();
		
		// Null checks
		Assert.assertNotNull(gender, "Gender is null");
		Assert.assertNotNull(nationality, "Nationality is null");
		Assert.assertNotNull(maritalStatus, "Marital status is null");
		// Empty or blank checks
		Assert.assertFalse(gender.trim().isEmpty(), "Gender is empty or blank");
		Assert.assertFalse(nationality.trim().isEmpty(), "Nationality is empty or blank");
		Assert.assertFalse(maritalStatus.trim().isEmpty(), "Marital status is empty or blank");
		
		editDetails.assignDetails(nationality, maritalStatus, gender);
		dashboardPage.resultPopup();
		test.log(Status.INFO, "Employee Data Edited");

//		dashboardPage.popupDisappear();
		// if all testcases passes, add data to excel sheet
		ExcelWriterUtil.writeRow(properties.getPATH("EXCEL_PATH"),
				Arrays.asList(firstName, middleName, lastName, username, password, empId));
		HeaderPage header = new HeaderPage(driver);
		header.logout();
	}

	@DataProvider(name = "employeeData")
	public Object[][] employeeDataProvider() throws Exception {
		return ExcelUtil.getTestData(properties.getPATH("EXCEL_PATH"), "Sheet1");
	}

}
