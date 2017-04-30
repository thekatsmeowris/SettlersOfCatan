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
class TradePack {
    Player sender, receiver;
    Boolean tradeSingle;    
    Boolean tradeBank;
    int [] resources;
        TradePack(){
            tradeSingle=false;
            tradeBank=false;
            resources=new int[]{0,0,0,0,0};
        }
        TradePack(Player p1, Player p2, int[] resources){
            sender=p1;
            receiver=p2;
            this.resources=resources;
        }

}
