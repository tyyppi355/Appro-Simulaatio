package application;


import simu.model.Matka;
import simu.model.Palvelupiste;
import simu.model.Tietokanta;

public class Main { // Simulaattorin käynnistyspääohjelma
	
	public static void main(String args[]) {
		
		Tietokanta yeet = new Tietokanta();
		Palvelupiste p = new Palvelupiste("Dondo",60.2219389088197,24.81443193360821);
		yeet.createMatka(new Matka("dondo",655,255));
		yeet.createPalvelupiste(p);
		view.SimulaattorinGUI.main(args);
		
	}

}
