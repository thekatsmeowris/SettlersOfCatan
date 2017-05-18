package som;
import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 * The resource generator is a class that will dispatch resource to each player.
 * it will get resources from the bank if the banks has available resources. 
 * @author David Kapanga
 * @author Chadwick J Davis

 * @version 1.0
 */

public class ResourceGenerator {

    //set resource constants
    private static final int STEEL=0;
    private static final int GLASS=1;
    private static final int HEMP=2;
    private static final int SOY=3;
    private static final int PLASTIC=4;

    private ArrayList<Player> players;
    private ArrayList<Hex> hexes;
    private int diceValue;
    private HexBoard board;
    private ResourceBank resourceBank = new ResourceBank();
    
    void generateResources(int diceValue) {
            
        //1 get dice value
        //2 find all hexes with correspondinge dice value
        //3 get all players adjacent to that hex
        //4 get amount of resources to be distributed
        //5 check if the bank has enough
        //6 distribute resources
        
        hexes=checkHexWithDiceValue(diceValue);
        players=getPlayersOnHex(hexes);
        //for each hex
        
    }



	
        /**
         * this method check and find every Hex on the board that correspond to the dice number
         * and put them in a list
         * @param dn the dice number
         * @return a list of hexes
         */
        public ArrayList<Hex> checkHexWithDiceValue(int diceValue) 
        {
		ArrayList<Hex>listOfHexes= new ArrayList<>();
                for (Hex hex: board.hexList)
                {
                    if(hex.getTokenValue()==diceValue)
                    {
                        listOfHexes.add(hex);
                    }
                }
                
		return listOfHexes;
	}
	/**
         * this method return the players found in the list of Hexes
         * @param listOfHexes a list of hex to check in
         * @return list of players to assign resources 
         */
	public ArrayList<Player> getPlayersOnHex(ArrayList<Hex> listOfHexes)
	{
		ArrayList<Player>listOfplayers= new ArrayList<>();
		for(Hex Hex:listOfHexes)
                {
                    for(int eachVertex=0;eachVertex<6;eachVertex++){
                        if(Hex.getVerticies().get(eachVertex).getAsset()!=null)
                        {
                            listOfplayers.add(Hex.getVerticies().get(eachVertex).getAsset().getPlayer());
                        }
                    }
                }
		return listOfplayers;
	}
        /**
         * this method check if there is enough resources in the bank and return true if the bank has it.
         * @return boolean value.
         */
	public boolean checkBank(int resource) 
  {                                                   
                                                      
      /*
      resource numbers:
      0 = steel = ore
      1 = glass = brick
      2 = hemp = sheep
      3 = soy = wheat
      4 = plastic = lumber
      */
      int resourceNumber = 0;
      boolean boolBank = false;      
         //needs to be changed   
      
        if(resourceBank.resources[resource] >= 0)
            boolBank = true;
        
      return boolBank;
  }
	/**
         * this method will be call if hexes are found, players are found, and the bank resource return true
         * so it will assign resources to each player in the list.
         * @param listOfHexes list of hex to get resource and assign it to player in it
         */
	public void assignValue(ArrayList<Hex> listOfHexes)
	{
            int resourceNumber;
            //geting value from hex
            for(Hex hex:listOfHexes)
            {
                hex.setFill(Color.WHITE);
                if(checkBank(hex.getTerrainType()))
                {
                    
                    switch(hex.getTerrainType())
                    {             
                        case SOY : 
                             
                             resourceBank.resourceList.get(SOY).drawResource(0);
                             for(int i = 0; i < 6 ; i++) {
                                 if(hex.getVerticies().get(i).getAsset()!=null)
                                  hex.getVerticies().get(i).getAsset().getPlayer().resources[SOY]++;
                             }
                             break;
                        case HEMP : 
                             
                             resourceBank.resourceList.get(HEMP).drawResource(0);
                             for(int i = 0; i < 6 ; i++) {
                                 if(hex.getVerticies().get(i).getAsset()!=null)
                                  hex.getVerticies().get(i).getAsset().getPlayer().resources[HEMP]++;
                             }
                             break;
                        
                        case PLASTIC : 
                             
                             resourceBank.resourceList.get(PLASTIC).drawResource(0);
                             for(int i = 0; i < 6 ; i++) {
                                 if(hex.getVerticies().get(i).getAsset()!=null)
                                  hex.getVerticies().get(i).getAsset().getPlayer().resources[PLASTIC]++;
                             }
                             break;
                        case STEEL : 
                             
                             resourceBank.resourceList.get(STEEL).drawResource(0);
                             for(int i = 0; i < 6 ; i++) {
                                 if(hex.getVerticies().get(i).getAsset()!=null)
                                  hex.getVerticies().get(i).getAsset().getPlayer().resources[STEEL]++;
                             }
                             break;
                             
                             default:
                                System.out.println("INVALID RESOURCE");
                                break; 
                   }
                }
                else
                {
                    System.out.println("Not enough resources for "+ hex.getTerrainType() +" in the Hex "+ hex.getTokenValue());
                }
            }
            
	}

        
    public int getDiceValue() {        
        return diceValue;
    }

    public void setDiceValue(int diceValue) {
        //will get the value of the static dice variable    
        this.diceValue = diceValue;
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
     */public void setHexes(ArrayList<Hex> hexes) {
            this.hexes = hexes;
    }	
    /**
     * this method check and find every Hex on the board that correspond to the dice number
     * and put them in a list
     * @param dn the dice number
     * @return a list of hexes
     */
    public ArrayList<Hex> getProducingHexes(){
            ArrayList<Hex>listOfHexes= new ArrayList<>();
            for(int i=0;i<25;i++)
            {
                if(board.hexList.get(i).getTokenValue()==diceValue)
                {
                    listOfHexes.add(board.hexList.get(i));
                }
            }

            return listOfHexes;
    }    
    public ArrayList<Hex> getHexes() {
            return hexes;
    }
/**
         * this method will set the Hexes list find by retrieveHex() to the variable Hex 
         * @param hexes the list of Hexes find by the method retrieveHex()
         */
    

    public ResourceBank getResourceBank() {
        return resourceBank;
    }

    public void setResourceBank(ResourceBank resourceBank) {
        this.resourceBank = resourceBank;
    }
        
}
  


