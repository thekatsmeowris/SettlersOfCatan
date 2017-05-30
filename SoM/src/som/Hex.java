/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package som;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 *
 * @author makogenq
 */
public class Hex extends Polygon {
    

	// double inRadius, circumRadius, centralAngle, interiorAngle, side;

	private double inRadius, circumRadius;
	private double centerX, centerY;
	private int index;
	private ArrayList<HexVertex> verticies;
	private ArrayList<HexEdge> edges;

	private ObservableList<Double> hexPoints;
	private Color hexColor;
	private int tokenValue;
	private int terrainType;
	private boolean sandstorming;

	private final static int STEEL = 0;
	private final static int GLASS = 1;
	private final static int HEMP = 2;
	private final static int SOY = 3;
	private final static int PLASTIC = 4;

	Hex() {
		super(100, 100, 200, 100, 200, 200, 200, 300, 200, 400, 100, 400, 0, 400, 0, 300, 0, 200, 0, 100);
		inRadius = this.getLayoutBounds().getWidth();
		circumRadius = this.getLayoutBounds().getHeight();
		centerX = 400;
		centerY = 300;
		verticies = new ArrayList<>();

		hexColor = Color.BLACK;
		this.setFill(hexColor);
		this.terrainType = 5;
	}

	Hex(int index, double centerX, double centerY, double circumRadius, double inRadius, Color hexColor, int tokenValue,
			int terrainType) {
		super(centerX, centerY - circumRadius,
				// cX+(Math.round(R*Math.sqrt(3)/2)), cY-(R/2),
				centerX + (Math.round(circumRadius * Math.sqrt(3) / 2)), centerY - (circumRadius / 2),
				centerX + (Math.round(circumRadius * Math.sqrt(3) / 2)), centerY + (circumRadius / 2), centerX,
				centerY + circumRadius, centerX - (Math.round(circumRadius * Math.sqrt(3) / 2)),
				centerY + (circumRadius / 2), centerX - (Math.round(circumRadius * Math.sqrt(3) / 2)),
				centerY - (circumRadius / 2));
		this.index = index;
		this.centerX = centerX;
		this.centerY = centerX;
		this.circumRadius = circumRadius;
		this.inRadius = circumRadius * (int) (Math.sqrt(3) / 2);
		this.hexPoints = super.getPoints();
		this.verticies = new ArrayList<>();
		this.edges = new ArrayList<>();

		this.hexColor = hexColor;
		this.setFill(hexColor);
		this.tokenValue = tokenValue;
		this.terrainType = terrainType;
	}

	public ArrayList<HexVertex> getVerticies() {
		return verticies;
	}

	public void addVertex(HexVertex hexVertex) {
		verticies.add(hexVertex);
	}

	public ArrayList<HexEdge> getEdges() {
		return edges;
	}

	public void setEdges(ArrayList<HexEdge> edges) {
		this.edges = edges;
	}

	public void addEdge(HexEdge hexEdge) {
		edges.add(hexEdge);
	}

	public void setHexColor(Color hexColor) {
		this.hexColor = hexColor;
		this.setFill(hexColor);
	}

	public Color getHexColor() {
		return this.hexColor;
	}

	public void setTerrainType(int terrainType) {
		this.terrainType = terrainType;
	}

	public int getTerrainType() {
		return this.terrainType;
	}

	public String terrainTypeToString() {
		switch (this.getTerrainType()) {
		case STEEL:
			return "Steel";
		case GLASS:
			return "Glass";
		case HEMP:
			return "Hemp";
		case SOY:
			return "Soy";
		case PLASTIC:
			return "Plastic";
		default:
			return "Error";
		}
	}

	public double getInRadius() {
		return inRadius;
	}

	public void setInRadius(double inRadius) {
		this.inRadius = inRadius;
	}

	public double getCircumRadius() {
		return circumRadius;
	}

	public void setCircumRadius(double circumRadius) {
		this.circumRadius = circumRadius;
	}

	public double getCenterX() {
		return centerX;
	}

	public void setCenterX(double centerX) {
		this.centerX = centerX;
	}

	public double getCenterY() {
		return centerY;
	}

	public void setCenterY(double centerY) {
		this.centerY = centerY;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public ObservableList<Double> getHexPoints() {
		return hexPoints;
	}

	public void setHexPoints(ObservableList<Double> hexPoints) {
		this.hexPoints = hexPoints;
	}

	public int getTokenValue() {
		return tokenValue;
	}

	public void setTokenValue(int tokenValue) {
		this.tokenValue = tokenValue;
	}

	public boolean isSandstorming() {
		return sandstorming;
	}

	public void setSandstorming(boolean sandstorming) {
		this.sandstorming = sandstorming;
	}

	@Override
	public String toString() {

		return "Hex: " + index + hexPoints + "\n";
	}
}