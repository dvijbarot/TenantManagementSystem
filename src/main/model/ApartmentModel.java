package main.model;

public class ApartmentModel extends PropertyModel {
     String civicAddress;
     int apartmentNumber, numberOfBedrooms, numberOfBathrooms;
     double squareFootage;
     
     public ApartmentModel(int propertyID, String propertyType ,String streetName,String city, String province ,String country, String postalCode, String civicAddress, int apartmentNumber, int numberOfBedrooms, int numberOfBathrooms, double squareFootage, int rentAmount) {
    	 super(propertyID, propertyType,streetName, city, province ,country,  postalCode, rentAmount);
    	 this.civicAddress = civicAddress;
    	 this.apartmentNumber = apartmentNumber;
    	 this.numberOfBedrooms = numberOfBedrooms;
    	 this.numberOfBathrooms = numberOfBathrooms;
    	 this.squareFootage = squareFootage;
     }
     
     // Getters for accessing data members
     public String getCivicAddress() {
    	 return this.civicAddress;
     }
     
     public int getApartmentNumber() {
    	 return this.apartmentNumber;
     }
     
     public int getNumberOfBedRooms() {
    	 return this.numberOfBedrooms;
     }
     
     public int getNumberOfBathrooms() {
    	 return this.numberOfBathrooms;
     }
     
     public double getSquareFootage() {
    	 return this.squareFootage;
     }
     
     // overiding toString()
     public String toString() {
    	 String leaseDate = (this.leaseStartDate!=null && this.leaseEndDate!=null)?("Lease Start Date: "+this.leaseStartDate+"\n"+"Lease End Date: "+this.leaseEndDate+"\n"):"";
    	 String result = "Property ID: "+this.propertyID+"\nProperty Type: "+this.propertyType+"\nProperty Vacancy Status: "+(this.isVacant?"Property is Vacant":"Property is not Vacant")+"\nApartment Number: "+this.apartmentNumber+"\nApartment Civic Address: "+this.civicAddress+"\nStreet: "+this.streetName+"\nCity: "+this.city+"\nProvince: "+this.province+"\nCountry: "+this.country+"\nPostal Code: "+this.postalCode+"\nNumber of Bedrooms: "+this.numberOfBedrooms+"\nNumber of Bathrooms: "+this.numberOfBathrooms+"\nSize of Apartment: "+this.squareFootage+" sq ft\nRent: "+this.rentAmount+" CAD\nRenovation needed for the Property: "+(this.renovationNeeded==true?"Yes":"No")+"\n"+leaseDate;
    	 return result;
     }
}
