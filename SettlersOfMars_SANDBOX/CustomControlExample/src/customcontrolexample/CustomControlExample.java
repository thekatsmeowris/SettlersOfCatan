/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customcontrolexample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author makogenq
 */
public class CustomControlExample extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        CustomControl customControl= new CustomControl();
        //customControl.setText("Hello!");
        Scene scene = new Scene(customControl);
        stage.setTitle("Custom Control");
        stage.setWidth(300);
        stage.setHeight(200);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
