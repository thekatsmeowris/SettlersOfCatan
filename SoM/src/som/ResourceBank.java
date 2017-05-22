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
    private static int STEEL=0;
    private static int GLASS=1;
    private static int HEMP=2;
    private static int SOY=3;
    private static int PLASTIC=4;
    ArrayList<Resource> resourceList = new ArrayList<>(); //creating an ArrayList of Resource objects
    int [] resources;
      public ResourceBank()
      {
          resources=new int[]{19,19,19,19,19};
          Resource soy = new Resource();    //creating objects from Resource class
          Resource hemp = new Resource();
          Resource plastic = new Resource();
          Resource glass = new Resource();
          Resource steel = new Resource();          
                              
          //Adding Resource objects to resourceList
          resourceList.add(soy);  //Value 0 in arrayList
          resourceList.add(hemp); //Value 1 in arrayList
          resourceList.add(plastic); //Value 2 in ArrayList
          resourceList.add(glass);  //Value 3 in ArrayList
          resourceList.add(steel);  //Value 4 in arrayList
                    
      }
            public ResourceBank(int qty)
      {
          resources=new int[]{qty,qty,qty,qty,qty};
          Resource soy = new Resource();    //creating objects from Resource class
          Resource hemp = new Resource();
          Resource plastic = new Resource();
          Resource glass = new Resource();
          Resource steel = new Resource();          
                              
          //Adding Resource objects to resourceList
          resourceList.add(soy);  //Value 0 in arrayList
          resourceList.add(hemp); //Value 1 in arrayList
          resourceList.add(plastic); //Value 2 in ArrayList
          resourceList.add(glass);  //Value 3 in ArrayList
          resourceList.add(steel);  //Value 4 in arrayList
                    
      }
    
      public void bankDrawResource(String resourceName, int amount) //Method requires a correct string variable: "SOY", "HEMP", "PLASTIC", "GLASS", or "STEEL" and an int variable to control how many of said resource
      {                                                             ////Method uses resourceList to call Resource.returnResource(amount) with correct Resource name
         int resourceNumber;
         
         switch(resourceName) //Switch statment to control resource name: The number corresponding to the resources in the arrayList are mentioned above
         {
             case "SOY" :
                 resourceNumber = 0;
                 resourceList.get(resourceNumber).drawResource(amount);
                 break;
             case "HEMP" :
                 resourceNumber = 1;
                 resourceList.get(resourceNumber).drawResource(amount);
                 break;
              case "PLASTIC" :
                 resourceNumber = 2;
                 resourceList.get(resourceNumber).drawResource(amount);
                 break;
             case "GLASS" :
                 resourceNumber = 3;
                 resourceList.get(resourceNumber).drawResource(amount);
                 break;
             case "STEEL" :
                 resourceNumber = 4;
                 resourceList.get(resourceNumber).drawResource(amount);
                 break;
             default:
                 System.out.println("INVALID RESOURCE");
                 break;                
         }
       }
      
      public void bankDrawResource(int resourceNumber, int amount){
          
          if((resources[resourceNumber]-amount)>=0){
                    resources[resourceNumber]-=amount;
          }
      }
      public void bankReturnResource(String resourceName,int amount) //Method requires a correct string variable: "SOY", "HEMP", "PLASTIC", "GLASS", or "STEEL" and an int variable to control how many of said resource
      {                                                              //Method uses resourceList to call Resource.returnResource(amount) with correct Resource name
          int resourceNumber;
         switch(resourceName) //Switch statment to control resource name: The number corresponding to the resources int the arrayList are mentioned assigned above
         {
             case "SOY" :
                 resourceNumber = 0;
                 resourceList.get(resourceNumber).returnResource(amount);
                 break;
             case "HEMP" :
                 resourceNumber = 1;
                 resourceList.get(resourceNumber).returnResource(amount);
                 break;
              case "PLASTIC" :
                 resourceNumber = 2;
                 resourceList.get(resourceNumber).returnResource(amount);
                 break;
             case "GLASS" :
                 resourceNumber = 3;
                 resourceList.get(resourceNumber).returnResource(amount);
                 break;
             case "STEEL" :
                 resourceNumber = 4;
                 resourceList.get(resourceNumber).returnResource(amount);
                 break;
             default:
                 System.out.println("INVALID RESOURCE:"+ resourceName);
                 break;                
         }
      }
      public void bankReturnResource(int resourceNumber, int amount){
          //if((resources[resourceNumber]-amount)>=0){
                    resources[resourceNumber]+=amount;
          //}
      }
        public void bankReturnResource(int[] resources){
          //if((resources[resourceNumber]-amount)>=0){
          for (int i=0; i<this.resources.length;i++){
                    this.resources[i]+=resources[i];
          }
          //}
      }
      public void printResourceList() //method to print out resourceList
      { 
          String resourceName = new String(); //String variable to keep track of resource Name
          System.out.println(resources);
          
          
          /*
          for(int i =0; i < 5; i++)          
          {
              switch(i)
              {
                  case 0:
                      resourceName = "Soy";
                      break;
                  case 1:
                      resourceName = "Hemp";
                      break;     
                  case 2:
                      resourceName = "Plastic";
                      break;
                  case 3:
                      resourceName = "Glass";
                      break;
                  case 4:
                      resourceName = "Steel";
                      break; 
              }
            */   
        //      System.out.println(resourceName+": "+resourceList.get(i).getResource()); //arrayList uses object Resource to call method getResource()

     //     }
          
          
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

