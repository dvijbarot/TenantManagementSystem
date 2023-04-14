package main.view;

import java.util.Optional;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
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
import main.controller.RentController;
import main.model.RentModel;
import main.util.SceneController;
import main.util.builder.RentModelBuilder;
import main.util.builder.RentViewModelBuilder;

public class AddPaymentView implements Runnable {
	// Creating a Scene Controller
	@Override
	public void run() {
		System.out.println("Add Payment View: " + Thread.currentThread().getName());
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				AddPaymentView addPaymentObj = new AddPaymentView();
				addPaymentObj.loadUI();

			}

		});
	}
	SceneController sController = new SceneController();

	// Creating Button Event Listener
	public void goHomeButtonClickListener(ActionEvent event) {
		sController.switchToScene("Welcome Screen");
	}

	public void loadUI() {
		VBox root = new VBox();
		Label propertyID = new Label("Property ID:");
		TextField propertyIDField = new TextField();
		Label tenantID = new Label("Tenant ID:");
		TextField tenantIDField = new TextField();
		Label rentAmount = new Label("Rent Amount:");
		TextField rentAmountField = new TextField();
		Label successMessage = new Label();
		Button homeScreenButton = new Button("Go Home Screen");
		homeScreenButton.setOnAction(this::goHomeButtonClickListener);

		// create a grid pane to hold the labels and fields
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(25);
		gridPane.setVgap(10);
		gridPane.addRow(0,tenantID,tenantIDField);
		gridPane.addRow(1,propertyID,propertyIDField);
		gridPane.addRow(2,rentAmount,rentAmountField);


		// add labels and fields to grid pane

		// create submit button
		Button submitButton = new Button("Submit");

		// add button to grid pane
		gridPane.addRow(3, submitButton, homeScreenButton);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(25);
		gridPane.setVgap(10);
		root.getChildren().addAll(gridPane);
		root.setAlignment(Pos.TOP_CENTER);
		root.setSpacing(30);
		Scene addPaymentScene = new Scene(root);
		sController.addScene("Add Payment", addPaymentScene);
		sController.switchToScene("Add Payment");

		addPaymentScene.getStylesheets()
				.add(getClass().getResource("/main/styles/AddPaymentViewStyle.css").toExternalForm());
		// Adding CSS Link
		// Adding CSS Selectors
		root.getStyleClass().add("root-layout");
		submitButton.getStyleClass().add("submit-btn");
		homeScreenButton.getStyleClass().add("go-home-btn");
		GridPane.setMargin(homeScreenButton, new Insets(40, 0, 0, 0));
		GridPane.setMargin(submitButton, new Insets(40, 0, 0, 0));
		// addTenantScene.getStylesheets().add(getClass().getResource("/main/styles/AddApartmentViewStyle.css").toExternalForm());
		// Creating an Event Listener for the submit button
		submitButton.setOnAction(e -> {
			System.out.println("Submit of add payment clicked");
			int tID = Integer.parseInt(tenantIDField.getText());
			int pID = Integer.parseInt(propertyIDField.getText());
			int rAmount = Integer.parseInt(rentAmountField.getText());

			// Thread for Adding Payment
			Runnable r1 = new Runnable() {

				@Override
				public void run() {
					RentModelBuilder rBuilder = new RentModelBuilder();
					RentViewModelBuilder rViewBuilder = new RentViewModelBuilder();
					RentModel rModel = rBuilder.build();
					RentView rView = rViewBuilder.build();
					RentController rController = new RentController(rModel, rView);
					rController.addRentPayment(pID, tID, rAmount);
					
				}};
			Thread t1 = new Thread(r1);
			t1.start();
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Success");
			alert.setHeaderText("Rent Paid Sucessfully");
			alert.setContentText("Rent Paid for " + pID + " successfully");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				sController.switchToScene("Welcome Screen");
			}

			else if (result.get() == ButtonType.CANCEL) {
				sController.switchToScene("Welcome Screen");
			}
//			successMessage.setText("Rent paid successfully");
//			successMessage.setTextFill(Color.GREEN);
		});

	}
}