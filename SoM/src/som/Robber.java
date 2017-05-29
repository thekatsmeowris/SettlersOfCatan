
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
    private Hex pickedHex = new Hex();
    private Hex previousHex = new Hex();
    
     /*
   * This method will Activate the Robber which will later activate all the othe methods
   * Not sure how turn order will work, for now I added a player argument as a requirement
   * @param players ArrayList of players
   */ 
    public void AcitaveRobber(Player player)
    {
        CurrentPlayer = player;
        SevenResourceCheck();               
    }
    
    /*
     * This method checks each player if they have 7 or more resources and has them randomly
     * remove half of their resources (rounding down)
     */
    private void SevenResourceCheck()
    {
        int randomResourceNumber = 0;
        for(int i = 0; i < players.size(); i++) //for loop runs once for every player
        {
            if(players.get(i).countResources() >= 7) //If the current player has more than 7 resource cards...
            {
                System.out.println(players.get(i).getNickname() + " lost:");
                for(int j = 0; j < (players.get(i).countResources()/2); j++) //for loop to run half of number of player resource cards, rounding down. Ex: player has 7 cards it will run 3 times
                {
                    randomResourceNumber = RandomResourceValue(i);      //randomly generates a number from 0-4 using the RandomResourceValue method
                    players.get(i).resources[randomResourceNumber]--;    //randomly removes one resource dictated by RandomResourceValue method  
                    resourceBank.bankReturnResource(randomResourceNumber,1);
                    System.out.print(" one " + resourceBank.getResourceName(randomResourceNumber));
                } //end of inner for loop
            } //end of inner if statment
        } //end of for loop
        
        MoveRobber(CurrentPlayer);
    }
    
    /*
     * This randomly generates a number bewteen 0 and 4 and checks if the
     * corresponding player has said resourceNumber in their resources[]
     */
    private int RandomResourceValue(int playerNumber)
    {
        
        ArrayList<Integer> excludedNumbers = new ArrayList<>();
                
         for(int i = 0; i < 5; i++)
        {
            if(players.get(playerNumber).resources[i] == 0)
                excludedNumbers.add(i);
        }
         
        Random r = new Random();
        int randomVariable = 0;
        randomVariable = r.nextInt(5-0)+0; // IF THERE IS AN INDEX ERROR:THIS MIGHT BE THE CAUSE: This should return a number between 0-4
        
        
        while(excludedNumbers.contains(randomVariable)){ //While loop to run until the random number generated is not in the excludedNumber arrayList
            randomVariable = r.nextInt(5-0)+0; // IF THERE IS AN INDEX ERROR:THIS MIGHT BE THE CAUSE: This should return a number between 0-4
        }
        
        return randomVariable;
    }
    
    /*
     * This method allows the current player to move the robber to a desired hex
     * Use this method for Knight card and use ActivateRobber method is a seven on the dice are rolled
     */
    public void MoveRobber(Player player)
    {
        //Needs Implementation to move Robber to specific hex
        
        //pickedHex = the specific hex
        //pickedHex can no longer generate resources until rober is moved again
        
        checkHex(pickedHex);
    }
    
    /*
     * This method checks the specific hex for players
      */
    private void checkHex(Hex pickedHex)
    {
     ArrayList<Player>listOfplayers= new ArrayList<>();
	
        for(int eachVertex=0;eachVertex<6;eachVertex++){
             if(pickedHex.getVerticies().get(eachVertex).getAsset()!=null)
                 {
                   listOfplayers.add(pickedHex.getVerticies().get(eachVertex).getAsset().getPlayer());
                   }
         }
        
        pickPlayer(listOfplayers);
        
    }
    
    /*
     * This method will either remove a random resources from one player and give it to the current player
     * or will allow the player to pick from a list of players on a specific hex
     * and remove a random resource from the picked player and give it to the current player
     */
    private void pickPlayer(ArrayList<Player> listOfplayers)
    {   
        int randomResourceNumber = 0;
        randomResourceNumber = RandomResourceValue(0);
        
        if(listOfplayers.size() == 1)
        {
            pickedPlayer = listOfplayers.get(0);
            pickedPlayer.resources[randomResourceNumber]--;
            CurrentPlayer.resources[randomResourceNumber]++;
            System.out.println(CurrentPlayer.getNickname() + " stole one " + resourceBank.getResourceName(randomResourceNumber) + " from " + pickedPlayer);
        }
         if (listOfplayers.size() > 1)
        {
            //pick player from list of players
            //pickedPlayer = listOfplayers[?]
            //pickedPlayer.resources[randomResourceNumber]--;
            //CurrentPlayer.resources[randomResourceNumber]++;
        }
        
    }
     /*
     * This method sets players to a given Player ArrayList
     */   
    public void setPlayerList(ArrayList<Player> listOfplayers)
    {
         players = (ArrayList<Player>)listOfplayers.clone();
    }
}

