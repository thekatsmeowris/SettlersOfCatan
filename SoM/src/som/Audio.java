/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package som;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;

/**
 *
 * @author dano2080
 */
public class Audio {

	Media media;
	MediaPlayer mediaPlayer;
	String music1Path;

	InputStream resource;
	static AudioClip aMusic1, aMusic2, soundClips;
	static MediaPlayer mediaPlayer1, mediaPlayer2;
	MediaView mediaView;
	static URL musicURL1, musicURL2, sounds;

	static ArrayList<String> audioClips = new ArrayList<String>();

	// static Audio audio;
	int numPlayers;
	static int oldHighestVP;
	static int newHighestVP;

	public Audio() {

		// resource =
		// this.getClass().getResourceAsStream("/Team-Project/SoM/src/som/music/WASTELAND1.wav");
		// start
		loadAudioAssets();
		// playMusic1();
	}

	/**
	 *
	 */
	public void changeMusic() {

		newHighestVP = 0;
		numPlayers = GameScreenController.players.size();
		for (int i = 0; i < numPlayers; i++) {
			if (newHighestVP < GameScreenController.players.get(i).getVictoryPoints())
				newHighestVP = GameScreenController.players.get(i).getVictoryPoints();
		}
		if (newHighestVP > 6 && oldHighestVP <= 6)
			playMusic2();
		else if (oldHighestVP > newHighestVP && newHighestVP <= 6)
			playMusic1();
		oldHighestVP = newHighestVP;
	}

	public void playClips(int i) {

		sounds = getClass().getResource(audioClips.get(i));
		soundClips = new AudioClip(sounds.toString());
		soundClips.setVolume(1);
		soundClips.play();
	}

	private void loadAudioAssets() {

		String path = "audio/sounds/";
		audioClips.add(path + "steel.wav");// 0
		audioClips.add(path + "glass.wav");// 1
		audioClips.add(path + "hemp.wav");// 2
		audioClips.add(path + "water.wav");// 3
		audioClips.add(path + "plastic.wav");// 4
		audioClips.add(path + "build.wav");// 5
		audioClips.add(path + "dice.wav");// 6
		audioClips.add(path + "drop.wav");// 7
		audioClips.add(path + "click.wav");// 8

		String music1Path = "src/som/audio/music/WASTELAND_newest.m4a";
		Media mMusic1 = new Media(new File(music1Path).toURI().toString());
		String music2Path = "src/som/audio/music/EdgeOfAwareMelody.m4a";
		Media mMusic2 = new Media(new File(music2Path).toURI().toString());

		mediaPlayer1 = new MediaPlayer(mMusic1); // mediaPlayer
		mediaPlayer2 = new MediaPlayer(mMusic2); // mediaPlayer
		// mediaView = new MediaView(mediaPlayer1);
		// mediaView.setMediaPlayer(mediaPlayer1);

		// musicURL1 = getClass().getResource("audio/music/WASTELAND1.wav");
		// aMusic1 = new AudioClip(musicURL1.toString());
		// musicURL2 = getClass().getResource("audio/music/WASTELAND2.wav");
		// aMusic2 = new AudioClip(musicURL2.toString());
	}

	public static void playMusic1() {
		mediaPlayer1.setVolume(0.1);
		mediaPlayer1.setCycleCount(AudioClip.INDEFINITE);
		mediaPlayer1.play();
		if (mediaPlayer2.getStatus().equals(Status.PLAYING)) {
			mediaPlayer2.stop();
		}
	}

	public static void playMusic2() {

		mediaPlayer2.setVolume(0.5);
		mediaPlayer2.setCycleCount(AudioClip.INDEFINITE);
		mediaPlayer2.play();
		if (mediaPlayer1.getStatus().equals(Status.PLAYING)) {
			mediaPlayer1.stop();
		}

	}
}

// determine the source directory for the playlist (either the first argument to
// the program or a default).
// final List<String> params = request.getParameters().getRaw();
// final File dir = new File(MUSIC_DIR);
//
// // create some media players.
// final List<MediaPlayer> players = new ArrayList<>();
// for (String file : dir.list(new FilenameFilter() {
// @Override
// public boolean accept(File dir, String name) {
// if (name.endsWith(".wav")) {
// return true;
// }
// return false;
// }
// })) {
// players.add(createPlayer("file:///" + (dir + "\\" + file).replace("\\",
// "/").replaceAll(" ", "%20")));
// }
// if (players.isEmpty()) {
// System.out.println("No audio found in " + dir);
// Platform.exit();
// return;
// }
// final MediaView mediaView = new MediaView(players.get(0));
//
// // play each audio file in turn.
// for (int i = 0; i < players.size(); i++) {
// final MediaPlayer player = players.get(i);
// final MediaPlayer nextPlayer = players.get((i + 1) % players.size());
// //if(player.getCurrentTime() == millis(17000.0) ){
// //player.setOnEndOfMedia(new Runnable()
// player.setOnEndOfMedia(new Runnable()
// {
// @Override
// public void run() {
// player.currentTimeProperty().removeListener(progressChangeListener);
// player.stop();
// mediaView.setMediaPlayer(nextPlayer);
// nextPlayer.play();
// }
// });
// }
// // start playing the first track.
// mediaView.setMediaPlayer(players.get(0));
// mediaView.getMediaPlayer().play();
// setCurrentlyPlaying(mediaView.getMediaPlayer());
// }
//
// /**
// * sets the currently playing label to the label of the new media player and
// * updates the progress monitor.
// */
// private synchronized void setCurrentlyPlaying(final MediaPlayer newPlayer) {
// newPlayer.seek(Duration.ZERO);
// progress.setProgress(0);
// progressChangeListener = new ChangeListener<Duration>() {
// @Override
// public void changed(ObservableValue<? extends Duration> observableValue,
// Duration oldValue, Duration newValue) {
// progress.setProgress(1.0 * newPlayer.getCurrentTime().toMillis() /
// newPlayer.getTotalDuration().toMillis());
// }
// };
// newPlayer.currentTimeProperty().addListener(progressChangeListener);
// String source = newPlayer.getMedia().getSource();
// source = source.substring(source.lastIndexOf("/") + 1).replaceAll("%20", "
// ");
// ;
// }
//
// private MediaPlayer createPlayer(String mediaSource) {
// final Media media = new Media(mediaSource);
// final MediaPlayer player = new MediaPlayer(media);
// player.setOnError(new Runnable() {
// @Override
// public void run() {
// System.out.println("Media error occurred: " + player.getError());
// }
// });
// return player;
// }
// }
// }
// }
