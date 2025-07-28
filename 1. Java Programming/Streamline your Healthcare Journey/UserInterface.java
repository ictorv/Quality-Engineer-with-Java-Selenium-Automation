import java.util.Scanner;

public class UserInterface {

	public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        System.out.println("Enter your name");
        String n=sc.nextLine();
        System.out.println("Enter your height in meters");
        double h=sc.nextDouble();
        System.out.println("Enter your weight in kilograms");
        double w=sc.nextDouble();
        
        double bmi=w/(h*h);
        System.out.println(String.format("Hello %s, Your BMI is %.2f",n,bmi));
        System.out.print("You are under the category ");
        if (bmi<18.5){
            System.out.println("Under weight");
        }
        else if(bmi<25){
            System.out.println("Normal weight");
        }
        else if(bmi<=30){
            System.out.println("Over weight");
        }
        else if(bmi>30){
            System.out.println("Obese");
        }

    }
}
