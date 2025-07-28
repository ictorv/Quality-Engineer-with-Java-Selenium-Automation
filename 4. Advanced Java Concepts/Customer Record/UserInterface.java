import java.util.Scanner;
import java.sql.*;
public class UserInterface {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the customer id");
		String customerId=sc.nextLine();
		
		System.out.println("Enter the customer name");
	    String customerName=sc.nextLine();
	    
	    System.out.println("Enter the email");
	    String customerEmail=sc.nextLine();
	    
	    System.out.println("Enter the phoneNo");
	    String phoneNo=sc.nextLine();
	    
	    Customer customer=new Customer(customerId, customerName, customerEmail,phoneNo);
	    CustomerUtility util=new CustomerUtility();
	    
	    try{
	        if(util.validateCustomerEmail(customerEmail) && util.validatePhoneNo(phoneNo)){
	            CustomerManagementSystem cms= new CustomerManagementSystem();
	            int result =cms.insertCustomerRecordsToDB(customer);
	            if(result==1){
	                System.out.println("Customer record is added successfully");
	            }
	            else{
	                System.out.println("Failed to add the Customer record");
	            }
	            
	        }
	        else{
	            System.out.println("Failed to add the Customer record");
	        }
	    }catch (SQLException| ClassNotFoundException e){
	            e.printStackTrace();
	        }
	}
}
