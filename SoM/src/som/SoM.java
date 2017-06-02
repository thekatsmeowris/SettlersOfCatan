/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package som;

import java.io.File;

import devCards.Knight;
import devCards.VictoryPoint;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import progressCards.Monopoly;
import progressCards.RoadBuilding;
import progressCards.YearOfPlenty;

/**
 *
 * @author makogenq
 */
public class SoM extends Application {

	// private static URL musicURL1, musicURL2;
	// static AudioClip aMusic1, aMusic2;
	String music1Path = "src/res/WASTELAND1.wav";

	Media mMusic1 = new Media(new File(music1Path).toURI().toString());
	static MediaPlayer mediaPlayer;
	MediaView mediaView;
	// Connect to Online
	static ObjectClient client;

	@Override
	public void start(Stage stage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("TitleScreen.fxml"));

		ResourceBank resourceBank = new ResourceBank();
		resourceBank.printResourceList();
		Scene scene = new Scene(root);

		stage.setX(300);
		stage.setY(0);

		stage.setTitle("Title Screen");
		stage.setScene(scene);
		stage.show();

		MediaClass mediaClass = new MediaClass();

	}

	/**
	 * @param args
	 *            the command line arguments
	 */

	public static void main(String[] args) {
		launch(args);

	}

	// Test comment
	private void loadAudioAssets() {

		// comment

		/*
		 * musicURL1 = getClass().getResource("/WASTELAND1.wav"); aMusic1 = new
		 * AudioClip(musicURL1.toString()); musicURL2 =
		 * getClass().getResource("/WASTELAND2.wav"); aMusic2 = new
		 * AudioClip(musicURL2.toString());
		 */
	}

	public static void playMusic1() {
		// aMusic1.setCycleCount(AudioClip.INDEFINITE);
		// aMusic1.play();

		mediaPlayer.setCycleCount(AudioClip.INDEFINITE);
		mediaPlayer.play();
	}

	public static void playMusic2() {
		// aMusic2.setCycleCount(AudioClip.INDEFINITE);
		// aMusic2.play();

		mediaPlayer.setCycleCount(AudioClip.INDEFINITE);
		mediaPlayer.play();
	}

	/*
	 * public AudioClip getaMusic1() { return aMusic1; }
	 * 
	 * public AudioClip getaMusic2() { return aMusic2; }
	 */

	public void TestDeck() {
		Knight k = new Knight();
		VictoryPoint vp = new VictoryPoint();
		YearOfPlenty yop = new YearOfPlenty();
		Monopoly mp = new Monopoly();
		RoadBuilding rb = new RoadBuilding();
		DevelopmentDeck d = new DevelopmentDeck();

		d.addCard(k);
		d.addCard(vp);
		// d.addCard(yop);
		// d.addCard(mp);
		d.addCard(rb);
		while (!d.isEmpty()) {
			DevelopmentCard card = d.drawCard();
			System.out.println(card);
			if (card instanceof RoadBuilding)
				card.playCard();
		}
	}

}