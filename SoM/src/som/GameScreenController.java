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
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;

import javafx.scene.text.Text;
import som.assets.Settlement;
import som.ResourceBank;



/*
                                                                                                                                              
                                                                                                                                              
TTTTTTTTTTTTTTTTTTTTTTTEEEEEEEEEEEEEEEEEEEEEE   SSSSSSSSSSSSSSS TTTTTTTTTTTTTTTTTTTTTTTIIIIIIIIIINNNNNNNN        NNNNNNNN        GGGGGGGGGGGGG
T:::::::::::::::::::::TE::::::::::::::::::::E SS:::::::::::::::ST:::::::::::::::::::::TI::::::::IN:::::::N       N::::::N     GGG::::::::::::G
T:::::::::::::::::::::TE::::::::::::::::::::ES:::::SSSSSS::::::ST:::::::::::::::::::::TI::::::::IN::::::::N      N::::::N   GG:::::::::::::::G
T:::::TT:::::::TT:::::TEE::::::EEEEEEEEE::::ES:::::S     SSSSSSST:::::TT:::::::TT:::::TII::::::IIN:::::::::N     N::::::N  G:::::GGGGGGGG::::G
TTTTTT  T:::::T  TTTTTT  E:::::E       EEEEEES:::::S            TTTTTT  T:::::T  TTTTTT  I::::I  N::::::::::N    N::::::N G:::::G       GGGGGG
        T:::::T          E:::::E             S:::::S                    T:::::T          I::::I  N:::::::::::N   N::::::NG:::::G              
        T:::::T          E::::::EEEEEEEEEE    S::::SSSS                 T:::::T          I::::I  N:::::::N::::N  N::::::NG:::::G              
        T:::::T          E:::::::::::::::E     SS::::::SSSSS            T:::::T          I::::I  N::::::N N::::N N::::::NG:::::G    GGGGGGGGGG
        T:::::T          E:::::::::::::::E       SSS::::::::SS          T:::::T          I::::I  N::::::N  N::::N:::::::NG:::::G    G::::::::G
        T:::::T          E::::::EEEEEEEEEE          SSSSSS::::S         T:::::T          I::::I  N::::::N   N:::::::::::NG:::::G    GGGGG::::G
        T:::::T          E:::::E                         S:::::S        T:::::T          I::::I  N::::::N    N::::::::::NG:::::G        G::::G
        T:::::T          E:::::E       EEEEEE            S:::::S        T:::::T          I::::I  N::::::N     N:::::::::N G:::::G       G::::G
      TT:::::::TT      EE::::::EEEEEEEE:::::ESSSSSSS     S:::::S      TT:::::::TT      II::::::IIN::::::N      N::::::::N  G:::::GGGGGGGG::::G
      T:::::::::T      E::::::::::::::::::::ES::::::SSSSSS:::::S      T:::::::::T      I::::::::IN::::::N       N:::::::N   GG:::::::::::::::G
      T:::::::::T      E::::::::::::::::::::ES:::::::::::::::SS       T:::::::::T      I::::::::IN::::::N        N::::::N     GGG::::::GGG:::G
      TTTTTTTTTTT      EEEEEEEEEEEEEEEEEEEEEE SSSSSSSSSSSSSSS         TTTTTTTTTTT      IIIIIIIIIINNNNNNNN         NNNNNNN        GGGGGG   GGGG



*/

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
            pnBuild,
            pnPlayerLeft, 
            pnPlayerMid, 
            pnPlayerRight,
          
            pnAcceptTradeDialog;

    
    @FXML
    Slider sldVictoryPoints;
    
    @FXML
    Label   lblCurrentStatus;

   
    
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
    private HBox tradeResourceTrackers,tradeResponses,
            hbIncomingResources, hbOutgoingResources;
    @FXML
    private VBox vbTradeContents;



    Node selectedItem;
    int diceValue;
    int longestRoadValue;
    int largestArmyValue;
    final int SETTLMENT_VP_VALUE=1;
    
    
    //bonuses
    int freeRoad;                           //number of free roads the player can place
    int freeSettlement;                      //number of free settlmenets the player can place
    //int resourcePass;                       //these skip checking resource requirements. 
    int ignoreRoadAdjacency;
//Circle selectedCircle=new HexVertex();
    
    /**
     * Initializes the controller class.
     */

    
