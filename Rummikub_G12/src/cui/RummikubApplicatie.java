package cui;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import domein.DomeinController;
import exceptions.AanmeldenException;
import exceptions.SpelerBestaatAlException;

public class RummikubApplicatie {

	private final DomeinController dc;
	
	Scanner input = new Scanner(System.in);
	
	public RummikubApplicatie(DomeinController dc) {
		this.dc = dc;
	}
	
	public void start() {
		
		dc.stelTaalIn("Nederlands");
		
		int aantal = registreerAantal();
		
		meldAan(aantal);
		
		System.out.println(toonAangemeldeSpelers());
		
		int keuze = toonKeuzemogelijkhedenEnKies();
		
		if(keuze == 1) {
			dc.startNieuwSpel();
			speelSpel();
			toonScores();
		}
		else {
			System.out.println("Verder uitwerken in toekomstige UC.");
		}
	}

	private int registreerAantal() {
			
		int aantal = 0;
			
		while (true) {
			try {
				System.out.print(dc.geefTekstTaal("aantalSpelers") + " ");
				aantal = input.nextInt();
				dc.registreerAantal(aantal);
				return aantal;
			}
			catch (IllegalArgumentException e) {
				System.err.println(e.getMessage());
			}
			catch (InputMismatchException e) {
				System.err.println(dc.geefTekstTaal("tekstinvoerException"));
				input.nextLine();
			}
		}
	}
	
	private void meldAan(int aantal) {
		
		int index = 0;
		
		while (true) {
			try {
				for (int i = index; index < aantal; index++) {
					String gebruikersnaam = vraagGebruikersnaam(index + 1);
					String wachtwoord = vraagWachtwoord(gebruikersnaam);
					dc.meldAan(gebruikersnaam, wachtwoord);
				}
				break;
			}
			catch (AanmeldenException e) {
				System.err.println(dc.geefTekstTaal(e.getMessage()));
			}
			catch (SpelerBestaatAlException e) {
				System.out.println(dc.geefTekstTaal(e.getMessage()));
			}
		}
	}
	
	private String vraagGebruikersnaam(int i) {
		System.out.printf("%s %d? ", dc.geefTekstTaal("vraagGebruikersnaam"), i);
		String gebruikersnaam = input.next();
		return gebruikersnaam;
	}
	
	private String vraagWachtwoord(String gebruikersnaam) {
		System.out.printf("%s %s? ", dc.geefTekstTaal("vraagWachtwoord"), gebruikersnaam);
		String wachtwoord = input.next();
		return wachtwoord;
	}
	
	private String toonAangemeldeSpelers() {
		
		String output = String.format("%n%s:%n", dc.geefTekstTaal("aangemeldeSpelers"));
		List<String> aangemeldeSpelers = dc.geefGebruikersnamen();
		int index = 1;
		
		for (String s : aangemeldeSpelers) {
			output += String.format("%s %d: %s%n", dc.geefTekstTaal("speler"),index, s);
			index++;
		}
		
		return output;
	}
	
	private int toonKeuzemogelijkhedenEnKies() {
		while(true) {
			try {
				System.out.printf("%s:%n1. %s%n2. %s%n%s: ", dc.geefTekstTaal("keuzemogelijkheden"), dc.geefTekstTaal("speelSpel"), dc.geefTekstTaal("toonOverzicht"), dc.geefTekstTaal("geefKeuze"));
				int keuze = input.nextInt();
				System.out.println();
				if(keuze == 1) {
					return 1;
				}
				else if(keuze == 2) {
					return 2;
				}
				else {
					throw new IllegalArgumentException(dc.geefTekstTaal("keuzeException"));
				}
			}
			catch (IllegalArgumentException e) {
				System.err.println(e.getMessage());
				System.out.println();
			}
			catch (InputMismatchException e) {
				System.err.println(dc.geefTekstTaal("tekstinvoerException"));
				input.nextLine();
			}
		}
	}

	private void speelSpel() {
		
		int index = 0; //voorlopige var om een einde te kunnen simuleren
		
		while(!dc.isEindeSpel()) {
			System.out.printf("%s %s%n", dc.geefTekstTaal("beurtSpeler"), dc.geefNaamSpelerAanDeBeurt());
			index++;
			if(index == 10) {
				break;
			}
		}
		
		System.out.println();
	}

	private void toonScores() {
		List<Integer> scores = dc.geefScores();
		for(int s : scores) {
			System.out.println(s);
		}
	}
}
