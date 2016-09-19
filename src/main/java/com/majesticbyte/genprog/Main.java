package com.majesticbyte.genprog;

import com.majesticbyte.genprog.Operations.OpNode;
import com.majesticbyte.genprog.Operations.Registry;
import com.majesticbyte.genprog.Operations.Stack;
import java.util.ArrayList;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mkarjanm
 */
class OpCallCounter {

    public int callCount = 0;

    public void Call() {
        callCount++;
    }
}

class OpNodeMockup implements OpNode {

    public int value = 0;
    private OpCallCounter counter;

    OpNodeMockup(int value, OpCallCounter counter) {
        this.value = value;
        this.counter = counter;
    }

    @Override
    public boolean call(Stack stack, Registry registers) {
        counter.callCount++;
        return true;
    }
}


/**
 *
 * @author mkarjanm
 */
public class Main {

    /**
     * No reason for having a main method at the moment.
     * For some reason my NetBeans refuses to do debug test runs (due to Maven?), so this is used for test debug
     * @param args
     */
    public static void main(String[] args) {
        Solver solver = new Solver();
        

    }

}
