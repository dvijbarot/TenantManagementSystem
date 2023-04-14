package main.view;

import java.util.LinkedHashMap;
import java.util.Map;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import main.controller.PropertyController;
import main.model.ApartmentModel;
import main.model.CondoModel;
import main.model.HouseModel;
import main.model.PropertyModel;
import main.util.SceneController;
import main.util.builder.PropertyModelBuilder;
import main.util.builder.PropertyViewModelBuilder;

public class DisplayPropertyView implements Runnable {
	String propertyFlag;

	DisplayPropertyView() {
	}

	DisplayPropertyView(String propertyVacancyStatusFlag) {
		this.propertyFlag = propertyVacancyStatusFlag;
	}

	// Creating SceneController Class Object
	SceneController sceneCtrl = new SceneController();

	// Event listener for the "Go Home Screen" button
	private void goHomeButtonClickListener(ActionEvent event) {
		sceneCtrl.switchToScene("Welcome Screen");
	}

	// Property Dummy Data
	static LinkedHashMap<Integer, PropertyModel> propertyList = new LinkedHashMap<Integer, PropertyModel>();

	// Load Property Data
	void setPropertyData(String propertyUpdate) {
		if (propertyUpdate.equalsIgnoreCase("All Property")) {
			Runnable r1 = new Runnable() {
				PropertyModelBuilder pBuilder = new PropertyModelBuilder();
				PropertyViewModelBuilder pViewBuilder = new PropertyViewModelBuilder();
				PropertyModel pModel = pBuilder.build();
				PropertyView pView = pViewBuilder.build();
				PropertyController pController = new PropertyController(pModel, pView);

				@Override
				public void run() {
					// TODO Auto-generated method stub

					DisplayPropertyView.propertyList = pController.displayPropertyController();
				}

			};
			Thread t = new Thread(r1, "Business Logic Thread");
			t.start();
		} else if (propertyUpdate.equalsIgnoreCase("Vacant Property")) {
			Runnable r2 = new Runnable() {
				PropertyModelBuilder pBuilder = new PropertyModelBuilder();
				PropertyViewModelBuilder pViewBuilder = new PropertyViewModelBuilder();
				PropertyModel pModel = pBuilder.build();
				PropertyView pView = pViewBuilder.build();
				PropertyController pController = new PropertyController(pModel, pView);

				@Override
				public void run() {

					DisplayPropertyView.propertyList = pController.displayVacantUnitsController();

				}

			};
			Thread t2 = new Thread(r2, "Business Logic Thread");
			t2.start();
		} else if (propertyUpdate.equalsIgnoreCase("Rented Property")) {

			Runnable r3 = new Runnable() {
				PropertyModelBuilder pBuilder = new PropertyModelBuilder();
				PropertyViewModelBuilder pViewBuilder = new PropertyViewModelBuilder();
				PropertyModel pModel = pBuilder.build();
				PropertyView pView = pViewBuilder.build();
				PropertyController pController = new PropertyController(pModel, pView);

				@Override
				public void run() {

					DisplayPropertyView.propertyList = pController.displayRentedUnitsController();

				}

			};
			Thread t3 = new Thread(r3, "Business Logic Thread");
			t3.start();
		}
	}

