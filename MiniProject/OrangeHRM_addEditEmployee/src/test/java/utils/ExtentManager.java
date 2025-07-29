package utils;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {
	private static ExtentReports extent;
	private static ExtentTest test;

	public static ExtentReports getInstance() {
		if (extent == null) {
			String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
			ExtentSparkReporter reporter = new ExtentSparkReporter("./ExtentReports/Report_" + timestamp + ".html");  //Report_2025-06-26_11-37-31.html
			extent = new ExtentReports();
			extent.attachReporter(reporter);
			reporter.config().setReportName("OrangeHRM Automation Report");
			reporter.config().setDocumentTitle("OrangeHRM Test Results");
		}
		return extent;
	}
	
	public static ExtentTest createTest(String testName) {
		test = getInstance().createTest(testName);
		return test;
	}
	
	public static void flush() {
		if (extent != null) {
			extent.flush();
		}
	}
}
