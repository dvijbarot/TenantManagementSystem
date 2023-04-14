package main.model;
import java.util.ArrayList;
import java.util.HashMap;

public class WaitlistModel {
	PropertyModel waitListedProperty;
	static ArrayList<TenantModel>waitListedTenant;
	//static HashMap<String, ArrayList<WaitlistModel>> waitList = new HashMap<String, ArrayList<WaitlistModel>>();
	static HashMap<Integer, ArrayList<TenantModel>> WaitList=new HashMap<>();
	public static String saveWaitList(int key, TenantModel w) {
		// TODO Auto-generated method stub
		if(WaitList.containsKey(key))
		{
			ArrayList<TenantModel>tenants = getWaitListedTenant(key);
			tenants.add(w);
			WaitList.put(key, tenants);
		}
		else
		{
			ArrayList<TenantModel>tenants = new ArrayList<>();
			tenants.add(w);
			WaitList.put(key, tenants);
		}
		//WaitList.put(key, w);
		String message="Tenant is added to waitlist for the property of ID "+key;
		return message;
	}
	public static ArrayList<TenantModel>getWaitListedTenant(int propertyID)
	{   
		return WaitlistModel.WaitList.get(propertyID);
	}
	public void setWaitListedProperty(PropertyModel p)
	{
		this.waitListedProperty=p;
	}
	public HashMap<Integer, ArrayList<TenantModel>> getWaitList() {
		// TODO Auto-generated method stub
		return WaitList;
	}
	public ArrayList<TenantModel> notifyTenants(int propertyID)
	{
		ArrayList<TenantModel>notifyTenants=getWaitListedTenant(propertyID);
		 			 return notifyTenants;
	}
	
	// Removing tenant from waitlist if he is rented to a particular property
	public void removeTenantFromWaitlist(int propertyID,int tenantID) {
	   
		if(!WaitlistModel.WaitList.isEmpty() && WaitList.containsKey(propertyID)) {
			ArrayList<TenantModel> tenantList = WaitlistModel.WaitList.get(propertyID);
			for(int i=0;i<tenantList.size();i++) {
				TenantModel t = tenantList.get(i);
				if(t.tenantId==tenantID) {
					tenantList.remove(i); // removing tenant from the waitlist
					break;
				}
			}
		}
	}

}