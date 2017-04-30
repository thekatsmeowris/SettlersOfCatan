/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package som;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

/**
 *
 * @author makogenq
 */
public class HexBoard {
    //get longest row
    //build board based on longest row
    //ie 5 builds 5 rows of 3,4,5,4,3
    
    ArrayList<Hex> hexList;
    ArrayList<HexVertex> vertexList;
    ArrayList<HexEdge> edgeList;

    int maxColumns=5; //default is 5 for catan
    int numberOfRows=maxColumns;
    int num=5;
    double currentX, currentY;
    StackPane boardShell;
    Pane boardPane;
    Pane vertexPane;
    Pane edgePane;

    int columnMax;
    double hexRadius, inRadius;
    float centerX, centerY;
    
    public HexBoard() {
        //int[] boardBoundaries = new int[]{0.0,2,3,4};
        //800x600
        columnMax=maxColumns-2;
        Hex modelHex= new Hex(0,400,300,50, 50*0.87);
        hexRadius=modelHex.getLayoutBounds().getHeight()/2;
        inRadius= modelHex.getLayoutBounds().getWidth()/2;
        boardPane= new Pane();
        vertexPane= new Pane();
        centerX= (float) (boardPane.getWidth()/2);
        centerY= (float) (boardPane.getHeight()/2);
        hexList = new ArrayList<>();
        vertexList = new ArrayList<>();
        edgeList=new ArrayList<>();
        boardShell= new StackPane();
        makeBoard();
        makeVertices();
        makeEdges();
        //allows for mouseclicks through layers above
        vertexPane.setPickOnBounds(false);
        edgePane.setPickOnBounds(false);
        boardShell.getChildren().addAll(boardPane,edgePane,vertexPane);
//        boardShell.getChildren().addAll(edgePane,vertexPane);
        
    }
    
    private void makeBoard(){
        int hexCounter=0;
        Hex h;
        double hexStartingPointY=0;

        for(int i=0; i<numberOfRows;i++){
             if (i>0){
                   

                    h=(Hex) boardPane.getChildren().get(hexCounter-1);
                    
                    hexStartingPointY=h.getPoints().get(5)+hexRadius;
                }else{
                     hexStartingPointY=200+((inRadius/2)+hexRadius)*i;
                }
            for (int j=0;j<columnMax;j++){
                
                

               
                h= new Hex(hexCounter,
                        200+(inRadius*(maxColumns-columnMax))+(inRadius*j*2),
                       hexStartingPointY,
                        hexRadius, 
                        inRadius);
                hexCounter++;
                boardPane.getChildren().add(h);
                
                float randomNum=(float) Math.random()*10;
                

                if(randomNum>8&&randomNum<10){
                    h.setFill(Color.BLUE);
                }else if(randomNum>6&&randomNum<8){
                    h.setFill(Color.MAROON);
                }else if(randomNum>4&&randomNum<6){
                    h.setFill(Color.YELLOW);
                }else if(randomNum>2&&randomNum<4){
                    h.setFill(Color.AQUAMARINE);
                }else if(randomNum>0&&randomNum<2){
                    h.setFill(Color.CHOCOLATE);
                }
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
                ((Circle) (hV)).setOnMouseEntered(e ->{
                    hV.setFill(Color.RED);
                });
             ((Circle) (hV)).setOnMouseExited(e ->{
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
                    vertexList.get(vertexList.indexOf(hV)).addHex(h);
                    
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
