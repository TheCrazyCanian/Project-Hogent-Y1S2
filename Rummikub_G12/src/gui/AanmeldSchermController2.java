package gui;

import java.io.IOException;

import domein.DomeinController;
import exceptions.AanmeldenException;
import exceptions.LeegVeldException;
import exceptions.SpelerBestaatAlException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AanmeldSchermController2 extends Pane {
	@FXML
	private Label lblInfo;
	@FXML
	private TextField txfGebruikersnaam;
	@FXML
	private PasswordField pwfWachtwoord;
	@FXML
	private Label lblGebruikersnaam;
	@FXML
	private Label lblWachtwoord;
	@FXML
	private Button btnAanmelden;
	
	private DomeinController dc;
	private int aantalSpelers;
	private int aantalAangemeld;
	private Alert alert;
	
	public AanmeldSchermController2(DomeinController dc, int aantalSpelers) {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AanmeldScherm2.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		try {
			loader.load();
		} 
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		this.dc = dc;
		this.aantalSpelers = aantalSpelers;
		buildGui();	
	}
	
	private void buildGui() {
		
		lblInfo.setText(dc.geefTekstTaal("voerIn"));
		lblGebruikersnaam.setText(dc.geefTekstTaal("gebruikersnaam"));
		lblWachtwoord.setText(dc.geefTekstTaal("wachtwoord"));
		btnAanmelden.setText(dc.geefTekstTaal("aanmelden"));
		
		txfGebruikersnaam.requestFocus();
		
		alert = new Alert(Alert.AlertType.WARNING);
		alert.setHeaderText(null);
		
		btnAanmelden.setOnAction(this::klikOpAanmeldKnop);
	}
	
	private void klikOpAanmeldKnop(ActionEvent event) {
		
		try {
			dc.meldAan(txfGebruikersnaam.getText(), pwfWachtwoord.getText());
			
			aantalAangemeld++;
			
			if (aantalAangemeld == aantalSpelers) {
				AanmeldSchermController3 as = new AanmeldSchermController3(dc);
				Scene scene = new Scene(as);
				Stage stage = (Stage) getScene().getWindow();
				stage.setScene(scene);
				stage.show();
			}
			
			char[] info = lblInfo.getText().toCharArray();
			
			for (int i = 0; i < info.length; i++) {
				if (Character.isDigit(info[i])) {
					info[i] += 1;
				}
			}
			
			lblInfo.setText(new String(info));
			
			txfGebruikersnaam.clear();
			txfGebruikersnaam.requestFocus();
			pwfWachtwoord.clear();
			
		}
		catch (AanmeldenException e) {
			alert.setTitle(dc.geefTekstTaal("aanmeldFout"));
			alert.setContentText(dc.geefTekstTaal("aanmeldenException"));
			alert.showAndWait();
			
		}
		catch (LeegVeldException e) {
			alert.setTitle(dc.geefTekstTaal("leegVeldWaarschuwing"));
			alert.setContentText(dc.geefTekstTaal("leegVeldAanmelden"));
			alert.showAndWait();
		}
		catch (SpelerBestaatAlException e) {
			alert.setTitle(dc.geefTekstTaal("reedsAangemeld"));
			alert.setContentText(dc.geefTekstTaal(e.getMessage()));
			alert.showAndWait();
		}
		
		txfGebruikersnaam.clear();
		txfGebruikersnaam.requestFocus();
		pwfWachtwoord.clear();
	}
}
