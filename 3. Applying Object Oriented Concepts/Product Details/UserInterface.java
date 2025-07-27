import java.util.Scanner;
public class UserInterface {
    public static boolean validateProductPrice(int price) throws InvalidPriceException
    {
        if (price>=1 && price <=20){
            return true;
        }
        else{
            throw new InvalidPriceException("Product price is invalid");
        }
    }
	public static void main(String[] args)
	{
	    Scanner sc= new Scanner(System.in);
	// Fill the code here 
    	System.out.println("Enter the number of product entries");
        int number =sc.nextInt();
        sc.nextLine();
        for(int i=1; i<=number;i++){
            System.out.println("Enter product "+i+" details");
            String productDetails=sc.nextLine();
            String[] details=productDetails.split(":");
            int productPrice=Integer.parseInt(details[2]);
            try{
                if(validateProductPrice(productPrice)){
                    System.out.println("Stock updated");
                }
            }catch(InvalidPriceException e){
                System.out.println(e.getMessage());
            }
        }
    
	}

}
