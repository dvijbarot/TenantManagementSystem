package main.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import main.model.TenantModel;

class TenantModelTest {

	static TenantModel tModel;
	
	@BeforeAll
	 public static void setUpData() {
		 tModel = new TenantModel(123,"Karthik","Durai","karthik@example.com");
	 }
	
	@Test
	public void testGetTenantName() {
		 assertEquals("Karthik Durai", tModel.getName());
	}

	@Test
	public void testGetTenant() {
		assertNull(tModel.getTenant(11));
	}
}

