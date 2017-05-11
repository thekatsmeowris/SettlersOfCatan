/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package som;

import java.util.Arrays;

/**
 *
 * @author makogenq
 */
class TradePack {
    private Player sender, receiver;
    Boolean tradeBank;
    int [] resourcesOffered;
    int [] resourcesRequested;
    
        TradePack(){
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
        TradePack(Player p1, int[] resourcesOffered, int[] resourcesRequested){
            sender=p1;
            receiver=null;
            this.resourcesOffered=resourcesOffered;
            this.resourcesRequested=resourcesRequested;
        }
    @Override
    public String toString(){
        return "SENDER: "+ sender.nickname
        +"RECEIVER: "+ receiver.nickname
        +"OFFERING: "+ Arrays.toString(resourcesOffered)
        +"Requesting: "+ Arrays.toString(resourcesRequested);
    }


    public void setReceiver(Player receiver) {
        this.receiver=receiver;
    }
    public Player getReceiver(){
        return receiver;
    }
    public void setSender(Player sender) {
        this.receiver=receiver;
    }
    public Player getSender(){
        return sender;
    }
    

}
