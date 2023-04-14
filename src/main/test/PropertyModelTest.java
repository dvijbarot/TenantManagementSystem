package main.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import main.model.ApartmentModel;
import main.model.PropertyModel;

class PropertyModelTest {
    static PropertyModel pModel = new PropertyModel(); 
    public static LinkedHashMap<Integer, PropertyModel> propertyList = new LinkedHashMap<Integer, PropertyModel>();
    
    @BeforeAll
	 public static void setUpData() {
		 pModel = new PropertyModel(12, "Apartment", "Guy Street","Montreal", "Quebec","Canada","H3V 1G8",2000);
		 propertyList.put(12, pModel);
	 }
   
	@Test
	public void testRentProperty() {
		assertEquals("There is no property of ID: 12", pModel.rentProperty(122, 12, 1, "13/03/2023", "13/03/2024"));
	}
	
	@Test
	public void testDisplayVacantUnits() {
		assertEquals("There is no Vacant Units", pModel.displayVacantUnits());
	}
	
	
	@Test
	public void testPropertyIDTest() {
		assertEquals(12, pModel.getPropertyID());
	}
	
	@Test
	public void getCityTest() {
		assertEquals("Montreal",pModel.getCity());
	}
	

}
