package gui;

import java.io.IOException;

import domein.DomeinController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AanmeldSchermController1 extends Pane {
	@FXML
	private Label lblVraag;
	@FXML
	private Label lblAantal;
	@FXML
	private Slider sldAantal;
	@FXML
	private Button btnBevestig;
	
	private DomeinController dc;
	
	public AanmeldSchermController1(DomeinController dc) {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AanmeldScherm1.fxml"));
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
		
		lblVraag.setText(dc.geefTekstTaal("aantalSpelers"));
		lblAantal.setText("2");
		
		sldAantal.setMin(2);
		sldAantal.setMax(4);
		sldAantal.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
				lblAantal.setText(String.format("%.0f", newValue));
			}
		});
		
		btnBevestig.setText(dc.geefTekstTaal("bevestig"));
		
		btnBevestig.setOnAction(this::klikOpBevestigKnop);
	}
	
	private void klikOpBevestigKnop(ActionEvent event)  {
		int aantalSpelers = Integer.parseInt(lblAantal.getText());
		dc.registreerAantal(aantalSpelers);
		AanmeldSchermController2 as = new AanmeldSchermController2(dc, aantalSpelers);
		Scene scene = new Scene(as);
		Stage stage = (Stage) getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
}
