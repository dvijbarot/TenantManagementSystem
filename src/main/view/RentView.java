package main.view;

import java.util.ArrayList;
import java.util.Map;

import main.model.RentModel;
import main.model.RentPaymentModel;

public class RentView {

	// Displays message sent by RentController after adding a rent payment into
	// RentModel
	public void displayAddRentResult(String message) {
		System.out.println(message);
	}

	// Displays all rent payments for a particular property
	public void displayPropertyRent(Map<String, ArrayList<RentModel>> rent) {
		for (Map.Entry<String, ArrayList<RentModel>> entry : rent.entrySet()) {
			String propertyId = entry.getKey();
			ArrayList<RentModel> rentList = entry.getValue();

			System.out.println("Property ID: " + propertyId);
			System.out.println("Rent Payments:");
			for (RentModel rentPayment : rentList) {
				System.out.println(rentPayment.toString());
			}
			System.out.println();
		}
	}

	// Displays all rent payments made by a particular tenant
	public void displayTenantRent(ArrayList<RentModel> rent) {
		System.out.println("Rent Payments:");
		for (RentModel rentPayment : rent) {
			System.out.println(rentPayment.toString());
		}
	}

	public void displayRentPayments(ArrayList<RentPaymentModel> rentPayments) {
	}

	public void displayAddRentPaymentResult(String result) {
		System.out.println(result);
	}

}