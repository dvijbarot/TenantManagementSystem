package main.view;

import java.util.ArrayList;
import java.util.Optional;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.controller.PropertyController;
import main.model.PropertyModel;
import main.model.TenantModel;
import main.util.SceneController;
import main.util.builder.PropertyModelBuilder;
import main.util.builder.PropertyViewModelBuilder;

public class CancelLeaseView implements Runnable {
	static String operation;
	String renovationUpdateResult = null;

	CancelLeaseView() {
	}

	CancelLeaseView(String operation) {
		CancelLeaseView.operation = operation;
	}

	// Creating SceneController Class Object
	@Override
	public void run() {
		if (CancelLeaseView.operation.equalsIgnoreCase("Update Lease")) {
			System.out.println("Update Lease View: " + Thread.currentThread().getName());
		} else if (CancelLeaseView.operation.equalsIgnoreCase("Cancel Lease")) {
			System.out.println("Cancel Lease View: " + Thread.currentThread().getName());
		}
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				CancelLeaseView cancelLeaseObj = new CancelLeaseView();
				if (CancelLeaseView.operation.equalsIgnoreCase("Update Lease")) {
					cancelLeaseObj.setLeaseViewFlag();
				}
				cancelLeaseObj.loadUI();

			}

		});
	}

	SceneController sceneCtrl = new SceneController();

	// Update Lease View Flag
	boolean updateLeaseViewFlag = false, submitBtnFlag = false;
	static boolean renovationStatusAddedFlag = false, renovationResultFlag = false;

	Text propertyNotPresentText = new Text();
	public static int propertyID = -1;

	public void setLeaseViewFlag() {
		this.updateLeaseViewFlag = true;
	}

	// Creating Button Event Listener
	public void goHomeButtonClickListener(Event event) {
		sceneCtrl.switchToScene("Welcome Screen");
	}

	// Validating inputs
	public static boolean validateFields(TextField propertIdValue, TextField renovationStatusFlagValue) {

		StringBuilder errorList = new StringBuilder();
		if (propertIdValue.getText().trim().isEmpty()) {
			errorList.append("Please enter Property ID\n");
		}
		if (CancelLeaseView.renovationStatusAddedFlag) {

			if (renovationStatusFlagValue.getText().trim().isEmpty()) {
				errorList.append("Please enter Renovation needed or not status\n");
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

		if (CancelLeaseView.propertyID == -1
				|| (CancelLeaseView.propertyID != -1 && CancelLeaseView.propertyID != pID)) {
			CancelLeaseView.propertyID = pID;
			// Building PropertyModel and PropertyView using builder
			PropertyModelBuilder pBuilder = new PropertyModelBuilder();
			PropertyViewModelBuilder pViewBuilder = new PropertyViewModelBuilder();
			PropertyModel pModel = pBuilder.build();
			PropertyView pView = pViewBuilder.build();
			PropertyController pController = new PropertyController(pModel, pView);
			if (pController.getPropertyController(CancelLeaseView.propertyID) == null) {
				return "Not Present";
			} else {
				return "Present";
			}
		} else {
			return "No Check";
		}

	}

	public void loadUI() {

		// Creating Layout
		VBox root = new VBox();
		// Label titleLabel = new Label("Add Apartment Data");
		GridPane gPane1 = new GridPane();
		GridPane gPane2 = new GridPane();

		// UI
		TextField renovationStatusFlagValue = new TextField();
		TextField propertIdValue = new TextField();
		Label propertyIDLabel = null;

		// Creating Labels
		if (!updateLeaseViewFlag) {
			propertyIDLabel = new Label("Enter Property ID to cancel lease");
		} else if (updateLeaseViewFlag) {
			propertyIDLabel = new Label("Enter Property ID to update lease");
		}
		Label renovationStatusFlagLabel = new Label("Enter Yes for renovation needed or No if renovation not needed");

		// Listening PropertyID TextField to get data from the user
		propertIdValue.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue && (propertIdValue.getText().length() > 0)) {
				String propertyIDStatusResult = this.checkPropertyIDStatus(Integer.parseInt(propertIdValue.getText()));
				if (propertyIDStatusResult.equalsIgnoreCase("Not Present")) {
					gPane1.getChildren()
							.removeIf(node -> GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) >= 1);

					propertyNotPresentText.setText("Sorry there is no property registered in this ID, try entering a valid Property ID");
					gPane1.add(propertyNotPresentText, 0, 1, 3, 1);
					submitBtnFlag = false;
					renovationStatusAddedFlag = false;

				} else if (propertyIDStatusResult.equalsIgnoreCase("Present")) {
					gPane1.getChildren()
							.removeIf(node -> GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) >= 1);

					gPane1.addRow(1, renovationStatusFlagLabel, renovationStatusFlagValue);
					submitBtnFlag = true;
					renovationStatusAddedFlag = true;
				}
			}
		});

		gPane1.addRow(0, propertyIDLabel, propertIdValue);

		gPane1.setAlignment(Pos.CENTER);
		gPane1.setHgap(25);
		gPane1.setVgap(10);

		// propertIdValue.setPrefWidth(200);
		// Creating Submit Button
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

		submitBtn.setOnAction((event) -> {
			if (submitBtnFlag) {
				// boolean validationResult = CancelLeaseView.validateFields(propertIdValue,
				// renovationStatusFlagValue);
				if (!propertIdValue.getText().trim().isEmpty()
						&& !renovationStatusFlagValue.getText().trim().isEmpty()) {
					System.out.println("Came");
					if (renovationStatusFlagValue.getText().trim().equalsIgnoreCase("Yes")
							|| renovationStatusFlagValue.getText().trim().equalsIgnoreCase("Y")) {
						renovationResultFlag = true;
					} else if (renovationStatusFlagValue.getText().trim().equalsIgnoreCase("No")
							|| renovationStatusFlagValue.getText().trim().equalsIgnoreCase("N")) {
						renovationResultFlag = false;
					}
					// Building PropertyModel and PropertyView using builder
					PropertyModelBuilder pBuilder = new PropertyModelBuilder();
					PropertyViewModelBuilder pViewBuilder = new PropertyViewModelBuilder();
					PropertyModel pModel = null;
					PropertyView pView;
					pModel = pBuilder.build();
					pView = pViewBuilder.build();
					PropertyController pController = new PropertyController(pModel, pView);
					
					if (this.updateLeaseViewFlag) {
						
						// Creating Thread for updating renovation status
                         Runnable r1 = new Runnable() {

							@Override
							public void run() {
								renovationUpdateResult = pController.updateRenovationStatus(CancelLeaseView.propertyID,
										renovationResultFlag);
								
							}};
						Thread t1 = new Thread(r1, "Business Logic Thread");
						t1.start();
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setTitle("Success");
						alert.setHeaderText("Renovation Status Update");
						alert.setContentText(renovationUpdateResult);
						Optional<ButtonType> result = alert.showAndWait();
						if (result.get() == ButtonType.OK) {
							CancelLeaseView.propertyID = -1;
							sceneCtrl.switchToScene("Welcome Screen");
						}

						else if (result.get() == ButtonType.CANCEL) {
							CancelLeaseView.propertyID = -1;
							sceneCtrl.switchToScene("Welcome Screen");
						}
					} else {
						ArrayList<TenantModel> waitlistResult = pController
								.cancelLeaseController(CancelLeaseView.propertyID, renovationResultFlag);
						if (waitlistResult==null) {
							Alert alert = new Alert(Alert.AlertType.INFORMATION);
							alert.setTitle("Success");
							alert.setHeaderText("Lease Cancellation");
							alert.setContentText("Lease Cancelled Successfully");
							alert.getDialogPane().setPrefSize(400, 200);
							Optional<ButtonType> result = alert.showAndWait();
							if (result.get() == ButtonType.OK) {
								CancelLeaseView.propertyID = -1;
								sceneCtrl.switchToScene("Welcome Screen");
							}

							else if (result.get() == ButtonType.CANCEL) {
								CancelLeaseView.propertyID = -1;
								sceneCtrl.switchToScene("Welcome Screen");
							}
						} else {
							Alert alert = new Alert(Alert.AlertType.INFORMATION);
							alert.setTitle("Success");
							alert.setHeaderText("Lease Cancellation");
							alert.setContentText("Lease Cancelled Successfully");
				
						
							alert.getDialogPane().setPrefSize(680, 250);
							for (TenantModel tenant : waitlistResult) {
								alert.setContentText("Dear " + tenant.getName()
										+ " ,the property you were looking for is available. Kindly contact the admin before its gone!!!!");

							}
							Optional<ButtonType> result = alert.showAndWait();
							if (result.get() == ButtonType.OK) {
								CancelLeaseView.propertyID = -1;
								sceneCtrl.switchToScene("Welcome Screen");
							}

							else if (result.get() == ButtonType.CANCEL) {
								CancelLeaseView.propertyID = -1;
								sceneCtrl.switchToScene("Welcome Screen");
							}
						}
					}

				}
			}

		});

		// Adding components in root layout
		root.getChildren().addAll(gPane1, gPane2);
		root.setAlignment(Pos.TOP_CENTER);
		root.setSpacing(30);

		Scene cancelLeaseScene = new Scene(root);

		// Adding CSS
		cancelLeaseScene.getStylesheets().add("/main/styles/CancelLeaseViewStyle.css");

		// Adding CSS Selectors
		root.getStyleClass().add("root-layout");
		submitBtn.getStyleClass().add("submit-btn");
		homeScreenButton.getStyleClass().add("go-home-btn");
		propertyNotPresentText.getStyleClass().add("no-property-text");

		sceneCtrl.addScene("Cancel Lease", cancelLeaseScene);
		sceneCtrl.switchToScene("Cancel Lease");

	}
}
