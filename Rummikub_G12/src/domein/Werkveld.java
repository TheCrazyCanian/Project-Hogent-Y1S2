package domein;

import exceptions.BezetVakjeException;
import exceptions.WerkveldNietLeegException;

public class Werkveld {

	private Steen[] stenenInWerkveld;

	/**
	 * UC3 contructor werkveld
	 */
	public Werkveld() {
		this.stenenInWerkveld = new Steen[23];
	}

	/**
	 * UC3 returnt de stenen in het werkveld.
	 * 
	 * @return
	 */
	public Steen[] getStenenInWerkveld() {
		return stenenInWerkveld;
	}

	/**
	 * UC3 Deze methode gaat kijken of de meegegeven steen op de plaats kan gelegd
	 * worden. Indien de plaats bezet is, gooit hij een exceptie. Indien de plaats
	 * niet bezet is dan gaat hij de steen op die plaats leggen.
	 * 
	 * @param steen
	 * @param plaats
	 */
	public void verplaatsSteenNaarWerkveld(Steen steen, int plaats) {
		if (stenenInWerkveld[plaats] != null) {
			throw new BezetVakjeException("bezetVakjeException");
		} else {
			stenenInWerkveld[plaats] = steen;
		}
	}

	/**
	 * UC3 Deze methode returnt de Steen op de meegegeven plaats. Hij gaat deze
	 * plaats dan ook de waarde null geven.
	 * 
	 * @param plaats
	 * @return
	 */
	public Steen haalSteenUitWerkveld(int plaats) {
		Steen tempSteen = stenenInWerkveld[plaats];
		stenenInWerkveld[plaats] = null;
		return tempSteen;
	}

	/**
	 * UC3 Deze methode gaat kijken of alle stenen uit het werkveld zijn, en dus
	 * controleren of het werkveld leeg is. Hij gooit een exceptie als deze niet
	 * leeg is.
	 */
	public void isWerkveldLeeg() {
		for (int i = 0; i < stenenInWerkveld.length; i++) {
			if (stenenInWerkveld[i] != null)
				throw new WerkveldNietLeegException("werkveldError");
		}
	}
}
