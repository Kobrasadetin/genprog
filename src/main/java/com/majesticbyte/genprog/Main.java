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

class DPMockup extends DataPoint {

}

/**
 *
 * @author mkarjanm
 */
public class Main {

    /**
     * No reason for having a main method at the moment.
     * For some reason my NetBeans refuses to do debug test runs, so this is used for test debug
     * @param args
     */
    public static void main(String[] args) {
        Random testRng = new Random();
        BasicLGA test1;
        BasicLGA test2;
        OpNode operation1;
        OpNode operation2;
        OpNode operation3;
        ArrayList<OpNode> mockupOperations = new ArrayList<>();
        OpCallCounter opCallCounter = new OpCallCounter();
        DataPoint data = new DPMockup();

        operation1 = new OpNodeMockup(1, opCallCounter);
        operation2 = new OpNodeMockup(2, opCallCounter);
        operation3 = new OpNodeMockup(3, opCallCounter);
        mockupOperations.add(operation1);
        mockupOperations.add(operation2);
        mockupOperations.add(operation3);
        test1 = new BasicLGA(mockupOperations, 12, testRng);
        test2 = new BasicLGA(mockupOperations, 16, testRng);

        System.out.println("combine");
        Genotype a = test1;
        Genotype b = test2;
        BasicLGA instance = new BasicLGA(testRng);
        instance.combine(a, b);
        instance.getPhenotype().calculate(data);

    }

}
