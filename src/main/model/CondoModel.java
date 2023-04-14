package main.model;

public class CondoModel extends PropertyModel{
     String streetNumber;
     int unitNumber;
     public CondoModel(int propertyID, String propertyType ,String streetName,String city, String province ,String country, String postalCode, String streetNumber, int unitNumber, int rentAmount) {
    	 super(propertyID, propertyType ,streetName, city,province,country, postalCode, rentAmount);
    	 this.streetNumber = streetNumber;
    	 this.unitNumber = unitNumber;
     }
     
     // getters for accessing data members
     public String getStreetNumber() {
    	 return this.streetNumber;
     }
     
     public int getUnitNumber() {
    	 return this.unitNumber;
     }
     
     // Overriding toString
     public String toString() {
    	 String leaseDate = (this.leaseStartDate!=null && this.leaseEndDate!=null)?("Lease Start Date: "+this.leaseStartDate+"\n"+"Lease End Date: "+this.leaseEndDate+"\n"):"";
    	 String result = "Property ID: "+this.propertyID+"\nProperty Type: "+this.propertyType+"\nProperty Vacancy Status: "+(this.isVacant?"Property is Vacant":"Property is not Vacant")+"\nCondo Unit Number: "+this.unitNumber+"\nCondo Street Number: "+this.streetNumber+"\nStreet: "+this.streetName+"\nCity: "+this.city+"\nProvince: "+this.province+"\nCountry: "+this.country+"\nPostal Code: "+this.postalCode+"\nRent: "+this.rentAmount+" CAD\nRenovation needed for the Property: "+(this.renovationNeeded==true?"Yes":"No")+"\n"+leaseDate;
    	 return result;
     }
     
}
