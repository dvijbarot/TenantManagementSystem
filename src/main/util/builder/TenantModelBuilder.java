package main.util.builder;

import main.model.TenantModel;

public class TenantModelBuilder {
	int tenantId;
	String firstName, lastName,  eMail;
	
	public TenantModelBuilder setTenantID(int tenantID) {
		this.tenantId = tenantID;
		return this;
	}
	
	public TenantModelBuilder setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}
	
	public TenantModelBuilder setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}
	
	public TenantModelBuilder setEmail(String email) {
		this.eMail = email;
		return this;
	}
	
	public TenantModel build() {
		return new TenantModel(tenantId, firstName, lastName, eMail);
	}
}
