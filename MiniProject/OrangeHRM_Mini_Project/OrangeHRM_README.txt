
OrangeHRM_Mini_Project
======================

Project Description:
--------------------
This project automates the process of adding and editing employee details in the OrangeHRM demo application using Selenium WebDriver with Java and TestNG. It demonstrates handling various web elements such as text boxes, dropdowns, radio buttons, and buttons, and integrates reporting and data-driven testing using Excel and ExtentReports.

Test Scenario:
--------------
1. Launch the OrangeHRM demo site in Chrome and Edge browsers.
2. Login using admin credentials.
3. Navigate to PIM > Add Employee.
4. Enter First Name, Middle Name, and Last Name.
5. Enable "Create Login Details" and enter username and password.
6. Save the employee details.
7. Navigate to Employee List and select the newly created employee.
8. Edit the employee's nationality, gender, and marital status.
9. Save the updated details.
10. Logout and close the browser.

Expected Output:
----------------
- Employee is successfully added with login credentials.
- Employee details are updated correctly.
- Screenshots are captured for each step and attached to the Extent Report.
- Test results are logged in the Excel sheet.

OutPut: (In Console)
--------------------
Chrome Browser Launched
Config properties loaded successfully.
[INFO] Employee already exists. Removing...
Employee deleted successfully.
Browser Closed
Edge Browser Launched
[INFO] Employee already exists. Removing...
Employee not found or elements not loaded. Skipping deletion.
stale element reference: stale element not found in the current frame
Browser Closed

===============================================
EmployeeAutomationSuite
Total tests run: 6, Passes: 6, Failures: 0, Skips: 0
===============================================



Project Structure:
------------------
OrangeHRM_Mini_Project/
├── src/
│   ├── test/
│   │   └── java/
│   │       ├── com.test.data/
│   │       │   ├── ExcelDataSheet.java
│   │       │   └── Employee.xlsx
│   │       ├── com.test.pages/
│   │       │   ├── AddEmployeePage.java
│   │       │   ├── DashboardPage.java
│   │       │   ├── EditEmployeePage.java
│   │       │   └── LoginPage.java
│   │       ├── com.test.utilities/
│   │       │   ├── DriverSetup.java
│   │       │   ├── ExtentReportsManager.java
│   │       │   ├── Screenshot.java
│   │       │   ├── WaitUtil.java
│   │       │   └── TestListener.java
│   │       └── com.test.testCases/
│   │           └── TestMain.java
├── pom.xml
└── testng.xml

Tools and Technologies Used:
----------------------------
- Java
- Selenium WebDriver
- TestNG
- Apache POI (Excel)
- ExtentReports
- Maven
- ChromeDriver and EdgeDriver

Instructions:
-------------
1. Clone the project and import into Eclipse.
2. Ensure Maven dependencies are installed.
3. Update browser drivers if needed.
4. Run the test using testng.xml.
5. View the generated Extent Report and screenshots in the /reports and /screenshots folders.
