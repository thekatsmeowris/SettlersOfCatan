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
import som.assets.Asset;
import som.assets.Road;

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
    private HexVertex startVertex, endVertex;
    private ArrayList<Hex> adjacentHex;
    private ArrayList<HexEdge> adjacentEdge;
    private ArrayList<HexVertex> adjacentVertex;
    private Asset asset;
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
    
    
    public void addStartVertex(HexVertex startVertex){
        this.startVertex=startVertex;
    }
    public HexVertex getStartVertex(){
        return startVertex;
    }
    public void addEndVertex(HexVertex endVertex){
        this.endVertex=endVertex;
    }
    public HexVertex getEndVertex(){
        return endVertex;
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
         if(hexVertex.equals(endVertex)){
            System.out.println("END VERTEX IN START VERTEX OUT "+ startVertex);
            
            return startVertex;
        }else if (hexVertex.equals(startVertex)){
            System.out.println("START VERTEX IN END VERTEX OUT "+ endVertex);

            return endVertex;
        }else{
            System.out.println("NO MATCH FOR GETOTHERPOINT");
            return null;
        }
        
        
    }

    public void setEdge(Point2D p, Point2D q){
        
    }
    public void setEdge(HexEdge hE){
        
    }

    public void addHex(Hex h) {
        adjacentHex.add(h);
    }
    
    
    
    
    public Road addRoad(Player player){
        this.owned=true;
        this.owner=player;
        this.asset=new Road(player, this);
        
       this.setOnMouseEntered(e ->{
            this.setStroke(Color.GREEN);
        });
       this.setOnMouseExited(e ->{
            //this.setStroke(Color.TRANSPARENT);
        });
       return (Road) this.asset;    
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
        return "HexEdge{" + "startPoint=" + startPoint + ", endPoint=" + endPoint + ", asset=" + asset + ", owned=" + owned + ", owner=" + owner + '}';
    }

}
