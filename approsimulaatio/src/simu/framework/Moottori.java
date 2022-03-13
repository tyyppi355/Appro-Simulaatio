package simu.framework;

import controller.IKontrolleriMtoV;
import javafx.application.Platform;
import simu.model.Palvelupiste;

public abstract class Moottori extends Thread implements IMoottori{
	
	private long viive = 0;
	
	private Kello kello;
	
	protected Tapahtumalista tapahtumalista;
	protected Palvelupiste[] palvelupisteet;
	
	protected IKontrolleriMtoV kontrolleri;
	
	public Moottori(IKontrolleriMtoV kontrolleri){

		
		this.kontrolleri = kontrolleri;
		
		kello = Kello.getInstance(); // Otetaan kello muuttujaan yksinkertaistamaan koodia
		
		tapahtumalista = new Tapahtumalista();
		
		viive = 100;
		
		// Palvelupisteet luodaan simu.model-pakkauksessa Moottorin aliluokassa 
		
		
	}
	
	public void setViive(long viive) {
		this.viive = viive;
	}
	
	public long getViive() {
		return viive;
	}
	
	public void run(){ // Entinen aja()
		alustukset(); // luodaan mm. ensimm�inen tapahtuma
		viive = 100;
		while (simuloidaan()){
			viive(); // UUSI
			kello.setAika(nykyaika());
			suoritaBTapahtumat();
			yritaCTapahtumat();
				if(!getPalvelupisteet().isEmpty()) {
					kontrolleri.updateUI();
			}
		}
		Platform.runLater(new Runnable() {                          
            @Override
            public void run() {
                try{
                    kontrolleri.kEnd();
                }finally{
                    System.out.println("yeet");
                }
            }
        });
		
	}
	
	private void suoritaBTapahtumat(){
		while (tapahtumalista.getSeuraavanAika() == kello.getAika()){
			suoritaTapahtuma(tapahtumalista.poista());
		}
	}

	private void yritaCTapahtumat(){
		for (Palvelupiste p: palvelupisteet){
			if (!p.onVarattu() && p.onJonossa()){
				p.aloitaPalvelu();
			}
		}
	}

	
	private double nykyaika(){
		return tapahtumalista.getSeuraavanAika();
	}
	
	private boolean simuloidaan(){
		//return Kello.getInstance().getAika() < simulointiaika;
		 return getPoistuneet() < getMontaOpiskelijaa();
	}
	
	private void viive() { // UUSI
		Trace.out(Trace.Level.INFO, "Viive " + viive);
		try {
			sleep(viive);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
			

	protected abstract int getMontaOpiskelijaa();

	protected abstract int getPoistuneet();

	protected abstract void alustukset(); // Määritellään simu.model-pakkauksessa Moottorin aliluokassa
	
	protected abstract void suoritaTapahtuma(Tapahtuma t);  // Määritellään simu.model-pakkauksessa Moottorin aliluokassa
	
	protected abstract void tulokset(); // Määritellään simu.model-pakkauksessa Moottorin aliluokassa
	
}