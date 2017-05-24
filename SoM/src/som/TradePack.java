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
    private int [] resourcesOffered;
    private int [] resourcesRequested;
    
        TradePack(){
            this.sender=null;
            this.receiver=null;
            this.tradeBank=null;
            this.resourcesOffered=null;
            this.resourcesRequested=null;
        }

    public int[] getResourcesOffered() {
        return resourcesOffered;
    }

    public void setResourcesOffered(int[] resourcesOffered) {
        this.resourcesOffered = resourcesOffered;
    }

    public int[] getResourcesRequested() {
        return resourcesRequested;
    }

    public void setResourcesRequested(int[] resourcesRequested) {
        this.resourcesRequested = resourcesRequested;
    }
        TradePack(Player sender){
            this.sender=sender;
            this.receiver=null;
            this.tradeBank=null;
            this.resourcesOffered=null;
            this.resourcesRequested=null;

        }
        TradePack(Player sender, Player receiver, int[] resourcesOffered, int[] resourcesRequested){
            this.sender=sender;
            this.receiver=receiver;
            this.resourcesOffered=resourcesOffered;
            this.resourcesRequested=resourcesRequested;
            
        }
        TradePack(Player sender, int[] resourcesOffered, int[] resourcesRequested){
            this.sender=sender;
            receiver=null;
            this.resourcesOffered=resourcesOffered;
            this.resourcesRequested=resourcesRequested;
        }
        TradePack(TradePack tp){
            this.sender=tp.sender;
            this.receiver=tp.receiver;
            this.resourcesOffered=tp.resourcesOffered;
            this.resourcesRequested=tp.resourcesRequested;
            
        }
    @Override
    public String toString(){
/*        return "SENDER: "+ sender.nickname
        +"RECEIVER: "+ receiver.nickname
        +"OFFERING: "+ Arrays.toString(resourcesOffered)
        +"Requesting: "+ Arrays.toString(resourcesRequested);*/
        String string="";
        string+="SENDER: "+ sender.nickname;
        string+="RECEIVER: "+ receiver.nickname;
        string+="OFFERING: "+ Arrays.toString(resourcesOffered);
        string+="Requesting: "+ Arrays.toString(resourcesRequested);
    return string;
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
