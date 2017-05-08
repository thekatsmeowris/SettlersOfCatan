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
    int [] resourcesOffered;
    int [] resourcesRequested;
    
        TradePack(){
            tradeSingle=false;
            tradeBank=false;
            resourcesOffered=new int[]{0,0,0,0,0};
            resourcesRequested=new int[]{0,0,0,0,0};

        }
        TradePack(Player p1, Player p2, int[] resourcesOffered, int[] resourcesRequested){
            sender=p1;
            receiver=p2;
            this.resourcesOffered=resourcesOffered;
            this.resourcesRequested=resourcesRequested;
            
        }
        
    @Override
    public String toString(){
        return "SENDER: "+ sender.nickname
        +"RECEIVER: "+ receiver.nickname
        +"OFFERING: "+ resourcesOffered.toString()
        +"Requesting: "+ resourcesRequested.toString();
    }

}
