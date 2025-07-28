import java.util.Scanner;
public class UserInterface{
    public static void main (String[] args) {
       Scanner sc = new Scanner(System.in);
       //Fill the code here 
       System.out.println("Enter the Customer Details");
       String inp=sc.next();
       String [] arr=inp.split(":");
       
       System.out.println("Enter the musical Instrument Details");
       String mus=sc.next();
       String [] marr=mus.split(":");
       
       Customer cust=new Customer(Integer.parseInt(arr[0]),arr[1],Long.parseLong(arr[2]));
       StringInstrument inst=new StringInstrument(Integer.parseInt(marr[0]),marr[1],marr[2],Double.parseDouble(marr[3]),cust,Integer.parseInt(marr[4]),marr[5]);
       
       double pr=inst.calculateTotalBill();
        if(pr==-1){
           System.out.println("Invalid Input");
           return;
        }
        if(Double.parseDouble(marr[3])>20000){ 
           System.out.printf("You have a discount! and your Total Bill is %.2f%n",pr);
        }
        else{
            System.out.printf("Total Bill is %.2f%n",pr);
        }
       
       
    }
}