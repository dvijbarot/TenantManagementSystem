package main.util.builder;

import main.model.CondoModel;

public class CondoModelBuilder {
	int propertyID, condoUnitNumberber, rentAmount;
	String condoStreetNumber, streetName,  city,  province,  country,  postalCode;
	
	public CondoModelBuilder setPropertyID(int propertyID) {
		this.propertyID = propertyID;
		return this;
	}
	
	public CondoModelBuilder setCondoUnitNumberber(int condoUnitNumberber) {
		this.condoUnitNumberber = condoUnitNumberber;
		return this;
	}
	
	public CondoModelBuilder setCondoStreetNumber(String condoStreetNumber) {
		this.condoStreetNumber = condoStreetNumber;
		return this;
	}
	
	public CondoModelBuilder setStreetName(String streetName) {
		this.streetName = streetName;
		return this;
	}
	
	public CondoModelBuilder setCity(String city) {
		this.city = city;
		return this;
	}
	
	public CondoModelBuilder setProvince(String province) {
		this.province = province;
		return this;
	}
	
	public CondoModelBuilder setCountry(String country) {
		this.country = country;
		return this;
	}
	
	public CondoModelBuilder setPostalCode(String postalCode) {
		this.postalCode = postalCode;
		return this;
	}
	
	
	public CondoModelBuilder setRentAmount(int rentAmount) {
		this.rentAmount = rentAmount;
		return this;
	}
	
	public CondoModel build() {
		return new CondoModel(propertyID, "Condo", streetName, city, province, country, postalCode,
				condoStreetNumber, condoUnitNumberber, rentAmount);
	}

	
}
