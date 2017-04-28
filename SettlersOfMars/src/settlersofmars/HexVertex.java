/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package settlersofmars;

import java.util.ArrayList;
import java.util.Objects;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author makogenq
 */
public class HexVertex extends Circle {
    Point2D position;
    ArrayList<Hex> adjacentHex;
    ArrayList<HexEdge> adjacentEdge;
    Asset asset;
    
    
    
    
    // hex vertex takes a location p and a hex h, 
    // the hex is the hex that the vertex belongs to
    public HexVertex(Point2D p, Hex h) {
        super(p.getX(),p.getY(),10, Color.TRANSPARENT);
        position=p;
        //adjacentHex.add(h);
        asset=null;
        adjacentHex=new ArrayList<>();
        adjacentEdge= new ArrayList<>();
        
    }
    public void setVertex(Point2D p){
        
    }
    public void setEdge(Point2D p, Point2D q){
        HexEdge hE= new HexEdge(p, q);
    }
    
    public void addHex(Hex h){
        adjacentHex.add(h);
        
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
