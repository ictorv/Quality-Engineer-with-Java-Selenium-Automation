import java.util.*;
import java.sql.*;
import java.util.List;
public class FitFreakManagementSystem {
    public List<FitFreak>retrieveFitFreakListBasedOnAge(int age){
        List<FitFreak>fitFreaks=new ArrayList<FitFreak>();
        Connection cn=null;
        
        try{
            cn=DB.getConnection();
            String query="SELECT * FROM FITFREAK WHERE AGE<=?";
            PreparedStatement stmt=cn.prepareStatement(query);
            stmt.setInt(1,age);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                fitFreaks.add(new FitFreak
                (
                    rs.getString("fitnessId"),
                    rs.getString("name"),
                    rs.getString("gender"),
                    rs.getInt("age"),
                    rs.getDouble("height"),
                    rs.getDouble("weight")
                    ));
            }
            cn.close();
        }
        catch(Exception e){
            System.out.println("Error retrieving FitFreak records: "+e.getMessage());
        }
        return fitFreaks;
    }
    
}	 	  	  		    	  	      	      	 	
