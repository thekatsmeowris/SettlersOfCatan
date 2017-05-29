/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package som;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author David Kapanga
 */
public class AI implements Runnable{

    @Override
    public void run() {
         Lock lock =new ReentrantLock();
        Condition startPlay=lock.newCondition();
        
    }    
}
    
