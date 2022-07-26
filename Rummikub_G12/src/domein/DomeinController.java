package domein;

import java.util.ArrayList;
import java.util.List;

import exceptions.AanmeldenException;
import exceptions.LeegVeldException;
import exceptions.SpelerBestaatAlException;
import javafx.scene.image.ImageView;
import utility.Taal;

public class DomeinController {

	private final SpelerRepository spelerRepository;
	private List<Speler> spelers;
	private Spel spel;
	private Taal taal;

	/**
	 * UC1 constructor
	 */
	public DomeinController() {
		this.spelerRepository = new SpelerRepository();
		this.spelers = new ArrayList<>();
	}

	/**
	 * UC1 Hier controleren we het aantal spelers op de grenzen 2, 4.
	 * 
	 * @param aantal
	 */
	public void registreerAantal(int aantal) throws IllegalArgumentException {
		if (aantal < 2 || aantal > 4) {
			throw new IllegalArgumentException(geefTekstTaal("aantalSpelersException"));
		}
	}

	/**
	 * UC1 Speler data ophalen uit databank. Controleert of de gebruikersnaam en
	 * wachtwoord null zijn en gooit indien dit het geval is een exceptie. Indien
	 * dit niet het geval is dan meld hij deze speler aan als hij nog niet bestaat
	 * en de gebruikersnaam en wachtwoord juist zijn.
	 * 
	 * @param gebruikersnaam
	 * @param wachtwoord
	 */

	public void meldAan(String gebruikersnaam, String wachtwoord) {
		List<String> gebruikersnamenLowerCase = new ArrayList<>();
		for(String s : geefGebruikersnamen())
			gebruikersnamenLowerCase.add(s.toLowerCase());
		
		if (gebruikersnaam == null || wachtwoord == null) {
			throw new AanmeldenException(geefTekstTaal("aanmeldenException"));
		}

		if (gebruikersnaam.isEmpty() || wachtwoord.isEmpty()) {
			throw new LeegVeldException(geefTekstTaal("leegVeldAanmelden"));
		}

		Speler speler = spelerRepository.geefAangemeldeSpeler(gebruikersnaam, wachtwoord);
		if (gebruikersnamenLowerCase.indexOf(gebruikersnaam.toLowerCase()) != -1) {
			throw new SpelerBestaatAlException("spelerBestaatAl");
		} 
		else {
			this.spelers.add(speler);
		}
	}

	/**
	 * UC1 returnt de gebruikersnamen van de aangemelde spelers.
	 * 
	 * @return
	 */
	public List<String> geefGebruikersnamen() {
		List<String> aangemeldeSpelers = new ArrayList<>();

		for (Speler i : this.spelers) {
			aangemeldeSpelers.add(i.getGebruikersnaam());
		}

		return aangemeldeSpelers;
	}

	/**
	 * UC2 stelt de taal Nederlands of Engels in.
	 * 
	 * @param taal
	 */
	public void stelTaalIn(String taal) {
		this.taal = new Taal(taal);
	}

	/**
	 * UC2 Wordt gebruikt om de properties door te geven aan de klasse taal. Hiermee
	 * wordt dan de juiste zin in de juiste taal teruggegeven.
	 * 
	 * @param key
	 * @return
	 */
	public String geefTekstTaal(String key) {
		return taal.geefTekst(key);
	}

	/**
	 * UC2 Nieuw spel starten met de aangemelde spelers.
	 */
	public void startNieuwSpel() {
		spel = new Spel(spelers);
	}

	/**
	 * UC2 roept bepaalNaamSpelerAanDeBeurt van spel aan en returnt dit.
	 * 
	 * @return
	 */
	public String geefNaamSpelerAanDeBeurt() {
		return spel.bepaalNaamSpelerAanDeBeurt();
	}

	/**
	 * UC2 roept isEindeSpel van spel aan en returnt dit.
	 * 
	 * @return
	 */
	public boolean isEindeSpel() {
		return spel.isEindeSpel();
	}

	/**
	 * UC2 roept geefScores aan van spel en returnt dit.
	 * 
	 * @return
	 */
	public List<Integer> geefScores() {
		return spel.geefScores();
	}

	/**
	 * UC3 roept de methode geefAantalStenenInPot van spel aan en returnt dit.
	 * 
	 * @return
	 */
	public int geefAantalStenenInPot() {
		return spel.geefAantalStenenInPot();
	}

	/**
	 * UC3 roept geefImageStenenSpelerAanDeBeurt aan van spel en returnt dit.
	 * 
	 * @return
	 */
	public ImageView[] geefImageStenenSpelerAanDeBeurt() {
		return spel.geefImageStenenSpelerAanDeBeurt();
	}

	/**
	 * UC3 roept geefImageStenenGemeenschappelijkVeld aan van spel en returnt dit.
	 * 
	 * @return
	 */
	public ImageView[][] geefImageStenenGemeenschappelijkVeld() {
		return spel.geefImageStenenGemeenschappelijkVeld();
	}

	/**
	 * UC3 roept startNieuweBeurt van spel aan.
	 * 
	 * @return
	 */
	public void startNieuweBeurt() {
		spel.startNieuweBeurt();
	}

	/**
	 * UC3 roept geefNamenSpelers aan van spel en returnt dit.
	 * 
	 * @return
	 */
	public List<String> geefNamenSpelers() {
		return spel.geefNamenSpelers();
	}

	/**
	 * UC3 roept controleerSpelSituatie van spel aan.
	 */
	public void controleerSpelSituatie() {
		spel.controleerSpelSituatie();
	}

