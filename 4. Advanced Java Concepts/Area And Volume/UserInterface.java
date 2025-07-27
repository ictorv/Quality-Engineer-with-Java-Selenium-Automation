import java.util.Scanner;

public class UserInterface {
    public static Calculation areaOfSquare(){
        return (a)->{
          return (float)(a*a*22/7);  
        };
    }
    public static Calculation areaOfCircle(){
        return (a)->{
          return (float)a*a;  
        };
    }
    public static Calculation volumeOfCube(){
        return (a)->{
          return (float)a*a*a;  
        };
    }
    public static Calculation volumeOfSphere(){
        return (a)->{
          return (float)((4/3)*(22/7)*a*a*a);  
        };
    }

	public static void main(String args[]){
		Scanner sc=new Scanner(System.in);

        System.out.println("Enter a value");
		int a=sc.nextInt();
		if (a<=0){
		    System.out.println(a+" is an invalid value");
		    return;
		}
        Calculation as= areaOfSquare();
        float areasq=(int)as.areaAndVolumeCalculation(a);
        System.out.println("Area of the Square = "+areasq);
        
        
        Calculation ac= areaOfCircle();
        float areaci=(int)ac.areaAndVolumeCalculation(a);
        System.out.println("Area of the Circle = "+areaci);
        
        
        Calculation vc= volumeOfCube();
        float volcube=(int)vc.areaAndVolumeCalculation(a);
        System.out.println("Volume of the Cube = "+volcube);
        
        Calculation sph= volumeOfSphere();
        float volsph=(int)sph.areaAndVolumeCalculation(a);
        System.out.println("Volume of the Sphere = "+volsph);
	}
}
