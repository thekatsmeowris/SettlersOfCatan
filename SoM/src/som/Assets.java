/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package som;

import java.util.ArrayList;
import som.assets.City;
import som.assets.Road;
import som.assets.Settlement;
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
    public void add(Player player, HexVertex hexVertex){
        if (hexVertex.asset==null){
        settlements.add(new Settlement(player, hexVertex));
        }else{
            settlements.remove((Settlement)hexVertex.getAsset());
            cities.add(new City(player, hexVertex));
        }
        
    }
    public void add(Player player, HexEdge hexEdge){ 
        Road road = new Road(player, hexEdge);
        roads.add(road);
    }
   
}