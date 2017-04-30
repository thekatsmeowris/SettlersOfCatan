/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package som;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Polygon;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Pane;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;




/**
 * FXML Controller class
 *
 * @author makogenq
 */
public class GameScreenController implements Initializable {
    @FXML
    Pane gameLayer;
    
    @FXML
    Pane gameBoard;
    
    @FXML
    Pane playerGUI; 
    
    @FXML
    Pane popupDialog;
    
    @FXML 
    Text popUpText;
    
    @FXML
    AnchorPane lastAnchorPane;

    @FXML
    Text leftDie;
    
    @FXML
    Text rightDie;            
    
    @FXML
    Pane dicePane;

    @FXML
    Button btnRollDice;
    
    Node selectedItem;
    int diceValue;
    //Circle selectedCircle=new HexVertex();
    
    /**
     * Initializes the controller class.
     */

    
//-----------------------------------------------------//
    
    ArrayList<Player> players;
    
    
    //----------------------------------------------------------------//

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dicePane.setVisible(true);
        dicePane.setMouseTransparent(false);
        dicePane.getParent().setMouseTransparent(false);
        leftDie.textProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
             try{   
                Thread.sleep((long) 2000.0);
            } catch (InterruptedException ex) {
                Logger.getLogger(GameScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }  
             dicePane.setMouseTransparent(true);
             dicePane.getParent().setMouseTransparent(true);

             dicePane.setVisible(false);

            }
        });
        
        
        // TODO
        //Parent game_screen_parent = FXMLLoader.load(getClass().getResource("GameScreen.fxml"));
        //Scene game_screen_scene = new Scene(game_screen_parent);
       // Stage a_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //a_stage.setScene(game_screen_scene);
        //playerGUI.setPickOnBounds(false);
        
        
        HexBoard board = new HexBoard();
       // Stage stage= (Stage) lastAnchorPane.getScene().getWindow();
        //System.out.println(lastAnchorPane.getScene());
        /*scene.setOnMousePressed((MouseEvent event) -> {
            System.out.println("mouse click detected! "+event.getSource());
        });
          */   
        for(HexVertex v: board.vertexList)
        {
            v.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                setSelectedItem(v);
            if(!popupDialog.isVisible()){
                popupDialog.setVisible(true);
                
                popupDialog.setMouseTransparent(false);
                popupDialog.getParent().setMouseTransparent(false);
                for (Node n: popupDialog.getChildren()){
                    n.setMouseTransparent(false);
                }
                
            }
                //popupDialog.setLayoutX(v.position.getX());
                //popupDialog.setLayoutY(v.position.getY());
                popupDialog.relocate(v.position.getX(), v.position.getY());
                //popupDialog.setLayoutY(v.getLayoutY());
            
            
            //selectedCircle.set(circle);
            //selectedLocation.set(new Point2D(x, y));
        });

        }
     
        
        gameBoard.getChildren().add(board.getBoardPane());
        
      
        
 
      
     
    
    }
    public void setSelectedItem(Node o){
        selectedItem=o;
    }
    
    public void rollDice(){
       btnRollDice.setDisable(true);
            Integer r;
            Random z = new Random();
            r=z.nextInt(6)+1;
            diceValue=r;
            System.out.println(r);
           leftDie.setText(r.toString());

           
            r=z.nextInt(6)+1;
            diceValue+=r;
            rightDie.setText(r.toString());
            System.out.println(r);

        
       
    }
    public void gameLoop(){
        int gameState=1;
        
        //is Player's turn
        //if so,
        btnRollDice.setDisable(false);
        
        generateResources(diceValue);
        
        //Player actions
        
        //build
        

        //trade
        
        
        //develop
        

    


         //get list of players
         //roll dice to find the first person's turn
         
         //first player's turn
         
         
            
            
        
        
    }
    
    public void closeParentPane() throws IOException{
        popupDialog.getParent().setMouseTransparent(true); 
        
        popupDialog.setMouseTransparent(true); 
        popupDialog.setVisible(false);
        
    }
    public void handleClick(MouseEvent e) throws IOException{
       
      System.out.println("Clicked!!!"
              +e+"\n"
              +e.getSource().getClass()+"\n"
              
             
      );
      /*if(popupDialog.isVisible() ){
        try{
          ((Button) (e.getSource())).toString();
        }catch(Exception ex){
              
        }
        
        popupDialog.setVisible(false);
        popupDialog.getParent().setMouseTransparent(true);
        System.out.println("BOOM");
      }*/
    }    
    public void handleButtonAction(ActionEvent event) throws IOException{
       
        
    }
   
     public void handleVertexButtonAction(ActionEvent event) throws IOException {
               String m=((RadioButton)event.getSource()).getText();
               System.out.println("This is a Vertex "+ m);

             
        //label.setText("Hello World!");

       
    } 
     public void handleEdgeButtonAction(ActionEvent event) throws IOException {
               String m=((RadioButton)event.getSource()).getText();
               System.out.println("This is an Edge:  "+ m);

             
        //label.setText("Hello World!");

       
    }
     
     
     //Buid Family of Functions:
     
     public void buildSettlement(){
         if(canBuild()){
             
             
         }
     }
     public Boolean canBuild(){
         return true;
     }
     public void buildRoad(){
         
     }
     public void buildCity(){
         
     }

    private void generateResources(int diceValue1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void createTestPlayers(){
        Player mark = new Player("Mark", new int[]{5,5,5,5,5});
        Player dek = new Player("Dehkoda", new int[]{5,5,5,5,5});
        Player lisa = new Player("Lisa", new int[]{5,5,5,5,5});
        Player mew = new Player("Lisa", new int[]{5,5,5,5,5});
        players.add(mark);
        players.add(dek);
        players.add(lisa);
        players.add(mew);
        
//        return mark;
        
    }
}
