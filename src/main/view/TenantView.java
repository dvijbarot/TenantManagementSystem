package main.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import main.model.TenantModel;

public class TenantView {

	public void displayTenant(HashMap<Integer,TenantModel>list) {
		if(list.size()==0) {
			System.out.println("There are no tenants in the system");
		}
		else {
			System.out.println(list);
		}

	}

	public void displayAddTenantMessage(String m) {
		// TODO Auto-generated method stub
		System.out.println(m);
	}

}