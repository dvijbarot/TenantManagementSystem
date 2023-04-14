package main.util;

import java.util.HashMap;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {
	// Make 'stage' variable private since it's not being used outside the class
	private static Stage stage;

	// Use diamond operator to reduce code verbosity
	public static HashMap<String, Scene> scenes = new HashMap<>();

	// Empty constructor is not needed
	public SceneController() {
	}

	// Use 'this' keyword to avoid static reference to instance field 'stage'
	public SceneController(Stage stage) {
		this.stage = stage;
	}

	// Use a more descriptive method name
	public void addScene(String name, Scene scene) {
		scenes.put(name, scene);
	}

	// Add comments to explain the method
	public void switchToScene(String name) {
		// Retrieve the scene from the HashMap using the specified name
		Scene scene = scenes.get(name);
		// If the scene doesn't exist, throw an IllegalArgumentException
		if (scene == null) {
			throw new IllegalArgumentException("Scene '" + name + "' does not exist");
		}
		// Set the stage's scene to the retrieved scene
		stage.setScene(scene);
		// Set the stage's title
		stage.setTitle("Tenant Management Application");
	;
		//stage.setMaximized(true);
	    stage.setWidth(950);
		stage.setHeight(800);
		// Make the stage visible
		stage.show();
	}
}
