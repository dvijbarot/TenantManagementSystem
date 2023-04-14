package main.view;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import main.controller.LeaseController;
// import main.model.ApartmentModel;
// import main.model.CondoModel;
// import main.model.HouseModel;
import main.model.LeaseModel;
import main.util.SceneController;
import main.util.builder.LeaseModelBuilder;
import main.util.builder.LeaseViewModelBuilder;

public class DisplayLeaseView implements Runnable {

	@Override
	public void run() {
		System.out.println("Display Lease View: " + Thread.currentThread().getName());
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				DisplayLeaseView displayLeaseViewObj = new DisplayLeaseView();
				displayLeaseViewObj.setLeaseData();
				displayLeaseViewObj.loadUI();
			}

		});
	}

	// Creating SceneController Class Object
	SceneController sceneCtrl = new SceneController();

	public void goHomeButtonClickListener(Event event) {
		sceneCtrl.switchToScene("Welcome Screen");
	}

	// Property Dummy Data

	static HashMap<Integer, LeaseModel> LeaseList = new HashMap<Integer, LeaseModel>();

	// Load Property Data
	void setLeaseData() {
		Runnable r1 = new Runnable() {
			LeaseModelBuilder lBuilder = new LeaseModelBuilder();
			LeaseViewModelBuilder lViewBuilder = new LeaseViewModelBuilder();
			LeaseModel lModel = lBuilder.build();
			LeaseView lView = lViewBuilder.build();
			LeaseController lController = new LeaseController(lModel, lView);
			@Override
			public void run() {
				DisplayLeaseView.LeaseList = lController.getLeases();
				
			}};
		
		Thread t1 = new Thread(r1, "Bussiness Logic Thread");
		t1.start();
	}

	public void loadUI() {

		Button homeScreenButton = new Button("Go Home Screen");
		homeScreenButton.setOnAction((event) -> {
			this.goHomeButtonClickListener(event);
		});
		if (LeaseList.size() == 0) {
			Text LeaseMessage = new Text("Sorry, there are no Leases in the system");
			GridPane root = new GridPane();
			// Create a StackPane and add the Text node to it
			StackPane stackPane = new StackPane(LeaseMessage);
			StackPane.setMargin(LeaseMessage, new Insets(100, 0, 50, 0));
			root.addRow(0, stackPane);
			root.addRow(1, homeScreenButton);
			root.setAlignment(Pos.TOP_CENTER);
			GridPane.setHalignment(homeScreenButton, javafx.geometry.HPos.CENTER);
			GridPane.setValignment(homeScreenButton, javafx.geometry.VPos.CENTER);
			Scene displayLeaseScene = new Scene(root);
			// Adding CSS Link
			LeaseMessage.getStyleClass().add("not-property-text");
			displayLeaseScene.getStylesheets()
					.add(getClass().getResource("/main/styles/DisplayPropertyViewStyle.css").toExternalForm());
			sceneCtrl.addScene("Display Lease", displayLeaseScene);
			sceneCtrl.switchToScene("Display Lease");

		} else {
			int row = 0, col = 0, i;
			Text propertyDisplayMessage = new Text("List of Leases are:");
			GridPane root = new GridPane();
			GridPane globalRoot = new GridPane();
			ScrollPane scrollPane = new ScrollPane(globalRoot);
			scrollPane.setFitToWidth(true);
			scrollPane.setFitToHeight(true);

			root.setAlignment(Pos.CENTER);
			root.setHgap(15);

			for (Map.Entry<Integer, LeaseModel> entry : LeaseList.entrySet()) {
				int key = entry.getKey();
				LeaseModel Lease = entry.getValue();
				BorderPane card = new BorderPane();
				card.getStyleClass().add("card-view");
				card.setPadding(new Insets(10));

				FlowPane fPane = new FlowPane();
				Label LeaseID = new Label("Lease ID : - " + String.valueOf(Lease.getLeaseID()));
				Label Name = new Label("Tenant Name : - " + Lease.getTenant().getName());
				Label StartDate = new Label("Lease Start Date: - " + Lease.getStartDate());
				Label EndDate = new Label("Lease End Date: - " + Lease.getEndDate());
				Label PropertyID = new Label("Property ID: - " + Lease.getProperty().getPropertyID());
				fPane.getChildren().addAll(LeaseID, PropertyID, Name, StartDate, EndDate);
				fPane.setMinHeight(200);
				fPane.setMaxHeight(200);
				fPane.setMinWidth(250);
				fPane.setMaxWidth(250);
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
			globalRoot.addRow(0, root);
			globalRoot.addRow(1, homeScreenButton);
			globalRoot.setAlignment(Pos.CENTER);
			GridPane.setHalignment(homeScreenButton, javafx.geometry.HPos.CENTER);
			GridPane.setValignment(homeScreenButton, javafx.geometry.VPos.CENTER);
			Scene displayLeaseScene = new Scene(scrollPane);
			sceneCtrl.addScene("Display Lease", displayLeaseScene);
			sceneCtrl.switchToScene("Display Lease");
			// Adding CSS Link
			// Adding CSS
			displayLeaseScene.getStylesheets()
					.add(getClass().getResource("/main/styles/DisplayPropertyViewStyle.css").toExternalForm());

		}
	}
}