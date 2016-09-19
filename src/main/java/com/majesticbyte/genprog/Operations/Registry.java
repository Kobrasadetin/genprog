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
public class Registry {

    private double[] values;
    int internalPC = 0;

    /**
     * Registry holds floating point (double) registers and a program counter
     * @param count     is the number of registry entries
     */
    public Registry(int count) {
        values = new double[count];
    }

    /**
     * 
     * @return      number of registry entries
     */
    public int size() {
        return values.length;
    }

    private double[] getValues() {
        return values;
    }

    private void setValues(double[] values) {
        this.values = values;
    }

    /**
     * sets a register to value
     * @param register  register to change
     * @param value     value to store
     */
    public void set(int register, double value) {
        values[register] = value;
    }

    /**
     * returns the value of register
     * @param register      register number
     * @return              the value of register
     */
    public double get(int register) {
        return values[register];
    }

    /**
     * returns current program counter value       
     * @return      current program counter value, positive integer
     */
    public int getPC() {
        return internalPC;
    }
    
    /**
     * sets program counter
     * @param value     new program counter value
     */
    public void setPC(int value)
    {
        internalPC = value;
    }
    
    /**
     * increments program counter by 1
     */
    public void incrementPC()
    {
        internalPC++;
    }

}
