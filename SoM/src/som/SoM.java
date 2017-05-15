/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package som;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

/**
 *
 * @author makogenq
 */
public class SoM extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
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
