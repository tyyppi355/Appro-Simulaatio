package simu.model;

public class Jonoajat {
	
	private String baari;
	private double jonotusaika;
	
	public Jonoajat(String nimi,double aika) {
		
		baari = nimi;
		jonotusaika = aika;
		
	}

	public String getBaari() {
		return baari;
	}

	public void setBaari(String baari) {
		this.baari = baari;
	}

	public double getJonotusaika() {
		return jonotusaika;
	}

	public void setJonotusaika(double jonotusaika) {
		this.jonotusaika = jonotusaika;
	}
	

}
