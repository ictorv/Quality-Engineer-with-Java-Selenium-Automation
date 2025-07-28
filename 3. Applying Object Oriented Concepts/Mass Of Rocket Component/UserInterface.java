import java.util.Scanner;
public class UserInterface {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		//Fill the code here
		System.out.println("Enter the component name");
		String name=sc.next();
		System.out.println("Enter the component weight on earth");
		double weight=sc.nextDouble();
		if (weight<=0){
		  //  RocketComponent test=new RocketComponent("Test", weight, "Test",-1);
		    System.out.println(weight+" Newton is an invalid weight");
		    return;
		}
		System.out.println("Enter the material used");
		String material=sc.next();
		System.out.println("Enter the cost of production");
		double costOfProduction=sc.nextDouble();
		
		RocketComponent r=new RocketComponent(name, weight, material, costOfProduction);
		
		System.out.println("Rocket Component");
		System.out.println("Name:"+r.getName());
		System.out.println("Weight:"+r.getWeight()+" Newton");
		System.out.println("Acceleration Due To Gravity: 9.8 meters per second square");
		System.out.println("Mass:"+r.calculateMassOfRocketComponent(weight)+" kg");
		System.out.println("Material Used:"+r.getMaterial());
		System.out.println("Cost Of Production:Rs."+r.getCostOfProduction());
	}
	
}	