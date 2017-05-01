package som.assets;

public abstract class Asset {

	public final static int ROAD = 0;
	public final static int SETTLEMENT = 1;
	public final static int CITY = 2;
	
	public int assetType;
	
	// Defining the player and the board to which the Asset belongs
	public Player player;
	public HexBoard board;
	public Hex hex;
	public HexVertex hexVertex;
	public HexEdge hexEdge;
	// Defining the coordinate according to the Hex on the HexBoard to which the Asset belongs
	//		-Name/datatype might need editing to match with rest of classes.
	public int[] coord;
	
	

	// Constructor method
	public Asset(Player player, int assetType, HexBoard board, Hex hex, int[] coord) throws IllegalArgumentException {
		if(player == null) {
			throw new IllegalArgumentException("Player cannot be null");
		}
		if(board == null) {
			throw new IllegalArgumentException("Board cannot be null");
		}
		this.player = player;
		this.assetType = assetType;
		this.board = board;
		this.hex = hex;
		this.coord = coord;
	}
	
	
	/** 
	 * Getters 
	 */
	public Player getPlayer() {
		return player;
	}
	
	public HexBoard getHexBoard() {
		return board;
	}
	public Hex getHex() {
		return hex;
	}
	public HexVertex hexVertex() {
		if(this.hexVertex == null) {
			return "Asset not attached to a hex vertex.";
		}
		return hexVertex;
	}
	public HexEdge getHexEdge() {
		if(hexEdge == null) {
			return "Asset not attached to a hex edge.";
		}
		return hexEdge;
	}
	/* Will have to change this method according to Hex board layout */
	public int[] getCoord() {
		return coord;
	}
	/**
	 * End Getters
	 */
	
	
	/**
	 *  !!!Unfinished method: 
	 *  --> I want this method to eventually return some data type that will let the player
	 *  	know which resources are necessary to build an asset.
	 */
	public int[] getResourcesRequired(int assetType) {
		int[] assetArray = new int[5];
		// Assumes => [clay, ore, sheep, wheat, wood] as order (alphabetical)
		switch(assetType) {
		case ROAD:
			assetArray = [1,0,0,0,1];
			break;
		case SETTLEMENT:
			assetArray = [1,0,1,1,1];
		case CITY:
			assetArray = [0,3,0,1,0];
		default:
			throw new IllegalArgumentException("Undefined asset type.");
		return assetArray;
		}
	}
	
	public String assetTypeToString(int assetType) throws IllegalArgumentException {
		switch(assetType) {
		case ROAD:
			return "Road";
		case SETTLEMENT:
			return "Settlement";
		case CITY:
			return "City";
		default:
			throw new IllegalArgumentException("Undefined asset type.");
		}
	}
	
	public String toString() {
		return "Asset type: "+this.assetTypeToString(assetType)+"//Player: "+player+"//Location: "+coord;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		}
		if(!(o instanceof Asset)){
			return false;
		}
		return ((this.assetType == ((Asset)o).assetType) && (this.player == ((Asset)o).player) && (this.coord == ((Asset)o).coord));
	}
}
