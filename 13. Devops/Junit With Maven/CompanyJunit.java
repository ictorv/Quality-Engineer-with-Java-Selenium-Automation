import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;

public class CompanyJunit {
	
	CompanyBO companyBO;
	
	@Before
	public void init(){
		companyBO = new CompanyBO();
	}
	
	@Test
	public void testValidDiscount() {
		Assert.assertEquals("Datex shipping offers discount",companyBO.hasDiscount(130, 300));
		Assert.assertEquals("Datex shipping offers discount",companyBO.hasDiscount(99, 1000));
		Assert.assertEquals("Datex shipping offers discount",companyBO.hasDiscount(160, 499));
	}
	
	@Test
	public void testInvalidDiscount() {
		Assert.assertEquals("Datex shipping offers no discount",companyBO.hasDiscount(121, 600));
		Assert.assertEquals("Datex shipping offers no discount",companyBO.hasDiscount(140, 700));
		Assert.assertEquals("Datex shipping offers no discount",companyBO.hasDiscount(100,500));
	}
}
