package main.view;

import java.util.Optional;

import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import main.controller.PropertyController;
import main.model.ApartmentModel;
import main.model.HouseModel;
import main.util.SceneController;
import main.util.builder.ApartmentModelBuilder;
import main.util.builder.HouseModelBuilder;
import main.util.builder.PropertyViewModelBuilder;

public class AddHouseView {
	// Creating SceneController Class Object
	SceneController sceneCtrl = new SceneController();

	// Creating variables for storing values entered by the user
	int propertyID, rentAmount;
	String houseStreetNumber, streetName, city, province, country, postalCode;

	// Validating inputs
	public static boolean validateFields(TextField propertIdValue, TextField houseStreetNumberValue,
			TextField houseStreetNameValue, TextField houseCityValue, TextField houseProvinceValue,
			TextField houseCountryValue, TextField housePostalCodeValue, TextField houseRentValue) {
		StringBuilder errorList = new StringBuilder();
		if (propertIdValue.getText().trim().isEmpty()) {
			errorList.append("Please enter Property ID\n");
		}
		if (houseStreetNumberValue.getText().trim().isEmpty()) {
			errorList.append("Please enter House Street Number\n");
		}
		if (houseStreetNameValue.getText().trim().isEmpty()) {
			errorList.append("Please enter House Street Name\n");
		}
		if (houseCityValue.getText().trim().isEmpty()) {
			errorList.append("Please enter City\n");
		}
		if (houseProvinceValue.getText().trim().isEmpty()) {
			errorList.append("Please enter Province\n");
		}
		if (houseCountryValue.getText().trim().isEmpty()) {
			errorList.append("Please enter Country\n");
		}
		if (housePostalCodeValue.getText().trim().isEmpty()) {
			errorList.append("Please enter Postal Code\n");
		}
		if (houseRentValue.getText().trim().isEmpty()) {
			errorList.append("Please enter Rent Amount\n");
		}

		if (errorList.length() > 0) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Warning");
			alert.setHeaderText("Required Fields Empty");
			alert.setContentText(errorList.toString());

			alert.showAndWait();
			return false;
		}

		// returns true if there is no error
		return true;
	}

	// Creating Button Event Listener
	public void goHomeButtonClickListener(Event event) {
		sceneCtrl.switchToScene("Welcome Screen");
	}

	public void loadUI() {

		// Creating Layout
		VBox root = new VBox();
		// Label titleLabel = new Label("Add Apartment Data");
		GridPane gPane = new GridPane();

		// Creating Labels
		Label propertyIDLabel = new Label("Enter Property ID");
		Label houseStreetNumberLabel = new Label("Enter House Street Number");
		Label houseStreetNameLabel = new Label("Enter Street Name");
		Label houseCityLabel = new Label("Enter City");
		Label houseProvinceLabel = new Label("Enter Province");
		Label houseCountryLabel = new Label("Enter Country");
		Label housePostalCodeLabel = new Label("Enter Postal Code");
		Label houseRentLabel = new Label("Enter Rent Amount");

		// Creating TextField to get data from the user
		TextField propertIdValue = new TextField();
		TextField houseStreetNumberValue = new TextField();
		TextField houseStreetNameValue = new TextField();
		TextField houseCityValue = new TextField();
		TextField houseProvinceValue = new TextField();
		TextField houseCountryValue = new TextField();
		TextField housePostalCodeValue = new TextField();
		TextField houseRentValue = new TextField();
		Button homeScreenButton = new Button("Go Home Screen");
		Button submitBtn = new Button("Submit");

		gPane.addRow(0, propertyIDLabel, propertIdValue);
		gPane.addRow(1, houseStreetNumberLabel, houseStreetNumberValue);
		gPane.addRow(2, houseStreetNameLabel, houseStreetNameValue);
		gPane.addRow(3, houseCityLabel, houseCityValue);
		gPane.addRow(4, houseProvinceLabel, houseProvinceValue);
		gPane.addRow(5, houseCountryLabel, houseCountryValue);
		gPane.addRow(6, housePostalCodeLabel, housePostalCodeValue);
		gPane.addRow(7, houseRentLabel, houseRentValue);
		gPane.addRow(8, submitBtn, homeScreenButton);
		gPane.setAlignment(Pos.CENTER);
		gPane.setHgap(25);
		gPane.setVgap(10);
		GridPane.setMargin(homeScreenButton, new Insets(40, 0, 0, 0));
		GridPane.setMargin(submitBtn, new Insets(40, 0, 0, 0));

		submitBtn.setOnAction((event) -> {
			boolean validationResult = AddHouseView.validateFields(propertIdValue, houseStreetNumberValue,
					houseStreetNameValue, houseCityValue, houseProvinceValue, houseCountryValue, housePostalCodeValue,
					houseRentValue);
			if (validationResult) {
				propertyID = Integer.parseInt(propertIdValue.getText());
				houseStreetNumber = houseStreetNumberValue.getText();
				streetName = houseStreetNameValue.getText();
				city = houseCityValue.getText();
				province = houseProvinceValue.getText();
				country = houseCountryValue.getText();
				postalCode = housePostalCodeValue.getText();
				rentAmount = Integer.parseInt(houseRentValue.getText());
				// Creating Thread for Adding House
				Runnable r1 = new Runnable() {

					@Override
					public void run() {
						HouseModelBuilder hBuilder = new HouseModelBuilder();
						PropertyViewModelBuilder pViewBuilder = new PropertyViewModelBuilder();
						PropertyView pView;
						HouseModel hModel;
						PropertyController pController;
						// creating ApartmentModel using ApartmentBuilder
						hModel = hBuilder.setPropertyID(propertyID).setHouseStreetNumber(houseStreetNumber)
								.setStreetName(streetName).setCity(city).setProvince(province).setCountry(country)
								.setPostalCode(postalCode).setRentAmount(rentAmount).build();
						// creating PropertyView using PropertyView
						pView = pViewBuilder.build();
						pController = new PropertyController(hModel, pView);
						pController.savePropertyController(hModel); // saving property object in PropertyModel through
																	// PropertyController
						pController.addPropertyMessageController("House of ID " + propertyID + " is added successfully"); // sending  message
																												
						
					}};
				Thread t1 = new Thread(r1, "Business Logic Thread");
				t1.start();
		
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Success");
				alert.setHeaderText("House Added Sucessfully");
				alert.setContentText("House of ID " + propertyID + " is added successfully");
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
					sceneCtrl.switchToScene("Welcome Screen");
				}

				else if (result.get() == ButtonType.CANCEL) {
					sceneCtrl.switchToScene("Welcome Screen");
				}

			}
		});

		// Invoking button event listeners method
		homeScreenButton.setOnAction((event) -> {
			this.goHomeButtonClickListener(event);
		});

		// Adding components in root layout
		root.getChildren().addAll(gPane);
		root.setAlignment(Pos.TOP_CENTER);
		root.setSpacing(30);

		Scene addHouseScene = new Scene(root);
		// Adding CSS
		addHouseScene.getStylesheets().add("/main/styles/AddHouseViewStyle.css");

		// Adding CSS Selectors
		root.getStyleClass().add("root-layout");
		submitBtn.getStyleClass().add("submit-btn");
		homeScreenButton.getStyleClass().add("go-home-btn");
		sceneCtrl.addScene("Add House", addHouseScene);
		sceneCtrl.switchToScene("Add House");

	}
}
