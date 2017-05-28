    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package som;

import java.awt.Point;
import java.io.IOException;
import static java.lang.System.gc;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import java.util.Collections;
import java.util.List;
import javafx.animation.Animation;
import javafx.geometry.Pos;
import static javafx.geometry.Pos.CENTER;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

<<<<<<< HEAD
<<<<<<< HEAD

=======
>>>>>>> master
=======
>>>>>>> master
import javafx.scene.shape.Shape;
import javafx.util.Duration;

/**
 *
 * @author makogenq
 */
public class HexBoard {
    
   Image marsImage,spriteImage; //Image of Mars and sprite sheet of tornado
    private static final int SPRITE_COLUMNS  =   5;
    private static final int SPRITE_COUNT    =  5;
    private static final int SPRITE_OFFSET_X =  0;
    private static final int SPRITE_OFFSET_Y =  2;
    private static final int SPRITE_WIDTH    = 95;
    private static final int SPRITE_HEIGHT   = 168;
    

	public Color[] getColorPalette() {
		return colorPalette;
	}

	// get longest row
	// build board based on longest row
	public void setColorPalette(Color[] colorPalette) {
		this.colorPalette = colorPalette;
	}
	// ie 5 builds 5 rows of 3,4,5,4,3

	// These lists keep track of the components that make up the board
	ArrayList<Hex> hexList; // the list of hexes in this board
	ArrayList<HexVertex> vertexList; // the list of vertices in this board
										// (removing duplicates)
	ArrayList<HexEdge> edgeList; // the list of edges in this board (removing
									// duplicates)

	int maxColumns = 5; // default is 5 for catan: this is the maximum number of
						// hexes allowed in the longest row
	int numberOfRows = 5; // this value is for the maximum number of rows.
	double currentX, currentY;
	StackPane boardShell; // this is the stackpane that merges the board, the
							// vertices, and the edges together
	Pane boardPane; // this is what contains the hexes once they're each drawn

	static Pane numberPane;
	Text t1;

	Pane vertexPane; // this contains the vertices once they're each drawn
	Pane edgePane; // this contains the edges once they're each drawn
	Pane popUpDialog = new Pane(); // this is the pane that appears once a
									// vertex or edge is clicked.

	int columnMax; // this is the maximum number of columns for a row...this is
					// incremented and decremented to yield the 3,4,5,4,3 row
					// pattern
	double hexRadius, inRadius; // this is the default circumradius and inradius
								// of all hexes
	float centerX, centerY; // this is the center of each hex when it is drawn

	// This is the palette for colors to be used in the generation of the board.
	// Also, below this color palette should be a secondary one used for
	// debugging the board
	Color[] colorPalette = new Color[] { Color.web("#7EA6C4"), // light blueish
																// color 0
			Color.web("#C16161"), // abse REDDISH Color 1
			Color.web("#EED79B"), // pale yellow 2
			Color.web("#AFBDA8"), // dull green
			Color.web("#AE9C9E"), // reddish GRAY 4
			Color.web("#000000") }; // black null color 5
	/*
	 * Color[] colorPalette= new Color[]{ Color.VIOLET, Color.BLUE, Color.GREEN,
	 * Color.YELLOW, Color.ORANGE, Color.web("#000000")}; //black null color 5
	 */

	List<Integer> possibleTokens = new ArrayList<Integer>(); // array of
																// possible
																// values for
																// each hex's
																// dice value
	int[] temp = new int[] { // tokenValue numbers possible
			2, 3, 3, 4, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 10, 11, 11, 12 };
	int[] possibleTerrainTypes = new int[] { 3, 3, 3, // hemp = grain
														// distribution
			3, 4, 4, 4, // plastic = lumber distribution
			4, 2, 2, 2, 2, // soy = pasture
			0, 0, 0, 1, // steel = ore
			1, 1 }; // brick = glass

	Stack tokenStack = new Stack();
	Stack terrainStack = new Stack();

