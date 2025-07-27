public class Investment
{
	
	//Include attributes
	private int companyId;
	private String companyName;
	private static int noOfShares;
	private double valueOfOneShare;

	//Include getters and setters
    public int getComapanyId(){
        return this.companyId;
    }
    public void setComapanyId(int companyId){
        this.companyId=companyId;
    }
    
    public String getComapanyName(){
        return this.companyName;
    }
    public void setComapanyName(String companyName){
        this.companyName=companyName;
    }
    
    public int getNoOfShares(){
        return this.noOfShares;
    }
    public void setNoOfShares(int shares){
        if (shares>0){
            this.noOfShares=shares;    
        }
    }
    
    public double getValueOfOneShare(){
        return this.valueOfOneShare;
    }
    public setValueOfOneShare(double valueOfOneShare){
        this.valueOfOneShare=valueOfOneShare;
    }

	public double calculateTotalAmountOfInvestment(int sharesBought)
    {
        //Fill the code here
        return 0;
    }
}