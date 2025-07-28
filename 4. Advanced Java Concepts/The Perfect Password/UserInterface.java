import java.util.Scanner;

public class UserInterface {
    public static WordSummarizer calculateAsciiSum(){
        return (word)->{
            // String [] charr=word.split("");
            int n=word.length();
            int[] arr=new int [n];
            for(int i=0;i<n;i++){
                arr[i]=(int)word.charAt(i);
            }
            int s=0;
            for(int val:arr){
                s+=val;
            }
            if (s%2==0){
                return word+" is a perfect word";
            }
            else{
                 return word+" is not a perfect word";
            }
            
        };
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //Fill the code here
        System.out.println("Enter the word to find the sum:");
        String word=sc.next();
        
        if (!word.matches("[a-zA-Z]+")){
            System.out.println(word+" is a invalid word");
            return;
        }
        else{
             WordSummarizer ws=calculateAsciiSum();
             String res=ws.summarize(word);
             System.out.println(res);
        }

    }
}
