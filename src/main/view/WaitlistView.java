package main.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import main.controller.WaitlistController;
import main.model.PropertyModel;
import main.model.TenantModel;
import main.model.WaitlistModel;

public class WaitlistView {
	
	  // "displayAddPropertyResult()" will display the message sent by PropertyConroller after adding the property into PropertyModel
	  public void displayAddWaitListResult(String message) {
		  System.out.println(message);
	  }
	
      public void displayWaitList(String message) {
    	  System.out.println(message);
      }
      public ArrayList<TenantModel> notifyTenants(int propertyid,ArrayList<TenantModel>notifyTenants)
      {
    	  //WaitlistController wLC=new WaitlistController();
    	  //ArrayList<TenantModel>tenantswailtlisted=wLC.notifyTenants(propertyid);
    	  if(notifyTenants==null)
    	  {
    		  
    		  //System.out.println("No tenants found for the oarticular property id: - "+propertyid);
    		  return notifyTenants;
    	  }
    	  else
    	  {
    		  for (TenantModel tenant : notifyTenants) 
    		  {
				//System.out.println("Dear "+tenant.getName()+" the property you were looking for is available. Kindly contact the admin before its gone!!!!");
				
			}
    	  }
    	  return notifyTenants;
      }
}
