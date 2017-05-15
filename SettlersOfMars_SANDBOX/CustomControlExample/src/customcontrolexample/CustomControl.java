/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customcontrolexample;

import java.io.IOException;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author makogenq
 */
public class CustomControl extends VBox {

    @FXML
    private TextField textField;
    //@FXML
    //private Button button;

    
    public CustomControl(){
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("custom_control.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try{
            fxmlLoader.load();
        }
        catch(IOException exception){
            throw new RuntimeException(exception);
            }
    }
    
    public String getText(){
        return textProperty().get();
    }
    public void setText(String string){
        textProperty().set(string);
        
    }
    public StringProperty textProperty(){
        return textField.textProperty();
    }
    
    @FXML
    protected void doSomething(ActionEvent event) {
        System.out.println("The button was clicked!");
    }
    
    
}
