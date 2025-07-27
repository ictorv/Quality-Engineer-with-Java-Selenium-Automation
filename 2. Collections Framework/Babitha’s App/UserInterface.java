import java.util.Scanner;
import java.util.regex.*;
import java.util.*;

public class UserInterface {
	public static void main(String args[]){
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the paragraph typed");
		String paragraph=sc.nextLine();
		
		Map<String , Integer> wordCounts=countWords(paragraph);
		int totalWords = wordCounts.values().stream().mapToInt(Integer::intValue).sum();
		System.out.println("Total number of words "+totalWords);
		System.out.println("Words with the count");
		
		wordCounts.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(entry -> System.out.println(entry.getKey() + " - " + entry.getValue()));
		//Fill the code here
	}
	
	private static Map<String,Integer> countWords(String paragraph){
	    String[] words=paragraph.split("[^a-zA-Z']+");
	    Map<String, Integer>wordCountMap=new HashMap<>();
	    for (String word:words){
	        if (!word.isEmpty()){
	            String cleanedWord=word.replaceAll("[^a-zA-Z']","").toLowerCase();
	            if(!cleanedWord.isEmpty()){
	                wordCountMap.put(cleanedWord,wordCountMap.getOrDefault(cleanedWord,0)+1);
	            }
	        }
	    }
	    return wordCountMap;
	}
}