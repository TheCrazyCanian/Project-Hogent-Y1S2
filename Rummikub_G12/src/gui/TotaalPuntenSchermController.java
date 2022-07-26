package gui;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import domein.DomeinController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TotaalPuntenSchermController extends Pane {
	@FXML
	private Label lblScores;
	@FXML
	private Button btnTerug;
	@FXML
	private Label lblSpeler1;
	@FXML
	private Label lblSpeler2;
	@FXML
	private Label lblSpeler3;
	@FXML
	private Label lblSpeler4;
	
	private DomeinController dc;
	private AanmeldSchermController3 asc3;

	public TotaalPuntenSchermController(DomeinController dc, AanmeldSchermController3 asc3) {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("TotaalPuntenScherm.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		try {
			loader.load();
		} 
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		this.dc = dc;
		this.asc3 = asc3;
		
		buildGui();
		
	}
	
	private void buildGui() {
		scoresToevoegenAanLabels();
		
		btnTerug.setText(dc.geefTekstTaal("terug"));
		btnTerug.setOnAction(event -> {
			Stage stage = (Stage)(getScene().getWindow());
            stage.setScene(asc3.getScene());
		});
	}

	private void scoresToevoegenAanLabels() {
		List<String> gebruikersnamenEnScore = dc.geefNamenEnScoresDatabank();
		if (dc.geefGebruikersnamen().size() >= 2) {
        	lblSpeler1.setText(String.format("%s %s %d", gebruikersnamenEnScore.get(0), dc.geefTekstTaal("score"), Integer.parseInt(gebruikersnamenEnScore.get(1))));
    		lblSpeler2.setText(String.format("%s %s %d", gebruikersnamenEnScore.get(2), dc.geefTekstTaal("score"), Integer.parseInt(gebruikersnamenEnScore.get(3))));
    		if (dc.geefGebruikersnamen().size() >= 3) {
    			lblSpeler3.setText(String.format("%s %s %d", gebruikersnamenEnScore.get(4), dc.geefTekstTaal("score"), Integer.parseInt(gebruikersnamenEnScore.get(5))));
    			if (dc.geefGebruikersnamen().size() >= 4) {
    				lblSpeler4.setText(String.format("%s %s %d", gebruikersnamenEnScore.get(6), dc.geefTekstTaal("score"), Integer.parseInt(gebruikersnamenEnScore.get(7))));
    			}
    		}
        }
	}

}
