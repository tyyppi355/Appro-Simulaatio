package simu.model;

import eduni.distributions.Normal;

public class Siirtymat {
	private int suoritukset = 3;
	private OmaMoottori omaMoottori = new OmaMoottori(null);

	public void aloitusPaikka(Palvelupiste p[]) {

		int montaOpiskelijaa = omaMoottori.getMontaOpiskelijaa();
		int montaBaaria = omaMoottori.getPalvelupisteidenMaara();
		// TƒRKEƒ!!!!!!!!!!!!!!
		// normaali generaattorissa MEAN on opiskelijoiden m‰‰r‰ jaettuna BAARIEN
		// m‰‰r‰ll‰.
		// VARIANCE on opiskelijoiden m‰‰r‰ jaettuna kaksinkertainen baarienm‰‰r‰
		//
		Asiakas a = new Asiakas(new Normal(100, 5));

		int kierretty = 0;

		int range = 0;

		do {
			range = (int) Math.ceil(Math.random() * montaBaaria);

			if (a.getTyytyvaisyysIndeksi() >= p[range].getMontaJonossa() || kierretty == montaBaaria) {
				p[range].lisaaJonoon(a);
				break;
			}

			kierretty++;

		} while (true);

	}

	public void siirtyminen(Palvelupiste[] pistelista, String nimi) {
		int kierretty = 0;
		int range;
		int montaBaaria;

		Asiakas a = null;
		// a.setKaydytPaikat(piste.getBaarinnimi());
		for (Palvelupiste p : pistelista) {
			if (p.getBaarinnimi().equals(nimi)) {
				a = p.otaSis‰lt‰();
				break;
			}
		}
		montaBaaria = omaMoottori.getPalvelupisteidenMaara();
		boolean onkoKayty;

		if (a.getSuorituspassi() == suoritukset) { // Siirsin suorituspassi tarkistuksen t‰nne.
			pistelista[0].lisaaJonoon(a); // Oli ennen palvelupisteess‰ itsess‰‰n. -otto

		} else {

			do {
				range = (int) Math.ceil(Math.random() * omaMoottori.getPalvelupisteidenMaara());
				onkoKayty = a.onkoBaarissaKayty(pistelista[range].getBaarinnimi());

				if (!onkoKayty && a.getTyytyvaisyysIndeksi() >= pistelista[range].getMontaJonossa()
						|| kierretty >= montaBaaria && !onkoKayty) {
					pistelista[range].lisaaJonoon(a);
					break;
				}

				kierretty++;

			} while (true);
		}
	}
	
	public void ulos(Palvelupiste p[]) {
		Asiakas a = p[0].otaSis‰lt‰(); // poistaa t‰ll‰ hetkell‰ vain asiakkaat j‰rjestelm‰st‰ lopullisesti
		omaMoottori.setAvgtyytyvaisyys(a.getTyytyvaisyysIndeksi());

		System.err.println(a.getId() + ":n tyytyv‰isyys oli: " + a.getTyytyvaisyysIndeksi());
		System.err.println("Keskim‰‰r‰inen jonotusaika " + a.getKeskimaarainenJonotusaika());
	}

}
