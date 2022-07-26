package utility;

import java.util.Locale;
import java.util.ResourceBundle;

public class Taal {

	private String taal;
	private Locale l;
	private ResourceBundle rb;
	
	public Taal(String taal) {
		this.taal = taal;
		
		if (taal.equals("Nederlands")) {
			l = new Locale("nl", "BE");
			rb = ResourceBundle.getBundle("/utility/Nederlands", l);
		}
		else {
			l = new Locale("en", "UK");
			rb = ResourceBundle.getBundle("/utility/English", l);
		}
	}
	
	public String geefTekst(String key) {
		return rb.getString(key);
	}
}