	public void loadUI() {
		Text propertyVacancyStatusMessage = new Text();
		if (propertyList.size() == 0) {
			GridPane root = new GridPane();
			propertyVacancyStatusMessage.setText("Sorry, there are no properties in the system");
			Button homeScreenButton = new Button("Go Home Screen");
			homeScreenButton.setAlignment(Pos.CENTER);

			homeScreenButton.setOnAction((event) -> {
				this.goHomeButtonClickListener(event);
			});

			// Create a StackPane and add the Text node to it
			StackPane stackPane = new StackPane(propertyVacancyStatusMessage);
			StackPane.setMargin(propertyVacancyStatusMessage, new Insets(100, 0, 50, 0));
			root.addRow(0, stackPane);
			root.addRow(1, homeScreenButton);
			root.setAlignment(Pos.TOP_CENTER);
			GridPane.setHalignment(homeScreenButton, javafx.geometry.HPos.CENTER);
			GridPane.setValignment(homeScreenButton, javafx.geometry.VPos.CENTER);
			Scene displayPropertyScene = new Scene(root);
			// Adding CSS
			propertyVacancyStatusMessage.getStyleClass().add("not-property-text");
			displayPropertyScene.getStylesheets()
					.add(getClass().getResource("/main/styles/DisplayPropertyViewStyle.css").toExternalForm());
			sceneCtrl.addScene("Display Property", displayPropertyScene);
			sceneCtrl.switchToScene("Display Property");

		} else {
			int row = 0, col = 0, i;
			Text propertyDisplayMessage = new Text("List of properties are:");
			GridPane root = new GridPane();
			GridPane globalRoot = new GridPane();
			ScrollPane scrollPane = new ScrollPane(globalRoot);
			scrollPane.setFitToWidth(true);
			scrollPane.setFitToHeight(true);
			root.setAlignment(Pos.CENTER);
			root.setHgap(15);

			for (Map.Entry<Integer, PropertyModel> entry : propertyList.entrySet()) {
				int key = entry.getKey();
				PropertyModel property = entry.getValue();
				BorderPane card = new BorderPane();
				card.getStyleClass().add("card-view");
				card.setPadding(new Insets(10));

				FlowPane fPane = new FlowPane();
				if (property.getPropertyType().equalsIgnoreCase("Apartment")) {
					Label propertyID = new Label("PropertyID : " + String.valueOf(property.getPropertyID()));
					Label typeOfProperty = new Label("Property Type : " + property.getPropertyType());
					Label propertyVacanyStatus = new Label("Property Vacany Status : "
							+ (property.getVacancy() ? "Property is Vacant" : "Property is not Vacant"));
					Label propertyNumber = new Label(
							"Apartment Number : " + ((ApartmentModel) property).getApartmentNumber());
					Label propertyCivicNumber = new Label(
							"Apartment Civic Number : " + ((ApartmentModel) property).getCivicAddress());
					Label propertyStreet = new Label("Street is : " + property.getStreetName());
					Label propertyCity = new Label("City is : " + property.getCity());
					Label properyProvince = new Label("Province is : " + property.getProvince());
					Label propertyCountry = new Label("Country is : " + property.getCountry());
					Label propertyPostalCode = new Label("Postal Code is : " + property.getPostalCode());
					Label propertyBedroomCount = new Label(
							"Number of Bedrooms : " + ((ApartmentModel) property).getNumberOfBedRooms());
					Label propertyBathromCount = new Label(
							"Number of Bathrooms : " + ((ApartmentModel) property).getNumberOfBathrooms());
					Label propertySquareFootage = new Label(
							"Size of Apartment : " + ((ApartmentModel) property).getSquareFootage() + " sq.ft");
					Label propertyRent = new Label("Rent is : " + property.getRent());
					Label renovationNeeded = new Label("Renovation needed for the property : "
							+ (property.getRenovationUpdateStatus() ? "Yes" : "No"));
					fPane.getChildren().addAll(propertyID, typeOfProperty, propertyVacanyStatus, propertyNumber,
							propertyCivicNumber, propertyStreet, propertyCity, properyProvince, propertyCountry,
							propertyPostalCode, propertyBedroomCount, propertyBathromCount, propertySquareFootage,
							propertyRent, renovationNeeded);
				} else if (property.getPropertyType().equalsIgnoreCase("Condo")) {
					Label propertyID = new Label("PropertyID : " + String.valueOf(property.getPropertyID()));
					Label typeOfProperty = new Label("Property Type : " + property.getPropertyType());
					Label propertyVacanyStatus = new Label("Property Vacany Status : "
							+ (property.getVacancy() ? "Property is Vacant" : "Property is not Vacant"));
					Label propertyNumber = new Label("Condo Unit Number : " + ((CondoModel) property).getUnitNumber());
					Label propertyCivicNumber = new Label(
							"Condo Street Number : " + ((CondoModel) property).getStreetNumber());
					Label propertyStreet = new Label("Street is : " + property.getStreetName());
					Label propertyCity = new Label("City is : " + property.getCity());
					Label properyProvince = new Label("Province is : " + property.getProvince());
					Label propertyCountry = new Label("Country is : " + property.getCountry());
					Label propertyPostalCode = new Label("Postal Code is : " + property.getPostalCode());
					Label propertyRent = new Label("Rent is : " + property.getRent());
					Label renovationNeeded = new Label("Renovation needed for the property : "
							+ (property.getRenovationUpdateStatus() ? "Yes" : "No"));
					fPane.getChildren().addAll(propertyID, typeOfProperty, propertyVacanyStatus, propertyNumber,
							propertyCivicNumber, propertyStreet, propertyCity, properyProvince, propertyCountry,
							propertyPostalCode, propertyRent, renovationNeeded);
				} else if (property.getPropertyType().equalsIgnoreCase("House")) {
					Label propertyID = new Label("PropertyID : " + String.valueOf(property.getPropertyID()));
					Label typeOfProperty = new Label("Property Type : " + property.getPropertyType());
					Label propertyVacanyStatus = new Label("Property Vacany Status : "
							+ (property.getVacancy() ? "Property is Vacant" : "Property is not Vacant"));

					Label propertyCivicNumber = new Label(
							"Condo Street Number : " + ((HouseModel) property).getStreetNumber());
					Label propertyStreet = new Label("Street is : " + property.getStreetName());
					Label propertyCity = new Label("City is : " + property.getCity());
					Label properyProvince = new Label("Province is : " + property.getProvince());
					Label propertyCountry = new Label("Country is : " + property.getCountry());
					Label propertyPostalCode = new Label("Postal Code is : " + property.getPostalCode());
					Label propertyRent = new Label("Rent is : " + property.getRent());
					Label renovationNeeded = new Label("Renovation needed for the property : "
							+ (property.getRenovationUpdateStatus() ? "Yes" : "No"));
					fPane.getChildren().addAll(propertyID, typeOfProperty, propertyVacanyStatus, propertyCivicNumber,
							propertyStreet, propertyCity, properyProvince, propertyCountry, propertyPostalCode,
							propertyRent, renovationNeeded);
				}

				fPane.setMinHeight(300);
				fPane.setMaxHeight(300);
				fPane.setMinWidth(280);
				fPane.setMaxWidth(280);
				fPane.setOrientation(Orientation.VERTICAL);
				card.setCenter(fPane);
				GridPane.setMargin(card, new Insets(20));
				root.add(card, col, row);

				++col;
				if (col == 4) {
					col = 0;
					++row;
				}
			}
			Button homeScreenButton = new Button("Go Home Screen");

			homeScreenButton.setOnAction((event) -> {
				this.goHomeButtonClickListener(event);
			});
			globalRoot.addRow(0, root);
			globalRoot.addRow(1, homeScreenButton);
			globalRoot.setAlignment(Pos.CENTER);
			GridPane.setHalignment(homeScreenButton, javafx.geometry.HPos.CENTER);
			GridPane.setValignment(homeScreenButton, javafx.geometry.VPos.CENTER);
			Scene displayPropertyScene = new Scene(scrollPane);
			sceneCtrl.addScene("Display Property", displayPropertyScene);
			sceneCtrl.switchToScene("Display Property");
			// Adding CSS
			displayPropertyScene.getStylesheets()
					.add(getClass().getResource("/main/styles/DisplayPropertyViewStyle.css").toExternalForm());
		}
	}

	@Override
	public void run() {
		System.out.println("Display Property: " + Thread.currentThread().getName());
		DisplayPropertyView dView = new DisplayPropertyView();

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				if (propertyFlag.equalsIgnoreCase("All Property")) {
					dView.setPropertyData(propertyFlag);
				} else if (propertyFlag.equalsIgnoreCase("Rented Property")) {
					dView.setPropertyData(propertyFlag);
				} else if (propertyFlag.equalsIgnoreCase("Vacant Property")) {
					dView.setPropertyData(propertyFlag);
				}
				dView.loadUI();
			}

		});
	}

}
