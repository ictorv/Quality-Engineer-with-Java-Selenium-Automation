
public class PrepaidPlan extends PhonePlan {
	private int validity;
	private double extraData;
	
	//Include eight-argument constructor

	//Fill the code here


	public int getValidity() {
		return validity;
	}

	public void setValidity(int validity) {
		this.validity = validity;
	}

	public double getExtraData() {
		return extraData;
	}

	public void setExtraData(double extraData) {
		this.extraData = extraData;
	}

	public PrepaidPlan(String providerName, String planName, String talkTime, int data, int smsPerDay,String planType, int validity, double extraData){
	    super( providerName, planName, talkTime, data, smsPerDay,planType);
	    this.validity=validity;
	    this.extraData=extraData;
	}
	
	public double findPlanAmount()
	{
	   double total=0;
        if (!providerName.equalsIgnoreCase("airtel") && !providerName.equalsIgnoreCase("jio")){
            return -1;
        }
        if(data!=2 && data !=3){
            return -1;
        }
        if(validity!=28 && validity!=56 && validity!=84){
            return -1;
        }
        if (providerName.equalsIgnoreCase("airtel")){
            if(data==2){
                if(validity==28){
                    total=299;
                }
                else if(validity==56){
                    total=549;
                }
                else{
                    total=839;
                }
            }
            else
            {
                if(validity==28){
                    total=399;
                }
                else if(validity==56){
                    total=649;
                }
                else{
                    total=939;
                }
            }
        }
        else{
            if(data==2){
                if(validity==28){
                    total=389;
                }
                else if(validity==56){
                    total=620;
                }
                else{
                    total=939;
                }
            }
            else{
                if(validity==28){
                    total=400;
                }
                else if(validity==56){
                    total=749;
                }
                else{
                    total=839;
                }
            }
        }
            
        
        return total;
        

	}
	

	
}
