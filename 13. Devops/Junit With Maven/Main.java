  import java.util.Scanner;
  public class Main{
    public static void main(String args[]){
        Scanner s=new Scanner(System.in);
        System.out.println("Enter the weight");
        int wt=s.nextInt();
        System.out.println("Enter the distance");
        int dis=s.nextInt();
        System.out.println(new CompanyBO().hasDiscount(wt,dis));
        s.close();
    } 
}