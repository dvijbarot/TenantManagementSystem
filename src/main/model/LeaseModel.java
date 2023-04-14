package main.model;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class LeaseModel {
	private int leaseID;
	private PropertyModel property;
	private TenantModel tenant;
	private String startDate;
	private String endDate;
	private double rentAmount;
	private boolean isActive;
	private static HashMap<Integer, LeaseModel> leases = new HashMap<>();
	static LinkedHashMap<Integer, LeaseModel> LeaseList = new LinkedHashMap<Integer, LeaseModel>();

	public LeaseModel() {
	}

	public LeaseModel(int leaseID, PropertyModel property, TenantModel tenant, String startDate, String endDate,
			double rentAmount) {
		this.leaseID = leaseID;
		this.property = property;
		this.tenant = tenant;
		this.startDate = startDate;
		this.endDate = endDate;
		this.rentAmount = rentAmount;
		isActive = true;
		int key = property.propertyID;
		if (leases.containsKey(key)) {
			leases.get(key).isActive = false; // deactivate the previous lease of the same property type
		}
		leases.put(key, this); // add the new lease to the HashMap
	}

	public int getLeaseID() {
		return this.leaseID;
	}

	public PropertyModel getProperty() {
		return property;
	}

	public TenantModel getTenant() {
		return tenant;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public double getRentAmount() {
		return rentAmount;
	}

	public boolean isActive() {
		return isActive;
	}

	public static HashMap<Integer, LeaseModel> getLeases() {
		return leases;
	}

	public LeaseModel getLeaseDetails(int propertyID) {
		return leases.get(propertyID);
	}

	@Override
	public String toString() {
		return "Property ID: " + this.property.getPropertyID() + "\nTenant Name: " + this.tenant.getName()
				+ "\nStart Date: " + this.getStartDate() + "\nEnd Date: " + this.getEndDate() + "\nRent Amount: $"
				+ this.getRentAmount();
	}

}