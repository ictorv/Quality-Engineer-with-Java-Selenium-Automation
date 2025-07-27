import java.util.Scanner;

public class UserInterface {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);

		System.out.println("Enter a sentence");
		String s=sc.nextLine();
        String[] arr=s.split(" ");
		for(int i=0;i<arr.length;i++){
		    if (!arr[i].matches("[a-zA-Z]+")){
		        System.out.println(s+" is an invalid sentence");
		        return;
		    }
		}
				    
		System.out.println("Enter a word");
		String w=sc.next();
		if (!w.matches("[a-zA-Z]+")){
		    System.out.println(w+" is an invalid word");
		    return;
		}
        
		for (int i=0;i<arr.length;i++){

		    if (arr[i].equals(w)){
		        String rev="";
		        for(int j=w.length()-1;j>=0;j--){
		            rev+=w.charAt(j);
		        }
		        arr[i]=rev;
		        s=String.join(" ",arr);
		        System.out.println(s);
		        return;
		    }
		    if (i==arr.length-1){
		        System.out.println(w+" is not in the sentence");
		        return;
		    }
		}
		
	}
}

