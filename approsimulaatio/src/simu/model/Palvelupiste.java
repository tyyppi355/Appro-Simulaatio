package simu.model;


import java.util.LinkedList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import eduni.distributions.ContinuousGenerator;
import simu.framework.Kello;
import simu.framework.Tapahtuma;
import simu.framework.Tapahtumalista;
import simu.framework.Trace;

// Palvelupistekohtaiset toiminnallisuudet, laskutoimitukset (+ tarvittavat muuttujat) ja raportointi koodattava
@Entity
@Table(name="Palvelupiste")
/**
 * <p>Palvelupiste-luokka</p>
 * <p>K‰ytet‰‰n tietokannassa(taulu) ja javassa(olio)</p>
 * 
 */
public class Palvelupiste {
	@Transient
	private LinkedList<Asiakas> jono = new LinkedList<Asiakas>(); // Tietorakennetoteutus
	@Transient
	private LinkedList<Asiakas> sisalla = new LinkedList<Asiakas>();
	@Transient
	private ContinuousGenerator generator;
	@Transient
	private Tapahtumalista tapahtumalista;
	@Transient
	private TapahtumanTyyppi skeduloitavanTapahtumanTyyppi;
	@Id
	@Column(name ="nimi")
	private String baarinnimi;
	@Transient
	private int montaKertaaKayty = 0;
	@Column(name ="lat")
	private double lat;
	@Column(name ="lon")
	private double lon;
	@Transient
	private int koko;

	// JonoStartegia strategia; //optio: asiakkaiden j√§rjestys

	public Palvelupiste(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi,
			String n, int koko, double lat, double lon) {
		this.tapahtumalista = tapahtumalista;
		this.generator = generator;
		this.skeduloitavanTapahtumanTyyppi = tyyppi;
		this.baarinnimi = n;
		this.koko = koko;
		this.lat = lat;
		this.lon = lon;

	}
	public Palvelupiste(String nimi,double lat,double lon) {
		super();
		this.baarinnimi = nimi;
		this.lat = lat;
		this.lon = lon;
	}
	public Palvelupiste() {
		super();
	}
	
	public void lisaaJonoon(Asiakas a) { // Jonon 1. asiakas aina palvelussa

		System.out.println("");
		System.out.println(baarinnimi + " jono: " + jono.size());
		System.out.println(baarinnimi + ": " + sisalla.size());
		System.out.println("");

		jono.add(a);
		a.setSaapumisaika(Kello.getInstance().getAika());

	}

	public TapahtumanTyyppi getSkeduloitavanTapahtumanTyyppi() {
		return skeduloitavanTapahtumanTyyppi;
	}

	public Asiakas otaJonosta() {
		return jono.poll();

	}

	public void aloitaPalvelu() { // Aloitetaan uusi palvelu, asiakas on jonossa palvelun aikana

		jono.getFirst().setPoistumisaika(Kello.getInstance().getAika());
		jono.getFirst().setJonoaikalista(baarinnimi);
		Asiakas a = jono.poll();
		sisalla.add(a);

		Trace.out(Trace.Level.INFO, "Aloitetaan uusi palvelu asiakkaalle " + a.getId());

		montaKertaaKayty++;

		a.setSuorituspassi();
		double palveluaika = generator.sample();

		tapahtumalista.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi, Kello.getInstance().getAika() + palveluaika, baarinnimi));
		

	}

	public int getMontaSisalla() {
		return this.sisalla.size();
	}

	public int getMontaJonossa() {
		return this.jono.size();
	}

	public Asiakas otaSisalta() { // Poistetaan palvelussa ollut
		Asiakas a = sisalla.poll();
		a.nostaTyytyvaisyytta();
		return a;
	}

	public int getMontaKertaaKayty() {
		return montaKertaaKayty;
	}

	public boolean onVarattu() {

		if (sisalla.size() == koko) {
			return true;
		} else {
			return false;
		}
	}

	public boolean onJonossa() {
		return !jono.isEmpty();
	}

	public String getBaarinnimi() {
		return baarinnimi;
	}

	public void setBaarinnimi(String baarinnimi) {
		this.baarinnimi = baarinnimi;
	}
	
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	

}
