package main.model;

public class HouseModel extends PropertyModel {
     String streetNumber;
     
     public HouseModel(int propertyID, String propertyType ,String streetName,String city, String province ,String country, String postalCode, String streetNumber, int rentAmount) {
    	 super(propertyID, propertyType,streetName, city, province,country, postalCode, rentAmount);
    	 this.streetNumber = streetNumber;
     }
     
     // getters for accessing data members of the class
     public String getStreetNumber() {
    	 return this.streetNumber;
     }
     
     // overriding toString()
     public String toString() {
    	 String leaseDate = (this.leaseStartDate!=null && this.leaseEndDate!=null)?("Lease Start Date: "+this.leaseStartDate+"\n"+"Lease End Date: "+this.leaseEndDate+"\n"):"";
    	 String result = "Property ID: "+this.propertyID+"\nProperty Type: "+this.propertyType+"\nProperty Vacancy Status: "+(this.isVacant?"Property is Vacant":"Property is not Vacant")+"\nHouse Street Number: "+this.streetNumber+"\nStreet: "+this.streetName+"\nCity: "+this.city+"\nCountry: "+this.country+"\nPostal Code: "+this.postalCode+"\nRent: "+this.rentAmount+" CAD\nRenovation needed for Property: "+(this.renovationNeeded==true?"Yes":"No")+"\n"+leaseDate;
    	 return result;
     }
}
