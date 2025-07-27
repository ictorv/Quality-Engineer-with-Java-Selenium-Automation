
public class LegoBuilders {
	protected String productId;
	protected String productType;
	
	// Include the two argument constructor here
	

	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	
	public LegoBuilders(String productId, String productType){
	    this.productId=productId;
	    this.productType=productType;
	}
   
	

}
