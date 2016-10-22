/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.majesticbyte.genprog;

import java.util.ArrayList;

/**
 *
 * @author mkarjanm
 */
public class ProgramResult {

    private ArrayList<Double> output;
    private boolean deadlock;

    public ProgramResult(ArrayList<Double> output, boolean deadlock) {
        this.output = output;
        this.deadlock = deadlock;
    }

    public ArrayList<Double> getOutput() {
        return output;
    }

    public void setOutput(ArrayList<Double> output) {
        this.output = output;
    }

    public boolean isDeadlock() {
        return deadlock;
    }

    public void setDeadlock(boolean deadlock) {
        this.deadlock = deadlock;
    }

    @Override
    public String toString() {
        return "ProgramResult{" + output + ", deadlock= " + deadlock + '}';
    }

    
}
