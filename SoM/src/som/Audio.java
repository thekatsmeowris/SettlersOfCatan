/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package som;

import java.io.InputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import static javafx.scene.input.DataFormat.URL;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaErrorEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

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

    static ArrayList<String> audioClips = new ArrayList();

    public Audio() {

//    resource = this.getClass().getResourceAsStream("/Team-Project/SoM/src/som/music/WASTELAND1.wav");
        //start
        loadAudioAssets();
        playMusic1();
    }

    public void playClips(int i) {
        //sounds = getClass().getResource("audio/sounds/building.wav");
        sounds = getClass().getResource(audioClips.get(i));
        soundClips = new AudioClip(sounds.toString());
        soundClips.play();
    }

    private void loadAudioAssets() {
        String path = "audio/sounds/";
        audioClips.add(path + "building.wav");
        audioClips.add(path + "dice.wav");
        audioClips.add(path + "drop.wav");
        audioClips.add(path + "glass.wav");
        audioClips.add(path + "hemp.wav");
        audioClips.add(path + "Plastic.wav");
        audioClips.add(path + "steel.wav");
        audioClips.add(path + "water.wav");

        String music1Path = "src/som/audio/music/WASTELAND2melody.wav";
        Media mMusic1 = new Media(new File(music1Path).toURI().toString());
        String music2Path = "src/som/audio/music/EDGEofAWARE1wbrass.wav";
        Media mMusic2 = new Media(new File(music2Path).toURI().toString());

        mediaPlayer1 = new MediaPlayer(mMusic1); //mediaPlayer
        mediaPlayer2 = new MediaPlayer(mMusic2); //mediaPlayer
        mediaView = new MediaView(mediaPlayer1);
        mediaView.setMediaPlayer(mediaPlayer1);

        musicURL1 = getClass().getResource("audio/music/WASTELAND1.wav");
        aMusic1 = new AudioClip(musicURL1.toString());
        musicURL2 = getClass().getResource("audio/music/WASTELAND2.wav");
        aMusic2 = new AudioClip(musicURL2.toString());
    }

    public static void playMusic1() {

        mediaPlayer1.setCycleCount(AudioClip.INDEFINITE);
        mediaPlayer1.play();
        if (mediaPlayer2.getStatus().equals(Status.PLAYING)) {
            mediaPlayer2.stop();
        }
    }

    public static void playMusic2() {

        mediaPlayer2.setCycleCount(AudioClip.INDEFINITE);
        mediaPlayer2.play();
        if (mediaPlayer1.getStatus().equals(MediaPlayer.Status.PLAYING)) {
            mediaPlayer1.stop();
        }
    }
}

// determine the source directory for the playlist (either the first argument to the program or a default).
//final List<String> params = request.getParameters().getRaw();
//        final File dir =  new File(MUSIC_DIR);
//
//        // create some media players.
//        final List<MediaPlayer> players = new ArrayList<>();
//        for (String file : dir.list(new FilenameFilter() {
//            @Override
//            public boolean accept(File dir, String name) {
//                if (name.endsWith(".wav")) {
//                    return true;
//                }
//                return false;
//            }
//        })) {
//            players.add(createPlayer("file:///" + (dir + "\\" + file).replace("\\", "/").replaceAll(" ", "%20")));
//        }
//        if (players.isEmpty()) {
//            System.out.println("No audio found in " + dir);
//            Platform.exit();
//            return;
//        }
//        final MediaView mediaView = new MediaView(players.get(0));
//
//        // play each audio file in turn.
//        for (int i = 0; i < players.size(); i++) {
//            final MediaPlayer player = players.get(i);
//            final MediaPlayer nextPlayer = players.get((i + 1) % players.size());
//            //if(player.getCurrentTime() == millis(17000.0) ){
//            //player.setOnEndOfMedia(new Runnable() 
//             player.setOnEndOfMedia(new Runnable() 
//            {
//                @Override
//                public void run() {
//                    player.currentTimeProperty().removeListener(progressChangeListener);
//                    player.stop();
//                    mediaView.setMediaPlayer(nextPlayer);
//                    nextPlayer.play();
//                }
//            });
//        }
//        // start playing the first track.
//        mediaView.setMediaPlayer(players.get(0));
//        mediaView.getMediaPlayer().play();
//        setCurrentlyPlaying(mediaView.getMediaPlayer());
//    }
//
//    /**
//     * sets the currently playing label to the label of the new media player and
//     * updates the progress monitor.
//     */
//    private synchronized void setCurrentlyPlaying(final MediaPlayer newPlayer) {
//        newPlayer.seek(Duration.ZERO);
//        progress.setProgress(0);
//        progressChangeListener = new ChangeListener<Duration>() {
//            @Override
//            public void changed(ObservableValue<? extends Duration> observableValue, Duration oldValue, Duration newValue) {
//                progress.setProgress(1.0 * newPlayer.getCurrentTime().toMillis() / newPlayer.getTotalDuration().toMillis());
//            }
//        };
//        newPlayer.currentTimeProperty().addListener(progressChangeListener);
//        String source = newPlayer.getMedia().getSource();
//        source = source.substring(source.lastIndexOf("/") + 1).replaceAll("%20", " ");
//        ;
//    }
//
//    private MediaPlayer createPlayer(String mediaSource) {
//        final Media media = new Media(mediaSource);
//        final MediaPlayer player = new MediaPlayer(media);
//        player.setOnError(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Media error occurred: " + player.getError());
//            }
//        });
//        return player;
//    }
//}
//    }
//}
