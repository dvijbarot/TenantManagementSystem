package main.view;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.controller.PropertyController;
import main.controller.TenantController;
import main.model.PropertyModel;
import main.model.TenantModel;
import main.util.SceneController;
import main.util.builder.PropertyModelBuilder;
import main.util.builder.PropertyViewModelBuilder;
import main.util.builder.TenantModelBuilder;
import main.util.builder.TenantViewBuilder;

public class RentUnitView implements Runnable {
	// Creating SceneController Class Object
	SceneController sceneCtrl = new SceneController();

	// Property Vacancy Flag
	boolean propertVacantFlag = true, propertyAvailabilityFlag = true, tenantAvailablityFlag = true,
			propertyNotVacantTenantFlag = false, submitBtnFlag = false;

	// UI Fields
	Text notVacantText = new Text();
	Text propertyNotPresentText = new Text();
	Text tenantNotAvailableText = new Text();
	Text propertyNotAvailableText = new Text();
	Label propertyIDLabel = null;
	Label tenantIDLabel = new Label();
	Label leaseIDLabel = new Label();
	Label leaseStartDateLabel = new Label();
	Label leaseEndDateLabel = new Label();
	TextField propertIdValue = new TextField();
	TextField tenantIDValue = new TextField();
	TextField tenantIDValue1 = new TextField();
	TextField leaseIDValue = new TextField();
	DatePicker leaseStartDateValue = new DatePicker();
	DatePicker leaseEndDateValue = new DatePicker();

	// Creating variables for storing values entered by the user
	static int propertyID = -1, tenantID = -1, leaseID = 0;
	String leaseStartDate = null, leaseEndDate = null;

	// Creating Button Event Listener
	public void goHomeButtonClickListener(Event event) {
		sceneCtrl.switchToScene("Welcome Screen");
	}

