package simu.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
/**
 * <p>DAO</p>
 * <p>Projektin data access object</p> 
 */
public class Tietokanta {

	private SessionFactory istuntotehdas;
	/**
	 * Thfghgfhgf
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
	 * Thfghgfhgf
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
	 * Thfghgfhgf
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
	 * Thfghgfhgf
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
	 * tseesus
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
	 * Thfghgfhgf
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
