/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package som;

import javafx.scene.shape.Polygon;

/**
 *
 * @author makogenq
 */
    class Resource extends Polygon{
       
        private int resourceAmount;
        
        public Resource()
        {
//            super(100,100,200,100,200,200,200,300,200,400,100,400,0,400, 0,300, 0,200,0, 100);

            resourceAmount = 0;
        }
        public Resource(int value){
            resourceAmount=value;
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