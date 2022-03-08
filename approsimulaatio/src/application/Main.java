package application;


import eduni.distributions.Normal;
import simu.model.Matka;
import simu.model.Palvelupiste;
import simu.model.Reittiopas;
import simu.model.TapahtumanTyyppi;
import simu.model.Tietokanta;

public class Main { // Simulaattorin käynnistyspääohjelma
	
	public static void main(String args[]) {
		
		Matka riku = Reittiopas.getDistance("bruv",60.26089854180149 , 24.854330464933437, 60.22202448765425, 24.81645231120877);
		
		System.out.println(riku.getAika());
		System.out.println(riku.getNimi());
		
		view.SimulaattorinGUI.main(args);
		
	}

}
