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
import javafx.scene.shape.Line;

/**
 *
 * @author makogenq
 */
public class HexEdge extends Line{
    Point2D startPoint, endPoint;
    ArrayList<Hex> adjacentHex;
    ArrayList<HexEdge> adjacentEdge;
    ArrayList<HexVertex> adjacentVertex;
    
    HexEdge(Point2D p, Point2D q){
        super(p.getX(),p.getY(),q.getX(),q.getY());
        super.setStrokeWidth(12);
        super.setStroke(Color.TRANSPARENT);
        startPoint=p;
        endPoint=q;
        
        adjacentHex=new ArrayList<>();
        
    }
    
    public void setVertex(Point2D p){
        
    }
    public void setVertex(HexVertex hV){
        
    }
    public void setHex(Hex h){
        
    }
    
    public void setEdge(Point2D p, Point2D q){
        
    }
    public void setEdge(HexEdge hE){
        
    }

    void addHex(Hex h) {
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
        HexEdge hE = (HexEdge) o;
        // field comparison
    
        return Objects.equals(startPoint, hE.startPoint)&& Objects.equals(endPoint,hE.endPoint)||Objects.equals(startPoint, hE.endPoint)&& Objects.equals(endPoint,hE.startPoint);
    }

 
}
