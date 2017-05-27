/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package som;

import javafx.scene.paint.Color;

/**
 *
 * @author david
 */
public class Cpu extends Player{
    
    private int victoryPoints;
    private TradePack tradePack= new TradePack(this);
    private String priority;

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    public TradePack getTradePack() {
        return tradePack;
    }

    public void setTradePack(TradePack tradePack) {
        this.tradePack = tradePack;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
       
    Cpu(){
        super();
        assets=new Assets();
        resources= new int[]{0,0,0,0,0}; 
        victoryPoints=0;
        
    }

    Cpu(String name, Color playerColor) {
        super(name,playerColor);
        assets=new Assets();
            nickname=name;
            this.playerColor=playerColor;
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Cpu(String name, int[] resources, Color playerColor) {
        super(name,resources,playerColor);
        this.nickname=name;
        this.resources=resources;
        this.victoryPoints=4;
        assets=new Assets();
        this.playerColor=playerColor;
    }
}
