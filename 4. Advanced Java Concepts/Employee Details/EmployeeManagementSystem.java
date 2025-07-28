import java.util.*;
import java.sql.*;
public class EmployeeManagementSystem {
   //Fill the code here
   public ArrayList<Employee> viewEmployeesByDepartmentAndExperience(String department,int experience){
       Connection cn=null;
       PreparedStatement psmt=null;
       
       ResultSet rs=null;
       ArrayList<Employee> employees=new ArrayList<>();
       
       try{
           cn=DB.getConnection();
           String query="SELECT * FROM EMPLOYEE WHERE DEPARTMENT=? AND EXPERIENCE>= ?";
           psmt=cn.prepareStatement(query);
           psmt.setString(1, department);
           psmt.setInt(2, experience);
           rs=psmt.executeQuery();
           
            while(rs.next()){
               int eid=rs.getInt("employeeId");
               String ename=rs.getString("employeeName");
               String dept=rs.getString("department");
               double salary=rs.getDouble("salary");
               int exp=rs.getInt("experience");
               

               Employee employee =new Employee(eid, ename, dept, salary, exp);
             
               employees.add(employee);
            }
           }catch(Exception e){
               System.out.println("database error: "+e.getMessage());
           }finally{
               try{ if(rs!=null) rs.close();} catch(SQLException e){}
               try{ if(psmt!=null) psmt.close();} catch(SQLException e){}
               try{ if(cn!=null) cn.close();} catch(SQLException e){}
           }
           return employees;
       }
   
}