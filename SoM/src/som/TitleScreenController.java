/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package som;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author makogenq
 */
public class TitleScreenController implements Initializable {

    @FXML
    private Label label;

    //private Button btnStartGame;
    //btnStartGame.setOnAction(new EventHandler<ActionEvent>())    
    public void handleButtonAction(ActionEvent event) throws IOException {
        GameScreenController.audio.playClips(8);
        //System.out.println("You clicked me!");
        label.setText("Hello World!");
        Parent game_room_parent = FXMLLoader.load(getClass().getResource("GameRoomSelect.fxml"));
        Scene game_room_scene = new Scene(game_room_parent);
        Stage a_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        a_stage.setScene(game_room_scene);
        a_stage.show();
    }

    public void handlePlayOnline(ActionEvent e) throws IOException {
        Parent serverPort = FXMLLoader.load(getClass().getResource("ConnectServer.fxml"));
        Scene serverPortScene = new Scene(serverPort);
        Stage a_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        a_stage.setScene(serverPortScene);
        a_stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
