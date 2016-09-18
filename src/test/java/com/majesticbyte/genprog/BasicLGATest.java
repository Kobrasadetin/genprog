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
        public boolean Call(Stack stack, Registry registers) {
            counter.callCount++;
            return true;
        }
    }
    
    class DPMockup extends DataPoint {
        
      
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
        test1 = new BasicLGA(mockupOperations, 12, testRng);
        test2 = new BasicLGA(mockupOperations, 16, testRng);
    }

    @Test
    public void testCombine() {
        System.out.println("combine");
        Genotype a = test1;
        Genotype b = test2;
        BasicLGA instance = new BasicLGA(testRng);
        instance.combine(a, b);
        assertTrue(opCallCounter.callCount == 0);
        instance.getPhenotype().calculate(data);
        assertTrue(opCallCounter.callCount >= 12);
    }

    @Test
    public void testGetPhenotype() {
        System.out.println("getPhenotype");
        BasicLGA instance = null;
        Phenotype expResult = null;
        Phenotype result = instance.getPhenotype();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testCloneRandomized() {
        System.out.println("cloneRandomized");
        BasicLGA instance = null;
        Genotype expResult = null;
        Genotype result = instance.cloneRandomized();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetFeebleness() {
        System.out.println("getFeebleness");
        BasicLGA instance = null;
        Double expResult = null;
        Double result = instance.getFeebleness();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetFeebleness() {
        System.out.println("setFeebleness");
        Double value = null;
        BasicLGA instance = null;
        instance.setFeebleness(value);
        fail("The test case is a prototype.");
    }

}
