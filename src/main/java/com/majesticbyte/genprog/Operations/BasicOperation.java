/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.majesticbyte.genprog.Operations;

import java.util.Objects;

/**
 *
 * @author mkarjanm
 */
public class BasicOperation implements OpNode {

    /*
    *   Inner classes
     */
    /**
     *
     */
    public enum Opcode {

        /**
         * No operation
         */
        noop(0),
        /**
         * Multiply
         */
        mul(1),
        /**
         * Add
         */
        add(2),
        /**
         * Subtract
         */
        sub(3),
        /**
         * Divide
         */
        div(4),
        /**
         * Push r1 to stack. R2 is not used.
         */
        push(5),
        /**
         * Pop from stack and set r1 to value. R2 is not used.
         */
        pop(6),
        /**
         * Compare
         */
        comp(7),
        /**
         * Jump to position set in r2 if r1 is zero
         */
        jzer(8),
        /**
         * Jump to position set in r2 if r1 is negative
         */
        jneg(9),
        /**
         * Jump to position set in r2 if r1 is positive
         */
        jpos(10),
        /**
         * Jump to position set in r2
         */
        jump(11),
        /**
         * Set register r1 to value in r2 (TODO details on implementation)
         */
        set(12),
        /**
         * Copy value from r2 to r1
         */
        mov(13),
        /**
         * Put state of stack in r1: -1 empty, 0 has next, 1 overflown
         */
        stck(14);

        private final int operation;

        Opcode(int operation) {
            this.operation = operation;
        }
    }

    /**
     * Jump conditions
     */
    public enum JumpCondition {

        /**
         * none
         */
        none,
        /**
         * positive value
         */
        pos,
        /**
         * negative value
         */
        neg,
        /**
         * value zero
         */
        zero;
    }

    // koska Javassa ei ole delegaatteja, tämä rakenne vaikutti järkevimmältä
    //
    private static interface ArgumentOperation {

        public boolean call(Stack stack, Registry registers, int r1, int r2);

    }

    private static class Noop implements ArgumentOperation {

        @Override
        public boolean call(Stack stack, Registry reg, int r1, int r2) {
            return true;
        }
    }

    private static class Multiply implements ArgumentOperation {

        @Override
        public boolean call(Stack stack, Registry reg, int r1, int r2) {
            double result = reg.get(r1) * reg.get(r2);
            reg.set(r1, result);
            return true;
        }
    }

    private static class Add implements ArgumentOperation {

        @Override
        public boolean call(Stack stack, Registry reg, int r1, int r2) {
            double result = reg.get(r1) + reg.get(r2);
            reg.set(r1, result);
            return true;
        }
    }

    private static class Divide implements ArgumentOperation {

        @Override
        public boolean call(Stack stack, Registry reg, int r1, int r2) {
            double result = reg.get(r1) / reg.get(r2);
            reg.set(r1, result);
            return true;
        }
    }

    private static class Subtract implements ArgumentOperation {

        @Override
        public boolean call(Stack stack, Registry reg, int r1, int r2) {
            double result = reg.get(r1) - reg.get(r2);
            reg.set(r1, result);
            return true;
        }
    }

    private static class Push implements ArgumentOperation {

        @Override
        public boolean call(Stack stack, Registry reg, int r1, int r2) {
            return stack.push(reg.get(r1));
        }
    }

    private static class Pop implements ArgumentOperation {

        @Override
        public boolean call(Stack stack, Registry reg, int r1, int r2) {
            return stack.pop(r1, reg);
        }
    }

    private static class Compare implements ArgumentOperation {

        @Override
        public boolean call(Stack stack, Registry reg, int r1, int r2) {
            double v1 = reg.get(r1);
            double v2 = reg.get(r2);
            if (v1 > v2) {
                reg.set(r1, 1.0);
                return true;
            }
            if (v1 < v2) {
                reg.set(r1, -1.0);
                return true;
            }
            reg.set(r1, 0.0);
            return true;
        }
    }

    private static class Move implements ArgumentOperation {

        @Override
        public boolean call(Stack stack, Registry reg, int r1, int r2) {
            reg.set(r1, reg.get(r2));
            return true;
        }
    }

