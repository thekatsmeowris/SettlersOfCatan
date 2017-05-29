//TODO Eventually move some of the hex-related code (i.e. hex.setStroke) to HexBoard for code clarity/organization
package som;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import static som.GameScreenController.audio;
import som.assets.Asset;

/**
 * The resource generator is a class that will dispatch resource to each player.
 * it will get resources from the bank if the banks has available resources.
 *
 * @author David Kapanga
 * @author Chadwick J Davis
 * @version 1.0
 */
public class ResourceGenerator {

    //set resource constants
    private static final int STEEL = 0;
    private static final int GLASS = 1;
    private static final int HEMP = 2;
    private static final int SOY = 3;
    private static final int PLASTIC = 4;

    private ArrayList<Player> players;
    private ArrayList<Hex> hexes;
    private int diceValue;
    private HexBoard board;
    private ResourceBank resourceBank = new ResourceBank();

    public ResourceGenerator(HexBoard board) {
        this.players = new ArrayList<>();
        this.hexes = new ArrayList<>();
        this.diceValue = 0;
        this.board = board;
    }

    void generateResources(int diceValue) {
        //1 get dice value
        //2 find all hexes with correspondinge dice value
        //3 get all players adjacent to that hex
        //4 get amount of resources to be distributed
        //5 check if the bank has enough
        //6 distribute resources

        hexes = checkHexWithDiceValue(diceValue);
        //1) find all assets on hex
        //assetsOnHex : arrayList
        //2) get players from asset
        //3) check if the bank has enough resources to give each player the proper amount of resources
        //4) give each player the proper amount of resources
        for (Hex hex : hexes) {
            int resourcesToDistribute = 0;
            ArrayList<Asset> assets = new ArrayList<>();
            for (Asset asset : getAssetsOnHex(hex)) {
                //get the total number of resources to be distributed
                resourcesToDistribute += asset.getType();
                assets.add(asset);
                //asset.getPlayer().addResource(resources);
                //player.addResources(resourceBank.bankDrawResource(hex.getTerrainType(),asset.getType()));
            }
            if (checkBank(hex.getTerrainType(), resourcesToDistribute)) {                      //if there are enough resources to give to everyone on the hex, do so
                distributeResources(assets, hex.getTerrainType());
            }

        }

        // players=getPlayersOnHex(hex);
        //for each hex
    }

    /**
     * this method check and find every Hex on the board that correspond to the
     * dice number and put them in a list
     *
     * @param dn the dice number
     * @return a list of hexes
     */
    public ArrayList<Hex> checkHexWithDiceValue(int diceValue) {
        ArrayList<Hex> listOfHexes = new ArrayList<>();
        for (Hex hex : board.hexList) {
            if (hex.getTokenValue() == diceValue) {
                hex.setFill(Color.GOLD);

                listOfHexes.add(hex);
            } else {
                hex.setFill(hex.getHexColor());
            }

        }

        return listOfHexes;
    }

    /**
     * this method return the players found in the list of Hexes
     *
     * @param listOfHexes a list of hex to check in
     * @return list of players to assign resources
     */
    public ArrayList<Player> getPlayersOnHex(Hex hex) {
        ArrayList<Player> listOfplayers = new ArrayList<>();
        //update hex vertices now
        int vertexCounter = 0;
        for (HexVertex vertex : hex.getVerticies()) {
            vertex = board.vertexList.get(board.vertexList.indexOf(vertex));

            if (vertex.getAsset() != null) {
                listOfplayers.add(vertex.getAsset().getPlayer());
            }

            vertexCounter++;
        }

        return listOfplayers;
    }

    public ArrayList<Asset> getAssetsOnHex(Hex hex) {
        ArrayList<Asset> listOfAssets = new ArrayList<>();
        //update hex vertices now
        int vertexCounter = 0;
        for (HexVertex vertex : hex.getVerticies()) {
            vertex = board.vertexList.get(board.vertexList.indexOf(vertex));

            if (vertex.getAsset() != null) {
                listOfAssets.add(vertex.getAsset());
            }

            vertexCounter++;
        }

        return listOfAssets;
    }

    /**
     * this method check if there is enough resources in the bank and return
     * true if the bank has it.
     *
     * @return boolean value.
     */
    public boolean checkBank(int resource, int qty) {
        /*resource numbers:
            0 = steel = ore
            1 = glass = brick
            2 = hemp = sheep
            3 = soy = wheat
            4 = plastic = lumber
         */
        return resourceBank.resources[resource] >= qty;
    }

    /**
     * this method will be call if hexes are found, players are found, and the
     * bank resource return true so it will assign resources to each player in
     * the list.
     *
     * @param listOfHexes list of hex to get resource and assign it to player in
     * it
     */
    public void distributeResources(ArrayList<Asset> assets, int resourceType) {
        //geting value from hex
        for (Asset asset : assets) {
            //1) get the player
            //2) get the number of resources to give (assetType)

            //3) give the player that number of resources of this hex's terrain type
            resourceBank.bankDrawResource(resourceType, asset.getType());
            asset.getPlayer().addResource(resourceType, asset.getType());                       //adds the number of resources equal to the assetType (0 for roads, 1 for settlemts, 2 for cities) to the players resources)

            audio.playClips(resourceType);
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
     *
     * @return Player a list of player object
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * this method set the list of players find around a specific Hex, take an
     * ArrayList of Player type
     *
     * @param players parameter players is a ArrayList of players around the Hex
     */
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    /**
     * this method return an arrayList of Hexes that has been set with the dice
     * number
     *
     * @return hexes the list of hex to find players in
     */
    public void setHexes(ArrayList<Hex> hexes) {
        this.hexes = hexes;
    }

    /**
     * this method check and find every Hex on the board that correspond to the
     * dice number and put them in a list
     *
     * @param dn the dice number
     * @return a list of hexes
     */
    public ArrayList<Hex> getProducingHexes() {
        ArrayList<Hex> listOfHexes = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            if (board.hexList.get(i).getTokenValue() == diceValue) {
                listOfHexes.add(board.hexList.get(i));
            }
        }

        return listOfHexes;
    }

    public ArrayList<Hex> getHexes() {
        return hexes;
    }

    /**
     * this method will set the Hexes list find by retrieveHex() to the variable
     * Hex
     *
     * @param hexes the list of Hexes find by the method retrieveHex()
     */
    public ResourceBank getResourceBank() {
        return resourceBank;
    }

    public void setResourceBank(ResourceBank resourceBank) {
        this.resourceBank = resourceBank;
    }

}
