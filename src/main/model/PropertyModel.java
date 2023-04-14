package main.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import main.controller.LeaseController;
import main.controller.TenantController;
import main.controller.WaitlistController;
import main.view.LeaseView;
import main.view.TenantView;
import main.view.WaitlistView;

public class PropertyModel {
	String streetName, postalCode, city, country, province, leaseStartDate, leaseEndDate, propertyType;
	public int propertyID;
	public boolean isVacant = true;
	public boolean rentPaid = false;
	public boolean renovationNeeded = false;
	public int rentAmount, i;
	public TenantModel tModel = null; // by default tenant is not there, so it is null
	public static LinkedHashMap<Integer, PropertyModel> propertyList = new LinkedHashMap<Integer, PropertyModel>();

	public PropertyModel() {
	}

	public PropertyModel(int propertyID, String propertyType, String streetName, String city, String province, String country,
			String postalCode, int rentAmount) {
		this.propertyID = propertyID;
		this.propertyType = propertyType;
		this.streetName = streetName;
		this.city = city;
		this.province = province;
		this.country = country;
		this.postalCode = postalCode;
		this.rentAmount = rentAmount;
		this.renovationNeeded = false;
		this.leaseStartDate = null;
		this.leaseEndDate = null;
	}

	// Creating getters for accessing data members

	public int getPropertyID() {
		return this.propertyID;
	}
	
	public String getPropertyType() {
		return this.propertyType;
	}

	public PropertyModel getProperty(int propertyID) {
		return propertyList.get(propertyID);
	}

	public boolean getVacancy() {
		return this.isVacant;
	}

	public String getStreetName() {
		return this.streetName;
	}

	public String getCity() {
		return this.city;
	}

	public String getProvince() {
		return this.province;
	}

	public String getCountry() {
		return this.country;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public int getRent() {
		return this.rentAmount;
	}

	public TenantModel getTenantModel() {
		return this.tModel;
	}
	
	public boolean getRenovationUpdateStatus() {
		return this.renovationNeeded;
	}

	// Getting All properties
	public LinkedHashMap<Integer, PropertyModel> getPropertyList() {
//		String res = "";
//		for (Map.Entry<Integer, PropertyModel> property : propertyList.entrySet()) {
//			int propertyID = property.getKey();
//			PropertyModel pModel = property.getValue();
//			res += pModel.toString();
//			res += "\n";
//		}
//		if (res.length() == 0) {
//			return "";
//		}
		return propertyList ;
	}

	// Getting Vacant properties
	public LinkedHashMap<Integer, PropertyModel>  getVacantPropertyList() {
		LinkedHashMap<Integer, PropertyModel> vacantPropertyList = new LinkedHashMap<Integer, PropertyModel>();
		String res = "";
		for (Map.Entry<Integer, PropertyModel> property : propertyList.entrySet()) {
			int propertyID = property.getKey();
			PropertyModel pModel = property.getValue();
			if (pModel.isVacant == true) {
				vacantPropertyList.put(propertyID, pModel);
			}
		}
		return vacantPropertyList;
	}

	// Getting Vacant properties
	public LinkedHashMap<Integer, PropertyModel>  getRentedPropertyList() {
		LinkedHashMap<Integer, PropertyModel> rentedPropertyList = new LinkedHashMap<Integer, PropertyModel>();
		String res = "";
		for (Map.Entry<Integer, PropertyModel> property : propertyList.entrySet()) {
			int propertyID = property.getKey();
			PropertyModel pModel = property.getValue();
			if (pModel.isVacant == false) {
				rentedPropertyList.put(propertyID, pModel);
			}
		}
		return rentedPropertyList;
	}

	// Save Apartment 
	public void saveProperty(ApartmentModel p) {
		propertyList.put(p.propertyID, p);
	}
	
	// Save Condo
	public void saveProperty(CondoModel p) {
		propertyList.put(p.propertyID, p);
	}
	
	// Save House
	public void saveProperty(HouseModel p) {
		propertyList.put(p.propertyID, p);
	}

	// Renting a property
	public String rentProperty(int leaseID, int propertyID, int tenantID, String leaseStartDate, String leaseEndDate) {
		String rentPropertyResult = null;
		
		for (Map.Entry<Integer, PropertyModel> obj : propertyList.entrySet()) {
			int pID = obj.getKey();
			PropertyModel pModel = obj.getValue();
			if (pModel.propertyID == propertyID) {
				TenantModel tModel = new TenantModel();
				TenantView tView = new TenantView();
				TenantController tController = new TenantController(tModel, tView);
				tModel =  tController.getTenant(tenantID);
				if (tModel == null) {
					return rentPropertyResult = "There is no tenant of ID, add a tenant and try again";
				}
				if (pModel.isVacant == true) {
					WaitlistModel wModel = new WaitlistModel();
					WaitlistView wView = new WaitlistView();
					WaitlistController wControl = new WaitlistController(wModel, wView);
					wControl.removeTenantFromWaitlistController(propertyID, tenantID);
					LeaseModel lModel = new LeaseModel();
					LeaseView lView = new LeaseView();
					LeaseController lController = new LeaseController(lModel, lView);
                    lController.createLease(leaseID, pModel, tModel, leaseStartDate, leaseEndDate, pModel.rentAmount);
					pModel.isVacant = false;
					pModel.tModel = tModel; // here "null" will be replaced by t which was mentioned above in comments
					rentPropertyResult = "Property of ID " + propertyID + " is rented to tenant of ID " + tenantID;
				} else {
					// waitlisting if property is not vacant

					String m = tController.waitListTenant(tenantID, pModel);
					rentPropertyResult = m;
					
				}
			}

		}
		if (rentPropertyResult == null) {
			rentPropertyResult = "There is no property of ID: " + propertyID;
		}
		return rentPropertyResult;
	}

	// Displaying Vacant Units
	public String displayVacantUnits() {
		String vacantUnitsResult = "";
		for (Map.Entry<Integer, PropertyModel> properties : propertyList.entrySet()) {
			int pID = properties.getKey();
			PropertyModel pModel = properties.getValue();
			if (pModel.isVacant == true) {
				vacantUnitsResult += pModel.toString().replaceAll(", ", "\n");
			}
		}

		if (vacantUnitsResult == "") {
			vacantUnitsResult = "There is no Vacant Units";
		}
		return vacantUnitsResult;

	}

	// Cancel Lease
	public ArrayList<TenantModel> cancelLease(int propertyID, boolean renovationUpdate) {
		propertyList.get(propertyID).tModel = null;
		propertyList.get(propertyID).leaseStartDate = null;
		propertyList.get(propertyID).leaseEndDate = null;
		if (renovationUpdate == true) {
			propertyList.get(propertyID).renovationNeeded = true;
			propertyList.get(propertyID).isVacant = false;
		} else if (renovationUpdate == false) {
			propertyList.get(propertyID).renovationNeeded = false;
			propertyList.get(propertyID).isVacant = true;
		}
		
		WaitlistModel wModel = new WaitlistModel();
		WaitlistView wView = new WaitlistView();
		WaitlistController wController  = new WaitlistController(wModel, wView);
		
		return wController.notifyTenants(propertyID);
	}
	
	// Update Renovation Status
		public String updateRenovationStatus(int propertyID, boolean renovationUpdate) {
			if (renovationUpdate == true) {
				propertyList.get(propertyID).renovationNeeded = true;
				propertyList.get(propertyID).isVacant = false;
			} else if (renovationUpdate == false) {
				propertyList.get(propertyID).renovationNeeded = false;
				propertyList.get(propertyID).isVacant = true;
			}

			return "Renovation Status updated successfully";
		}



}
