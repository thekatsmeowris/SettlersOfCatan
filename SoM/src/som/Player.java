/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package som;

import java.util.ArrayList;

import devCards.Knight;
import devCards.VictoryPoint;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Arc;

/**
 *
 * @author makogenq
 * @author thekatsmeowris
 */
public class Player {
	private static final int VICTORY_POINT_MAX = 10;

	Integer diceRoll;

	private TradePack tradePack = new TradePack(this);

	Pane pnPlayerInfo = new Pane();
	Arc victoryPointGauge = new Arc();

	int[] resources;
	ArrayList<DevelopmentCard> hand;
	ArrayList<DevelopmentCard> removevp;
	private int freeSettlements = 2;
	private int freeRoads = 2;
	int resourceType;
	public static int largestArmy;

	ResourceManager resourceManager = new ResourceManager();
	private int victoryPoints;

	Color playerColor;
	LinearGradient linearGradient;
	String nickname;
	PlayerAssets assets;
	DevelopmentDeck deck;
	DevelopmentCard card;// Creates an object instance of the card from the
							// development deck

	Player() {

		assets = new PlayerAssets();
		resources = new int[] { 0, 0, 0, 0, 0 };
		hand = new ArrayList<>();
		victoryPoints = 0;

	}

	Player(String name, Color playerColor) {
		assets = new PlayerAssets();
		nickname = name;
		this.playerColor = playerColor;
		Stop[] stops = new Stop[] {
				new Stop(0, new Color(88, 44, 44, 0.5)), new Stop(0.5, new Color(this.getPlayerColor().getRed(),
						this.getPlayerColor().getGreen(), this.getPlayerColor().getBlue(), 0.5)),
				new Stop(1, new Color(88, 44, 44, 0.5)) };
		linearGradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, stops);
		// throw new UnsupportedOperationException("Not supported yet."); //To
		// change body of generated methods, choose Tools | Templates.
	}

	Player(String name, int[] resources, Color playerColor) {
		this.nickname = name;
		this.resources = resources;
		victoryPoints = 0;
		assets = new PlayerAssets();
		this.playerColor = playerColor;
		Stop[] stops = new Stop[] { new Stop(0, new Color(88 / 255, 44 / 255, 44 / 255, 0.5)),
				new Stop(0.6,
						new Color(this.getPlayerColor().getRed(), this.getPlayerColor().getGreen(),
								this.getPlayerColor().getBlue(), 0.8)),
				new Stop(1, new Color(88 / 255, 44 / 255, 44 / 255, 0.6)) };
		linearGradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, stops);
	}

	public void addCard(DevelopmentCard d) {
		hand = new ArrayList<>();
		hand.add(d);
	}

	public void removeCard(DevelopmentCard d) {
		hand.remove(d);
	}

	public void ifDevCardVictoryPoint(ArrayList<DevelopmentCard> hand1) {
		/*
		 * for (Iterator<DevelopmentCard> it = hand.iterator(); it.hasNext();) {
		 * DevelopmentCard d1 = it.next(); VictoryPoint vp=new VictoryPoint();
		 * if(d1==VictoryPoint vp) victoryPoints+=1; }
		 */
		removevp = new ArrayList<>();
		hand.forEach((DevelopmentCard d1) -> {// Go through whole deck
			// if("Victory Point".equals(d1.getName()))
			if (d1 instanceof VictoryPoint) {// check if the card is an instance
												// of VictoryPoint
				victoryPoints += 1;
				removevp.add(d1);
				// add to the VictoryPoints of the player
				// remove the card that was a VictoryPoint
				System.out.println("There is a VP in deck");

			} else {
				System.out.println("There is no VP in deck");
			}

		});
		removeDevelopmentCardArrayList(removevp);
	}

	public void ifKnightAddToLargestArmy(ArrayList<DevelopmentCard> hand2) {

		hand.forEach((DevelopmentCard d1) -> {// Go through whole deck
			// if("Victory Point".equals(d1.getName()))
			if (d1 instanceof Knight) {// check if the card is an instance of
										// VictoryPoint
				largestArmy += 1;// add to the VictoryPoints of the player
				// remove the card that was a VictoryPoint
				System.out.println("There is a KC in deck");

			} else {
				System.out.println("There is no KC in deck");
			}

		});

	}

	public void removeDevelopmentCardArrayList(ArrayList<DevelopmentCard> newArrayList) {
		newArrayList.forEach((DevelopmentCard d5) -> {
			hand.remove(hand.indexOf(newArrayList.get(newArrayList.indexOf(d5))));
		});

	}

	public void setVictoryPoints(int value) {
		victoryPoints = value;
		victoryPointGauge.setLength(((double) value / (double) VICTORY_POINT_MAX) * 360);
		GameScreenController.audio.changeMusic();
		System.out.println(pnPlayerInfo.getWidth());

	}

	public void setPlayerGradient() {

	}

	public String colorToHexString() {
		String colorString = String.format("#%02X%02X%02X", (int) (this.getPlayerColor().getRed() * 255),
				(int) (this.getPlayerColor().getGreen() * 255), (int) (this.getPlayerColor().getBlue() * 255));
		return colorString;
	}

	public LinearGradient getPlayerColorGradient() {
		return this.linearGradient;
	}

	public int largestArmyAdd() {
		largestArmy += 1;
		return largestArmy;

	}

	public int largestArmySubtract() {
		largestArmy -= 1;
		return largestArmy;
	}

	public int getLargestArmy() {
		return largestArmy;
	}

	public void setLargestArmy() {
		this.largestArmy = largestArmy;
	}

	int getVictoryPoints() {
		return victoryPoints;
	}

	public TradePack getTradePack() {
		return tradePack;
	}

	public void setTradePack(TradePack tradePack) {
		this.tradePack = tradePack;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public ArrayList<DevelopmentCard> getHand() {
		return hand;
	}

	public int[] getResources() {
		return resources;
	}

	public void setResources(int[] resources) {
		this.resources = resources;
	}

	public void addResource(int resourceType, int qty) {
		this.resources[resourceType] += qty;
	}

	public void addResources(int[] resources) {
		for (int i = 0; i < this.resources.length; i++) {
			this.resources[i] += resources[i];
		}
	}

	public void setResource(int index, int value) {
		this.resources[index] = value;
	}

	public int[] removeResources(int[] resources) {
		for (int i = 0; i < this.resources.length; i++) {
			this.resources[i] -= resources[i];
		}
		return resources;
	}

	@Override
	public String toString() {
		return "Player{" + "VICTORY_POINT_MAX=" + VICTORY_POINT_MAX + ", pnPlayerInfo=" + pnPlayerInfo
				+ ", victoryPointGauge=" + victoryPointGauge + ", resources=" + resources + ", resourceManager="
				+ resourceManager + ", victoryPoints=" + victoryPoints + ", playerColor=" + playerColor + ", nickname="
				+ nickname + ", assets=" + assets + '}';
	}

	public int countResources() {
		int counter = 0;

		for (int i = 0; i < resources.length - 1; i++) {
			counter += resources[i];
		}
		return counter;
	}

	public void setDiceRoll(Integer rollDice) {
		this.diceRoll = rollDice;
	}

	public Integer getDiceRoll() {
		return this.diceRoll;
	}

	public boolean hasLargestArmy() {
		return (GameScreenController.getPlayerWithLargestArmy() == this);
	}

	public boolean hasLongestRoad() {
		return (GameScreenController.getPlayerWithLongestRoad() == this);
	}

	Color getPlayerColor() {
		return playerColor;
	}

	public int getFreeSettlements() {
		return freeSettlements;
	}

	public void setFreeSettlements(int freeSettlements) {
		this.freeSettlements = freeSettlements;
	}

	public int getFreeRoads() {
		return freeRoads;
	}

	public void setFreeRoads(int freeRoads) {
		this.freeRoads = freeRoads;
	}

}
