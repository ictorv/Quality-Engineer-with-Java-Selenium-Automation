
public class CompanyBO {
	public String hasDiscount(Integer weight,Integer distance) {
		if(weight < 100||distance < 500)
            return "Datex shipping offers discount";
        else
            return "Datex shipping offers no discount";
	}
}
