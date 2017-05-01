package com.som.assets;

public class Settlement extends Asset {

	public Settlement(Player player, HexBoard board, Hex hex, int[] coord) {
		super(player, Asset.SETTLEMENT, board, hex, coord);
	}
	
}
