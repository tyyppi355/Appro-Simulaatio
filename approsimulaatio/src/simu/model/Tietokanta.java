package simu.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
/**
 * <p>Appro-simulaattorin DAO</p>
 * <p>t‰ss‰ luokassa pystyt‰‰n lukemaan tietokannan tauluja objekteihin ja</p> 
 * <p>luomaan tauluja javan objekteista</p> 
 */
public class Tietokanta {

	private SessionFactory istuntotehdas;
	/**
	 * Tietokanna konstruktori
	 */
	public Tietokanta() {

		try {
			istuntotehdas = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		} catch (Exception e) {
			System.out.println(e);
			System.err.println("Istuntotehtaan luominen ei onnistunut.");
			System.exit(-1);
		}

	}
	/**
	 * <p>Metodi palvelupiste objektin lis‰‰misest‰ tietokantaan</p>
	 * <p>createPalvelupiste(Palvelupiste p)</p>
	 */
	public void createPalvelupiste(Palvelupiste p) {
		Transaction transaktio = null;
		try (Session istunto = istuntotehdas.openSession()) {
			transaktio = istunto.beginTransaction();
			istunto.saveOrUpdate(p);
			transaktio.commit();
		} catch (Exception e) {
			if (transaktio != null)
				transaktio.rollback();
			throw e;
		}
	}
	/**
	 * <p>Metodi matka objektin lis‰‰misest‰ tietokantaan</p>
	 * <p>createMatka(Matka m)</p>
	 */
	public void createMatka(Matka m) {
		Transaction transaktio = null;
		try (Session istunto = istuntotehdas.openSession()) {
			transaktio = istunto.beginTransaction();
			istunto.saveOrUpdate(m);
			transaktio.commit();
		} catch (Exception e) {
			if (transaktio != null)
				transaktio.rollback();
			throw e;
		}
	}
	/**
	 * <p>Metodi matka taulun lukemisesta javassa k‰ytett‰v‰‰n objektiin</p>
	 * <p>readMatka(String tunnus)</p>
	 * <p>parametrihin annetaan matkan nimi esim. Dondotokannunkulma</p>
	 */
	public Matka readMatka(String tunnus) {
		// TODO Auto-generated method stub
		Session istunto = istuntotehdas.openSession();
		istunto = istuntotehdas.openSession();
		istunto.beginTransaction();

		Matka val = new Matka();
		istunto.load(val, tunnus);

		istunto.getTransaction().commit();
		istunto.close();
		return val;
	}
	/**
	 * <p>Metodi kaikkien matkojen hakeminen tietokannasta listaan</p>
	 * <p>Listaa voidaan k‰ytt‰‰ esimerkiksi matkan pituuden hakuu myˆhemmin</p>
	 * <p>Listan taulut koostuvat matka objekteista</p>
	 */
	public List<Matka> readMatkat() {
		// TODO Auto-generated method stub
		Session istunto = istuntotehdas.openSession();
		istunto.beginTransaction();
		List result = istunto.createQuery("from Matka").list();
		istunto.getTransaction().commit();
		istunto.close();
		return result;
	}
	/**
	 * <p>Metodi kaikkien palvelupisteiden hakeminen tietokannasta listaan</p>
	 * <p>Listaa voidaan k‰yttet‰‰n simulaattorin palvelupistein‰</p>
	 * <p>Listan taulut koostuvat palvelupiste objekteista</p>
	 */
	public List<Palvelupiste> readPalvelupisteet() {
		// TODO Auto-generated method stub
		Session istunto = istuntotehdas.openSession();
		istunto.beginTransaction();
		List result = istunto.createQuery("from Palvelupiste").list();
		istunto.getTransaction().commit();
		istunto.close();
		return result;
	}

}
