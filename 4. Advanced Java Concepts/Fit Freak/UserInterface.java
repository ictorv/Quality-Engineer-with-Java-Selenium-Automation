import java.util.Scanner;
import java.util.*;
public class UserInterface
{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        
        FitFreakManagementSystem ffms=new FitFreakManagementSystem();
        System.out.println("Enter the age:");
        int age=sc.nextInt();
        sc.nextLine();
        List<FitFreak> ffl=ffms.retrieveFitFreakListBasedOnAge(age);
        if(ffl.isEmpty()){
            System.out.println("No FitFreak records available for the given age");
        }
        else{
            for(FitFreak ff:ffl){
                System.out.println(ff.toString());
            }
        }
    }
}	 	  	  		    	  	      	      	 	
