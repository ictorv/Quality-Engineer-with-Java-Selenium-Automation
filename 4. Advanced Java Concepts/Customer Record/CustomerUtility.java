import java.util.regex.Pattern;
public class CustomerUtility {
	public boolean validateCustomerEmail(String customerEmail) {
	
		//Fill the code here
		String regexEmail="^[\\w.-]+@[\\w.-]+\\.\\w{2,}$";
		return Pattern.matches(regexEmail,customerEmail);
		  //  	return false;
	}
	public boolean validatePhoneNo(String phoneNo){
	    String regexPhNo="^[0-9]{10}$";
	    return Pattern.matches(regexPhNo,phoneNo);
	}
}
