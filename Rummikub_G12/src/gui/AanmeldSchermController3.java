package gui;

import java.io.IOException;
import java.util.List;

import domein.DomeinController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AanmeldSchermController3 extends Pane {
	@FXML
	private Label lblAangemeld;
	@FXML
	private Label lblNamen;
	@FXML
	private Button btnSpeel;
	@FXML
	private Button btnOverzicht;
	@FXML
	private Button btnAfsluiten;

	private DomeinController dc;
	
	public AanmeldSchermController3(DomeinController dc) {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AanmeldScherm3.fxml"));
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
	
	public void buildGui() {
		
		lblAangemeld.setText(dc.geefTekstTaal("aangemeldeSpelers"));
		
		List<String> aangemeldeSpelers = dc.geefGebruikersnamen();
		
		
		String namen = String.format("");
		
		for (int i = 0; i < aangemeldeSpelers.size(); i++) {
			if (i == aangemeldeSpelers.size() - 1) {
				namen += aangemeldeSpelers.get(i);
			}
			else {
				namen += String.format("%s, ", aangemeldeSpelers.get(i));
			}
		}
		
		lblNamen.setText(namen);
		
		btnSpeel.setText(dc.geefTekstTaal("speel"));
		btnOverzicht.setText(dc.geefTekstTaal("overzicht"));
		btnAfsluiten.setText(dc.geefTekstTaal("btnAfsluiten"));
		
		btnSpeel.setOnAction(this::klikOpSpeelKnop);
		btnOverzicht.setOnAction(this::klikOpOverzichtKnop);
		btnAfsluiten.setOnAction(event -> Platform.exit());
		
	}
	
	private void klikOpSpeelKnop(ActionEvent event) {
		dc.startNieuwSpel();
		SpelbordSchermController ss = new SpelbordSchermController(dc, this);
		Scene scene = new Scene(ss);
		scene.getStylesheets().add(getClass().getResource("/gui/SpelbordScherm.css").toExternalForm());
		Stage stage = (Stage) getScene().getWindow();
		stage.setScene(scene);
		stage.setFullScreen(true);
		stage.show();
	}
	
	private void klikOpOverzichtKnop(ActionEvent event) {
		TotaalPuntenSchermController ttps = new TotaalPuntenSchermController(dc, this);
		Scene scene = new Scene(ttps);
		Stage stage = (Stage) getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
}
