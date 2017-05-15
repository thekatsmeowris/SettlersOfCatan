/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package som;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;


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
   private ObservableList<Double>hexPoints;
   private Color hexColor;
   private int tokenValue;
   private int terrainType;
   private boolean sandstorming;

    Hex(){
        super(100,100,200,100,200,200,200,300,200,400,100,400,0,400, 0,300, 0,200,0, 100);
        inRadius=this.getLayoutBounds().getWidth();
        circumRadius=this.getLayoutBounds().getHeight();
        centerX=400;
        centerY=300;
        verticies= new ArrayList<>();

        hexColor=Color.BLACK;
        this.setFill(hexColor);
        this.terrainType=5;
    }
    Hex(int i,double cX, double cY, double R, double r, Color hexColor, int tokenValue, int terrainType){
        super(
                cX, cY-R,
//                cX+(Math.round(R*Math.sqrt(3)/2)), cY-(R/2),
                cX+(Math.round(R*Math.sqrt(3)/2)), cY-(R/2),
                cX+(Math.round(R*Math.sqrt(3)/2)), cY+(R/2),
                cX, cY+R,
                cX-(Math.round(R*Math.sqrt(3)/2)), cY+(R/2),
                cX-(Math.round(R*Math.sqrt(3)/2)), cY-(R/2)
        );
        index =i;
        centerX=cX;
        centerY=cY;
        circumRadius=R;
        inRadius=R*(int)(Math.sqrt(3)/2);
        hexPoints=super.getPoints();
        verticies= new ArrayList<>();

        this.hexColor=hexColor;
        this.setFill(hexColor);
        this.tokenValue=tokenValue;
        this.terrainType=terrainType;   
       // makePoints();
       // this.getPoints().clear();
       //     for(int i=0; i<hexPoints.length-1; i++){
       //         super.getPoints().add(hexPoints[i]);
       //     }
      // System.out.println(this.getPoints());
        System.out.println("width: "+ this.getLayoutBounds().getWidth());
       
        
      
       
   
        
    }
    public ArrayList<HexVertex> getVerticies(){
        return verticies;
    }
   public void addVertex(HexVertex hexVertex){
       verticies.add(hexVertex);
   }    
   public void setHexColor(Color hexColor){
       this.hexColor=hexColor;
       this.setFill(hexColor);
   }
   public Color getHexColor(){
       return this.hexColor;
   }
   public void setTerrainType(int terrainType){
       this.terrainType=terrainType;
   }
   public int getTerrainType(){
       return this.terrainType;
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
   public String toString(){
    
    return "Hex: "+index
            +hexPoints+"\n";
}
   

  
    
}
