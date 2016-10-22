/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.majesticbyte.genprog;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mkarjanm
 */
public class PopulationTest {

    Batch batch = new Batch();

    public PopulationTest() {
    }

    @Before
    public void setUp() {
        for (int i = 0; i < 4; i++) {
            ArrayList<Double> input = new ArrayList<>(Arrays.asList((double) i + 1));
            ArrayList<Double> output = new ArrayList<>(Arrays.asList((double) Math.pow(i + 1, 2)));
            batch.add(new DataPoint(input, output));
        }
        
    }

    @Test
    public void testEvaluate() {
        System.out.println("evaluate");
        Evaluator evaluator = null;
        Batch batch = null;
        Population instance = null;
        instance.evaluate(evaluator, batch);
        fail("The test case is a prototype.");
    }

    @Test
    public void testToString() {
        System.out.println("toString");
        Population instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testTournament() {
        System.out.println("tournament");
        double killRatio = 0.0;
        Population instance = null;
        instance.tournament(killRatio, new Evaluator());
        fail("The test case is a prototype.");
    }

    @Test
    public void testPrintFittest() {
        System.out.println("printFittest");
        Population instance = null;
        String expResult = "";
        String result = instance.printFittest( new Evaluator());
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

}
