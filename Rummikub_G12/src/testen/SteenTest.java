/*
package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domein.DomeinController;
import domein.Spel;
import domein.Steen;
import javafx.scene.image.ImageView;

public class SteenTest {
	
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
	}
	
	@Test
	public void Steen_maaktSteenAan() {
		ImageView image = null;
		Steen steen = new Steen(5, "geel", true, 5, image);
		Assertions.assertNotNull(steen);
		Assertions.assertEquals(5, steen.getGetal());
		Assertions.assertEquals("geel", steen.getKleur());
	}
}
*/