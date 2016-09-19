/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.majesticbyte.genprog;

import com.majesticbyte.genprog.Operations.BasicOperation;
import com.majesticbyte.genprog.Operations.BasicOperation.Opcode;
import com.majesticbyte.genprog.Operations.OpNode;
import com.majesticbyte.genprog.Operations.Registry;
import com.majesticbyte.genprog.Operations.Stack;
import java.util.ArrayList;
import java.util.Objects;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mkarjanm
 */
public class LinearOpnodeBuilderTest {
    
    
    public LinearOpnodeBuilderTest() {
    }
    
    @Before
    public void setUp() {
    }

    @Test
    public void testAddOperation() {
        System.out.println("addOperation");
        BasicOperation.Opcode operation = Opcode.mul;
        LinearOpnodeBuilder instance = new LinearOpnodeBuilder();
        ArrayList<? extends OpNode> result = instance.addOperation(operation).toArray();
        assertEquals(1, result.size());
        assertEquals(new BasicOperation(Opcode.mul, 0 , 1), result.get(0));
    }

    @Test
    public void testR1Range() {
        System.out.println("r1Range");
        int rangeStart = 0;
        int rangeEnd = 2;
        BasicOperation.Opcode operation = Opcode.add;
        LinearOpnodeBuilder instance = new LinearOpnodeBuilder();
        ArrayList<? extends OpNode> result = instance.addOperation(Opcode.add).r1Range(rangeStart, rangeEnd).toArray();
        assertEquals(3, result.size());
        assertEquals(new BasicOperation(Opcode.add, 0 , 1), result.get(0));
        assertEquals(new BasicOperation(Opcode.add, 1 , 1), result.get(1));
        assertEquals(new BasicOperation(Opcode.add, 2 , 1), result.get(2));
    }

    @Test
    public void testR2Range() {
        System.out.println("r2Range");
        int rangeStart = 3;
        int rangeEnd = 5;
        BasicOperation.Opcode operation = Opcode.add;
        LinearOpnodeBuilder instance = new LinearOpnodeBuilder();
        ArrayList<? extends OpNode> result = instance.addOperation(Opcode.add).r2Range(rangeStart, rangeEnd).toArray();
        assertEquals(3, result.size());
        assertEquals(new BasicOperation(Opcode.add, 0 , 3), result.get(0));
        assertEquals(new BasicOperation(Opcode.add, 0 , 4), result.get(1));
        assertEquals(new BasicOperation(Opcode.add, 0 , 5), result.get(2));
    }

    @Test
    public void testR2StepSize() {
        System.out.println("r2StepSize");
        int multiplier = 4;
        int rangeStart = 3;
        int rangeEnd = 9;
        BasicOperation.Opcode operation = Opcode.add;
        LinearOpnodeBuilder instance = new LinearOpnodeBuilder();
        ArrayList<? extends OpNode> result = instance.addOperation(Opcode.add).r2Range(rangeStart, rangeEnd).r2StepSize(multiplier).toArray();
        assertEquals(2, result.size());
        assertEquals(new BasicOperation(Opcode.add, 0 , 3), result.get(0));
        assertEquals(new BasicOperation(Opcode.add, 0 , 7), result.get(1));
    }
    
}
