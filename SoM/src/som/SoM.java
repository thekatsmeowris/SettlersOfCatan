/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package som;

import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;

import javafx.stage.Stage;

/**
 *
 * @author makogenq
 */
public class SoM extends Application {
    
    //private static URL musicURL1, musicURL2;
    //static AudioClip aMusic1, aMusic2;    
    static MediaPlayer mediaPlayer1,mediaPlayer2; 
    MediaView  mediaView;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        MediaClass mediaClass = new MediaClass();
       
        Parent root = FXMLLoader.load(getClass().getResource("TitleScreen.fxml"));
        ResourceBank resourceBank= new ResourceBank();
        resourceBank.printResourceList();
        Scene scene = new Scene(root);
        
        stage.setX(300);
        stage.setY(0);
        
        stage.setTitle("Title Screen");
        stage.setScene(scene);
        stage.show();

        
    }

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        launch(args);
        
      
        
    }
    
    
    

    
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
		while (d.hasCard()){
			DevelopmentCard card = d.drawCard();
			System.out.println(card);	
			if (card instanceof RoadBuilding)
				card.playCard();
                }
        }
    
}
