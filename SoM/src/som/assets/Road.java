package som.assets;
import javafx.geometry.Point2D;
import som.Hex;
import som.HexEdge;
import som.Player;

public class Road extends Asset {

    private final Player player;
    private final Hex hex;
    private final HexEdge hexEdge;

    /*public Road(Player player, HexBoard board, Hex hex, int[] coord) {
    super(player, Asset.ROAD, board, hex, coord);
    }*/

    /**
     *
     * @param player
     * @param hexEdge
     */
    public Road(Player player, HexEdge hexEdge) {
        super (player, hexEdge.getHex());
        this.player = player;      
        this.hex = hexEdge.getHex();
        this.hexEdge = hexEdge;
    }
        public int getType(){
        return 0;
    }
}
