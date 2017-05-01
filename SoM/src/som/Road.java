package som.assets;

public class Road extends Asset {

	public Road(Player player, HexBoard board, Hex hex, int[] coord) {
		super(player, Asset.ROAD, board, hex, coord);
	}
	
}
