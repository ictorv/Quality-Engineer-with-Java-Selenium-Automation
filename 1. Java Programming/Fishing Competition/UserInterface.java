import java.util.Scanner;

public class UserInterface{
    
    public static void main(String[] args){
        
       Scanner sc=new Scanner(System.in);
       
        System.out.println("Enter the details");
        String inp=sc.next();
        String[] arr=inp.split(":");
        
        if(Integer.parseInt(arr[1])<18){
            System.out.println(arr[1]+" is an invalid age");
            return;
        }
        int a=Integer.parseInt(arr[2]),b=Integer.parseInt(arr[3]),c=Integer.parseInt(arr[4]);
        if (a<0){
            System.out.println(a+" is an invalid input");
            return;
        }
        else if(b<0){
            System.out.println(b+" is an invalid input");
            return;
        }
        else if(c<0){
            System.out.println(c+" is an invalid input");
            return;
        }
        else{
            System.out.println(arr[0]+" scored "+(a*10 + b*6 + c*3)+" points");
        }
    }
}