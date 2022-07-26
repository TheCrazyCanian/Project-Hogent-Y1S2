package domein;

import javafx.scene.image.ImageView;

public class Steen implements Comparable<Steen> {

	private int getal;
	private KLEUR kleur;
	private boolean isJoker;
	private int id;
	private ImageView image;

	/**
	 * UC2 Constructor
	 * 
	 * @param getal
	 * @param kleur
	 * @param isJoker
	 * @param id
	 * @param image
	 */
	public Steen(int getal, KLEUR kleur, boolean isJoker, int id, ImageView image) {
		this.getal = getal;
		this.kleur = kleur;
		this.isJoker = isJoker;
		this.id = id;
		this.image = image;
	}

	/**
	 * UC2 Geef het getal van de steen terug.
	 * 
	 * @return
	 */
	public int getGetal() {
		return getal;
	}

	/**
	 * UC2 Geef het kleur van de steen terug.
	 * 
	 * @return
	 */
	public KLEUR getKleur() {
		return kleur;
	}

	/**
	 * UC2 Geef terug of een steen al dan niet een joker is.
	 * 
	 * @return
	 */
	public boolean isJoker() {
		return isJoker;
	}

	/**
	 * UC2 Geeft de image verbonden aan de steen terug.
	 * 
	 * @return
	 */
	public ImageView getImage() {
		return image;
	}

	/**
	 * UC2 Geef het id van de steen terug.
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * UC3 sorteert de stenen eerst op nummer
	 */
	@Override
	public int compareTo(Steen s) { 
		return this.getal - s.getGetal();
	}
}
