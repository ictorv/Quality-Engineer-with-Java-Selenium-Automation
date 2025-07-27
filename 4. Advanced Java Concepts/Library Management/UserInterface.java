import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class UserInterface {
	public static void main(String [] args)
	{

	    Scanner sc=new Scanner(System.in);
	    // FILL THE CODE HERE
	    BookUtility bookUtility=new BookUtility();
	    System.out.println("Enter the number of entries");
		int numEntries=sc.nextInt();
		sc.nextLine();
		 System.out.println("Enter the bookId/bookName/bookType/bookCategory/bookEdition");
		 for(int i=0;i<numEntries;i++){
		    String input=sc.nextLine();
		    String[]bookDetails=input.split("/");
		    Book book=new Book(bookDetails[0],bookDetails[1],bookDetails[2],bookDetails[3],bookDetails[4]);
		    try{
		        bookUtility.toValidateBookType(book);
		    }catch(InvalidBookException e){
		        System.out.println(e.getMessage());
		        return;
		    }
		 }
		 
		 
		 if(!bookUtility.getBookList().isEmpty()){
		    System.out.println("Print the book details");
		    for(Book book:bookUtility.getBookList()){
		        System.out.println("bookId="+book.getBookId()+", bookName="+book.getBookName()+", bookType="+book.getBookType());
    		}
    		
    		System.out.println("Enter book name to search");
		    String searchBookName=sc.nextLine();
		    bookUtility.setSearchbookName(searchBookName);
    		
    		
		    Thread thread=new Thread(bookUtility);
		    thread.start();
		 
		    try{
		        thread.join();
		    }catch(InterruptedException e){
		        e.printStackTrace();
    		}
		     
	        if(bookUtility.getCounter()>0){
		        System.out.println("Count of books in the library with the book name "+searchBookName.toUpperCase()+" is "+bookUtility.getCounter());
		    
    		}else{
	    	    System.out.println("No Books found");
		    }
	
		 }
		 
		 
	}
}