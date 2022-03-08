package simu.model;

import controller.IKontrolleriMtoV;
import eduni.distributions.Negexp;
import eduni.distributions.Normal;
import simu.framework.Kello;
import simu.framework.Moottori;
import simu.framework.Saapumisprosessi;
import simu.framework.Tapahtuma;

public class OmaMoottori extends Moottori {

	private Saapumisprosessi saapumisprosessi;

	private int poistuneet = 1;
	private int luodut = 0;

	private int montaOpiskelijaa = 500;
	private int palvelupisteidenMaara = 3;
	
	private double avgtyytyvaisyys = 0;
	private int tyytyvaisyysCount = 0;

	public int getPoistuneet() {
		return poistuneet;
	}

	public int getMontaOpiskelijaa() {
		return montaOpiskelijaa;
	}

	public int getPalvelupisteidenMaara() {
		return palvelupisteidenMaara;
	}

	public OmaMoottori(IKontrolleriMtoV kontrolleri) {

		super(kontrolleri);

		palvelupisteet = new Palvelupiste[palvelupisteidenMaara + 1];/*
for(int i = 1; i<palvelupisteidenMaara;i++) {
	palvelupisteet[i] = new Palvelupiste(new Normal(100, 50), tapahtumalista, TapahtumanTyyppi.Palvelupiste,"Dondo"+i);
}*/
		
		palvelupisteet[1] = new Palvelupiste(new Normal(100, 50), tapahtumalista, TapahtumanTyyppi.Palvelupiste,
				"Apollo",50);
		palvelupisteet[2] = new Palvelupiste(new Normal(100, 50), tapahtumalista, TapahtumanTyyppi.Palvelupiste,
				"Kannunkulma",50);
		palvelupisteet[3] = new Palvelupiste(new Normal(100, 50), tapahtumalista, TapahtumanTyyppi.Palvelupiste,
				"Karhunkansi",50);
		/*	
		palvelupisteet[4] = new Palvelupiste(new Normal(100, 50), tapahtumalista, TapahtumanTyyppi.Palvelupiste,
				"Cortisooni baari",50);
		palvelupisteet[5] = new Palvelupiste(new Normal(100, 50), tapahtumalista, TapahtumanTyyppi.Palvelupiste,
<<<<<<< Updated upstream
				"Cortisooni baari");
		palvelupisteet[6] = new Palvelupiste(new Normal(100, 50), tapahtumalista, TapahtumanTyyppi.Palvelupiste,
				"Kaivo huone");
=======
				"Kaivo huone",100);*/
		

>>>>>>> Stashed changes
		palvelupisteet[0] = new Palvelupiste(new Normal(1, 1), tapahtumalista, TapahtumanTyyppi.OUT,
				"***JATKOPAIKKA***",montaOpiskelijaa); // jatkopaikan "palvelupiste"

		saapumisprosessi = new Saapumisprosessi(new Negexp(2, 5), tapahtumalista, TapahtumanTyyppi.ARR1);

	}

	public Palvelupiste[] getPalvelupisteet() {

		return palvelupisteet;

	}

	@Override
	protected void alustukset() {
		// saapumisprosessi.generoiSeuraava(); // Ensimm√§inen saapuminen
		// j√§rjestelm√§√§n
		saapumisprosessi.generoiMonta(montaOpiskelijaa);
	}

	@Override
	protected void suoritaTapahtuma(Tapahtuma t) { // B-vaiheen tapahtumat

		Siirtymat h = new Siirtymat();
		switch (t.getTyyppi()) {

		case ARR1:
			h.aloitusPaikka(palvelupisteet);
			luodut++;
			// saapumisprosessi.generoiSeuraava(); // Ei tarvita jos kaikki opiskelijat
			// generoidaan alustuksessa
			break;
		case Palvelupiste:
			h.siirtyminen(palvelupisteet, t.getNimi());
			break;
		case OUT:
			h.ulos(palvelupisteet);
			poistuneet++;
			break;

		}
	}
	
	public void setAvgtyytyvaisyys(double tyytyvaisyys) {
		avgtyytyvaisyys += tyytyvaisyys;
	}
	
	@Override
	protected void tulokset() {
		System.out.println("Simulointi p‰‰ttyi kello " + Kello.getInstance().getAika());
		System.out.println("Luodut opsikelijat: " + luodut);
		System.out.println("Poistuneiden m‰‰r‰: " + poistuneet);
		System.out.println();

		for (Palvelupiste p : palvelupisteet) {
			System.out.println(p.getBaarinnimi() + "ssa on k‰yty " + p.getMontaKertaaKayty());
			System.out.println("Jono: " + p.getMontaJonossa() + " Sis‰ll‰: " + p.getMontaSisalla());

		}
		avgtyytyvaisyys= avgtyytyvaisyys / montaOpiskelijaa;
		System.err.println("Average tyytyvaisyys "+ avgtyytyvaisyys);

		System.out.println("Simulaatio loppui");
		kontrolleri.naytaLoppuaika(Kello.getInstance().getAika());

	}

	@Override
	public void setSimulointiaika(double aika) {
		// TODO Auto-generated method stub

	}

}
