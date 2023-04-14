package main.controller;

import java.util.ArrayList;
import java.util.HashMap;

import main.model.PropertyModel;
import main.model.TenantModel;
import main.model.WaitlistModel;
import main.view.WaitlistView;

public class WaitlistController {
	PropertyModel waitListedProperty;
	TenantModel waitListedTenant;
	WaitlistModel wModel;
	WaitlistView wView;

	public WaitlistController() {

	}
	
	public WaitlistController(WaitlistModel model, WaitlistView view) {
		this.wModel = model;
		this.wView = view;
		
	}

	public WaitlistController(WaitlistModel model, WaitlistView view, PropertyModel p, TenantModel t) {
		this.wModel = model;
		this.wView = view;
		this.waitListedProperty = p;
		this.waitListedTenant = t;
	}

	public void saveWaitListedObjects(int key, TenantModel w) {
		WaitlistModel.saveWaitList(key, w);
	}

	public HashMap<Integer, ArrayList<TenantModel>> getWaitList() {
		return wModel.getWaitList(); // returning WaitList from WaitListModel
	}

	public void updateWaitListView() {
		wView.displayWaitList(wModel.getWaitList().toString());
	}

	public void addWaitListMessage(String m) {
		wView.displayAddWaitListResult(m);
	}

	public ArrayList<TenantModel> notifyTenants(int propertyID) {
		ArrayList<TenantModel> notifyTenants = new ArrayList<TenantModel>();
		notifyTenants = wModel.notifyTenants(propertyID);
		wView.notifyTenants(propertyID, notifyTenants);
		return notifyTenants;

	}
	
	public void removeTenantFromWaitlistController(int propertyID, int tenantID) {
		wModel.removeTenantFromWaitlist(propertyID,tenantID);
	}
}