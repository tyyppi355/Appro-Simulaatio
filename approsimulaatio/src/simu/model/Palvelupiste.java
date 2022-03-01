package simu.model;

import java.util.LinkedList;
import java.util.PriorityQueue;

import eduni.distributions.ContinuousGenerator;
import simu.framework.Kello;
import simu.framework.Tapahtuma;
import simu.framework.Tapahtumalista;
import simu.framework.Trace;

// TODO:
// Palvelupistekohtaiset toiminnallisuudet, laskutoimitukset (+ tarvittavat muuttujat) ja raportointi koodattava
public class Palvelupiste {

	private LinkedList<Asiakas> jono = new LinkedList<Asiakas>(); // Tietorakennetoteutus
	private LinkedList<Asiakas> sisalla = new LinkedList<Asiakas>();
	private ContinuousGenerator generator;
	private Tapahtumalista tapahtumalista;
	private TapahtumanTyyppi skeduloitavanTapahtumanTyyppi;
	private String baarinnimi;
	private int montaKertaaKayty = 0;
	
	//JonoStartegia strategia; //optio: asiakkaiden j√§rjestys
	


	public Palvelupiste(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi,String n){
		this.tapahtumalista = tapahtumalista;
		this.generator = generator;
		this.skeduloitavanTapahtumanTyyppi = tyyppi;
		baarinnimi = n;
				
	}


	public void lisaaJonoon(Asiakas a){   // Jonon 1. asiakas aina palvelussa
		
		System.out.println("*************");
		System.out.println();
		System.out.println(baarinnimi + " jono: " + jono.size());
		System.out.println(baarinnimi + ": " + sisalla.size());
		System.out.println();
		
		jono.add(a);
		a.setSaapumisaika(Kello.getInstance().getAika());
		
		
	}

	public Asiakas otaJonosta(){
		return jono.poll();
		
	}

	public void aloitaPalvelu(){  //Aloitetaan uusi palvelu, asiakas on jonossa palvelun aikana
		
		jono.getFirst().setPoistumisaika(Kello.getInstance().getAika());
		jono.getFirst().setJonoaikalista(baarinnimi);
		Asiakas a = jono.poll();
		sisalla.add(a);
		
		Trace.out(Trace.Level.INFO, "Aloitetaan uusi palvelu asiakkaalle " + a.getId());
		
		montaKertaaKayty++;

		a.setSuorituspassi();
		double palveluaika = generator.sample();
		/*if(a.getSuorituspassi() == 3) {
			tapahtumalista.lisaa(new Tapahtuma(TapahtumanTyyppi.OUT,Kello.getInstance().getAika()+palveluaika));
		}else {*/
			tapahtumalista.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi,Kello.getInstance().getAika()+palveluaika));
		//}
		
	}
	
	public int getMontaSisalla() {
		return this.sisalla.size();
	}
	public int getMontaJonossa() {
		return this.jono.size();
	}
	
	public Asiakas otaSis‰lt‰(){  // Poistetaan palvelussa ollut
		return sisalla.poll();
	}


	public int getMontaKertaaKayty() {
		return montaKertaaKayty;
	}


	public boolean onVarattu(){
		
		if(sisalla.size() == 10) {
			return true;
		} else {
			return false;
		}
	}


	public boolean onJonossa(){
		return !jono.isEmpty();
	}


	public String getBaarinnimi() {
		return baarinnimi;
	}


	public void setBaarinnimi(String baarinnimi) {
		this.baarinnimi = baarinnimi;
	}


	

}
