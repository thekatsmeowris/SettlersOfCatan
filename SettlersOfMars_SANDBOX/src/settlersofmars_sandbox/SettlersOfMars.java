/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package settlersofmars_sandbox;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

/**
 *
 * @author makogenq
 */
public class SettlersOfMars extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        Button btn = new Button();
        Pane pane= new Pane();
        
        
       
        btn.setText("Start Game");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
                
            }
        });
        
        
        HexBoard board= new HexBoard();
        StackPane root = new StackPane();
        
        pane=board.getBoardPane();
        
        
       
        root.getChildren().add(pane);
        
        Scene scene = new Scene(root, 800, 800);
        
        primaryStage.setTitle("Title Screen");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
