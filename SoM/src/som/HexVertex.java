/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package som;

import java.util.ArrayList;
import java.util.Objects;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import som.assets.Asset;


/**
 *
 * @author makogenq
 */
public class HexVertex extends Circle {
    private Point2D position;
    private ArrayList<Hex> adjacentHex;
    private ArrayList<HexEdge> adjacentEdge;
    private Asset asset;
    private Hex parentHex;

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public ArrayList<Hex> getAdjacentHex() {
        return adjacentHex;
    }

    public void setAdjacentHex(ArrayList<Hex> adjacentHex) {
        this.adjacentHex = adjacentHex;
    }

    public ArrayList<HexEdge> getAdjacentEdge() {
        return adjacentEdge;
    }

    public void setAdjacentEdge(ArrayList<HexEdge> adjacentEdge) {
        this.adjacentEdge = adjacentEdge;
    }

    public Hex getParentHex() {
        return parentHex;
    }

    public void setParentHex(Hex parentHex) {
        this.parentHex = parentHex;
    }

    
    
    public HexVertex(Point2D position) {
        super(position.getX(),position.getY(),10, Color.TRANSPARENT);
        this.position=position;

        //adjacentHex.add(h);
        asset=null;
        adjacentHex=new ArrayList<>();
        adjacentEdge= new ArrayList<>();
        parentHex=null;

    }
    
    // hex vertex takes a location p and a hex h, 
    // the hex is the hex that the vertex belongs to
    public HexVertex(Point2D position, Hex hex) {
        super(position.getX(),position.getY(),10, Color.TRANSPARENT);
        this.position=position;

        //adjacentHex.add(h);
        asset=null;
        adjacentHex=new ArrayList<>();
        adjacentEdge= new ArrayList<>();
        parentHex=hex;

    }
    public void setVertex(Point2D p){
        
    }
    public void setEdge(Point2D p, Point2D q){
        HexEdge hE= new HexEdge(p, q);
    }
    
    public void addAdjacentHex(Hex h){

        adjacentHex.add(h);
        
    }
    
    public Hex getHex(){
        return parentHex;
    }
    public void addSettlement(){
         //asset= new Settlement();
       this.setOnMouseEntered(e ->{
            this.setStroke(Color.GREEN);
            this.setFill(Color.GREEN);
        });
       this.setOnMouseExited(e ->{
            //this.setStroke(Color.TRANSPARENT);
        });
        
    }
    public Asset getAsset(){
        return asset;
    }
    public void addAdjacentEdge(HexEdge hexEdge){
        adjacentEdge.add(hexEdge);
    }

    
    @Override
    public boolean equals(Object o){
        boolean result = false;
        
        // self check
        if (this == o) return true;
        // null check
        if (o == null) return false;
        // type check and cast
        if (getClass() != o.getClass()) return false;
        HexVertex hV = (HexVertex) o;
        // field comparison
    
        return Objects.equals(position, hV.position);
    }
   
    
    // mouse events
    /*
    public void handle(MouseEvent e){
        this.setFill(Color.RED);
    }
    */

    @Override
    public String toString() {
        return "HexVertex{" + "position=" + position + ", adjacentHex=" + adjacentHex + ", adjacentEdge=" + adjacentEdge + ", asset=" + asset + ", \n\tparentHex=" + parentHex + '}';
    }
}
