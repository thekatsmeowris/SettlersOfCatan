
package som.assets;

public class City extends Asset{

	public City(Player player, HexBoard board, Hex hex, int[] coord) {
		super(player, Asset.CITY, board, hex, coord);
	}
	
}
