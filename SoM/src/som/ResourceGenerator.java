/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package som;

/**
 *
 * @author David Kapanga
 */
import java.util.ArrayList;

public class ResourceGenerator {
    	private ArrayList<Player> players;
	private ArrayList<Hex> hexes;
	
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	public ArrayList<Hex> getHexes() {
		return hexes;
	}
	public void setHexes(ArrayList<Hex> hexes) {
		this.hexes = hexes;
	}
	
	public int getDN()
	{
		return 0;
	}

	public ArrayList<Hex> retrouveHex(int dn)
	{
		ArrayList<Hex>listOfHexes;
		return listOfHexes;
	}
	
	public ArrayList<Player> checkPlayerOnHex(ArrayList<Hex> listOfHexes)
	{
		ArrayList<Player>listOfplayers;
		
		return listOfplayers;
	}
	public boolean checkBank()
	{
		return true;
	}
	
	public void assignValue()
	{
		
	}
}
