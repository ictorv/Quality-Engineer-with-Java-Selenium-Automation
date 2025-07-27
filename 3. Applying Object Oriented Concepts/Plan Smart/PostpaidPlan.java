
import java.util.HashMap;
import java.util.Map;
public class PostpaidPlan extends PhonePlan {
	private String dataCarryOver;
	private String subscription;

	//Include seven-argument constructor

	//Fill the code here



	public String getDataCarryOver() {
		return dataCarryOver;
	}

	public void setDataCarryOver(String dataCarryOver) {
		this.dataCarryOver = dataCarryOver;
	}

	public String getSubscription() {
		return subscription;
	}

	public void setSubscription(String subscription) {
		this.subscription = subscription;
	}

    public PostpaidPlan(String providerName, String planName, String talkTime, int data, int smsPerDay,String planType,String dataCarryOver){
        super( providerName, planName, talkTime, data, smsPerDay,planType);
        this.dataCarryOver=dataCarryOver;
    }

	public double findPlanAmount()
	{
		double total=0;
		
// 		return total;

        if (!providerName.equalsIgnoreCase("airtel") && !providerName.equalsIgnoreCase("jio")){
            return -1;
        }
        if(!planName.equalsIgnoreCase("regular-postpay") && !planName.equalsIgnoreCase("family plan for 2") && !planName.equalsIgnoreCase("family plan for 5") && !planName.equalsIgnoreCase("family plan for 8")){
            return -1;
        }
        if(providerName.equalsIgnoreCase("airtel")){
            if(planName.equalsIgnoreCase("Regular-PostPay")){
                subscription="Wynk Music";
                total=399;
            }
            else if(planName.equalsIgnoreCase("Family Plan For 2")){
                subscription="Wynk Music,Prime";
                total=599;
            }
            else if(planName.equalsIgnoreCase("Family Plan for 5")){
                subscription="Wynk Music,Prime,Extra 5GB data";
                total=999;
            }
            else if(planName.equalsIgnoreCase("Family Plan for 8")){
                subscription="Wynk Music,Prime,Netflix,Extra 10GB data";
                total=1599;
            }
            else{
                return -1;
            }
        }
        else{
            if(planName.equalsIgnoreCase("Regular-PostPay")){
                subscription="Jio Saavn";
                total=500;
            }
            else if(planName.equalsIgnoreCase("Family Plan For 2")){
                subscription="Jio Saavn,Amazon Prime,Extra 3GB data";
                total=720;
            }
            else if(planName.equalsIgnoreCase("Family Plan For 5")){
                subscription="Jio Saavn,Amazon Prime,Jio Apps,Extra 5GB data";
                total=980;
            }
            else if(planName.equalsIgnoreCase("Family Plan For 8")){
                subscription="Jio Saavn,Amazon Prime,Netflix,Jio Apps,Extra 10GB data";
                total=1650;
            }
            else{
                return -1;
            }
            
        }
        return total;
	}


}
