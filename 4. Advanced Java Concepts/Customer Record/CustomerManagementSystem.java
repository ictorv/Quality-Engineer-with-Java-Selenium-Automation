import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerManagementSystem {
public int insertCustomerRecordsToDB(Customer sObj) throws SQLException, ClassNotFoundException{
    		
		int res=0;
		Connection cn=null;
		PreparedStatement ps=null;
		CustomerUtility utility=new CustomerUtility();
		if(!utility.validateCustomerEmail(sObj.getEmail()) || !utility.validatePhoneNo(sObj.getPhoneNo())){
		    return 0;
		}
		try{
		    cn=DB.getConnection();
		    String query="INSERT INTO CUSTOMER(ID,NAME,EMAIL,PHONENO) VALUES(?,?,?,?)";
		    ps=cn.prepareStatement(query);
		    ps.setString(1,sObj.getId());
		    ps.setString(2,sObj.getName());
		    ps.setString(3,sObj.getEmail());
		    ps.setString(4,sObj.getPhoneNo());
		    res=ps.executeUpdate();
		}catch(Exception e){
		    e.printStackTrace();
		}finally{
		    try{
		        if(ps!=null)ps.close();
		        if(cn!=null)cn.close();
		        
		    }catch(SQLException e){
		        e.printStackTrace();
		    }
		}
		return res>0?1:0;
	}
}