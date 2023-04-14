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
import main.model.CondoModel;
import main.model.HouseModel;
import main.util.SceneController;
import main.util.builder.CondoModelBuilder;
import main.util.builder.HouseModelBuilder;
import main.util.builder.PropertyViewModelBuilder;

public class AddCondoView {
	// Creating SceneController Class Object
	SceneController sceneCtrl = new SceneController();

	// Creating variables for storing values entered by the user
	int propertyID, rentAmount, condoUnitNumber;
	String condoStreetNumber, streetName, city, province, country, postalCode;

	// Creating Button Event Listener
	public void goHomeButtonClickListener(Event event) {
		sceneCtrl.switchToScene("Welcome Screen");
	}

	// Validating inputs
	public static boolean validateFields(TextField propertIdValue, TextField condoUnitNumberValue,
			TextField condoStreetNumberValue, TextField condoStreetNameValue, TextField condoCityValue,
			TextField condoProvinceValue, TextField condoCountryValue, TextField condoPostalCodeValue,
			TextField condoRentValue) {
		StringBuilder errorList = new StringBuilder();
		if (propertIdValue.getText().trim().isEmpty()) {
			errorList.append("Please enter Property ID\n");
		}
		if (condoUnitNumberValue.getText().trim().isEmpty()) {
			errorList.append("Please enter Condo Unit Number\n");
		}
		if (condoStreetNameValue.getText().trim().isEmpty()) {
			errorList.append("Please enter Condo Street Name\n");
		}
		if (condoCityValue.getText().trim().isEmpty()) {
			errorList.append("Please enter City\n");
		}
		if (condoProvinceValue.getText().trim().isEmpty()) {
			errorList.append("Please enter Province\n");
		}
		if (condoCountryValue.getText().trim().isEmpty()) {
			errorList.append("Please enter Country\n");
		}
		if (condoPostalCodeValue.getText().trim().isEmpty()) {
			errorList.append("Please enter Postal Code\n");
		}
		if (condoRentValue.getText().trim().isEmpty()) {
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

	public void loadUI() {

		// Creating Layout
		VBox root = new VBox();
		// Label titleLabel = new Label("Add Apartment Data");
		GridPane gPane = new GridPane();

		// Creating Labels
		Label propertyIDLabel = new Label("Enter Property ID");
		Label condoUnitNumberLabel = new Label("Enter Condo Unit Number");
		Label condoStreetNumberLabel = new Label("Enter Condo Street Number");
		Label condoStreetNameLabel = new Label("Enter Street Name");
		Label condoCityLabel = new Label("Enter City");
		Label condoProvinceLabel = new Label("Enter Province");
		Label condoCountryLabel = new Label("Enter Country");
		Label condoPostalCodeLabel = new Label("Enter Postal Code");
		Label condoRentLabel = new Label("Enter Rent Amount");

		// Creating TextField to get data from the user
		TextField propertIdValue = new TextField();
		TextField condoUnitNumberValue = new TextField();
		TextField condoStreetNumberValue = new TextField();
		TextField condoStreetNameValue = new TextField();
		TextField condoCityValue = new TextField();
		TextField condoProvinceValue = new TextField();
		TextField condoCountryValue = new TextField();
		TextField condoPostalCodeValue = new TextField();
		TextField condoRentValue = new TextField();
		Button homeScreenButton = new Button("Go Home Screen");
		Button submitBtn = new Button("Submit");

		gPane.addRow(0, propertyIDLabel, propertIdValue);
		gPane.addRow(1, condoUnitNumberLabel, condoUnitNumberValue);
		gPane.addRow(2, condoStreetNumberLabel, condoStreetNumberValue);
		gPane.addRow(3, condoStreetNameLabel, condoStreetNameValue);
		gPane.addRow(4, condoCityLabel, condoCityValue);
		gPane.addRow(5, condoProvinceLabel, condoProvinceValue);
		gPane.addRow(6, condoCountryLabel, condoCountryValue);
		gPane.addRow(7, condoPostalCodeLabel, condoPostalCodeValue);
		gPane.addRow(8, condoRentLabel, condoRentValue);
		gPane.addRow(9, submitBtn, homeScreenButton);
		gPane.setAlignment(Pos.CENTER);
		gPane.setHgap(25);
		gPane.setVgap(10);
		GridPane.setMargin(homeScreenButton, new Insets(40, 0, 0, 0));
		GridPane.setMargin(submitBtn, new Insets(40, 0, 0, 0));

		submitBtn.setOnAction((event) -> {
			boolean validationResult = AddCondoView.validateFields(propertIdValue, condoUnitNumberValue,
					condoStreetNumberValue, condoStreetNameValue, condoCityValue, condoProvinceValue, condoCountryValue,
					condoPostalCodeValue, condoRentValue);
			if (validationResult) {
				propertyID = Integer.parseInt(propertIdValue.getText());
				condoUnitNumber = Integer.parseInt(condoUnitNumberValue.getText());
				condoStreetNumber = condoStreetNumberValue.getText();
				streetName = condoStreetNameValue.getText();
				city = condoCityValue.getText();
				province = condoProvinceValue.getText();
				country = condoCountryValue.getText();
				postalCode = condoPostalCodeValue.getText();
				rentAmount = Integer.parseInt(condoRentValue.getText());

				// Creating Thread for Adding Condo
				Runnable r1 = new Runnable() {

					@Override
					public void run() {
						CondoModelBuilder cBuilder = new CondoModelBuilder();
						PropertyViewModelBuilder pViewBuilder = new PropertyViewModelBuilder();
						PropertyView pView;
						CondoModel cModel;
						PropertyController pController;
						// creating ApartmentModel using ApartmentBuilder
						cModel = cBuilder.setPropertyID(propertyID).setCondoStreetNumber(condoStreetNumber)
								.setCondoStreetNumber(condoStreetNumber).setStreetName(streetName).setCity(city)
								.setProvince(province).setCountry(country).setPostalCode(postalCode)
								.setRentAmount(rentAmount).build();
						// creating PropertyView using PropertyView
						pView = pViewBuilder.build();
						pController = new PropertyController(cModel, pView);
						pController.savePropertyController(cModel); // saving property object in PropertyModel through
																	// PropertyController
						pController
								.addPropertyMessageController("Condo of ID " + propertyID + " is added successfully"); // sending
																														// message

					}

				};
				Thread t1 = new Thread(r1, "Business Logic Thread");
				t1.start();

				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Success");
				alert.setHeaderText("Condo Added Sucessfully");
				alert.setContentText("Condo of ID " + propertyID + " is added successfully");
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

		Scene addCondoScene = new Scene(root);
		// Adding CSS
		addCondoScene.getStylesheets().add("/main/styles/AddCondoViewStyle.css");

		// Adding CSS Selectors
		root.getStyleClass().add("root-layout");
		submitBtn.getStyleClass().add("submit-btn");
		homeScreenButton.getStyleClass().add("go-home-btn");
		sceneCtrl.addScene("Add Condo", addCondoScene);
		sceneCtrl.switchToScene("Add Condo");

	}
}
