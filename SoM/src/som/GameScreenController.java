/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package som;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.ResourceBundle;

import customcontrols.TradeResourceTracker;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import som.assets.Settlement;
import som.ResourceBank;


/**
 * FXML Controller class
 *
 * @author makogenq
 */
public class GameScreenController implements Initializable {



	@FXML
	Pane gameLayer, 
                gameBoard, 
                playerGUI, 
                popupDialog, 
                dicePane, 
                pnTradeDialog, 
                pnPlayerLeft, 
                pnPlayerMid,
                pnPlayerRight, 
                pnAcceptTradeDialog,
                pnBuild;

	@FXML
	Slider sldVictoryPoints;

	@FXML
	Label txtCurrentStatus, 
                txtLastRoll;

	@FXML
	Text txtPopUpText, 
                leftDie, 
                rightDie, 
                txtRes00Text, 
                txtRes01Text, 
                txtRes02Text, 
                txtRes03Text, 
                txtRes04Text;

	@FXML
	AnchorPane 
                lastAnchorPane, 
                playerInfoPane;

	@FXML
	Button diceRoller, 
                btnRollDice, 
                startGameBtn, 
                tradeBtn, 
                buildBtn, 
                cancelBuildBtn, 
                gameStateBtn, 
                endBtn, 
                devBtn;

	@FXML
	Text txtThisPlayerName;

	@FXML
	ProgressBar 
                pgbLongestRoad, 
                pgbVictoryPoints;

	@FXML
	private HBox 
                tradeResourceTrackers, 
                tradeResponses, 
                hbIncomingResources, 
                hbOutgoingResources;
	@FXML
	private VBox vbTradeContents;
        

	Node selectedItem;
	int diceValue;
	int longestRoadValue;
	int largestArmyValue;
        int freeSettlement;
        int resourcePass;
	final int SETTLMENT_VP_VALUE = 1;
	ResourceBank resourceBank = new ResourceBank(19);
	ResourceGenerator resGen;
        
        static Audio audio = new Audio();
	public final static int MAX_PLAYERS = 4;
	private static Player currentPlayer;
	private static Player playerWithLongestRoad;
	private static Player playerWithLargestArmy;
	private static int gameState;

	public final static int GAME_OVER = 404;
	public final static int NEW_GAME = 0;
	public final static int DETERMINE_PLAYER_ORDER = 1;
	public final static int INIT_BUILD_PHASE_A = 2;
	public final static int INIT_BUILD_PHASE_B = 3;
	public final static int PRE_ROLL = 4;
	public final static int MAIN_PHASE = 5;

	public final static int PLACING_ROAD = 10;
	public final static int PLACING_SETTLEMENT = 11;
	public final static int PLACING_CITY = 12;
	public final static int MOVING_ROBBER = 13;

	public final static int PLAY_ROAD_BUILDING = 15;
	public final static int PLAY_MONOPOLY = 16;
	public final static int PLAY_KNIGHT = 17;
	public final static int PLAY_VICTORY_POINT = 18;

	private static int currentPlayerNumber;
	/*
	 * public final static int DISTR_RESOURCES = 20; public final static int
	 * DISCARDING = 21; public final static int STEALING_RESOURCE = 22; public
	 * final static int
	 * 
	 */

        int freeRoad;                           //number of free roads the player can place
        //int resourcePass;                       //these skip checking resource requirements. 
        int ignoreRoadAdjacency;
    //Circle selectedCircle=new HexVertex();


	/**
	 * Initializes the controller class.
	 */

	// -----------------------------------------------------//

