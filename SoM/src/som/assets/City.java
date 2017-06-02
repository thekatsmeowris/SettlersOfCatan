
package som.assets;

import som.HexVertex;
import som.Player;

public class City extends Asset{

    /*
    public City(Player player, HexBoard board, Hex hex, int[] coord) {
    super(player, Asset.CITY, board, hex, coord);
    }*/

    public City(Player player, HexVertex hexVertex) {
        super (player, hexVertex.getHex());
        this.player = player;      
        this.hex = hexVertex.getHex();
        this.hexVertex= hexVertex;
        

    }
    
    @Override
        public int getType(){
        return 2;
    }
        
	
}
