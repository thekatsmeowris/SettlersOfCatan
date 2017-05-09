

//package som;

import java.util.ArrayList;

/*
 *  Resource generator is a class that will dispatch resources to each player.
 * it will get resources from the bank if the bank has available resources. 
 * @author Chadwick J Davis
 */
 class ResourceGenerator {
    
    private  ArrayList<Players> players = new ArrayList<>();
    private  ArrayList<Hex> hexes = new ArrayList<>();
    private int currentDiceValue;
    private String currentResource;    
    private ResourceBank resourceBank = new ResourceBank();
    
    
    
  /*
   * Constructor for ResourceGenerator
   * @param resourceBank an object of class ResourceBank
   */ 
  public ResourceGenerator(ResourceBank resourceBank)
  {
      currentResource = "N/A";
      this.resourceBank = resourceBank; 
     
  }  
  
  /*
   * This method will set the Players list
   * @param players ArrayList of players
   */  
  public void setPlayers(ArrayList<Players> players)
  {
      this.players = players;
  }
  
  /*
   * This method returns a list of players around an Hex
   * @return Players a list of player object
   */
  public ArrayList<Players> getPlayers()
  {
      return players;
  }  
  
  /*
   * This method will set the Hexes list
   * @param hexes ArrayList of hexes
   */
  
  public void setHexes(ArrayList<Hex> hexes)
  {
      this.hexes = hexes;
  }
  
  /*
   * This method returns a list of hexes that has been set with the dice number
   * @return hexes the list of hex to find players in
   */
  public ArrayList<Hex> getHexes()
  {
      return hexes;
  }
  
 /*
  * This method returns the value of the dice Roll
  * @return integer
  */
  
  public int getDiceValue()
  {      
      return currentDiceValue;      
  }
  
  /*
   * This method sets the dice value
   * @param diceValue the value of the dice
   */
  public void setDiceValue(int diceValue)
  {
      currentDiceValue = diceValue;
  }
  
  /*
  * This method returns the value of the Current Resource
  * @return String
  */
  public String getResource()
  {
      return currentResource;
  }
  
  /*
  * This method sets the Current Resource String value
  * @param resource String variable representing resource
  */
  public void setResource(String resource)
  {
      currentResource = resource;
  }
  
  /*
   * this method return the players found in the list of Hexes
   * @param listOfHexes a list of hex to check in
   * @return list of players to assign resources 
   */   
  public ArrayList<Players> checkPlayerOnHex(ArrayList<Hex> listOfHexes)
  {
      ArrayList<Players>listOfplayers = new ArrayList<>();
  		
  		return listOfplayers;
  }
   
 /*
  * This method checks if there is enoungh resources in the bank and returns true if the bank has it.
  * @param resource the resource
  * @return boolean value.
  */
 
  public boolean checkBank(String resource) 
  {                                                   
                                                      
      /*
      resource numbers:
      0 = soy = wheat
      1 = hemp = sheep
      2 = plastic = lumber
      3 = glass = brick
      4 = steel = ore
      */
      int resourceNumber = 0;
      boolean boolBank = false;      
            
      switch(resource){
            
            case "SOY" :                
                 resourceNumber = 0;
                 if (resourceBank.resourceList.get(resourceNumber).getResource() >= 1) //goes through resourceBank->resourceList->specific resourceNumber-> getResource Method
                    boolBank = true; 
                 break;
             case "HEMP" :
                 resourceNumber = 1;
                 if (resourceBank.resourceList.get(resourceNumber).getResource() >= 1) //goes through resourceBank->resourceList->specific resourceNumber-> getResource Method
                    boolBank = true; 
                 break;
              case "PLASTIC" :
                 resourceNumber = 2;
                 if (resourceBank.resourceList.get(resourceNumber).getResource() >= 1) //goes through resourceBank->resourceList->specific resourceNumber-> getResource Method
                    boolBank = true; 
                 break;
             case "GLASS" :
                 resourceNumber = 3;
                 if (resourceBank.resourceList.get(resourceNumber).getResource() >= 1) //goes through resourceBank->resourceList->specific resourceNumber-> getResource Method
                    boolBank = true; 
                 break;
             case "STEEL" :
                 resourceNumber = 4;
                 if (resourceBank.resourceList.get(resourceNumber).getResource() >= 1) //goes through resourceBank->resourceList->specific resourceNumber-> getResource Method
                    boolBank = true; 
                 break;
             default:
                 System.out.println("INVALID RESOURCE");
                 break;                
         }
      
      
      return boolBank;
  }
  
  /*
  * this method will check the Bank and add the desired resource to the specific player
  * and will remove one of the desired resource from the bank;
  * @param playersTurnNumber the number corresponding to the turn order
  */
  
  
  public void assignValue(int playerTurnNumber) 
  {
      //goes through resourceBank->resourceList->specific resourceNumber-> getResource Method
      //goes through players->specific playerTurnNumber -> increas resources array[resourceNumber];
      
      int resourceNumber;
      switch(currentResource){ 
            
            case "SOY" : 
                 resourceNumber = 0;
                 if (checkBank(currentResource))
                      resourceBank.resourceList.get(resourceNumber).drawResource(1);
                      players.get(playerTurnNumber).resources[resourceNumber]++;
                 break;
             case "HEMP" :
                 resourceNumber = 1;
                 if (checkBank(currentResource))
                      resourceBank.resourceList.get(resourceNumber).drawResource(1);
                      players.get(playerTurnNumber).resources[resourceNumber]++;
                 resourceNumber = 1;                  
                 break;
              case "PLASTIC" :
                 resourceNumber = 2;
                 if (checkBank(currentResource))
                      resourceBank.resourceList.get(resourceNumber).drawResource(1);
                      players.get(playerTurnNumber).resources[resourceNumber]++;                  
                 break;
             case "GLASS" :
                 resourceNumber = 3;
                 if (checkBank(currentResource))
                      resourceBank.resourceList.get(resourceNumber).drawResource(1);
                      players.get(playerTurnNumber).resources[resourceNumber]++; 
                 break;
             case "STEEL" :
                 resourceNumber = 4;
                 if (checkBank(currentResource))
                      resourceBank.resourceList.get(resourceNumber).drawResource(1);
                      players.get(playerTurnNumber).resources[resourceNumber]++;  
                 break;
             default:
                 System.out.println("INVALID RESOURCE");
                 break;                
         }
  }
 }
  

 
    
