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
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import main.controller.PropertyController;
import main.model.ApartmentModel;
import main.util.SceneController;
import main.util.builder.ApartmentModelBuilder;
import main.util.builder.PropertyViewModelBuilder;

public class AddApartmentView  {
	// Creating SceneController Class Object
	SceneController sceneCtrl = new SceneController();
	// Creating variables for storing values entered by the user
	int id, aptNumber, aptBedroomCount, aptBathroomCount, rentAmount;
	String aptCivicAddress, streetName, city, province, country, postalCode;
	double aptSquareFootage;

	// Creating Button Event Listener
	public void goHomeButtonClickListener(Event event) {
		sceneCtrl.switchToScene("Welcome Screen");
	}

	public static boolean validateFields(TextField propertIdValue, TextField aptNumberValue,
			TextField aptCivicAddressValue, TextField aptStreetNameValue, TextField aptCityValue,
			TextField aptProvinceValue, TextField aptCountryValue, TextField aptPostalCodeValue,
			TextField aptBedroomCountValue, TextField aptBathroomCountValue, TextField aptSquareFootageValue,
			TextField aptRentValue) {
		StringBuilder errorList = new StringBuilder();
		if (propertIdValue.getText().trim().isEmpty()) {
			errorList.append("Please enter Property ID\n");
		}
		if (propertIdValue.getText().trim().isEmpty()) {
			errorList.append("Please enter Property ID\n");
		}
		if (aptNumberValue.getText().trim().isEmpty()) {
			errorList.append("Please enter Apartment Number\n");
		}
		if (aptCivicAddressValue.getText().trim().isEmpty()) {
			errorList.append("Please enter Apartment Civic Address\n");
		}
		if (aptStreetNameValue.getText().trim().isEmpty()) {
			errorList.append("Please enter Street Name\n");
		}
		if (aptCityValue.getText().trim().isEmpty()) {
			errorList.append("Please enter City\n");
		}
		if (aptProvinceValue.getText().trim().isEmpty()) {
			errorList.append("Please enter Province\n");
		}
		if (aptCountryValue.getText().trim().isEmpty()) {
			errorList.append("Please enter Country\n");
		}
		if (aptPostalCodeValue.getText().trim().isEmpty()) {
			errorList.append("Please enter Postal Code\n");
		}
		if (aptBedroomCountValue.getText().trim().isEmpty()) {
			errorList.append("Please enter No of Bedrooms\n");
		}
		if (aptBathroomCountValue.getText().trim().isEmpty()) {
			errorList.append("Please enter No of Bathrooms\n");
		}
		if (aptSquareFootageValue.getText().trim().isEmpty()) {
			errorList.append("Please enter Apartment Square Footage\n");
		}
		if (aptRentValue.getText().trim().isEmpty()) {
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
		Label aptNumberLabel = new Label("Enter Apartment Number");
		Label aptCivicAddressLabel = new Label("Enter Apartment Civic Address");
		Label aptStreetNameLabel = new Label("Enter Street Name");
		Label aptCityLabel = new Label("Enter City");
		Label aptProvinceLabel = new Label("Enter Province");
		Label aptCountryLabel = new Label("Enter Country");
		Label aptPostalCodeLabel = new Label("Enter Postal Code");
		Label aptBedroomCountLabel = new Label("Enter No of Bedrooms");
		Label aptBathroomCountLabel = new Label("Enter No of Bathrooms");
		Label aptSquareFootageLabel = new Label("Enter Apartment Square Footage");
		Label aptRentLabel = new Label("Enter Rent Amount");
		Label aptAddMessgae = new Label();

		// Creating TextField to get data from the user
		TextField propertIdValue = new TextField();
		TextField aptNumberValue = new TextField();
		TextField aptCivicAddressValue = new TextField();
		TextField aptStreetNameValue = new TextField();
		TextField aptCityValue = new TextField();
		TextField aptProvinceValue = new TextField();
		TextField aptCountryValue = new TextField();
		TextField aptPostalCodeValue = new TextField();
		TextField aptBedroomCountValue = new TextField();
		TextField aptBathroomCountValue = new TextField();
		TextField aptSquareFootageValue = new TextField();
		TextField aptRentValue = new TextField();
		Button homeScreenButton = new Button("Go Home Screen");
		Button submitBtn = new Button("Submit");

		gPane.addRow(0, propertyIDLabel, propertIdValue);
		gPane.addRow(1, aptNumberLabel, aptNumberValue);
		gPane.addRow(2, aptCivicAddressLabel, aptCivicAddressValue);
		gPane.addRow(3, aptStreetNameLabel, aptStreetNameValue);
		gPane.addRow(4, aptCityLabel, aptCityValue);
		gPane.addRow(5, aptProvinceLabel, aptProvinceValue);
		gPane.addRow(6, aptCountryLabel, aptCountryValue);
		gPane.addRow(7, aptPostalCodeLabel, aptPostalCodeValue);
		gPane.addRow(8, aptBedroomCountLabel, aptBedroomCountValue);
		gPane.addRow(9, aptBathroomCountLabel, aptBathroomCountValue);
		gPane.addRow(10, aptSquareFootageLabel, aptSquareFootageValue);
		gPane.addRow(11, aptRentLabel, aptRentValue);
		gPane.addRow(12, submitBtn, homeScreenButton);
		gPane.setAlignment(Pos.CENTER);
		gPane.setHgap(25);
		gPane.setVgap(10);
		GridPane.setMargin(homeScreenButton, new Insets(40, 0, 0, 0));
		GridPane.setMargin(submitBtn, new Insets(40, 0, 0, 0));

		submitBtn.setOnAction((event) -> {
			boolean validationResult = AddApartmentView.validateFields(propertIdValue, aptNumberValue,
					aptCivicAddressValue, aptStreetNameValue, aptCityValue, aptProvinceValue, aptCountryValue,
					aptPostalCodeValue, aptBedroomCountValue, aptBathroomCountValue, aptSquareFootageValue,
					aptRentValue);
			if (validationResult) {
				id = Integer.parseInt(propertIdValue.getText());
				aptNumber = Integer.parseInt(aptNumberValue.getText());
				aptCivicAddress = aptCivicAddressValue.getText();
				streetName = aptStreetNameValue.getText();
				city = aptCityValue.getText();
				province = aptProvinceValue.getText();
				country = aptCountryValue.getText();
				postalCode = aptPostalCodeValue.getText();
				aptBedroomCount = Integer.parseInt(aptBedroomCountValue.getText());
				aptBathroomCount = Integer.parseInt(aptBathroomCountValue.getText());
				aptSquareFootage = Double.parseDouble(aptSquareFootageValue.getText());
				rentAmount = Integer.parseInt(aptRentValue.getText());
				// Creating Thread for Add Property
				Runnable r1 = new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						ApartmentModelBuilder aBuilder = new ApartmentModelBuilder();
						PropertyViewModelBuilder pViewBuilder = new PropertyViewModelBuilder();
						PropertyView pView;
						ApartmentModel aModel;
						PropertyController pController;
						// creating PropertyView using PropertyView
						// creating ApartmentModel using ApartmentBuilder
						aModel = aBuilder.setPropertyID(id).setaptNumber(aptNumber).setAptCivicAddress(aptCivicAddress)
								.setStreetName(streetName).setCity(city).setProvince(province).setCountry(country)
								.setPostalCode(postalCode).setAptBedroomCount(aptBedroomCount)
								.setAptBathroomCount(aptBathroomCount).setAptSquareFootage(aptSquareFootage)
								.setRentAmount(rentAmount).build();
						
						pView = pViewBuilder.build();
						pController = new PropertyController(aModel, pView);
						pController.savePropertyController(aModel); // saving property object in PropertyModel
																	// through PropertyController
						pController.addPropertyMessageController("Apartment of ID " + id + " is added successfully"); // sending
																														// message
					}
					
				};
			    Thread t1 = new Thread("Bussiness Logic Thread");
			    t1.start();
			    
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Success");
				alert.setHeaderText("Apartment Added Sucessfully");
				alert.setContentText("Apartment of ID " + id + " is added successfull");
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

		Scene addApartmentScene = new Scene(root);

		// Adding CSS
		addApartmentScene.getStylesheets().add("/main/styles/AddApartmentViewStyle.css");

		// Adding CSS Selectors
		root.getStyleClass().add("root-layout");
		submitBtn.getStyleClass().add("submit-btn");
		homeScreenButton.getStyleClass().add("go-home-btn");

		// Invoking Scenes
		sceneCtrl.addScene("Add Apartment", addApartmentScene);
		sceneCtrl.switchToScene("Add Apartment");

	}

	
}
