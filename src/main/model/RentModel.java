package main.model;

import java.util.ArrayList;
import java.util.HashMap;

public class RentModel {
    private static ArrayList<RentPaymentModel> rentPayments = new ArrayList<RentPaymentModel>();

    public RentModel() {
 
    }

    public void addRentPayment(RentPaymentModel rentPayment) {
        rentPayments.add(rentPayment);
        PropertyModel pModel = new PropertyModel();
        pModel.getProperty(rentPayment.getPropertyID()).rentPaid=true;
        
    }

    public ArrayList<RentPaymentModel> getRentPayments() {
        return rentPayments;
    }

    
    
    public String checkRentStatus(int propertyID) {
    	
    	 String res="";
    	 PropertyModel pModel = new PropertyModel();
    	 if(pModel.getProperty(propertyID).rentPaid==true) {
    		 res = "Rent for the property ID "+propertyID+" is paid by tenant of ID "+ pModel.getProperty(propertyID).getTenantModel().tenantId;
    	 }
    	 else {
    		 res = "Rent for the property ID "+propertyID+" is not paid by tenant of ID "+ pModel.getProperty(propertyID).getTenantModel().tenantId;
    	 }
//    	for(int i=0;i<rentPayments.size();i++) {
//    		RentPaymentModel rModel = rentPayments.get(i);
//    		if(rModel.getPropertyID() == propertyID) {
//    			
//    			if(rModel.isPaid()==true) {
//    				res = "Rent for the property ID "+propertyID+" is paid by tenant of ID "+rModel.getTenantID();
//    			}
//    			else {
//    				res = "Rent for the property ID "+propertyID+" is not paid by tenant of ID "+rModel.getTenantID();
//    			}
//    			break;
//    		}
//    	}
    	
    	return res;
    }
}