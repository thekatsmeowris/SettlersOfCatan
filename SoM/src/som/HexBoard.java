/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package som;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import java.util.Collections;
import java.util.List;

import javafx.scene.shape.Shape;

/**
 *
 * @author makogenq
 */
public class HexBoard {
    //get longest row
    //build board based on longest row
    //ie 5 builds 5 rows of 3,4,5,4,3


    //These lists keep track of the components that make up the board
    ArrayList<Hex> hexList;                 //the list of hexes in this board
    ArrayList<HexVertex> vertexList;        //the list of vertices in this board (removing duplicates)
    ArrayList<HexEdge> edgeList;            //the list of edges in this board (removing duplicates)

    int maxColumns=5;                       //default is 5 for catan: this is the maximum number of hexes allowed in the longest row
    int numberOfRows=5;                     //this value is for the maximum number of rows.
    double currentX, currentY;
    StackPane boardShell;                   //this is the stackpane that merges the board, the vertices, and the edges together
    Pane boardPane;                         //this is what contains the hexes once they're each drawn
    Pane vertexPane;                        //this contains the vertices once they're each drawn
    Pane edgePane;                          //this contains the edges once they're each drawn
    Pane popUpDialog=new Pane();            //this is the pane that appears once a vertex or edge is clicked.

    int columnMax;                          //this is the maximum number of columns for a row...this is incremented and decremented to yield the 3,4,5,4,3 row pattern
    double hexRadius, inRadius;             //this is the default circumradius and inradius of all hexes
    float centerX, centerY;                 //this is the center of each hex when it is drawn
    Color[]  colorPallete= new Color[]{
            Color.web("#7EA6C4"),               //light blueish color           0
            Color.web("#C16161"),               //abse REDDISH Color            1
            Color.web("#EED79B"),               //pale yellow                   2
            Color.web("#BDB7A2"),               //greenish GRAY                 3 
            Color.web("#AE9C9E"),               //reddish GRAY                  4
            Color.web("#000000")};              //black null color              5
    List<Integer> possibleTokens= new ArrayList<Integer>();
    int[] temp= new int[]{                      //tokenValue numbers possible
        2,3,3,
        4,4,5,5,
        6,6,8,8,9,
        9,10,10,11,
        11,12};
    int[] possibleTerrainTypes= new int[]{
        3,3,3,                                  //hemp = grain distribution
        3,4,4,4,                                //plastic = lumber distribution
        4,2,2,2,2,                               //soy = pasture
        0,0,0,1,                                  //steel = ore
        1,1};                                        //brick = glass



    Stack tokenStack= new Stack();
    Stack terrainStack= new Stack();
    
    public HexBoard() {
        //int[] boardBoundaries = new int[]{0.0,2,3,4};
        //800x600
        columnMax=maxColumns-2;                                 //this sets the max columns of the first row to 2 less than the longest (median) row
                                                                //i needed this because i had a hard time generating the radii of each in the constructor
                                                                //since i needed to call super() before determining any of the hex's values
        hexRadius=modelHex.getLayoutBounds().getHeight()/2;     //this makes the circumradius which is half the height (of a pointy top hexagon)
        inRadius= modelHex.getLayoutBounds().getWidth()/2;      //this makes the inRadius which is roughly the circumradius * (sqrt(3)/2) but 1/2*getwidth is the same and it's prettier
        boardPane= new Pane();                                  //creates new board for boardpane
        vertexPane= new Pane();                                 //creates new board for vertexpane
        
        centerX= (float) (boardPane.getWidth()/2);              //assigns the center of the pane a value
        centerY= (float) (boardPane.getHeight()/2);             
                    
        hexList = new ArrayList<>();                            //initializes the hex,vertex, and edge lists
        vertexList = new ArrayList<>();
        edgeList=new ArrayList<>();
        boardShell= new StackPane();                            //creates the stackpane for boardshell
        makeBoard();                                            //generates a board of hexes using random distribution of terrain (does not assign dice values yet)
        makeVertices();                                         //generates a set of verticies based on the hexes and vertexList
        makeEdges();                                            //generates a set of edges based on the hexes, vertexList and edgeList
        //allows for mouseclicks through layers above
        vertexPane.setPickOnBounds(false);                      //sets property to false so that the circle (vertex point) is selectable and the bounding shape around it is not
        edgePane.setPickOnBounds(false);
        
        
        boardShell.getChildren().addAll(boardPane,edgePane,vertexPane);     //adds the 3 panes to the stackpane and so publishes the constructed board.
//        boardShell.getChildren().addAll(edgePane,vertexPane);

    }
    
