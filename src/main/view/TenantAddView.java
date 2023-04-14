package main.view;

import java.util.Optional;
import java.util.regex.Pattern;

import javafx.application.Platform;
import javafx.event.Event;
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
import javafx.scene.paint.Color;
import main.controller.TenantController;
import main.model.TenantModel;
import main.util.SceneController;
import main.util.builder.TenantModelBuilder;

public class TenantAddView implements Runnable {
	// Creating a Scene Controller
	SceneController sController = new SceneController();

	public void goHomeButtonClickListener(Event event) {
		sController.switchToScene("Welcome Screen");
	}

	public void loadUI() {
		// Creating Layout
		VBox root = new VBox();
		Label tenantID = new Label("Tenant ID:");
		TextField tenantIDField = new TextField();
		Label tenantFirstName = new Label("First Name:");
		TextField tenantFirstNameField = new TextField();
		Label tenantLastName = new Label("Last Name:");
		TextField tenantLastNameField = new TextField();
		Label tenantEmail = new Label("Email:");
		TextField tenantEmailField = new TextField();
		Label successMessage = new Label();

		// create a grid pane to hold the labels and fields
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);

		Button submitButton = new Button("Submit");
		Button homeScreenButton = new Button("Go Home Screen");
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(25);
		gridPane.setVgap(10);
		gridPane.add(tenantID, 0, 0);
		gridPane.add(tenantIDField, 1, 0);
		gridPane.add(tenantFirstName, 0, 2);
		gridPane.add(tenantFirstNameField, 1, 2);
		gridPane.add(tenantLastName, 0, 4);
		gridPane.add(tenantLastNameField, 1, 4);
		gridPane.add(tenantEmail, 0, 6);
		gridPane.add(tenantEmailField, 1, 6);
		gridPane.add(successMessage, 4, 6);
		gridPane.addRow(7, submitButton, homeScreenButton);

		// add labels and fields to grid pane

		// add button to grid pane

		// Adding components in root layout
		root.getChildren().addAll(gridPane);
		root.setAlignment(Pos.TOP_CENTER);
		root.setSpacing(30);

		GridPane.setMargin(homeScreenButton, new Insets(40, 0, 0, 0));
		GridPane.setMargin(submitButton, new Insets(40, 0, 0, 0));

		

		homeScreenButton.setOnAction((event) -> {
			this.goHomeButtonClickListener(event);
		});

		// Adding CSS Link
		homeScreenButton.getStyleClass().add("go-home-btn");
		root.getStyleClass().add("root-layout");
		Scene addTenantScene = new Scene(root);
		addTenantScene.getStylesheets()
				.add(getClass().getResource("/main/styles/AddTenantViewStyle.css").toExternalForm());
		submitButton.getStyleClass().add("submit-btn");
		sController.addScene("Add Tenant", addTenantScene);
		sController.switchToScene("Add Tenant");
	
		// Creating an Event Listener for the submit button
		submitButton.setOnAction(e -> {
			System.out.println("Submit of add tenant clicked");
			int tID = Integer.parseInt(tenantIDField.getText());
			String tFName = tenantFirstNameField.getText();
			String tLName = tenantLastNameField.getText();
			String tEmail = tenantEmailField.getText();
			String emailRegex = "^(.+)@(.+)$";
			Pattern pat = Pattern.compile(emailRegex);
			if (tEmail == null || !pat.matcher(tEmail).matches()) {
				System.out.println("In if add tenant");
				successMessage.setText("Invalid Email...");
				successMessage.setTextFill(Color.RED);
				tenantEmailField.setText("");
			} else {
				// Creating Thread for Saving Tenant
				Runnable r1 = new Runnable() {

					@Override
					public void run() {
						TenantModelBuilder tBuilder = new TenantModelBuilder();
						TenantModel tModel = tBuilder.setTenantID(tID).setFirstName(tFName).setLastName(tLName).setEmail(tEmail)
								.build();
						TenantController tController = new TenantController();
						tController.saveTenantObject(tID, tModel);
						
					}};
				Thread t1 = new Thread(r1, "Business Logic Thread");
			    t1.start();

				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Success");
				alert.setHeaderText("Tenant Added Sucessfully");
				alert.setContentText("Tenant of ID " + tID + " is added successfully");
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
					sController.switchToScene("Welcome Screen");
				}

				else if (result.get() == ButtonType.CANCEL) {
					sController.switchToScene("Welcome Screen");
				}
			}

		});

	}

	@Override
	public void run() {
		System.out.println("Tenant Add View: " + Thread.currentThread().getName());
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				TenantAddView tenantViewObj = new TenantAddView();
				tenantViewObj.loadUI();

			}

		});
	}
}