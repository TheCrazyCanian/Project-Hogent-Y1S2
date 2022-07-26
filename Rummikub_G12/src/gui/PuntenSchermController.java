package gui;

import java.io.IOException;
import java.util.List;

import domein.DomeinController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PuntenSchermController extends Pane{
	@FXML
	private Label lblWinnaar;
	@FXML
	private Label lblScores;
	@FXML
	private Label lblSpeler1;
	@FXML
	private Label lblSpeler2;
	@FXML
	private Label lblSpeler3;
	@FXML
	private Label lblSpeler4;
	@FXML
	private Button btnNieuwSpel;
	@FXML
	private Button btnAfsluiten;

	private DomeinController dc;
	private List<String> spelers;
	private List<Integer> scores;
	private AanmeldSchermController3 asc3;
	
	public PuntenSchermController(DomeinController dc, AanmeldSchermController3 asc3) {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("PuntenScherm.fxml"));
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
		
		scores = dc.geefScores();
        spelers = dc.geefNamenSpelers();
        updateScores();
        
        for (int i = 0; i < scores.size(); i++) {
        	for (int j = i + 1; j < scores.size(); j++) {
        		if (scores.get(j) > scores.get(i)) {
        			int temp1 = scores.get(j);
        			String temp2 = spelers.get(j);
        			scores.set(j, scores.get(i));
        			spelers.set(j, spelers.get(i));
        			scores.set(i, temp1);
        			spelers.set(i, temp2);
        		}
        	}
        }
        
        for (int i = 0; i < scores.size(); i++) {
        	for (int j = i + 1; j < scores.size(); j++) {
        		if (scores.get(i) == scores.get(j)) {
        			char[] naam1 = spelers.get(i).toCharArray();
        			char[] naam2 = spelers.get(j).toCharArray();
        			if (naam1[0] > naam2[0] || (naam1[0] > naam2[0] && naam1[1] > naam2[1])) {
        				int temp1 = scores.get(j);
            			String temp2 = spelers.get(j);
            			scores.set(j, scores.get(i));
            			spelers.set(j, spelers.get(i));
            			scores.set(i, temp1);
            			spelers.set(i, temp2);
        			}
        		}
        	}
        }
        
        if(isErEenWinnaar()) {
        	String s = String.format("");
        	s += dc.geefTekstTaal("winnaarText");
        	s += String.format("%s", spelers.get(0));
        	lblWinnaar.setText(s);
        }
        else {
        	lblWinnaar.setText(dc.geefTekstTaal("geenWinnaarText"));
        }
	
        if (spelers.size() >= 2) {
        	lblSpeler1.setText(String.format("%s %s %d", spelers.get(0), dc.geefTekstTaal("score"), scores.get(0)));
    		lblSpeler2.setText(String.format("%s %s %d", spelers.get(1), dc.geefTekstTaal("score"), scores.get(1)));
    		if (spelers.size() >= 3) {
    			lblSpeler3.setText(String.format("%s %s %d", spelers.get(2), dc.geefTekstTaal("score"), scores.get(2)));
    			if (spelers.size() >= 4) {
    				lblSpeler4.setText(String.format("%s %s %d", spelers.get(3), dc.geefTekstTaal("score"), scores.get(3)));
    			}
    		}
        }

		btnAfsluiten.setText(dc.geefTekstTaal("btnAfsluiten"));		
		btnAfsluiten.setOnAction( event -> Platform.exit());
		
		btnNieuwSpel.setText(dc.geefTekstTaal("spelStarten"));
		btnNieuwSpel.setOnAction(event -> {
			Stage stage = (Stage)(getScene().getWindow());
            stage.setScene(asc3.getScene());
		});
	}
	
	private void updateScores() {
		for(int i = 0; i < spelers.size(); i++) {
			dc.updateScoreVanSpeler(spelers.get(i), scores.get(i));
		}
	}

	private boolean isErEenWinnaar() {
		for (Integer i : scores) {
			if (i >= 0) {
				return true;
			}
		}
		return false;
	}
}
