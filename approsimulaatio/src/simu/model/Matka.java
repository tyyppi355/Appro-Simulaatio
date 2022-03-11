package simu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Matka")
/**
 * <p>Matka-luokka</p>
 * <p>tämä luokka on tehty tietokannasta tulevan ja menevän data säilömiseen javassa</p>
 */
public class Matka {
	@Id
	@Column(name ="nimi")
	private String nimi;
	@Column(name ="walkDistance")
	private double walkDistance;
	@Column(name ="aika")
	private int aika;
	
	public Matka(String nimi,double walkDistance, int aika) {
		super();
		this.nimi = nimi;
		this.walkDistance = walkDistance;
		this.aika = aika;
	}
	public Matka() {
		super();
	}
	public double getWalkDistance() {
		return walkDistance;
	}
	public void setWalkDistance(double walkDistance) {
		this.walkDistance = walkDistance;
	}
	public int getAika() {
		return aika;
	}
	public void setAika(int aika) {
		this.aika = aika;
	}
	public String getNimi() {
		return nimi;
	}
	public void setNimi(String nimi) {
		this.nimi = nimi;
	}
	

}
