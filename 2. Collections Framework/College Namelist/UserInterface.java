import java.util.*;

public class UserInterface {

	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		
		Student student=new Student();
		
		while(true){
		    System.out.println("1.Add");
		    System.out.println("2.Display");
		    System.out.println("3.Remove");
		    System.out.println("4.Exit");
		    System.out.println("Enter your choice");
		    
		    int choice;
		    try{
		        choice =sc.nextInt();
		        sc.nextLine();
		    }catch(InputMismatchException e){
		        System.out.println("Invalid choice. Please enter a number between 1 and 4");
		        sc.nextLine();
		        continue;
		    }
		    switch(choice){
		        case 1:
		            System.out.println("Enter the student name");
		            String name=sc.nextLine();
		            student.addStudentToList(name);
		            break;
		        case 2:
		            if(student.getStudentList().isEmpty()){
		                System.out.println("The student list is empty");
		            }
		            else{
		                student.sortStudentList();
		                for(String studentName:student.getStudentList()){
		                    System.out.println(studentName);
		                }
		            }
		            break;
		            
                    case 3:
        		        if (student.getStudentList().isEmpty()){
        		            System.out.println("The student list is empty");
        		        }
                        else{
                            System.out.println("Enter the student name");
                            String removeName=sc.nextLine();
                            student.removeStudentFromList(removeName);
                        }
        		        break; 
        		        
        		    case 4:
        		        System.out.println("Thank you for using the application");
        		        sc.close();
        		        return;
    		            
        		    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 4.");
		    }
		}
		
		
	}
}
