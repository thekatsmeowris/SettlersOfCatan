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
import progressCards.Monopoly;
import progressCards.YearOfPlenty;
import som.assets.Settlement;
import progressCards.RoadBuilding;
import devCards.Knight;
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

/**
 * FXML Controller class
 *
 * @author makogenq
 */
public class GameScreenController implements Initializable {

	@FXML
	Pane gameLayer, gameBoard, playerGUI, popupDialog, dicePane, pnTradeDialog, robberConfirmDialog, pnPlayerLeft,
			pnPlayerMid, pnPlayerRight, pnAcceptTradeDialog, pnBuild, pnDevelopDialog;

	@FXML
	Slider sldVictoryPoints;

	@FXML
	Label txtCurrentStatus, txtLastRoll;

	@FXML
	Text txtPopUpText, leftDie, rightDie, txtRes00Text, txtRes01Text, txtRes02Text, txtRes03Text, txtRes04Text;

	@FXML
	AnchorPane lastAnchorPane, playerInfoPane;

	@FXML
	Button diceRoller, btnRollDice, startGameBtn, tradeBtn, buildBtn, cancelBuildBtn, gameStateBtn, endBtn, devBtn;

	@FXML
	Text txtThisPlayerName;

	@FXML
	ProgressBar pgbLongestRoad, pgbVictoryPoints;

	@FXML
	private HBox tradeResourceTrackers, tradeResponses, hbIncomingResources, hbOutgoingResources;
	@FXML
	private VBox vbTradeContents;

	Node selectedItem;
	int diceValue;
	int longestRoadValue;
	int largestArmyValue;
	int resourcePass;
	final int SETTLMENT_VP_VALUE = 1;
	ResourceBank resourceBank = new ResourceBank(19);
	ResourceGenerator resGen;

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
	public final static int CONFIRMING_ROBBER = 14;

	public final static int PLAY_ROAD_BUILDING = 15;
	public final static int PLAY_MONOPOLY = 16;
	public final static int PLAY_KNIGHT = 17;
	public final static int PLAY_VICTORY_POINT = 18;

	public final static int PLACING_FREE_SETTLEMENT = 20;
	public final static int PLACING_FREE_ROAD = 21;

	private static int currentPlayerNumber;
	private boolean advanceBackwards;
	/*
	 * public final static int DISTR_RESOURCES = 20; public final static int
	 * DISCARDING = 21; public final static int STEALING_RESOURCE = 22; public
	 * final static int
	 * 
	 */

	int freeRoad; // number of free roads the player can place
	// int resourcePass; //these skip checking resource requirements.
	// Circle selectedCircle=new HexVertex();

	/**
	 * Initializes the controller class.
	 */

	// -----------------------------------------------------//

	TradePack thisPlayerTradePack;
	ArrayList<Player> players;
	DevelopmentDeck developmentDeck = new DevelopmentDeck();
	DevelopmentCard thisCard;
	HexBoard board;
	int turnCount;
	ResourceGenerator resourceGenerator;
	Robber rob;
	// ----------------------------------------------------------------//

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		currentPlayerNumber = 0;
		board = new HexBoard();
		rob = new Robber();
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
		resourcePass = 4;

