package userDefinedLibraries;

import java.io.IOException;

import org.testng.annotations.DataProvider;

//Utility class that provides test data to TestNG test methods using @DataProvider
public class TestDataProviderUtils {

	@DataProvider(name = "studentData")
	public static Object[][] getStudentData() throws IOException {

		// Define the path to the Excel test data file
		String filepath = System.getProperty("user.dir") + "\\testdata\\testDatademoQA.xlsx";

		// Specify the sheet name that contains the data
		String sheetName = "Form";

		// Read and return the data from Excel as a 2D Object array
		Object[][] data = ExcelUtils.readMultipleData(filepath, sheetName);
		return data;
	}
}
