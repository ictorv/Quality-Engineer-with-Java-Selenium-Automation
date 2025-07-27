import java.util.*;
public class UserInterface {
	
	public static Calculate calculateSlope() {
	    
		return (x1,x2,y1,y2)->{
		    int slope= (y2-y1)/(x2-x1);  
		    return "Slope of the line is " +slope;
		} ;
		
	}
	
	public static Calculate calculateMidpoint() {
	    
		return (x1,x2,y1,y2)->{
		    int midX= (x1+x2)/2;  
		    int midY= (y1+y2)/2;
		    return "Mid point of the line is (" +midX+","+midY+")";
		};
	}
	public static void main(String[] args) {
		Scanner sc=  new Scanner(System.in);
		
		System.out.println("Enter the values for x intercepts and y intercepts");
        int x1=sc.nextInt();
        int x2=sc.nextInt();
	    int y1=sc.nextInt();
	    int y2=sc.nextInt();
	    
	    Calculate sl=calculateSlope();
	    String slRet=sl.performCalculation(x1,x2,y1,y2);
	    System.out.println(slRet);
	    
        Calculate mp=calculateMidpoint();
	    String slMp=mp.performCalculation(x1,x2,y1,y2);
	    System.out.println(slMp);
	   // System.out.println("Slope of the line is "+calculateSlope(x1, x2, y1, y2).performCalculation());
	   // System.out.println("Slope of the line is "+calculateMidpoint(x1, x2, y1, y2).performCalculation());
	    
	}
}
