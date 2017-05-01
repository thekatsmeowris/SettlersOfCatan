package som.assets;
import javafx.geometry.Point2D;

public class Settlement extends Asset {

	public Settlement(Player player, HexBoard board, Hex hex, int[] coord) {
		super(player, Asset.SETTLEMENT, board, hex, coord);
	}
	



/*    Point2D position;
    
    Settlement(){
        
    }
    
    Settlement(Point2D p){
        position=p;
    }
*/
}
