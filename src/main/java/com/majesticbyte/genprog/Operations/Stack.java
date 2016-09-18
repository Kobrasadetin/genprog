/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.majesticbyte.genprog.Operations;

/**
 *
 * @author mkarjanm
 */
public class Stack {
    
    private int maxSize;
    private int currentSize;
    private int pointer;
    private boolean overflow;
    private double[] data;

    public int getMaxSize() {
        return maxSize;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public boolean isOverflown() {
        return overflow;
    }
    
    public Stack(int maxSize)
    {
        this.maxSize = maxSize;
        currentSize= 0;
        pointer = -1;
        overflow = false;
        data = new double[maxSize];
    }
    
    public boolean push (double value)
    {
        if (pointer==maxSize)
        {
            overflow = true;
            return false;
        }
        
        pointer++;
        data[pointer] = value;       
        return true;
    }
    
    public boolean pop (int regNumber, Registry registry)
    {
         if (pointer<0)
         {
             return false;
         }
         registry.set(regNumber, data[pointer]);
         pointer--;
         return true;
    }
    
}
