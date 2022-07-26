package domein;

import exceptions.BezetVakjeException;

public class Beurt {

	private Werkveld werkveld;

	/**
	 * UC3 constructor beurt
	 */
	public Beurt() {
		this.werkveld = new Werkveld();
	}

	/**
	 * UC3 returnt de stenen in werkveld.
	 * 
	 * @return
	 */
	public Steen[] getStenenInWerkveld() {
		return werkveld.getStenenInWerkveld();
	}

	/**
	 * UC3 roept de methode verplaatsSteenNaarWerkveld aan van werkveld met de steen
	 * en de plaats.
	 * 
	 * @param teVerplaatsenSteen
	 * @param plaatsWerk
	 */
	public void verplaatsVanInventoryNaarWerkveld(Steen teVerplaatsenSteen, int plaatsWerk) {
		werkveld.verplaatsSteenNaarWerkveld(teVerplaatsenSteen, plaatsWerk);
	}

	/**
	 * UC3 roept de methode haalSteenUitWerkveld aan van werkveld. Deze returnt een
	 * Steen uit het werkveld die op plaats xWerk lag.
	 * 
	 * @param xWerk
	 * @return
	 */
	public Steen verplaatsVanWerkveldNaarInventory(int xWerk) {
		return werkveld.haalSteenUitWerkveld(xWerk);
	}

	/**
	 * UC3 Deze methode zorgt voor het verplaatsen van stenen in het werkveld. Eerst
	 * wordt er gekeken of het vakje bezet is, indien dit niet het geval is, dan
	 * worden de methode verplaatsSteenNaarWerkveld(met de steen van de methode
	 * haalSteenUitWerkveld en de plaats) aangeroepen. Indien het vakje wel bezet is
	 * gooit het een exceptie.
	 * 
	 * @param xWerk
	 * @param plaatsWerk
	 */
	public void verplaatsVanWerkveldNaarWerkveld(int xWerk, int plaatsWerk) {
		if (werkveld.getStenenInWerkveld()[plaatsWerk] != null) {
			throw new BezetVakjeException("bezetVakjeException");
		}
		Steen teVerplaatsenSteen = werkveld.haalSteenUitWerkveld(xWerk);
		werkveld.verplaatsSteenNaarWerkveld(teVerplaatsenSteen, plaatsWerk);
	}

	/**
	 * UC3 Deze methode kijkt of de Steen op die plaats kan gelegd worden. Indien de
	 * plaats bezet is, gooit hij een exceptie en als de plaats wel vrij is dan
	 * roept hij de methode verplaatsSteenNaarWerkveld aan van werkveld met de te
	 * verplaatsen steen en de plaats.
	 * 
	 * @param teVerplaatsenSteen
	 * @param plaatsWerk
	 */
	public void verplaatsVanGemeenschappelijkNaarWerkveld(Steen teVerplaatsenSteen, int plaatsWerk) {
		if (werkveld.getStenenInWerkveld()[plaatsWerk] != null) {
			throw new BezetVakjeException("bezetVakjeException");
		}
		werkveld.verplaatsSteenNaarWerkveld(teVerplaatsenSteen, plaatsWerk);
	}

	/**
	 * UC3 Deze methode roept de methode haalSteenUitWerkveld aan van werkveld met
	 * de plaats.
	 * 
	 * @param plaatsWerk
	 * @return
	 */
	public Steen verplaatsVanWerkveldNaarGemeenschappelijkVeld(int plaatsWerk) {
		return werkveld.haalSteenUitWerkveld(plaatsWerk);
	}

	/**
	 * UC3 Deze methode roept de methode IsWerkveldLeeg aan van werkveld.
	 */
	public void isWerkveldLeeg() {
		werkveld.isWerkveldLeeg();
	}
}
