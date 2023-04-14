package main.controller;

import main.model.RentModel;
import main.model.RentPaymentModel;
import main.view.RentView;

public class RentController {
	private RentModel rentModel;
	private RentView rentView;

	public RentController(RentModel rentModel, RentView rentView) {
		this.rentModel = rentModel;
		this.rentView = rentView;
	}

	public RentController() {
	}

//	public void addRentPayment(int propertyID, int tenantID, String date, int amount) {
//		RentPaymentModel rentPayment = new RentPaymentModel(propertyID, tenantID, date, amount, true);
//		rentModel.addRentPayment(rentPayment);
//		rentView.displayAddRentPaymentResult("Rent payment added for property of ID " + propertyID);
//	}

	// updated method
	public void addRentPayment(int propertyID, int tenantID, int amount) {
		RentPaymentModel rentPayment = new RentPaymentModel(propertyID, tenantID, amount, true);
		rentModel.addRentPayment(rentPayment);
		rentView.displayAddRentPaymentResult("Rent payment added for property of ID " + propertyID);
	}

	public void viewRentPayments() {
		rentView.displayRentPayments(rentModel.getRentPayments());
	}

	public void checkRentStatusController(int propertyID) {
		rentView.displayAddRentPaymentResult(rentModel.checkRentStatus(propertyID));
	}

//    public void markRentPaid(int index) {
//        RentPaymentModel rentPayment = rentModel.getRentPayments().get(index);
//        rentPayment.setPaid(true);
//        rentView.displayAddRentResult("Rent payment marked as paid for property of ID" + rentModel.getProperty().getPropertyID()
//                + " for " + rentPayment.getMonth() + "/" + rentPayment.getYear());
//    }
}