		for (Hex h : board.transHexList) {
			h.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
				if (getGameState() == MOVING_ROBBER) {
					setSelectedItem(board.hexList.get(h.getIndex()));
					h.setFill(Color.rgb(25, 25, 200, 0.5));
					robberConfirmDialog.setVisible(true);
					robberConfirmDialog.toFront();
					setGameState(CONFIRMING_ROBBER);
				}
			});
		}
		for (Hex hex : board.hexList) {
			hex.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
				setSelectedItem(hex);
				hex.setFill(Color.YELLOW);
				System.out.println(
				// hex.getTokenValue()+"\n"
				// +hex.getVerticies()+"\n+---------+\n"
				);
				int vertexCounter = 0;
				// System.out.println("[\t\t\t\t\t"+hexCounter+"\t\t\t\t\t]");
				for (HexVertex vertex : hex.getVerticies()) {
					System.out.print(vertexCounter + "\t\t");
					System.out.print("BEFORE ASSN: \t" + vertex + "\n");
					System.out.println("it's in the list on at index: " + board.vertexList.indexOf(vertex));
					System.out.println("BEFORE ASSN: "
							+ ((boolean) (vertex == board.vertexList.get(board.vertexList.indexOf(vertex)))));
					vertex = board.vertexList.get(board.vertexList.indexOf(vertex));
					System.out.println("AFTER ASSN: "
							+ ((boolean) (vertex == board.vertexList.get(board.vertexList.indexOf(vertex)))));
					System.out.print("AFTER ASSN: \t" + vertex + "\n\n\n");

					vertexCounter++;
				}

			});
		}
		for (HexVertex hexVertex : board.vertexList) {
			hexVertex.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
				setSelectedItem(hexVertex);
				try {
					if (gameState == PLACING_SETTLEMENT || gameState == PLACING_FREE_SETTLEMENT) {
						buildSettlement();
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
					if (gameState == PLACING_FREE_ROAD) {
						buildRoad();
						setGameState(PLACING_FREE_SETTLEMENT);
						if ((currentPlayerNumber == players.size() - 1)
								&& (players.get(players.size() - 1).getFreeRoads() > 0)) {
							setAdvanceBackwards(true);
						} else {
							System.out.println(gameStateToString());
							endTurn();
						}

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
		createTestPlayers();
		fillPlayerInfo();
		// TODO: reorganize
		rob.setPlayerArray(players);
		rob.setBank(resourceBank);
		cancelBuildBtn.setVisible(false);

		setGameState(NEW_GAME);
	}

	public void setSelectedItem(Node o) {
		selectedItem = o;
	}

	public int rollDice() {
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
			setGameState(MOVING_ROBBER);
		}
		if (gameState == PRE_ROLL) {
			resGen.generateResources(diceValue);
			setGameState(MAIN_PHASE);
		}

		updateResources();
		return diceValue;

	}

	public ArrayList<Player> getPlayerOrder(ArrayList<Player> players) {
		Collections.shuffle(players);
		for (int i = 0; i < players.size(); i++) {
			System.out.println(i + ": " + players.get(i).getNickname());
		}
		return players;
	}

	public void startGame() {
		getPlayerOrder(players);

		// setGameState(PRE_ROLL);
		setGameState(INIT_BUILD_PHASE_A);

		currentPlayer = players.get(0);
		makeResources();
		System.out.println(currentPlayer.toString());
		fillPlayerInfo();
		System.out.println("//////////////////////////////////");
		System.out.println("GAME STARTED");
		System.out.println("//////////////////////////////////");
		System.out.println(gameStateToString());
		doInitBuildPhase();

	}

	public void doInitBuildPhase() {
		int prevGS = getGameState();
		setGameState(PLACING_FREE_SETTLEMENT);
	}

	public void endTurn() {
		System.out.println(gameStateToString());
		if (advanceBackwards) {
			if (currentPlayerNumber == 0) {
				setGameState(PRE_ROLL);
				setAdvanceBackwards(false);
			} else {
				currentPlayerNumber--;
			}
		} else {
			++currentPlayerNumber;
			if (currentPlayerNumber == players.size()) {
				currentPlayerNumber = 0;
			}
			if (popupDialog.isVisible()) {
				popupDialog.setVisible(false);
			}
		}
		if (getGameState() != PLACING_FREE_SETTLEMENT) {
			updateResources();
		}
		fillPlayerInfo();
		newTurn();
	}

	public void endTurnBackwards() {

	}

	public void newTurn() {
		System.out.println(gameStateToString());
		System.out.println("/////////////////////////////////////");
		currentPlayer = players.get(currentPlayerNumber);
		System.out.println("Current Player: " + players.get(currentPlayerNumber).getNickname());
		if (getGameState() != PLACING_FREE_SETTLEMENT) {
			setGameState(PRE_ROLL);
		}
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
		case INIT_BUILD_PHASE_A:
		case INIT_BUILD_PHASE_B:
			tradeBtn.setDisable(true);
			diceRoller.setDisable(true);
			startGameBtn.setDisable(true);
			devBtn.setDisable(true);
			gameBoard.setDisable(false);
			buildBtn.setDisable(true);
			cancelBuildBtn.setVisible(false);
			cancelBuildBtn.setDisable(true);
			endBtn.setDisable(true);
			break;
		case PRE_ROLL:
			tradeBtn.setDisable(true);
			diceRoller.setDisable(false);
			startGameBtn.setDisable(true);
			devBtn.setDisable(false);
			gameBoard.setDisable(true);
			buildBtn.setDisable(true);
			cancelBuildBtn.setVisible(false);
			cancelBuildBtn.setDisable(true);
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
			cancelBuildBtn.setDisable(true);
			endBtn.setDisable(false);
			break;
		case MOVING_ROBBER:
			tradeBtn.setDisable(true);
			diceRoller.setDisable(true);
			startGameBtn.setDisable(true);
			devBtn.setDisable(true);
			gameBoard.setDisable(false);
			buildBtn.setVisible(true);
			buildBtn.setDisable(true);
			cancelBuildBtn.setVisible(false);
			endBtn.setDisable(true);
			break;
		case CONFIRMING_ROBBER:
			tradeBtn.setDisable(true);
			diceRoller.setDisable(true);
			startGameBtn.setDisable(true);
			devBtn.setDisable(true);
			gameBoard.setDisable(true);
			buildBtn.setVisible(true);
			buildBtn.setDisable(true);
			cancelBuildBtn.setVisible(false);
			endBtn.setDisable(true);
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
			cancelBuildBtn.setDisable(false);
			endBtn.setDisable(true);
			break;
		case PLACING_FREE_ROAD:
		case PLACING_FREE_SETTLEMENT:
			tradeBtn.setDisable(true);
			diceRoller.setDisable(true);
			startGameBtn.setDisable(true);
			devBtn.setDisable(true);
			gameBoard.setDisable(false);
			buildBtn.setVisible(true);
			buildBtn.setDisable(true);
			cancelBuildBtn.setVisible(false);
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

	public void closeDevCardsPane() throws IOException {
		pnDevelopDialog.setMouseTransparent(true);
		pnDevelopDialog.setVisible(false);
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

	public void moveRobber(Robber rob) {
		if (canMoveRobber((Hex) selectedItem, players.get(currentPlayerNumber))) {
			((Hex) selectedItem).setSandstorming(true);
			clearPreviousRobberHex(false);
			rob.setCurrentHex((Hex) selectedItem);
			rob.ActivateRobber(players.get(currentPlayerNumber), board);
			updateResources();
			setGameState(MAIN_PHASE);
		}
	}

	private boolean canMoveRobber(Hex h, Player player) {

		if (!h.isSandstorming()) {
			return true;
		}

		return false;
	}

	public void buildSettlement() throws IOException {

		if (canBuildSettlement((HexVertex) selectedItem, players.get(currentPlayerNumber))) {
			System.out.println("You can build a settlement! Wheeee!");
			if (players.get(currentPlayerNumber).getFreeSettlements() > 0) {
				players.get(currentPlayerNumber)
						.setFreeSettlements(players.get(currentPlayerNumber).getFreeSettlements() - 1);
				System.out.println("Free Settlements: " + players.get(currentPlayerNumber).getFreeSettlements());
				setGameState(PLACING_FREE_ROAD);
			} else {

				resourceBank.bankReturnResource(players.get(currentPlayerNumber).removeResources(resourceBank
						.getResourceCost(((HexVertex) selectedItem).addSettlement(players.get(currentPlayerNumber)))));
				System.out.println("Current color: " + players.get(currentPlayerNumber).getPlayerColor());
				System.out.println("Free Settlement greater than 0: fs = "
						+ players.get(currentPlayerNumber).getFreeSettlements());
				setGameState(MAIN_PHASE);
			}

			((HexVertex) selectedItem).addSettlement(players.get(currentPlayerNumber));
			players.get(currentPlayerNumber).assets.add(players.get(currentPlayerNumber), ((HexVertex) selectedItem));
			players.get(currentPlayerNumber)
					.setVictoryPoints((players.get(currentPlayerNumber).getVictoryPoints()) + SETTLMENT_VP_VALUE);

			updateResources();

			for (HexEdge edge : ((HexVertex) selectedItem).getAdjacentEdge()) {
				// edge.setStroke(Color.WHITE);
				if (((HexEdge) edge).isOwned()) {
					edge.setFill(players.get(currentPlayerNumber).getPlayerColor());

				}
			}
		} else {
			System.out.println("You can't build a settlement. awERAIWEJFOI");
		}
		closeParentPane();

	}

	public void getSelectedItemInfo() {
		System.out.println((HexEdge) selectedItem);
		System.out.println((HexVertex) selectedItem);
		// offerTrade(new tp(thisPlayer));

	}

	public boolean canBuildSettlement(HexVertex hv, Player player) {
		if (checkRequirements(hv, player) && !((HexVertex) hv).isOwned()) {
			boolean result = true;

			for (HexEdge edge : hv.getAdjacentEdge()) {
				result = ((HexVertex) (edge.getOtherPoint(((HexVertex) hv)))).getAsset() == null;

				if (!result) {
					return result;
				} // if any of the otherVertex's contain an asset return false
					// and
					// player cannot build here

			}

			// If player has no free settlements (i.e. is not
			// INIT_BUILD_PHASE_A/B, then check road adjacency
			if (player.getFreeSettlements() <= 0) {
				System.out.println("Missing da free settlements");
				// Check road adjacency
				for (HexEdge edge : hv.getAdjacentEdge()) {
					if (edge.isOwned()) {
						return true;
					} else {
						System.out.println("Edge not owned??????");
						result = false;
						continue;
					}
				}
			} else {
				return result;
			}
		}

		System.out.println("Requirements not met for buildSettlement");
		System.out.println("Free Settlements: " + players.get(currentPlayerNumber).getFreeSettlements());
		return false;

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

	public void buildRoad() throws IOException {
		if (canBuildRoad(((HexEdge) selectedItem), players.get(currentPlayerNumber))) {
			if (players.get(currentPlayerNumber).getFreeRoads() > 0) {
				((HexEdge) selectedItem).addRoad(players.get(currentPlayerNumber));
				players.get(currentPlayerNumber).setFreeRoads(players.get(currentPlayerNumber).getFreeRoads() - 1);
				System.out.println("Free Roads: " + players.get(currentPlayerNumber).getFreeRoads());
			} else {
				resourceBank.bankReturnResource(players.get(currentPlayerNumber).removeResources(resourceBank
						.getResourceCost(((HexEdge) selectedItem).addRoad(players.get(currentPlayerNumber)))));
			}
			players.get(currentPlayerNumber).assets.add(players.get(currentPlayerNumber), ((HexEdge) selectedItem));
			((HexEdge) selectedItem).setStroke((Paint) players.get(currentPlayerNumber).getPlayerColor());

			updateResources();

			checkForLongestRoad();

			// System.out.println(((HexEdge)
			// selectedItem).getType().toString());
			for (HexEdge edge : ((HexEdge) selectedItem).getAdjacentEdge()) {
				// edge.setStroke(Color.WHITE);
				System.out.println("Edge owned?: " + edge.isOwned());
			}
			closeParentPane();
		}
	}

	private boolean canBuildRoad(HexEdge hexEdge, Player player) {

		if (checkRequirements(hexEdge, player) && !((HexEdge) selectedItem).isOwned()) {
			// check all the adjacent edges to see if at leaset one of them has
			// a road
			for (HexEdge edge : hexEdge.getAdjacentEdge()) {

				if (edge.isOwned()) {
					if (edge.getOwner().equals(players.get(currentPlayerNumber))) {
						return true;
					}
				}
			}
			// Check if startpoint or endpoint has an asset on it's vertex
			try {
				if (hexEdge.getStartVertex().getAsset() != null) {

					if (hexEdge.getStartVertex().getAsset().getPlayer().equals(players.get(currentPlayerNumber))) {
						return true;
					}
				}
			} catch (NullPointerException nullPointer) {
				System.out.println("FAIL ON START");
			}

			try {
				System.out.println("This vertex was not null");
				if (hexEdge.getEndVertex().getAsset().getPlayer().equals(players.get(currentPlayerNumber))) {
					return true;
				}

				// return true;
			} catch (NullPointerException nullPointer) {
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

	public void buildCity() {
		System.out.println("BUILD CITY DIALOG");
		if (canBuildCity((HexVertex) selectedItem)) {
			System.out.println("BUILD SETTLEMENT DIALOG");
			resourceBank.bankReturnResource(players.get(currentPlayerNumber).removeResources(resourceBank
					.getResourceCost(((HexVertex) selectedItem).addCity(players.get(currentPlayerNumber))))); // remove
			// resources
			// from
			// a
			// player
			// and
			// give
			// them
			// to
			// the
			// bank
		}
		((HexVertex) selectedItem).setStroke(Color.GOLD); // set fill to color
															// the vertex the
															// player's color
															// (indicating a
															// settlement
		players.get(currentPlayerNumber).assets.add(players.get(currentPlayerNumber), ((HexVertex) selectedItem)); // add
		// this
		// new
		// asset
		// to
		// the
		// player's
		// list
		// of
		// assets
		players.get(currentPlayerNumber)
				.setVictoryPoints((players.get(currentPlayerNumber).getVictoryPoints()) + SETTLMENT_VP_VALUE + 1); // increase
		// the
		// vp
		// of
		// the
		// player
		pgbVictoryPoints.setProgress(((double) players.get(currentPlayerNumber).getVictoryPoints() / 10)); // adjust
		// the
		// progress
		// bar
		// for
		// vp
		// for
		// thisPlayer
		updateResources();

	}

	private void generateResources(int diceValue) {
		resourceGenerator.generateResources(diceValue);
		updateResources();

	}

	public void createTestPlayers() {
		players = new ArrayList<>();
		Player mark = new Player("Mark", new int[] { 5, 5, 5, 5, 5 }, Color.CYAN);
		Player dek = new Player("Dehkhoda", new int[] { 5, 5, 5, 5, 5 }, Color.RED);
		Player lisa = new Player("Lisa", new int[] { 5, 5, 5, 5, 5 }, Color.BLUE);
		Player mew = new Player("Mew", new int[] { 5, 5, 5, 5, 5 }, Color.VIOLET);
		players.add(mark);
		players.add(dek);
		players.add(lisa);
		players.add(mew);

		System.out.println(getPlayerNum());

	}

	public void openTradeDialog() {
		// set Resource Values
		// 1) get hbox with tradeTrackers in it
		ObservableList<Node> trackers, anchors;
		trackers = tradeResourceTrackers.getChildren(); // get the resource
														// trackers frmo the
														// TradeResourceTrackers
														// HBox
		for (Node node : trackers) {
			anchors = ((VBox) node).getChildren();
			((TradeResourceTracker) node)
					.setResourcesAvailable(players.get(currentPlayerNumber).resources[trackers.indexOf(node)]);
			for (Node n : anchors) {
				System.out.println(((AnchorPane) n).getChildren());
				// 0 Circle
				System.out.println(anchors.size() + "!!!!" + anchors.indexOf(n));
				((Circle) ((AnchorPane) n).getChildren().get(0))
						.setFill((Paint) board.getColorPalette()[trackers.indexOf(node)]);
				// ((Circle)((AnchorPane)n).getChildren().get(0)).setFill(Color.);

				// 1 lblResourceGiveValue
				// 2 lblResourceReqValue
				// 3 Button ^
				// 4 Button v
				// 5 lblResourcesAvailable
				((Label) (((AnchorPane) n).getChildren().get(5)))
						.setText("" + players.get(currentPlayerNumber).resources[anchors.indexOf(n)]);

			}

		}

		pnTradeDialog.setVisible(true);
		pnTradeDialog.getParent().setMouseTransparent(false);
		pnTradeDialog.setMouseTransparent(false);
		// create new tradepack
		thisPlayerTradePack = new TradePack();

		/*
		 * pnPlayerLeft.setLayoutX(); pnPlayerMid.setLayoutX();
		 * pnPlayerRight.setLayoutX();
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
		if (!pnDevelopDialog.isVisible())
			pnDevelopDialog.setVisible(true);
		pnDevelopDialog.setMouseTransparent(false);

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
		txtRes00Text.setText("" + players.get(currentPlayerNumber).getResources()[0]);
		txtRes01Text.setText("" + players.get(currentPlayerNumber).getResources()[1]);
		txtRes02Text.setText("" + players.get(currentPlayerNumber).getResources()[2]);
		txtRes03Text.setText("" + players.get(currentPlayerNumber).getResources()[3]);
		txtRes04Text.setText("" + players.get(currentPlayerNumber).getResources()[4]);

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
		case PLACING_FREE_ROAD:
			return "Placing Free Road";
		case PLACING_FREE_SETTLEMENT:
			return "Placing Free Settlement";
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

	public static int getGameState() {
		return gameState;
	}

	private void setAdvanceBackwards(boolean bool) {
		advanceBackwards = bool;
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

	@FXML
	private void confirmPlaceRobber() {
		moveRobber(rob);
		robberConfirmDialog.setVisible(false);
	}

	@FXML
	private void cancelPlaceRobber() {
		setGameState(MOVING_ROBBER);
		boolean isCanceled = true;
		clearPreviousRobberHex(isCanceled);
		robberConfirmDialog.setVisible(false);
	}

	private void clearPreviousRobberHex(boolean wasCanceled) {
		if (wasCanceled) {
			board.transHexList.get(((Hex) selectedItem).getIndex()).setFill(Color.TRANSPARENT);
		} else if (rob.getCurrentHex() != null) {
			rob.setPreviousHex(rob.getCurrentHex());
			board.transHexList.get(rob.getPreviousHex().getIndex()).setFill(Color.TRANSPARENT);
		}
	}

	public void printPrettyGameState() {
		System.out.println("Game State ==> " + gameStateToString());
		txtCurrentStatus.setText("Game State: " + gameStateToString());
	}

	private boolean canBuyDev(Player player) {
		System.out.println(Arrays.toString(players.get(currentPlayerNumber).getResources()));
		if (checkRequirements(player)) {
			return true;
		}
		return false;
	}

	public void closeBuild() {
		pnBuild.setVisible(false);
		pnBuild.setMouseTransparent(true);
		pnBuild.getParent().setMouseTransparent(true);

	}

        public void buildDev(ActionEvent e) throws IOException{
                System.out.println("You clicked me");

               if(canBuyDev(thisPlayer)){//check if player has the requirements to buy dev card
                    System.out.println("You have enough resources");
                    if(!developmentDeck.isEmpty()){//check there is a dev card to take
                        System.out.println("There are enough Development Cards");
                       resourceBank.bankReturnResource(2,1);//return 1 hemp to bank
                       System.out.println("You returned Hemp");
                       resourceBank.bankReturnResource(3, 1);//return 1 soy to bank
                       System.out.println("You Returned Soy");
                       resourceBank.bankReturnResource(0, 1);//return 1 steel to bank
                       System.out.println("You Returned Steel");
                       System.out.println("RETURNED TO BANK");
                       thisPlayer.setResources(subtractTwoResourceSets(thisPlayer.getResources(), new int[]{1,0,1,1,0}));//subtract resources used from player's resources
                       System.out.println("SUBTRACTED RESOURCES");
                       thisCard=developmentDeck.drawCard();//take development card from deck
                       System.out.println("SAVED CARD FROM DECK");
                       thisCard=developmentDeck.drawCard();
                       thisPlayer.addCard(thisCard);

                       System.out.println("YOU'VE BUILT A DEV CARD");
                       thisPlayer.ifDevCardVictoryPoint(thisPlayer.hand);

                       System.out.println("You've Checked if there is a VP in the Deck");
                       keepKnightCard();
                       System.out.println("You've Checked if Knight gives you longest Road");
                    }
                }
            }
public void useYearOfPlenty (ActionEvent e) throws IOException{
    
}

public void useMonopolyCard(ActionEvent e) throws IOException{
    
}

public void useRoadBuildingCard(ActionEvent e) throws IOException{
    
}

public void keepKnightCard(){
    
}

public void playKnightCard(ActionEvent e) throws IOException{
    
}

	private int getPlayerNum() {
		try {
			return (int) SoM.client.read();
		} catch (Exception e) {
			return -1;
		}
	}
}
