cd C:\Users\2403738\selenium-workspace\Identify-Car-Wash-Services
mvn clean test
allure generate allure-results --clean -o allure-report --name "Allure Report - JustDial"
allure open allure-report
