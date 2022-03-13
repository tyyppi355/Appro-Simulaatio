package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import controller.IKontrolleriVtoM;
import controller.Kontrolleri;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import simu.framework.Trace;
import simu.framework.Trace.Level;

public class AnimaatioOhjain extends Application implements ISimulaattorinUI {

	private IKontrolleriVtoM kontrolleri;
	private Stage primaryStage;
	private AnchorPane rootLayout;
	private int laskuri = 0;

	@FXML
	ImageView kuvahaku;
	@FXML
	Button Aloita;
	@FXML
	Button Plus;
	@FXML
	Button Minus;

	@FXML
	ImageView g1h1, g1h2, g1h3, g1h4, g1h5;

	@FXML
	ImageView g2h1, g2h2, g2h3, g2h4, g2h5;

	@FXML
	ImageView g3h1, g3h2, g3h3, g3h4, g3h5;

	@FXML
	ImageView g4h1, g4h2, g4h3, g4h4, g4h5;

	@FXML
	ImageView g5h1, g5h2, g5h3, g5h4, g5h5;

	@FXML
	private Label numero;
	
	@FXML
	private Label avg;
	
	HashMap<String,ImageView> kuvat;

	// kuvat itsessään
	Image myImageALK = new Image(getClass().getResourceAsStream("katu.png"));
	Image myImage = new Image(getClass().getResourceAsStream("katu2.jpg"));
	Image myImage3 = new Image(getClass().getResourceAsStream("katu3.jpg"));
	Image myImage4 = new Image(getClass().getResourceAsStream("katu4.jpg"));
	Image myImage5 = new Image(getClass().getResourceAsStream("katu5.jpg"));

	// Vaihdetaan kuvat, lisäämäärä on kiinni plus-napissa
	public void lisaamaara(ActionEvent e) {
		if (laskuri >= 0) {
			laskuri++;
		}
		if (laskuri == 0) {
			kuvahaku.setImage(myImageALK);
		}

		if (laskuri == 1) {
			kuvahaku.setImage(myImage);

		}
		if (laskuri == 2) {
			kuvahaku.setImage(myImage3);

		}
		if (laskuri == 3) {
			kuvahaku.setImage(myImage4);

		}
		if (laskuri == 4) {
			kuvahaku.setImage(myImage5);
		}

		int num = Integer.parseInt(numero.getText());
		numero.setText(Integer.toString(num + 1));

	}

	// Vaihdetaan kuvat, poista on kiinni minus-napissa
	public void poista() {
		if (laskuri >= 1) {
			laskuri = laskuri - 1;
		}

		if (laskuri == 0) {
			kuvahaku.setImage(myImageALK);
		}
		if (laskuri == 1) {
			kuvahaku.setImage(myImage);
		}
		if (laskuri == 2) {
			kuvahaku.setImage(myImage3);

		}
		if (laskuri == 3) {
			kuvahaku.setImage(myImage4);
		}
		if (laskuri == 4) {
			kuvahaku.setImage(myImage5);
		}

		if (laskuri >= 1) {
			int num = Integer.parseInt(numero.getText());
			numero.setText(Integer.toString(num - 1));
		}
		if (laskuri == 0) {
			int num = 2;
			numero.setText(Integer.toString(num - 1));
		}

	}

