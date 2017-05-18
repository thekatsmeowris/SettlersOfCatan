/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package som;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author David Kapanga
 */
public class AI implements Runnable{

    @Override
    public void run() {
         Lock lock =new ReentrantLock();
        Condition startPlay=lock.newCondition();
        
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
    
    public static void buildSettlement(HexBoard board)
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
        //this index is where the AI will built the city
        int indexForBuildInVertex = (int)(Math.random()*(listOfVertexAvailable.size()-0+1)+0);
        
   }
    public static void BuildRoad()
    {
    }
   
    public static void tradeResspurce()
    {
    }
    
}
    
