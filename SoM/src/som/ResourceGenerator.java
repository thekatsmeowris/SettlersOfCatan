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
         * this method will set the Hexes list find by retrouveHex() to the variable Hex 
         * @param hexes the list of Hexes find by the method retrouveHex()
         */
	public void setHexes(ArrayList<Hex> hexes) {
		this.hexes = hexes;
	}
	/**
         * this method return the dice number 
         * @return integer  
         */        
	public int getDN()
	{
		return 0;
	}
        /**
         * this method check and find every Hex on the board that correspond to the dice number
         * and put them in a list
         * @param dn the dice number
         * @return a list of hexes
         */
	public ArrayList<Hex> retrouveHex(int dn)
	{
		ArrayList<Hex>listOfHexes;
		return listOfHexes;
	}
	/**
         * this method return the players found in the list of Hexes
         * @param listOfHexes a list of hex to check in
         * @return list of players to assign resources 
         */
	public ArrayList<Player> checkPlayerOnHex(ArrayList<Hex> listOfHexes)
	{
		ArrayList<Player>listOfplayers;
		
		return listOfplayers;
	}
        /**
         * this method check if there is enoungh resources in the bank and return true if the bank has it.
         * @return boolean value.
         */
	public boolean checkBank()
	{
		return true;
	}
	/**
         * this method will be call if hexes are found, players are found and the bank resource return true
         * so it will assign resources to each player in the list.
         */
	public void assignValue()
	{
		
	}
}
