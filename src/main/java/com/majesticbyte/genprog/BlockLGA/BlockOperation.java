/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.majesticbyte.genprog.BlockLGA;

import com.majesticbyte.genprog.Operations.OpNode;
import com.majesticbyte.genprog.Operations.Registry;
import com.majesticbyte.genprog.Operations.Stack;

/**
 *
 * @author mkarjanm
 */
public class BlockOperation implements OpNode {

    public static OpNode forLoop(int counter, int compareTo) {
        return new BlockOperation("forLoop", counter, compareTo);
    }
    public static OpNode endLoop = new BlockOperation("endLoop");

    private String name;
    private int countRegister;
    private int compareRegister;

    public BlockOperation(String name, int countRegister, int compareRegister) {
        this.name = name;
        this.countRegister = countRegister;
        this.compareRegister = compareRegister;
    }

    public BlockOperation(String name) {
        this.name = name;
    }

    @Override
    public boolean call(Stack stack, Registry registers) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    @Override
    public String toString() {
        return "{" + name + ", R" + countRegister + " < R" + compareRegister + '}';
    }

}