	public HexBoard() {
		// int[] boardBoundaries = new int[]{0.0,2,3,4};
		// 800x600
		columnMax = maxColumns - 2; // this sets the max columns of the first
									// row to 2 less than the longest (median)
									// row
									// i needed this because i had a hard time
									// generating the radii of each in the
									// constructor
		Hex modelHex = new Hex(0, 400, 300, 50, 50 * 0.87, Color.ALICEBLUE, 0, 5); // this
																					// generates
																					// a
																					// hex
																					// as
																					// a
																					// model
																					// to
																					// generate
																					// the
																					// rest
																					// of
																					// the
																					// board
																					// hexes
																					// from
		// since i needed to call super() before determining any of the hex's
		// values
		hexRadius = modelHex.getLayoutBounds().getHeight() / 2; // this makes
																// the
																// circumradius
																// which is half
																// the height
																// (of a pointy
																// top hexagon)
		inRadius = modelHex.getLayoutBounds().getWidth() / 2; // this makes the
																// inRadius
																// which is
																// roughly the
																// circumradius
																// * (sqrt(3)/2)
																// but
																// 1/2*getwidth
																// is the same
																// and it's
																// prettier
		boardPane = new Pane(); // creates new board for boardpane
		numberPane = new Pane();
		numberPane.setRotate(30);
		vertexPane = new Pane(); // creates new board for vertexpane

		centerX = (float) (boardPane.getWidth() / 2); // assigns the center of
														// the pane a value
		centerY = (float) (boardPane.getHeight() / 2);

		hexList = new ArrayList<>(); // initializes the hex,vertex, and edge
										// lists
		vertexList = new ArrayList<>();
		edgeList = new ArrayList<>();
		boardShell = new StackPane(); // creates the stackpane for boardshell
		makeBoard(); // generates a board of hexes using random distribution of
						// terrain (does not assign dice values yet)
		makeVertices(); // generates a set of verticies based on the hexes and
						// vertexList
		makeEdges(); // generates a set of edges based on the hexes, vertexList
						// and edgeList
		addAdjacentEdges();
		// addAdjacentVerticies();
		// addAdjacentHexes();
		//

		vertexPane.setPickOnBounds(false); // sets property to false so that the
											// circle (vertex point) is
											// selectable and the bounding shape
											// around it is not
		edgePane.setPickOnBounds(false);

		for (int i = 0; i < numberPane.getChildren().size(); i++) {
			System.out.println(numberPane.getChildren().get(i));
		}

		boardShell.getChildren().addAll(boardPane, numberPane, edgePane, vertexPane); // adds
		// the
		// 3
		// panes
		// to
		// the
		// stackpane
		// and
		// so
		// publishes
		// the
		// constructed
		// board.

	}

	private void makeBoard() { // makes the board with the hexes,
		int hexCounter = 0; // hexCounter keeps track of which hex is being
							// genrated
		Hex h; // a model hex to build the rest on
		double hexStartingPointY = 0; // the hex tarting point, to determine the
										// verticies of the hex
		// MAKE number tokens
		for (int j = 0; j < temp.length; j++) { // adding the diceNumber values
												// and the terrain types to
												// stacks for distribution
			tokenStack.push(temp[j]);
			terrainStack.push(possibleTerrainTypes[j]);
		}
		Collections.shuffle(tokenStack); // shuffle the stacks for randomness
		Collections.shuffle(terrainStack);

		for (int i = 0; i < numberOfRows; i++) {
			if (i > 0) {
				h = (Hex) boardPane.getChildren().get(hexCounter - 1);

				hexStartingPointY = h.getPoints().get(5) + hexRadius;
			} else {
				hexStartingPointY = 200 + ((inRadius / 2) + hexRadius) * i;
			}
			for (int j = 0; j < columnMax; j++) {

				Color hexColor; // Declare the variable for the color of each
								// hex

				hexColor = colorPalette[(int) terrainStack.peek()]; // get the
																	// color
																	// based on
																	// the type
																	// of
																	// terrain
																	// the hex
																	// will be

				if (i == 2 && j == 2) { // the center hex is the "desert hex"
					h = new Hex(hexCounter, 200 + (inRadius * (maxColumns - columnMax)) + (inRadius * j * 2),
							hexStartingPointY, hexRadius, inRadius, Color.BLACK, 0, 5);
				} else {
					h = new Hex(hexCounter, 200 + (inRadius * (maxColumns - columnMax)) + (inRadius * j * 2),
							hexStartingPointY, hexRadius, inRadius, hexColor, (int) tokenStack.pop(),
							(int) terrainStack.pop());
					// *********************************************************
					// NEW CODE 5/27/2017 for Numbers and Terrain Type on Board
					// --> Needs cleaning
					Circle circle = new Circle(25);
					circle.setFill(Color.ANTIQUEWHITE);
					circle.setLayoutX(205 + (inRadius * (maxColumns - columnMax)) + (inRadius * j * 2));
					circle.setLayoutY(hexStartingPointY);
					numberPane.getChildren().add(circle);
					t1 = new Text(20, 20, "" + h.terrainTypeToString() + "\n" + h.getTokenValue());
					t1.setLayoutX(170 + (inRadius * (maxColumns - columnMax)) + (inRadius * j * 2));
					t1.setLayoutY(hexStartingPointY - inRadius + 20);
					t1.setRotate(270);
					t1.setTextAlignment(TextAlignment.CENTER);
					numberPane.getChildren().add(t1);
					numberPane.setRotate(0);
					// End New Code 5/27/2017
					// **********************************************************
				}

				hexCounter++;

				boardPane.getChildren().add(h);

				hexList.add(h);
			}
			if (i < 2) {

				columnMax++;
			} else {
				columnMax--;
			}
		}

	}

