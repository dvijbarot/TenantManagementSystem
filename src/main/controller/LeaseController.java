package main.controller;

import java.util.HashMap;

import main.model.LeaseModel;
import main.model.PropertyModel;
import main.model.TenantModel;
import main.view.LeaseView;

public class LeaseController {
	private LeaseModel leaseModel;
	private LeaseView leaseView;

	public LeaseController(LeaseModel leaseModel, LeaseView leaseView) {
		this.leaseModel = leaseModel;
		this.leaseView = leaseView;
	}

	public void createLease(int leaseID, PropertyModel property, TenantModel tenant, String startDate, String endDate,
			int rentAmount) {
		leaseModel = new LeaseModel(leaseID, property, tenant, startDate, endDate, rentAmount);
	}

	public HashMap<Integer, LeaseModel> getLeases() {
		return LeaseModel.getLeases();
	}

	public void updateLeaseView() {
		leaseView.displayLeases(getLeases());
	}

	public void getLeaseDetailsController(int propertyID) {
		String res = "";
		LeaseModel lModel = leaseModel.getLeaseDetails(propertyID);
		if (lModel == null) {
			res = "There is no lease for the property ID " + propertyID;
		} else {
			res = "Property ID: " + propertyID + "\nLease ID: " + lModel.getLeaseID() + "\nLease Start Date: "
					+ lModel.getStartDate() + "\nLease End Date: " + lModel.getEndDate();
		}
		leaseView.displayLeaseDetails(res);
	}

}