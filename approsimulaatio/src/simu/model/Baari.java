package simu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="Baari")
public class Baari {
	@Transient
	private String yeet;
	@Id
	@Column(name ="nimi")
	private String nimi;
	@Column(name ="lat")
	private double lat;
	@Column(name ="lon")
	private double lon;
	
	public Baari(String nimi, double lat,double lon) {
		super();
		this.nimi = nimi;
		this.lat = lat;
		this.lon = lon;
	}
	public Baari() {
		super();
	}
	public String getNimi() {
		return nimi;
	}
	public void setNimi(String nimi) {
		this.nimi = nimi;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	
	

}