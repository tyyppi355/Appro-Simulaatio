package simu.model;

import eduni.distributions.Distributions;

public class Siirtymat {
	

	
	public void aloitusPaikka(Palvelupiste p[]) {
		int range = (int) Math.ceil(Math.random()* 3);
		   switch(range) {
		   
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
		Asiakas a = p[0].otaSisältä();
		int range = (int) Math.ceil(Math.random()* 2);
		   switch(range) {
		   case 1:
			   p[1].lisaaJonoon(a);
			   break;
		   case 2:
			   p[2].lisaaJonoon(a);
			   break;
		   
		   }
		
	}
	public void apollo(Palvelupiste p[]) {
		Asiakas a = p[1].otaSisältä();
		int range = (int) Math.ceil(Math.random()* 2);
		   switch(range) {
		   case 1:
			   p[0].lisaaJonoon(a);
			   break;
		   case 2:
			   p[2].lisaaJonoon(a);
			   break;
		   
		   }
		
	}
	
	public void kannunkulma(Palvelupiste p[]) {
		Asiakas a = p[2].otaSisältä();
		int range = (int) Math.ceil(Math.random()* 2);
		   switch(range) {
		   case 1:
			   p[0].lisaaJonoon(a);
			   break;
		   case 2:
			   p[1].lisaaJonoon(a);
			   break;
		   
		   }
		   
		
	}
	public void poistuminen(Palvelupiste p) {
		   p.otaSisältä();
	   }
	
	
	
	

}
