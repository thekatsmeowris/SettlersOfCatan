/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package som;

import java.util.ArrayList;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;


/**
 *
 * @author makogenq
 */
public class Player {
    final int VICTORY_POINT_MAX=10;
    
    private TradePack tradePack= new TradePack(this);


    Pane pnPlayerInfo=new Pane();
    Arc victoryPointGauge = new Arc();
    
    
    int[] resources;
    ArrayList<DevelopmentCard> developmentCards;
    ResourceManager resourceManager= new ResourceManager(); 
    private int victoryPoints;
    
    Color playerColor;
    String nickname;
    //String n, d, imgName;
    Assets assets;
    DevelopmentCard card;//Creates an object instance of the card from the development deck
    Player(){

        assets=new Assets();
        resources= new int[]{0,0,0,0,0}; 
        developmentCards=new ArrayList<>();//new development card ArrayList to hold Player's development Cards
        
        victoryPoints=0;
        
    }

    Player(String name, Color playerColor) {
        assets=new Assets();
            nickname=name;
            this.playerColor=playerColor;
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       
    }
    
    

    Player(String name, int[] resources, Color playerColor) {
        this.nickname=name;
        this.resources=resources;
        victoryPoints=4;
        assets=new Assets();
        this.playerColor=playerColor;
    }
    Player(ArrayList<DevelopmentCard> developmentCards){
        
        this.developmentCards=developmentCards;
        developmentCards=new ArrayList<>();
             
    }
    public void add(DevelopmentCard card){//add a development card to the Player's ArrayList
        developmentCards.add(card);
    }
    //need to add remove development card from Player's ArrayList
    //need to add victory point increase for Player if the development card is a VP
    public void setVictoryPoints(int value){
        victoryPoints=value;
        victoryPointGauge.setLength(((double)value/(double)VICTORY_POINT_MAX)*360);     //3.6 is 360 divided by the 100 for the 100 we would have multiplied the value/vpMax to get a percentage.
        System.out.println(pnPlayerInfo.getWidth());
    }
    
    	public void build(String assetName){
		if (assetName.equals("SETTLEMENT"));
		else if (assetName.equals("ROAD"));
		else if (assetName.equals("CITY"));
	}

	public void buy(){
		// resources.bankDrawResource("SOY", 10);
		// resources.printResourceList();
                
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


    
    public int countResources()
    {
        int counter = 0;
        
        for(int i = 0; i < resources.length -1; i++)
        {
            counter += resources[i];
        }
        return counter;
    }

    Color getPlayerColor() {
        return playerColor;
    }






 
}
