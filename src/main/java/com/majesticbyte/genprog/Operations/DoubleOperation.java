/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.majesticbyte.genprog.Operations;

import java.util.Random;

/**
 *
 * @author mkarjanm
 */
public class DoubleOperation implements OpNode {

    private final OpNode node1;
    private final OpNode node2;
    private final Random rng;

    public DoubleOperation(OpNode node1, OpNode node2, Random rng) {
        this.rng = rng;
        this.node1 = node1;
        this.node2 = node2;
    }

    @Override
    public boolean call(Stack stack, Registry registers) {
         //return node1.call(stack, registers);
        if (rng.nextInt(16)!=0) {
            return node1.call(stack, registers);
        } else {
            return node2.call(stack, registers);
        }
    }


}
