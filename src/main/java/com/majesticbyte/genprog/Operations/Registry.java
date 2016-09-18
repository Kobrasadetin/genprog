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

    public Registry(int count) {
        values = new double[count];
    }

    public int size() {
        return values.length;
    }

    private double[] getValues() {
        return values;
    }

    private void setValues(double[] values) {
        this.values = values;
    }

    public void set(int register, double value) {
        values[register] = value;
    }

    public double get(int register) {
        return values[register];
    }

    public int getPC() {
        return internalPC;
    }
    
    public void setPC(int value)
    {
        internalPC = value;
    }
    
    public void incrementPC()
    {
        internalPC++;
    }

}
