/*
package testen;

import org.graalvm.compiler.debug.Assertions;

import domein.DomeinController;
import domein.Spel;
import domein.Speler;

public class SpelTest {

	private Spel spel;
	private DomeinController dc;
	
	@BeforeEach
	public void before() {
		dc = new DomeinController();
		dc.stelTaalIn("Nederlands");
		dc.meldAan("Gilles", "123");
		dc.meldAan("Tibo", "123");
		dc.meldAan("Oliver", "123");
		dc.meldAan("Aron", "123");
		dc.startNieuwSpel();
		spel = dc.getSpel();
	}
	
	@Test
	public void spel_geldigArgument_maaktSpelAan() {
		Spel s = new Spel(dc.getSpelers());
		Assertions.assertNotNull(s);
		Assertions.assertEquals(-1, s.getSpelerAanDeBeurt());
		Assertions.assertNotNull(s.getSpelers());
		Assertions.assertNotNull(s.getStenen());
	}
	
	@Test
	public void maakStenenAan_maakt106StenenAan() {
		int totaalStenen = 0;
		for(Speler s : dc.getSpelers()) {
			totaalStenen += s.getStenen().size();
		}
		totaalStenen += spel.getStenen().size();
		Assertions.assertEquals(106, totaalStenen);
	}
	
	@Test
	public void verdeelStenen_geeftElkeSpeler14Stenen() {
		for(Speler s : dc.getSpelers()) {
			Assertions.assertEquals(14, s.getStenen().size());
		}
	}
	
	@Test
	public void geefStenenSpelers_vierSpelers_return56stenen() {
		//Aangezien er 56 stenen gerturned waren, zijn deze verwijdert uit de lijst "stenen" en blijven er daar nog 50 stenen over
		Assertions.assertEquals(50, spel.getStenen().size());            
	}
	
	@ParameterizedTest
	@ValueSource(ints = {1, 5, 30})
	public void bepaalNaamSpelerAanDeBeurt_vierSpelers_returnNaamSpelerAanDeBeurt(int aantalBeurten) {
		String naam = "";
		for(int i = 0; i <= aantalBeurten; i++)
			naam = spel.bepaalNaamSpelerAanDeBeurt();
		int spelerAanDeBeurt = (aantalBeurten % 4);
		Assertions.assertEquals(dc.getSpelers().get(spelerAanDeBeurt).getGebruikersnaam(), naam);
	}
}
*/