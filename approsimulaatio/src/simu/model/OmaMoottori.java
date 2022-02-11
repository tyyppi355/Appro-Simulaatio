package simu.model;

import eduni.distributions.Negexp;
import eduni.distributions.Normal;
import simu.framework.Kello;
import simu.framework.Moottori;
import simu.framework.Saapumisprosessi;
import simu.framework.Tapahtuma;

public class OmaMoottori extends Moottori{
	
	private Saapumisprosessi saapumisprosessi;
	
	private static double kokonaisaika = 0;
	private int poistuneet = 1;
	private int luodut  = 0;
	
	private int montaOpiskelijaa = 5;
	
	
	public int getPoistuneet() {
		return poistuneet;
	}


	public int getMontaOpiskelijaa() {
		return montaOpiskelijaa;
	}


	public OmaMoottori(){
			
		palvelupisteet = new Palvelupiste[3];
	
		palvelupisteet[0]=new Palvelupiste(new Normal(10,10), tapahtumalista, TapahtumanTyyppi.DONDO,"Dondo");	
		palvelupisteet[1]=new Palvelupiste(new Normal(10,10), tapahtumalista, TapahtumanTyyppi.APOLLO,"Apollo");
		palvelupisteet[2]=new Palvelupiste(new Normal(10,10), tapahtumalista, TapahtumanTyyppi.KANNUNKULMA,"Kannunkulma");
		
		saapumisprosessi = new Saapumisprosessi(new Negexp(2,5), tapahtumalista, TapahtumanTyyppi.ARR1);

	}

	
	@Override
	protected void alustukset() {
		saapumisprosessi.generoiSeuraava(); // Ensimmäinen saapuminen järjestelmään
		//saapumisprosessi.generoiMonta(montaOpiskelijaa); 
	}
	
	@Override
	protected void suoritaTapahtuma(Tapahtuma t){  // B-vaiheen tapahtumat

		Asiakas a;
		Siirtymat h = new Siirtymat();
		switch (t.getTyyppi()){
			
			case ARR1: 
				h.aloitusPaikka(palvelupisteet);
				luodut++;
				saapumisprosessi.generoiSeuraava(); // Ei tarvita jos kaikki opiskelijat generoidaan alustuksessa
				break;
			case DONDO:
				h.dondo(palvelupisteet);
				break;				
			case APOLLO:
				h.apollo(palvelupisteet);
				break;	
			case KANNUNKULMA:
				h.kannunkulma(palvelupisteet);
				break;
			case OUT:
				
				poistuneet++;
				System.out.println("Nyt on asiakas poistunut systeemist�! " + poistuneet);
				System.out.println();
				for(Palvelupiste p : palvelupisteet) {
					System.out.println(p.getBaarinnimi() + "ssa on k�yty "+ p.getMontaKertaaKayty());

				}
				break;
				
		}	
	}
	
	@Override
	protected void tulokset() {	
		System.out.println("Simulointi p��ttyi kello " + Kello.getInstance().getAika());
		System.out.println("Luodut opsikelijat: "+ luodut);
		System.out.println("Poistuneiden m��r�: "+ poistuneet);
		System.out.println();

		for(Palvelupiste p : palvelupisteet) {
			System.out.println(p.getBaarinnimi() + "ssa on k�yty "+ p.getMontaKertaaKayty());
			System.out.println("Jono: "+ p.getMontaJonossa() + " Sis�ll�: " + p.getMontaSisalla());

		}
		
	}

	
}
