import java.util.Scanner;
import java.util.*;

public class UserInterface {

	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the number of details");
		int inp=sc.nextInt();
		sc.nextLine();
		
		if(inp<1){
		    System.out.println(inp+" is an invalid input");
		    return;
		}
		System.out.println("Enter the details");
		String d;
		
		DepartmentGrouping obj=new DepartmentGrouping();
		for (int i=0;i<inp;i++){
		    d=sc.nextLine();
		    obj.addEmployeeId(d);
		}
		if(obj.findDepartmentNameWithEmployeeId().isEmpty()){
		    System.out.println("All the provided employee ids are invalid");
		}
		else{
		    HashSet<String>f=obj.findDepartmentNameWithEmployeeId();
		    for(String a:f){
		        System.out.println(a);
		    }
		}
	}
}	