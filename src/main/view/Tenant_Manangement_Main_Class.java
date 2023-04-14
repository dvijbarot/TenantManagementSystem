package main.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import main.util.SceneController;

public class Tenant_Manangement_Main_Class extends Application {
	static Thread uiThread;
	static Thread displayThread;

	// method to check date is valid or not
	static String checkDateValidity(String d) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
			format.setLenient(false); // will set the date entered by the user in the format (dd/MM/yyyy)
			Date userEnteredDate = format.parse(d);
			return "Valid";

		} catch (ParseException e) {
			return "Invalid";
		}
	}

	// Creating Button Event Listener
	public static void addPropertyButtonClickListener(Event event) {
		AddPropertyView addPropertyView = new AddPropertyView();
		try {
			uiThread = new Thread(addPropertyView, "UI Thread");
			uiThread.start();
		} catch (Exception e) {
			System.out.println("Exception is: " + e);
		}

	}

	public static void rentUnitButtonClickListener(Event event) {
		RentUnitView rentUnitView = new RentUnitView();
		try {
			uiThread = new Thread(rentUnitView, "UI Thread");
			uiThread.start();
		} catch (Exception e) {
			System.out.println("Exception is: " + e);
		}
	}

	public static void cancelLeaseButtonClickListener(Event event) {
		CancelLeaseView cancelLeaseView = new CancelLeaseView("Cancel Lease");
		try {
			uiThread = new Thread(cancelLeaseView, "UI Thread");
			uiThread.start();
		} catch (Exception e) {
			System.out.println("Exception is: " + e);
		}
	}

	public static void updateLeaseButtonClickListener(Event event) {
		CancelLeaseView updateLeaseView = new CancelLeaseView("Update Lease");
		try {
			uiThread = new Thread(updateLeaseView, "UI Thread");
			uiThread.start();
		} catch (Exception e) {
			System.out.println("Exception is: " + e);
		}
	}

	public static void displayPropertyButtonClickListener(Event event) {
		try {
			DisplayPropertyView displayPropertyView = new DisplayPropertyView("All Property");
			displayThread = new Thread(displayPropertyView, "Display Thread");
			displayThread.start();
		} catch (Exception e) {
			System.out.println("Exception is: " + e);
		}

	}

	public static void displayRentedPropertyButtonClickListener(Event event) {
		try {
			DisplayPropertyView displayRentPropertyView = new DisplayPropertyView("Rented Property");
			displayThread = new Thread(displayRentPropertyView, "Display Thread");
			displayThread.start();
		} catch (Exception e) {
			System.out.println("Exception is: " + e);
		}

	}

	public static void displayVacantPropertyButtonClickListener(Event event) {

		try {
			DisplayPropertyView displayVacantPropertyView = new DisplayPropertyView("Vacant Property");
			displayThread = new Thread(displayVacantPropertyView, "Display Thread");
			displayThread.start();
		} catch (Exception e) {
			System.out.println("Exception is: " + e);
		}

	}

	public static void addTenantButtonClickListener(Event event) {
		TenantAddView addTenantView = new TenantAddView();
		try {
			uiThread = new Thread(addTenantView, "UI Thread");
			uiThread.start();
		} catch (Exception e) {
			System.out.println("Exception is: " + e);
		}

	}

	public static void addRentButtonClickListener(Event event) {
		AddPaymentView addRentView = new AddPaymentView();
		try {
			uiThread = new Thread(addRentView, "UI Thread");
			uiThread.start();
		} catch (Exception e) {
			System.out.println("Exception is: " + e);
		}
	}

	public static void displayTenantButtonClickListener(Event event) {

		DisplayTenantView displayTenantView = new DisplayTenantView();
		try {
			uiThread = new Thread(displayTenantView, "UI Thread");
			uiThread.start();
		} catch (Exception e) {
			System.out.println("Exception is: " + e);
		}
	}

	public static void trackRentButtonClickListener(Event event) {
		TrackRentView trackRentView = new TrackRentView();
		try {
			uiThread = new Thread(trackRentView, "UI Thread");
			uiThread.start();
		} catch (Exception e) {
			System.out.println("Exception is: " + e);
		}
	}

	public static void trackLeaseButtonClickListener(Event event) {
		TrackLeaseView trackLeaseView = new TrackLeaseView();
		try {
			uiThread = new Thread(trackLeaseView, "UI Thread");
			uiThread.start();
		} catch (Exception e) {
			System.out.println("Exception is: " + e);
		}
	}

	public static void displayLeaseButtonClickListener(Event event) {
		DisplayLeaseView displayLeaseView = new DisplayLeaseView();
		try {
			uiThread = new Thread(displayLeaseView, "UI Thread");
			uiThread.start();
		} catch (Exception e) {
			System.out.println("Exception is: " + e);
		}

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		// Creating SceneController Class Object
		SceneController sceneCtrl = new SceneController(primaryStage);
		FlowPane root = new FlowPane();
		Label titleLabel = new Label("Welcome to Tenant Management Application");
		Button addPropertyBtn = new Button("Add Property");
		Button addTenantBtn = new Button("Add Tenant");
		Button rentUnitBtn = new Button("Rent Unit");
		Button displayPropertyBtn = new Button("Display Property");
		Button displayTenantBtn = new Button("Display Tenant");
		Button displayRentedUnitsBtn = new Button("Display Rented Units");
		Button displayVacantUnitsBtn = new Button("Display Vacant Units");
		Button displayLeaseBtn = new Button("Display All Leases");
		Button trackLeaseBtn = new Button("Track Leases");
		Button addRentBtn = new Button("Record Payment");
		Button trackRentBtn = new Button("Track Payment");
		Button cancelLeaseBtn = new Button("Cancel Lease");
		Button updateRenovationStatusBtn = new Button("Update Renovation");
		Button exitButton = new Button("Exit");
		// Invoking button event listeners method
		addPropertyBtn.setOnAction((event) -> {
			Tenant_Manangement_Main_Class.addPropertyButtonClickListener(event);

		});
		rentUnitBtn.setOnAction((event) -> {
			Tenant_Manangement_Main_Class.rentUnitButtonClickListener(event);

		});

		cancelLeaseBtn.setOnAction((event) -> {
			Tenant_Manangement_Main_Class.cancelLeaseButtonClickListener(event);
		});
		// create another executer
		displayPropertyBtn.setOnAction((event) -> {
			Tenant_Manangement_Main_Class.displayPropertyButtonClickListener(event);

		});

		displayRentedUnitsBtn.setOnAction((event) -> {
			Tenant_Manangement_Main_Class.displayRentedPropertyButtonClickListener(event);
		});

		displayVacantUnitsBtn.setOnAction((event) -> {
			Tenant_Manangement_Main_Class.displayVacantPropertyButtonClickListener(event);
		});

		updateRenovationStatusBtn.setOnAction((event) -> {
			Tenant_Manangement_Main_Class.updateLeaseButtonClickListener(event);
		});

		addTenantBtn.setOnAction((event) -> {
			Tenant_Manangement_Main_Class.addTenantButtonClickListener(event);
		});

		addRentBtn.setOnAction((event) -> {
			Tenant_Manangement_Main_Class.addRentButtonClickListener(event);
		});

		displayTenantBtn.setOnAction((event) -> {

			Tenant_Manangement_Main_Class.displayTenantButtonClickListener(event);
		});

		trackRentBtn.setOnAction((event) -> {
			Tenant_Manangement_Main_Class.trackRentButtonClickListener(event);
		});

		trackLeaseBtn.setOnAction((event) -> {
			Tenant_Manangement_Main_Class.trackLeaseButtonClickListener(event);
		});

		displayLeaseBtn.setOnAction((event) -> {
			Tenant_Manangement_Main_Class.displayLeaseButtonClickListener(event);
		});

		exitButton.setOnAction(e -> System.exit(0));

		// Creating GridPane
		GridPane gPaneRoot = new GridPane();
		gPaneRoot.addRow(0, addPropertyBtn, addTenantBtn, rentUnitBtn);
		gPaneRoot.addRow(1, displayPropertyBtn, displayTenantBtn, displayRentedUnitsBtn);
		gPaneRoot.addRow(2, displayVacantUnitsBtn, displayLeaseBtn, trackLeaseBtn);
		gPaneRoot.addRow(3, addRentBtn, trackRentBtn, cancelLeaseBtn);
		gPaneRoot.addRow(4, updateRenovationStatusBtn, exitButton);
		gPaneRoot.setHgap(15);
		gPaneRoot.setVgap(20);
		gPaneRoot.setAlignment(Pos.CENTER);
		root.getChildren().add(titleLabel);
		root.getChildren().add(gPaneRoot);
		root.setOrientation(Orientation.VERTICAL);
		root.setVgap(60);
		root.setAlignment(Pos.TOP_CENTER);
		// Adding class value to apply CSS
		root.getStyleClass().add("root-layout");
		titleLabel.getStyleClass().add("application-title");

		// Creating Scene
		Scene welcomeScreen = new Scene(root);

		// Adding CSS File
		welcomeScreen.getStylesheets()
				.add(getClass().getResource("/main/styles/Tenant_Management_Main_Class_Style.css").toExternalForm());

		// Invoking Screen
		sceneCtrl.addScene("Welcome Screen", welcomeScreen);
		sceneCtrl.switchToScene("Welcome Screen");

	}

	public static void main(String[] args) {
		launch();
	}

}
