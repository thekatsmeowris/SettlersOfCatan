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
    class Resource {
       
        private int resourceAmount;
        
        public Resource()
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