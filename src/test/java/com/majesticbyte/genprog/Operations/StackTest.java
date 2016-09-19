/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.majesticbyte.genprog.Operations;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mkarjanm
 */
public class StackTest {
    
    Stack instance = new Stack(8);
    
    public StackTest() {
    }
    
    @Before
    public void setUp() {
    }

    @Test
    public void testGetMaxSize() {
        System.out.println("getMaxSize");
        int expResult = 8;
        int result = instance.getMaxSize();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetCurrentSize() {
        System.out.println("getCurrentSize");
        int expResult = 0;
        int result = instance.getCurrentSize();
        assertEquals(expResult, result);
    }

    @Test
    public void testIsOverflown() {
        System.out.println("isOverflown");
        assertEquals(false, instance.isOverflown());
        for (int i = 0; i<9; i++)
        {
            instance.push(42.0);
        }
        assertEquals(true, instance.isOverflown());
    }

    @Test
    public void testPush() {
        System.out.println("push");
        double value = 0.0;
        boolean expResult = true;
        boolean result = instance.push(value);
        assertEquals(expResult, result);
        assertEquals(1, instance.getCurrentSize());
    }

    @Test
    public void testPop() {
        System.out.println("pop");
        Registry registry = new Registry(8);
        boolean expResult = true;
        instance.push(42.0);
        assertEquals(1, instance.getCurrentSize());
        boolean result = instance.pop(0, registry);
        assertEquals(0, instance.getCurrentSize());
        assertEquals(expResult, result);
    }
    
}
