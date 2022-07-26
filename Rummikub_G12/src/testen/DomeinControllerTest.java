/*
package testen;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import domein.DomeinController;
import exceptions.AanmeldenException;
import exceptions.LeegVeldException;

class DomeinControllerTest {	
	
	private DomeinController dc;
	
	//UC1
	
	@BeforeEach
	public void before() {
		dc = new DomeinController();
		dc.stelTaalIn("Nederlands");
	}
	
	@Test
	public void DomeinController_maaktDomeinControllerAan() {
		DomeinController d = new DomeinController();
		Assertions.assertNotNull(d);
		Assertions.assertNotNull(d.getSpelers());
	}
	
	@ParameterizedTest
	@ValueSource(ints = {Integer.MIN_VALUE, -1, 0, 1, 5, 6, Integer.MAX_VALUE})
	public void registreerAantal_foutAantal_IllegalArgumentException(int getal) {
		String msg = dc.geefTekstTaal("aantalSpelersException");
		Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> dc.registreerAantal(getal), msg);
		Assertions.assertEquals(msg, exception.getMessage());
	}
	
	@ParameterizedTest
	@CsvSource(value = {"gilles:123:Gilles", "tibo:123:Tibo", "Aron:123:Aron", "OLIVER:123:Oliver"}, delimiter = ':')
	public void meldAan_geldigeNaamEnGebruikersnaam_meldDeSpelerAan(String gebruikersnaam, String wachtwoord, String expected) {
		dc.meldAan(gebruikersnaam, wachtwoord);
		Assertions.assertEquals(expected, dc.getSpelers().get(0).getGebruikersnaam());
		Assertions.assertEquals(wachtwoord, dc.getSpelers().get(0).getWachtwoord());
	}

	@ParameterizedTest
	@CsvSource(value = {"AAAA:aaa", "Thomas:Thomas", " : ", "111 : 111"}, delimiter = ':')
	public void meldAan_ongeldigeNaamEnGebruikersnaam_AanmeldenException(String gebruikersnaam, String wachtwoord) {
		Assertions.assertThrows(AanmeldenException.class, () -> dc.meldAan(gebruikersnaam, wachtwoord));
	}
	
	@ParameterizedTest
	@NullSource
	public void meldAan_NullNaamEnGebruikersnaam_AanmeldenException(String input) {
		Assertions.assertThrows(AanmeldenException.class, () -> dc.meldAan(input, input));
	}
	
	@ParameterizedTest
	@EmptySource
	public void meldAan_EmptyNaamEnGebruikersnaam_LeegVeldException(String input) {
		Assertions.assertThrows(LeegVeldException.class, () -> dc.meldAan(input, input));
	}
	
	@Test
	public void geefGebruikersnamen_geldigeGebruikersnamen_ListVanGebruikersnamen() {
		dc.meldAan("Gilles", "123");
		dc.meldAan("Tibo", "123");
		dc.meldAan("Oliver", "123");
		dc.meldAan("Aron", "123");
		List<String> gebruikersnamen = new ArrayList<>();
		String[] namen = {"Gilles" , "Tibo", "Oliver", "Aron"};
		for(String i : namen) {
			gebruikersnamen.add(i);
		}
		Assertions.assertEquals(gebruikersnamen, dc.geefGebruikersnamen());
	}
	
	//UC2
	
	@Test
	public void startNieuwSpel_vierSpelers_maaktSpelAan() {
		dc.meldAan("Gilles", "123");
		dc.meldAan("Tibo", "123");
		dc.meldAan("Oliver", "123");
		dc.meldAan("Aron", "123");
		dc.startNieuwSpel();
		Assertions.assertNotNull(dc.getSpel());
		Assertions.assertEquals(-1, dc.getSpel().getSpelerAanDeBeurt());
	}	
	
	@Test
	public void geefNaamSpelerAanDeBeurt_eersteBeurt_eersteSpeler() {
		dc.meldAan("Gilles", "123");
		dc.meldAan("Tibo", "123");
		dc.meldAan("Oliver", "123");
		dc.meldAan("Aron", "123");
		dc.startNieuwSpel();
		Assertions.assertEquals(dc.getSpel().getSpelers().get(0).getGebruikersnaam(), dc.geefNaamSpelerAanDeBeurt());
	}
	
	@Test
	public void geefNaamSpelerAanDeBeurt_twintigsteBeurt_vierdeSpeler() {
		dc.meldAan("Gilles", "123");
		dc.meldAan("Tibo", "123");
		dc.meldAan("Oliver", "123");
		dc.meldAan("Aron", "123");
		dc.startNieuwSpel();
		for(int i = 0; i < 19; i++)
		{
			dc.getSpel().bepaalNaamSpelerAanDeBeurt();
		}
		Assertions.assertEquals(dc.getSpel().getSpelers().get(3).getGebruikersnaam(), dc.geefNaamSpelerAanDeBeurt());
	}
	
	
	@ParameterizedTest
	@ValueSource(ints = {0, 1, 2, 3})
	public void isEindeSpel_spelerZonderStenen_returnTrue(int spelerZonderStenen) {
		dc.meldAan("Gilles", "123");
		dc.meldAan("Tibo", "123");
		dc.meldAan("Oliver", "123");
		dc.meldAan("Aron", "123");
		dc.startNieuwSpel();
		dc.getSpelers().get(spelerZonderStenen).setStenen(new ArrayList<>());
		Assertions.assertTrue(dc.isEindeSpel());
	}
	
	@Test
	public void isEindeSpel_spelersMetStenen_returntTrue() {
		dc.meldAan("Gilles", "123");
		dc.meldAan("Tibo", "123");
		dc.meldAan("Oliver", "123");
		dc.meldAan("Aron", "123");
		dc.startNieuwSpel();
		Assertions.assertFalse(dc.isEindeSpel());
	}
	
	@Test
	public void geefScores_vierSpelers_vierScores() {
		dc.meldAan("Gilles", "123");
		dc.meldAan("Tibo", "123");
		dc.meldAan("Oliver", "123");
		dc.meldAan("Aron", "123");
		dc.startNieuwSpel();
		for(Speler s : dc.getSpelers())
			s.bepaalScore();
		Assertions.assertEquals(dc.getSpelers().get(0).getScore(), dc.geefScores().get(0));
		Assertions.assertEquals(dc.getSpelers().get(1).getScore(), dc.geefScores().get(1));
		Assertions.assertEquals(dc.getSpelers().get(2).getScore(), dc.geefScores().get(2));
		Assertions.assertEquals(dc.getSpelers().get(3).getScore(), dc.geefScores().get(3));
	}
}
*/