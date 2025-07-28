import java.util.Scanner;

public class UserInterface {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		//Fill the code here
		System.out.println("Enter the network provider name:");
		String providerName=sc.nextLine();
		
		System.out.println("Enter the plan name:");
	    String planName=sc.nextLine();

        System.out.println("Enter the talk time plan:");
	    String talkTime=sc.nextLine();
	    
	    System.out.println("Enter the data plan(in GB):");
	    int data=sc.nextInt();
	    
	    System.out.println("Enter the SMS plan(per/day):");
        int smsPerDay=sc.nextInt();
        String t=sc.nextLine();
        
        System.out.println("Enter the plan type:");
	    String planType=sc.nextLine();
	    
	    
	   // double extraData;
	    if (!planType.equalsIgnoreCase("prepaidPlan") && !planType.equalsIgnoreCase("postpaidPlan")){
	        System.out.println("Please provide a valid plan details");
	        return;
	    }
	    
	    PhonePlan obj;
	    if (planType.equalsIgnoreCase("prepaidPlan")){
	        System.out.println("Enter the validity(in days):");
	        int validity = sc.nextInt();
	        System.out.println("Enter the extra data provided(in GB):");
	        double extraData = sc.nextDouble();
	        obj=new PrepaidPlan(providerName,  planName,  talkTime,  data,  smsPerDay, planType, validity, extraData);
	    }
	    else{
	        System.out.println("Do you want to roll over unused data");
	        String rollOver=sc.nextLine();
	        obj=new PostpaidPlan(providerName,  planName,  talkTime,  data,  smsPerDay,planType,rollOver);
	    }
	    
	    double amt=obj.findPlanAmount();
	    if(amt==-1){
	        if(obj.getPlanType().equalsIgnoreCase("prepaidplan"))
	            System.out.println("Invalid prepaid plan Details");
	        else
	            System.out.println("Invalid postpaid plan Details");
	        return;
	    }

	    
	    System.out.println("Provider Name - "+obj.getProviderName());
	    System.out.println("Plan Name - "+obj.getPlanName());
	    System.out.println("Talk Time - "+obj.getTalkTime());
	    System.out.println("Data for the plan (per day) - "+obj.getData()+" GB" );
	    System.out.println("SMS for the plan - "+obj.getSmsPerDay()+" SMS per day" );
	    System.out.println("Plan type - "+obj.getPlanType());
	    if(obj instanceof PrepaidPlan){
	        PrepaidPlan objPre=(PrepaidPlan)obj;
	        System.out.println("Validity for the plan - "+objPre.getValidity() +" days");
	        System.out.println("Extra data for the plan - "+objPre.getExtraData()+" GB");
	    }
	    else{
	        PostpaidPlan objPos=(PostpaidPlan)obj;
	        System.out.println("want to roll over unused data - "+objPos.getDataCarryOver());
	        System.out.println("Subscription for the plan - "+objPos.getSubscription());
	    }
	    
	    
	    System.out.printf("Plan Amount to be paid for "+obj.getPlanName()+"plan is Rs "+amt);
	    
	}
}
