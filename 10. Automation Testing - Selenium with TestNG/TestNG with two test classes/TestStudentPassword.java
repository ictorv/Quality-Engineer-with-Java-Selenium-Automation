import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class TestStudentPassword {
	
      // Add the required annotation
      @Test
	  public void testGeneratePassword() {
			Student s = new Student(146,"Emlie","EEE",8.5);
		    // Fill the code
	        String password=s.generatePassword();
	        String expectedPass="146EEEEml";
	        assertEquals(expectedPass,password);
	      
	  }
}
