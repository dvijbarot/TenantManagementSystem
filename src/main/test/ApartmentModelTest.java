package main.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import main.model.ApartmentModel;
import main.model.PropertyModel;

class ApartmentModelTest {
	 static ApartmentModel aModel;
	 
	 @BeforeAll
	 public static void setUpData() {
		 aModel = new ApartmentModel(12, "Apartment", "Guy Street","Montreal", "Quebec","Canada","H3V 1G8","4870 Rockhill",804,2,3,1800.2,2000);
	 }
	 
	@Test
	void getCivicAddressTest() {
		assertEquals(12, aModel.getPropertyID());
	}
	
	@Test
	void getApartmentNumberTest() {
		assertEquals(804, aModel.getApartmentNumber());
	}

}
