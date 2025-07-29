

TestScenario:

Detailed Description:
---------------------

1.Launch the browser
2.Enter the url "https://www.rediff.com"
3.Click on "Create Account" link
4.Validate “Create Rediffmail account” webpage is opened.
5.Find the total number of links in “Create Rediffmail account” webpage and print the links.
6.Click on "terms and conditions" link
7.Validate child window “Terms and Conditions” is opened.
8.Switch to the child window
9.Get the title of the child window
10.Validate the title of the child window with the expected title.
11.Close the child window
12.Switch to the parent window (“Create Rediffmail account” webpage)
13.Close the browser



OutPut: (In Console)
-------
Opened the URL in the Edge browser
Total Failed Validations in Edge : 1
Opened the URL in the Chrome browser
Total Failed Validations in Chrome : 1

===============================================
Suite
Total tests run: 2, Passes: 2, Failures: 0, Skips: 0
===============================================




Sample project Structure:
-------------------------

Rediff_Com/
│
├── pom.xml
├── testng.xml
├── README.txt
│
├── /src           
│   └── /test
│       └── /java
│           └── com.selenium.data       		  --> ExcelUtils, Constants, MiniProject(Rediff)_TestData.xlsx(Excel for test inputs/results)
│           └── com.selenium.mainTest   		  --> TestMain
│			└── com.selenium.pages      		  --> ChildWindow, ParentWindow
│			└── com.selenium.rediffUtilities   --> DriverSetup, ExtentReportManager, ScreenShot, WaitUtils		 
│                   
├── /reports
│   └── (Extent Reports).html
├── /screenshots
│   └── All screenshots captured





⚙️ Tools Used
--------------
Java 21+

Selenium 4.x

TestNG 7.x

Apache POI 5.x

ExtentReports 5.x

Maven Project 

PageFactory
