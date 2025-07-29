package userDefinedLibraries;

public class FlipkartValidation {
		
		//to convert String to int type 
		public int convertStringToInt(String str) {
			String tempString=str.replaceAll("[^0-9]", "");
			if(tempString.equals("")) {
				return 0;
			}
			 return Integer.parseInt(tempString);
		}
		
		public int validateTotal(String total,String discount,String coupon,String deliveryCharge,String protectedFee,String handlingFee,String BuyMoreSaveMore) {
			if(deliveryCharge.contains("Free")) {
				deliveryCharge="0";
			}
			int result= convertStringToInt(total)-convertStringToInt(discount)-convertStringToInt(BuyMoreSaveMore)-convertStringToInt(coupon)+convertStringToInt(deliveryCharge)+convertStringToInt(handlingFee)+convertStringToInt(protectedFee);
			return result;	
		}
	
}
