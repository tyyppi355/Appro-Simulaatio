package simu.model;

import java.util.HashMap;
import eduni.distributions.ContinuousGenerator;
import simu.framework.Kello;
import simu.framework.Trace;



/**
 * <p>Asiakas-luokka</p>
 * <p>Maarittelee asiakkaan</p>
 * <p>K‰ytet‰‰n asiakaan simuloinnissa</p>
 */
public class Asiakas {
	
	private double saapumisaika;
	private double poistumisaika;
	private int id;
	private static int i = 1;
	private long sum = 0;
	private double tyytyvaisyysIndeksi = 1000;
	private int jonoIndeksi;
	private int matkaaika = 0;
	private HashMap<String,Double> jonoaikalista = new HashMap<String,Double>();
	
	private int suorituspassi = 0;
	

	
	public Asiakas(ContinuousGenerator generator){
	    id = i++;
	    jonoIndeksi = (int) Math.round(generator.sample());
		saapumisaika = Kello.getInstance().getAika();
		Trace.out(Trace.Level.INFO, "Uusi asiakas nro " + id + " saapui klo "+saapumisaika);
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
		for(Double value : jonoaikalista.values()) {
			tulos += value;
		}
		return tulos;
	}
	
	// Tarkista onko asiakas k‰ynyt annetussa baarissa. K‰ytet‰‰n siirtym‰ luokassa.
	public boolean onkoBaarissaKayty(String baari) {
		if(jonoaikalista.containsKey(baari)) {
			return true;
		}
		return false;
	}
	
	public int baarissaKaytyListaPituus() {
		return jonoaikalista.size();
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


	public int getMatkaaika() {
		return matkaaika;
	}


	public void setMatkaaika(int matkaaika) {
		this.matkaaika += matkaaika;
	}
	
	
	

}
