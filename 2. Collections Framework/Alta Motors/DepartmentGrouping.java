import java.util.*;
public class DepartmentGrouping {
   private HashSet<String>employeeSet=new HashSet<>();
   public HashSet<String>getEmployeeSet(){
       return employeeSet;
   }
   public void setEmployeeSet(HashSet<String> employeeSet){
       this.employeeSet=employeeSet;
   }
   public void addEmployeeId(String details){
       String d[]=details.split(":");
       if(d.length==2){
           String employeeId=d[0];
           if(employeeId.matches("[PQMS]\\d{3}$")){
               employeeSet.add(employeeId);
           }
       }
   }
   
   public HashSet<String>findDepartmentNameWithEmployeeId(){
       HashSet<String> r=new HashSet<>();
       for(String e:employeeSet){
           String t="";
           if(e.charAt(0)=='P'){
               t=e+":Product";
               r.add(t);
           }
            else if(e.charAt(0)=='Q'){
               t=e+":Quality Control";
               r.add(t);
           }
           else if(e.charAt(0)=='M'){
               t=e+":Maintenance";
               r.add(t);
           }
           else{
               t=e+":Sales";
               r.add(t);
           }
       }
       return r;
   }
    
}    