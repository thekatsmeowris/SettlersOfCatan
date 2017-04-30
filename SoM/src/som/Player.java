/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package som;

/**
 *
 * @author makogenq
 */
class Player {
    int[] resources;    
    String nickname;
    Player(){
        
        resources= new int[]{0,0,0,0,0}; 

    }

    Player(String name) {
        nickname=name;
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Player(String name, int[] res) {
        nickname=name;
        resources=res;

       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
