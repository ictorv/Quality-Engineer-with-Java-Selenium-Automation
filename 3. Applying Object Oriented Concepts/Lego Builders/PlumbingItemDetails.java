
public class PlumbingItemDetails extends LegoBuilders  {
	
	// Fill the code
	private String productName;
	private int quantity;
	private double price;
	
	public void setProductName(String productName){
	    this.productName=productName;
	}
	public void setQuantity(int quantity){
	    this.quantity=quantity;
	}
	public void setPrice(double price){
	    this.price=price;
	}

    public String getProductName(){
	    return productName;
	}
	public int getQuantity(){
	    return quantity;
	}
	public double getPrice(){
	    return price;
	}
	public PlumbingItemDetails(String productId, String productType, String productName, int quantity, double price){
	    super(productId, productType);
	    this.productName=productName;
	    this.quantity=quantity;
	    this.price=price;
	}
	
	public double calculateTotalBill(){
	    if (productName.equals("Tube") || productName.equals("PlumbingPipe")){
	        double bill=quantity*price;
	        return bill;
	    }
	    return 0;
	}

}
