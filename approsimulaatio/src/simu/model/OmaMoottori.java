package simu.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

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
	private int palvelupisteidenMaara;

	private double avgtyytyvaisyys = 0;
	private int suoritukset = 4;
	private HashMap<String, Matka> matkalista = new HashMap<String, Matka>();
	private ArrayList<Integer> matkaaikalista = new ArrayList<Integer>();

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

		Tietokanta tietokanta = new Tietokanta();

		List<Palvelupiste> lista = tietokanta.readPalvelupisteet();

		
		
		palvelupisteidenMaara = lista.size();

		palvelupisteet = new Palvelupiste[palvelupisteidenMaara + 1];

		palvelupisteet[0] = new Palvelupiste(new Normal(1, 1), tapahtumalista, TapahtumanTyyppi.OUT,
				"***JATKOPAIKKA***", 5000, 60.169494575285455, 24.9339736292758); // jatkopaikan "palvelupiste"

		
		
		
		for (int i = 1; i <= lista.size(); i++) {

			palvelupisteet[i] = new Palvelupiste(new Normal(100, 50), tapahtumalista, TapahtumanTyyppi.Palvelupiste,
					lista.get(i - 1).getBaarinnimi(), 50, lista.get(i - 1).getLat(), lista.get(i - 1).getLon());

		} /*
			 * for (int i = 1; i <= lista.size(); i++) {
			 * 
			 * Palvelupiste p = palvelupisteet[i];
			 * 
			 * for (int i2 = 1; i2 <= lista.size(); i2++) {
			 * 
			 * Palvelupiste p2 = palvelupisteet[i2];
			 * 
			 * if(!p.getBaarinnimi().equals(p2.getBaarinnimi())) { String a =
			 * p.getBaarinnimi() + "to" + p2.getBaarinnimi();
			 * 
			 * Matka m = Reittiopas.getDistance(a, p.getLat(), p.getLon(), p2.getLat(),
			 * p2.getLon());
			 * 
			 * tietokanta.createMatka(m);
			 * 
			 * }
			 * 
			 * }
			 * 
			 * }
			 */
		
		List<Matka> list = tietokanta.readMatkat();

		for (int i = 0; i < list.size(); i++) {
			matkalista.put(list.get(i).getNimi(), list.get(i));
		}
		saapumisprosessi = new Saapumisprosessi(new Negexp(2, 5), tapahtumalista, TapahtumanTyyppi.ARR1);
	}
		
	
	
	public ArrayList<Palvelupiste> getPalvelupisteet() {
        ArrayList<Palvelupiste> lista = new ArrayList<Palvelupiste>();

        for (Palvelupiste p : palvelupisteet) {
            lista.add(p);
        }
        return lista;

    }

	@Override
	protected void alustukset() {
		// saapumisprosessi.generoiSeuraava(); // Ensimmäinen saapuminen
		// järjestelmään
		saapumisprosessi.generoiMonta(montaOpiskelijaa);
	}

	@Override
	protected void suoritaTapahtuma(Tapahtuma t) { // B-vaiheen tapahtumat

		switch (t.getTyyppi()) {

		case ARR1:
			aloitusPaikka(palvelupisteet);
			luodut++;
			// saapumisprosessi.generoiSeuraava(); // Ei tarvita jos kaikki opiskelijat
			// generoidaan alustuksessa
			break;
		case Palvelupiste:
			siirtyminen(palvelupisteet, t.getNimi());
			break;
		case OUT:
			ulos(palvelupisteet);
			poistuneet++;
			break;

		}
	}

	public void setAvgtyytyvaisyys(double tyytyvaisyys) {
		avgtyytyvaisyys += tyytyvaisyys;
	}

	@Override
	protected void tulokset() {
		System.out.println("Simulointi paattyi kello " + Kello.getInstance().getAika());
		System.out.println("Luodut opsikelijat: " + luodut);
		System.out.println("Poistuneiden maara: " + poistuneet);
		System.out.println();

		for (Palvelupiste p : palvelupisteet) {
			System.out.println(p.getBaarinnimi() + "ssa on kayty " + p.getMontaKertaaKayty());
			System.out.println("Jono: " + p.getMontaJonossa() + " Sisalla: " + p.getMontaSisalla());

		}
		avgtyytyvaisyys = avgtyytyvaisyys / montaOpiskelijaa;
		System.err.println("Average tyytyvaisyys " + avgtyytyvaisyys);
		System.out.println();
		Collections.sort(matkaaikalista);
		System.out.println("Nopeimman asiakkaan matka aika: " + matkaaikalista.get(0));
		System.out.println("Hitaimman asiakkaan matka aika: " + matkaaikalista.get(matkaaikalista.size() - 1));

		System.out.println("Simulaatio loppui");
		kontrolleri.naytaLoppuaika(Kello.getInstance().getAika());

	}

	@Override
	public void setSimulointiaika(double aika) {
		// TODO Auto-generated method stub

	}

	public void aloitusPaikka(Palvelupiste p[]) {

		int montaBaaria = getPalvelupisteidenMaara();
		// T�RKE�!!!!!!!!!!!!!!
		// normaali generaattorissa MEAN on opiskelijoiden m��r� jaettuna BAARIEN
		// m��r�ll�.
		// VARIANCE on opiskelijoiden m��r� jaettuna kaksinkertainen baarienm��r�
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
				a = p.otaSisältä();
				break;
			}
		}
		montaBaaria = getPalvelupisteidenMaara();
		boolean onkoKayty;

		if (a.getSuorituspassi() == suoritukset) { // Siirsin suorituspassi tarkistuksen t�nne.
			pistelista[0].lisaaJonoon(a); // Oli ennen palvelupisteess� itsess��n. -otto

		} else {

			do {
				range = (int) Math.ceil(Math.random() * getPalvelupisteidenMaara());
				onkoKayty = a.onkoBaarissaKayty(pistelista[range].getBaarinnimi());

				if (!onkoKayty && a.getTyytyvaisyysIndeksi() >= pistelista[range].getMontaJonossa()
						|| kierretty >= montaBaaria && !onkoKayty) {
					a.setMatkaaika(matkalista.get(nimi + "to" + pistelista[range].getBaarinnimi()).getAika());
					pistelista[range].lisaaJonoon(a);
					break;
				}

				kierretty++;

			} while (true);
		}
	}

	public void ulos(Palvelupiste p[]) {
		Asiakas a = p[0].otaSisältä(); // poistaa t�ll� hetkell� vain asiakkaat j�rjestelm�st� lopullisesti
		matkaaikalista.add(a.getMatkaaika());

		setAvgtyytyvaisyys(a.getTyytyvaisyysIndeksi());
		System.err.println(a.getId() + ":n tyytyv�isyys oli: " + a.getTyytyvaisyysIndeksi());
		System.err.println(a.getMatkaaika() + " Matkanaika!!!!!!!!!");
		System.err.println("Keskim��r�inen jonotusaika " + a.getKeskimaarainenJonotusaika());
	}

}
