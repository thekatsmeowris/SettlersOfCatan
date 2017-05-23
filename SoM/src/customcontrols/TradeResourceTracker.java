/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customcontrols;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author makogenq
 */
public class TradeResourceTracker extends VBox {
    String resourceType;
    int givingAmount, requestedAmount;
    int resourcesAvailable;
    
    @FXML
    private Label lblResourceReqValue, lblResourceGiveValue;
    @FXML
    private Label lblResourcesAvailable;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
      //  label.setText("Hello World!");
    }
    @FXML
    private void giveResource(ActionEvent event){
        System.out.println(resourcesAvailable);
        if(resourcesAvailable>0&&requestedAmount<=0){
            resourcesAvailable--;
            givingAmount++;
        }else if(resourcesAvailable>0){
            requestedAmount--;
        }
        lblResourceReqValue.setText(""+requestedAmount);
        lblResourceGiveValue.setText(""+givingAmount);
        lblResourcesAvailable.setText(""+resourcesAvailable);

    }
    @FXML
    private void reqResource(ActionEvent event){
        if(givingAmount>0){
            givingAmount--;
            resourcesAvailable++;
        }else{
            requestedAmount++;
        }        
        lblResourceReqValue.setText(""+requestedAmount);
        lblResourceGiveValue.setText(""+givingAmount);
        lblResourcesAvailable.setText(""+resourcesAvailable);

    }
    @FXML
    private void submitPackage(){
        System.out.println("Create Package 1 -- Giving "+givingAmount);
        System.out.println("Create Package 1 -- Requesting "+ requestedAmount);
        
    }
    public TradeResourceTracker(){
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("custom_control.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try{
            fxmlLoader.load();
        }catch(IOException exception){
            throw new RuntimeException(exception);
            
        }
        givingAmount=0;
        requestedAmount=0;
        resourcesAvailable=6;
        lblResourcesAvailable.setText(""+resourcesAvailable);

    }
        public TradeResourceTracker(int resourcesAvailable){
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("custom_control.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try{
            fxmlLoader.load();
        }catch(IOException exception){
            throw new RuntimeException(exception);
            
        }    
        this.givingAmount=0;
        this.requestedAmount=0;
        this.resourcesAvailable=resourcesAvailable;
        this.lblResourcesAvailable.setText(""+resourcesAvailable);

    }
    public int getRequestedAmount(){
        return requestedAmount;
    }
    public int getGivingAmount(){
        return givingAmount;
    }
    public int getResourcesAvailable(){
        return resourcesAvailable;
    }    
    public void setRequestedAmount(int requestedAmount){
        this.requestedAmount=requestedAmount;
    }
    public void setGivingAmount(int givingAmount){
        this.givingAmount=givingAmount;
    }
    public void setResourcesAvailable(int resourcesAvailable){
        this.resourcesAvailable=resourcesAvailable;
    }
}
