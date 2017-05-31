/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package som;

import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 *
 * @author david
 */
public class Cpu extends Player{
    
    GameScreenController gm=new GameScreenController();
    
    private int victoryPoints;
    private TradePack tradePack= new TradePack(this);
    private int priority;
    static GameScreenController gsc=new GameScreenController();
    HexBoard board;

    public HexBoard getBoard() {
        return board;
    }

    public void setBoard(HexBoard board) {
        this.board = board;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    public TradePack getTradePack() {
        return tradePack;
    }

    public void setTradePack(TradePack tradePack) {
        this.tradePack = tradePack;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
       
    Cpu(){
        super();
        assets=new Assets();
        resources= new int[]{0,0,0,0,0}; 
        victoryPoints=0;
        setBoard(board);
        int prio=(int)(Math.random()*(7-0+1)+0);
        setPriority(prio);
        
    }

    Cpu(String name, Color playerColor, HexBoard board) {
        super(name,playerColor);
        assets=new Assets();
            nickname=name;
            this.playerColor=playerColor;
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.       
        setBoard(board);
        int prio=(int)(Math.random()*(7-0+1)+0);
        setPriority(prio);
    }

    Cpu(String name, int[] resources, Color playerColor,HexBoard board) {
        super(name,resources,playerColor);
        this.nickname=name;
        this.resources=resources;
        this.victoryPoints=4;
        assets=new Assets();
        this.playerColor=playerColor;           
        setBoard(board);
        int prio=(int)(Math.random()*(7-0+1)+0);
        setPriority(prio);
    }
    public synchronized void play() throws IOException
    {
         /*resource numbers:
            0 = steel = ore
            1 = glass = brick
            2 = hemp = sheep
            3 = soy = wheat
            4 = plastic = lumber
            */
        int steel=resources[0],glass=resources[1],hemp=resources[2],soy=resources[3],plastic=resources[4];

        if(priority==1) //priority 1 the cpu prefferences is on building settlement 
        {           
            if((steel==4) && (glass==4) && (soy==4) && (plastic==4))
            {
                buildSettlement(this.board);
            }
            else
                
            {
                int action=(int)(Math.random()*(5-0+1)+0);
                if(action==1)
                {
                    //build road, if not enough resources incriment action +1 so the next if can be executed
                }
                if(action==2)
                {
                    //trade, if not enough resources incriment action +1 so the next if can be executed
                }
                if(action==3)
                {
                    //upgrade settlement to city, if not enough resources incriment action +1 so the next if can be executed
                }
                if(action==4)
                {
                    //dev card,if not enough resources incriment action +1 so the next if can be executed
                }
                if(action==5)
                {
                    //progress card, if not enough resources incriment action +1 so the next if can be executed
                }
                if(action==6)
                {
                    //pass without doing anything
                }                
            }
        }
        else if(priority==2)//priority 2 the cpu focuse on building road 
        {
            if((steel==4) && (glass==4) && (soy==4) && (plastic==4))
            {
                BuildRoad(board);
            }
            else
            {
                int action=(int)(Math.random()*(5-0+1)+0);
                if(action==1)
                {
                    //build road, if not enough resources incriment action +1 so the next if can be executed
                }
                if(action==2)
                {
                    //trade, if not enough resources incriment action +1 so the next if can be executed
                }
                if(action==3)
                {
                    //upgrade settlement to city, if not enough resources incriment action +1 so the next if can be executed
                }
                if(action==4)
                {
                    //dev card,if not enough resources incriment action +1 so the next if can be executed
                }
                if(action==5)
                {
                    //progress card, if not enough resources incriment action +1 so the next if can be executed
                }
                if(action==6)
                {
                    //pass without doing anything
                }                
            }
        }
        else if(priority==3)
        {
        }
        else if(priority==4)
        {
        }
        else if(priority==5)
        {
        }
        else if(priority==6)
        {
        }
        else
        {
        }
    }
    
    public static void buildSettlement(HexBoard board) throws IOException
    {
        ArrayList<HexVertex>listOfVertexAvailable= new ArrayList<>();
        //for each hex on the bord check each vertex to know if a player has a Settlement already build
        //if not we put the available vertex in a list of available vertex
        for(Hex  hex: board.hexList)
        {
            for(int i=0;i<6;i++)
            {
                if(hex.getVerticies().get(i).getAsset().getPlayer()!=null)
                {
                    listOfVertexAvailable.add(hex.getVerticies().get(i));
                }
            }
        }
        //generate a random number between 0 and the number of vertex available 
        //this index is where the AI will built the settlement
        int indexForBuildInVertex = (int)(Math.random()*(listOfVertexAvailable.size()-0+1)+0);
        HexVertex selectedItem = listOfVertexAvailable.get(indexForBuildInVertex);
        gsc.buildSettlement(selectedItem);
              
   }
    
    public static void BuildRoad(HexBoard board) throws IOException
    {
        ArrayList<HexEdge>listOfEgdeAvailable= new ArrayList<>();
         for(HexEdge  edge: board.edgeList)
        {
            if(edge.getOwner()!=null)
            {
                listOfEgdeAvailable.add(edge);
            }
        }
         int indexForBuildInEdge = (int)(Math.random()*(listOfEgdeAvailable.size()-0+1)+0);
         HexEdge selectedItem = listOfEgdeAvailable.get(indexForBuildInEdge);
        gsc.buildRoad(selectedItem);
    }
     public static void tradeRessource()
    {
    }
     
     public static void buildCity(HexBoard board)
    {
        ArrayList<HexVertex>listOfPlayerVertexToUpgrade= new ArrayList<>();
        //for each hex on the bord check each vertex to know if a player has a city already build
        //if not we put the available vertex in a list of available vertex
        for(Hex  hex: board.hexList)
        {
            for(int i=0;i<6;i++)
            {
                Player thisPlayer = null;

                if(hex.getVerticies().get(i).getAsset().getPlayer()==thisPlayer)
                {
                    listOfPlayerVertexToUpgrade.add(hex.getVerticies().get(i));
                }
            }
        }
        //generate a random number between 0 and the number of vertex that the player has a settlement 
        //this number will be use as index where the AI will built the city
        int indexForBuildInVertex = (int)(Math.random()*(listOfPlayerVertexToUpgrade.size()-0+1)+0);
        
        /*
        code for upgradind the settlement to a city here
        */
        
   }
}
