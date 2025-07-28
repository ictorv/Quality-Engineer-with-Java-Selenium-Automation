import java.util.Scanner;
import java.util.*;
public class UserInterface
{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        
	    // Fill your code here
	    System.out.println("Enter the department");
	    String dept=sc.nextLine();
	    
	    System.out.println("Enter the experience");
	    int exp=sc.nextInt();
	    
	    EmployeeManagementSystem ems=new EmployeeManagementSystem();
	    ArrayList<Employee> employees =ems.viewEmployeesByDepartmentAndExperience(dept, exp);
	    
	    if(employees.isEmpty()){
	        System.out.println("No employee records available for the "+dept+" department and "+exp+" years of experience");
	    }
	    else{
	        System.out.println("EMPLOYEEID EMPLOYEENAME DEPARTMENT SALARY EXPERIENCE");
	        for(Employee employee:employees){
	            System.out.println(employee.getEmployeeId()+" "+
	            employee.getEmployeeName()+" "+
	            employee.getDepartment()+" "+
	            employee.getSalary()+" "+
	            employee.getExperience()
	            );
	        }
	    }

    }
}