package view;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AnimaatioOhjain extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private int laskuri = 0;

	@FXML
	ImageView kuvahaku;
	Button Aloita;
	Button Plus;
	Button Minus;

	@FXML
	private Label numero;
	
	//kuvat itsessään
	Image myImageALK = new Image(getClass().getResourceAsStream("katu.png"));
	Image myImage = new Image(getClass().getResourceAsStream("katu2.jpg"));
	Image myImage3 = new Image(getClass().getResourceAsStream("katu3.jpg"));
	Image myImage4 = new Image(getClass().getResourceAsStream("katu4.jpg"));
	Image myImage5 = new Image(getClass().getResourceAsStream("katu5.jpg"));
	
	//Vaihdetaan kuvat, lisäämäärä on kiinni plus-napissa
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
	//Vaihdetaan kuvat, poista on kiinni minus-napissa
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

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("ApproAppi");
		initRootLayout();

	}

	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(AnimaatioOhjain.class.getResource("Animaatio1.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
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
}
