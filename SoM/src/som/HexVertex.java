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
    Point2D position;
    ArrayList<Hex> adjacentHex;
    ArrayList<HexEdge> adjacentEdge;
    Asset asset;
    Hex parentHex;
    
    
    
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
    
    public Asset getAsset(){
        return asset;
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
    @Override
    public String toString(){
        return "("+position.getX()+", "+position.getY()+")\n";
        
        
    }
    
    // mouse events
    /*
    public void handle(MouseEvent e){
        this.setFill(Color.RED);
    }
    */


}
