package domein;

import exceptions.BezetVakjeException;

public class GemeenschappelijkVeld {

	private Steen[][] stenenInGemeenschappelijkVeld;

	/**
	 * UC3 constructor 1
	 */
	public GemeenschappelijkVeld() {
		this(new Steen[7][29]);
	}

	/**
	 * UC3 constructor 2
	 * 
	 * @param stenen
	 */
	public GemeenschappelijkVeld(Steen[][] stenen) {
		setStenenInGemeenschappelijkVeld(stenen);
	}

	/**
	 * UC3 Returnt de stenen in het gemeenschappelijk veld.
	 * 
	 * @return
	 */
	public Steen[][] getStenenInGemeenschappelijkVeld() {
		return stenenInGemeenschappelijkVeld;
	}

	/**
	 * UC3 Zet de paramater stenenInGemeenschappelijkVeld op de meegegeven waarde
	 * 
	 * @param stenenInGemeenschappelijkVeld
	 */
	public void setStenenInGemeenschappelijkVeld(Steen[][] stenenInGemeenschappelijkVeld) {
		this.stenenInGemeenschappelijkVeld = stenenInGemeenschappelijkVeld;
	}

	/**
	 * UC3 Deze methode gaat de meegegeven steen plaatsen in het gemeenschappelijk
	 * veld op de juiste plaats. Eerst wordt gecontroleerd of de plaats leeg is en
	 * indien nodig een exceptie gegooid. Daarna wordt de steen op de juiste plaats
	 * gelegd.
	 * 
	 * @param steen
	 * @param rij
	 * @param kolom
	 */
	public void verplaatsSteenNaarGemeenschappelijkVeld(Steen steen, int rij, int kolom) {
		if (stenenInGemeenschappelijkVeld[rij][kolom] != null) {
			throw new BezetVakjeException("bezetVakjeException");
		} else {
			stenenInGemeenschappelijkVeld[rij][kolom] = steen;
		}
	}

	/**
	 * Deze methode gaat een steen uit het gemeenschappelijkVeld halen, en op die
	 * plaats de waarde op null zetten.
	 * 
	 * @param rij
	 * @param kolom
	 * @return
	 */
	public Steen haalSteenUitGemeenschappelijkVeld(int rij, int kolom) {
		Steen tempSteen = stenenInGemeenschappelijkVeld[rij][kolom];
		stenenInGemeenschappelijkVeld[rij][kolom] = null;
		return tempSteen;
	}

	/**
	 * Deze methode geeft een steen terug die op de meegegeven plaats ligt.
	 * 
	 * @param rij
	 * @param kolom
	 * @return
	 */
	public Steen getSteenUitGemeenschappelijkVeld(int rij, int kolom) {
		Steen tempSteen = stenenInGemeenschappelijkVeld[rij][kolom];
		return tempSteen;
	}
}