	public void nakyvyys(ArrayList<Integer> L) {

		for (int i = 0; i <= L.size() - 1; i++) {
			
			if (L.get(i) == 0) {
				switch(i) {
				case 1:
					g1h1.setOpacity(0);
					g1h2.setOpacity(0);
					g1h3.setOpacity(0);
					g1h4.setOpacity(0);
					g1h5.setOpacity(0);
					break;
				case 2:
					g2h1.setOpacity(0);
					g2h2.setOpacity(0);
					g2h3.setOpacity(0);
					g2h4.setOpacity(0);
					g2h5.setOpacity(0);
					break;
				case 3:
					g3h1.setOpacity(0);
					g3h2.setOpacity(0);
					g3h3.setOpacity(0);
					g3h4.setOpacity(0);
					g3h5.setOpacity(0);
					break;
				case 4:
					g4h1.setOpacity(0);
					g4h2.setOpacity(0);
					g4h3.setOpacity(0);
					g4h4.setOpacity(0);
					g4h5.setOpacity(0);
					break;
				case 5:
					g5h1.setOpacity(0);
					g5h2.setOpacity(0);
					g5h3.setOpacity(0);
					g5h4.setOpacity(0);
					g5h5.setOpacity(0);
					break;

				}
			}
			
			if (L.get(i) < 20) {
				switch(i) {
				case 1:
					g1h1.setOpacity(0);
					g1h2.setOpacity(0);
					g1h3.setOpacity(0);
					g1h4.setOpacity(0);
					g1h5.setOpacity(1);
					break;
				case 2:
					g2h1.setOpacity(0);
					g2h2.setOpacity(0);
					g2h3.setOpacity(0);
					g2h4.setOpacity(0);
					g2h5.setOpacity(1);
					break;
				case 3:
					g3h1.setOpacity(0);
					g3h2.setOpacity(0);
					g3h3.setOpacity(0);
					g3h4.setOpacity(0);
					g3h5.setOpacity(1);
					break;
				case 4:
					g4h1.setOpacity(0);
					g4h2.setOpacity(0);
					g4h3.setOpacity(0);
					g4h4.setOpacity(0);
					g4h5.setOpacity(1);
					break;
				case 5:
					g5h1.setOpacity(0);
					g5h2.setOpacity(0);
					g5h3.setOpacity(0);
					g5h4.setOpacity(0);
					g5h5.setOpacity(1);
					break;

				}
			}

			if (L.get(i) < 40 && L.get(i) > 20) {
				switch(i) {
				case 1:
					g1h1.setOpacity(0);
					g1h2.setOpacity(0);
					g1h3.setOpacity(0);
					g1h4.setOpacity(1);
					g1h5.setOpacity(1);
					break;
				case 2:
					g2h1.setOpacity(0);
					g2h2.setOpacity(0);
					g2h3.setOpacity(0);
					g2h4.setOpacity(1);
					g2h5.setOpacity(1);
					break;
				case 3:
					g3h1.setOpacity(0);
					g3h2.setOpacity(0);
					g3h3.setOpacity(0);
					g3h4.setOpacity(1);
					g3h5.setOpacity(1);
					break;
				case 4:
					g4h1.setOpacity(0);
					g4h2.setOpacity(0);
					g4h3.setOpacity(0);
					g4h4.setOpacity(1);
					g4h5.setOpacity(1);
					break;
				case 5:
					g5h1.setOpacity(0);
					g5h2.setOpacity(0);
					g5h3.setOpacity(0);
					g5h4.setOpacity(1);
					g5h5.setOpacity(1);
					break;

				}
			}

			if (L.get(i) < 60 && L.get(i) > 40) {
				switch(i) {
				case 1:
					g1h1.setOpacity(0);
					g1h2.setOpacity(0);
					g1h3.setOpacity(1);
					g1h4.setOpacity(1);
					g1h5.setOpacity(1);
					break;
				case 2:
					g2h1.setOpacity(0);
					g2h2.setOpacity(0);
					g2h3.setOpacity(1);
					g2h4.setOpacity(1);
					g2h5.setOpacity(1);
					break;
				case 3:
					g3h1.setOpacity(0);
					g3h2.setOpacity(0);
					g3h3.setOpacity(1);
					g3h4.setOpacity(1);
					g3h5.setOpacity(1);
					break;
				case 4:
					g4h1.setOpacity(0);
					g4h2.setOpacity(0);
					g4h3.setOpacity(1);
					g4h4.setOpacity(1);
					g4h5.setOpacity(1);
					break;
				case 5:
					g5h1.setOpacity(0);
					g5h2.setOpacity(0);
					g5h3.setOpacity(1);
					g5h4.setOpacity(1);
					g5h5.setOpacity(1);
					break;

				}
			}

			if (L.get(i) < 80 && L.get(i) > 60) {
				switch(i) {
				case 1:
					g1h1.setOpacity(0);
					g1h2.setOpacity(1);
					g1h3.setOpacity(1);
					g1h4.setOpacity(1);
					g1h5.setOpacity(1);
					break;
				case 2:
					g2h1.setOpacity(0);
					g2h2.setOpacity(1);
					g2h3.setOpacity(1);
					g2h4.setOpacity(1);
					g2h5.setOpacity(1);
					break;
				case 3:
					g3h1.setOpacity(0);
					g3h2.setOpacity(1);
					g3h3.setOpacity(1);
					g3h4.setOpacity(1);
					g3h5.setOpacity(1);
					break;
				case 4:
					g4h1.setOpacity(0);
					g4h2.setOpacity(1);
					g4h3.setOpacity(1);
					g4h4.setOpacity(1);
					g4h5.setOpacity(1);
					break;
				case 5:
					g5h1.setOpacity(0);
					g5h2.setOpacity(1);
					g5h3.setOpacity(1);
					g5h4.setOpacity(1);
					g5h5.setOpacity(1);
					break;

				}
			}
			
			if (L.get(i) >= 80 ) {
				switch(i) {
				case 1:
					g1h1.setOpacity(1);
					g1h2.setOpacity(1);
					g1h3.setOpacity(1);
					g1h4.setOpacity(1);
					g1h5.setOpacity(1);
					break;
				case 2:
					g2h1.setOpacity(1);
					g2h2.setOpacity(1);
					g2h3.setOpacity(1);
					g2h4.setOpacity(1);
					g2h5.setOpacity(1);
					break;
				case 3:
					g3h1.setOpacity(1);
					g3h2.setOpacity(1);
					g3h3.setOpacity(1);
					g3h4.setOpacity(1);
					g3h5.setOpacity(1);
					break;
				case 4:
					g4h1.setOpacity(1);
					g4h2.setOpacity(1);
					g4h3.setOpacity(1);
					g4h4.setOpacity(1);
					g4h5.setOpacity(1);
					break;
				case 5:
					g5h1.setOpacity(1);
					g5h2.setOpacity(1);
					g5h3.setOpacity(1);
					g5h4.setOpacity(1);
					g5h5.setOpacity(1);
					break;

				}
			}

		}
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("ApproAppi");
		initRootLayout();

	}

	public void initRootLayout() {
		try {
			// Lataa Root-tiedosto
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(AnimaatioOhjain.class.getResource("Animaatio.fxml"));
			rootLayout = (AnchorPane) loader.load();

			// Näytä scene joka sisältää root-tiedoston
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public double getAika() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getViive() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLoppuaika(double aika) {
		// TODO Auto-generated method stub

	}
	
	public void handler() {
        Trace.setTraceLevel(Level.INFO);
        kontrolleri = new Kontrolleri(this);
        kontrolleri.kaynnistaSimulointi();
    }
	
	public void setEnd(String s) {
		avg.setText(s);
	}
	public void nopeuta() {
		kontrolleri.hidasta();
	}
	public void hidasta() {
		kontrolleri.nopeuta();
	}
}
