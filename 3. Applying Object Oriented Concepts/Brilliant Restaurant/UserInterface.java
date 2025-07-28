import java.util.Scanner;
public class UserInterface 
{
	public static void main(String args[]) 
	{
		Scanner sc =new Scanner(System.in);
		System.out.println("Enter the Name");
		String name=sc.next();
		
		System.out.println("Enter the Order Number");
		int orderNumber=sc.nextInt();
		
		System.out.println("Enter the Delivery Type");
		String type=sc.next();
		if (!(type.equals("HomeDelivery") || type.equals("Parcel") || type.equals("NormalOrder")) ){
		    System.out.println("Invalid Order Type");
		    return;
		}
		
		System.out.println("Enter the Food Name");
		String foodName=sc.next();
		
		System.out.println("Enter the Phone Number");
		long phoneNumber=sc.nextLong();
		
		System.out.println("Enter the price of the item");
		double price=sc.nextDouble();
		
		
		Restaurant res=new Restaurant(name, orderNumber, type, foodName,phoneNumber);
		
		System.out.println("Name "+res.getCustomerName());
		System.out.println("Order Number "+res.getOrderNumber());
		System.out.println("Delivery Type "+res.getDeliveryType());
		System.out.println("Food Name "+res.getFoodName());
		System.out.println("Phone Number "+res.getPhoneNumber());
		System.out.printf("Bill Amount %.1f%n",res.calculateTotalBill(price));

	}
}
