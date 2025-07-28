import java.util.Scanner;
public class UserInterface {
	
	public static void main(String[] arg)
    {
        Scanner sc=new Scanner(System.in); 
        System.out.println("Enter dress code");
        String dressCode= sc.next();
        
        System.out.println("Enter dress style");
        String style=sc.next();
        System.out.println("Enter dress material");
        String material=sc.next();
        
        System.out.println("Enter dress price");
        double price=sc.nextDouble();
        

        
        int f=0;
        while (f==0){
            System.out.println("Prefered size is XL(Yes/No)");
            String opt=sc.next();
            

            
            if (opt.equalsIgnoreCase("yes")){
                f=1;
                // System.out.println("Enter yes");
                Order ora=new Order(dressCode, style, material,price);
                
                System.out.println("Dress code:"+ ora.getDressCode());
                System.out.println("Dress Style:"+ ora.getStyle());
                System.out.println("Material Type:"+ ora.getMaterial());
                System.out.println("Dress Size:"+ ora.getSize());
                System.out.println("Price:"+ ora.getPrice());
                return;
            }
            else if (opt.equalsIgnoreCase("no")){
                f=1;
                System.out.println("Enter the dress size");
                String size=sc.next();
                // System.out.println("Enter no");
                if (size.equals("XXL")|| size.equals("XXXL") || size.equals("XXXXL")){
                    price=price+100;
                }
                Order ora=new Order(dressCode, style, material,size, price);
                
                System.out.println("Dress code:"+ ora.getDressCode());
                System.out.println("Dress Style:"+ ora.getStyle());
                System.out.println("Material Type:"+ ora.getMaterial());
                System.out.println("Dress Size:"+ ora.getSize());
                System.out.println("Price:"+ ora.getPrice());
                return;
            }
            else{
                System.out.println("Wrong Input");
            }
            
            // return;
        }
        
        

    }
}

