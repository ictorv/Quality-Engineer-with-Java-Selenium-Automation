import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class AnimalUtil {

	public List<Animal> retriveDistinctAnimals(Stream<Animal> animalStream){
    	//Fill the code here 
    	List<Animal>disAni =animalStream.distinct().collect(Collectors.toList());
    // 	return animalStream.distinct().collect(Collectors.toList());
        return disAni;
	}
	public long countOfTotalDistinctAnimals(Stream<Animal> animalStream) {
		//Fill the code here 
    	
    // 	return animalStream.distinct().count();
        long count= animalStream.distinct().count();
	    return count;
	}
}
