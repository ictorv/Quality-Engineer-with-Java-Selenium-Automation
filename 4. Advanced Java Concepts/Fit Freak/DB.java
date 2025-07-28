import java.sql.*;
import java.util.*;
import java.io.*;

public class DB {

	private static Connection con = null;
	private static Properties props = new Properties();

	public static Connection getConnection(){
	 //ENSURE YOU DON'T CHANGE THE BELOW CODE WHEN YOU SUBMIT
		
		try{
		    FileInputStream fis=new FileInputStream("database.properties");
		    props.load(fis);
		    Class.forName(props.getProperty("DB_DRIVER_CLASS"));
		    con=DriverManager.getConnection(props.getProperty("DB_URL"),props.getProperty("DB_USERNAME"),props.getProperty("DB_PASSWORD"));
		}
		catch(IOException e){
	    	e.printStackTrace();
	    }
	    catch(ClassNotFoundException ce){
	    	ce.printStackTrace();
	    }
	    catch(SQLException se){	 	  	  			  	  	     	 	
	    	se.printStackTrace();
	    }
 		return con;
	}
}