	/**
	 * UC3 roept controleerPuntenEersteBeurt van spel aan.
	 */
	public void controleerPuntenEersteBeurt() {
		spel.controleerPuntenEersteBeurt();
	}

	/**
	 * UC3 roept isEersteBeurtaan van spel en returnt dit.
	 * 
	 * @return
	 */
	public boolean isEersteBeurt() {
		return spel.isEersteBeurt();
	}

	/**
	 * UC3 roept verplaatsVanInventoryNaarWerkveld van spel aan.
	 * 
	 * @param xInv
	 * @param plaatsWerk
	 */
	public void verplaatsVanInventoryNaarWerkveld(int xInv, int plaatsWerk) {
		spel.verplaatsVanInventoryNaarWerkveld(xInv, plaatsWerk);
	}

	/**
	 * UC3 roept verplaatsVanInventoryNaarInventory van spel aan.
	 * 
	 * @param xInv
	 * @param plaatsInv
	 */
	public void verplaatsVanInventoryNaarInventory(int xInv, int plaatsInv) {
		spel.verplaatsVanInventoryNaarInventory(xInv, plaatsInv);
	}

	/**
	 * UC3 roept verplaatsVanInventoryNaarGemeenschappelijkVeld van spel aan.
	 * 
	 * @param plaatsInv
	 * @param rij
	 * @param kolom
	 */
	public void verplaatsVanInventoryNaarGemeenschappelijkVeld(int plaatsInv, int rij, int kolom) {
		spel.verplaatsVanInventoryNaarGemeenschappelijkVeld(plaatsInv, rij, kolom);
	}

	/**
	 * UC3 roept verplaatsVanWerkveldNaarInventory van spel aan.
	 * 
	 * @param xWerk
	 * @param plaatsInv
	 */
	public void verplaatsVanWerkveldNaarInventory(int xWerk, int plaatsInv) {
		spel.verplaatsVanWerkveldNaarInventory(xWerk, plaatsInv);
	}

	/**
	 * UC3 roept verplaatsVanWerkveldNaarWerkveld van spel aan.
	 * 
	 * @param xWerk
	 * @param plaatsWerk
	 */
	public void verplaatsVanWerkveldNaarWerkveld(int xWerk, int plaatsWerk) {
		spel.verplaatsVanWerkveldNaarWerkveld(xWerk, plaatsWerk);
	}

	/**
	 * UC3 roept verplaatsVanWerkveldNaarGemeenschappelijkVeld van spel aan.
	 * 
	 * @param plaatsWerk
	 * @param rij
	 * @param kolom
	 */
	public void verplaatsVanWerkveldNaarGemeenschappelijkVeld(int plaatsWerk, int rij, int kolom) {
		spel.verplaatsVanWerkveldNaarGemeenschappelijkVeld(plaatsWerk, rij, kolom);
	}

	/**
	 * UC3 roept verplaatsVanGemeenschappelijkNaarWerkveld van spel aan.
	 * 
	 * @param rij
	 * @param kolom
	 * @param plaatsWerk
	 */
	public void verplaatsVanGemeenschappelijkNaarWerkveld(int rij, int kolom, int plaatsWerk) {
		spel.verplaatsVanGemeenschappelijkNaarWerkveld(rij, kolom, plaatsWerk);
	}

	/**
	 * UC3 roept verplaatsVanGemeenschappelijkNaarGemeenschappelijk van spel aan.
	 * rijO = rij oorsprong en rijB = rij Bestemming.
	 * 
	 * @param rijO
	 * @param kolomO
	 * @param rijB
	 * @param kolomB
	 */
	public void verplaatsVanGemeenschappelijkNaarGemeenschappelijk(int rijO, int kolomO, int rijB, int kolomB) {
		spel.verplaatsVanGemeenschappelijkNaarGemeenschappelijk(rijO, kolomO, rijB, kolomB);
	}

	/**
	 * UC3 roept voegSteenToeIndienNodig van spel aan.
	 */
	public void voegSteenToeIndienNodig() {
		spel.voegSteenToeIndienNodig();
	}

	/**
	 * UC3 roept resetBeurt van spel aan.
	 */
	public void resetBeurt() {
		spel.resetBeurt();
	}

	/**
	 * UC3 roept isWerkveldLeeg van spel aan.
	 */
	public void isWerkveldLeeg() {
		spel.isWerkveldLeeg();
	}

	/**
	 * UC3 roept splitsRijOfSerie van spel aan.
	 * 
	 * @param rij1
	 * @param kolom1
	 * @param rij2
	 * @param kolom2
	 */
	public void splitsRijOfSerie(int rij1, int kolom1, int rij2, int kolom2) {
		spel.splitsRijOfSerie(rij1, kolom1, rij2, kolom2);
	}

	/**
	 * UC3 roept de methode sorteerInventory van spel aan.
	 */
	public void sorteerInventory() {
		spel.sorteerInventory();
	}
	
	/**
	 * UC4 roept updateScoreVanSpeler van spelerRepository aan.
	 * 
	 * @param gebruikersnaam
	 * @param toeTeVoegenScore
	 */
	public void updateScoreVanSpeler(String gebruikersnaam, int toeTeVoegenScore) {
		spelerRepository.updateScoreVanSpeler(gebruikersnaam, toeTeVoegenScore);
	}

	/**
	 * UC4 Deze methode gaat de methode geefNamenEnScoresDatabank aanroepen van de spelerRepository.
	 * @return
	 */
	public List<String> geefNamenEnScoresDatabank() {
		return spelerRepository.geefNamenEnScoresDatabank(geefGebruikersnamen());
	}
}
