package gui;

import java.io.IOException;

import domein.DomeinController;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class WelkomSchermController extends Pane {
	
	@FXML
	private ImageView imgBelgium;
	@FXML
	private ImageView imgUK;
	@FXML
	private Label lblTekst;
	@FXML
	private Label lblRummikub;
	
	private DomeinController dc;
	
	public WelkomSchermController(DomeinController dc) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("WelkomScherm.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		try {
			loader.load();
		} 
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		this.dc = dc;
		buildGui();
	}
	
	private void buildGui() {
		
		dc.stelTaalIn("Nederlands");
		imgBelgium.setOnMouseClicked(this::keuzeNederlands);
		imgUK.setOnMouseClicked(this::keuzeEngels);
		
		FadeTransition ft = new FadeTransition(Duration.millis(2000), lblTekst);
		ft.setFromValue(0.2);
		ft.setToValue(0.85);
		ft.setCycleCount(999999999);
		ft.setAutoReverse(true);
		ft.play();
		
		lblRummikub.setOnMouseClicked(this::startTitelAangeklikt);
	}
	
	private void keuzeNederlands(MouseEvent event) {
		lblTekst.setText("Klik op Rummikub om te beginnen");
		dc.stelTaalIn("Nederlands");
	}
	
	private void keuzeEngels(MouseEvent event) {
		lblTekst.setText("       Click Rummikub to start");
		dc.stelTaalIn("Engels");
	}
	
	private void startTitelAangeklikt(MouseEvent event) {
		AanmeldSchermController1 as = new AanmeldSchermController1(dc);
		Scene scene = new Scene(as);
		scene.getStylesheets().add(getClass().getResource("/gui/AanmeldScherm1.css").toExternalForm());
		Stage stage = (Stage) getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	
	
	
}
