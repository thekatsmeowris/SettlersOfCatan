package som.assets;
import som.HexVertex;
import som.Player;

public class Settlement extends Asset {

    private HexVertex hexVertex;
    //hexVertex holds the 2dPoint position

    public HexVertex getHexVertex() {
        return hexVertex;
    }

    public void setHexVertex(HexVertex hexVertex) {
        this.hexVertex = hexVertex;
    }



    /*public Settlement(Player player, HexBoard board, Hex hex, int[] coord) {
    super(player, Asset.SETTLEMENT, board, hex, coord);
    }
    */
/*
        Settlement should contain a few values
        the assetType should be registered from the actual object type. there need be no variable dictating what is implicit in the construction of the object
        the board variable doesn't need to be used, as there is only one board which can be determined from parent accessors
        the int[] coord array need not be used as the Point2D has more functionality and is simpler to use, also roads have 2 for determining location
        
        all that would be helpful would be the player, the hex and, the hexvertex or hexedge
        */
        
        
    public Settlement(Player player, HexVertex hexVertex ) {
        super (player, hexVertex.getHex());
        this.player = player;      
        this.hex = hexVertex.getHex();
        this.hexVertex= hexVertex;
        

    }
    @Override
    public int getType(){
        return 1;
    }
        
}
