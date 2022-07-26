package domein;

import exceptions.BezetVakjeException;

public class Speler  {

	private String gebruikersnaam;
	private String wachtwoord;
	private int score;
	private Steen[] stenen;
	private boolean eersteBeurt;

	/**
	 * UC1 Constructor 1
	 * 
	 * @param gebruikersnaam
	 * @param wachtwoord
	 */
	public Speler(String gebruikersnaam, String wachtwoord) {
		setGebruikersnaam(gebruikersnaam);
		setWachtwoord(wachtwoord);
		this.stenen = new Steen[53];
		this.eersteBeurt = true;
	}

	/**
	 * UC1 Constructor 2
	 * 
	 * @param gebruikersnaam
	 * @param score
	 */
	public Speler(String gebruikersnaam, int score) {
		setGebruikersnaam(gebruikersnaam);
		setScore(score);
	}

	/**
	 * UC1 Geeft de gebruikersnaam van speler terug.
	 * 
	 * @return
	 */
	public String getGebruikersnaam() {
		return gebruikersnaam;
	}

	/**
	 * UC1 Bij het aanroepen van methode wordt de gebruikersnaam ingesteld. Indien
	 * de gebruikersnaam leeg is, wordt er een exceptie gegooid. (Wordt enkel
	 * gebruikt voor de testen)
	 * 
	 * @param gebruikersnaam
	 * @throws IllegalArgumentException
	 */
	public void setGebruikersnaam(String gebruikersnaam) throws IllegalArgumentException {
		if (gebruikersnaam == null || gebruikersnaam.isEmpty()) {
			throw new IllegalArgumentException("gebruikersnaam");
		}
		this.gebruikersnaam = gebruikersnaam;
	}

	/**
	 * UC1 Bij het aanroepen van deze methode wordt het wachtwoord ingesteld. Indien
	 * het wachtwoord leeg is, wordt er een exceptie gegooid. (Wordt enkel gebruikt
	 * voor de testen)
	 * 
	 * @param wachtwoord
	 * @throws IllegalArgumentException
	 */
	public void setWachtwoord(String wachtwoord) throws IllegalArgumentException {
		if (wachtwoord == null || wachtwoord.isEmpty()) {
			throw new IllegalArgumentException("wachtwoord");
		}
		this.wachtwoord = wachtwoord;
	}

	/**
	 * UC1 geeft de waarde van wachtwoord terug. (Wordt enkel gebruikt voor de
	 * testen)
	 * 
	 * @return
	 */
	public String getWachtwoord() {
		return wachtwoord;
	}

	/**
	 * UC1 Geeft de waarde van score terug.
	 * 
	 * @return
	 */
	public int getScore() {
		return score;
	}

	/**
	 * UC1 Bij het aanroepen van deze methode wordt de score ingesteld.
	 * 
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * UC1 Geeft de stenen van de speler terug in een array.
	 * 
	 * @return
	 */
	public Steen[] getStenen() {
		return stenen;
	}

	/**
	 * UC1 Bij het aanroepen van deze methode worden de stenen ingesteld.
	 * 
	 * @param stenen
	 */
	public void setStenen(Steen[] stenen) {
		this.stenen = stenen;
	}

	/**
	 * UC2 Deze methode controleert of er nog stenen aanwezig zijn in het inventory.
	 * Wanneer een null waarde gevonden wordt, wordt true teruggeven.
	 * 
	 * @return
	 */
	public boolean nogStenenAanwezig() {
		boolean nogAanwezig = false;
		for (Steen i : stenen) {
			if (i != null) {
				nogAanwezig = true;
				break;
			}
		}
		return nogAanwezig;
	}

	/**
	 * UC2 Op basis van de stenen die nog aanwezig zijn in het inventory van de
	 * speler wordt de score berekend. Een joker telt voor een score van 25. De som
	 * van de score wordt negatief terug gegeven.
	 */
	public void bepaalScore() {

		int score = 0;

		for (Steen s : stenen) {

			if (s == null) {
				continue;
			}

			if (!s.isJoker()) {
				score += s.getGetal();
			} else {
				score += 25;
			}
		}

		setScore(-score);
	}

	/**
	 * UC3 Deze methode aanvaard een steen en wordt geplaast op een beschikbaar
	 * vakje in het inventory. Wanneer het vakje reeds bezet is, wordt er een
	 * exceptie gegooid.
	 * 
	 * @param steen
	 * @param plaats
	 */
	public void verplaatsSteenNaarInventory(Steen steen, int plaats) {
		if (stenen[plaats] != null) {
			throw new BezetVakjeException("bezetVakjeException");
		} else {
			stenen[plaats] = steen;
		}
	}

	/**
	 * UC3 Geeft de waarde van eersteBeurt terug
	 * 
	 * @return
	 */
	public boolean getEersteBeurt() {
		return eersteBeurt;
	}

	/**
	 * UC3 Bij het aanroepen van deze methode wordt de waarde van eersteBeurt
	 * ingesteld.
	 * 
	 * @param eersteBeurt
	 */
	public void setEersteBeurt(boolean eersteBeurt) {
		this.eersteBeurt = eersteBeurt;
	}

	/**
	 * UC3 verwijderd een specifieke steen uit het inventory op basis van de
	 * parameter plaats.
	 * 
	 * @param plaats
	 */
	public void verwijderSteenUitInventory(int plaats) {
		stenen[plaats] = null;
	}
}
