/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.majesticbyte.util;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mkarjanm
 */
public class BinarySetTest {
    BinarySet A, B;
    
    public BinarySetTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        A= new BinarySet(370);
        B= new BinarySet(370);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of union method, of class BinarySet.
     */
    @Test
    public void testUnion() {
        System.out.println("union");
        A.add(0);
        A.add(1);
        A.add(2);
        A.add(222);
        A.add(142);
        A.add(112);
        B.add(222);
        B.add(142);
        B.add(4);
        B.union(A);
        assertTrue(B.contains(0));
        assertTrue(B.contains(1));
        assertTrue(B.contains(2));
        assertTrue(B.contains(222));
    }

    /**
     * Test of contains method, of class BinarySet.
     */
    @Test
    public void testAddContainsRemove() {
        System.out.println("add, contain, remove");
        assertFalse(A.contains(369));
        A.add(369);
        assertTrue(A.contains(369));
        A.remove(369);
        assertFalse(A.contains(369));
    }

    /**
     * Test of substract method, of class BinarySet.
     */
    @Test
    public void testSubstract() {
        System.out.println("substract");
        assertFalse(B.contains(0));
        A.add(0);
        A.add(1);
        A.add(2);
        A.add(222);
        A.add(142);
        A.add(112);
        B.add(222);
        B.add(142);
        B.add(4);
        B.substract(A);
        assertFalse(B.contains(0));
        assertTrue(B.contains(4));
        assertFalse(B.contains(222));
    }
        @Test
    public void testCount() {
        System.out.println("substract");
        assertEquals(0, B.count());
        assertEquals(0, A.count());
        A.add(0);
        A.add(1);
        A.add(2);
        A.add(222);
        A.add(142);
        A.add(112);
        assertEquals(6, A.count());
        B.add(222);
        B.add(142);
        B.add(4);
        assertEquals(3, B.count());
        B.substract(A);
        assertEquals(1, B.count());
        assertEquals(6, A.count());
    }

    @Test
    public void testContains() {
        System.out.println("contains");
        A.add(0);
        assertTrue(A.contains(0));
    }

    @Test
    public void testListValues() {
        System.out.println("listValues");
         A.add(0);
         A.add(122);
         A.add(172);
        Integer[] contents = new Integer[256];
        A.listValues(contents);
        assertTrue(contents[0] == 3);
        assertTrue(contents[1] == 0);
        assertTrue(contents[2] == 122);
        assertTrue(contents[3] == 172);
    }

    @Test
    public void testAdd() {
        System.out.println("add");
        A.add(0);
        A.add(1);
        assertTrue(A.contains(1));
    }

    @Test
    public void testRemove() {
        System.out.println("remove");
        System.out.println("add");
        A.add(0);
        A.add(1);
        A.remove(0);
        assertFalse(A.contains(0));
    }

    @Test
    public void testClear() {
        System.out.println("clear");
        A.add(0);
        A.add(1);
        A.clear();
        assertFalse(A.contains(0));
        assertFalse(A.contains(1));
    }
    
}