	// Validating inputs
	public static boolean validateFields(TextField propertIdValue, TextField tenantIDValue, TextField leaseIDValue,
			DatePicker leaseStartDateValue, DatePicker leaseEndDateValue) {
		StringBuilder errorList = new StringBuilder();
		if (propertIdValue.getText().trim().isEmpty()) {
			errorList.append("Please enter Property ID\n");
		}
		if (tenantIDValue.getText().trim().isEmpty()) {
			errorList.append("Please enter Tenant ID\n");
		}
		if (leaseIDValue.getText().trim().isEmpty()) {
			errorList.append("Please enter Lease ID\n");
		}
		if (leaseStartDateValue.getValue() == null) {
			errorList.append("Please enter Lease Start Date\n");
		}
		if (leaseEndDateValue.getValue() == null) {
			errorList.append("Please enter Lease End Date\n");
		}
		if (leaseStartDateValue.getValue() != null) {
			LocalDate leaseStartDate = leaseStartDateValue.getValue();
			LocalDate currentDate = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			if (leaseStartDate.isBefore(currentDate)) {
				errorList.append(
						"You selected wrong date, Please select lease start date that is equal to or after today's date\n");
			}
		}
		if (leaseEndDateValue.getValue() != null) {
			LocalDate leaseEndDate = leaseStartDateValue.getValue();
			LocalDate currentDate = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			if (leaseEndDate.isBefore(currentDate)) {
				errorList.append("You selected wrong date, Please select lease end date that is after today's date\n");
			}
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

	// Checking PropertyID status from DB
	public String checkPropertyIDStatus(int pID) {

		RentUnitView.propertyID = pID;
		// Building PropertyModel and PropertyView using builder
		PropertyModelBuilder pBuilder = new PropertyModelBuilder();
		PropertyViewModelBuilder pViewBuilder = new PropertyViewModelBuilder();
		PropertyModel pModel = pBuilder.build();
		PropertyView pView = pViewBuilder.build();
		PropertyController pController = new PropertyController(pModel, pView);
		if (pController.getPropertyController(RentUnitView.propertyID) == null) {
			return "Not Present";
		} else if (pController.getPropertyController(RentUnitView.propertyID).isVacant) {
			return "Vacant";
		} else if (!pController.getPropertyController(RentUnitView.propertyID).isVacant) {
			return "Not Vacant";
		}

		return "No Check";
	}

	// Checking TenantID status from DB
	public String checkTenantIDStatus(int tID) {

		RentUnitView.tenantID = tID;
		// Building TenantModel and TenantView using builder
		TenantModelBuilder tBuilder = new TenantModelBuilder();
		TenantViewBuilder tViewBuilder = new TenantViewBuilder();
		TenantModel tModel = tBuilder.build();
		TenantView tView = tViewBuilder.build();
		TenantController tController = new TenantController(tModel, tView);
		if (tController.getTenant(RentUnitView.tenantID) == null) {
			return "Not Present";
		}

		else {
			return "Tenant Present";
		}

	}

	public void loadUI() {

		// Creating Layout
		VBox root = new VBox();
		// Label titleLabel = new Label("Add Apartment Data");
		GridPane gPane1 = new GridPane();
		GridPane gPane2 = new GridPane();

		// Creating Labels
		propertyIDLabel = new Label("Enter Property ID");
		tenantIDLabel = new Label("Enter Tenant ID");
		if (propertVacantFlag) {
			leaseIDLabel = new Label("Enter Lease ID");
			leaseStartDateLabel = new Label("Enter Lease Start Date");
			leaseEndDateLabel = new Label("Enter Lease End Date");
		}

		// Text when property is not vacant
		if (!propertVacantFlag) {
			notVacantText = new Text(
					"Property is currently not vacant, kindly provide your details. Will contact you once it gets vacant");
		}

		// Creating TextField to get data from the user
		propertIdValue = new TextField();
		propertIdValue.focusedProperty().addListener((observable, oldValue, newValue) -> {

			if (!newValue && (propertIdValue.getText().length() > 0)) {
				String propertyIDStatusResult = this.checkPropertyIDStatus(Integer.parseInt(propertIdValue.getText()));
				if (propertyIDStatusResult.equalsIgnoreCase("Vacant")) {
					gPane1.getChildren().removeIf(node -> GridPane.getRowIndex(node) == 1);
					propertVacantFlag = true;
					tenantIDValue.setText("");
					gPane1.addRow(1, tenantIDLabel, tenantIDValue);
					// submitBtnFlag = true;
				} else if (propertyIDStatusResult.equalsIgnoreCase("Not Vacant")) {
					gPane1.getChildren()
							.removeIf(node -> GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) >= 1);
					notVacantText.setText("Property is currently not vacant, kindly provide your details. Will contact you once it gets vacant");
					propertVacantFlag = false;
					tenantIDValue.setText("");
					gPane1.add(notVacantText, 0, 1, 3, 1);
					gPane1.addRow(2, tenantIDLabel, tenantIDValue); // will not track this textfield
					// submitBtnFlag = true;
				} else if (propertyIDStatusResult.equalsIgnoreCase("Not Present")) {
					gPane1.getChildren()
							.removeIf(node -> GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) >= 1);
					propertyNotPresentText.setText("Sorry there is no property registered in this ID, try entering a valid Property ID");
					gPane1.add(propertyNotPresentText, 0, 1, 3, 1);
				}
			}
		});

		tenantIDValue = new TextField();
		tenantIDValue.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue && (tenantIDValue.getText().length() > 0)) {
				System.out.println(propertVacantFlag);
				String tenantIDStatusResult = this.checkTenantIDStatus(Integer.parseInt(tenantIDValue.getText()));
				if (tenantIDStatusResult.equalsIgnoreCase("Tenant Present") && !propertVacantFlag) {

					submitBtnFlag = true;

				}
				else if (tenantIDStatusResult.equalsIgnoreCase("Tenant Present") && propertVacantFlag) {
					gPane1.getChildren().removeIf(node -> GridPane.getRowIndex(node) == 2);
					leaseIDValue.setText("");
					leaseStartDateValue.setValue(null);
					leaseEndDateValue.setValue(null);
					gPane1.addRow(2, leaseIDLabel, leaseIDValue);
					gPane1.addRow(3, leaseStartDateLabel, leaseStartDateValue);
					gPane1.addRow(4, leaseEndDateLabel, leaseEndDateValue);
					submitBtnFlag = true;

				} else if (tenantIDStatusResult.equalsIgnoreCase("Not Present")) {
					gPane1.getChildren().removeIf(node -> GridPane.getRowIndex(node) == 1);
					gPane1.getChildren().removeIf(node -> GridPane.getRowIndex(node) == 2);
					tenantIDValue.setText("");
					gPane1.addRow(1, tenantIDLabel, tenantIDValue);
	
					tenantNotAvailableText.setText("Sorry, there is no tenant regsitered in this ID, try entering a valid Tenant ID");
					gPane1.add(tenantNotAvailableText, 0, 2, 3, 1);

					// submitBtnFlag = false;
				}
				

			}
		});

		gPane1.addRow(0, propertyIDLabel, propertIdValue);
		gPane1.setAlignment(Pos.CENTER);
		gPane1.setHgap(25);
		gPane1.setVgap(15);

		// Setting up GridPane2
		Button submitBtn = new Button("Submit");
		Button homeScreenButton = new Button("Go Home Screen");
		gPane2.addRow(0, submitBtn, homeScreenButton);
		gPane2.setAlignment(Pos.CENTER);
		gPane2.setHgap(25);
		gPane2.setVgap(15);

		// Invoking button event listeners method
		homeScreenButton.setOnAction((event) -> {
			this.goHomeButtonClickListener(event);
		});

		System.out.println("Came here");
		submitBtn.setOnAction((event) -> {

			if (submitBtnFlag && propertVacantFlag) {
				boolean validationResult = RentUnitView.validateFields(propertIdValue, tenantIDValue, leaseIDValue,
						leaseStartDateValue, leaseEndDateValue);
				if (validationResult) {
					propertyID = Integer.parseInt(propertIdValue.getText());
					tenantID = Integer.parseInt(tenantIDValue.getText());
					leaseID = Integer.parseInt(leaseIDValue.getText());
					leaseStartDate = leaseStartDateValue.getValue().toString();
					leaseEndDate = leaseEndDateValue.getValue().toString();
					// Building PropertyModel and PropertyView using builder
					PropertyModelBuilder pBuilder = new PropertyModelBuilder();
					PropertyViewModelBuilder pViewBuilder = new PropertyViewModelBuilder();
					PropertyModel pModel = pBuilder.build();
					PropertyView pView = pViewBuilder.build();
					PropertyController pController = new PropertyController(pModel, pView);
					String res = pController.rentPropertyController(leaseID, propertyID, tenantID, leaseStartDate,
							leaseEndDate);
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Success");
					alert.setHeaderText("Rent Property");
					alert.setContentText(res);
					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == ButtonType.OK) {
						sceneCtrl.switchToScene("Welcome Screen");
					}

					else if (result.get() == ButtonType.CANCEL) {
						sceneCtrl.switchToScene("Welcome Screen");
					}
				}
			}
			else if (submitBtnFlag && !propertVacantFlag) {
				
				
					propertyID = Integer.parseInt(propertIdValue.getText());
					tenantID = Integer.parseInt(tenantIDValue.getText());
					leaseID = 0;
					leaseStartDate = null;
					leaseEndDate = null;
					// Building PropertyModel and PropertyView using builder
					PropertyModelBuilder pBuilder = new PropertyModelBuilder();
					PropertyViewModelBuilder pViewBuilder = new PropertyViewModelBuilder();
					PropertyModel pModel = pBuilder.build();
					PropertyView pView = pViewBuilder.build();
					PropertyController pController = new PropertyController(pModel, pView);
					String res = pController.rentPropertyController(leaseID, propertyID, tenantID, leaseStartDate,
							leaseEndDate);
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Waitlisted");
					alert.setHeaderText("Waitlisted in Property");
					alert.setContentText(res);
					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == ButtonType.OK) {
						sceneCtrl.switchToScene("Welcome Screen");
					}

					else if (result.get() == ButtonType.CANCEL) {
						sceneCtrl.switchToScene("Welcome Screen");
					}
				}
			
		});

		// Adding components in root layout
		root.getChildren().addAll(gPane1, gPane2);
		root.setAlignment(Pos.TOP_CENTER);
		root.setSpacing(30);

		Scene rentUnitScene = new Scene(root);

		
		// Adding CSS Selectors
		notVacantText.getStyleClass().add("not-present-text");
		propertyNotPresentText.getStyleClass().add("not-present-text");
		tenantNotAvailableText.getStyleClass().add("not-present-text");
		root.getStyleClass().add("root-layout");
		submitBtn.getStyleClass().add("submit-btn");
		homeScreenButton.getStyleClass().add("go-home-btn");
		rentUnitScene.getStylesheets().add("/main/styles/RentUnitViewStyle.css");
	
		
		sceneCtrl.addScene("Rent Unit", rentUnitScene);
		sceneCtrl.switchToScene("Rent Unit");
	}

	@Override
	public void run() {
		System.out.println("Rent Unit View: " + Thread.currentThread().getName());
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				RentUnitView rentViewObj = new RentUnitView();
				rentViewObj.loadUI();
			}

		});

	}
}