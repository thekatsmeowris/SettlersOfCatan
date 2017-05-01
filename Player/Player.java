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
		if (assetName.equals("SETTLEMENT"));
		else if (assetName.equals("ROAD"));
		else if (assetName.equals("CITY"));
	}

	public void buy(){
		// resources.bankDrawResource("SOY", 10);
		// resources.printResourceList();
	}

	// Trade to another player with resources
	public void trade(Player p, String giveResourceName, int amountGive, String recieveResourceName, int amountRecieve){
		System.out.println("Before Trade:");
		System.out.println("YOU:");
		System.out.println(resourceManager);
		System.out.println();
		System.out.println("Player:");
		System.out.println(p.resourceManager);
		System.out.println("------------------");
		
		resourceManager.give(giveResourceName, amountGive);
		p.resourceManager.recieve(giveResourceName, amountGive);
		p.resourceManager.give(recieveResourceName, amountRecieve);
		resourceManager.recieve(recieveResourceName, amountRecieve);

		System.out.println("After Trade:");
		System.out.println("YOU:");
		System.out.println(resourceManager);
		System.out.println();
		System.out.println("Player:");
		System.out.println(p.resourceManager);
		System.out.println("------------------");
	}

	public static void driver(){
		Player p1 = new Player();
		Player p2 = new Player();
		p1.trade(p2,"SOY",4,"STEEL",5);
	}

	public static void main(String[] args) {
		Player.driver();
	}
}