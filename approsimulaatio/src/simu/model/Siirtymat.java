package simu.model;

import eduni.distributions.Distributions;
import eduni.distributions.Normal;

public class Siirtymat {
	private int suoritukset = 2;
	private OmaMoottori omaMoottori = new OmaMoottori(null);

	public void aloitusPaikka(Palvelupiste p[]) {
		int range = (int) Math.ceil(Math.random() * (omaMoottori.getPalvelupisteidenMaara() - 1));

		int montaOpiskelijaa = omaMoottori.getMontaOpiskelijaa();
		
			// TƒRKEƒ!!!!!!!!!!!!!!
			// normaali generaattorissa MEAN on opiskelijoiden m‰‰r‰ jaettuna BAARIEN
			// m‰‰r‰ll‰.
			// VARIANCE on opiskelijoiden m‰‰r‰ jaettuna kaksinkertainen baarienm‰‰r‰
			//
			p[range].lisaaJonoon(new Asiakas(new Normal(montaOpiskelijaa / 3, montaOpiskelijaa / (3 * 2))));
			
		
	}

	public void siirtyminen(Palvelupiste[] pistelista, String nimi) {
		
		Asiakas a = null;
		//a.setKaydytPaikat(piste.getBaarinnimi());
		for(Palvelupiste p : pistelista) {
			if(p.getBaarinnimi().equals(nimi)) {
				a = p.otaSis‰lt‰();
				break;
			}
		}
			
		if (a.getSuorituspassi() == suoritukset) { // Siirsin suorituspassi tarkistuksen t‰nne.
			pistelista[0].lisaaJonoon(a); // Oli ennen palvelupisteess‰ itsess‰‰n. -otto
			
		} else {
			do {
				
				int range = 0;
				
				do {	
					range = (int) Math.ceil(Math.random() * (omaMoottori.getPalvelupisteidenMaara() - 1));
				} while (a.onkoBaarissaKayty(pistelista[range].getBaarinnimi()));

				
				if (a.getJonoIndeksi() <= pistelista[range].getMontaJonossa()) {
					pistelista[range].lisaaJonoon(a);
					break;
				}

			} while (true);
		}
	}

	public void dondo(Palvelupiste p[]) {
		Asiakas a = p[0].otaSis‰lt‰();
		if (a.getSuorituspassi() == suoritukset) { // Siirsin suorituspassi tarkistuksen t‰nne.
			p[3].lisaaJonoon(a); // Oli ennen palvelupisteess‰ itsess‰‰n. -otto
		} else {
			int range = (int) Math.ceil(Math.random() * 2);
			switch (range) {
			case 1:
				if (a.getJonoIndeksi() <= p[1].getMontaJonossa()) {
					p[1].lisaaJonoon(a);
				} else {
					p[2].lisaaJonoon(a);
				}
				break;
			case 2:
				if (a.getJonoIndeksi() <= p[2].getMontaJonossa()) {
					p[2].lisaaJonoon(a);
				} else {
					p[1].lisaaJonoon(a);
				}
				break;

			}
		}

	}

	public void apollo(Palvelupiste p[]) {
		Asiakas a = p[1].otaSis‰lt‰();
		if (a.getSuorituspassi() == suoritukset) {
			p[3].lisaaJonoon(a);
		} else {
			int range = (int) Math.ceil(Math.random() * 2);
			switch (range) {
			case 1:
				if (a.getJonoIndeksi() <= p[0].getMontaJonossa()) {
					p[0].lisaaJonoon(a);
				} else {
					p[2].lisaaJonoon(a);

				}
				break;
			case 2:
				if (a.getJonoIndeksi() <= p[2].getMontaJonossa()) {
					p[2].lisaaJonoon(a);
				} else {
					p[0].lisaaJonoon(a);

				}
				break;

			}
		}

	}

	public void kannunkulma(Palvelupiste p[]) {
		Asiakas a = p[2].otaSis‰lt‰();
		if (a.getSuorituspassi() == suoritukset) {
			p[3].lisaaJonoon(a);
		} else {
			int range = (int) Math.ceil(Math.random() * 2);

			switch (range) {
			case 1:
				if (a.getJonoIndeksi() <= p[0].getMontaJonossa()) {
					p[0].lisaaJonoon(a);
				} else {
					p[1].lisaaJonoon(a);

				}
				break;
			case 2:
				if (a.getJonoIndeksi() <= p[1].getMontaJonossa()) {
					p[1].lisaaJonoon(a);
				} else {
					p[0].lisaaJonoon(a);

				}
				break;

			}
		}

	}

	public void ulos(Palvelupiste p[]) {
		Asiakas a = p[0].otaSis‰lt‰(); // poistaa t‰ll‰ hetkell‰ vain asiakkaat j‰rjestelm‰st‰ lopullisesti

		System.err.println(a.getId() + ":n tyytyv‰isyys oli: " + a.getTyytyvaisyysIndeksi());
		System.err.println("Keskim‰‰r‰inen jonotusaika " + a.getKeskimaarainenJonotusaika());
	}

	public void poistuminen(Palvelupiste p) {
		p.otaSis‰lt‰();
	}

}
