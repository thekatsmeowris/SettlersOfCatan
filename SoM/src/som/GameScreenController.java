/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package som;


import customcontrols.TradeResourceTracker;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;

import javafx.scene.text.Text;



/**
 * FXML Controller class
 *
 * @author makogenq
 */
public class GameScreenController implements Initializable {
    @FXML
    Pane 
            gameLayer, 
            gameBoard,
            playerGUI, 
            popupDialog,
            dicePane,
            pnTradeDialog,
            pnPlayerLeft, 
            pnPlayerMid, 
            pnPlayerRight;

    
    @FXML
    Slider sldVictoryPoints;
    
   
    
    @FXML 
    Text 
            txtPopUpText, 
            leftDie,
            rightDie,
            txtRes00Text,
            txtRes01Text, 
            txtRes02Text,
            txtRes03Text,
            txtRes04Text;

    @FXML
    AnchorPane lastAnchorPane;

                
    
    

    @FXML
    Button btnRollDice;
    
    
    
    
    @FXML
    Text txtThisPlayerName;
    
    @FXML
    ProgressBar pgbLongestRoad, pgbVictoryPoints;
    
    @FXML
    private HBox tradeResourceTrackers,tradeResponses;



    Node selectedItem;
    int diceValue;
    int longestRoadValue;
    int largestArmyValue;
    final int SETTLMENT_VP_VALUE=1;
    //Circle selectedCircle=new HexVertex();
    
    /**
     * Initializes the controller class.
     */

    
//-----------------------------------------------------//
    Player thisPlayer=new Player("mark",new int[]{5,5,5,5,5});
    TradePack thisPlayerTradePack;
    ArrayList<Player> players;
    HexBoard board; 

    
    //----------------------------------------------------------------//

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        board= new HexBoard();
        sldVictoryPoints.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                    for (Player p : players){
                        p.setVictoryPoints((Math.round((new_val.floatValue()))));
                        System.out.println(((Math.round((new_val.floatValue()))))+"  "+ p.victoryPointGauge.getLength());
                        
                    }
            }
        });
        
        
        
        
        // THIS IS THE SECTON FOR THE ROLL DICE PANE AND FUNCTIONALITY 
        dicePane.setVisible(true);
        dicePane.setMouseTransparent(false);
        dicePane.getParent().setMouseTransparent(false);
        leftDie.textProperty().addListener(new ChangeListener(){
        
            // a change listener, once the value on the dice change it should do a 2 second sleep and then kill the dice pane
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
        
        // ROLL DICE SECTION ENDS
        longestRoadValue=3;
        largestArmyValue=3;
        
        // TODO
        
        

        for(HexVertex hexVertex: board.vertexList)
        {
            hexVertex.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                setSelectedItem(hexVertex);
            if(!popupDialog.isVisible()){
                popupDialog.setVisible(true);
                //board.popUpDialog.setVisible(true);

                popupDialog.setMouseTransparent(false);
                //popupDialog.getParent().setMouseTransparent(false);
                for (Node n: popupDialog.getChildren()){
                    n.setMouseTransparent(false);
                }
                
            }
            if(gameBoard.getRotate()>0){
                                popupDialog.relocate(hexVertex.position.getY(), hexVertex.position.getX());

            }else{
                txtPopUpText.setText("Old: \n"+ popupDialog.getLayoutX()+", "+popupDialog.getLayoutY()
                            +"Current: "+hexVertex.position.getX()+300+", "+ hexVertex.position.getY()
                        );
                popupDialog.relocate(hexVertex.position.getX()+350, hexVertex.position.getY());
                //popupDialog.setLayoutX(v.position.getX());
                //popupDialog.setLayoutY(v.position.getY());
                
            }
                //popupDialog.setLayoutY(v.getLayoutY());
            
            
            //selectedCircle.set(circle);
            //selectedLocation.set(new Point2D(x, y));
        });

        }
        
                for(HexEdge hexEdge: board.edgeList)
        {
            hexEdge.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                setSelectedItem(hexEdge);
            if(!popupDialog.isVisible()){
                popupDialog.setVisible(true);
                //board.popUpDialog.setVisible(true);

                popupDialog.setMouseTransparent(false);
                //popupDialog.getParent().setMouseTransparent(false);
                for (Node n: popupDialog.getChildren()){
                    n.setMouseTransparent(false);
                }
                
            }
                if(gameBoard.getRotate()>0){
                popupDialog.relocate(Math.abs(hexEdge.startPoint.getY()-hexEdge.endPoint.getY()),Math.abs(hexEdge.startPoint.getX()-hexEdge.endPoint.getX()));
                    
                }else{
                txtPopUpText.setText("Old: \n"+ popupDialog.getLayoutX()+", "+popupDialog.getLayoutY()
                            +"Current: "+
                            Math.abs(hexEdge.startPoint.getX()-hexEdge.endPoint.getX())
                            +","+
                            Math.abs(hexEdge.startPoint.getY()-hexEdge.endPoint.getY())
                );
                popupDialog.relocate(Math.abs(hexEdge.startPoint.getX()-hexEdge.endPoint.getX()),Math.abs(hexEdge.startPoint.getY()-hexEdge.endPoint.getY()));
                }
                /*
                popupDialog.setLayoutX(v.getLayoutX());
                popupDialog.setLayoutY(v.getLayoutY());*/                

                //popupDialog.setLayoutY(v.getLayoutY());
            
            
            //selectedCircle.set(circle);
            //selectedLocation.set(new Point2D(x, y));
        });

        }

     
        
        gameBoard.getChildren().add(board.getBoardPane());
        
