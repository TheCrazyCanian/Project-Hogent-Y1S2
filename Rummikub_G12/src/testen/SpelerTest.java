/*
package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import domein.Speler;

class SpelerTest {

	private Speler speler;
	
	@BeforeEach
	public void before() {
		speler = new Speler("Gilles", "123");
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"Gilles", "Tibo", "Aron", "Oliver"})
	public void setGebruikersnaam_geldigeGebruikersnaam_verandertGebruikersnaam(String gebruikersnaam) {
		speler.setGebruikersnaam(gebruikersnaam);
		Assertions.assertEquals(gebruikersnaam, speler.getGebruikersnaam());
	}
	
	@ParameterizedTest
	@NullAndEmptySource
	public void setGebruikersnaam_ongeldigeGebruikersnaam_verandertGebruikersnaam(String gebruikersnaam) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> speler.setGebruikersnaam(gebruikersnaam));
	}

	@ParameterizedTest
	@ValueSource(strings = {"123", "456", "Aron", "Oliver"})
	public void setWachtwoord_geldigeWachtwoord_verandertWachtwoord(String wachtwoord) {
		speler.setWachtwoord(wachtwoord);
		Assertions.assertEquals(wachtwoord, speler.getWachtwoord());		
	}
	
	@ParameterizedTest
	@NullAndEmptySource
	public void setWachtwoord_ongeldigeWachtwoord_verandertWachtwoord(String wachtwoord) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> speler.setWachtwoord(wachtwoord));
	}
	
	@ParameterizedTest
	@ValueSource(ints = {Integer.MIN_VALUE, -1, 0, 1,Integer.MAX_VALUE})
	public void setScore_geldigeScore_verandertScore(int score) {
		speler.setScore(score);
		Assertions.assertEquals(score, speler.getScore());
	}
	
	@Test
	public void setStenen_geldigeSteen_verandertStenen() {
		ImageView image = null;
		Steen steen = new Steen(5, "geel", true, 5, image);
		List<Steen> stenen = new ArrayList<>();
		stenen.add(steen);
		speler.setStenen(stenen);
		Assertions.assertEquals(steen.getGetal(), speler.getStenen().get(0).getGetal());
	}
	
	@Test
	public void nogStenenAanwezig_geenStenenAanwezig_returnFalse() {
		Assertions.assertFalse(speler.nogStenenAanwezig());
	}
	
	@Test
	public void nogStenenAanwezig_StenenAanwezig_returnTrue() {
		ImageView image = null;
		Steen steen = new Steen(5, "geel", true, 5, image);
		List<Steen> stenen = new ArrayList<>();
		stenen.add(steen);
		speler.setStenen(stenen);
		Assertions.assertTrue(speler.nogStenenAanwezig());
	}
	
	@ParameterizedTest
	@CsvSource(value = {"5:6:7:false", "20:35:60:true", "15:20:25:true"}, delimiter = ':')
	public void bepaalScore_stenenAanwezig_returnScore(int getal1, int getal2, int getal3, boolean joker) {
		ImageView image = null;
		List<Steen> stenen = new ArrayList<>();
		int totaal = getal1 + getal2 + getal3;
		Steen steen1 = new Steen(getal1, "geel", false, 5, image);
		stenen.add(steen1);
		Steen steen2 = new Steen(getal2, "geel", false, 5, image);
		stenen.add(steen2);
		Steen steen3 = new Steen(getal3, "geel", false, 5, image);
		stenen.add(steen3);
		if(joker == true) {
			Steen steen4 = new Steen(0, "geel", true, 5, image);
			stenen.add(steen4);
			totaal += 25;
		}
		speler.setStenen(stenen);	
		speler.bepaalScore();
		Assertions.assertEquals(-totaal, speler.getScore());
	}
	
	@Test
	public void bepaalScore_geenStenenAanwezig_returnScore() {
		speler.bepaalScore();
		Assertions.assertEquals(0, speler.getScore());
	}
}
*/