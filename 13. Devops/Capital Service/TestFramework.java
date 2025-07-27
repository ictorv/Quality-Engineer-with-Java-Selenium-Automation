package mytest;
import codes.CapitalService;
import junit.framework.TestCase;

public class TestFramework extends TestCase
{
	public void testGreeting() {
		CapitalService cs=new CapitalService();
		assertEquals("New Delhi",cs.getCapital("India"));
		
	}
}