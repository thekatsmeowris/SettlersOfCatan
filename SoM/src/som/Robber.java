package som;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Robber is a class that move to desired hexes when a seven is rolled on the
 * die. The Robber can move, take resources, and prevent resources from being
 * generated on a hex.
 * 
 * @author Chadwick J Davis
 */
public class Robber {
	private Player CurrentPlayer;
	private Player pickedPlayer;
	private ArrayList<Player> robberPlayers = new ArrayList<>();
	private ResourceBank resourceBank = new ResourceBank();
	private Hex currentHex;
	private Hex previousHex;
	private HexBoard board;

	// Only available constructor: clones ArrayList<Player> from
	// GameScreenController.players
	public Robber() {
		// robberPlayers = (ArrayList<Player>) GSC.players.clone();
	}

	/*
	 * This method will Activate the Robber which will later activate all the
	 * othe methods Not sure how turn order will work, for now I added a player
	 * argument as a requirement
	 * 
	 * @param players ArrayList of players
	 */
	public void ActivateRobber(Player player, HexBoard board) {
		CurrentPlayer = player;
		this.board = board;
		SevenResourceCheck();
	}

	/*
	 * This method checks each player if they have 7 or more resources and has
	 * them randomly remove half of their resources (rounding down)
	 */
	private void SevenResourceCheck() {
		int randomResourceNumber = 0;
		int resourceCounter = 0;
		for (int i = 0; i < robberPlayers.size(); i++) // for loop runs once for
														// every player
		{
			if (robberPlayers.get(i).countResources() >= 7) // If the current
															// than 7 resource
															// cards...
				resourceCounter = robberPlayers.get(i).countResources() / 2;
			{
				System.out.println("\n" + robberPlayers.get(i).getNickname() + " lost:");
				for (int j = 0; j < resourceCounter; j++) {
					randomResourceNumber = RandomResourceValue(i);
					robberPlayers.get(i).resources[randomResourceNumber]--;
					resourceBank.bankReturnResource(randomResourceNumber, 1);
					System.out.print(" one " + resourceBank.getResourceName(randomResourceNumber));
				} // end of inner for loop
			} // end of inner if statment
		} // end of for loop
		System.out.println(Arrays.toString(resourceBank.resources));
		checkHex(this.getCurrentHex());
	}

	/*
	 * This randomly generates a number bewteen 0 and 4 and checks if the
	 * corresponding player has said resourceNumber in their resources[]
	 */
	private int RandomResourceValue(int playerNumber) {
		ArrayList<Integer> excludedNumbers = new ArrayList<>();
		for (int i = 0; i < CurrentPlayer.resources.length; i++) {
			if (robberPlayers.get(playerNumber).resources[i] == 0)
				excludedNumbers.add(i);
		}
		Random r = new Random();
		int randomVariable = 0;
		randomVariable = r.nextInt(5 - 0) + 0; // IF THERE IS AN INDEX
												// ERROR:THIS MIGHT BE THE
												// CAUSE: This should return a
												// number between 0-4
		while (excludedNumbers.contains(randomVariable)) { // While loop to run
															// until the random
															// number generated
															// is not in the
															// excludedNumber
															// arrayList
			randomVariable = r.nextInt(5 - 0) + 0; // IF THERE IS AN INDEX
													// ERROR:THIS MIGHT BE THE
													// CAUSE: This should return
													// a number between 0-4
		}
		return randomVariable;
	}

	/*
	 * This method allows the current player to move the robber to a desired hex
	 * Use this method for Knight card and use ActivateRobber method is a seven
	 * on the dice are rolled
	 */
	/*
	 * This method checks the specific hex for players
	 */
	private void checkHex(Hex currentHex) {
		ArrayList<Player> listOfPlayers = new ArrayList<>();
		// update hex vertices now
		int vertexCounter = 0;
		for (HexVertex vertex : currentHex.getVerticies()) {
			vertex = board.vertexList.get(board.vertexList.indexOf(vertex));
			if (vertex.getAsset() != null) {
				Player p = vertex.getAsset().getPlayer();
				System.out.println(p.toString());
				listOfPlayers.add(vertex.getAsset().getPlayer());
			}
			vertexCounter++;
		}
		System.out.println("List o' Playas: " + listOfPlayers);
		stealResources(listOfPlayers);
	}

	/*
	 * This method will either remove a random resources from one player and
	 * give it to the current player or will and remove a random resource from
	 * all players on the specific hex and give them to the current player
	 */
	private void stealResources(ArrayList<Player> listOfplayers) {
		int randomResourceNumber = 0;
		randomResourceNumber = RandomResourceValue(0);
		if (listOfplayers.size() == 1) {
			pickedPlayer = listOfplayers.get(0);
			pickedPlayer.resources[randomResourceNumber]--;
			CurrentPlayer.resources[randomResourceNumber]++;
			System.out.println(CurrentPlayer.getNickname() + " stole one "
					+ resourceBank.getResourceName(randomResourceNumber) + " from " + pickedPlayer);
		}
		if (listOfplayers.size() > 1) {
			for (int i = 0; i < listOfplayers.size(); i++) {
				randomResourceNumber = RandomResourceValue(i);
				listOfplayers.get(i).resources[randomResourceNumber]--;
				CurrentPlayer.resources[randomResourceNumber]++;
				System.out.println(
						CurrentPlayer.getNickname() + " stole one " + resourceBank.getResourceName(randomResourceNumber)
								+ " from " + listOfplayers.get(i).getNickname());
			}
		}
	}

	public Hex getPreviousHex() {
		return previousHex;
	}

	public void setPreviousHex(Hex previousHex) {
		this.previousHex = previousHex;
	}

	public Hex getCurrentHex() {
		return currentHex;
	}

	public void setCurrentHex(Hex currentHex) {
		this.currentHex = currentHex;
	}

	public void setPlayerArray(ArrayList<Player> players) {
		this.robberPlayers = players;
	}

	public void setBank(ResourceBank resourceBank) {
		this.resourceBank = resourceBank;
	}
}