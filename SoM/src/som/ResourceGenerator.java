package som;
import java.util.ArrayList;

/**
 * The resource generator is a class that will dispatch resource to each player.
 * it will get resources from the bank if the banks has available resources. 
 * @author David Kapanga
 * @version 1.0
 */
public class ResourceGenerator {
    	private ArrayList<Player> players;
	private ArrayList<Hex> hexes;
	private int diceValue;
        private ResourceBank resourceBank = new ResourceBank();

    public int getDiceValue() {        
        return diceValue;
    }

    public void setDiceValue(int diceValue) {
        //will get the value of the static dice variable    
        this.diceValue = diceValue;
    }

    public ResourceBank getResourceBank() {
        return resourceBank;
    }

    public void setResourceBank(ResourceBank resourceBank) {
        this.resourceBank = resourceBank;
    }
        
        
                
	/**
         * this method return a list of players around an Hex
         * @return Player a list of player object
         */
	public ArrayList<Player> getPlayers() {
		return players;
	}
        /**
         * this method set the list of players find around a specific Hex, take an ArrayList of Player type
         * @param players parameter players is a ArrayList of players around the Hex
         */
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
        /**
         * this method return an arrayList of Hexes that has been set with the dice number
         * @return hexes the list of hex to find players in
         */
	public ArrayList<Hex> getHexes() {
		return hexes;
	}
        /**
         * this method will set the Hexes list find by retrieveHex() to the variable Hex 
         * @param hexes the list of Hexes find by the method retrieveHex()
         */
	public void setHexes(ArrayList<Hex> hexes) {
		this.hexes = hexes;
	}	
        /**
         * this method check and find every Hex on the board that correspond to the dice number
         * and put them in a list
         * @param dn the dice number
         * @return a list of hexes
         */
	public ArrayList<Hex> retrieveHex(int diceValue, HexBoard board)
	{
		ArrayList<Hex>listOfHexes= new ArrayList<>();
                for(int i=0;i<25;i++)
                {
                    if(board.hexList.get(i).tokenValue==diceValue)
                    {
                        listOfHexes.add(board.hexList.get(i));
                    }
                }
                
		return listOfHexes;
	}
	/**
         * this method return the players found in the list of Hexes
         * @param listOfHexes a list of hex to check in
         * @return list of players to assign resources 
         */
	public ArrayList<Player> checkPlayerOnHex(ArrayList<Hex> listOfHexes)
	{
		ArrayList<Player>listOfplayers= new ArrayList<>();
		for(Hex Hex:listOfHexes)
                {
                    for(int eachVertex=0;eachVertex<6;eachVertex++){
                        if(Hex.verticies.get(eachVertex).asset!=null)
                        {
                            listOfplayers.add(Hex.verticies.get(eachVertex).asset.getPlayer());
                        }
                    }
                }
		return listOfplayers;
	}
        /**
         * this method check if there is enoungh resources in the bank and return true if the bank has it.
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
                 if (resourceBank.resourceList.get(resourceNumber).getResourceAmount() >= 1) //goes through resourceBank->resourceList->specific resourceNumber-> getResource Method
                    boolBank = true; 
                 break;
             case "HEMP" :
                 resourceNumber = 1;
                 if (resourceBank.resourceList.get(resourceNumber).getResourceAmount() >= 1) //goes through resourceBank->resourceList->specific resourceNumber-> getResource Method
                    boolBank = true; 
                 break;
              case "PLASTIC" :
                 resourceNumber = 2;
                 if (resourceBank.resourceList.get(resourceNumber).getResourceAmount() >= 1) //goes through resourceBank->resourceList->specific resourceNumber-> getResource Method
                    boolBank = true; 
                 break;
             case "GLASS" :
                 resourceNumber = 3;
                 if (resourceBank.resourceList.get(resourceNumber).getResourceAmount() >= 1) //goes through resourceBank->resourceList->specific resourceNumber-> getResource Method
                    boolBank = true; 
                 break;
             case "STEEL" :
                 resourceNumber = 4;
                 if (resourceBank.resourceList.get(resourceNumber).getResourceAmount() >= 1) //goes through resourceBank->resourceList->specific resourceNumber-> getResource Method
                    boolBank = true; 
                 break;
             default:
                 System.out.println("INVALID RESOURCE");
                 break;                
         }
      
      
      return boolBank;
  }
	/**
         * this method will be call if hexes are found, players are found and the bank resource return true
         * so it will assign resources to each player in the list.
         * @param listOfHexes list of hex to get ressource and assign it to player in it
         */
	public void assignValue(ArrayList<Hex> listOfHexes)
	{
            int resourceNumber;
            //geting valeu from hex
            for(Hex hex:listOfHexes)
            {
                if(checkBank(hex.ressourceValue))
                {
                    switch(hex.ressourceValue)
                    {             
                        case "SOY" : 
                             resourceNumber = 0;
                             resourceBank.resourceList.get(resourceNumber).drawResource(0);
                             for(int i = 0; i < 6 ; i++) {
                                 if(hex.verticies.get(i).asset!=null)
                                  hex.verticies.get(i).asset.getPlayer().resources[resourceNumber]++;
                             }
                             break;
                        case "HEMP" : 
                             resourceNumber = 1;
                             resourceBank.resourceList.get(resourceNumber).drawResource(0);
                             for(int i = 0; i < 6 ; i++) {
                                 if(hex.verticies.get(i).asset!=null)
                                  hex.verticies.get(i).asset.getPlayer().resources[resourceNumber]++;
                             }
                             break;
                        
                        case "PLASTIC" : 
                             resourceNumber = 1;
                             resourceBank.resourceList.get(resourceNumber).drawResource(0);
                             for(int i = 0; i < 6 ; i++) {
                                 if(hex.verticies.get(i).asset!=null)
                                  hex.verticies.get(i).asset.getPlayer().resources[resourceNumber]++;
                             }
                             break;
                        case "STEEL" : 
                             resourceNumber = 1;
                             resourceBank.resourceList.get(resourceNumber).drawResource(0);
                             for(int i = 0; i < 6 ; i++) {
                                 if(hex.verticies.get(i).asset!=null)
                                  hex.verticies.get(i).asset.getPlayer().resources[resourceNumber]++;
                             }
                             break;
                             
                             default:
                                System.out.println("INVALID RESOURCE");
                                break; 
                   }
                }
                else
                {
                    System.out.println("Not enough ressources for "+ hex.ressourceValue +" in the Hex "+ hex.tokenValue);
                }
            }
            
	}
}
