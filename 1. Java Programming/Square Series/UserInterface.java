import java.util.Scanner;

public class UserInterface {
    public static void main(String[] args) {
    	Scanner sc=new Scanner(System.in);
    	System.out.println("Enter the first number");
    	int n=sc.nextInt();
    	System.out.println("Required Square Increment Sequence");
    	for (int i=0;i<11;i++){
    	    System.out.print((n+i)*(n+i)+1+" ");
    	}
    }
}
