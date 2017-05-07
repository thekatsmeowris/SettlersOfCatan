// Player.java
import java.util.ArrayList;
public class Player {
	// private Assets<asset> assets;
	private ResourceManager resourceManager;
	// private int playerNumber;
	// private int victoryPoints;

	public Player(){
		resourceManager = new ResourceManager();
	}

	public ResourceManager getResourceManager(){
		return resourceManager;
	}

	public void build(String assetName){
		// Still working on 
		if (assetName.equals("SETTLEMENT"));
		else if (assetName.equals("ROAD"));
		else if (assetName.equals("CITY"));
	}

	public void buy(){ 
		// Still working on 
	}

	// Trade to another player with resources
	public void trade(Player p, String giveResourceName, int amountGive, String recieveResourceName, int amountRecieve){
		try {
			resourceManager.give(giveResourceName, amountGive);
			p.resourceManager.recieve(giveResourceName, amountGive);
			p.resourceManager.give(recieveResourceName, amountRecieve);
			resourceManager.recieve(recieveResourceName, amountRecieve);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void driver(){
		Player p1 = new Player();
		Player p2 = new Player();
		System.out.println("Before Trade:");
		System.out.println("YOU:");
		System.out.println(p1.resourceManager);
		System.out.println();
		System.out.println("Player:");
		System.out.println(p2.resourceManager);
		System.out.println("------------------");
		
		p1.trade(p2,"SOY",4,"STEEL",5);

		System.out.println("After Trade:");
		System.out.println("YOU:");
		System.out.println(p1.resourceManager);
		System.out.println();
		System.out.println("Player:");
		System.out.println(p2.resourceManager);
		System.out.println("------------------");
	}

	public static void main(String[] args) {
		Player.driver();
	}
}