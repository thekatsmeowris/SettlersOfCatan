/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package som;

import java.io.File;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 *
 * @author dano2080
 */
public class MediaClass {
	// Image image;
	static MediaPlayer mediaPlayer1, mediaPlayer2;
	MediaView mediaView;

	public MediaClass() {
		// start
		loadAudioAssets();

		playMusic1();
	}

	private void loadAudioAssets() {
		String music1Path = "src/som/music/WASTELAND2melody.wav";
		Media mMusic1 = new Media(new File(music1Path).toURI().toString());
		String music2Path = "src/som/music/EDGEofAWARE1wbrass.wav";
		Media mMusic2 = new Media(new File(music2Path).toURI().toString());

		mediaPlayer1 = new MediaPlayer(mMusic1); // mediaPlayer
		mediaPlayer2 = new MediaPlayer(mMusic2); // mediaPlayer
		mediaView = new MediaView(mediaPlayer1);
		mediaView.setMediaPlayer(mediaPlayer1);

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

		mediaPlayer1.setCycleCount(AudioClip.INDEFINITE);
		mediaPlayer1.play();
		// if(mediaPlayer2.getStatus().equals(Status.PLAYING))
		// mediaPlayer2.stop();
	}

	public static void playMusic2() {
		// aMusic2.setCycleCount(AudioClip.INDEFINITE);
		// aMusic2.play();

		mediaPlayer2.setCycleCount(AudioClip.INDEFINITE);
		// mediaPlayer1.play();

		mediaPlayer2.play();
		if (mediaPlayer1.getStatus().equals(MediaPlayer.Status.PLAYING))
			mediaPlayer1.stop();
	}
}