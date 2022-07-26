package domein;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;

import exceptions.BezetVakjeException;
import exceptions.GeenEigenStenenGebruiktEersteBeurtException;
import exceptions.GeenPlaatsSpitsException;
import exceptions.GeenStenenInPotMeerException;
import exceptions.JokerEersteBeurtException;
import exceptions.LeegVeldException;
import exceptions.OngeldigGemeenschappelijkVeldException;
import exceptions.SplitsExceptie;
import exceptions.TeWeinigPuntenEersteBeurtException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Spel {

	private int spelerAanDeBeurt;
	private List<Steen> stenen;
	private List<Speler> spelers;
	private Beurt beurt;
	private GemeenschappelijkVeld gemeenschappelijkVeld;
	private GemeenschappelijkVeld backupGemeenschappelijkVeld;
	private Steen[] backupStenenSADB;
	private Rij rij;
	private Serie serie;

	/**
	 * UC2 constructor De spelers worden hier geshuffled omdat we dan een random
	 * volgorde krijgen om het spel te spelen. Hier worden ook de private methodes
	 * maakStenenAan en verdeelStenen aangeroepen om de stenen aan te maken en te
	 * verdelen.
	 * 
	 * @param spelers
	 */
	public Spel(List<Speler> spelers) {
		this.spelerAanDeBeurt = -1;
		this.stenen = new ArrayList<>();
		Collections.shuffle(spelers);
		this.spelers = spelers;
		maakStenenAan();
		verdeelStenen();
		this.gemeenschappelijkVeld = new GemeenschappelijkVeld();
	}

	/**
	 * UC2 returnt de int spelerAanDeBeurt.
	 * 
	 * @return
	 */
	public int getSpelerAanDeBeurt() {
		return spelerAanDeBeurt;
	}

	/**
	 * UC2 returnt de spelers van het spel.
	 * 
	 * @return
	 */
	public List<Speler> getSpelers() {
		return spelers;
	}

	/**
	 * UC2 Aanmaken van 106 stenen en deze random door elkaar shuffelen.
	 */
	private void maakStenenAan() {

		String[] kleuren = { "Zwart", "Rood", "Blauw", "Geel" };
		int id = 0;

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < KLEUR.values().length; j++) {
				for (int k = 0; k < 13; k++) {
					String path = String.format("/images/%s%d.png", kleuren[j], k + 1);
					ImageView image = new ImageView(new Image(getClass().getResourceAsStream(path)));
					image.setFitHeight(100);
					image.setFitWidth(60);
					stenen.add(new Steen(k + 1, KLEUR.values()[j], false, id, image));
					id++;
				}
			}
		}

		ImageView imgJoker1 = new ImageView(new Image(getClass().getResourceAsStream("/images/Joker1.png")));
		imgJoker1.setFitHeight(100);
		imgJoker1.setFitWidth(60);
		stenen.add(new Steen(0, null, true, id, imgJoker1));
		
		ImageView imgJoker2 = new ImageView(new Image(getClass().getResourceAsStream("/images/Joker2.png")));
		imgJoker2.setFitHeight(100);
		imgJoker2.setFitWidth(60);
		stenen.add(new Steen(0, null, true, id++, imgJoker2));

		Collections.shuffle(stenen);
	}

	/**
	 * UC2 Lijst van random stenen (14 * aantal spelers) aanroepen uit klasse Spel
	 * via geefStenenSpelers. 14 Stenen per aangemelde speler verdelen
	 */
	private void verdeelStenen() {

		Steen[] stenen = geefStenenSpelers();
		Steen[] stenenSpeler = new Steen[53];

		int start = 0;
		int einde = 14;
		int index = 0;

		for (int i = 0; i < spelers.size(); i++) {

			for (int j = start; j < einde; j++) {
				stenenSpeler[index] = stenen[j];
				index++;
			}

			spelers.get(i).setStenen(stenenSpeler);
			stenenSpeler = new Steen[53];
			start += 14;
			einde += 14;
			index = 0;
		}
	}

	/**
	 * UC2 Genereert (14 * aantalSpelers) random stenen uit de lijst van de 106
	 * stenen. Dan worden deze 56 stenen verwijdert uit de lijst van 106 stenen en
	 * worden ze gereturned.
	 * 
	 * @return
	 */
	private Steen[] geefStenenSpelers() {

		Steen[] stenenSpelers = new Steen[14 * spelers.size()];
		SecureRandom sr = new SecureRandom();

		for (int i = 0; i < (14 * spelers.size()); i++) {
			int random = sr.nextInt(stenen.size());
			stenenSpelers[i] = stenen.get(random);
			stenen.remove(random);
		}

		return stenenSpelers;
	}

	/**
	 * UC2 Naam speler aan de beurt bepalen.
	 * 
	 * @return
	 */
	public String bepaalNaamSpelerAanDeBeurt() {

		if (spelerAanDeBeurt < (spelers.size() - 1)) {
			spelerAanDeBeurt++;
		} else {
			spelerAanDeBeurt = 0;
		}

		return spelers.get(spelerAanDeBeurt).getGebruikersnaam();
	}

	/**
	 * UC2 Bepalen of het einde van het spel bereikt is. Spel stopt wanneer een
	 * speler geen stenen meer in zijn bezit heeft.
	 * 
	 * @return
	 */
	public boolean isEindeSpel() {

		if (spelers.get(spelerAanDeBeurt).nogStenenAanwezig()) {
			return false;
		}

		return true;
	}

	/**
	 * UC2 Afdrukken van scores van spelers.
	 * 
	 * @return
	 */
	public List<Integer> geefScores() {

		List<Integer> scores = new ArrayList<>();

		bepaalScore();

		for (Speler s : spelers) {
			scores.add(s.getScore());
		}

		return scores;

	}

	/**
	 * UC2 Score bereken van speler.
	 */
	private void bepaalScore() {

		int scoreWinnaar = 0, winnaar = -1;

		for (Speler s : spelers) {

			s.bepaalScore();

			if (s.getScore() == 0) {
				winnaar = spelers.indexOf(s);
			} else {
				scoreWinnaar += Math.abs(s.getScore());
			}
		}

		if (winnaar != -1) {
			spelers.get(winnaar).setScore(scoreWinnaar);
		}
	}

	/**
	 * UC2/UC3 returnt de stenen van de pot. wordt gebruikt voor de test
	 * 
	 * @return
	 */
	public List<Steen> getStenen() {
		return stenen;
	}

	/**
	 * UC2/UC3 returnt het aantal stenen in de pot.
	 * 
	 * @return
	 */
	public int geefAantalStenenInPot() {
		return stenen.size();
	}

	/**
	 * UC3 returnt het gemeenschappelijk veld.
	 * 
	 * @return
	 */
	private GemeenschappelijkVeld getGemeenschappelijkVeld() {
		return gemeenschappelijkVeld;
	}

	/**
	 * UC3 vervangt het attribuut gemeenschappelijkVeld met het meegegeven
	 * attribuut.
	 * 
	 * @param gemeenschappelijkVeld
	 */
	private void setGemeenschappelijkVeld(GemeenschappelijkVeld gemeenschappelijkVeld) {
		this.gemeenschappelijkVeld = gemeenschappelijkVeld;
	}

	/**
	 * UC3 returnt de namen van de spelers
	 * 
	 * @return
	 */
	public List<String> geefNamenSpelers() {
		List<String> namen = new ArrayList<>();
		for (Speler s : spelers) {
			namen.add(s.getGebruikersnaam());
		}
		return namen;
	}

	/**
	 * UC3 returnt de image van stenen van de speler aan de beurt. Dit zijn dus de
	 * images van de inventory stenen.
	 * 
	 * @return
	 */
	public ImageView[] geefImageStenenSpelerAanDeBeurt() {

		ImageView[] stenen = new ImageView[53];

		for (int i = 0; i < stenen.length; i++) {
			if (spelers.get(spelerAanDeBeurt).getStenen()[i] == null) {
				continue;
			} else {
				stenen[i] = spelers.get(spelerAanDeBeurt).getStenen()[i].getImage();
			}
		}
		return stenen;
	}

	/**
	 * UC3 returnt de image van de stenen van het gemeenschappelijk veld.
	 * 
	 * @return
	 */
	public ImageView[][] geefImageStenenGemeenschappelijkVeld() {
		ImageView[][] stenen = new ImageView[7][29];
		Steen[][] stenenGemeenschappelijkVeld = gemeenschappelijkVeld.getStenenInGemeenschappelijkVeld();

		for (int i = 0; i < stenenGemeenschappelijkVeld.length; i++) {
			for (int j = 0; j < stenenGemeenschappelijkVeld[i].length; j++) {
				if (stenenGemeenschappelijkVeld[i][j] == null) {
					continue;
				}
				stenen[i][j] = stenenGemeenschappelijkVeld[i][j].getImage();
			}
		}
		return stenen;
	}

	/**
	 * UC3 deze methode gaat een nieuwe beurt aanmaken. Het maakt ook weer een
	 * backup van het gemeenschappelijkveld en de inventory van de speler aan de
	 * beurt door de methodes maakBackup van beiden aan te roepen.
	 */
	public void startNieuweBeurt() {
		beurt = new Beurt();
		maakBackupGemeenschappelijkVeld();
		maakBackupInventory();
	}

	/**
	 * UC3 Deze methode gaat eerst de te verplaatsen steen uit het werkveld halen,
	 * dan gaat hij deze naar het werkveld verplaatsen door de methode
	 * verplaatsVanInventoryNaarWerkveld aan te roepen van beurt. Als laatste gaat
	 * het de steen verwijder uit de inventory.
	 * 
	 * @param xInv
	 * @param plaatsWerk
	 */
	public void verplaatsVanInventoryNaarWerkveld(int xInv, int plaatsWerk) {
		Steen teVerplaatsenSteen = spelers.get(spelerAanDeBeurt).getStenen()[xInv];
		beurt.verplaatsVanInventoryNaarWerkveld(teVerplaatsenSteen, plaatsWerk);
		spelers.get(spelerAanDeBeurt).verwijderSteenUitInventory(xInv);
	}

	/**
	 * UC3 Deze methode gaat een steen uit de inventory halen, ze dan verplaatsen
	 * naar een andere plaats in de inventory en de steen op de originele plaats
	 * verwijderen.
	 * 
	 * @param xInv
	 * @param plaatsInv
	 */
	public void verplaatsVanInventoryNaarInventory(int xInv, int plaatsInv) {
		Steen teVerplaatsenSteen = spelers.get(spelerAanDeBeurt).getStenen()[xInv];
		spelers.get(spelerAanDeBeurt).verplaatsSteenNaarInventory(teVerplaatsenSteen, plaatsInv);
		spelers.get(spelerAanDeBeurt).verwijderSteenUitInventory(xInv);
	}

	/**
	 * UC3 Deze methode gaat een steen uit de inventory halen, ze dan verplaatsen
	 * naar een plaats in het gemeenschappelijk veld, en de steen uit de inventory
	 * verwijderen.
	 * 
	 * @param plaatsInv
	 * @param rij
	 * @param kolom
	 */
	public void verplaatsVanInventoryNaarGemeenschappelijkVeld(int plaatsInv, int rij, int kolom) {
		Steen teVerplaatsenSteen = spelers.get(spelerAanDeBeurt).getStenen()[plaatsInv];
		gemeenschappelijkVeld.verplaatsSteenNaarGemeenschappelijkVeld(teVerplaatsenSteen, rij, kolom);
		spelers.get(spelerAanDeBeurt).verwijderSteenUitInventory(plaatsInv);
	}

	/**
	 * UC3 Deze methode gaat een steen uit het werkveld halen, ze verplaatsen naar
	 * de inventory en deze dan uit het werkveld verwijderen.
	 * 
	 * @param xWerk
	 * @param plaatsInv
	 */
	public void verplaatsVanWerkveldNaarInventory(int xWerk, int plaatsInv) {
		Steen teVerplaatsenSteen = beurt.verplaatsVanWerkveldNaarInventory(xWerk);
		spelers.get(spelerAanDeBeurt).verplaatsSteenNaarInventory(teVerplaatsenSteen, plaatsInv);
	}

	/**
	 * UC3 Deze methode gaat een steen uit het werkveld halen indien de steen op een
	 * andere plaats legt en deze op de originele plaats verwijderen. Gooit een
	 * error indien het op dezelfde plaats wordt gelegd.
	 * 
	 * @param xWerk
	 * @param plaatsWerk
	 */
	public void verplaatsVanWerkveldNaarWerkveld(int xWerk, int plaatsWerk) {
		if (xWerk == plaatsWerk) {
			throw new BezetVakjeException("bezetVakjeException");
		}
		beurt.verplaatsVanWerkveldNaarWerkveld(xWerk, plaatsWerk);
	}

	/**
	 * UC3 Deze methode gaat een steen uit het werkveld halen, deze op het
	 * gemeenschappelijk veld plaatsen en deze dan uit het werkveld verwijderen.
	 * Gooit een error indien het op dezelfde plaats wordt gelegd.
	 * 
	 * @param plaatsWerk
	 * @param rij
	 * @param kolom
	 */
	public void verplaatsVanWerkveldNaarGemeenschappelijkVeld(int plaatsWerk, int rij, int kolom) {
		if (gemeenschappelijkVeld.getStenenInGemeenschappelijkVeld()[rij][kolom] != null) {
			throw new BezetVakjeException("bezetVakjeException");
		}
		Steen teVerplaatsenSteen = beurt.verplaatsVanWerkveldNaarGemeenschappelijkVeld(plaatsWerk);
		getGemeenschappelijkVeld().verplaatsSteenNaarGemeenschappelijkVeld(teVerplaatsenSteen, rij, kolom);
	}

	/**
	 * UC3 Deze methode gaat een steen uit het gemeenschappelijk veld halen, deze
	 * naar het werkveld verplaatsen en deze dan uit het gemeenschappelijk veld
	 * verwijderen.
	 * 
	 * @param rij
	 * @param kolom
	 * @param plaatsWerk
	 */
	public void verplaatsVanGemeenschappelijkNaarWerkveld(int rij, int kolom, int plaatsWerk) {
		Steen teVerplaatsenSteen = getGemeenschappelijkVeld().getSteenUitGemeenschappelijkVeld(rij, kolom);
		beurt.verplaatsVanGemeenschappelijkNaarWerkveld(teVerplaatsenSteen, plaatsWerk);
		getGemeenschappelijkVeld().haalSteenUitGemeenschappelijkVeld(rij, kolom);
	}

	/**
	 * UC3 Deze methode gaat een steen uit het gemeenschappelijk veld halen, deze
	 * verplaatsen naar een andere plaats in het gemeenschappelijk veld en deze dan
	 * verwijderen op de originele plaats. Gooit een error indien het op dezelfde
	 * plaats wordt gelegd en indien er al een steen ligt op de gewenste plaats.
	 * 
	 * @param rijO
	 * @param kolomO
	 * @param rijB
	 * @param kolomB
	 */
	public void verplaatsVanGemeenschappelijkNaarGemeenschappelijk(int rijO, int kolomO, int rijB, int kolomB) {
		if (rijO == rijB && kolomO == kolomB) {
			throw new BezetVakjeException("bezetVakjeException");
		} else if (gemeenschappelijkVeld.getStenenInGemeenschappelijkVeld()[rijB][kolomB] != null) {
			throw new BezetVakjeException("bezetVakjeException");
		}
		Steen teVerplaatsenSteen = getGemeenschappelijkVeld().haalSteenUitGemeenschappelijkVeld(rijO, kolomO);
		gemeenschappelijkVeld.verplaatsSteenNaarGemeenschappelijkVeld(teVerplaatsenSteen, rijB, kolomB);
	}

	/**
	 * UC3 Controleert of de speler nog plaats heeft in zijn inventory, indien niet
	 * dan gooit die een error. Daarna gaat hij controleren of de speler in zijn
	 * beurt een steen heeft gelegd en indien niet dan voegt hij een steen van de
	 * pot toe aan zijn inventory.
	 */
	public void voegSteenToeIndienNodig() {
		List<Integer> idsStenen = new ArrayList<>();
		List<Integer> idsBackupStenen = new ArrayList<>();
		if (stenen.size() == 0)
			throw new GeenStenenInPotMeerException("geenSteenInPotMeer");
		Steen[] stenenSADB = spelers.get(spelerAanDeBeurt).getStenen();
		for(int i = 0; i < 53; i++) {
			if(stenenSADB[i] == null)
				continue;
			idsStenen.add(stenenSADB[i].getId());
		}
		for(int i = 0; i < 53; i++) {
			if(backupStenenSADB[i] == null)
				continue;
			idsBackupStenen.add(backupStenenSADB[i].getId());
		}
		Collections.sort(idsStenen);
		Collections.sort(idsBackupStenen);
		if(idsStenen.equals(idsBackupStenen)) {
			SecureRandom sr = new SecureRandom();
			int random = sr.nextInt(stenen.size());
			for (int i = 0; i < stenenSADB.length; i++) {
				if (stenenSADB[i] == null) {
					stenenSADB[i] = stenen.get(random);
					stenen.remove(random);
					spelers.get(spelerAanDeBeurt).setStenen(stenenSADB);
					break;
				}
			}
		}
	}

	/**
	 * UC3 Deze methode gaat de beurt resetten en dus de stenen terugleggen zoals
	 * aan het begin van de beurt.
	 */
	public void resetBeurt() {
		setGemeenschappelijkVeld(backupGemeenschappelijkVeld);
		spelers.get(spelerAanDeBeurt).setStenen(backupStenenSADB);
		startNieuweBeurt();
	}

	/**
	 * UC3 Deze methode roept de methode isWerkveldLeeg aan van beurt.
	 */
	public void isWerkveldLeeg() {
		beurt.isWerkveldLeeg();
	}

	/**
	 * UC3 Deze methode zorgt dat er een backup gemaakt wordt van het
	 * gemeenschappelijk veld.
	 */
	public void maakBackupGemeenschappelijkVeld() {
		Steen[][] stenenBackup = new Steen[7][29];
		Steen[][] stenenOrigineel = getGemeenschappelijkVeld().getStenenInGemeenschappelijkVeld();
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 29; j++) {
				stenenBackup[i][j] = stenenOrigineel[i][j];
			}
		}
		backupGemeenschappelijkVeld = new GemeenschappelijkVeld(stenenBackup);
	}

	/**
	 * UC3 Deze methode zorgt dat er een backup gemaakt wordt van de inventory.
	 */
	public void maakBackupInventory() {
		backupStenenSADB = new Steen[53];

		int index = 0;
		for (Steen s : spelers.get(spelerAanDeBeurt).getStenen()) {
			backupStenenSADB[index] = s;
			index++;
		}
	}

	/**
	 * UC3 Deze methode gaat de spelsituatie controleren. Hij gaat dus kijken of
	 * alle stenen die gelegd zijn, juist gelegd zijn en voldoen aan de regels. Hij
	 * gooit een error indien de spelsituatie niet geldig is.
	 */
	public void controleerSpelSituatie() {
		Steen[][] veld = gemeenschappelijkVeld.getStenenInGemeenschappelijkVeld();
		List<Steen> teControleren = new ArrayList<>();

		int index = 0;

		for (int i = 0; i < veld.length; i++) {
			for (int j = 0; j < veld[i].length; j++) {
				if (veld[i][j] != null) {
					teControleren.add(veld[i][j]);
				}
				if (veld[i][j] == null && teControleren.size() != 0) {
					Steen[] temp = new Steen[teControleren.size()];
					for (Steen s : teControleren) {
						temp[index] = s;
						index++;
					}
					serie = new Serie(temp);
					rij = new Rij(temp);
					teControleren.clear();
					index = 0;
					if (serie.isGeldigeSerie() || rij.isGeldigeRij()) {
						continue;
					} else {
						throw new OngeldigGemeenschappelijkVeldException("gemeenschappelijkVeldFout");
					}
				}
			}
		}
	}

	/**
	 * UC3 Deze methode checkt alle vereisten waaraan de eerste beurt moet voldoen
	 * en gooit error indien de eisen niet vervuld zijn. Hij gaat dus checken of de
	 * totale score aan stenen die gelegd is, groter is dan 30. Hij checkt ook of er
	 * geen joker is gelegd en dat de speler eigen series en rijen heeft gemaakt die
	 * geen stenen van andere spelers bevat.
	 */
	public void controleerPuntenEersteBeurt() {
		Steen[][] veld = gemeenschappelijkVeld.getStenenInGemeenschappelijkVeld();
		Steen[][] backupVeld = backupGemeenschappelijkVeld.getStenenInGemeenschappelijkVeld();
		List<Integer> idsBackupVeld = new ArrayList<>();
		for (int i = 0; i < backupVeld.length; i++) {
			for (int j = 0; j < backupVeld[i].length; j++) {
				if (backupVeld[i][j] != null)
					idsBackupVeld.add(backupVeld[i][j].getId());
			}
		}
		int totaleScore = 0;
		for (int i = 0; i < veld.length; i++) {
			for (int j = 0; j < veld[i].length; j++) {
				if (veld[i][j] == null)
					continue;
				else if (idsBackupVeld.indexOf(veld[i][j].getId()) == -1) {
					if (veld[i][j].getGetal() == 0)
						throw new JokerEersteBeurtException("jokerEersteBeurt");
					totaleScore += veld[i][j].getGetal();
				}
			}
		}

		List<Steen> teControleren = new ArrayList<>();

		int eigenStenen = 0;
		int aanwezigeStenen = 0;

		for (int i = 0; i < veld.length; i++) {
			for (int j = 0; j < veld[i].length; j++) {
				if (veld[i][j] != null) {
					teControleren.add(veld[i][j]);
				}
				if (veld[i][j] == null && teControleren.size() != 0) {
					for (Steen s : teControleren) {
						if (idsBackupVeld.indexOf(s.getId()) == -1)
							eigenStenen += 1;
						else
							aanwezigeStenen += 1;
					}
					if (eigenStenen == teControleren.size() || aanwezigeStenen == teControleren.size()) {
						teControleren.clear();
						eigenStenen = 0;
						aanwezigeStenen = 0;
					} else
						throw new GeenEigenStenenGebruiktEersteBeurtException("geenEigenStenenGebruiktEersteBeurt");
				}
			}

		}
		if (totaleScore < 30 && totaleScore != 0) {
			throw new TeWeinigPuntenEersteBeurtException("geen30Punten");
		} else if (totaleScore >= 30) {
			try {
				controleerSpelSituatie();
				spelers.get(spelerAanDeBeurt).setEersteBeurt(false);
			} catch (OngeldigGemeenschappelijkVeldException e) {

			}
		}
	}

	/**
	 * UC3 Deze methode controleert of het de eerste beurt is en retourneert false
	 * indien het niet de eerste beurt is en true indien het wel de eerste beurt is.
	 * 
	 * @return
	 */
	public boolean isEersteBeurt() {
		return spelers.get(spelerAanDeBeurt).getEersteBeurt();
	}

	/**
	 * UC3 Deze methode zorgt voor het splitsen van rijen. Er worden errors gegeven
	 * indien er foute getallen worden ingegeven, geen getallen worden ingegeven,
	 * indien hij dezelfde plaatsen doorgeeft, en indien er geen plaats meer is om
	 * te splitsen. Afhankelijk van de plaats zullen de stenen naar
	 * rechts(standaard) of naar links verschoven worden.
	 * 
	 * @param rij1
	 * @param kolom1
	 * @param rij2
	 * @param kolom2
	 */
	public void splitsRijOfSerie(int rij1, int kolom1, int rij2, int kolom2) {
		if (rij1 < 0 || rij2 < 0 || kolom1 < 0 || kolom2 < 0)
			throw new InputMismatchException("fouteWaardeSplit");
		else if (rij1 > 6 || rij2 > 6 || kolom1 > 28 || kolom2 > 28)
			throw new InputMismatchException("fouteWaardeSplit");
		else if (rij1 != rij2)
			throw new SplitsExceptie("splitsExceptie");
		else if (kolom1 == kolom2)
			throw new SplitsExceptie("splitsExceptie");
		else if (kolom1 + 1 != kolom2)
			throw new SplitsExceptie("splitsExceptie");

		boolean plaatsRechtsVrij = false;
		boolean plaatsLinksVrij = false;
		int vrijePlaatsRechts = 0, vrijePlaatsLinks = 0;
		Steen[] rijSplits = gemeenschappelijkVeld.getStenenInGemeenschappelijkVeld()[rij1];

		if (rijSplits[kolom1] == null || rijSplits[kolom2] == null)
			throw new LeegVeldException("splitsLeegVeld");

		Steen[][] nieuwGemeenschappelijkVeld = gemeenschappelijkVeld.getStenenInGemeenschappelijkVeld();

		for (int i = kolom2 + 1; i < rijSplits.length; i++) {
			if (rijSplits[i] == null) {
				plaatsRechtsVrij = true;
				vrijePlaatsRechts = i;
				break;
			}
		}
		for (int j = kolom1 - 1; j >= 0; j--) {
			if (rijSplits[j] == null) {
				plaatsLinksVrij = true;
				vrijePlaatsLinks = j;
				break;
			}
		}

		if (plaatsRechtsVrij == true) {
			for (int i = vrijePlaatsRechts; i > kolom2; i--) {
				rijSplits[i] = rijSplits[i - 1];
				rijSplits[i - 1] = null;
			}
			nieuwGemeenschappelijkVeld[rij1] = rijSplits;
			gemeenschappelijkVeld.setStenenInGemeenschappelijkVeld(nieuwGemeenschappelijkVeld);
		} else if (plaatsLinksVrij == true) {
			for (int i = vrijePlaatsLinks; i < kolom1; i++) {
				rijSplits[i] = rijSplits[i + 1];
				rijSplits[i + 1] = null;
			}
			nieuwGemeenschappelijkVeld[rij1] = rijSplits;
			gemeenschappelijkVeld.setStenenInGemeenschappelijkVeld(nieuwGemeenschappelijkVeld);
		} else
			throw new GeenPlaatsSpitsException("splitsGeenPlaatsMeer");
	}

	/**
	 * UC3 sorteert de stenen van de inventory op basis van kleur, en daarna op
	 * basis van nummer.
	 */
	public void sorteerInventory() {
		List<Steen> blauweStenen = new ArrayList<>();
		List<Steen> rodeStenen = new ArrayList<>();
		List<Steen> geleStenen = new ArrayList<>();
		List<Steen> zwarteStenen = new ArrayList<>();
		List<Steen> jokers = new ArrayList<>();
		
		List<Steen> stenenGesorteerd = new ArrayList<>();
		for (Steen s : spelers.get(spelerAanDeBeurt).getStenen()) {
			if(s == null)
				continue;
			else if(s.isJoker())
				jokers.add(s);
			else if(s.getKleur().getNumberValue() == 0) 
				zwarteStenen.add(s);
			else if(s.getKleur().getNumberValue() == 1) 
				geleStenen.add(s);
			else if(s.getKleur().getNumberValue() == 2) 
				rodeStenen.add(s);
			else if(s.getKleur().getNumberValue() == 3) 
				blauweStenen.add(s);
			
			
		}
		Collections.sort(blauweStenen);
		Collections.sort(rodeStenen);
		Collections.sort(geleStenen);
		Collections.sort(zwarteStenen);
		stenenGesorteerd.addAll(blauweStenen);
		stenenGesorteerd.addAll(rodeStenen);
		stenenGesorteerd.addAll(geleStenen);
		stenenGesorteerd.addAll(zwarteStenen);
		stenenGesorteerd.addAll(jokers);
		Steen [] stenenGesorteerdArray = new Steen[53];
		int index = 0;
		for (Steen s : stenenGesorteerd) {
			stenenGesorteerdArray[index] = s;
			index++;
		}
		spelers.get(spelerAanDeBeurt).setStenen(stenenGesorteerdArray);
	}
	
}