//Player GUI Stuff
    makeResources();
    createTestPlayers();
    fillPlayerInfo();
 
      
     
    
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
        //popupDialog.getParent().setMouseTransparent(true); 
//        board.popUpDialog.setVisible(false);

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
             System.out.println("BUILD SETTLEMENT DIALOG");
             ((HexVertex)selectedItem).addSettlement();
         System.out.println("BUILD Settlement DIALOG");
         thisPlayer.assets.add(thisPlayer,((HexVertex)selectedItem));
         thisPlayer.setVictoryPoints((thisPlayer.getVictoryPoints())+SETTLMENT_VP_VALUE);
         pgbVictoryPoints.setProgress(((double)thisPlayer.getVictoryPoints()/10));
          System.out.println(((HexVertex)selectedItem).adjacentEdge);
          
             
         }
     }
     public Boolean canBuild(){
         return true;
     }
     public void buildRoad(ActionEvent e) throws IOException{
         if(canBuildRoad((HexEdge)selectedItem)){
         ((HexEdge)selectedItem).addRoad();
         System.out.println("BUILD ROAD DIALOG");
         thisPlayer.assets.add(thisPlayer,((HexEdge)selectedItem));
          checkForLongestRoad();

          System.out.println("PRogress: " + thisPlayer.assets.roads.size());
          //System.out.println(((HexEdge) selectedItem).getType().toString());

          closeParentPane();
         }
     }
     private boolean canBuildRoad(HexEdge hexEdge) {
         //if(hexEdge.adjacentEdge.contains(this))
          /* 
            if thisPlayer.resources are greater than or equal to Asset.requirement then continue
            else return false
            
            if the adjacent vertex contains an asset belonging to thisPlayer, then return true
            elseif the adjacent edges contain at least one road belonging to thisPlayer then return true
            else rturn false
         
         
         */
     return true;
    }
    
     
     public void checkForLongestRoad(){
         if(thisPlayer.assets.roads.size()>longestRoadValue) longestRoadValue=thisPlayer.assets.roads.size();
         
         pgbLongestRoad.progressProperty().set((double)(thisPlayer.assets.roads.size())/longestRoadValue);
         if (pgbLongestRoad.progressProperty().doubleValue()>=100.0){
             pgbLongestRoad.getStyleClass().add("gold-bar");
         }else{
            pgbLongestRoad.getStyleClass().add("basic-bar");

         }
         
     }
     public void buildCity(){
         System.out.println("BUILD CITY DIALOG");
         
     }

    private void generateResources(int diceValue1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void createTestPlayers(){
        players=new ArrayList<>();
        Player mark = new Player("Mark", new int[]{5,5,5,5,5});
        Player dek = new Player("Dehkoda", new int[]{5,5,5,5,5});
        Player lisa = new Player("Lisa", new int[]{5,5,5,5,5});
        Player mew = new Player("Mew", new int[]{5,5,5,5,5});
        players.add(mark);
        players.add(dek);
        players.add(lisa);
        players.add(mew);
        
//        return mark;
        
    }
    public void openTradeDialog(){
        //set Resource Values
        //1) get hbox with tradeTrackers in it
        ObservableList<Node> trackers, anchors;
        trackers = tradeResourceTrackers.getChildren();
        System.out.println("TRACKERS");
        for(Node node: trackers){
            anchors=((VBox) node).getChildren();
            ((TradeResourceTracker)node).setResourcesAvailable(thisPlayer.resources[trackers.indexOf(node)]);
            for(Node n: anchors){
                System.out.println(((AnchorPane)n).getChildren());
                //0 Circle
                ((AnchorPane)n).getChildren().get(0);
                //1 lblResourceGiveValue
                //2 lblResourceReqValue
                //3 Button ^
                //4 Button v
                //5 lblResourcesAvailable
                ((Label)(((AnchorPane)n).getChildren().get(5))).setText(""+thisPlayer.resources[anchors.indexOf(n)]);
                        //thisPlayer.resources[]
                
                
                
                
                
                
                
            }
            
            
        }

        
        
        
        pnTradeDialog.setVisible(true);
        pnTradeDialog.getParent().setMouseTransparent(false);
        pnTradeDialog.setMouseTransparent(false);
        //create new tradepack
        thisPlayerTradePack= new TradePack();
    
        /*
        pnPlayerLeft.setLayoutX();
        pnPlayerMid.setLayoutX();
        pnPlayerRight.setLayoutX();
        */
        
    }

//Trade Functionality    
    public boolean submitTradeOffer(){
       TradePack tp=createTradePackage();
       boolean response;
       //check for receiver
       if (tp.getReceiver()==null){
           //offer to each player
           for(Player p: players){
               if(p!=thisPlayer){
                   tp.setReceiver(p);
                   if(offerTrade(tp)){
                   ((ImageView)(tradeResponses.getChildren().get(players.indexOf(p)+1))).setImage(new Image(""));
               }else{
                    ((ImageView)(tradeResponses.getChildren().get(players.indexOf(p)+1))).setImage(new Image(""));
    
                   }
               }
           }
       }else{
           return offerTrade(tp);
       }
       
       
       System.out.println(tp);
        return false;
        
    } 
    public void closeTradeDialog(){
        pnTradeDialog.setVisible(false);
        pnTradeDialog.getParent().setMouseTransparent(true);
        pnTradeDialog.setMouseTransparent(true);
        //clear tradepack
        thisPlayerTradePack=null;
    }
    public TradePack createTradePackage(){
        Player creator, receiver;
        int []resourcesOffered =new int[5];
        int []resourcesRequested =new int[5];

        Boolean accept;
        
        Boolean tradeSingle=false;
        Boolean tradeBank=false;
        TradePack tp;
        
        ObservableList<Node> trackers;
        trackers = tradeResourceTrackers.getChildren();
            
         for(Node node: trackers){
           resourcesRequested[trackers.indexOf(node)]=((TradeResourceTracker) node).getRequestedAmount();
           resourcesOffered[trackers.indexOf(node)]=((TradeResourceTracker) node).getGivingAmount();
           
         }
        return new TradePack(thisPlayer,players.get(3),resourcesOffered, resourcesRequested);         
    }
        
        //offer trade should fire an event with whichever player is being offered a trade
        //need to find a way to get a response from players
    private boolean offerTrade(TradePack tp) {
        boolean response=false;
        if (tp.getReceiver()==thisPlayer){
            //open trade requested dialog
            return response;
        }else{
            return generateTradeResponse();
        }

    }
      //generate random response
      public boolean generateTradeResponse(){
          return new Random().nextBoolean();
      }
      public void offerResource(MouseEvent e){
    if(pnTradeDialog.isVisible()){
    //thisPlayerTradePack.resourcesOffered[0]+=1;
    }
    
    }
      public void requestResource(Resource rec, TradePack tp){
        
    }
    

//board setup
    public void makeResources(){
        txtRes00Text.setText(""+thisPlayer.resources[0]);
        txtRes01Text.setText(""+thisPlayer.resources[1]);
        txtRes02Text.setText(""+thisPlayer.resources[2]);
        txtRes03Text.setText(""+thisPlayer.resources[3]);
        txtRes04Text.setText(""+thisPlayer.resources[4]);

    }
    public void fillPlayerInfo(){
        //get player icon blocks
        ArrayList<Pane> playerBlocks= new ArrayList<>();
        playerBlocks.add(pnPlayerLeft);
        playerBlocks.add(pnPlayerMid);
        playerBlocks.add(pnPlayerRight);
        int counter=0;
        for(Player p: players){
            if(p.nickname!="Mark"){
                p.pnPlayerInfo=playerBlocks.get(counter);
                ((Label) playerBlocks.get(counter).getChildren().get(6)).setText(p.nickname);
                 p.victoryPointGauge=(Arc) playerBlocks.get(counter).getChildren().get(1);
                System.out.println(p.victoryPointGauge);

                 counter++;

                
            }else{
                txtThisPlayerName.setText(p.nickname);
            }
        }
    }

    
}

