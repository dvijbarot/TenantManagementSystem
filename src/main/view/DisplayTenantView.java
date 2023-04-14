package main.view;

import java.util.LinkedHashMap;
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
import main.controller.TenantController;
// import main.model.ApartmentModel;
// import main.model.CondoModel;
// import main.model.HouseModel;
import main.model.TenantModel;
import main.util.SceneController;
import main.util.builder.TenantModelBuilder;
import main.util.builder.TenantViewModelBuilder;

public class DisplayTenantView implements Runnable {
	@Override
	public void run() {
		System.out.println("Display Tenant View: " + Thread.currentThread().getName());
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				DisplayTenantView displayTenantViewObj = new DisplayTenantView();
				displayTenantViewObj.setTenantData();
				displayTenantViewObj.loadUI();
			}

		});
	}

	// Creating SceneController Class Object
	SceneController sceneCtrl = new SceneController();

	public void goHomeButtonClickListener(Event event) {
		sceneCtrl.switchToScene("Welcome Screen");
	}

	// Property Dummy Data

	static LinkedHashMap<Integer, TenantModel> tenantList = new LinkedHashMap<Integer, TenantModel>();

	// Load Property Data
	void setTenantData() {
		Runnable r1 = new Runnable() {
			TenantModelBuilder tBuilder = new TenantModelBuilder();
			TenantViewModelBuilder tViewBuilder = new TenantViewModelBuilder();
			TenantModel tModel = tBuilder.build();
			TenantView tView = tViewBuilder.build();
			TenantController tController = new TenantController(tModel, tView);
			@Override
			public void run() {
				DisplayTenantView.tenantList = tController.displayTenantController();
				
			}};
		Thread t1 = new Thread(r1, "Business Logic Thread");
		t1.start();
		
	}

	public void loadUI() {
		Button homeScreenButton = new Button("Go Home Screen");
		homeScreenButton.setOnAction((event) -> {
			this.goHomeButtonClickListener(event);
		});
		if (tenantList.size() == 0) {
			Text TenantMessage = new Text("Sorry, there are no Tenants in the system");

			GridPane root = new GridPane();

			StackPane stackPane = new StackPane(TenantMessage);
			StackPane.setMargin(TenantMessage, new Insets(100, 0, 50, 0));
			root.addRow(0, stackPane);
			root.addRow(1, homeScreenButton);
			root.setAlignment(Pos.TOP_CENTER);
			GridPane.setHalignment(homeScreenButton, javafx.geometry.HPos.CENTER);
			GridPane.setValignment(homeScreenButton, javafx.geometry.VPos.CENTER);
			Scene displayTenantScene = new Scene(root);
			// Adding CSS Link
			TenantMessage.getStyleClass().add("not-property-text");
			displayTenantScene.getStylesheets()
			.add(getClass().getResource("/main/styles/DisplayPropertyViewStyle.css").toExternalForm());
			sceneCtrl.addScene("Display Tenant", displayTenantScene);
			sceneCtrl.switchToScene("Display Tenant");

		} else {
			int row = 0, col = 0, i;
			Text propertyDisplayMessage = new Text("List of Tenants are:");
			GridPane globalRoot = new GridPane();
			GridPane root = new GridPane();
			ScrollPane scrollPane = new ScrollPane(globalRoot);
			scrollPane.setFitToWidth(true);
			scrollPane.setFitToHeight(true);

			root.setAlignment(Pos.CENTER);
			root.setHgap(15);

			for (Map.Entry<Integer, TenantModel> entry : tenantList.entrySet()) {
				int key = entry.getKey();
				TenantModel tenant = entry.getValue();
				BorderPane card = new BorderPane();
				card.getStyleClass().add("card-view");
				card.setPadding(new Insets(10));

				FlowPane fPane = new FlowPane();
				Label tenantID = new Label("Tenant ID : - " + String.valueOf(tenant.getTenantId()));
				Label firstName = new Label("Tenant First Name : - " + tenant.getTenantFirstName());
				Label lastName = new Label("Tenant Last Name: - " + tenant.getTenantLastName());
				Label eMail = new Label("Tenant Email: - " + tenant.getTenantEmail());
				fPane.getChildren().addAll(tenantID, firstName, lastName, eMail);
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
			Scene displayTenantScene = new Scene(scrollPane);
			displayTenantScene.getStylesheets()
			.add(getClass().getResource("/main/styles/DisplayPropertyViewStyle.css").toExternalForm());
			sceneCtrl.addScene("Display Tenant", displayTenantScene);
			sceneCtrl.switchToScene("Display Tenant");

		}
	}
}