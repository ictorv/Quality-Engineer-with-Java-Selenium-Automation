import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class UserInterface {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		int option=0;
		String title="";
		String author="";
		int price=0;
		
		BookStoreUtility utilObj=new BookStoreUtility();
		do {
			System.out.println("Select an option:");
			System.out.println("1.Add Books\n2.Calculate Purchase Cost\n3.Exit");
			option=Integer.parseInt(sc.nextLine());
			
			switch(option) {
			case 1:
				System.out.println("Enter the book title");
				title=sc.nextLine();
				System.out.println("Enter the author name");
				author=sc.nextLine();
				System.out.println("Enter the book price");
				price=Integer.parseInt(sc.nextLine());
				Book b=new Book(title,author,price);
				utilObj.addBook(b);
				break;
			case 2:
			    if(utilObj.getBookList().isEmpty()){
			        System.out.println("There are no books available.Try to add books.");
			    }
			    else{
			        int val=utilObj.calculateTotalPurchaseCost(utilObj.getBookList().stream());
			        System.out.println("The total purchase cost is Rs "+val);
			    }
			    break;
			case 3:
				System.out.println("Thank you!!!");
				break;
			default:
				System.out.println("Invalid option. Please try again.");
				break;
			}
			
			
		}while(option!=3);
		
	}

}