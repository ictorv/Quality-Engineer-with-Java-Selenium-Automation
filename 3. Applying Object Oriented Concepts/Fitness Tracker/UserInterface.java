
import java.util.Scanner;

public class UserInterface {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter user name:");
        String userName=sc.next();
        
        System.out.println("Enter weight in kg:");
        double weight=sc.nextDouble();
        
        System.out.println("Enter number of steps taken:");
        int stepsTaken=sc.nextInt();
        
        System.out.println("Enter distance covered in km:");
        double distanceCovered=sc.nextDouble();
        
        System.out.println("Is the user running?(true/false)");
        boolean runningStatus=sc.nextBoolean();
        
        UserFitnessData us=new UserFitnessData(userName, weight, stepsTaken, distanceCovered, runningStatus);
        // FitnessMonitor fn=new UserFitnessData(userName, weight, stepsTaken, distanceCovered, runningStatus);
        System.out.println("User Name: "+us.getUserName());
        System.out.println("Weight: "+us.getWeight()+" kg");
        System.out.println("Steps Taken: "+us.getStepsTaken());
        System.out.println("Distance Covered: "+us.getDistanceCovered()+" km");
        System.out.println("Running: "+us.getRunningStatus());
        System.out.println("Fitness Level: "+us.findFitnessLevel());
        System.out.printf("Calories Burnt: %.2f",FitnessMonitor.calculateCaloriesBurnt(weight,distanceCovered,runningStatus));
        //Fill the code here
    }
}