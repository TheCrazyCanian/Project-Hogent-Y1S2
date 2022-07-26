package domein;

import java.util.List;

import exceptions.AanmeldenException;
import persistentie.SpelerMapper;

public class SpelerRepository {

	private final SpelerMapper mapper;

	/**
	 * UC1 Constructor
	 */
	public SpelerRepository() {
		mapper = new SpelerMapper();
	}

	/**
	 * UC1 Roept een speler op via de methode geefSpeler uit de persistentie package
	 * en slaat deze lokaal op. Wanneer een speler null is wordt een exceptie
	 * gegooid.
	 * 
	 * @param gebruikersnaam
	 * @param wachtwoord
	 * @return
	 * @throws AanmeldenException
	 */
	public Speler geefAangemeldeSpeler(String gebruikersnaam, String wachtwoord) throws AanmeldenException {

		Speler speler = mapper.geefSpeler(gebruikersnaam, wachtwoord);

		if (speler != null) {
			return speler;
		} else {
			throw new AanmeldenException("aanmeldenException");
		}
	}

	/**
	 * UC4 Roept de methode updateScoreVanSpeler aan uit de persistentie package.
	 * 
	 * @param gebruikersnaam
	 * @param toeTeVoegenScore
	 */
	public void updateScoreVanSpeler(String gebruikersnaam, int toeTeVoegenScore) {
		mapper.updateScoreVanSpeler(gebruikersnaam, toeTeVoegenScore);
	}

	/**
	 * UC4 Roept de methode geefNamenEnScoresDatabank aan uit de persistentie
	 * package.
	 * 
	 * @param gebruikersnamen
	 * @return
	 */
	public List<String> geefNamenEnScoresDatabank(List<String> gebruikersnamen) {
		return mapper.geefNamenEnScoresDatabank(gebruikersnamen);
	}
}
