import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BookCabServiceTest {

	private static List<Booking> cabList = new ArrayList<Booking>();
	private static BookCabService serviceObj;
	private static SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");

	
	//Add Required annotation
	@BeforeClass
	public static void setUp() throws Exception {

		serviceObj = new BookCabService();	
		Booking cab[]=new Booking[5];

		cab[0]=new Booking("178","Prime","LosAngeles","New York",21);
		cab[1]=new Booking("179","Micro","San Antonio","Philadelphia",20);
		cab[2]=new Booking("180","Prime","New York","Houston",12);
		cab[3]=new Booking("181","Mini","Chicago","LosAngeles",14);
		cab[4]=new Booking("182","Prime","Houston","San Diego",21);

		cabList = new ArrayList<>(Arrays.asList(cab));	
		serviceObj.setBookingList(cabList);
	}


	//Test viewBookingById method for an existing bookingId
	//Include appropriate annotation
	@Test
	public void test11ViewBookingByIdWhenValid() {
		//fill code here	
		//Use assertTrue assertion to test the viewBookingById method
        Booking book=serviceObj.viewBookingById("178");
        assertTrue(book!=null);
	}

	//Test viewBookingById method when the bookingId does not exist
	//Include appropriate annotation
	@Test
	public void test12ViewBookingByIdWhenInvalid() {
		//fill code here
		//Create an object for the Booking class usnig the variable name book
		//Use assertNull assertion to check the book object
    	Booking book=serviceObj.viewBookingById("101");
        assertNull(book);
	}

	//Test viewBookingByCabType method
	//Include appropriate annotation
	@Test
	public void test13ViewBookingByCabType() {
		//fill code here
        //create a list named ins, holding the values by calling the viewBookingByCabType method
        //Use assertSame assertion to check the size of the list ins
        List<Booking>ins =serviceObj.viewBookingByCabType("Prime");
    	assertSame(3,ins.size());
	}
    
	//Test viewBookingByCabType method when the list is empty
    //Include appropriate annotation
	@Test
	public void test14ViewBookingByCabTypeForEmptyList() {
		//fill code here
		//Set the setBookingList with an empty list
		//Create a list named booking, holding the values by calling the viewBookingByCabType method 
        //Use assertFalse assertion to check the size of the list booking
        serviceObj.setBookingList(serviceObj.viewBookingByCabType("Large"));
	    List<Booking> booking=serviceObj.getBookingList();
	    assertFalse(booking.size()>0);
	    
	}

}
