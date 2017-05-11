/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package som;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.collections.ObservableList;
import javafx.scene.shape.*;


/**
 *
 * @author makogenq
 */
public class Hex extends Polygon {
   // double inRadius, circumRadius, centralAngle, interiorAngle, side;
   double inRadius, circumRadius;
   double centerX, centerY;
   public int index;
   ArrayList<HexVertex> verticies;
   ObservableList<Double>hexPoints;
  

    Hex(){
        super(100,100,200,100,200,200,200,300,200,400,100,400,0,400, 0,300, 0,200,0, 100);
        inRadius=this.getLayoutBounds().getWidth();
        circumRadius=this.getLayoutBounds().getHeight();
        centerX=400;
        centerY=300;
        verticies= new ArrayList<>();
    }
    Hex(int i,double cX, double cY, double R, double r){
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


   @Override
   public String toString(){
    
    return "Hex: "+index
            +hexPoints+"\n";
}
   

  
    
}
