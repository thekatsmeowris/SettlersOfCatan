package som;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Chadwick J Davis
 * CS 56
 * April-9-2017
 * Purpose: ResourceBank class and Resource class for 
 *          Settlers Of Mars Project. Contains an arrayList of Resource objects
 *          and a simple driver to test the code.
 */

import java.util.*;
import som.assets.City;
import som.assets.Road;
import som.assets.Settlement;



public class ResourceBank {
    private final static int STEEL=0;
    private final static int GLASS=1;
    private final static int HEMP=2;
    private final static int SOY=3;
    private final static int PLASTIC=4;
    private int bankQuantity;
    ArrayList<Resource> resourceList = new ArrayList<>(); //creating an ArrayList of Resource objects
    int [] resources;
    
   
      public ResourceBank()
      {
          bankQuantity = 19;
          resources=new int[]{19,19,19,19,19};
          Resource soy = new Resource();    //creating objects from Resource class
          Resource hemp = new Resource();
          Resource plastic = new Resource();
          Resource glass = new Resource();
          Resource steel = new Resource();   
          
          resourceList.add(steel);  //Value 0 in arrayList
          resourceList.add(glass); //Value 1 in arrayList
          resourceList.add(hemp); //Value 2 in ArrayList
          resourceList.add(soy);  //Value 3 in ArrayList
          resourceList.add(plastic);  //Value 4 in arrayList
                    
      }
            public ResourceBank(int qty)
      {
          bankQuantity = qty;
          resources=new int[]{qty,qty,qty,qty,qty};
          Resource soy = new Resource();    //creating objects from Resource class
          Resource hemp = new Resource();
          Resource plastic = new Resource();
          Resource glass = new Resource();
          Resource steel = new Resource();          
                              
          //Adding Resource objects to resourceList
          resourceList.add(steel);  //Value 0 in arrayList
          resourceList.add(glass); //Value 1 in arrayList
          resourceList.add(hemp); //Value 2 in ArrayList
          resourceList.add(soy);  //Value 3 in ArrayList
          resourceList.add(plastic);  //Value 4 in arrayList
                    
      }
           
      public void bankDrawResource(int resourceNumber, int amount){
          
          if((resources[resourceNumber]-amount)>=0){
                    resources[resourceNumber]-=amount;
          }
          else System.out.println("No more of desired resource");
      }
      
      public void bankReturnResource(int resourceNumber, int amount){
          if((resources[resourceNumber]+amount)<=bankQuantity){
                    resources[resourceNumber]+=amount;
          }
          else System.out.println("Too many of desired resource");
      }
      public void printResourceList() //method to print out resourceList
      { 
          //String resourceName = new String(); //String variable to keep track of resource Name
          System.out.println(resources);

      }
    
    public static void driver() {
        
        ResourceBank TheResourceBank = new ResourceBank();
                       
        
        TheResourceBank.bankDrawResource(SOY, 15);
        TheResourceBank.bankDrawResource(PLASTIC, 2);
        
        TheResourceBank.bankDrawResource(SOY, 1);
        TheResourceBank.bankReturnResource(SOY, 16);
        
        
        
        
        TheResourceBank.bankReturnResource(GLASS, 1);
        
        TheResourceBank.printResourceList();   
        
    }
    public int[] getResourceCost(Settlement settlement){
        return new int[]{0,1,1,1,1};
    }
    public int[] getResourceCost(Road road){
        return new int[]{0,1,0,0,1};
    }
    public int[] getResourceCost(City city){
        return new int[]{3,0,0,2,0};
    }
    public int[] getResourceCost(DevelopmentCard devCard){
        return new int[]{1,0,1,1,0};
    }

    void bankReturnResource(int[] resourceCost, Player player) {
        for (int i=0; i<resources.length; i++){
            player.setResource(i,player.getResources()[i]-resourceCost[i]);
            resources[i]+=resourceCost[i];
            
        }
    }
} 

