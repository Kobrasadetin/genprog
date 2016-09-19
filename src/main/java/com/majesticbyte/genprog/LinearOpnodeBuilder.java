/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.majesticbyte.genprog;

import com.majesticbyte.genprog.Operations.BasicOperation;
import com.majesticbyte.genprog.Operations.BasicOperation.Opcode;
import java.util.ArrayList;

/**
 * Usage: add operations first, then ranges for the operations. Ranges are inclusive.
 * If either of the ranges is omitted, r1 defaults to [0,0], r2 defaults to [1,1].
 * @author mkarjanm
 */
public class LinearOpnodeBuilder {

    private  ArrayList<Opcode> currentOperation = new ArrayList();
    private int r1s = 0, r1e = 0, r2s = 1, r2e = 1;
    private int r2multiplier = 1;
    private ArrayList<BasicOperation> operations = new ArrayList();
    private boolean currentListFinal = false;

    public LinearOpnodeBuilder addOperation(Opcode operation) {
        if (currentListFinal) addOpnodes();
        currentOperation.add(operation);
        return this;
    }

    public LinearOpnodeBuilder r1Range(int rangeStart, int rangeEnd) {
        r1s = rangeStart;
        r1e = rangeEnd;
        currentListFinal = true;
        return this;
    }

    public LinearOpnodeBuilder r2Range(int rangeStart, int rangeEnd) {
        r2s = rangeStart;
        r2e = rangeEnd;
        currentListFinal = true;
        return this;
    }
    
    public LinearOpnodeBuilder r2StepSize(int multiplier) {
        r2multiplier = multiplier;
        return this;
    }

    private void addOpnodes() {
        for (Opcode operation : currentOperation) {
            for (int r1 = r1s; r1 <= r1e; r1++) {
                for (int r2 = r2s; r2 <= r2e; r2+=r2multiplier) {
                    operations.add(new BasicOperation(operation, r1, r2));
                }
            }
        }      
        reinitialize();
    }

    private void reinitialize() {
        currentListFinal = false;
        currentOperation.clear();
        r1s = 0;
        r1e = 0;
        r2s = 1;
        r2e = 1;
        r2multiplier = 1;
    }
    
    public  ArrayList<BasicOperation> toArray()
    {
        addOpnodes();
        return operations;
    }

}
