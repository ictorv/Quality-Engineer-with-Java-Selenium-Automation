public class Restaurant 
{
    //Include Attributes, Getters and Setters
    //Include five argument constructor
    //Include public double calculateTotalBill(double cost) method	
    private String customerName;
    private int orderNumber;
    private String deliveryType;
    private String foodName;
    private long phoneNumber;
    
    public String getCustomerName(){
        return this.customerName;
    }
    public void setCustomerName(String customerName){
        this.customerName=customerName;
    }
    
    public int getOrderNumber(){
        return this.orderNumber;
    }
    public void setOrderNumber(int orderNumber){
        this.orderNumber=orderNumber;
    }
    
    public String getDeliveryType(){
        return this.deliveryType;
    }
    public void setDeliveryType(String deliveryType){
        this.deliveryType=deliveryType;
    }
    
    public String getFoodName(){
        return this.foodName;
    }
    public void setFoodName(String foodName){
        this.foodName=foodName;
    }
    
    public long getPhoneNumber(){
        return this.phoneNumber;
    }
    public void setPhoneNumber(long phoneNumber){
        this.phoneNumber=phoneNumber;
    }
    
    public Restaurant(String customerName, int orderNumber, String deliveryType, String foodName, long  phoneNumber){
        this.customerName=customerName;
        this.orderNumber=orderNumber;
        this.deliveryType=deliveryType;
        this.foodName=foodName;
        this.phoneNumber=phoneNumber;
    }
    
    public double calculateTotalBill(double cost){
       double tax=0;
       if (deliveryType.equals("HomeDelivery")){
           tax=0.8;
       }
       else if(deliveryType.equals("Parcel")){
           tax=0.5;
       }
       else if(deliveryType.equals("NormalOrder")){
           tax=0.3;
       }
       return  (cost*tax)+cost;
    }
    
}