    private void makeBoard(){
        int hexCounter=0;
        Hex h;
        double hexStartingPointY=0;
        //MAKE number tokens
    for(int j=0; j< temp.length; j++){
        tokenStack.push(temp[j]);
        terrainStack.push(possibleTerrainTypes[j]);
    }
    System.out.println(tokenStack);
    Collections.shuffle(tokenStack);
    Collections.shuffle(terrainStack);
    
    System.out.println(tokenStack);

        
        
        for(int i=0; i<numberOfRows;i++){
             if (i>0){
                   

                    h=(Hex) boardPane.getChildren().get(hexCounter-1);
                    
                    hexStartingPointY=h.getPoints().get(5)+hexRadius;
                }else{
                     hexStartingPointY=200+((inRadius/2)+hexRadius)*i;
                }
            for (int j=0;j<columnMax;j++){
            
            Color hexColor;

            hexColor=colorPallete[(int)terrainStack.peek()];
/*          
            if(randomNum>8&&randomNum<10){
                hexColor=colorPallete[0];
            }else if(randomNum>6&&randomNum<8){
                hexColor=colorPallete[1];
            }else if(randomNum>4&&randomNum<6){
                hexColor=colorPallete[0];
            }else if(randomNum>2&&randomNum<4){
                hexColor=colorPallete[0];
             //   hexColor=(Color.MOSSGREEN);
            }else if(randomNum>0&&randomNum<2){
                hexColor=Color.web("#AE9C9E");
             //   hexColor=(Color.CHOCOLATE);
            }else{
                hexColor=Color.web("#000000");
             //   hexColor=Color.BLACK;
            }*/
            //h.setHexColor(hexColor);
    
                

               if(i==2&&j==2){
                h= new Hex(hexCounter,
                        200+(inRadius*(maxColumns-columnMax))+(inRadius*j*2),
                       hexStartingPointY,
                        hexRadius, 
                        inRadius,
                        Color.BLACK, 0, 5);   
               }else{
                h= new Hex(hexCounter,
                        200+(inRadius*(maxColumns-columnMax))+(inRadius*j*2),
                       hexStartingPointY,
                        hexRadius, 
                        inRadius,
                        hexColor, (int) tokenStack.pop(), (int) terrainStack.pop());
               }
                
                hexCounter++;
                boardPane.getChildren().add(h);
                
                hexList.add(h);
            }
        if(i<2){
            columnMax++;
        }else{
            columnMax--;
        }
    }
    
}
   private void makeVertices(){
      
       Double[] points;
        //these are simply two different types of forEach loops
        
        for(Hex h:  hexList){
            for (int i=0; i< h.getPoints().size(); i=i+2){
                Point2D p= new Point2D(h.getPoints().get(i), h.getPoints().get(i+1));
                
                HexVertex hV= new HexVertex(p, h);
                h.addVertex(hV);
                ((Circle) (hV)).setOnMouseEntered(e ->{
                    hV.setFill(Color.RED);
                });
             ((Circle) (hV)).setOnMouseExited(e ->{
                 /*  if(popUpDialog.isVisible()){
                 hV.setFill(Color.MAGENTA);
                 
                 }else{
                 hV.setFill(Color.TRANSPARENT);
                 }*/
                hV.setFill(Color.TRANSPARENT);

                });
             
                
                //
                    //hV.setFill(Color.TRANSPARENT);
                
                
               // if (hV.equals(hV2))neSystem.out.println(" the two hex's are equal");
                //check if vertexList contains the vertex already
                //check item
                
                System.out.println(vertexList.contains(hV));

                boolean inList=vertexList.contains(hV);
                
                
                
                System.out.println("check 0");
                if (inList){
                    System.out.println(hV+" already exists");
                    vertexList.get(vertexList.indexOf(hV)).addAdjacentHex(h);
                    
                }else{
                    System.out.println("adding vertex: "+hV+"\n");
                    vertexList.add(hV);
                    //vertexPane.getChildren().add(new Circle(hV.position.getX(),hV.position.getX(),5,Color.BLACK));
                    System.out.println(vertexPane);
                    
                    //Circle c= new Circle(hV.position.getX(), hV.position.getY(),5,Color.BLACK);
                    
                   // System.out.println(c);
                    vertexPane.getChildren().add(hV);
                    
                }
                

            }
        //boardPane.getChildren().add(vertexPane);
        
   }
        System.out.println("Verticies: ");
        System.out.print(vertexList);
        System.out.println("Hex List: ");
        System.out.println(hexList);

}
       
   
   
   private void makeEdges(){
       edgePane=new Pane();
       

       ObservableList points;
        for(Hex h:  hexList){
        
            points=h.getPoints();
            int p3,p4;
            
//            for (int i=0; i< points.size(); i=i+2){
            for (int i=0; i<points.size(); i=i+2){
                p3=i+2;
                p4=i+3;
               /*
                ((Circle) (hV)).setOnMouseEntered(e ->{
                    hV.setFill(Color.RED);
                });
                /*
             ((Circle) (hV)).setOnMouseExited(e ->{
                    hV.setFill(Color.TRANSPARENT);*/
                
                if (i>=10){
                    p3=0;
                    p4=1;
                }
                HexEdge hE = new HexEdge(
                        new Point2D((double) points.get(i),(double) points.get(i+1)),
                        new Point2D((double) points.get(p3),(double) points.get(p4))
                        
                        );
                
               ((Line) (hE)).setOnMouseEntered(e ->{
                    hE.setStroke(Color.BLACK);
                });
               ((Line) (hE)).setOnMouseExited(e ->{
                    hE.setStroke(Color.TRANSPARENT);
                });
                   
            
             
                
                //
                    //hV.setFill(Color.TRANSPARENT);
                
                
               // if (hV.equals(hV2))neSystem.out.println(" the two hex's are equal");
                //check if vertexList contains the vertex already
                //check item
                

                boolean inList= edgeList.contains(hE);
                
                
                
                System.out.println("check 0");
                if (inList){
                    System.out.println(hE+" already exists");
                    edgeList.get(edgeList.indexOf(hE)).addHex(h);
                    
                }else{
                    System.out.print(h);

                    System.out.println("adding edge: "+hE+"\n");
                    edgeList.add(hE);
                
                    
                    //Circle c= new Circle(hV.position.getX(), hV.position.getY(),5,Color.BLACK);
                    
                   // System.out.println(c);
                    edgePane.getChildren().add(hE);
                    
                }
                

            }
   
   
   
   
   
   
   }
}
    
   public Pane getBoardPane(){
       return boardShell;
       
   }
    
    
    
    
}
