package main.model;

public class RentPaymentModel {
	private int tenantID;
	private int propertyID;
	private String date;
	private int amount;
	private boolean isPaid;

//	public RentPaymentModel(int propertyID, int tenantID, String date, int amount, boolean isPaid) {
//		this.propertyID = propertyID;
//		this.tenantID = tenantID;
//		this.date = date;
//		this.amount = amount;
//		this.isPaid = isPaid;
//	}

	// updated model
	public RentPaymentModel(int propertyID, int tenantID, int amount, boolean isPaid) {
		this.propertyID = propertyID;
		this.tenantID = tenantID;
//		this.date = date;
		this.amount = amount;
		this.isPaid = isPaid;
	}

	public int getPropertyID() {
		return this.propertyID;
	}

	public int getTenantID() {
		return this.tenantID;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String geDate() {
		return this.date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}
}