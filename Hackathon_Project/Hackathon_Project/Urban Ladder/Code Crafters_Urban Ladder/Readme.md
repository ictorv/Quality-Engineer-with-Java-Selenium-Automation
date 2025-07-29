# Urban Ladder Test Automation Framework

A comprehensive automated testing framework for the Urban Ladder web application using Selenium WebDriver, TestNG, and Cucumber.

## Project Objectives

- Conduct end-to-end automated UI testing of the Urban Ladder website
- Facilitate cross-browser testing using Selenium Grid
- Implement data-driven testing for extensive input coverage
- Generate structured logs and reports for better traceability
- Capture failure evidence using automated screenshots
- Provide a reusable and scalable testing architecture

## Tech Stack

- Java 21
- Selenium WebDriver
- TestNG & Cucumber
- Maven
- Log4j2
- Allure Reports
- Apache POI
- Selenium Grid

## Project Structure

```
UrbanLadder_Major_Project
└── src/
    ├── main/
    │   ├── java/
    │   └── resources/
    └── test/
        ├── java/
        │   └── Urban_ladder/
        │       ├── com.urban.base/                 # Base class (Setup)
        │       ├── com.urban.hooks/                # Cucumber hooks (@Before, @After)
        │       ├── com.urban.pageObject/           # Page Object Model classes
        │       ├── retry/                          # Retry logic for failed test reruns
        │       ├── com.urban.stepDefinitions/      # Step definitions for feature files
        │       ├── com.urban.testRunner/           # TestNG or Cucumber runner classes
        │       └── com.urban.utilities/            # Reusable utilities (WebDriver, readers)
        └── resources/
            ├── feature/                # Gherkin feature files
            ├── config.properties       # Environment and test configuration
            ├── log4j2.xml              # Logging configuration
            ├── BeingAtHome.xml         # Test data in XML format
            ├── TrackOrder.xml          # Test data in XML format
            ├── allure.properties       # Allure report configuration
            └── ProjectData.xlsx        # Test data in Excel format
├── logs/                          # Log files
├── Screenshots/                   # Failure screenshots
├── target/                       # Build and report output
├── gridRun.bat                   # Grid execution script
├── mvn-run.bat                  # Maven execution script
├── pom.xml                       # Maven configuration
└── testng.xml                    # TestNG configuration
```

## Prerequisites

- Java JDK 21
- Maven 3.6+
- Chrome/Edge browser
- Allure Command Line
- Selenium Grid (optional)

## Quick Start

1. Clone the repository
2. Install dependencies:
```bash
mvn clean install
```

3. Run tests:
```bash
mvn test
```

4. Generate Allure report:
```bash
allure serve target/allure-results
```

## Key Features

- Page Object Model with PageFactory
- Cross-browser testing (Chrome/Edge)
- BDD support with Cucumber
- Data-driven testing (Excel/XML/Properties)
- Automatic retry for failed tests
- Comprehensive logging
- Failure screenshots
- Parallel execution with Selenium Grid
- Detailed Allure reporting

## Framework Features

### Page Object Model (POM)
The framework uses the Page Object Model with PageFactory to ensure separation of concerns and improve reusability and readability.

### Cross-Browser Testing
Browser support is parameterized via TestNG. The framework supports execution on both Chrome and Edge.

### Cucumber Integration
Supports BDD-style test development using Gherkin feature files and step definitions.

### Data-Driven Testing
Supports dynamic input through external data sources:
- Excel files using Apache POI
- XML files for structured data
- `.properties` files for configuration

### Retry Mechanism
Includes a custom implementation of TestNG's `IRetryAnalyzer` to rerun failed tests and handle flaky test cases.

## Test Execution

### Regular Run
```bash
mvn test
```

### Specific Suite
```bash
mvn test -DsuiteXmlFile=testng.xml
```

### Grid Execution
```bash
./gridRun.bat
```

## Output Locations

- Logs: `logs/automation.log`
- Screenshots: `screenshots/`
- Reports: `target/allure-results/`
- Test Results: `target/test-output/`

## Contributing

Feel free to submit issues and enhancement requests.

## License

For educational and non-commercial use only.
