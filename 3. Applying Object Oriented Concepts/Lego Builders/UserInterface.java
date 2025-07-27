import java.util.Scanner;

public class UserInterface {
	
	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		// Fill the code
		System.out.println("Enter the Product Id");
		String productId=sc.next();
		
		System.out.println("Enter the Product Type");
		String productType=sc.next();
		if (!(productType.equals("ElectricalItem") || productType.equals("PlumbingItem"))){
		    System.out.println(productType+" is an invalid product type");
		    return;
		}
		
		System.out.println("Enter the Product Name");
		String productName=sc.next();
		
		System.out.println("Enter the Quantity");
		int quantity=sc.nextInt();
		
		System.out.println("Enter the Price");
		double price=sc.nextDouble();
		
		double bill;
		if(productType.equals("ElectricalItem")){
		    ElectricalItemDetails pr=new ElectricalItemDetails(productId, productType, productName, quantity, price);
		    bill=pr.calculateTotalBill();
		    if(bill==0){
		        System.out.println("Product not available");
		        return;
    		}
    		System.out.println("Bill Amount is "+bill);
		}
		else if (productType.equals("PlumbingItem")){
		    PlumbingItemDetails pr=new PlumbingItemDetails(productId, productType, productName, quantity, price);
		    bill=pr.calculateTotalBill();
            if(bill==0){
		        System.out.println("Product not available");
		        return;
    		}
    		System.out.println("Bill Amount is "+bill);
		}
		
		
	}
}
