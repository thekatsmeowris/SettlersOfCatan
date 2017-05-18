
package som;

import java.util.ArrayList;
import java.util.Random;

/**
 * Robber is a class that move to desired hexes when a seven is rolled on the die.
 * The Robber can move, take resources, and prevent resources from being generated on a hex. 
 * @author Chadwick J Davis
 */
public class Robber {
    private Player CurrentPlayer;
    private Player pickedPlayer;
    private  ArrayList<Player> players = new ArrayList<>();
    private ResourceBank resourceBank = new ResourceBank();
     /*
   * This method will Activate the Robber which will later activate all the othe methods
   * Not sure how turn order will work, for now I added a player argument as a requirement
   * @param players ArrayList of players
   */ 
    public void AcitaveRobber(Player player)
    {
        CurrentPlayer = player;
        SevenResourceCheck();
        MoveRobber();        
    }
    
    private void SevenResourceCheck()
    {
        
        for(int i = 0; i < players.size() -1; i++) //for loop runs once for every player
        {
            if(players.get(i).countResources() >= 7) //If the current player has more than 7 resource cards...
            {
                for(int j = 0; j < (players.get(i).countResources()/2) -1; j++) //for loop to run half of number of player resource cards, rounding down. Ex: player has 7 cards it will run 3 times
                {
                    players.get(i).resources[RandomResourceValue(i)]--;    //randomly removes one resource dictated by RandomResourceValue method  
                    resourceBank.resourceList.get(i).returnResource(i);
                } //end of inner for loop
            } //end of inner if statment
        } //end of for loop
        
        MoveRobber();
    }
    
    private int RandomResourceValue(int playerNumber) //Need to set exceptions to random so prevent endless recurrision
    {
        Random r = new Random();
        int randomVariable = 0;
        randomVariable = r.nextInt(5-0)+0; // IF THERE IS AN INDEX ERROR:THIS MIGHT BE THE CAUSE: This should return a number between 0-4
        
        if(players.get(playerNumber).resources[randomVariable] == 0) //If the player does not have the resource matching the random number: the program runs again
        {
            RandomResourceValue(playerNumber);
        }
        return randomVariable;
    }
       
    private void MoveRobber()
    {
        //Needs Implementation
        StealResources(pickedPlayer);
    }
    
    private void StealResources(Player player)
    {
     //Needs Implementation
    }
    
    public void driver()
    {
       //Needs Implementation  
    }
    
}
