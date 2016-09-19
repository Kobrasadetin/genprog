/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.majesticbyte.genprog.Operations;

/**
 *
 * @author mkarjanm
 */
public interface OpNode {

    /**
     * Operations on linear genetic programs use a stack and registers to hold state.
     * The registry has a program counter.
     * @param stack         is the Stack (@see com.majesticbyte.genprog.Operations#Stack)
     * @param registers     is the Registry (@see com.majesticbyte.genprog.Operations#Registry)
     * @return              returns true if the operation succeeded
     */
    public boolean call(Stack stack, Registry registers);
}
