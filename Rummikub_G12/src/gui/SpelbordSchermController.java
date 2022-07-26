package gui;

import java.io.IOException;
import java.util.InputMismatchException;

import domein.DomeinController;
import exceptions.BezetVakjeException;
import exceptions.GeenEigenStenenGebruiktEersteBeurtException;
import exceptions.GeenPlaatsSpitsException;
import exceptions.GeenStenenInPotMeerException;
import exceptions.JokerEersteBeurtException;
import exceptions.LeegVeldException;
import exceptions.OngeldigGemeenschappelijkVeldException;
import exceptions.SplitsExceptie;
import exceptions.TeWeinigPuntenEersteBeurtException;
import exceptions.WerkveldNietLeegException;
import javafx.animation.PauseTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SpelbordSchermController extends Pane {
	
	@FXML
	private GridPane gemeenschappelijkVeld;
	@FXML
	private GridPane werkveld;
	@FXML
	private Label lblAantalPot;
	@FXML
	private Button btnKlaar;
	@FXML
	private Button btnReset;
	@FXML
	private Button btnSorteer;
	@FXML
	private Label lblInfoSpeler;
	@FXML
	private Label lblWerkveld;
	@FXML
	private Label lblAantalPot1;
	@FXML
	private Label infoFout;
	@FXML
	private Button btnSplits;
	@FXML
	private Button btnStop;
	@FXML
	private Rectangle rectangleCover;
	@FXML
	private Label lblSplits1;
	@FXML
	private Label lblSplits2;
	@FXML
	private Button btnAnnuleerSplits;
	@FXML
	private Button btnOkSplits;
	@FXML
	private TextField txfRij1;
	@FXML
	private TextField txfKolom1;
	@FXML
	private TextField txfRij2;
	@FXML
	private TextField txfKolom2;
	@FXML
	private GridPane inventory;

	private DomeinController dc;
	private Integer cIndex1;
	private Integer rIndex1;
	private Integer cIndex2;
	private Integer rIndex2;
	private ImageView[] stenenInventory;
	private ImageView[][] stenenGemeenschappelijkVeld;
	private Node node;
	private Node node2;
	private int oorsprong;
	private String naam;
	private AanmeldSchermController3 asc3;

	public SpelbordSchermController(DomeinController dc, AanmeldSchermController3 asc3) {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("SpelbordScherm.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		try {
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		this.dc = dc;
		this.asc3 = asc3;

		buildGui();

	}

	private void buildGui() {

		/***** SpelbordScherm initieel instellen *****/

		veranderSplitsVisibiliteit(false);
		stelInventoryIn();
		stelwerkveldIn();
		stelGemeenschappelijkVeldenIn();
		lblWerkveld.setText(dc.geefTekstTaal("werkveld"));
		naam = dc.geefNaamSpelerAanDeBeurt();
		lblInfoSpeler.setText(dc.geefTekstTaal("spelerAanDeBeurt") + naam);
		btnKlaar.setText(dc.geefTekstTaal("klaar"));
		btnSplits.setText(dc.geefTekstTaal("splits"));
		btnSorteer.setText(dc.geefTekstTaal("sorteer"));
		btnAnnuleerSplits.setText(dc.geefTekstTaal("annuleer"));
		lblSplits1.setText(dc.geefTekstTaal("geefRijEnKolomSteen1"));
		lblSplits2.setText(dc.geefTekstTaal("geefRijEnKolomSteen2"));
		lblAantalPot.setText(String.format("%d", dc.geefAantalStenenInPot()));
		infoFout.setVisible(false);
		dc.startNieuweBeurt();
		toonStenenInInventory();

		/******************************/
		/***** Drag detected code *****/
		/******************************/

		inventory.setOnDragDetected(event -> {
			try {
				oorsprong = 1;
				node = event.getPickResult().getIntersectedNode();
				cIndex1 = GridPane.getColumnIndex(node);
				Dragboard db = node.startDragAndDrop(TransferMode.ANY);
				ClipboardContent cbContent = new ClipboardContent();
				cbContent.putImage(((ImageView) node).getImage());
				db.setContent(cbContent);
				event.consume();

			} catch (Exception e) {
				// Crash programma voorkomen bij drag zonder steen.
			}
		});

		werkveld.setOnDragDetected(event -> {
			try {
				oorsprong = 2;
				node = event.getPickResult().getIntersectedNode();
				cIndex1 = GridPane.getColumnIndex(node);
				Dragboard db = node.startDragAndDrop(TransferMode.ANY);
				ClipboardContent cbContent = new ClipboardContent();
				cbContent.putImage(((ImageView) node).getImage());
				db.setContent(cbContent);
				event.consume();
			} catch (Exception e) {
				// Crash programma voorkomen bij drag zonder steen.
			}
		});

		gemeenschappelijkVeld.setOnDragDetected(event -> {
			try {
				oorsprong = 3;
				node = event.getPickResult().getIntersectedNode();
				cIndex1 = GridPane.getColumnIndex(node);
				rIndex1 = GridPane.getRowIndex(node);
				Dragboard db = node.startDragAndDrop(TransferMode.ANY);
				ClipboardContent cbContent = new ClipboardContent();
				cbContent.putImage(((ImageView) node).getImage());
				db.setContent(cbContent);
				event.consume();
			} catch (Exception e) {
				// Crash programma voorkomen bij drag zonder steen.
			}
		});

		/******************************/
		/****** Drag accept code ******/
		/******************************/

		inventory.setOnDragOver(event -> {
			if (oorsprong == 1) {
				event.acceptTransferModes(TransferMode.MOVE);
			}
			event.consume();
		});

		werkveld.setOnDragOver(event -> {
			event.acceptTransferModes(TransferMode.MOVE);
			event.consume();
		});

		gemeenschappelijkVeld.setOnDragOver(event -> {
			event.acceptTransferModes(TransferMode.MOVE);
			event.consume();
		});

		/******************************/
		/***** Drop code inventory ****/
		/******************************/

		inventory.setOnDragDropped(event -> {
			speelDropGeluid();
			Dragboard db = event.getDragboard();
			node2 = event.getPickResult().getIntersectedNode();
			cIndex2 = GridPane.getColumnIndex(node2);
			if (cIndex2 == null) {
				cIndex2 = 12;
			}
			ImageView image = new ImageView(db.getImage());
			image.setFitHeight(100);
			image.setFitWidth(60);

			try {
				dc.verplaatsVanInventoryNaarInventory(cIndex1, cIndex2);
				inventory.add(image, cIndex2, 0);
				verwijderNodeUitGrid(0, cIndex1, inventory);
				event.setDropCompleted(true);
			} catch (BezetVakjeException e) {
				event.setDropCompleted(false);
				foutmeldingTonen("bezetVakjeException");
			}
			event.consume();
		});

		/******************************/
		/***** Drop code werkveld *****/
		/******************************/

		werkveld.setOnDragDropped(event -> {
			speelDropGeluid();
			Dragboard db = event.getDragboard();
			node2 = event.getPickResult().getIntersectedNode();
			cIndex2 = GridPane.getColumnIndex(node2);
			if (cIndex2 == null) {
				cIndex2 = 12;
			}
			ImageView image = new ImageView(db.getImage());
			image.setFitHeight(100);
			image.setFitWidth(60);

			if (oorsprong == 1) {
				try {
					dc.verplaatsVanInventoryNaarWerkveld(cIndex1, cIndex2);
					werkveld.add(image, cIndex2, 0);
					verwijderNodeUitGrid(0, cIndex1, inventory);
					event.setDropCompleted(true);
				} catch (BezetVakjeException e) {
					event.setDropCompleted(false);
					foutmeldingTonen("bezetVakjeException");
				}
			}

			if (oorsprong == 2) {
				try {
					dc.verplaatsVanWerkveldNaarWerkveld(cIndex1, cIndex2);
					werkveld.add(image, cIndex2, 0);
					verwijderNodeUitGrid(0, cIndex1, werkveld);
					event.setDropCompleted(true);
				} catch (BezetVakjeException e) {
					event.setDropCompleted(false);
					foutmeldingTonen("bezetVakjeException");
				}
			}

			if (oorsprong == 3) {
				try {
					dc.verplaatsVanGemeenschappelijkNaarWerkveld(rIndex1, cIndex1, cIndex2);
					werkveld.add(image, cIndex2, 0);
					verwijderNodeUitGrid(rIndex1, cIndex1, gemeenschappelijkVeld);
					event.setDropCompleted(true);
				} catch (BezetVakjeException e) {
					event.setDropCompleted(false);
					foutmeldingTonen("bezetVakjeException");
				}
			}

			event.consume();
		});

		/******************************/
		/****** Drop code gemveld *****/
		/******************************/

		gemeenschappelijkVeld.setOnDragDropped(event -> {
			speelDropGeluid();
			Dragboard db = event.getDragboard();
			node2 = event.getPickResult().getIntersectedNode();
			cIndex2 = GridPane.getColumnIndex(node2);
			rIndex2 = GridPane.getRowIndex(node2);
			ImageView image = new ImageView(db.getImage());
			image.setFitHeight(100);
			image.setFitWidth(60);

			if (oorsprong == 1) {
				try {
					dc.verplaatsVanInventoryNaarGemeenschappelijkVeld(cIndex1, rIndex2, cIndex2);
					gemeenschappelijkVeld.add(image, cIndex2, rIndex2);
					verwijderNodeUitGrid(0, cIndex1, inventory);
					event.setDropCompleted(true);
				} catch (BezetVakjeException e) {
					event.setDropCompleted(false);
					foutmeldingTonen("bezetVakjeException");
				}
			}

			if (oorsprong == 2) {
				try {
					dc.verplaatsVanWerkveldNaarGemeenschappelijkVeld(cIndex1, rIndex2, cIndex2);
					gemeenschappelijkVeld.add(image, cIndex2, rIndex2);
					verwijderNodeUitGrid(0, cIndex1, werkveld);
					event.setDropCompleted(true);
				} catch (BezetVakjeException e) {
					event.setDropCompleted(false);
					foutmeldingTonen("bezetVakjeException");
				}
			}

			if (oorsprong == 3) {
				try {
					dc.verplaatsVanGemeenschappelijkNaarGemeenschappelijk(rIndex1, cIndex1, rIndex2, cIndex2);
					gemeenschappelijkVeld.add(image, cIndex2, rIndex2);
					verwijderNodeUitGrid(rIndex1, cIndex1, gemeenschappelijkVeld);
					event.setDropCompleted(true);
				} catch (BezetVakjeException e) {
					event.setDropCompleted(false);
					foutmeldingTonen("bezetVakjeException");
				}
			}

			event.consume();
		});

		/***** Klaar knop *****/

		btnKlaar.setOnAction(event -> {
			
			try {
				dc.isWerkveldLeeg();
				if (dc.isEersteBeurt()) {
					dc.controleerPuntenEersteBeurt();
				}
				dc.controleerSpelSituatie();
				if (dc.isEindeSpel()) {
					openPuntenScherm();
				}
				
				try {
					dc.voegSteenToeIndienNodig();
				}
				catch (GeenStenenInPotMeerException e) {
					/* ***** */
				}
				
				naam = dc.geefNaamSpelerAanDeBeurt();
				lblInfoSpeler.setText(dc.geefTekstTaal("spelerAanDeBeurt") + naam);
				lblAantalPot.setText(String.format("%d", dc.geefAantalStenenInPot()));
				dc.startNieuweBeurt();

				verwijderAlleNodesUitGrid(inventory);
				verwijderAlleNodesUitGrid(werkveld);
				verwijderAlleNodesUitGrid(gemeenschappelijkVeld);

				toonStenenInInventory();
				toonStenenInGemeenschappelijkVeld();
			} catch (OngeldigGemeenschappelijkVeldException e1) {
				foutmeldingTonen(e1.getMessage());
			} catch (JokerEersteBeurtException e) {
				foutmeldingTonen(e.getMessage());
			} catch (TeWeinigPuntenEersteBeurtException e) {
				foutmeldingTonen(e.getMessage());
			} catch (GeenEigenStenenGebruiktEersteBeurtException e) {
				foutmeldingTonen(e.getMessage());
			}catch (WerkveldNietLeegException e) {
				foutmeldingTonen(e.getMessage());
			}
		});

		/***** Sorteer knop *****/
		
		btnSorteer.setOnAction(event -> {
			dc.sorteerInventory();

			verwijderAlleNodesUitGrid(inventory);
			verwijderAlleNodesUitGrid(gemeenschappelijkVeld);

			toonStenenInInventory();
			toonStenenInGemeenschappelijkVeld();
		});

		/***** Stop knop *****/

		btnStop.setOnMouseClicked(event -> {
			try {
				dc.isWerkveldLeeg();
				if (dc.isEersteBeurt()) {
					dc.controleerPuntenEersteBeurt();
				}
				dc.controleerSpelSituatie();
				openPuntenScherm();
			}
			catch(Exception e) {
				dc.resetBeurt();
				openPuntenScherm();
			}
		});

		/***** Reset knop *****/

		btnReset.setOnMouseClicked(event -> {
			dc.resetBeurt();

			verwijderAlleNodesUitGrid(inventory);
			verwijderAlleNodesUitGrid(werkveld);
			verwijderAlleNodesUitGrid(gemeenschappelijkVeld);

			toonStenenInInventory();
			toonStenenInGemeenschappelijkVeld();
		});

		/***** Splits code *****/

		btnSplits.setOnAction(event -> {
			veranderSplitsVisibiliteit(true);
		});

		btnAnnuleerSplits.setOnAction(event -> {
			veranderSplitsVisibiliteit(false);
			resetInputTxf();
		});

		btnOkSplits.setOnAction(event -> {
			veranderSplitsVisibiliteit(false);
			splisRijOfSerie();
			resetInputTxf();
		});
	}

	private void toonStenenInInventory() {
		stenenInventory = dc.geefImageStenenSpelerAanDeBeurt();
		for (int i = 0; i < stenenInventory.length; i++) {
			if (stenenInventory[i] == null) {
				continue;
			}
			inventory.add(stenenInventory[i], i, 0);
		}
	}

	private void toonStenenInGemeenschappelijkVeld() {
		stenenGemeenschappelijkVeld = dc.geefImageStenenGemeenschappelijkVeld();
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 29; j++) {
				if (stenenGemeenschappelijkVeld[i][j] == null) {
					continue;
				}
				gemeenschappelijkVeld.add(stenenGemeenschappelijkVeld[i][j], j, i);
			}
		}
	}

	private void stelInventoryIn() {
		for (int i = 0; i < 53; i++) {
			StackPane stck = new StackPane();
			inventory.add(stck, i, 0);
		}
	}

	private void stelwerkveldIn() {
		for (int i = 0; i < 23; i++) {
			StackPane stck = new StackPane();
			werkveld.add(stck, i, 0);
		}
	}

	private void stelGemeenschappelijkVeldenIn() {
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 29; j++) {
				StackPane stck = new StackPane();
				gemeenschappelijkVeld.add(stck, j, i);
			}
		}
	}

	private void verwijderNodeUitGrid(int rij, int kolom, GridPane grid) {
		ObservableList<Node> children = grid.getChildren();
		for (Node node : children) {
			if (node instanceof ImageView && grid.getRowIndex(node) == rij && grid.getColumnIndex(node) == kolom) {
				grid.getChildren().remove(node);
				grid.add(new StackPane(), kolom, rij);
				break;
			}
		}
	}

	private void verwijderAlleNodesUitGrid(GridPane grid) {
		Node node = grid.getChildren().get(0);
		grid.getChildren().clear();
		grid.getChildren().add(0, node);
		if (grid.getId().equals("inventory")) {
			stelInventoryIn();
		} else if (grid.getId().equals("werkveld")) {
			stelwerkveldIn();
		} else {
			stelGemeenschappelijkVeldenIn();
		}
	}

	private void openPuntenScherm() {
		PuntenSchermController ps = new PuntenSchermController(dc, asc3);
		Scene scene = new Scene(ps);
		Stage stage = (Stage) getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	private void foutmeldingTonen(String soort) {
		speelErrorGeluid();
		infoFout.setText(dc.geefTekstTaal(soort));
		if (soort == "bezetVakjeException") {
			infoFout.setVisible(true);
			PauseTransition delay = new PauseTransition(Duration.seconds(0.8));
			delay.setOnFinished(event -> infoFout.setVisible(false));
			delay.play();
		} else {
			infoFout.setVisible(true);
			PauseTransition delay = new PauseTransition(Duration.seconds(2.3));
			delay.setOnFinished(event -> infoFout.setVisible(false));
			delay.play();
		}

	}

	private void veranderSplitsVisibiliteit(boolean waarde) {
		rectangleCover.setVisible(waarde);
		lblSplits1.setVisible(waarde);
		lblSplits2.setVisible(waarde);
		txfRij1.setVisible(waarde);
		txfRij2.setVisible(waarde);
		txfKolom1.setVisible(waarde);
		txfKolom2.setVisible(waarde);
		btnAnnuleerSplits.setVisible(waarde);
		btnOkSplits.setVisible(waarde);

		btnKlaar.setDisable(waarde);
		btnStop.setDisable(waarde);
		btnSplits.setDisable(waarde);
		btnReset.setDisable(waarde);
		btnSorteer.setDisable(waarde);
	}

	private void splisRijOfSerie() {
		try {
			int rij1 = Integer.parseInt(txfRij1.getText());
			int kolom1 = Integer.parseInt(txfKolom1.getText());
			int rij2 = Integer.parseInt(txfRij2.getText());
			int kolom2 = Integer.parseInt(txfKolom2.getText());
			if (kolom1 > kolom2) {
				dc.splitsRijOfSerie(rij2, kolom2, rij1, kolom1);
			} else
				dc.splitsRijOfSerie(rij1, kolom1, rij2, kolom2);

			verwijderAlleNodesUitGrid(inventory);
			verwijderAlleNodesUitGrid(werkveld);
			verwijderAlleNodesUitGrid(gemeenschappelijkVeld);

			toonStenenInInventory();
			toonStenenInGemeenschappelijkVeld();
		} catch (NumberFormatException e) {
			foutmeldingTonen("tekstinvoerException");
		} catch (InputMismatchException e) {
			foutmeldingTonen(e.getMessage());
		} catch (SplitsExceptie e) {
			foutmeldingTonen(e.getMessage());
		} catch (GeenPlaatsSpitsException e) {
			foutmeldingTonen(e.getMessage());
		} catch (LeegVeldException e) {
			foutmeldingTonen(e.getMessage());
		}
	}

	private void resetInputTxf() {
		txfRij1.clear();
		txfKolom1.clear();
		txfRij2.clear();
		txfKolom2.clear();
	}

	private void speelDropGeluid() {
		AudioClip drop = new AudioClip(getClass().getResource("/audio/SteenDropSound.mp3").toExternalForm());
		drop.play();
	}

	private void speelErrorGeluid() {
		AudioClip drop = new AudioClip(getClass().getResource("/audio/error.mp3").toExternalForm());
		drop.play();
	}
}
