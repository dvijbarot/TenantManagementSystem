package main.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import main.model.ApartmentModel;
import main.model.CondoModel;
import main.model.HouseModel;
import main.model.PropertyModel;

public class PropertyView {

	// "displayAddPropertyResult()" will display the message sent by
	// PropertyConroller after adding the property into PropertyModel
	public void displayStatusResult(String message) {
		System.out.println(message);
	}

	public void displayProperty(String propertyList) {
		if (propertyList.length() == 0) {
			System.out.println("Sorry, there are no vacant properties in the system");
		} else {
			System.out.println(propertyList);
		}
	}
}
