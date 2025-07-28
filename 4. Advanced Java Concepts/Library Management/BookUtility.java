import java.util.ArrayList;
import java.util.List;

public class BookUtility implements Runnable{
	private List<Book> bookList = new ArrayList<Book>();
	private String searchbookName;
	private int counter;
	
	//FILL THE CODE HERE
	public List<Book>getBookList(){
	    return bookList;
	}
	public void setBookList(List<Book> bookList){
	    this.bookList=bookList;
	}
	
	public String getSearchbookName(){
	    return searchbookName;
	}
	
	public void setSearchbookName(String searchbookName){
	    this.searchbookName=searchbookName;
	}
	public int getCounter(){
	    return counter;
	}
	public void setCounter(int counter){
	    this.counter=counter;
	}
	public void toValidateBookType(Book obj) throws InvalidBookException
	{
	    if(obj.getBookType().equalsIgnoreCase("Engineering")){
	        bookList.add(obj);
	    }
	    else{
	        throw new InvalidBookException("Book type Invalid");
	    }
	}
	
	public void run()
	{
		//FILL THE CODE HERE
		counter=0;
		synchronized(bookList){
		    for(Book book:bookList){
		        if(book.getBookName().equalsIgnoreCase(searchbookName)){
		            counter++;
		        }
		    }
		}
	}

}
