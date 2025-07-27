package codes;

import java.util.HashMap;
import java.util.Map;

public class CapitalService {

	public String greeting(String name)
	{
		return "Welcome "+name;
	}
	
	public String getCapital(String country)
	{
		Map<String, String> hashMap = new HashMap<>();
		hashMap.put("India", "New Delhi");
        hashMap.put("Singapore", "Singapore");
        hashMap.put("United Kingdom", "London");
        String capital = hashMap.get(country);
        if(capital!=null) return capital;
        else
        	return "Country not found in database";
	}
	public static void main (String[] args)
	{
		System.out.println("Welcome to capital service");
		CapitalService cs=new CapitalService();
		System.out.println(cs.getCapital("India"));
	}
}