    Player thisPlayer=new Player("Mark",new int[]{5,5,5,5,5}, Color.GREEN);
    TradePack thisPlayerTradePack;
    ArrayList<Player> players;
    DevelopmentDeck developmentDeck= new DevelopmentDeck();
    DevelopmentCard thisCard;
    HexBoard board; 
    int turnCount;
    ResourceGenerator resourceGenerator;
	// ----------------------------------------------------------------//

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		currentPlayerNumber = 0;
		board = new HexBoard();
                turnCount=1;
                audio.playMusic1();
		sldVictoryPoints.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				for (Player p : players) {
					p.setVictoryPoints((Math.round((new_val.floatValue()))));
					System.out.println(((Math.round((new_val.floatValue())))) + "  " + p.victoryPointGauge.getLength());

				}
			}

		});

		/*
		 * // THIS IS THE SECTON FOR THE ROLL DICE PANE AND FUNCTIONALITY
		 * dicePane.setVisible(true); dicePane.setMouseTransparent(false);
		 * dicePane.getParent().setMouseTransparent(false);
		 */
		/*
		 * leftDie.textProperty().addListener(new ChangeListener(){ // a change
		 * listener, once the value on the dice change it should do a 2 second
		 * sleep and then kill the dice pane
		 * 
		 * @Override public void changed(ObservableValue observable, Object
		 * oldValue, Object newValue) { try{ Thread.sleep((long) 2000.0); }
		 * catch (InterruptedException ex) {
		 * Logger.getLogger(GameScreenController.class.getName()).log(Level.
		 * SEVERE, null, ex); } dicePane.setMouseTransparent(true);
		 * dicePane.getParent().setMouseTransparent(true);
		 * 
		 * dicePane.setVisible(false);
		 * 
		 * } });
		 */

		// ROLL DICE SECTION ENDS
		longestRoadValue = 5;
		largestArmyValue = 3;
		freeRoad = 2;
		freeSettlement = 2;
		resourcePass = 4;
                
                
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
		for (HexVertex hexVertex : board.vertexList) {
			hexVertex.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
				setSelectedItem(hexVertex);
				try {
					if (gameState == PLACING_SETTLEMENT) {
						buildSettlement();
						setGameState(MAIN_PHASE);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			});

		}

		for (HexEdge hexEdge : board.edgeList) {
			hexEdge.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
				setSelectedItem(hexEdge);
				popupDialog.setVisible(false);
				try {
					if (gameState == PLACING_ROAD) {
						buildRoad();
						setGameState(MAIN_PHASE);
					} else {
						printPrettyGameState();
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				if (gameBoard.getRotate() > 0) {
					popupDialog.relocate(Math.abs(hexEdge.getStartPoint().getY() - hexEdge.getEndPoint().getY()),
							Math.abs(hexEdge.getStartPoint().getX() - hexEdge.getEndPoint().getX()));

				} else {
					txtPopUpText.setText("Old: \n" + popupDialog.getLayoutX() + ", " + popupDialog.getLayoutY()
							+ "Current: " + Math.abs(hexEdge.getStartPoint().getX() - hexEdge.getEndPoint().getX())
							+ "," + Math.abs(hexEdge.getStartPoint().getY() - hexEdge.getEndPoint().getY()));
					popupDialog.relocate(Math.abs(hexEdge.getStartPoint().getX() - hexEdge.getEndPoint().getX()),
							Math.abs(hexEdge.getStartPoint().getY() - hexEdge.getEndPoint().getY()));
				}
			});

		}

		gameBoard.getChildren().add(board.getBoardPane());

		// Player GUI Stuff
		resGen = new ResourceGenerator(board);
		makeResources();
		createTestPlayers();
		fillPlayerInfo();
		cancelBuildBtn.setVisible(false);

		setGameState(NEW_GAME);
	}

	public void setSelectedItem(Node o) {
		selectedItem = o;
	}

	public int rollDice() {
	        audio.playClips(1);
	
            btnRollDice.setDisable(true);
		Integer r;
		Random z = new Random();
		r = z.nextInt(6) + 1;
		diceValue = r;
		System.out.println("first die: " + r);
		leftDie.setText(r.toString());

		r = z.nextInt(6) + 1;
		diceValue += r;
		rightDie.setText(r.toString());
		System.out.println("total die: " + diceValue);
		txtLastRoll.setText("Last Dice Roll: " + diceValue);
		if (diceValue == 7) {
			System.out.println("ROBBER!!!");
		}
		if (gameState == PRE_ROLL) {
			resGen.generateResources(diceValue);
			setGameState(MAIN_PHASE);
		}

		return diceValue;

	}

	public ArrayList<Player> getPlayerOrder(ArrayList<Player> players) {
		Collections.shuffle(players);
		for (int i = 0; i < players.size(); i++) {
			System.out.println(i + ": " + players.get(i).getNickname());
		}
		return players;
	}

	public void doInitialBuildPhase() {
		for (int i = 0; i < players.size(); i++) {
			players.get(i).buildInitial();
		}
		for (int i = players.size() - 1; i >= 0; i--) {
			players.get(i).buildInitial();
		}
	}

	public void buildPhase() {

	}

	public void startGame() {
		getPlayerOrder(players);
		setGameState(PRE_ROLL);
		currentPlayer = players.get(0);
		System.out.println(currentPlayer.toString());
		fillPlayerInfo();
		System.out.println("//////////////////////////////////");
		System.out.println("GAME STARTED");
		System.out.println("//////////////////////////////////");

	}

	public void endTurn() {
		++currentPlayerNumber;
		if (currentPlayerNumber == players.size()) {
			currentPlayerNumber = 0;
		}
		fillPlayerInfo();
		newTurn();
	}

	public void newTurn() {
		System.out.println("/////////////////////////////////////");
		currentPlayer = players.get(currentPlayerNumber);
		System.out.println("Current Player: " + players.get(currentPlayerNumber).toString());
		setGameState(PRE_ROLL);
		// if (currentPlayer.getNickname() != "Mark") {
		// System.out.println("Skipping cpu turn...");
		// rollDice();
		// endTurn();
		// }
	}

	public void setUIFromGameState() {
		switch (gameState) {
		case NEW_GAME:
			tradeBtn.setDisable(true);
			diceRoller.setDisable(true);
			startGameBtn.setDisable(false);
			devBtn.setDisable(true);
			gameBoard.setDisable(true);
			buildBtn.setDisable(true);
			endBtn.setDisable(true);
			break;
		case PRE_ROLL:
			tradeBtn.setDisable(true);
			diceRoller.setDisable(false);
			startGameBtn.setDisable(true);
			devBtn.setDisable(false);
			gameBoard.setDisable(true);
			buildBtn.setDisable(true);
			endBtn.setDisable(true);
			break;
		case MAIN_PHASE:
			tradeBtn.setDisable(false);
			diceRoller.setDisable(true);
			startGameBtn.setDisable(true);
			devBtn.setDisable(false);
			gameBoard.setDisable(true);
			buildBtn.setVisible(true);
			buildBtn.setDisable(false);
			cancelBuildBtn.setVisible(false);
			endBtn.setDisable(false);
			break;
		case PLACING_ROAD:
		case PLACING_CITY:
		case PLACING_SETTLEMENT:
			tradeBtn.setDisable(true);
			diceRoller.setDisable(true);
			startGameBtn.setDisable(true);
			devBtn.setDisable(true);
			gameBoard.setDisable(false);
			buildBtn.setVisible(false);
			cancelBuildBtn.setVisible(true);
			endBtn.setDisable(true);
		}
	}

	public boolean getWinCondition() {
		for (Player player : players) {
			if (player.getVictoryPoints() >= 10) {
				System.out.println("GAME OVER");

				return true;
			}
		}

		return false;
	}

	// End(immediately-related) Game Loop stuff

	public void closeParentPane() throws IOException {
		// popupDialog.getParent().setMouseTransparent(true);
		// board.popUpDialog.setVisible(false);

		popupDialog.setMouseTransparent(true);
		popupDialog.setVisible(false);

	}

	public void handleClick(MouseEvent e) throws IOException {

		System.out.println("Clicked!!!" + e + "\n" + e.getSource().getClass() + "\n"

		);
		/*
		 * if(popupDialog.isVisible() ){ try{ ((Button)
		 * (e.getSource())).toString(); }catch(Exception ex){
		 * 
		 * }
		 * 
		 * popupDialog.setVisible(false);
		 * popupDialog.getParent().setMouseTransparent(true);
		 * System.out.println("BOOM"); }
		 */
	}

	public void handleButtonAction(ActionEvent event) throws IOException {

	}

	public void handleVertexButtonAction(ActionEvent event) throws IOException {
		String m = ((RadioButton) event.getSource()).getText();
		System.out.println("This is a Vertex " + m);

		// label.setText("Hello World!");

	}

	public void handleEdgeButtonAction(ActionEvent event) throws IOException {
		String m = ((RadioButton) event.getSource()).getText();
		System.out.println("This is an Edge:  " + m);

	}

	// Buid Family of Functions:

	public void buildSettlement() throws IOException {
		if (canBuildSettlement((HexVertex) selectedItem)) {
			// ((HexVertex)selectedItem).addSettlement(thisPlayer);
			if (freeSettlement < 0) { // quick bug fix to see if freeSettlment
										// can work. must change where it
										// decrements.
				resourceBank.bankReturnResource(
						resourceBank.getResourceCost(
								((HexVertex) selectedItem).addSettlement(players.get(currentPlayerNumber))),
						players.get(currentPlayerNumber));
			} else {
				((HexVertex) selectedItem).addSettlement(players.get(currentPlayerNumber));
				System.out.println("Current color: " + players.get(currentPlayerNumber).getPlayerColor());
				System.out.println("For current player: " + players.get(currentPlayerNumber) + " and player number: "
						+ currentPlayerNumber);
			}
			System.out.println("BUILD Settlement DIALOG");
			thisPlayer.assets.add(thisPlayer, ((HexVertex) selectedItem));
			thisPlayer.setVictoryPoints((thisPlayer.getVictoryPoints()) + SETTLMENT_VP_VALUE);
			audio.playClips(0);

                        // pgbVictoryPoints.setProgress(((double)thisPlayer.getVictoryPoints()/10));
			updateResources();
			// System.out.println("ADJACENT EDGES:");
			// System.out.println(((HexVertex) selectedItem).getAdjacentEdge());
			for (HexEdge edge : ((HexVertex) selectedItem).getAdjacentEdge()) {
				// edge.setStroke(Color.WHITE);
				if (((HexEdge) edge).isOwned()) {
					edge.setFill(players.get(currentPlayerNumber).getPlayerColor());

				}
			}
			closeParentPane();

		}
	}
        public void getSelectedItemInfo(){
                   System.out.println((HexEdge)selectedItem);
                   System.out.println((HexVertex)selectedItem);
           //        offerTrade(new tp(thisPlayer));

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
      return result;
    }

	private boolean canBuildCity(HexVertex hv) {
		return true;
	}

	private boolean checkDistanceRuleSettlement(HexVertex selectedItem) {
		boolean result = false;

		for (HexEdge e : selectedItem.getAdjacentEdge()) {
			if ((((HexVertex) (e.getOtherPoint(selectedItem))).getAsset()) != null) {
				result = true;
			} else {
				return false;
			}

		}
		return result;
	}

	public void buildRoad() throws IOException{
        if(canBuildRoad(((HexEdge)selectedItem), thisPlayer)){
            if(freeRoad>0){
               ((HexEdge)selectedItem).addRoad(thisPlayer);
               freeRoad--;
            }else{
               resourceBank.bankReturnResource(thisPlayer.removeResources(resourceBank.getResourceCost(((HexEdge)selectedItem).addRoad(thisPlayer))));
            }
           thisPlayer.assets.add(thisPlayer,((HexEdge)selectedItem));
           ((HexEdge) selectedItem).setStroke((Paint)thisPlayer.getPlayerColor());                                //set fill to color the vertex the player's color (indicating a settlement
            audio.playClips(0);


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

	private boolean checkRequirements(HexEdge hexEdge, Player player) { // checks
																		// requirements
																		// for
																		// road
		// road requirements: BRICK AND LUMBER (plastic + glass) 1+4

		return (isGreater(players.get(currentPlayerNumber).getResources(), new int[] { 0, 1, 0, 0, 1 }));

	}

	private boolean checkRequirements(HexVertex hexVertex, Player player) { // checks
																			// requirements
																			// for
																			// settlement
		// road requirements: BRICK AND LUMBER (plastic + glass) 1+4

		return (isGreater(players.get(currentPlayerNumber).getResources(), new int[] { 0, 1, 1, 1, 1 }));

	}


	private boolean checkRequirements(HexVertex hexVertex, Player player, Settlement settlement) { // checks
																									// requirements
																									// for
																									// settlement

		return (isGreater(players.get(currentPlayerNumber).getResources(), new int[] { 3, 0, 0, 2, 0 }));

	}

	private boolean checkRequirements(Player player) { // checks requirements
														// for developmentCards

		return (isGreater(players.get(currentPlayerNumber).getResources(), new int[] { 1, 0, 1, 1, 0 }));

	}

	private boolean isAdjacentEdgeOwned(HexEdge hexEdge, Player player) {
		for (HexEdge edge : hexEdge.getAdjacentEdge()) {
			if (edge.isOwned()) {

			}
		}

		return false;
	}

	private boolean isGreater(int[] a, int[] b) { // checks if the contents of
													// int[]a are greater than
													// int[] b
		for (int i = 0; i < a.length; i++) {
			if (a[i] >= b[i]) {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}

	public void checkForLongestRoad() {
		if (players.get(currentPlayerNumber).assets.roads.size() > longestRoadValue)
			longestRoadValue = players.get(currentPlayerNumber).assets.roads.size();

		pgbLongestRoad.progressProperty()
				.set((double) (players.get(currentPlayerNumber).assets.roads.size()) / longestRoadValue);
		if (pgbLongestRoad.progressProperty().doubleValue() >= 100.0) {
			pgbLongestRoad.getStyleClass().add("gold-bar");
		} else {
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
        Player mark = thisPlayer;
        Player dek = new Player("Dehkoda", new int[]{0,0,0,0,0}, Color.RED);
        Player lisa = new Player("Lisa", new int[]{0,0,0,0,0}, Color.BLUE);
        Player mew = new Player("Mew", new int[]{0,0,0,0,0}, Color.VIOLET);
        players.add(mark);
        players.add(dek);
        players.add(lisa);
        players.add(mew);

        System.out.println(getPlayerNum());

        thisPlayer=mark;


        
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
                ((Circle)((AnchorPane)n).getChildren().get(0)).setFill((Paint)board.getColorPalette()[trackers.indexOf(node)]); 
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

	// Trade Functionality
	public boolean submitTradeOffer() {
		TradePack tp = createTradePackage();
		boolean response;
		// check for receiver
		if (tp.getReceiver() == null) {
			// offer to each player
			for (Player p : players) {
				if (p != players.get(currentPlayerNumber)) {
					tp.setReceiver(p);
					if (offerTrade(tp)) {
						((ImageView) (tradeResponses.getChildren().get(players.indexOf(p) + 1)))
								.setImage(new Image(""));
					} else {
						((ImageView) (tradeResponses.getChildren().get(players.indexOf(p) + 1)))
								.setImage(new Image(""));

					}
				}
			}
		} else {
			return offerTrade(tp);
		}

		System.out.println(tp);
		return false;

	}

	public void closeTradeDialog() {
		pnTradeDialog.setVisible(false);
		pnTradeDialog.getParent().setMouseTransparent(true);
		pnTradeDialog.setMouseTransparent(true);
		// clear tradepack
		thisPlayerTradePack = null;
	}

	public TradePack createTradePackage() {
		Player creator, receiver;
		int[] resourcesOffered = new int[5];
		int[] resourcesRequested = new int[5];

		Boolean accept;

		Boolean tradeSingle = false;
		Boolean tradeBank = false;
		TradePack tp;

		ObservableList<Node> trackers;
		trackers = tradeResourceTrackers.getChildren();

		for (Node node : trackers) {
			resourcesRequested[trackers.indexOf(node)] = ((TradeResourceTracker) node).getRequestedAmount();
			resourcesOffered[trackers.indexOf(node)] = ((TradeResourceTracker) node).getGivingAmount();

		}
		return new TradePack(players.get(currentPlayerNumber), players.get(3), resourcesOffered, resourcesRequested);
	}

	@FXML
	private void simulateTradeRequest() {
		offerTrade(new TradePack(players.get(2), players.get(currentPlayerNumber), new int[] { 0, 2, 2, 0, 1 },
				new int[] { 1, 0, 0, 2, 0 }));
	}

	// offer trade should fire an event with whichever player is being offered a
	// trade
	// need to find a way to get a response from players
	private boolean offerTrade(TradePack tp) {
		boolean response = false;
		if (tp.getReceiver() == players.get(currentPlayerNumber)) {
			// open trade requested dialog
			players.get(currentPlayerNumber).setTradePack(tp);
			pnAcceptTradeDialog.setVisible(true);
			pnAcceptTradeDialog.getParent().setMouseTransparent(false);

			((Label) (vbTradeContents.getChildren().get(0))).setText(tp.getSender().getNickname());
			for (Node n : hbIncomingResources.getChildren()) {
				((Circle) ((StackPane) n).getChildren().get(0)).setFill(
						(board.getColorPalette())[((ObservableList) (hbIncomingResources.getChildren())).indexOf(n)]);
				((Label) ((StackPane) n).getChildren().get(1)).setText(""
						+ tp.getResourcesOffered()[((ObservableList) (hbIncomingResources.getChildren())).indexOf(n)]);
			}

			for (Node n : hbOutgoingResources.getChildren()) {
				((Circle) ((StackPane) n).getChildren().get(0)).setFill(
						(board.getColorPalette())[((ObservableList) (hbOutgoingResources.getChildren())).indexOf(n)]);
				((Label) ((StackPane) n).getChildren().get(1)).setText("" + tp
						.getResourcesRequested()[((ObservableList) (hbOutgoingResources.getChildren())).indexOf(n)]);
			}

			return response;
		} else {
			return generateTradeResponse();
		}

	}

	@FXML
	public void tradeDialogYes() {
		acceptTrade(players.get(currentPlayerNumber).getTradePack());
		pnAcceptTradeDialog.setVisible(false);
		pnAcceptTradeDialog.getParent().setMouseTransparent(true);
	}

	public void tradeDialogNo() {
		// acceptTrade(players.get(currentPlayerNumber).getTradePack());
		pnAcceptTradeDialog.setVisible(false);
		pnAcceptTradeDialog.getParent().setMouseTransparent(true);
	}

	private void acceptTrade(TradePack tp) {
		// System.out.println("this player is:
		// "+tp.getReceiver().getNickname());

		System.out.println(tp);
		if (tp.getReceiver() == players.get(currentPlayerNumber)) {
			/*
			 * for (int
			 * i=0;i<players.get(currentPlayerNumber).resources.length;i++){
			 * players.get(currentPlayerNumber).resources[i]+=tp.
			 * resourcesOffered[i];
			 * players.get(currentPlayerNumber).resources[i]-=tp.
			 * resourcesRequested[i]; }
			 */
			players.get(currentPlayerNumber).setResources(
					addTwoResourceSets(players.get(currentPlayerNumber).getResources(), tp.getResourcesOffered()));
			players.get(currentPlayerNumber).setResources(subtractTwoResourceSets(
					players.get(currentPlayerNumber).getResources(), tp.getResourcesRequested()));
			updateResources();

			if (tp.getSender() == players.get(currentPlayerNumber)) {
				for (int i = 0; i < players.get(currentPlayerNumber).resources.length; i++) {
					/*
					 * players.get(currentPlayerNumber).resources[i]-=tp.
					 * resourcesOffered[i];
					 * players.get(currentPlayerNumber).resources[i]+=tp.
					 * resourcesRequested[i];
					 */
					players.get(currentPlayerNumber).setResources(addTwoResourceSets(
							players.get(currentPlayerNumber).getResources(), tp.getResourcesRequested()));
					players.get(currentPlayerNumber).setResources(subtractTwoResourceSets(
							players.get(currentPlayerNumber).getResources(), tp.getResourcesOffered()));
					updateResources();

				}

			}

			System.out.println(players.get(currentPlayerNumber).getResources());

			// open trade requested dialog
		} else {
		}

	}

	public void openDevelopDialog() {
		System.out.println("Develop Card Play!");
	}

	public void openBuildDialog() {
		if (!popupDialog.isVisible()) {
			popupDialog.setVisible(true);
		}
		popupDialog.setMouseTransparent(false);
		for (Node n : popupDialog.getChildren()) {
			n.setMouseTransparent(false);
		}
	}

	public void buildDevCard() {
		System.out.println("Buy/Build dev card stuff here");
	}

	private int[] addTwoResourceSets(int[] a, int[] b) {
		int[] result = new int[a.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = a[i] + b[i];
		}

		return result;
	}

	private int[] subtractTwoResourceSets(int[] a, int[] b) {
		int[] result = new int[a.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = a[i] - b[i];
		}
		return result;
	}

	void updateResources() {
		txtRes00Text.setText("" + (players.get(currentPlayerNumber).getResources())[0]);
		txtRes01Text.setText("" + (players.get(currentPlayerNumber).getResources())[1]);
		txtRes02Text.setText("" + (players.get(currentPlayerNumber).getResources())[2]);
		txtRes03Text.setText("" + (players.get(currentPlayerNumber).getResources())[3]);
		txtRes04Text.setText("" + (players.get(currentPlayerNumber).getResources())[4]);

	}

	private void declineTrade(TradePack tp) {
		boolean response = false;
		if (tp.getReceiver() == players.get(currentPlayerNumber)) {
			// open trade requested dialog
		} else {
		}

	}

	// generate random response
	public boolean generateTradeResponse() {
		return new Random().nextBoolean();
	}

	public void offerResource(MouseEvent e) {
		if (pnTradeDialog.isVisible()) {
			// players.get(currentPlayerNumber)TradePack.resourcesOffered[0]+=1;
		}

	}

	public void requestResource(Resource rec, TradePack tp) {

	}

	// board setup
	public void makeResources() {
		txtRes00Text.setText("" + thisPlayer.resources[0]);
		txtRes01Text.setText("" + thisPlayer.resources[1]);
		txtRes02Text.setText("" + thisPlayer.resources[2]);
		txtRes03Text.setText("" + thisPlayer.resources[3]);
		txtRes04Text.setText("" + thisPlayer.resources[4]);

	}

	public void fillPlayerInfo() {
		// get player icon blocks
		ArrayList<Pane> playerBlocks = new ArrayList<>();
		playerBlocks.add(pnPlayerLeft);
		playerBlocks.add(pnPlayerMid);
		playerBlocks.add(pnPlayerRight);
		int counter = 0;
		for (Player p : players) {
			if (p.nickname != players.get(currentPlayerNumber).getNickname()) {
				p.pnPlayerInfo = playerBlocks.get(counter);
				((Label) playerBlocks.get(counter).getChildren().get(6)).setText(p.nickname);
				p.victoryPointGauge = (Arc) playerBlocks.get(counter).getChildren().get(1);
				System.out.println(p.victoryPointGauge);
				counter++;
			} else {
				if (gameState != NEW_GAME) {
					txtThisPlayerName.setText(p.nickname);
				} else {
					txtThisPlayerName.setText("New Game");
				}
			}
		}
		if (gameState != NEW_GAME)

		{
			playerInfoPane
					.setBackground(new Background(new BackgroundFill(players.get(currentPlayerNumber).getPlayerColor(),
							CornerRadii.EMPTY, Insets.EMPTY)));
		}

	}

	private boolean winCondition() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public static Player getPlayerWithLargestArmy() {
		return playerWithLargestArmy;
	}

	public void setPlayerWithLargestArmy(Player player) {
		this.playerWithLargestArmy = player;
	}

	public static Player getPlayerWithLongestRoad() {
		return playerWithLongestRoad;
	}

	public void setPlayerWithLongestRoad(Player player) {
		this.playerWithLongestRoad = player;
	}

	// Temporary Helper function to help determine current game state; Should
	// delete when no longer needed
	public String gameStateToString() {
		switch (gameState) {
		case NEW_GAME:
			return "New Game";
		case DETERMINE_PLAYER_ORDER:
			return "Determining Player Order";
		case INIT_BUILD_PHASE_A:
			return "Initial Build Phase Part A";
		case INIT_BUILD_PHASE_B:
			return "Initial Build Phase Part B";
		case PRE_ROLL:
			return "Pre-roll Phase";
		case MAIN_PHASE:
			return "Main Phase";
		case PLACING_ROAD:
		case PLACING_CITY:
		case PLACING_SETTLEMENT:
			return "Placing asset piece";
		case MOVING_ROBBER:
			return "Moving Robber";
		case PLAY_ROAD_BUILDING:
		case PLAY_MONOPOLY:
		case PLAY_KNIGHT:
		case PLAY_VICTORY_POINT:
			return "Development Card Played";
		default:
			return "Game State unknown";
		}
	}

	public void setGameState(int gs) {
		this.gameState = gs;
		setUIFromGameState();
	}

	public int getGameState() {
		return gameState;
	}

	public void placingSettlement() {
		setGameState(PLACING_SETTLEMENT);
		popupDialog.setVisible(false);
	}

	public void placingRoad() {
		setGameState(PLACING_ROAD);
		popupDialog.setVisible(false);
	}

	public void placingCity() {
		setGameState(PLACING_CITY);
		popupDialog.setVisible(false);
	}

	public void cancelBuild() {
		setGameState(MAIN_PHASE);
		popupDialog.setVisible(false);
	}

	public void printPrettyGameState() {
		System.out.println("Game State ==> " + gameStateToString());
		txtCurrentStatus.setText("Game State: " + gameStateToString());
	}
        private boolean canBuyDev(Player player){
        System.out.println(Arrays.toString(thisPlayer.getResources()));
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
        public void buildDev(){
            
        }
   

    private int getPlayerNum(){
        try{
            return (int) SoM.client.read();
        }
        catch (Exception e){
            return -1;
        }
    }
}

   
