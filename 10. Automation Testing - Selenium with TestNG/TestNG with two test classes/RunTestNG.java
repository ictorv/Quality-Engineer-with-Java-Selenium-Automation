import org.testng.collections.Lists;
import java.util.List;
import org.testng.*;

public class RunTestNG{
    
    public static void main(String[] args) {
		try {
				TestNG testng = new TestNG();
				List<String> suites = Lists.newArrayList();
				String workingDir = System.getProperty("user.dir");
				System.out.println("user.dir "+ workingDir);
				suites.add(workingDir + "/testng.xml");
				
				testng.setTestSuites(suites);
				testng.run();
				
			} catch (Exception e) {
				e.printStackTrace();
		} 
	}
}