    private static class Jump implements ArgumentOperation {
        //jump to arg2 if arg1 satisfies condition

        private final JumpCondition condition;

        public Jump(JumpCondition condition) {
            this.condition = condition;
        }

        @Override
        public boolean call(Stack stack, Registry reg, int r1, int r2) {
            boolean jumpcond = true;
            if (condition != JumpCondition.none) {
                double v1 = reg.get(r1);
                if (condition == JumpCondition.pos && v1 < 0) {
                    jumpcond = false;
                }
                if (condition == JumpCondition.neg && v1 > 0) {
                    jumpcond = false;
                }
                if (condition == JumpCondition.zero && v1 != 0) {
                    jumpcond = false;
                }
            }
            if (jumpcond == true) {
                reg.setPC(r2);
            }
            return true;
        }
    }

    private static class Set implements ArgumentOperation {

        @Override
        public boolean call(Stack stack, Registry reg, int r1, int r2) {
            // TODO implementation might change?
            // atm we use fixed point with 13 bits for decimals
            double value = (double) r2 / 8192.0;
            reg.set(r1, value);
            return true;
        }
    }

    private static class StackState implements ArgumentOperation {

        @Override
        public boolean call(Stack stack, Registry reg, int r1, int r2) {
            // TODO implementation might change?
            // atm we use fixed point with 13 bits for decimals
            double value = 0.0;
            if (stack.isOverflown()) {
                value = 1;
            }
            if (stack.isEmpty()) {
                value = -1;
            }
            reg.set(r1, value);
            return true;
        }
    }

    /*
    *   BasicOperation class
    *
     */
    private final Opcode operation;
    private final int register1;
    private final int register2;
    private final ArgumentOperation argOp;

    public BasicOperation(Opcode operation, int register1, int register2) {
        this.operation = operation;
        this.register1 = register1;
        this.register2 = register2;
        switch (operation) {
            case mul:
                argOp = new Multiply();
                break;
            case div:
                argOp = new Divide();
                break;
            case add:
                argOp = new Add();
                break;
            case sub:
                argOp = new Subtract();
                break;
            case jump:
                argOp = new Jump(JumpCondition.none);
                break;
            case jpos:
                argOp = new Jump(JumpCondition.pos);
                break;
            case jneg:
                argOp = new Jump(JumpCondition.neg);
                break;
            case jzer:
                argOp = new Jump(JumpCondition.zero);
                break;
            case push:
                argOp = new Push();
                break;
            case pop:
                argOp = new Pop();
                break;
            case comp:
                argOp = new Compare();
                break;
            case mov:
                argOp = new Move();
                break;
            case set:
                argOp = new Set();
                break;
            case stck:
                argOp = new StackState();
                break;
            case noop:
            default:
                argOp = new Noop();
                break;
        }
    }

    /**
     * Operations on linear genetic programs use a stack and registers to hold
     * state. The registry has a program counter.
     *
     * @param stack is the Stack (@see
     * com.majesticbyte.genprog.Operations#Stack)
     * @param registers is the Registry (@see
     * com.majesticbyte.genprog.Operations#Registry)
     * @return returns true if the operation succeeded
     */
    @Override
    public boolean call(Stack stack, Registry registers) {
        return argOp.call(stack, registers, register1, register2);
    }
    
    public static BasicOperation NoOperation()
    {
        return new BasicOperation(Opcode.noop, 0, 0);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.operation);
        hash = 37 * hash + this.register1;
        hash = 37 * hash + this.register2;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BasicOperation other = (BasicOperation) obj;
        if (this.register1 != other.register1) {
            return false;
        }
        if (this.register2 != other.register2) {
            return false;
        }
        if (this.operation != other.operation) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        switch (operation) {
            case set:
                return "{set, " + register1 + ", " + (double) register2 / 8192.0 + '}';
            case mov:
                return "{mov, " + register1 + "<-" + register2 + '}';
            default:
                return "{" + operation.name() + ", " + register1 + ", " + register2 + '}';
        }
    }
    
    public BasicOperation mutation(int random)
    {
        if (this.operation == Opcode.set)
            return new BasicOperation(operation, register1, (register2 * (random + 1024)/ 2048));
        return null;
    }

}
