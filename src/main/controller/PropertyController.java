package main.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import main.model.ApartmentModel;
import main.model.CondoModel;
import main.model.HouseModel;
import main.model.PropertyModel;
import main.model.TenantModel;
import main.view.PropertyView;

public class PropertyController implements Runnable {
	
    PropertyModel pModel;
    PropertyView pView;
    String methodName;
    
    public PropertyController(PropertyModel pModel, PropertyView pView,String methodName) {
    	this.pModel = pModel;
    	this.pView = pView;
    	this.methodName = methodName;
    }
    public PropertyController(PropertyModel pModel, PropertyView pView) {
    	this.pModel = pModel;
    	this.pView = pView;
    }
    
//    // PropertyModel Methods
//    public String getPropertyList() {
//   	    return pModel.getPropertyList();   
//    }
    
    public PropertyModel getPropertyController(int propertyID) {
    	return pModel.getProperty(propertyID);
    }
    
    
    public void savePropertyController(ApartmentModel a) {
    	pModel.saveProperty(a);
    }
    
    public void savePropertyController(CondoModel c) {
    	
    	pModel.saveProperty(c);
    }

    public void savePropertyController(HouseModel h) {
    	pModel.saveProperty(h);
    }
    
   
    // add Property Message
    public void addPropertyMessageController(String m) {
    	pView.displayStatusResult(m);
    }
    
    // display Prooperty
    public LinkedHashMap<Integer, PropertyModel> displayPropertyController() {
    	System.out.println(Thread.currentThread().getName());
    	return pModel.getPropertyList();
    }
    
    // renting a unit
    public String rentPropertyController(int leaseID, int propertyID, int tenantID, String leaseStartDate, String leaseEndDate) {
    	String rentProperyResult = pModel.rentProperty(leaseID, propertyID, tenantID, leaseStartDate, leaseEndDate);
 
    	return rentProperyResult;
    }
    
    // displaying vacant unit
    public LinkedHashMap<Integer, PropertyModel> displayVacantUnitsController() {
    	
    	return pModel.getVacantPropertyList();
    }
   
    // displaying rented  unit
    public LinkedHashMap<Integer, PropertyModel> displayRentedUnitsController() {
    	return pModel.getRentedPropertyList();
    }
    
    // Cancelling a lease
    public ArrayList<TenantModel> cancelLeaseController(int propertyID, boolean renovationUpdate) {
        return  pModel.cancelLease(propertyID, renovationUpdate);
   
    }
    
    // Renovation update
    public String updateRenovationStatus(int propertyID, boolean renovationUpdate) {
    	String res = pModel.updateRenovationStatus(propertyID, renovationUpdate);
    	return res;
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
