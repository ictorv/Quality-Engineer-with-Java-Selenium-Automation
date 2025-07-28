import java.util.Scanner;

public class UserInterface {

	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the radius");
		double r=sc.nextDouble();
		System.out.println("Enter the height");
		double h=sc.nextDouble();
		
		if (r<=0 || h<=0){
		    System.out.println("Invalid measurement");
		    return;
		}
		else{
		    double cap=3.14*r*r*h;
		    System.out.println(String.format("Total capacity is %.2f litres",cap));
		    System.out.println(String.format("Capacity for alarm is %.2f litres",3*cap/4));
		}
		
	}

}
