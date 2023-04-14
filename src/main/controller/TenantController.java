package main.controller;

import java.util.LinkedHashMap;

import main.model.PropertyModel;
import main.model.TenantModel;
import main.model.WaitlistModel;
import main.view.TenantView;
import main.view.WaitlistView;

public class TenantController implements Runnable {
	TenantModel tModel;
	TenantView tView;
	WaitlistController wController;
	WaitlistModel wModel;
	WaitlistView wView;
	String methodIndex;
	

	public TenantController() {
	}
	

	public TenantController(TenantModel tm, TenantView tv) {
		this.tModel = tm;
		this.tView = tv;
		this.wController = new WaitlistController();
		this.wModel = new WaitlistModel();
		this.wView = new WaitlistView();
	}

	// Save Tenant in Tenant Model
	public void saveTenantObject(int TenantID, TenantModel T) {
		TenantModel.saveTenant(TenantID, T);
	}

	// Getting Tenant list from Tenant Model
	public void getTenantList() {
		tView.displayTenant(tModel.getTenantList());
	}

	// Updating Tenant View
	public void updateTenantView() {
		tView.displayTenant(tModel.getTenantList());
	}

	// Add Tenant Message
	public void addTenantMessage(String m) {
		tView.displayAddTenantMessage(m);
	}

	public TenantModel getTenant(int tenantID) {

		return tModel.getTenant(tenantID);
	}

	public String waitListTenant(int tenantID, PropertyModel pModel) {
		TenantModel tenantObj = this.tModel.getTenant(tenantID);
		wModel.setWaitListedProperty(pModel);
		int key = pModel.getPropertyID();
		String message = WaitlistModel.saveWaitList(key, tenantObj);
		return message;
	}

	public LinkedHashMap<Integer, TenantModel> displayTenantController() {


		return tModel.getTenantList();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}