	private void makeVertices() { // makes the vertices for the board

		Double[] points; // declare an array for the points for the vertices
		// these are simply two different types of forEach loops

		for (Hex h : hexList) {
			int j = 0;
			h.setOnMouseEntered(e -> {
				System.out.println("TERRAIN TYPE: " + h.terrainTypeToString() + "; Token Value: " + h.getTokenValue());
			});
			for (int i = 0; i < h.getPoints().size(); i = i + 2) {
				Point2D p = new Point2D(h.getPoints().get(i), h.getPoints().get(i + 1));
				HexVertex hV = new HexVertex(p, h);
				h.addVertex(hV);
				// hV.setFill(colorPalette[j]);
				// j++;
				((Circle) (hV)).setOnMouseEntered(e -> {
					hV.setFill(Color.RED);
				});
				((Circle) (hV)).setOnMouseExited(e -> {

					hV.setFill(Color.TRANSPARENT);

				});

				//
				// hV.setFill(Color.TRANSPARENT);

				// if (hV.equals(hV2))neSystem.out.println(" the two hex's are
				// equal");
				// check if vertexList contains the vertex already
				// check item

				// System.out.println(vertexList.contains(hV));

				boolean inList = vertexList.contains(hV);

				// System.out.println("check 0");
				if (inList) {
					// System.out.println(hV + " already exists");
					vertexList.get(vertexList.indexOf(hV)).addAdjacentHex(h);

				} else {
					// System.out.println("adding vertex: " + hV + "\n");
					vertexList.add(hV);
					// vertexPane.getChildren().add(new
					// Circle(hV.position.getX(),hV.position.getX(),5,Color.BLACK));
					// System.out.println(vertexPane);

					// Circle c= new Circle(hV.position.getX(),
					// hV.position.getY(),5,Color.BLACK);

					// System.out.println(c);
					vertexPane.getChildren().add(hV);

				}

			}
			// boardPane.getChildren().add(vertexPane);

		}
		// System.out.println("Verticies: ");
		// System.out.print(vertexList);
		// System.out.println("Hex List: ");
		// System.out.println(hexList);

	}

	private void makeEdges() {
		edgePane = new Pane();

		ObservableList points;
		for (Hex h : hexList) {

			points = h.getPoints();
			int p3, p4;
			int j = 0;
			// for (int i=0; i< points.size(); i=i+2){
			for (int i = 0; i < points.size(); i = i + 2) {
				p3 = i + 2;
				p4 = i + 3;
				/*
				 * ((Circle) (hV)).setOnMouseEntered(e ->{
				 * hV.setFill(Color.RED); }); /* ((Circle)
				 * (hV)).setOnMouseExited(e ->{ hV.setFill(Color.TRANSPARENT);
				 */

				if (i >= 10) {
					p3 = 0;
					p4 = 1;
				}

				HexEdge hE = new HexEdge(new Point2D((double) points.get(i), (double) points.get(i + 1)),
						new Point2D((double) points.get(p3), (double) points.get(p4))

				);

				((Line) (hE)).setOnMouseEntered(e -> {
					hE.setStroke(Color.BLACK);
				});
				((Line) (hE)).setOnMouseExited(e -> {
					hE.setStroke(Color.TRANSPARENT);
				});
				// hE.setStroke(colorPalette[j]);

				// j++;
				h.addEdge(hE);

				//
				// hV.setFill(Color.TRANSPARENT);

				// if (hV.equals(hV2))neSystem.out.println(" the two hex's are
				// equal");
				// check if vertexList contains the vertex already
				// check item

				boolean inList = edgeList.contains(hE);

				// System.out.println("check 0");
				if (inList) {
					// System.out.println(hE + " already exists");
					edgeList.get(edgeList.indexOf(hE)).addHex(h);

				} else {
                                        edgeList.add(hE);
                

                                        //Circle c= new Circle(hV.position.getX(), hV.position.getY(),5,Color.BLACK);

                                        // System.out.println(c);
                                        edgePane.getChildren().add(hE);

				}

			}

		}
                  int hexCounter=0;
         for (Hex hex: hexList){
             int vertexCounter=0;
             System.out.println("[\t\t\t\t\t"+hexCounter+"\t\t\t\t\t]");
             for(HexVertex vertex: hex.getVerticies()){
                 System.out.print(vertexCounter+"\t\t");
                 System.out.print(vertex +"\n" );                 
                 System.out.println("it's in the list on at index: "+vertexList.indexOf(vertex));
                 System.out.println("BEFORE ASSN: "+ ((boolean)(vertex==vertexList.get(vertexList.indexOf(vertex)))));
                 vertex=vertexList.get(vertexList.indexOf(vertex));
                System.out.println("AFTER ASSN: "+ ((boolean)(vertex==vertexList.get(vertexList.indexOf(vertex)))));

                 vertexCounter++;
             }
             hexCounter++;
         } 
	}