//-----------------------------------------------------//
    
    Player thisPlayer=new Player("mark",new int[]{0,0,0,0,0}, Color.GREEN);
    TradePack thisPlayerTradePack;
    ArrayList<Player> players;
    DevelopmentDeck thisDevelopmentDeck;
    DevelopmentCard thisCard;
    HexBoard board; 
    int turnCount;
    ResourceBank resourceBank=new ResourceBank(19);
    ResourceGenerator resourceGenerator;

    
    //----------------------------------------------------------------//

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        board= new HexBoard();
        resourceGenerator= new ResourceGenerator(board);
        turnCount=1;
        sldVictoryPoints.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                    for (Player p : players){
                        p.setVictoryPoints((Math.round((new_val.floatValue()))));
                        System.out.println(((Math.round((new_val.floatValue()))))+"  "+ p.victoryPointGauge.getLength());
                        
                    }
            }
        });
        
        
        
        /*
        // THIS IS THE SECTON FOR THE ROLL DICE PANE AND FUNCTIONALITY 
        dicePane.setVisible(true);
        dicePane.setMouseTransparent(false);
        dicePane.getParent().setMouseTransparent(false);*/
        /*leftDie.textProperty().addListener(new ChangeListener(){
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
        });*/
        
        // ROLL DICE SECTION ENDS
        longestRoadValue=5;
        largestArmyValue=3;
        freeRoad=2;
        freeSettlement=2;
