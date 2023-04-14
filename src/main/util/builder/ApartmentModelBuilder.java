package main.util.builder;

import main.model.ApartmentModel;

public class ApartmentModelBuilder {
	int propertyID, aptNumber, rentAmount,aptBedroomCount,aptBathroomCount;
	String aptCivicAddress, streetName,city, province, country, postalCode;
	double aptSquareFootage;
	
	public ApartmentModelBuilder setPropertyID(int propertyID) {
		this.propertyID = propertyID;
		return this;
	}
	
	public ApartmentModelBuilder setaptNumber(int aptNumber) {
		this.aptNumber = aptNumber;
		return this;
	}
	
	public ApartmentModelBuilder setAptCivicAddress(String aptCivicAddress) {
		this.aptCivicAddress = aptCivicAddress;
		return this;
	}
	
	public ApartmentModelBuilder setStreetName(String streetName) {
		this.streetName = streetName;
		return this;
	}
	
	public ApartmentModelBuilder setCity(String city) {
		this.city = city;
		return this;
	}
	
	public ApartmentModelBuilder setProvince(String province) {
		this.province = province;
		return this;
	}
	
	public ApartmentModelBuilder setCountry(String country) {
		this.country = country;
		return this;
	}
	
	public ApartmentModelBuilder setPostalCode(String postalCode) {
		this.postalCode = postalCode;
		return this;
	}
	
	public ApartmentModelBuilder setAptBedroomCount(int aptBedroomCount) {
		this.aptBedroomCount = aptBedroomCount;
		return this;
	}
	
	public ApartmentModelBuilder setAptBathroomCount(int aptBathroomCount) {
		this.aptBathroomCount = aptBathroomCount;
		return this;
	}
	
	public ApartmentModelBuilder setRentAmount(int rentAmount) {
		this.rentAmount = rentAmount;
		return this;
	}
	
	public ApartmentModelBuilder setAptSquareFootage(double aptSquareFootage) {
		this.aptSquareFootage = aptSquareFootage;
		return this;
	}
	
	public ApartmentModel build() {
		return new ApartmentModel(propertyID, "Apartment", streetName, city, province, country,
				postalCode, aptCivicAddress, aptNumber, aptBedroomCount, aptBathroomCount, aptSquareFootage,
				rentAmount); 
	}
	
}
