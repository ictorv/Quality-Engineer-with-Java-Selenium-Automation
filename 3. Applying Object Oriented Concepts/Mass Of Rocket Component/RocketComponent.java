public class RocketComponent {
   
    // Include all the attributes of the RocketComponent class
    String name;
    double weight;
    final double accelerationDueToGravity=9.8;
    String material;
    double costOfProduction;
    
    //Provide setters and getter methods
    public void setName(String name){
        this.name=name;
    }
    
    public void setWeight(double weight){
        this.weight=weight;
    }
    
    // public void setAccelerationDueToGravity(double acc){
    //     this.accelerationDueToGravity=acc;
    // } 
    public void setMaterial(String material){
        this.material=material;
    }
    public void setCostOfProduction(double costOfProduction){
        this.costOfProduction=costOfProduction;
    }
    
   
    String getName(){
        return name;
    }
    double getWeight(){
        return weight;
    }
    double getAccelerationDueToGravity(){
        return accelerationDueToGravity;
    } 
    String getMaterial(){
        return material;
    }
    double getCostOfProduction(){
        return costOfProduction;
    }
    
    //Include parameterised constructor
    public RocketComponent(String name, double weight,String material, double costOfProduction){
        
        this.name=name;
        this.weight=weight;
        this.material=material;
        this.costOfProduction=costOfProduction;
    }
    
    public double calculateMassOfRocketComponent(double weight){
        double mass=weight/accelerationDueToGravity;
        if (mass<=0){
            return -1;
        }
        return Math.round(mass*100.0)/100.0;
    }
}    