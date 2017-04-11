/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resourcebank;

/**
 *
 * @author Chadwick J Davis
 * CS 56
 * April-9-2017
 * Purpose: ResourceBank class and AResource class for 
 *          Settlers Of Mars Project. Contains an arrayList of AResource objects
 *          and a simple driver to test the code.
 */

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.*;



public class ResourceBank {
       
        
    ArrayList<AResource> resourceList = new ArrayList<AResource>(); //creating an ArrayList of AResource objects
    
      public ResourceBank()
      {
                    
          AResource soy = new AResource();    //creating objects from AResource class
          AResource hemp = new AResource();
          AResource plastic = new AResource();
          AResource glass = new AResource();
          AResource steel = new AResource();          
                              
          //Adding AResource objects to resourceList
          resourceList.add(soy);  //Value 0 in arrayList
          resourceList.add(hemp); //Value 1 in arrayList
          resourceList.add(plastic); //Value 2 in ArrayList
          resourceList.add(glass);  //Value 3 in ArrayList
          resourceList.add(steel);  //Value 4 in arrayList
                    
      }
    
      public void bankDrawResource(String resourceName, int amount) //Method requires a correct string variable: "SOY", "HEMP", "PLASTIC", "GLASS", or "STEEL" and an int variable to control how many of said resource
      {                                                             ////Method uses resourceList to call AResource.returnResource(amount) with correct Resource name
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
      
      public void bankReturnResource(String resourceName,int amount) //Method requires a correct string variable: "SOY", "HEMP", "PLASTIC", "GLASS", or "STEEL" and an int variable to control how many of said resource
      {                                                              //Method uses resourceList to call AResource.returnResource(amount) with correct Resource name
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
      
      public void printResourceList() //method to print out resourceList
      { 
          String resourceName = new String(); //String variable to keep track of resource Name
          
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
               
              System.out.println(resourceName+": "+resourceList.get(i).getResource()); //arrayList uses object AResource to call method getResource()
          }
          
          
      }
    
    class AResource {
       
        private int resourceAmount;
        
        public AResource()
        {
            resourceAmount = 19;
        }
        
        public void drawResource(int amount) //method to take desired amount of resources from the Bank, provided the desired number is appropriate
        {
            if(resourceAmount-amount >=0) resourceAmount -=amount;
            else System.out.println("No more of desired resource");            
        }
        
        public void returnResource(int amount) //method to return desired amount of resources from the Bank, provided the desired number is appropriate
        {
            if(resourceAmount+amount <=19) resourceAmount +=amount;
            else System.out.println("Too many of desired resource");
        }
        
        public int getResource() //returns number of resource
        {
            return resourceAmount;
        }
    }
    
    
    public static void main(String[] args) {
        
        ResourceBank TheResourceBank = new ResourceBank();
                       
        
        TheResourceBank.bankDrawResource("SOY", 15);
        TheResourceBank.bankDrawResource("PLASTIC", 2);
        
        TheResourceBank.bankDrawResource("SOY", 1);
        TheResourceBank.bankReturnResource("SOY", 16);
        
        
        
        
        TheResourceBank.bankReturnResource("BRICK", 1);
        
        TheResourceBank.printResourceList();   
        
    }
}  

