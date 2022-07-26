/*
package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import domein.Speler;
import domein.SpelerRepository;
import exceptions.AanmeldenException;

class SpelerRepositoryTest {

	private SpelerRepository sr;
	
	@BeforeEach
	public void before() {
		sr = new SpelerRepository();
	}
	
	@ParameterizedTest
	@CsvSource(value = {"Gilles:123", "Tibo:123", "Aron:123", "Oliver:123"}, delimiter = ':')
	public void geefAangemeldeSpeler_geldigeNaamEnGebruikersnaam_returnSpeler(String gebruikersnaam, String wachtwoord) {
		Speler speler = new Speler(gebruikersnaam, wachtwoord);
		Assertions.assertEquals(speler.getGebruikersnaam(), sr.geefAangemeldeSpeler(gebruikersnaam, wachtwoord).getGebruikersnaam());
		Assertions.assertEquals(speler.getWachtwoord(), sr.geefAangemeldeSpeler(gebruikersnaam, wachtwoord).getWachtwoord());
	}
	
	@ParameterizedTest
	@CsvSource(value = {"AAAA:aaa", "Thomas:Thomas", " : ", "111 : 111"}, delimiter = ':')
	public void geefAangemeldeSpeler_ongeldigeNaamEnGebruikersnaam_AanmeldenException(String gebruikersnaam, String wachtwoord) {
		Assertions.assertThrows(AanmeldenException.class, () -> sr.geefAangemeldeSpeler(gebruikersnaam, wachtwoord));
	}
	
	@ParameterizedTest
	@NullAndEmptySource
	public void geefAangemeldeSpeler_NullNaamEnGebruikersnaam_AanmeldenException(String input) {
		Assertions.assertThrows(AanmeldenException.class, () -> sr.geefAangemeldeSpeler(input, input));
	}
	
}
*/