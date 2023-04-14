package main.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import main.model.CondoModel;

class CondoModelTest {
    static CondoModel cModel;
    
    @BeforeAll
	 public static void setUpData() {
		 cModel = new CondoModel(13, "Condo", "Guy Street","Montreal", "Quebec","Canada","H3V 1G8","32-9A",12,2000);
	 }
    
    @Test
    public void testGetStreetNumber() {
    	assertEquals("32-9A", cModel.getStreetNumber());
    }
    
    @Test
    public void testGetUnitNumberTest() {
    	assertEquals(12,cModel.getUnitNumber());
    }

}
