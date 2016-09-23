/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.majesticbyte.genprog;

import com.majesticbyte.genprog.Operations.OpNode;
import com.majesticbyte.genprog.Operations.Registry;
import com.majesticbyte.genprog.Operations.Stack;
import java.util.ArrayList;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mkarjanm
 */
public class BasicLGATest {

    Random testRng = new Random();
    BasicLGA test1;
    BasicLGA test2;
    OpNode operation1;
    OpNode operation2;
    OpNode operation3;
    ArrayList<OpNode> mockupOperations = new ArrayList<>();
    OpCallCounter opCallCounter = new OpCallCounter();
    DataPoint data = new DPMockup();

    class OpCallCounter {

        public int callCount = 0;
        public int[] callArray = new int[256];

        public void Call(int callType) {
            callCount++;
            callArray[callType]++;
        }

        public void clear() {
            callCount = 0;
            callArray = new int[256];
            for(int i = 0; i<256; i++)
            {
                callArray[i] = 0;
            }
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
            counter.Call(value);
            return true;
        }

    }

    class DPMockup extends DataPoint {

        public DPMockup() {
            super(null, null);
        }

    }

    public BasicLGATest() {
    }

    @Before
    public void setUp() {
        operation1 = new OpNodeMockup(1, opCallCounter);
        operation2 = new OpNodeMockup(2, opCallCounter);
        operation3 = new OpNodeMockup(3, opCallCounter);
        mockupOperations.add(operation1);
        mockupOperations.add(operation2);
        mockupOperations.add(operation3);
        test1 = new BasicLGA(mockupOperations, 12, 10, testRng);
        test2 = new BasicLGA(mockupOperations, 16, 10, testRng);
    }

    @Test
    public void testCombine() {
        System.out.println("combine");
        Genotype a = test1;
        Genotype b = test2;
        BasicLGA instance = new BasicLGA(testRng);
        instance.combine(a, b);
        opCallCounter.clear();
        assertTrue(opCallCounter.callCount == 0);
        instance.getPhenotype().calculate(data);
        assertTrue(opCallCounter.callCount >= 12);
    }

    @Test
    public void testGetPhenotype() {
        System.out.println("getPhenotype");
        BasicLGA instance = test1;
        Phenotype result = instance.getPhenotype();
        assertEquals(BasicLGAPhenotype.class, result.getClass());
    }

    @Test
    public void testCloneRandomized() {
        System.out.println("cloneRandomized");
        BasicLGA instance = test1;
        opCallCounter.clear();
        Genotype result = instance.cloneRandomized();
        result.getPhenotype().calculate(data);
        assertTrue(opCallCounter.callCount >= 12);
    }

}
