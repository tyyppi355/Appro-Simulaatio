package testi;
import simu.framework.*;
import simu.framework.Trace.Level;
import simu.model.Asiakas;
import simu.model.OmaMoottori;

public class Simulaattori { //Tekstipohjainen

	public static void main(String[] args) {
		
		Trace.setTraceLevel(Level.INFO);
		Moottori m = new OmaMoottori();
		m.setSimulointiaika(1000);
		m.aja();
		Asiakas riku = new Asiakas();
		Asiakas riku2 = new Asiakas();
		Asiakas riku3 = new Asiakas();
		Asiakas riku4 = new Asiakas();
		Asiakas riku5 = new Asiakas();
		Asiakas riku6 = new Asiakas();
		
		System.out.println(riku.getOdotusindeksi());
		System.out.println(riku2.getOdotusindeksi());
		System.out.println(riku3.getOdotusindeksi());
		System.out.println(riku4.getOdotusindeksi());
		System.out.println(riku5.getOdotusindeksi());
		System.out.println(riku6.getOdotusindeksi());
		
	}
}
