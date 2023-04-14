package main.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashMap;

public class TenantModel {
	public String firstName, lastName, eMail;
	public int tenantId;
	static LinkedHashMap<Integer, TenantModel> TenantList = new LinkedHashMap<Integer, TenantModel>();
	// static ArrayList<TenantModel> tenantListarrayList = new
	// ArrayList<TenantModel>();

	public TenantModel() {
	}

	public TenantModel(int tenantId, String firstName, String lastName, String eMail) {
		this.tenantId = tenantId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.eMail = eMail;
	}
	public int getTenantId()
	{
		return this.tenantId;
	}
	public String getTenantFirstName()
	{
		return this.firstName;
	}
	public String getTenantLastName()
	{
		return this.lastName;
	}
	public String getTenantEmail()
	{
		return this.eMail;
	}

	public static void saveTenant(int tenantId, TenantModel T) {
		TenantList.put(tenantId, T);
	}

	public LinkedHashMap<Integer,TenantModel> getTenantList() {
		// TODO Auto-generated method stub
		// String res="";
		// for(Map.Entry<Integer, TenantModel> tList: TenantList.entrySet()) {
		// 	int id = tList.getKey();
		// 	TenantModel tenant = tList.getValue();
		// 	res+= tenant.toString();
		// 	res+="\n";
		// }
		// if (res.length() == 0) {
		// 	return "";
		// }
		return TenantList;
	}

	public String getName() {
		return (firstName +" "+lastName);
	}

	public TenantModel getTenant(int tenantID) {
		return TenantList.get(tenantID);
	}
	
	
	@Override
	public String toString() {
		String res = "Tenant ID: "+this.tenantId+"\nFirst Name: "+this.firstName+"\nLast Name: "+this.lastName+"\nEmail ID: "+this.eMail+"\n";
		return res;
	}

}
