package domein;

public enum KLEUR {
	ZWART(0),
	GEEL(1),
	ROOD(2), 
	BLAUW(3);

	private int numberValue;
	
	/**
	 * UC2 Deze methode stelt de waarde van kleur in. 
	 * @param numberValue
	 */
	KLEUR(int numberValue) {
		this.numberValue = numberValue;
	}

	/**
	 * UC2 Deze methode returned de numberValue van kleur.
	 * @return
	 */
	public int getNumberValue() {
		return numberValue;
	}
}
