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
   static AudioClip aMusic1,aMusic2;
    static MediaPlayer mediaPlayer1,mediaPlayer2; 
    MediaView  mediaView;
    static URL musicURL1,musicURL2;
    
    
    
//    
//    URL url = getClass().getResource("").toURI().toURL();
//String applicationDir = url.getPath();

//if(url.getProtocol().equals("jar")) {
//    appDir = new File(((JarURLConnection)url.openConnection()).getJarFileURL().getFile()).().getFile()).getParent();
//}

//String mp3Dir = appDir + "ProjectName" + System.getProperty("file.separator") + "mp3Directory";
    
    //final File dir = new File("music");
    //private static final String MUSIC_DIR = "/Users/dano2080/Desktop/music";
    
    //final ProgressBar progress = new ProgressBar();
    //private ChangeListener<Duration> progressChangeListener;

    

   



    public Audio(){
//    resource = this.getClass().getResourceAsStream("/Team-Project/SoM/src/som/music/WASTELAND1.wav");
//aMusic1 = new AudioClip(musicURL1.toString());  
//     musicURL1 = getClass().getResource("/WASTELAND1.wav");
//        aMusic1 = new AudioClip(musicURL1.toString());
//        musicURL2 = getClass().getResource("/WASTELAND2.wav");
//        aMusic2 = new AudioClip(musicURL2.toString());
    //start
        loadAudioAssets(); 
        
        playMusic1();
        //playSound();
    
    }
    private void loadAudioAssets(){
       String music1Path = "src/som/music/WASTELAND2melody.wav";
    Media mMusic1 = new Media(new File(music1Path).toURI().toString());
     String music2Path = "src/som/music/EDGEofAWARE1wbrass.wav";
    Media mMusic2 = new Media(new File(music2Path).toURI().toString());
        
        mediaPlayer1 = new MediaPlayer(mMusic1); //mediaPlayer
        mediaPlayer2 = new MediaPlayer(mMusic2); //mediaPlayer
        mediaView = new MediaView(mediaPlayer1);
        mediaView.setMediaPlayer(mediaPlayer1);
        
        musicURL1 = getClass().getResource("music/WASTELAND1.wav");
        aMusic1 = new AudioClip(musicURL1.toString());
        musicURL2 = getClass().getResource("music/WASTELAND2.wav");
        aMusic2 = new AudioClip(musicURL2.toString());
    }
    public void playSound(){
         
        try {
        media = new Media(music1Path);
        if (media.getError() == null) {
            media.setOnError(new Runnable() {
                public void run() {
                    // Handle asynchronous error in Media object.
                }
            });
            try {
                mediaPlayer = new MediaPlayer(media);
                if (mediaPlayer.getError() == null) {
                    mediaPlayer.setOnError(new Runnable() {
                        public void run() {
                            // Handle asynchronous error in MediaPlayer object.
                        }
                    });
                    mediaView = new MediaView(mediaPlayer);
                    mediaView.setOnError(new EventHandler() {
                        public void handle(MediaErrorEvent t) {
                            // Handle asynchronous error in MediaView.
                        }

                        @Override
                        public void handle(Event event) {
                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });
                } else {
                    // Handle synchronous error creating MediaPlayer.
                }
            } catch (Exception mediaPlayerException) {
                // Handle exception in MediaPlayer constructor.
            }
        } else {
            // Handle synchronous error creating Media.
        }
    } catch (Exception mediaException) {
        // Handle exception in Media constructor.
    }
        
    }
    public static void playMusic1() {
//        aMusic1.setCycleCount(AudioClip.INDEFINITE);
//        aMusic1.play();
        
       mediaPlayer1.setCycleCount(AudioClip.INDEFINITE);
        mediaPlayer1.play();
        if(mediaPlayer2.getStatus().equals(Status.PLAYING))
            mediaPlayer2.stop();
    }
    
    public static void playMusic2() {
        //aMusic2.setCycleCount(AudioClip.INDEFINITE);
        //aMusic2.play();
        
        mediaPlayer2.setCycleCount(AudioClip.INDEFINITE);
        //mediaPlayer1.play();
        
        mediaPlayer2.play();
        if(mediaPlayer1.getStatus().equals(MediaPlayer.Status.PLAYING))
            mediaPlayer1.stop();
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
