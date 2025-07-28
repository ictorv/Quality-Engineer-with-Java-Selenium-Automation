public class StringInstrument extends MusicalInstrument{
 
    protected int numberOfStrings;
    protected String stringMaterial;
 
    //Uncomment the getters and setters after writing the attributes
    
    public int getNumberOfStrings() {
		return numberOfStrings;
	}
	public void setNumberOfStrings(int numberOfStrings) {
		this.numberOfStrings = numberOfStrings;
	}
	public String getStringMaterial() {
		return stringMaterial;
	}
	public void setStringMaterial(String stringMaterial) {
		this.stringMaterial = stringMaterial;
	}
	public StringInstrument(int instrumentId, String instrumentName, String instrumentType, double price, Customer customerObj, int numberOfStrings, String stringMaterial){
	    super(instrumentId, instrumentName, instrumentType, price, customerObj);
	    this.numberOfStrings=numberOfStrings;
	    this.stringMaterial=stringMaterial;
	}
	
	public double calculateTotalBill(){
	    double pps;
	    double total;
	    if (stringMaterial.equals("Nylon")){
	        pps=15;
	    }
	    else if(stringMaterial.equals("Steel")){
	        pps=20;
	    }
	    else{
	        return -1;
	    }
	    total= price +(pps*numberOfStrings);
	    if (price>20000){
	        total=total-(total*0.30);
	        price=total;
	        return total;
	    }
	    price=total;
	    return total;
	}
}