/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package som;

import java.util.ArrayList;
import javafx.geometry.Point2D;

/**
 *
 * @author makogenq
 */
public class Assets {
    ArrayList<Settlement> settlements;
    ArrayList<Road> roads;
    ArrayList<City> cities;

    public Assets() {
        settlements = new ArrayList<>();
        roads = new ArrayList<>();
        cities = new ArrayList<>();
        
    }
    public void add(HexVertex s){
        Settlement settlement=new Settlement(s.position);
        settlements.add(settlement);
    }
    public void add(HexEdge r){
        Road road= new Road(r.startPoint, r.endPoint);
        roads.add(road);
    }
    public void add(HexVertex c, Settlement s){
        City city=new City();
        settlements.remove(s);
        cities.add(city);
    }
}
