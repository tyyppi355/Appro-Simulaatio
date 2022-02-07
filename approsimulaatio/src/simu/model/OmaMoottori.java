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
	
	
	public OmaMoottori(){
			
		palvelupisteet = new Palvelupiste[3];
	
		palvelupisteet[0]=new Palvelupiste(new Normal(10,10), tapahtumalista, TapahtumanTyyppi.DONDO,"Dondo");	
		palvelupisteet[1]=new Palvelupiste(new Normal(10,10), tapahtumalista, TapahtumanTyyppi.APOLLO,"Apollo");
		palvelupisteet[2]=new Palvelupiste(new Normal(10,10), tapahtumalista, TapahtumanTyyppi.KANNUNKULMA,"Kannunkulma");
		
		saapumisprosessi = new Saapumisprosessi(new Negexp(3,5), tapahtumalista, TapahtumanTyyppi.ARR1);

	}

	
	@Override
	protected void alustukset() {
		saapumisprosessi.generoiSeuraava(); // Ensimmäinen saapuminen järjestelmään
	}
	
	@Override
	protected void suoritaTapahtuma(Tapahtuma t){  // B-vaiheen tapahtumat

		Asiakas a;
		Siirtymat h = new Siirtymat();
		switch (t.getTyyppi()){
			
			case ARR1: 
				h.aloitusPaikka(palvelupisteet);
				saapumisprosessi.generoiSeuraava();
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
				System.out.print("Nyt on asiakas poistunut systeemist�!");
		}	
	}

	
	@Override
	protected void tulokset() {	
		System.out.println("Simulointi päättyi kello " + Kello.getInstance().getAika());
		System.out.println("Tulokset ... puuttuvat vielä");
		System.out.print(kokonaisaika);
	}

	
}
