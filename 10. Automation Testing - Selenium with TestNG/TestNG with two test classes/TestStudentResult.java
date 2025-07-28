import org.testng.annotations.Test;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;

public class TestStudentResult {
	  
	  // Add the required annotation
	  @Test
	  public void testResult() {
		Student s = new Student(146,"Emlie","EEE",8.5);
		   // Fill the code
	    String result=s.getResult();
	    String actualResult="First class";
	    assertEquals(result, actualResult);
	      
	  }
}
