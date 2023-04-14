package main.view;

import java.util.HashMap;
import java.util.Map;

import main.model.LeaseModel;

public class LeaseView {

	public void displayLeases(HashMap<Integer, LeaseModel> leases) {

		for (Map.Entry<Integer, LeaseModel> entry : leases.entrySet()) {
			LeaseModel lease = entry.getValue();
			System.out.println("Property ID: " + lease.getProperty().getPropertyID() + "\nTenant name: "
					+ lease.getTenant().getName() + "\nStart date: " + lease.getStartDate() + "\nEnd date: "
					+ lease.getEndDate() + "\nRent amount: " + lease.getRentAmount() + "\n");
		}
	}

	public void displayLeaseDetails(String message) {
		System.out.println(message);
	}
}
