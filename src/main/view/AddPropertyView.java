package main.view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import main.util.SceneController;

public class AddPropertyView implements Runnable {

	@Override
	public void run() {
		System.out.println("Add Property View: " + Thread.currentThread().getName());
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				AddPropertyView aObj = new AddPropertyView();
				aObj.loadUI();

			}

		});
	}

	// Creating sceneController Object
	SceneController sceneCtrl = new SceneController();

	// Creating Button Event Listener
	public void goHomeButtonClickListener(Event event) {
		sceneCtrl.switchToScene("Welcome Screen");
	}

	public void addApartmentButtonClickListener(Event event) {
		AddApartmentView aView = new AddApartmentView();
		aView.loadUI();
	}

	public void addCondoButtonClickListener(Event event) {
		AddCondoView cView = new AddCondoView();
		cView.loadUI();
	}

	public void addHouseButtonClickListener(Event event) {
		AddHouseView hView = new AddHouseView();
		hView.loadUI();
	}

	// laodUI
	public void loadUI() {
		// Declaring FlowPane as root
		FlowPane root = new FlowPane();
		Button addApartmentButton = new Button("Add Apartment");
		Button addCondoButton = new Button("Add Condo");
		Button addHouseButton = new Button("Add House");
		Button homeScreenButton = new Button("Go Home Screen");

		// Invoking button event listeners method
		homeScreenButton.setOnAction((event) -> {
			this.goHomeButtonClickListener(event);
		});

		addApartmentButton.setOnAction((event) -> {
			this.addApartmentButtonClickListener(event);
		});

		addCondoButton.setOnAction((event) -> {
			this.addCondoButtonClickListener(event);
		});

		addHouseButton.setOnAction((event) -> {
			this.addHouseButtonClickListener(event);
		});

		HBox hPane = new HBox();
		hPane.getChildren().addAll(homeScreenButton);

		root.getChildren().addAll(addApartmentButton, addCondoButton, addHouseButton, hPane);
		root.setOrientation(Orientation.VERTICAL);
		root.setVgap(40);
		root.setAlignment(Pos.CENTER);
		Scene addPropertyScene = new Scene(root);
		// Adding CSS
		addPropertyScene.getStylesheets().add("/main/styles/AddPropertyViewStyle.css");
		sceneCtrl.addScene("Add Property", addPropertyScene);
		sceneCtrl.switchToScene("Add Property");

	}

}
