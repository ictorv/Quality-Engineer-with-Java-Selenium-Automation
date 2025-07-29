package com.urban.utillities;

import java.io.IOException;

public class AllureReportOpener {
	
	public static void openAllureReport() {
		try {
			// Step 1: Generate Allure report
			ProcessBuilder generate = new ProcessBuilder(

					"C:\\Users\\2403712\\Softwares\\allure-2.34.1\\allure-2.34.1\\bin\\allure.bat",
					"generate", "target/allure-results", "-o", "target/allure-report", "--clean");
			generate.inheritIO(); // Optional: shows output in console
			Process genProcess = generate.start();
			genProcess.waitFor();

			// Step 2: Open Allure report in browser
			ProcessBuilder open = new ProcessBuilder(
					"C:\\Users\\2403712\\Softwares\\allure-2.34.1\\allure-2.34.1\\bin\\allure.bat", "open",
					"target/allure-report");
			open.inheritIO();
			Process openProcess = open.start();
			openProcess.waitFor();

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
