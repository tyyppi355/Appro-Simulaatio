package simu.model;

import eduni.distributions.Distributions;

public class Siirtymat {

	public void aloitusPaikka(Palvelupiste p[]) {
		int range = (int) Math.ceil(Math.random() * 3);
		switch (range) {

		case 1:
			p[0].lisaaJonoon(new Asiakas());
			break;
		case 2:
			p[1].lisaaJonoon(new Asiakas());
			break;
		case 3:
			p[2].lisaaJonoon(new Asiakas());
			break;

		}
	}

	public void dondo(Palvelupiste p[]) {
		Asiakas a = p[0].otaSisalla();
		if (a.getSuorituspassi() == 4) { 	// Siirsin suorituspassi tarkistuksen t�nne.  
			p[3].lisaaJonoon(a);			//	Oli ennen palvelupisteess� itsess��n. -otto
		} else {							
			int range = (int) Math.ceil(Math.random() * 2);
			switch (range) {
			case 1:
				p[1].lisaaJonoon(a);
				break;
			case 2:
				p[2].lisaaJonoon(a);
				break;

			}
		}

	}

	public void apollo(Palvelupiste p[]) {
		Asiakas a = p[1].otaSisalla();
		if (a.getSuorituspassi() == 4) {
			p[3].lisaaJonoon(a);
		} else {
			int range = (int) Math.ceil(Math.random() * 2);
			switch (range) {
			case 1:
				p[0].lisaaJonoon(a);
				break;
			case 2:
				p[2].lisaaJonoon(a);
				break;

			}
		}

	}

	public void kannunkulma(Palvelupiste p[]) {
		Asiakas a = p[2].otaSisalla();
		if (a.getSuorituspassi() == 4) {
			p[3].lisaaJonoon(a);
		} else {
			int range = (int) Math.ceil(Math.random() * 2);
			switch (range) {
			case 1:
				p[0].lisaaJonoon(a);
				break;
			case 2:
				p[1].lisaaJonoon(a);
				break;

			}
		}

	}

	public void ulos(Palvelupiste p[]) {
		Asiakas a = p[3].otaSisalla(); // poistaa t�ll� hetkell� vain asiakkaat j�rjestelm�st� lopullisesti

	}

	public void poistuminen(Palvelupiste p) {
		p.otaSisalla();
	}

}
