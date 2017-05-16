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

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
    private Point2D startPoint, endPoint;
    private ArrayList<Hex> adjacentHex;
    private ArrayList<HexEdge> adjacentEdge;
    private ArrayList<HexVertex> adjacentVertex;
    private Boolean owned;
    private Player owner;

    
    HexEdge(Point2D p, Point2D q){
        super(p.getX(),p.getY(),q.getX(),q.getY());
        super.setStrokeWidth(12);
        super.setStroke(Color.TRANSPARENT);
        startPoint=p;
        endPoint=q;
        owned=false;
        owner=null;
        
        adjacentHex=new ArrayList<>();
        adjacentVertex=new ArrayList<>();
        adjacentEdge=new ArrayList<>();
        
    }
    
    
    
    public void addAdjacentVertex(HexVertex hexVertex){
        adjacentVertex.add(hexVertex);
    }
    public void addAdjacentEdge(HexEdge hexEdge){
        
        adjacentEdge.add(hexEdge);
    }    
    public void addAdjacentHex(Hex hex){
        
        adjacentHex.add(hex);
    }        
    public Point2D getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point2D startPoint) {
        this.startPoint = startPoint;
    }

    public Point2D getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point2D endPoint) {
        this.endPoint = endPoint;
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

    public ArrayList<HexVertex> getAdjacentVertex() {
        return adjacentVertex;
    }

    public void setAdjacentVertex(ArrayList<HexVertex> adjacentVertex) {
        this.adjacentVertex = adjacentVertex;
    }

    public Boolean isOwned() {
        return owned;
    }

    public void setOwned(Boolean owned) {
        this.owned = owned;
    }
    
    public void setVertex(Point2D p){
        
    }
    public void setVertex(HexVertex hV){
        
    }
    public void setHex(Hex h){
        
    }
    public Hex getHex(){
        
        return null;
        
    }
    public HexVertex getOtherPoint(HexVertex hexVertex){
        if(hexVertex.getPosition()==startPoint){
            return new HexVertex(endPoint);
        }else{
            return new HexVertex(startPoint);
        }
        
        
    }

    public void setEdge(Point2D p, Point2D q){
        
    }
    public void setEdge(HexEdge hE){
        
    }

    void addHex(Hex h) {
        adjacentHex.add(h);
    }
    void addRoad(Player player){
        this.owned=true;
        this.owner=player;
       this.setOnMouseEntered(e ->{
            this.setStroke(Color.GREEN);
        });
       this.setOnMouseExited(e ->{
            //this.setStroke(Color.TRANSPARENT);
        });
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

    @Override
    public String toString() {
       // return "HexEdge{" + "startPoint=" + startPoint + ", endPoint=" + endPoint + ", adjacentHex=" + adjacentHex + ", adjacentEdge=" + adjacentEdge + ", adjacentVertex=" + adjacentVertex + ", owned=" + owned + '}';
       return "owned: "+owned;
    }

}
