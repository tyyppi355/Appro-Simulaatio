package simu.model;

import java.util.ArrayList;

import eduni.distributions.Distributions;
import simu.framework.Kello;
import simu.framework.Trace;


// TODO:
// Asiakas koodataan simulointimallin edellyttämällä tavalla (data!)
public class Asiakas {
	
	private double saapumisaika;
	private double poistumisaika;
	private int id;
	private static int i = 1;
	private static long sum = 0;
	
	private Distributions Generaattori = new Distributions();
	private double Odotusindeksi;
	private ArrayList<Jonoajat> jonoaikalista = new ArrayList<Jonoajat>();
	private int suorituspassi = 0;
	
	public Asiakas(){
	    id = i++;
	    Odotusindeksi = Generaattori.uniform(0, 1);
	    
		saapumisaika = Kello.getInstance().getAika();
		Trace.out(Trace.Level.INFO, "Uusi asiakas nro " + id + " saapui klo "+saapumisaika);
	}

	public double getPoistumisaika() {
		return poistumisaika;
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
	
	
	public double getOdotusindeksi() {
		return Odotusindeksi;
	}

	public void setOdotusindeksi(double odotusindeksi) {
		Odotusindeksi = odotusindeksi;
	}
	

	public ArrayList<Jonoajat> getJonoaikalista() {
		return jonoaikalista;
	}

	public void setJonoaikalista(String nimi) {
		Jonoajat b = new Jonoajat(nimi,poistumisaika - saapumisaika);
		jonoaikalista.add(b);
	}
	public String getBaarit(){
		String a = "";
		for(int i = 1;i < jonoaikalista.size();i++) {
			a += jonoaikalista.get(i).getBaari() + " " + jonoaikalista.get(i).getJonotusaika() + " " + "\n";
		}
		return a;
	}

	public void raportti(){
		Trace.out(Trace.Level.INFO, "\nAsiakas "+id+ " valmis! ");
		System.out.println(getBaarit());
		sum += (poistumisaika-saapumisaika);
		double keskiarvo = sum/id;
		System.out.println("Asiakkaiden läpimenoaikojen keskiarvo tähän asti "+ keskiarvo);
	}

	public int getSuorituspassi() {
		return suorituspassi;
	}

	public void setSuorituspassi() {
		suorituspassi++;
	}
	
	

}
