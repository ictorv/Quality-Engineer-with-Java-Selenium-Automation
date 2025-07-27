import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
public class UserInterface {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        List<Animal>animal=new ArrayList<Animal>();
	    System.out.println("Enter the Number of animals");
	    int number=sc.nextInt();
	    sc.nextLine();
	    System.out.println("Enter the animal details");
	    
	    while(number!=0){
	        number--;
	        String []ani=sc.nextLine().split(":");
	        animal.add(new Animal(ani[0],ani[1],ani[2]));
	        
	    }
	    
	    for (int i=0;i<numberOfAnimals ;i++ ){
	        String[]details=sc.nextLine().split(":");
	        animals.add(new Animal(details[0],details[1],details[2]));
	        
	        AnimalUtil animalUtil=new AnimalUtil();
	        Stream<Animal>animalStream=animals.stream();
	       // Stream<Animal>animalStreamForCount=animals.stream();
	        
	        List<Animal> distinctAnimals=animalUtil.retriveDistinctAnimals(animalStream);
	        long count=animalUtil.countOfTotalDistinctAnimals(animalStreamForCount);
	        
	        System.out.println("Distinct Animal Details");
	        distinctAnimals.forEach(animal->System.out.println(animal.getAnimalName()+":"+animal.getIsDomesticOrWild()+":"+animal.getHabitat()));
	        System.out.println("Total Count of Distinct Animals: "+count);
	    } 
		
	}

}
