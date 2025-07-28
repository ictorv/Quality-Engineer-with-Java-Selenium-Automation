
public interface FitnessMonitor {

	//Fill the code here
	
	public abstract String findFitnessLevel();
	public static double calculateCaloriesBurnt(double weight, double distanceCovered, boolean runningStatus){
	    if (runningStatus==true){
	        return (0.75*weight*distanceCovered);
	    }
	    else{
	        return (0.53*weight*distanceCovered);	    
	        
	    } 
	}
	
}
