package main;

import domein.DomeinController;
import gui.WelkomSchermController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class StartUp extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			WelkomSchermController root = new WelkomSchermController(new DomeinController());
	        Scene scene = new Scene(root);
	        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/Icon.png")));
			primaryStage.setScene(scene);
			primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
	            if (KeyCode.F11.equals(event.getCode())) {
	                primaryStage.setFullScreen(!primaryStage.isFullScreen());
	            }
	        });
			primaryStage.setTitle("Rummikub");
			primaryStage.show();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