//        resourcePass=4;
        ignoreRoadAdjacency=2;
        // TODO
        
        
        for (Hex hex: board.hexList){
            hex.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                setSelectedItem(hex);
                hex.setFill(Color.YELLOW);
                System.out.println(
                        //hex.getTokenValue()+"\n"
                        //+hex.getVerticies()+"\n+---------+\n"
                );
                int vertexCounter=0;
             //System.out.println("[\t\t\t\t\t"+hexCounter+"\t\t\t\t\t]");
             for(HexVertex vertex: hex.getVerticies()){
                 System.out.print(vertexCounter+"\t\t");
                 System.out.print("BEFORE ASSN: \t"+vertex +"\n" );                 
                 System.out.println("it's in the list on at index: "+board.vertexList.indexOf(vertex));
                 System.out.println("BEFORE ASSN: "+ ((boolean)(vertex==board.vertexList.get(board.vertexList.indexOf(vertex)))));
                 vertex=board.vertexList.get(board.vertexList.indexOf(vertex));
                System.out.println("AFTER ASSN: "+ ((boolean)(vertex==board.vertexList.get(board.vertexList.indexOf(vertex)))));
                 System.out.print("AFTER ASSN: \t"+vertex +"\n\n\n" );                 
                               
                

                 vertexCounter++;
             }
                
        });
        }
                    
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
                                popupDialog.relocate(hexVertex.getPosition().getY(), hexVertex.getPosition().getX());

            }else{
                txtPopUpText.setText("Old: \n"+ popupDialog.getLayoutX()+", "+popupDialog.getLayoutY()
                            +"Current: "+hexVertex.getPosition().getX()+300+", "+ hexVertex.getPosition().getY()
                        );
                popupDialog.relocate(hexVertex.getPosition().getX()+350, hexVertex.getPosition().getY());
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
                popupDialog.relocate(Math.abs(hexEdge.getStartPoint().getY()-hexEdge.getEndPoint().getY()),Math.abs(hexEdge.getStartPoint().getX()-hexEdge.getEndPoint().getX()));
                    
                }else{
                txtPopUpText.setText("Old: \n"+ popupDialog.getLayoutX()+", "+popupDialog.getLayoutY()
                            +"Current: "+
                            Math.abs(hexEdge.getStartPoint().getX()-hexEdge.getEndPoint().getX())
                            +","+
                            Math.abs(hexEdge.getStartPoint().getY()-hexEdge.getEndPoint().getY())
                );
                popupDialog.relocate(Math.abs(hexEdge.getStartPoint().getX()-hexEdge.getEndPoint().getX()),Math.abs(hexEdge.getStartPoint().getY()-hexEdge.getEndPoint().getY()));
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
 
      
     
    //gameLoop();
    }
    public void setSelectedItem(Node o){
        selectedItem=o;
    }
    
    public int rollDice(){
       btnRollDice.setDisable(true);
            int diceValue;
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
            return diceValue;
        
       
    }
    public void function(){
                generateResources(rollDice());
                lblCurrentStatus.setText(Arrays.toString(resourceBank.resources));
        	
    }

    public void gameLoop(){
        int gameState=1;
        
        
        
        //is Player's turn
        //if so,
        
        boolean endGame=false;
        while (!endGame){
        btnRollDice.setDisable(false);
        //ResourceGenerator.generateResourcesiceValue);
        
        //Player actions
        
        //build
        

        //trade
        
        
        //develop
        

    


         //get list of players
         //roll dice to find the first person's turn
         
         //first player's turn
         endGame=getWinCondition();
        }
         
            
            
        
        
    }

    public boolean getWinCondition(){
        for (Player player: players){
            if(player.getVictoryPoints()>=10){
                 System.out.println("GAME OVER");

                return true;
            }
        }
        
        return false;
    }
    public void endTurn(){
        
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
     
     public void buildSettlement() throws IOException{
         if(canBuildSettlement((HexVertex) selectedItem)){
             System.out.println("BUILD SETTLEMENT DIALOG");
            if (freeSettlement<=0){                                                                                                               
            resourceBank.bankReturnResource(thisPlayer.removeResources(resourceBank.getResourceCost(((HexVertex)selectedItem).addSettlement(thisPlayer))));         //remove resources from a player and give them to the bank
            }else{
                ((HexVertex)selectedItem).addSettlement(thisPlayer);
                freeSettlement--;
            }
            ((HexVertex) selectedItem).setFill((Paint) thisPlayer.getPlayerColor());                                //set fill to color the vertex the player's color (indicating a settlement
            thisPlayer.assets.add(thisPlayer,((HexVertex)selectedItem));                                            //add this new asset to the player's list of assets
            thisPlayer.setVictoryPoints((thisPlayer.getVictoryPoints())+SETTLMENT_VP_VALUE);                        //increase the vp of the player
            pgbVictoryPoints.setProgress(((double)thisPlayer.getVictoryPoints()/10));                               //adjust the progress bar for vp for thisPlayer
            updateResources();
            for(HexEdge edge:((HexVertex)selectedItem).getAdjacentEdge() ){
                edge.setStroke(Color.WHITE);
                if(((HexEdge)edge).isOwned()){
                    edge.setFill(Color.GREENYELLOW);
                }
            }
            //test

            HexVertex hv=(HexVertex)selectedItem;
            int index=board.vertexList.indexOf(hv);
            for(HexEdge edge: ((HexVertex) selectedItem).getAdjacentEdge()){
                
                System.out.println("//");
                try{
                    
                    ((HexVertex)(edge.getOtherPoint(((HexVertex)selectedItem)))).setFill(Color.GOLDENROD);

                }catch(NullPointerException nullPointer){
                    System.out.println("FAIL");
                }
                
            }
           closeParentPane();

             
         }
     }
     public void getSelectedItemInfo(){
            System.out.println((HexEdge)selectedItem);
            System.out.println((HexVertex)selectedItem);
     }
     public boolean canBuildSettlement(HexVertex selectedItem){
            boolean result=true;
            //if (thisPlayer.assets.settlements.size()>=1&&thisPlayer.assets.cities.size()>=1){
             
                int localCount=1;
                for(HexEdge edge: selectedItem.getAdjacentEdge() ){                            //check all adjacent edges' "otherVertex" to see if it is occupied with an asset
                    localCount++;
                    result= ((HexVertex) (edge.getOtherPoint(((HexVertex)selectedItem)))).getAsset()==null;         //if any of the "otherVertex"'s are null then continue on
                    
                    
                    if (!result){return result ;}                                                                   //if any of the otherVertex's contain an asset return false and player cannot build here

                }
                
                for(HexEdge edge: selectedItem.getAdjacentEdge() ){                                                 //check all adjacent edges for roads, then ensure that road belongs to the building player
                    if(ignoreRoadAdjacency<=0){
                        if(edge.isOwned()){
                            result=true;
                            break;
                        }else{
                            result=false;
                            continue;
                        }
                    }else{
                        ignoreRoadAdjacency--;
                        result= true;
                        break;
                    }
                }
                /*if((((HexVertex)(e.getOtherPoint(selectedItem))).getAsset())==null){    //in each edge check if the otherPoint has city or settlement
                        result=true;                                                        //only returns true if the otherPoint has no settlement
                    }*/
            

                //check resource requirements

 
              
               
         return result;
     }
     private boolean checkDistanceRuleSettlement(HexVertex selectedItem){
             boolean result=false;

         for(HexEdge e: selectedItem.getAdjacentEdge() ){
             /*    if((((HexVertex)(e.getOtherPoint(selectedItem))).getAsset())!=null){
             result=true;
             }else{
             return false;
             }*/
                   }
         return result;
     }
     public void buildRoad(ActionEvent e) throws IOException{
         if(canBuildRoad(((HexEdge)selectedItem), thisPlayer)){
             if(freeRoad>0){
                ((HexEdge)selectedItem).addRoad(thisPlayer);
                freeRoad--;
             }else{
                resourceBank.bankReturnResource(thisPlayer.removeResources(resourceBank.getResourceCost(((HexEdge)selectedItem).addRoad(thisPlayer))));
             }
            thisPlayer.assets.add(thisPlayer,((HexEdge)selectedItem));
            ((HexEdge) selectedItem).setStroke((Paint)thisPlayer.getPlayerColor());                                //set fill to color the vertex the player's color (indicating a settlement


            updateResources();
            

                                                                                                //put resources back to the bank
         System.out.println("BUILD ROAD DIALOG");
         System.out.println(((HexEdge)selectedItem).getAdjacentEdge());
          checkForLongestRoad();

          System.out.println("PRogress: " + thisPlayer.assets.roads.size());
          //System.out.println(((HexEdge) selectedItem).getType().toString());
        for(HexEdge edge:((HexEdge)selectedItem).getAdjacentEdge() ){
             edge.setStroke(Color.WHITE);
             System.out.println(edge.isOwned());
         }
          closeParentPane();
         } 
     }
     private boolean canBuildRoad(HexEdge hexEdge, Player player)   {
         
         if(checkRequirements(hexEdge, player)&&!((HexEdge)selectedItem).isOwned()){
             //check all the adjacent edges to see if at leaset one of them has a road
             for(HexEdge edge : hexEdge.getAdjacentEdge()){
                 
                 if(edge.isOwned()){
                     System.out.println(edge.getOwner());
                     if(edge.getOwner().equals(thisPlayer)){
                         return true;
                     }
                 }
             }
                // Check if startpoint or endpoint has an asset on it's vertex
                try
                {               
                    if(hexEdge.getStartVertex().getAsset()!=null){

                        if(hexEdge.getStartVertex().getAsset().getPlayer().equals(thisPlayer)){
                        return true;
                    }
                }
                }
                catch (NullPointerException nullPointer)
                {
                    System.out.println("FAIL ON START");
                }   
                
                try{
                    System.out.println("This vertex was not null");
                        if(hexEdge.getEndVertex().getAsset().getPlayer().equals(thisPlayer)){
                            return true;
                        }

                    

                    //return true;
                }
                catch(NullPointerException nullPointer){
                    System.out.println("FAIL ON END");
                }

                System.out.println(hexEdge.getAdjacentVertex());
             
         }
     return false;
    }

     private boolean checkRequirements(HexEdge hexEdge, Player player){             //checks requirem   ents for road
        //road requirements: BRICK AND LUMBER (plastic + glass) 1+4
        
        return (freeRoad>0||
                isGreater(thisPlayer.getResources(), new int[]{0,1,0,0,1}));
                
        
    }
        private boolean checkRequirements(HexVertex hexVertex, Player player){      //checks requirements for settlement
        //road requirements: BRICK AND LUMBER (plastic + glass) 1+4
        
        return (isGreater(thisPlayer.getResources(), new int[]{0,1,1,1,1}));
                
        
    }
        private boolean checkRequirements(HexVertex hexVertex, Player player, Settlement settlement){      //checks requirements for settlement

            return (isGreater(thisPlayer.getResources(), new int[]{3,0,0,2,0}));

        }
        private boolean checkRequirements(Player player){      //checks requirements for developmentCards

            return (isGreater(thisPlayer.getResources(), new int[]{1,0,1,1,0}));

        }
       
    private boolean isAdjacentEdgeOwned(HexEdge hexEdge, Player player){
        for(HexEdge edge: hexEdge.getAdjacentEdge()){
            if(edge.isOwned()){
                
            }
        }
        
        
        
        return false;
    }
    private boolean isGreater(int[] a, int[] b){                                //checks if the contents of int[]a are greater than int[] b
        for(int i=0; i<a.length; i++){
            if(a[i]>=b[i]){
               continue; 
            }else{
                return false;
            }
        }
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
         if(canBuildCity((HexVertex) selectedItem)){
             System.out.println("BUILD SETTLEMENT DIALOG");
            resourceBank.bankReturnResource(thisPlayer.removeResources(resourceBank.getResourceCost(((HexVertex)selectedItem).addCity(thisPlayer))));         //remove resources from a player and give them to the bank
            }
            ((HexVertex) selectedItem).setStroke(Color.GOLD);                                //set fill to color the vertex the player's color (indicating a settlement
            thisPlayer.assets.add(thisPlayer,((HexVertex)selectedItem));                                            //add this new asset to the player's list of assets
            thisPlayer.setVictoryPoints((thisPlayer.getVictoryPoints())+SETTLMENT_VP_VALUE+1);                        //increase the vp of the player
            pgbVictoryPoints.setProgress(((double)thisPlayer.getVictoryPoints()/10));                               //adjust the progress bar for vp for thisPlayer
            updateResources();
         
     }

    private void generateResources(int diceValue) {
        resourceGenerator.generateResources(diceValue);
        updateResources();




    }
    public void createTestPlayers(){
        players=new ArrayList<>();
        Player mark = new Player("Mark", new int[]{0,0,0,0,0}, Color.GREEN);
        Player dek = new Player("Dehkoda", new int[]{0,0,0,0,0}, Color.RED);
        Player lisa = new Player("Lisa", new int[]{0,0,0,0,0}, Color.BLUE);
        Player mew = new Player("Mew", new int[]{0,0,0,0,0}, Color.VIOLET);
        players.add(mark);
        players.add(dek);
        players.add(lisa);
        players.add(mew);
        thisPlayer=mark;
//      return mark;
        
    }
    public void openTradeDialog(){
        //set Resource Values
        //1) get hbox with tradeTrackers in it
        ObservableList<Node> trackers, anchors;
        trackers = tradeResourceTrackers.getChildren();                         //get the resource trackers frmo the TradeResourceTrackers HBox
        for(Node node: trackers){
            anchors=((VBox) node).getChildren();
            ((TradeResourceTracker)node).setResourcesAvailable(thisPlayer.resources[trackers.indexOf(node)]);
            for(Node n: anchors){
                System.out.println(((AnchorPane)n).getChildren());
                //0 Circle
                System.out.println(anchors.size()+"!!!!"+ anchors.indexOf(n));
                ((Circle)((AnchorPane)n).getChildren().get(0)).setFill((Paint)board.getColorPallete()[trackers.indexOf(node)]); 
                //((Circle)((AnchorPane)n).getChildren().get(0)).setFill(Color.); 

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
    @FXML
    private void simulateTradeRequest(){
        offerTrade(new TradePack(players.get(2),thisPlayer,new int[]{0,2,2,0,1}, new int[]{1,0,0,2,0}));
        System.out.println(thisPlayer);
    }
    
        //offer trade should fire an event with whichever player is being offered a trade
        //need to find a way to get a response from players
    private boolean offerTrade(TradePack tp) {
        boolean response=false;
        if (tp.getReceiver()==thisPlayer){
            //open trade requested dialog
            thisPlayer.setTradePack(tp);
            pnAcceptTradeDialog.setVisible(true);
            pnAcceptTradeDialog.getParent().setMouseTransparent(false);
            
            ((Label)(vbTradeContents.getChildren().get(0))).setText(tp.getSender().getNickname());
            for (Node n: hbIncomingResources.getChildren()){
                ((Circle)((StackPane) n).getChildren().get(0)).setFill((board.getColorPallete())[((ObservableList)(hbIncomingResources.getChildren())).indexOf(n)]);
                ((Label)((StackPane) n).getChildren().get(1)).setText(""+tp.getResourcesOffered()[((ObservableList)(hbIncomingResources.getChildren())).indexOf(n)]);
            }
            
            for (Node n: hbOutgoingResources.getChildren()){
                ((Circle)((StackPane) n).getChildren().get(0)).setFill((board.getColorPallete())[((ObservableList)(hbOutgoingResources.getChildren())).indexOf(n)]);
                ((Label)((StackPane) n).getChildren().get(1)).setText(""+tp.getResourcesRequested()[((ObservableList)(hbOutgoingResources.getChildren())).indexOf(n)]);
            }
   
            
            
            return response;
        }else{
            return generateTradeResponse();
        }

    }
    
    
    @FXML
    public void tradeDialogYes(){
            acceptTrade(thisPlayer.getTradePack());
            pnAcceptTradeDialog.setVisible(false);
            pnAcceptTradeDialog.getParent().setMouseTransparent(true);
    }
    public void tradeDialogNo(){
            //acceptTrade(thisPlayer.getTradePack());
            pnAcceptTradeDialog.setVisible(false);
            pnAcceptTradeDialog.getParent().setMouseTransparent(true);
    }    
    private void acceptTrade(TradePack tp) {
        System.out.println("this player is: "+thisPlayer.getNickname());
       // System.out.println("this player is: "+tp.getReceiver().getNickname());

        System.out.println(tp);
        if (tp.getReceiver()==thisPlayer){
            /*for (int i=0;i<thisPlayer.resources.length;i++){
            thisPlayer.resources[i]+=tp.resourcesOffered[i];
            thisPlayer.resources[i]-=tp.resourcesRequested[i];
            }*/
            thisPlayer.setResources(addTwoResourceSets(thisPlayer.getResources(), tp.getResourcesOffered()));
            thisPlayer.setResources(subtractTwoResourceSets(thisPlayer.getResources(), tp.getResourcesRequested()));
            updateResources();

        if (tp.getSender()==thisPlayer){
            for (int i=0;i<thisPlayer.resources.length;i++){
                /*thisPlayer.resources[i]-=tp.resourcesOffered[i];
                thisPlayer.resources[i]+=tp.resourcesRequested[i];
                */
            thisPlayer.setResources(addTwoResourceSets(thisPlayer.getResources(), tp.getResourcesRequested()));
            thisPlayer.setResources(subtractTwoResourceSets(thisPlayer.getResources(), tp.getResourcesOffered()));
                updateResources();

            }
            
        }
    
        
        System.out.println(thisPlayer.getResources());
            
            //open trade requested dialog
        }else{
        }

    }

    
    private int[] addTwoResourceSets(int[] a, int[] b){
        int[] result=new int[a.length];
        for (int i=0; i<result.length; i++){
            result[i]=a[i]+b[i];
        }
            
        return result;
    }
    private int[] subtractTwoResourceSets(int[] a, int[] b){
        int[] result=new int[a.length];
        for (int i=0; i<result.length; i++){
            result[i]=a[i]-b[i];
        }
        return result;
    }

    void updateResources() {
        txtRes00Text.setText(""+(thisPlayer.getResources())[0]);
        txtRes01Text.setText(""+(thisPlayer.getResources())[1]);
        txtRes02Text.setText(""+(thisPlayer.getResources())[2]);
        txtRes03Text.setText(""+(thisPlayer.getResources())[3]);
        txtRes04Text.setText(""+(thisPlayer.getResources())[4]);
        
            
    }
    
    private void declineTrade(TradePack tp) {
        boolean response=false;
        if (tp.getReceiver()==thisPlayer){
            //open trade requested dialog
        }else{
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
    
    public void openBuild(){
        
        pnBuild.setVisible(true);
        pnBuild.setMouseTransparent(false);
        pnBuild.getParent().setMouseTransparent(false);
        
    }
    public void buildDev(ActionEvent e) throws IOException{
        System.out.println("You clicked me");
      
        if(canBuyDev(thisPlayer)){//check if player has the requirements to buy dev card
            if(thisDevelopmentDeck.hasCard()){//check there is a dev card to take
               resourceBank.bankReturnResource(2,1);//return 1 hemp to bank
               resourceBank.bankReturnResource(3, 1);//return 1 soy to bank 
               resourceBank.bankReturnResource(0, 1);//return 1 steel to bank 
               System.out.println("RETURNED TO BANK");
               thisPlayer.setResources(subtractTwoResourceSets(thisPlayer.getResources(), new int[]{1,0,1,1,0}));//subtract resources used from player's resources
               System.out.println("SUBTRACTED RESOURCES");
               thisCard=thisDevelopmentDeck.drawCard();//take development card from deck
               System.out.println("SAVED CARD FROM DECK");
               thisPlayer.add(thisCard);
               System.out.println("YOU'VE BUILT A DEV CARD");
            }
        }
    }
    private boolean canBuyDev(Player player){
        if(checkRequirements(player)){
            return true;
           }
        return false;
    }
    
    public void closeBuild(){
        pnBuild.setVisible(false);
        pnBuild.setMouseTransparent(true);
        pnBuild.getParent().setMouseTransparent(true);
        
    }

    private boolean winCondition() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean canBuildCity(HexVertex hexVertex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}

