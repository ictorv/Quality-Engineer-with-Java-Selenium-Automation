import java.util.Scanner;
public class UserInterface {

	public static String sumOfArrays(String data) {
	
		//Fill the code here
		
// 		return "";
        try{
            String[] s1=data.split(":");
            int arraySize=Integer.parseInt(s1[0]);
            if (arraySize<0){
                throw new NegativeArraySizeException("Provide a positive Integer to allocate memory for the array");
            }
            if(s1.length-1!=arraySize){
                throw new ArrayIndexOutOfBoundsException("Error: Array index is out of range");
            }
            int[] array =new int[arraySize];
            int sum=0;
                for (int i=1;i<=arraySize;i++){
                    array[i-1]=Integer.parseInt(s1[i]);
                    sum+=array[i-1];
                }
                return("Sum of the array elements is "+sum);
            
        }
        
        catch(NumberFormatException e){
            throw new NumberFormatException("Provide a valid Integer");
        }
        catch (ArrayIndexOutOfBoundsException e){
            throw new ArrayIndexOutOfBoundsException("Error: Array index is out of range");
        }
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
            System.out.println("Enter the data");
            String line=sc.next();
            try{
                String result=sumOfArrays(line);
                System.out.println(result);
            }
            catch(NegativeArraySizeException e){
                System.out.println(e.getMessage());
            }
            catch(NumberFormatException e){
                System.out.println(e.getMessage());
            }
            catch(ArrayIndexOutOfBoundsException e){
                System.out.println(e.getMessage());
            }
		}
	}
