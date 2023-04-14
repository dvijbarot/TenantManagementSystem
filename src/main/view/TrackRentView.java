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
import main.model.RentModel;
import main.util.SceneController;

public class TrackRentView implements Runnable {
	String rentStatus;
// Creating SceneController object to handle scene switching
	private final SceneController sceneController = new SceneController();

//Event listener for the "Go Home Screen" button
	private void goHomeButtonClickListener(ActionEvent event) {
		sceneController.switchToScene("Welcome Screen");
	}

//Load the user interface for tracking rent payments
	public void loadUI() {
		// Create the layout
		VBox root = new VBox();
		Label titleLabel = new Label("Track Rent Payment Data");
		GridPane gridPane = new GridPane();

		// Create labels for the UI
		Label propertyIDLabel = new Label("Enter Property ID");
		Label successMessage = new Label();

		// Create a text field for the user to input the property ID
		TextField propertyIdValue = new TextField();

		// Create buttons for the UI
		Button homeScreenButton = new Button("Go Home Screen");
		Button submitButton = new Button("Submit");
		GridPane.setMargin(homeScreenButton, new Insets(40, 0, 0, 0));
		GridPane.setMargin(submitButton, new Insets(40, 0, 0, 0));

		// Set event listeners for the buttons
		homeScreenButton.setOnAction(this::goHomeButtonClickListener);
		submitButton.setOnAction(e -> {
			// Get the property ID from the text field
			int propertyID = Integer.parseInt(propertyIdValue.getText());

			// Creating Thread for Tracking Rent
			Runnable r1 = new Runnable() {

				@Override
				public void run() {
					RentModel rentModel = new RentModel();
					 rentStatus = rentModel.checkRentStatus(propertyID);
				}};
			Thread t1 = new Thread(r1, "Business Logic Thread");
			t1.start();

			// Display the rent payment information in the successMessage label
//			successMessage.setText(rentStatus);
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Track Rent");
			alert.setHeaderText("Rent Payment Status");
			alert.setContentText(rentStatus);
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				sceneController.switchToScene("Welcome Screen");
			}

			else if (result.get() == ButtonType.CANCEL) {
				sceneController.switchToScene("Welcome Screen");
			}
		});

		// Add UI elements to the grid pane
		gridPane.addRow(0, propertyIDLabel, propertyIdValue);
		//gridPane.add(successMessage, 1, 3);
		gridPane.addRow(1, submitButton, homeScreenButton);

		// Set alignment and spacing for the grid pane
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(25);
		gridPane.setVgap(10);
		root.getChildren().addAll(gridPane);
		root.setAlignment(Pos.TOP_CENTER);
		root.setSpacing(30);

		// Create the scene and switch to it
		Scene trackRentScene = new Scene(root);
		sceneController.addScene("Track Rent Payment", trackRentScene);
		sceneController.switchToScene("Track Rent Payment");

		// Add a CSS style sheet to the scene
		trackRentScene.getStylesheets()
				.add(getClass().getResource("/main/styles/TrackRentViewStyle.css").toExternalForm());
		// Adding CSS Selectors
//		root.getStyleClass().add("root-layout");
		root.getStyleClass().add("root-layout");
		submitButton.getStyleClass().add("submit-btn");
		homeScreenButton.getStyleClass().add("go-home-btn");
	}

	@Override
	public void run() {
		System.out.println("Track Payment View: " + Thread.currentThread().getName());
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				TrackRentView trackRentViewObj = new TrackRentView();
				trackRentViewObj.loadUI();

			}

		});

	}
}