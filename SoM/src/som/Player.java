/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package som;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;

/**
 *
 * @author makogenq
 */
public class Player {
<<<<<<< HEAD
    final int VICTORY_POINT_MAX=10;
    Integer diceRoll;
    private TradePack tradePack= new TradePack(this);
    Pane pnPlayerInfo=new Pane();
    Arc victoryPointGauge = new Arc();
    
    int[] resources;
   
    int resourceType;
    private int freeSettlements = 2;
    private int freeRoads = 2;
    public static int largestArmy=0;
    ArrayList<DevelopmentCard> hand;
    private int freeSettlements = 2;
    private int freeRoads = 2;
  
    ResourceManager resourceManager= new ResourceManager(); 
    private int victoryPoints;
    
    Color playerColor;
    String nickname;

    public int getVictoryPoints() {
        return victoryPoints;
    }
    //String n, d, imgName;
    PlayerAssets assets;
    DevelopmentDeck deck;
    DevelopmentCard card;//Creates an object instance of the card from the development deck
   
    
    Player(){

        assets = new PlayerAssets();
        resources= new int[]{0,0,0,0,0};
        hand=new ArrayList<>(25);
        victoryPoints=0;
        
        //hand=new ArrayList[24];
        //for(int i=0; i<24; i++){
           // hand[i]=new ArrayList<DevelopmentCard>();
        //}
        
        
        Knight k= new Knight();
        VictoryPoint vp=new VictoryPoint();
        YearOfPlenty yop=new YearOfPlenty();
        Monopoly mp=new Monopoly();
        RoadBuilding rb=new RoadBuilding();
       
    }
    
    


	


    Player(String name, Color playerColor) {
        assets = new PlayerAssets();
            nickname=name;
            this.playerColor=playerColor;
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       
    }
    
    


	public void buy(){
		// resources.bankDrawResource("SOY", 10);
		// resources.printResourceList();
=======
	private static final int VICTORY_POINT_MAX = 10;
>>>>>>> master

	Integer diceRoll;

	private TradePack tradePack = new TradePack(this);

	Pane pnPlayerInfo = new Pane();
	Arc victoryPointGauge = new Arc();

	int[] resources;
	private int freeSettlements = 2;
	private int freeRoads = 2;
	ResourceManager resourceManager = new ResourceManager();
	private int victoryPoints;

	Color playerColor;
	String nickname;
	PlayerAssets assets;

	Player() {

		assets = new PlayerAssets();
		resources = new int[] { 0, 0, 0, 0, 0 };
		victoryPoints = 0;

	}

	Player(String name, Color playerColor) {
		assets = new PlayerAssets();
		nickname = name;
		this.playerColor = playerColor;
		// throw new UnsupportedOperationException("Not supported yet."); //To
		// change body of generated methods, choose Tools | Templates.
	}

	Player(String name, int[] resources, Color playerColor) {
		this.nickname = name;
		this.resources = resources;
		victoryPoints = 0;
		assets = new PlayerAssets();
		this.playerColor = playerColor;
	}

	public void setVictoryPoints(int value) {
		victoryPoints = value;
		victoryPointGauge.setLength(((double) value / (double) VICTORY_POINT_MAX) * 360);
		System.out.println(pnPlayerInfo.getWidth());

<<<<<<< HEAD
		





    Player(String name, int[] resources, Color playerColor) {
        this.nickname=name;
        this.resources=resources;
        victoryPoints=4;
        assets = new PlayerAssets();
        this.playerColor=playerColor;
    }
   
    public void addCard(DevelopmentCard d){
		hand=new ArrayList<>();
		hand.add(d);
	}
    public void removeCard(DevelopmentCard d){
            hand.remove(d);
    }
   //no remove method can use remlve card when acted upon in each use card method
    public void ifDevCardVictoryPoint(ArrayList<DevelopmentCard> hand1){
        /*for (Iterator<DevelopmentCard> it = hand.iterator(); it.hasNext();) {
        DevelopmentCard d1 = it.next();
        VictoryPoint vp=new VictoryPoint();
        if(d1==VictoryPoint vp)
        victoryPoints+=1;
        }*/
       
        hand.forEach((DevelopmentCard d1) -> {//Go through whole deck 
            //if("Victory Point".equals(d1.getName()))
            if(d1 instanceof VictoryPoint) {//check if the card is an instance of VictoryPoint
                victoryPoints+=1;//add to the VictoryPoints of the player
                hand.remove(d1);//remove the card that was a VictoryPoint
                System.out.println("There is a VP in deck");
                
            }
            else{
                System.out.println("There is no VP in deck");
            }
                
            
           
        });
    }
   
     
    
    public void setVictoryPoints(int value){
        victoryPoints=value;
        victoryPointGauge.setLength(((double)value/(double)VICTORY_POINT_MAX)*360);     //3.6 is 360 divided by the 100 for the 100 we would have multiplied the value/vpMax to get a percentage.
        System.out.println(pnPlayerInfo.getWidth());
        if (victoryPoints > 6) {
=======
		if (victoryPoints > 6) {
>>>>>>> master
			MediaClass.playMusic2();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				Logger.getLogger(GameRoomSelectController.class.getName()).log(Level.SEVERE, null, ex);
			}
			// SoM.mMusic1.stop();
		}

	}

	public void build(String assetName) {
		if (assetName.equals("SETTLEMENT"))
			;
		else if (assetName.equals("ROAD"))
			;
		else if (assetName.equals("CITY"))
			;
	}

	public void buildInitial() {
		this.build("SETTLEMENT");
		this.build("ROAD");
	}

	public void buy() {

		// resources.bankDrawResource("SOY", 10);
		// resources.printResourceList();

	}

	int getVictoryPoints() {
		return victoryPoints;
	}

<<<<<<< HEAD
<<<<<<< HEAD
        public int largestArmyAdd(){
            largestArmy+=1;
            return largestArmy;
            
        }
        public int largestArmySubtract(){
            largestArmy-=1;
            return largestArmy;
        }
        public int getLargestArmy(){
            return largestArmy;
        }
        public void setLargestArmy(){
            this.largestArmy=largestArmy;
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

    public int[] getResources() {
        return resources;
    }
    
    public void setResource(int index, int value) {
        this.resources[index]=value;
    }
    
    public void setResources(int[] resources) {
        this.resources = resources;
    }
    
    public void addResource(int resourceType, int qty){
            this.resources[resourceType]+=qty;
    }
    
    public void addResources(int[] resources){
        for(int i=0; i<this.resources.length;i++){
            this.resources[i]+=resources[i];
        }
    }
 
    public int[] removeResources(int[] resources){
        for(int i=0; i<this.resources.length;i++){
            this.resources[i]-=resources[i];
        }
        return resources;
    }
=======
	public void setFreeRoads(int freeRoads) {
		this.freeRoads = freeRoads;
=======
	public TradePack getTradePack() {
		return tradePack;
	}

	public void setTradePack(TradePack tradePack) {
		this.tradePack = tradePack;
>>>>>>> master
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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
>>>>>>> origin/katie_mowris

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

<<<<<<< HEAD
        for (int i = 0; i < resources.length - 1; i++) {
            counter += resources[i];
        }
        return counter;
    }
<<<<<<< HEAD

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
        public void build(String assetName) {
		if (assetName.equals("SETTLEMENT"))
			;
		else if (assetName.equals("ROAD"))
			;
		else if (assetName.equals("CITY"))
			;
	}
public void buildInitial() {
		this.build("SETTLEMENT");
		this.build("ROAD");
	}






 
=======
    	
>>>>>>> origin/katie_mowris
}
=======
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
>>>>>>> master

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
