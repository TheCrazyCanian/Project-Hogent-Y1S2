package domein;

public class Serie {
	Steen[] stenenInSerie;

	/**
	 * UC3 Constructor 
	 * @param stenen
	 */
	public Serie(Steen[] stenen) {
		setStenenInSerie(stenen);
	}

	/**
	 * UC3 Deze methode geeft de serie terug.
	 * @return
	 */
	private Steen[] getStenenInSerie() {
		return stenenInSerie;
	}

	/** 
	 * UC3 Deze methode stelt de serie in.
	 * @param stenenInGroep
	 */
	private void setStenenInSerie(Steen[] stenenInGroep) {
		this.stenenInSerie = stenenInGroep;
	}
	
	/**
	 * UC3 Deze methode controleert eerst of de serie niet kleiner is dan 3. Daarna wordt gecontroleerd of een joker niet voor een 1 of na een 13 wordt gezet.
	 * Tot slot worden de getallen in de serie gecontroleerd.
	 * @return
	 */
	public boolean isGeldigeSerie() {
		if (stenenInSerie.length < 3) {
			return false;
		}
		
		for (int teller = 1; teller < stenenInSerie.length; teller++) { 
			if (stenenInSerie[teller].isJoker()) {
				
				//als joker op het eind van de rij mag de vorige geen 13 zijn
				if(stenenInSerie[teller-1].getGetal() == 13) {
					return false;
				}
				continue;
			}
			
			if (stenenInSerie[teller-1].isJoker()) {
				if (stenenInSerie[teller].getGetal() == 1) {
					return false;
				}
			}
			
			if (stenenInSerie[teller-1].isJoker()) {
				if (teller - 1 == 0) {
					continue;
				}
				if (stenenInSerie[teller-2].getGetal() + 2 != stenenInSerie[teller].getGetal() || 
						stenenInSerie[teller-2].getKleur() != stenenInSerie[teller].getKleur()) {
					return false;
				}
			} else {
				if (stenenInSerie[teller-1].getGetal() + 1 != stenenInSerie[teller].getGetal() || 
						stenenInSerie[teller-1].getKleur() != stenenInSerie[teller].getKleur()) {
					return false;
				}
			}
		}
		return true;
	}
}
