package simu.model;

import java.util.ArrayList;
import java.util.HashMap;

import eduni.distributions.ContinuousGenerator;
import eduni.distributions.Distributions;
import simu.framework.Kello;
import simu.framework.Trace;


// TODO:
// Asiakas koodataan simulointimallin edellytt√§m√§ll√§ tavalla (data!)
public class Asiakas {
	
	private double saapumisaika;
	private double poistumisaika;
	private int id;
	private static int i = 1;
	private long sum = 0;
	private double tyytyvaisyysIndeksi = 1000;
	private int jonoIndeksi;

	
	private ContinuousGenerator Generaattori;
	private int odotusindeksi;
	private HashMap<String,Double> jonoaikalista = new HashMap<String,Double>();
	
	private int suorituspassi = 0;
	

	
	public Asiakas(ContinuousGenerator generator){
	    id = i++;
	    odotusindeksi = (int) Math.round(generator.sample());
		saapumisaika = Kello.getInstance().getAika();
		Trace.out(Trace.Level.INFO, "Uusi asiakas nro " + id + " saapui klo "+saapumisaika);
	}

	public int getOdotusindeksi(){
		return odotusindeksi;
	}
	
	public double getTyytyvaisyysIndeksi() {
		return this.tyytyvaisyysIndeksi;
	}
	
	public void laskeTyytyvaisyytta(double aika) {
		aika = Math.round(aika);
		this.tyytyvaisyysIndeksi -= aika;
	}
	
	public void nostaTyytyvaisyytta() {
		this.tyytyvaisyysIndeksi += 50;
	}
	
	public double getPoistumisaika() {
		return poistumisaika;
	}
	
	public int getJonoIndeksi() {
		return jonoIndeksi;
	}

	public void setPoistumisaika(double poistumisaika) {
		this.poistumisaika = poistumisaika;
		
	}

	public double getSaapumisaika() {
		return saapumisaika;
	}

	public void setSaapumisaika(double saapumisaika) {
		this.saapumisaika = saapumisaika;
	}
	
	public int getId() {
		return id;
	}

	public HashMap<String,Double> getJonoaikalista() {
		return jonoaikalista;
	}
	
	public double getKeskimaarainenJonotusaika() {
		double tulos = 0;
		for(var entry : jonoaikalista.entrySet()) {
			tulos += entry.getValue();
		}
		return tulos;
	}
	
	// Tarkista onko asiakas k‰ynyt annetussa baarissa. K‰ytet‰‰n siirtym‰ luokassa.
	public boolean onkoBaarissaKayty(String baari) {
		for(var entry : jonoaikalista.entrySet()) {
			if(entry.getKey().equals(baari)) {
				return true;
			}
		}
		return false;
	}

	public void setJonoaikalista(String nimi) {
		Double jonotusaika = poistumisaika - saapumisaika;
		laskeTyytyvaisyytta(jonotusaika);
		
		
		jonoaikalista.put(nimi,jonotusaika);
	}
	
	
	
	

	public void raportti(){
		Trace.out(Trace.Level.INFO, "\nAsiakas "+id+ " valmis! ");
		
		sum += (poistumisaika-saapumisaika);
		
		double keskiarvo = sum/id;
		System.out.println("Asiakkaiden l√§pimenoaikojen keskiarvo t√§h√§n asti "+ keskiarvo);
	}

	public int getSuorituspassi() {
		return suorituspassi;
	}

	public void setSuorituspassi() {
		suorituspassi++;
	}
	
	

}
