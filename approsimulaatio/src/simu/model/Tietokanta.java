package simu.model;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class Tietokanta {
	
private SessionFactory istuntotehdas;
	
	public Tietokanta() {
		
		try {
			istuntotehdas = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		} catch (Exception e) {
			System.out.println(e);
			System.err.println("Istuntotehtaan luominen ei 	onnistunut.");
			System.exit(-1);
		}
		
	}


	public void createPalvelupiste(Palvelupiste p) {
		// TODO Auto-generated method stub
		Transaction transaktio = null;
		try (Session istunto = istuntotehdas.openSession()){
			transaktio = istunto.beginTransaction();
			istunto.saveOrUpdate(p);
			transaktio.commit();
		} catch (Exception e) {
			System.out.println(e);
			if (transaktio!=null) transaktio.rollback();
			throw e;
		}
	}
	public void createMatka(Matka m) {
		// TODO Auto-generated method stub
		Transaction transaktio = null;
		try (Session istunto = istuntotehdas.openSession()){
			transaktio = istunto.beginTransaction();
			istunto.saveOrUpdate(m);
			transaktio.commit();
		} catch (Exception e) {
			System.out.println(e);
			if (transaktio!=null) transaktio.rollback();
			throw e;
		}
	}
	public Baari readValuutta(String tunnus) {
		// TODO Auto-generated method stub
		Session istunto = istuntotehdas.openSession();
		istunto = istuntotehdas.openSession();
		istunto.beginTransaction();

		Baari val = new Baari();
		istunto.load(val, tunnus);

		istunto.getTransaction().commit();
		istunto.close();
		return val;
	}

	public List<Palvelupiste> readValuutat() {
		// TODO Auto-generated method stub
		Session istunto = istuntotehdas.openSession();
		istunto.beginTransaction();
		List result = istunto.createQuery( "from Palvelupiste" ).list();
		istunto.getTransaction().commit();
		istunto.close();
		return result;
	}


}
