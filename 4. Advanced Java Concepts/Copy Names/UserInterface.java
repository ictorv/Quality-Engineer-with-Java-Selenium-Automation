import java.util.Scanner;
import java.io.*;
public class UserInterface {
	public static void main(String args[]){
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the starting character:");
		char startingChar=sc.next().charAt(0);
		
		startingChar=Character.toUpperCase(startingChar);
		
		int count=0;
		
		try (BufferedReader reader=new BufferedReader(new FileReader("file1.txt"));
		FileWriter writer=new FileWriter("file2.txt"))
		{
		    String line;
		    while((line=reader.readLine())!=null){
		        if(line.charAt(0)==startingChar){
		            writer.write(line+System.lineSeparator());
		            count++;
		        }
		    }
		    if(count>0){
		        System.out.println(count+" names are added to the file2");
		    }
		    else{
		        System.out.println("No names found");
		    }
		    
		}
		catch(IOException e){
		    System.out.println("An error occured during file I/O operations: "+e.getMessage());
		}
		//Fill the code here
	}
}