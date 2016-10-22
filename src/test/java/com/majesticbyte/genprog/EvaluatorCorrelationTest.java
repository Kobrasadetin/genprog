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
public class EvaluatorCorrelationTest {
    
    public EvaluatorCorrelationTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of evaluate method, of class EvaluatorCorrelation.
     */
    @Test
    public void testEvaluate() {
        System.out.println("evaluate");
        Genotype genotype = null;
        Batch batch = null;
        EvaluatorCorrelation instance = new EvaluatorCorrelation();
        double expResult = 1.0;
        double result = instance.evaluate(genotype, batch);
        assertEquals(expResult, result, 0.001);
        fail("The test case is a prototype.");
    }

    /**
     * Test of correlation method, of class EvaluatorCorrelation.
     */
    @Test
    public void testCorrelation() {
        System.out.println("correlation");
        ArrayList<Double> in = new ArrayList<Double>();
        ArrayList<Double> out = new ArrayList<Double>();
        in.add(0.0);in.add(2.0);in.add(4.0);
        out.add(3.0);out.add(4.0);out.add(5.0);
        EvaluatorCorrelation instance = new EvaluatorCorrelation();
        double expResult = 1.0;
        double result = instance.correlation(in, out);
        assertEquals(expResult, result, 0.01);
        
        in.clear();in.add(12.0);in.add(11.0);in.add(10.0);
        expResult = -1.0;
        result = instance.correlation(in, out);
        assertEquals(expResult, result, 0.01);
        
        in.clear();out.clear();
        in.add(-1.0); out.add(-200.062218489655);
        in.add(10.696152422706632); out.add(100.8241646417322);
        in.add(40.0); out.add(400.682040539909971);
         in.add(160.79611541307906); out.add(1602.5830499036138);
          in.add(757.0); out.add(7501.4830652546245);
          in.add(3823.495116153135); out.add(38593.113200372825);
          in.add(19726.0); out.add(190758.8791205125);
          
          /*int i = 0;
          for(Double val : out)
          {
          out.set(i++, val/5 + 900);
          
          }*/
          System.out.println(Arrays.toString(out.toArray()));
          

        result = instance.correlation(in, out);
        System.out.println(result);
        assertEquals(expResult, result, 0.01);
    }
    /*input:[0.0], expected :[-1.0]
output:ProgramResult{[-4833.062218489655], deadlock= false}
input:[1.5], expected :[10.696152422706632]
output:ProgramResult{[547.8241646417322], deadlock= false}
input:[3.0], expected :[40.0]
output:ProgramResult{[3.682040539909971], deadlock= false}
input:[4.5], expected :[160.79611541307906]
output:ProgramResult{[52.5830499036138], deadlock= false}
input:[6.0], expected :[757.0]
output:ProgramResult{[1901.4830652546245], deadlock= false}
input:[7.5], expected :[3823.495116153135]
output:ProgramResult{[15593.113200372825], deadlock= false}
input:[9.0], expected :[19726.0]
output:ProgramResult{[100758.8791205125], deadlock= false}
input:[10.5], expected :[102326.36813613464]
output:ProgramResult{[520090.89365339995], deadlock= false}*/
}
