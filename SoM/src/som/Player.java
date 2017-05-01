/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package som;

import java.util.ArrayList;

/**
 *
 * @author makogenq
 */
class Player {
    int[] resources;
    int victoryPoints;
    String nickname;
    Assets assets;
    Player(){
        assets=new Assets();
        resources= new int[]{0,0,0,0,0}; 
        victoryPoints=0;
        
    }

    Player(String name) {
        assets=new Assets();
            nickname=name;
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Player(String name, int[] res) {
        nickname=name;
        resources=res;
        victoryPoints=4;
        assets=new Assets();
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
