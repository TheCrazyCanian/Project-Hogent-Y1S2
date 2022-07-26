package domein;

import java.util.Arrays;

public class Rij {
	Steen[] stenenInRij;

	/**
	 * UC3 Constructor.
	 * @param stenen
	 */
	public Rij(Steen[] stenen) {
		setstenenInRij(stenen);
	}

	/**
	 * UC3 Deze methode geeft een rij terug.
	 * @return
	 */
	private Steen[] getstenenInRij() {
		return stenenInRij;
	}

	/**
	 * UC3 Deze methode stelt de rij in.
	 * @param stenenInGroep
	 */
	private void setstenenInRij(Steen[] stenenInGroep) {
		this.stenenInRij = stenenInGroep;
	}
	
	/**
	 * UC3 Deze methode controleert of de rij geldig is. Eerst wordt de lengte gecontroleerd en daarna wordt gecontroleerd of de stenen het zelfde getal hebben en een verschillende kleur.
	 * @return
	 */
	public boolean isGeldigeRij() {
		if (stenenInRij.length < 3 || stenenInRij.length > 4) {
			return false;
		}
		
		int[] getallen = new int[13];
		int[] kleuren = new int[KLEUR.values().length];
		
		for (Steen s : stenenInRij) {
			if (!s.isJoker()) {
				getallen[s.getGetal()-1]++;
				kleuren[s.getKleur().getNumberValue()]++;
			}			
		}
		
		if (Arrays.stream(getallen).filter(g -> g != 0).count() != 1) {
			return false;
		}
		if (Arrays.stream(kleuren).filter(g -> g > 1).count() > 0) {
			return false;
		}
		
		return true;	
	}
}