	public Pane getBoardPane() {
		return boardShell;

	}

	private void addAdjacentEdges() {
		for (HexVertex h : vertexList) {
			ArrayList<Hex> hexes = new ArrayList<>();
			for (Hex hex : hexList) {
				if (hex.getVerticies().contains(h)) {
					int vertexIndex, vertexIndexNext, vertexIndexPrev;
					vertexIndex = hex.getVerticies().indexOf(h);
					/*
					 * if(vertexIndex+1>5){ vertexIndexNext=0; }else{
					 * vertexIndexNext=vertexIndex+1; }
					 */
					if (vertexIndex - 1 < 0) {
						vertexIndexPrev = 5;
					} else {
						vertexIndexPrev = vertexIndex - 1;
					}
					if (!h.getAdjacentEdge().contains(hex.getEdges().get(vertexIndex))) {
						h.addAdjacentEdge(hex.getEdges().get(vertexIndex));
					}
					if (!h.getAdjacentEdge().contains(hex.getEdges().get(vertexIndexPrev))) {
						h.addAdjacentEdge(hex.getEdges().get(vertexIndexPrev));
					}
				}
			}

			// System.out.println(vertexList.indexOf(h) + ": " + h + "\n" +
			// h.getAdjacentEdge());

		}
		for (HexEdge h : edgeList) {
			// 1) hexEdge.AdjaentVertices
			HexVertex start, end;
			start = new HexVertex(h.getStartPoint());
			end = new HexVertex(h.getEndPoint());
			if (vertexList.contains(start)) {
				h.addAdjacentVertex(vertexList.get(vertexList.indexOf(start)));
				h.addStartVertex(vertexList.get(vertexList.indexOf(start)));
			}
			if (vertexList.contains(end)) {
				h.addAdjacentVertex(vertexList.get(vertexList.indexOf(end)));
				h.addEndVertex(vertexList.get(vertexList.indexOf(end)));
			}
			// now that adjacent vertices are attached, get the adjacent edges
			// from each

			for (HexVertex v : h.getAdjacentVertex()) {
				for (HexEdge edge : v.getAdjacentEdge()) {
					if (!h.getAdjacentEdge().contains(edge)) {
						if (edge != h) {
							h.addAdjacentEdge(edge);
						}
					}
				}
			}

			/*
			 * for(Hex hex: hexList){ if(hex.getEdges().contains(h)){ int
			 * edgeIndex, edgeIndexNext, edgeIndexPrev;
			 * edgeIndex=hex.getEdges().indexOf(h);
			 */
			/*
			 * if(edgeIndex+1>5){ edgeIndexNext=0; }else{
			 * edgeIndexNext=edgeIndex+1; } if(edgeIndex-1<0){ edgeIndexPrev=5;
			 * }else{ edgeIndexPrev=edgeIndex-1; }
			 */
			/*
			 * if(!h.getAdjacentEdge().contains(hex.getEdges().get(edgeIndex))){
			 * h.addAdjacentEdge(hex.getEdges().get(edgeIndex)); }
			 *//*
				 * if(!h.getAdjacentEdge().contains(hex.getEdges().get(
				 * edgeIndexPrev))){
				 * h.addAdjacentEdge(edgeList.get(edgeList.indexOf(hex.getEdges(
				 * ).get(edgeIndexPrev)))); }
				 * if(!h.getAdjacentEdge().contains(hex.getEdges().get(
				 * edgeIndexNext))){
				 * h.addAdjacentEdge(edgeList.get(edgeList.indexOf(hex.getEdges(
				 * ).get(edgeIndexNext))));
				 * System.out.println(edgeIndexPrev+" "+edgeIndex+" "
				 * +edgeIndexNext+" ");
				 */
			// }
			// }
			// }

			/*
			 * System.out.println(vertexList.indexOf(h)+": " +h+"\n"
			 * +h.getAdjacentEdge());
			 */
		}

	}

	private void addAdjacentHexes() {

	}

}