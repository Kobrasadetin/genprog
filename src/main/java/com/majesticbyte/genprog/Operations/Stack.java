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
    
    private final int maxSize;
    private int pointer;
    private boolean overflow;
    private final double[] data;

    /**
     *
     * @return
     */
    public int getMaxSize() {
        return maxSize;
    }

    /**
     *
     * @return
     */
    public int getCurrentSize() {
        return pointer;
    }

    /**
     *
     * @return
     */
    public boolean isOverflown() {
        return overflow;
    }
    
    public boolean isEmpty() {
        return pointer == 0;
    }
    
    /**
     *
     * @param maxSize
     */
    public Stack(int maxSize)
    {
        this.maxSize = maxSize;
        pointer = 0;
        overflow = false;
        data = new double[maxSize];
    }
    
    /**
     *
     * @param value
     * @return
     */
    public boolean push (double value)
    {
        if (pointer==maxSize)
        {
            overflow = true;
            return false;
        }
        data[pointer++] = value; 
        return true;
    }
    
    /**
     *
     * @param regNumber
     * @param registry
     * @return
     */
    public boolean pop (int regNumber, Registry registry)
    {
         if (pointer<1)
         {
             return false;
         }
         registry.set(regNumber, data[--pointer]);
         return true;
    }
    
}
