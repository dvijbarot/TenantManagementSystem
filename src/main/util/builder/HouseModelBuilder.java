package main.util.builder;

import main.model.HouseModel;

public class HouseModelBuilder {
	int propertyID,rentAmount;
	String houseStreetNumber,streetName, city, province,  country,  postalCode;
	
	public HouseModelBuilder setPropertyID(int propertyID) {
		this.propertyID = propertyID;
		return this;
	}
	
	public HouseModelBuilder setHouseStreetNumber(String houseStreetNumber) {
		this.houseStreetNumber = houseStreetNumber;
		return this;
	}
	
	
	public HouseModelBuilder setStreetName(String streetName) {
		this.streetName = streetName;
		return this;
	}
	
	public HouseModelBuilder setCity(String city) {
		this.city = city;
		return this;
	}
	
	public HouseModelBuilder setProvince(String province) {
		this.province = province;
		return this;
	}
	
	public HouseModelBuilder setCountry(String country) {
		this.country = country;
		return this;
	}
	
	public HouseModelBuilder setPostalCode(String postalCode) {
		this.postalCode = postalCode;
		return this;
	}
	
	
	public HouseModelBuilder setRentAmount(int rentAmount) {
		this.rentAmount = rentAmount;
		return this;
	}
	
	public HouseModel  build() {
		return new HouseModel(propertyID, "House", streetName, city, province, country, postalCode,
				houseStreetNumber, rentAmount);
	}
	
	
}
